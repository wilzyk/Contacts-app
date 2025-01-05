package contactsapp;

/**
 * Validate class contains various methods to validate strings of characters.
 */
public class Validate {

    /**
     * Validates name. Valid names can contain letters and optionally "-" and more letters.
     * @param name Name to be validated.
     * @return true if valid, false if not.
     */
    public static boolean name(String name) {
        return name.matches("[\\p{L}]+(-[\\p{L}]+)*+");
    }

    /**
     * Validates phone number. Valid phone number starts with "+" and follows with 8-15 numbers.
     *
     * @param number String of numbers to be validated.
     * @return true if valid, false if not.
     */
    public static boolean phone(String number) {
        return number.matches("\\+\\d{1,3}\\d{7,12}");
    }

    /**
     * Validates email. First part of the email can contain letters, numbers and some special characters.
     * followed by @ and another set of letters including "." and "-" for the domain name.
     * followed by "." and string of characters for the TLD.
     *
     * @param email String of characters to be validated.
     * @return true if valid, false if not.
     */
    public static boolean email(String email) {
        return email.matches("^[a-zA-Z0-9._-]+\\@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    /**
     * Validates address. Street address can contain letters, spaces and "-".
     * Followed by space and street number and optionally apartment.
     * Followed by space and 5 digit postal code.
     * Followed by space and city name.
     *
     * @param address String of characters to be validated.
     * @return true if valid, false if not.
     */
    public static boolean address(String address) {
        return address.matches("^[\\p{L}\\s-]+\\s\\d+[a-zA-Z0-9\\s-]*\\s\\d{5}\\s[\\p{L}\\s-]+$");
    }

    /**
     * Validates finnish id.
     * Id starts with date of birth in format of day/month/year eq. 11/03/99.
     * Followed by century symbol.
     * Followed by 3 digits.
     * Followd by a letter in a list of 31 letters. Picked by putting date and 3 digits together and % 31.
     *
     * @param id String of charaters to be validated.
     * @return true if valid, false if not.
     */
    public static boolean id(String id) {
        if (!id.matches("^(\\d{2})(\\d{2})(\\d{2})([+-UVWXYABCDEF])(\\d{3})([a-zA-Z0-9])$")) {
            return false;
        }

        int day = Integer.parseInt(id.substring(0, 2));
        int month = Integer.parseInt(id.substring(2, 4));
        int year = Integer.parseInt(id.substring(4, 6));

        if (!validateDate(day, month, year)) {
            return false;
        }

        String c = id.substring(6, 7);
        if (year < 0 || year > 99 || !c.matches("[+-UVWXYABCDEF]")) {
            return false;
        }

        int z = Integer.parseInt(id.substring(7,10));
        if (z < 1 || z > 899) {
            return false;
        }

        char q = id.charAt(10);
        String remainder = "0123456789ABCDEFHJKLMNPRSTUVWXY";
        int qInt = Integer.parseInt(id.substring(0,6) + id.substring(7,10)) % 31;
        return remainder.charAt(qInt) == q;
    }

    /**
     * Helper method to check if day exists in a given month. Takes leap years in consideration.
     *
     * @param day integer for the day.
     * @param month integer for the month.
     * @param year integer for the year.
     * @return true if day exists, false if not
     */
    private static boolean validateDate(int day, int month, int year) {
        if (day < 1 || day > 31 || month < 1 || month > 12) {
            return false;
        }

        int[] month31 = {1, 3, 5, 7, 8, 10, 12};
        int[] month30 = {4, 6, 9, 11};

        if (contains(month31, month)){
            return day >= 1 && day < 32;
        }

        if (contains(month30, month)) {
            return day >= 1 && day < 31;
        }

        boolean isLeapYear = (year % 4 == 0 && (year % 100 == 0 && year % 400 == 0));

        if (month == 2) {
            if (isLeapYear) {
                return day >= 1 && day <= 29;
            } else {
                return day >= 1 && day <= 28;
            }
        }

        return false;
    }

    /**
     * Helper method to check if a number is in a list.
     * @param array Integer array of numbers to be checked.
     * @param number Integer to be checked.
     * @return true if the array contains the number, false if it doesnt.
     */
    private static boolean contains(int[] array, int number) {
        for (int i : array) {
            if (i == number) {
                return true;
            }
        }
        return false;
    }
}
