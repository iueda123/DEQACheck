package iu.SpringBoot.Vaadin.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Lightweight logger to track UI actions and related errors.
 */
public final class UiActionLogger {

    private static final Logger log = LoggerFactory.getLogger("UI_ACTIONS");

    private UiActionLogger() {
    }

    public static void logAction(String action, Map<String, ?> details) {
        log.info("action={} user={} {}", action, getCurrentUsername(), format(details));
    }

    public static void logError(String action, Throwable throwable, Map<String, ?> details) {
        log.error(
                "action={} user={} {} message={}",
                action,
                getCurrentUsername(),
                format(details),
                throwable.getMessage(),
                throwable);
    }

    static String getCurrentUsername() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getName() != null) {
                return authentication.getName();
            }
        } catch (Exception ignored) {
            // Fall through to anonymous
        }
        return "anonymous";
    }

    private static String format(Map<String, ?> details) {
        if (details == null || details.isEmpty()) {
            return "";
        }
        return details.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + String.valueOf(entry.getValue()))
                .collect(Collectors.joining(", "));
    }
}
