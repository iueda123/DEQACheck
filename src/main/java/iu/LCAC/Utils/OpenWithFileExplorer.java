package iu.LCAC.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OpenWithFileExplorer {

  public static void main(String[] args) throws IOException {
    OpenWithFileExplorer.exploreParent("/tmp");
    OpenWithFileExplorer.explore("/tmp");
  }

  public static void exploreParent(String open_target) {
    ArrayList<String> command_words = new ArrayList<>();

    if (OsValidator.isUnix()) {
      command_words.add("nautilus");
      command_words.add("-s");
      command_words.add(open_target);
    } else if (OsValidator.isMac()) {
      command_words.add("open");
      command_words.add("-R");
      command_words.add(open_target);
    } else if (OsValidator.isWindows()) {
      open_target = open_target.replaceAll("/", SystemPropertyManager.getFileSeparator());
      command_words.add("explorer.exe");
      command_words.add("/select," + open_target);
    } else {
      System.err.println(
          OpenWithFileExplorer.class.toString()
              + "can not explore in "
              + SystemPropertyManager.getOsName()
              + ".");
      return;
    }

    /* **** Preparing ProcessBuilder **** */
    ProcessBuilder process_builder = new ProcessBuilder();

    /* **** Set Command **** */
    process_builder.command(command_words);

    /* **** Process Start **** */
    Process processing = null;
    try {
      processing = process_builder.start();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    /* **** Receive processing results **** */
    inheritIO(processing.getInputStream(), System.out);
    inheritIO(processing.getErrorStream(), System.err);
  }

  public static void explore(String open_target) {
    ArrayList<String> command_words = new ArrayList<>();

    if (OsValidator.isUnix()) {
      command_words.add("nautilus");
      command_words.add(open_target);
    } else if (OsValidator.isMac()) {
      command_words.add("open");
      command_words.add(open_target);
    } else if (OsValidator.isWindows()) {
      open_target = open_target.replaceAll("/", SystemPropertyManager.getFileSeparator());
      command_words.add("explorer.exe");
      command_words.add(open_target);
    } else {
      System.err.println(
          OpenWithFileExplorer.class.toString()
              + "can not explore in "
              + SystemPropertyManager.getOsName()
              + ".");
      return;
    }

    /* **** Preparing ProcessBuilder **** */
    ProcessBuilder process_builder = new ProcessBuilder();

    /* **** Set Command **** */
    process_builder.command(command_words);

    /* **** Process Start **** */
    Process processing = null;
    try {
      processing = process_builder.start();
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    /* **** Receive processing results **** */
    inheritIO(processing.getInputStream(), System.out);
    inheritIO(processing.getErrorStream(), System.err);
  }

  private static void inheritIO(final InputStream src, final PrintStream dest) {
    new Thread(
            new Runnable() {
              public void run() {
                Scanner sc = new Scanner(src);
                while (sc.hasNextLine()) {
                  dest.println(sc.nextLine());
                }
              }
            })
        .start();
  }
}
