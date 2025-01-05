package contactsapp;

/**
 * Class for the contact information.
 */
public class Contact {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;

    /**
     * Constructs a new contact.
     *
     * @param id Id number of the contact.
     * @param firstName First name of the contact.
     * @param lastName Last name of the contact.
     * @param phoneNumber Pohone number of the contact.
     * @param address Address of the contact.
     * @param email Email of the contact.
     */
    public Contact(String id, String firstName, String lastName, String phoneNumber, String address, String email) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setEmail(email);
    }

    /**
     * Updates the contacts information.
     *
     * @param id Id of the contact.
     * @param firstName First name of the contact.
     * @param lastName Last name of the contact.
     * @param phoneNumber Phone number of the contact.
     * @param address Address of the contact.
     * @param email Email of the contact.
     */
    public void updateInfo(String id, String firstName, String lastName, String phoneNumber, String address, String email) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setAddress(address);
        setEmail(email);
    }

    /**
     * Get the contacts information in a form of a string, values are separated by a ",".
     *
     * @return Contacts information in a single String.
     */
    public String getInformation() {
        String information = "";
        information += this.getId();
        information += ",";
        information += this.getFirstName();
        information += ",";
        information += this.getLastName();
        information += ",";
        information += this.getPhoneNumber();
        information += ",";
        information += this.getAddress();
        information += ",";
        information += this.getEmail();

        return information;
    }

    /**
     * Set the id of the contact.
     *
     * @param id Id for the contact.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the id of the contact.
     *
     * @return String containing the id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the first name of the contact.
     *
     * @param firstName First name for the contact.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the first name of the contact.
     *
     * @return First name of the contact.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Set the last name of the contact.
     *
     * @param lastName Last name of the contact.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the last name of the contact.
     *
     * @return Last name of the contact.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Set the phone number of the contact.
     *
     * @param phoneNumber Phone number of the contact.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the phone number of the contact.
     *
     * @return Phone number of the contact.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Set the address of the contact.
     *
     * @param address Address of the contact.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the address of the contact.
     *
     * @return Address of the contact.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Set the email of the contact.
     *
     * @param email Email of the contact.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the Email of the contact.
     *
     * @return Email of the contact.
     */
    public String getEmail() {
        return this.email;
    }

}