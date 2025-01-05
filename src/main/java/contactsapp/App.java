package contactsapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Main class for the app containing creating and managing the window.
 */
public class App {
    public static List<Contact> contacts = new ArrayList<>();

    /**
     * The program starts here.
     *
     * @param args command-line arguments.
     */
    public static void main(final String[] args) {
        final int frameSize = 600;

        // Create the window.
        JFrame frame = new JFrame("Contacts-app");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameSize, frameSize);

        // Panel to hold contacts.
        JPanel contactsPanel = new JPanel();
        contactsPanel.setLayout(new GridBagLayout());
        contactsPanel.setBorder(new EmptyBorder(10, 10, 10, 20));

        // Vertical scrollbar for contact list.
        JScrollPane scrollPane = new JScrollPane(contactsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Panel to hold text fields.
        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(new GridBagLayout());
        informationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Constraints for the information panel.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextArea warningLabel = addWarningLabelToPanel(informationPanel, gbc);

        // Labels and text fields for information panel.
        JTextField textAreaId = addFieldToPanel("ID:", informationPanel, gbc, 1);
        JTextField textAreaFirstName = addFieldToPanel("First name:", informationPanel, gbc, 2);
        JTextField textAreaLastName = addFieldToPanel("Last name:", informationPanel, gbc, 3);
        JTextField textAreaPhone = addFieldToPanel("Phone number:", informationPanel, gbc, 4);
        JTextField textAreaAddress = addFieldToPanel("Address:", informationPanel, gbc, 5);
        JTextField textAreaEmail = addFieldToPanel("Email:", informationPanel, gbc, 6);

        JTextField[] informationFields = {textAreaId, textAreaFirstName,
            textAreaLastName, textAreaPhone,
            textAreaAddress, textAreaEmail
        };

        // Create list of contacts from file. Create button for each contact.
        contacts = FileRW.readFile();
            for (Contact contact : contacts) {
                createContactButton(contact, contactsPanel, 10, frame);
            }

        // Button to save. Center it under text fields
        gbc.gridx = 1;
        gbc.gridy = 7;
        JButton button = new JButton("Save to CSV");
        informationPanel.add(button, gbc);

        // Save button logic.
        button.addActionListener((final ActionEvent e) -> {
            String errorMsg = "";

            // Validate inputs.
            if (!Validate.id(informationFields[0].getText())) {
                errorMsg += "Invalid ID. ";
            }
            if (!Validate.name(informationFields[1].getText())) {
                errorMsg += "Invalid First Name. ";
            }
            if (!Validate.name(informationFields[2].getText())) {
                errorMsg += "Invalid Last Name. ";
            }
            if (!Validate.phone(informationFields[3].getText())) {
                errorMsg += "Invalid Phone Number. ";
            }
            if (!informationFields[4].getText().isEmpty()) {
                if (!Validate.address(informationFields[4].getText())) {
                    errorMsg += "Invalid Address. ";
                }
            }
            if (!informationFields[5].getText().isEmpty()) {
                if (!Validate.email(informationFields[5].getText())) {
                    errorMsg += "Invalid Email. ";
                }
            }

            // If there is invalid data, show error and return.
            if (errorMsg.length() != 0) {
                warningLabel.setText(errorMsg);
                return;
            }

            warningLabel.setText("");

            // Read text fields and create a new contact.
            Contact newContact = new Contact(
                informationFields[0].getText(),
                informationFields[1].getText(),
                informationFields[2].getText(),
                informationFields[3].getText(),
                informationFields[4].getText(),
                informationFields[5].getText());

            contacts.add(newContact);

            // Write the contact to file.
            FileRW.writeFile(contacts);
            createContactButton(newContact, contactsPanel, 10, frame);
        });

            // Add the panels to the frame.
            frame.add(scrollPane, BorderLayout.WEST);
            frame.add(informationPanel, BorderLayout.CENTER);

            // Make the frame visible.
            frame.setVisible(true);
        }

