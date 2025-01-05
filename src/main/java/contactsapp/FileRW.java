package contactsapp;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for reading and writing contacts into a csv file.
 */
public class FileRW {

    /**
     * Read a csv file. If it doesnt exists, create one. Create contacts from each line.
     * @return A list of contacts.
     */
    public static List<Contact> readFile() {
        File file = checkForFile();

        if (file == null) {
            System.out.println("Error: File could not be found or opened.");
            return new ArrayList<>();
        }

        List<Contact> contacts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // Read each line from the file.
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                // Ensure the line has the correct number of fields.
                if (data.length >= 4) {
                    // Create a new contact and add it to the list.
                    Contact contact = new Contact(
                        data[0].trim(), // id.
                        data[1].trim(), // First name.
                        data[2].trim(), // Last name.
                        data[3].trim(), // Phone number.
                        data.length >= 5 ? data[4].trim() : "", // Address optional.
                        data.length >= 6 ? data[5].trim() : ""  // Email optional.
                    );
                    contacts.add(contact);
                } else {
                    System.out.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }

        return contacts;
    }

    /**
     * Write contact information to csv file.
     *
     * @param contacts List of contacts which will be written to file.
     */
    public static void writeFile(List<Contact> contacts) {
        File file = checkForFile();
        if (file == null) {
            System.out.println("Error: File could not be created or opened.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Contact contact : contacts) {
                writer.write(contact.getInformation() + "\n");
            }
            System.out.println("File has been updated.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    /**
     * Helper method to check if file exists, if not create one.
     * @return File that was created or found.
     */
    public static File checkForFile() {
        String fileName = "Contacts.csv";

        try {
            File file = new File(fileName);

            // Check if the file exists
            if (!file.exists()) {
                // If not, create the file
                boolean isFileCreated = file.createNewFile();

                if (isFileCreated) {
                    System.out.println(fileName + " has been created successfully.");
                } else {
                    System.out.println("Failed to create " + fileName);
                }
            } else {
                System.out.println(fileName + " already exists.");
            }
            return file;
        } catch (IOException e) {
            System.out.println("An error occurred while checking or creating the file");
        }
        return null;
    }
}