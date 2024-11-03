import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Base64;

/**
 * CommandShell is a simple command line interface that allows users to
 * execute shell commands and provides a way to track command usage.
 * The developer information is encoded to prevent modification.
 *
 * Features include:
 * - Change directory command ('cd')
 * - Exit command
 * - Command usage tracking
 * - Suggestions for frequently used commands
 *
 * Developer: Sai Rithwik Kukunuri
 */


public class CommandShell {
    private static final String COMMAND_USAGE_FILE = "command_usage.txt"; // File to store command usage
    private static Map<String, Integer> commandUsageMap = new HashMap<>();
    private static String currentDirectory = System.getProperty("user.dir"); // Tracks current directory
    private static final String DEVELOPER_NAME_ENCODED = "U2FpIFJpdGh3aWsgS3VrdW51cmk=";
    private static boolean developerInfoDisplayed = false;


    private static String getDeveloperName() {
        byte[] decodedBytes = Base64.getDecoder().decode(DEVELOPER_NAME_ENCODED);
        return new String(decodedBytes);
    }

    // Method to display developer information
    private static void displayDeveloperInfo() {
        System.out.println("Developer: " + getDeveloperName());
        developerInfoDisplayed = true; // Set the flag to true after displaying
    }

    // Method to ensure developer info has been displayed
    private static void ensureDeveloperInfoDisplayed() {
        if (!developerInfoDisplayed) {
            System.out.println("WARNING: Developer information not displayed. Please contact the developer admin.");
        }
    }

    /**
     * The main method that runs the command shell application.
     * Initializes the command usage tracking and command registration.
     *
     * @param args command line arguments
     */

    public static void main(String[] args) {
        displayDeveloperInfo();
        loadCommandUsage();

        System.out.println("Welcome to the Command Shell. Type 'exit' to quit.");


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("shell> ");
            String command = scanner.nextLine().trim();

            // Exit command
            if (command.equals("exit")) {
                System.out.println("Exiting Command Shell.");
                break;
            }

            executeCommand(command);
        }

        saveCommandUsage();
        scanner.close();
    }

    /**
     * Executes the command entered by the user.
     *
     * @param command the command to be executed
     */

    private static void executeCommand(String command) {
        if (command.startsWith("cd ")) {
            changeDirectory(command.substring(3).trim());
            return; // No need to execute further commands for `cd`
        }

        try {
            Process process = Runtime.getRuntime().exec(command, null, new java.io.File(currentDirectory));
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            // Read the output of the command
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }

            // Wait for the process to complete
            process.waitFor();

            // Print the output
            if (output.length() > 0) {
                System.out.println(output.toString());
            } else {
                System.out.println("No output.");
            }

            // Log command usage
            logCommandUsage(command);
            suggestCommand(command);

        } catch (Exception e) {
            System.out.println("Error executing command: " + e.getMessage());
        }
    }

    /**
     * Changes the current working directory.
     *
     * @param path the path to the new directory
     */

    private static void changeDirectory(String path) {
        try {
            java.io.File dir = new java.io.File(path);
            if (dir.isDirectory() && dir.exists()) {
                currentDirectory = dir.getCanonicalPath(); // Update current directory
                System.out.println("Changed directory to: " + currentDirectory);
            } else {
                System.out.println("Directory not found: " + path);
            }
        } catch (Exception e) {
            System.out.println("Error changing directory: " + e.getMessage());
        }
    }

    /**
     * Logs the usage of a command by counting its executions.
     *
     * @param command the name of the command executed
     */

    private static void logCommandUsage(String command) {
        commandUsageMap.put(command, commandUsageMap.getOrDefault(command, 0) + 1);
    }

    /**
     * Suggests a command based on the most frequently used commands.
     *
     * @param command the name of the current command
     */

    private static void suggestCommand(String command) {
        // Suggest a command based on usage
        String suggestion = "Suggested Command: ";

        // Find the most frequently used command
        String mostFrequentCommand = null;
        int maxCount = 0;

        for (Map.Entry<String, Integer> entry : commandUsageMap.entrySet()) {
            if (!entry.getKey().equals(command) && entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentCommand = entry.getKey();
            }
        }

        if (mostFrequentCommand != null) {
            suggestion += mostFrequentCommand;
        } else {
            suggestion += "No suggestions available.";
        }

        System.out.println(suggestion);
    }

    /**
     * Loads command usage data from a file to track command usage.
     */

    private static void loadCommandUsage() {
        try {
            if (Files.exists(Paths.get(COMMAND_USAGE_FILE))) {
                Files.lines(Paths.get(COMMAND_USAGE_FILE)).forEach(line -> {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        commandUsageMap.put(parts[0], Integer.parseInt(parts[1]));
                    }
                });
            }
        } catch (Exception e) {
            System.out.println("Error loading command usage: " + e.getMessage());
        }
    }

    /**
     * Saves the current command usage data to a file.
     */

    private static void saveCommandUsage() {
        try (PrintWriter writer = new PrintWriter(COMMAND_USAGE_FILE)) {
            for (Map.Entry<String, Integer> entry : commandUsageMap.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Error saving command usage: " + e.getMessage());
        }
    }
}