        /**
         * Creates a text field with a label and adds it to the panel.
         *
         * @param label Label text.
         * @param panel Panel where label is added.
         * @param gbc   Layout constraints.
         * @param row   y position for the text field.
         * @return Created JTextArea.
         */
        public static JTextField addFieldToPanel(final String label, final JPanel panel, final GridBagConstraints gbc, final int row) {
            // Label for the text field.
            gbc.gridy = row;
            gbc.gridx = 0;
            JLabel jLabel = new JLabel(label);
            jLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
            panel.add(jLabel, gbc);

            // Add the text field.
            gbc.gridx = 1;
            JTextField textArea = new JTextField(15);
            textArea.setFont(new Font("Helvetica", Font.PLAIN, 12));
            textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(textArea, gbc);

            return textArea;
        }

        /**
         * Adds a JTextArea to the top of the panel that holds warning for invalid inputs.
         *
         * @param panel Panel where to put the text area.
         * @param gbc GridBagConstraints for the text area.
         * @return JTextArea for invalid inputs.
         */
        public static JTextArea addWarningLabelToPanel(final JPanel panel, final GridBagConstraints gbc) {
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;

            // Create a JTextArea for the warning label
            JTextArea warningLabel = new JTextArea(2, 30);
            warningLabel.setForeground(Color.RED);
            warningLabel.setFont(new Font("Helvetica", Font.BOLD, 12));
            warningLabel.setLineWrap(true);
            warningLabel.setWrapStyleWord(true);
            warningLabel.setEditable(false);
            warningLabel.setOpaque(false);
            panel.add(warningLabel, gbc);

            gbc.gridwidth = 1;

            return warningLabel;
        }

        /**
         * Creates button from contact and add it to the panel.
         *
         * @param contact contact of which the button is made from.
         * @param panel Panel where buttons will be added.
         * @param padding Spacing between the buttons.
         * @param frame Frame where the panel is.
         */
        public static void createContactButton(final Contact contact, final JPanel panel, final int padding, final JFrame frame) {
            JButton contactButton = new JButton(contact.getFirstName());
            contactButton.setPreferredSize(new Dimension(100, 35));
            contactButton.setFont(new Font("Helvetica", Font.PLAIN, 16));

            // Create constraints.
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = panel.getComponentCount();
            gbc.insets = new Insets(0, 0, padding, 0);
            gbc.anchor = GridBagConstraints.NORTH;

            panel.add(contactButton, gbc);

            // Display contact details if pressed.
            contactButton.addActionListener((final ActionEvent e) -> {
                showContactDialog(contact, frame, contactButton, panel);
            });

            arrangePanel(panel);
            frame.revalidate();
            frame.repaint();
        }

