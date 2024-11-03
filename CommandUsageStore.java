import java.io.*;
import java.util.HashMap;
import java.util.Map;



/**
 * CommandUsageStore is a class responsible for tracking and managing
 * the usage of commands in the command shell application.
 *
 * It provides functionality to load command usage data from a file,
 * save command usage data to a file, track how often commands are
 * used, and delete command usage data as needed.
 *
 * Features include:
 * - Loading command data from a text file.
 * - Saving command data to a text file.
 * - Tracking frequency of command usage.
 * - Deleting specific all command data.
 *
 */

public class CommandUsageStore {
    private static final String FILE_PATH = "command_usage.txt"; // Simple text file to store commands
    private Map<String, Integer> commandFrequency;

    /**
     * Constructor for CommandUsageStore.
     * Initializes the commandFrequency map and loads existing
     * command usage data from the file.
     */

    public CommandUsageStore() {
        commandFrequency = new HashMap<>();
        loadCommandData();
    }

    /**
     * Loads command usage data from a text file and populates the
     * commandFrequency map.
     */

    private void loadCommandData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    commandFrequency.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            // File not found; starting fresh
            System.out.println("No existing command data found. Starting fresh.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves the current command usage data to a text file.
     */

    public void saveCommandData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, Integer> entry : commandFrequency.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tracks the usage of a specific command by incrementing its
     * count in the commandFrequency map and saving the updated
     * data to the file.
     *
     * @param command The command to be tracked.
     */

    public void trackCommand(String command) {
        commandFrequency.put(command, commandFrequency.getOrDefault(command, 0) + 1);
        saveCommandData();
    }

    /**
     * Deletes data for a specific command from the commandFrequency map
     * and updates the file.
     *
     * @param command The command for which data should be deleted.
     */

    public void deleteCommandData(String command) {
        if (commandFrequency.remove(command) != null) {
            saveCommandData();
            System.out.println("Command data deleted for: " + command);
        } else {
            System.out.println("No data found for command: " + command);
        }
    }

    /**
     * Deletes all command data from the commandFrequency map
     * and updates the file.
     */

    public void deleteAllData() {
        commandFrequency.clear();
        saveCommandData();
        System.out.println("All command data has been deleted.");
    }

    /**
     * Returns the current command frequency map.
     *
     * @return A map of commands and their usage frequency.
     */

    public Map<String, Integer> getCommandFrequency() {
        return commandFrequency;
    }
}
