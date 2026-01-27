package iu.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;

//
//Normalize rci5_imaging_modality.answer in human/Human JSONs under share_package/data/**/DE/json*/DE_*human*.json.
//Categories: T1w MRI, T2w MRI, fMRI, dMRI, PET, EEG, MEG, Others.
//Details are kept in parentheses for applicable categories, e.g., fMRI (task), PET (Amyloid, 18F-Florbetapir).
//
public class ModalityHumanNormalizer {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Path base = Paths.get("share_package", "data");
        if (!Files.isDirectory(base)) {
            System.err.println("No data dir: " + base.toAbsolutePath());
            return;
        }

        Pattern jsonDirName = Pattern.compile("json.*");
        Pattern humanJson = Pattern.compile("DE_.*(?i)(human).*\\.json$");

        int processed = 0, updated = 0, skipped = 0, errors = 0;

        try (DirectoryStream<Path> authors = Files.newDirectoryStream(base)) {
            for (Path author : authors) {
                if (!Files.isDirectory(author)) continue;
                Path de = author.resolve("DE");
                if (!Files.isDirectory(de)) continue;

                try (DirectoryStream<Path> subs = Files.newDirectoryStream(de)) {
                    for (Path sub : subs) {
                        if (!Files.isDirectory(sub)) continue;
                        if (!jsonDirName.matcher(sub.getFileName().toString()).matches()) continue;

                        try (DirectoryStream<Path> files = Files.newDirectoryStream(sub, "DE_*.json")) {
                            for (Path f : files) {
                                if (!humanJson.matcher(f.getFileName().toString()).matches()) {
                                    skipped++;
                                    continue;
                                }
                                processed++;
                                try {
                                    boolean changed = normalizeFile(f);
                                    if (changed) updated++;
                                } catch (Exception ex) {
                                    errors++;
                                    System.err.println("ERROR: " + f + " -> " + ex.getMessage());
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.printf(Locale.ROOT, "Done. processed=%d, updated=%d, skipped(non-human)=%d, errors=%d\n",
                processed, updated, skipped, errors);
    }

    private static boolean normalizeFile(Path file) throws IOException {
        JsonNode root = MAPPER.readTree(file.toFile());

        JsonNode ref = root.get("reference_cohort_and_imaging_part");
        if (ref == null || !ref.isObject()) return false;

        JsonNode rci5 = ref.get("rci5_imaging_modality");
        if (rci5 == null) return false;

        String raw = null;
        if (rci5.isObject()) {
            JsonNode ans = rci5.get("answer");
            if (ans != null && !ans.isNull()) raw = ans.isTextual() ? ans.asText() : ans.toString();
        } else if (rci5.isTextual()) {
            raw = rci5.asText();
        }

        String normalized = normalizeAnswer(raw);
        if (normalized == null) return false; // no change

        // Write back
        if (rci5.isObject()) {
            ((ObjectNode) rci5).put("answer", normalized);
        } else {
            ((ObjectNode) ref).put("rci5_imaging_modality", normalized);
        }

        // Pretty print back to file
        byte[] out = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsBytes(root);
        Files.write(file, out, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        System.out.println("UPDATED: " + file);
        return true;
    }

    private static String normalizeAnswer(String raw) {
        if (raw == null) return null;
        String s = stripTrailingPeriod(raw).replace('\u3000', ' ').trim();
        if (s.isEmpty()) return "NR";

        String lower = s.toLowerCase(Locale.ROOT);
        if (lower.equals("nr") || lower.equals("yes")) return "NR";

        String[] parts = s.split("\\s*;\\s*");
        List<String> out = new ArrayList<>();
        for (String part : parts) {
            String p = part.trim();
            if (p.isEmpty()) continue;
            out.add(classifyOne(p));
        }
        if (out.isEmpty()) return "NR";

        String joined = String.join("; ", out);
        if (joined.equals(s)) return null; // unchanged
        return joined;
    }

    private static String classifyOne(String src) {
        String lower = src.toLowerCase(Locale.ROOT);

        // T1w MRI (all variations collapse to plain label)
        if (lower.matches(".*\\bt1\\s*-?weighted\\b.*\\bmri\\b.*")
                || lower.contains("structural mri")
                || lower.matches(".*\\bsmri\\b.*")
                || lower.matches(".*\\bt1w\\b.*")) {
            return "T1w MRI";
        }

        // T2w MRI
        if (lower.contains("t2-weighted") && lower.contains("mri")) {
            return "T2w MRI";
        }
        if (lower.contains("flair")) {
            return "T2w MRI"; // treat FLAIR under T2w MRI category
        }

        // fMRI
        if (lower.contains("fmri")) {
            List<String> details = new ArrayList<>();
            if (lower.contains("rest")) details.add("rest");
            if (lower.contains("task")) details.add("task");
            if (lower.contains("bold")) details.add("BOLD");
            return details.isEmpty() ? "fMRI" : "fMRI (" + String.join(", ", details) + ")";
        }

        // Diffusion
        if (lower.contains("diffusion") || lower.contains("dwi") || lower.contains("dti") || lower.contains("dmri")) {
            List<String> details = new ArrayList<>();
            if (lower.contains("dti")) {
                List<String> metrics = new ArrayList<>();
                if (containsWord(lower, "fa")) metrics.add("FA");
                if (containsWord(lower, "md")) metrics.add("MD");
                if (containsWord(lower, "rd")) metrics.add("RD");
                if (containsWord(lower, "axd") || containsWord(lower, "ad")) metrics.add("AxD");
                if (!metrics.isEmpty()) details.add("DTI: " + String.join(", ", metrics));
                else details.add("DTI");
            }
            return details.isEmpty() ? "dMRI" : "dMRI (" + String.join(", ", details) + ")";
        }

        // PET
        if (lower.contains("pet")) {
            List<String> details = new ArrayList<>();
            if (lower.contains("amyloid") || lower.contains("av45") || lower.contains("florbetapir")) {
                details.add("Amyloid");
                if (lower.contains("florbetapir") || lower.contains("av45")) details.add("18F-Florbetapir");
            }
            if (lower.contains("tau") || lower.contains("flortaucipir") || lower.contains("ftp")) {
                details.add("Tau");
                if (lower.contains("flortaucipir") || lower.contains("ftp")) details.add("18F-Flortaucipir");
            }
            if (lower.contains("fdopa")) details.add("18F-FDOPA");
            if (lower.contains("[11c]") || lower.contains("11c")) details.add("11C tracer");
            return details.isEmpty() ? "PET" : "PET (" + String.join(", ", details) + ")";
        }

        // EEG
        if (lower.contains("eeg")) {
            List<String> details = new ArrayList<>();
            if (lower.contains("rest")) details.add("rest");
            if (lower.contains("hd") || lower.contains("high-density") || lower.contains("128"))
                details.add("HD-128ch");
            return details.isEmpty() ? "EEG" : "EEG (" + String.join(", ", details) + ")";
        }

        // MEG
        if (lower.contains("meg")) {
            if (lower.contains("rest")) return "MEG (rest)";
            return "MEG";
        }

        return "Others";
    }

    private static boolean containsWord(String lower, String w) {
        return lower.matches(".*\\b" + Pattern.quote(w) + "\\b.*");
    }

    private static String stripTrailingPeriod(String s) {
        String t = s == null ? "" : s.trim();
        while (t.endsWith(".")) t = t.substring(0, t.length() - 1);
        return t;
    }
}