    /**
     * Show dialog window containing contact information.
     * Window contains button to edit the contact information, close the window and remove the contact.
     *
     * @param contact Contact which information will be displayed.
     * @param frame Frame of which the dialog window will be created from.
     * @param button Button that belongs to the contact.
     * @param panel panel holding contacts, will be rearranged if contact is removed.
     */
    public static void showContactDialog(final Contact contact, final JFrame frame, final JButton button, JPanel panel) {
        // Create a dialog window.
        JDialog dialog = new JDialog(frame, contact.getFirstName(), true);
        dialog.setSize(550, 400);
        dialog.setLayout(new BorderLayout());

        // Create panel for contact details.
        JPanel contactDetails = new JPanel();
        contactDetails.setLayout(new GridBagLayout());
        dialog.add(contactDetails);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JTextArea warningLabel = addWarningLabelToPanel(contactDetails, gbc);

        // Create text fields for panel.
        JTextField textAreaId = addFieldToPanel("ID:", contactDetails, gbc, 1);
        JTextField textAreaFirstName = addFieldToPanel("First Name:", contactDetails, gbc, 2);
        JTextField textAreaLastName = addFieldToPanel("Last Name:", contactDetails, gbc, 3);
        JTextField textAreaPhone = addFieldToPanel("Phone number:", contactDetails, gbc, 4);
        JTextField textAreaAddress = addFieldToPanel("Address:", contactDetails, gbc, 5);
        JTextField textAreaEmail = addFieldToPanel("Email:", contactDetails, gbc, 6);

        // Get the existing information.
        textAreaId.setText(contact.getId());
        textAreaFirstName.setText(contact.getFirstName());
        textAreaLastName.setText(contact.getLastName());
        textAreaPhone.setText(contact.getPhoneNumber());
        textAreaAddress.setText(contact.getAddress());
        textAreaEmail.setText(contact.getEmail());


        // Buttons for save, cancel and remove.
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        JButton removeButton = new JButton("Remove Contact");
        gbc.gridy = 7;
        gbc.gridx = 0;
        contactDetails.add(saveButton, gbc);
        gbc.gridy = 7;
        gbc.gridx = 1;
        contactDetails.add(cancelButton, gbc);
        gbc.gridy = 7;
        gbc.gridx = 2;
        contactDetails.add(removeButton, gbc);

        // Save button logic.
        saveButton.addActionListener((final ActionEvent e) -> {
            String errorMsg = "";

            // Validate inputs.
            if (!Validate.id(textAreaId.getText())) {
                errorMsg += "Invalid ID. ";
            }
            if (!Validate.name(textAreaFirstName.getText())) {
                errorMsg += "Invalid First Name. ";
            }
            if (!Validate.name(textAreaLastName.getText())) {
                errorMsg += "Invalid Last Name. ";
            }
            if (!Validate.phone(textAreaPhone.getText())) {
                errorMsg += "Invalid Phone Number. ";
            }
            if (!textAreaAddress.getText().isEmpty()) {
                if (!Validate.address(textAreaAddress.getText())) {
                    errorMsg += "Invalid Address. ";
                }
            }
            if (!textAreaEmail.getText().isEmpty()) {
                if (!Validate.email(textAreaEmail.getText())) {
                    errorMsg += "Invalid Email. ";
                }
            }

            // If there is invalid data, show error and return.
            if (errorMsg.length() != 0) {
                warningLabel.setText(errorMsg);
                return;
            }

            // Update info, write to file and close the window
            warningLabel.setText("");
            contact.updateInfo(textAreaId.getText(), textAreaFirstName.getText(),
                                textAreaLastName.getText(), textAreaPhone.getText(),
                                textAreaAddress.getText(), textAreaEmail.getText());
            FileRW.writeFile(contacts);
            dialog.dispose();
            button.setText(contact.getFirstName());
            frame.repaint();
        });

        // Cancel button logic.
        cancelButton.addActionListener((final ActionEvent e) -> {
            dialog.dispose();
        });

        // Remove button logic.
        removeButton.addActionListener((final ActionEvent e) -> {
            contacts.remove(contact);
            FileRW.writeFile(contacts);
            Container parent = button.getParent();
            parent.remove(button);
            arrangePanel(panel);
            dialog.dispose();
            frame.revalidate();
            frame.repaint();
        });

        // Show the dialog.
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    /**
     * Creates an empty panel at the bottom of the GridBagConstraints pushing everything up.
     *
     * @param contactsPanel Target of the rearranging.
     */
    public static void arrangePanel(final JPanel contactsPanel) {
        //Find the existing panel and remove it.
        Component[] panelComponents = contactsPanel.getComponents();
        for (Component component : panelComponents) {
            if (component instanceof JPanel) {
                contactsPanel.remove(component);
                break;
            }
        }

        // Create new panel and constraints for it.
        GridBagConstraints fillerGbc = new GridBagConstraints();
        fillerGbc.gridx = 0;
        fillerGbc.gridy = GridBagConstraints.RELATIVE;
        fillerGbc.weighty = 1;
        fillerGbc.fill = GridBagConstraints.VERTICAL;
        contactsPanel.add(new JPanel(), fillerGbc);

        contactsPanel.revalidate();
        contactsPanel.repaint();
    }
}
