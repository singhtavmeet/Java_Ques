
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class Regex {
 
    static class InvalidUsernameException extends Exception {
        InvalidUsernameException(String message) { super(message); }
    }
    static class InvalidPasswordException extends Exception {
        InvalidPasswordException(String message) { super(message); }
    }
 
    public static void main(String[] args) {
        final String userPattern = "^(?=.{5,}$)[A-Za-z]+(?:\\s[A-Za-z]+)?$";
        final Pattern userPtrn = Pattern.compile(userPattern);
 
        final String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-+_])(?=\\S+$).{8,20}$";
        final Pattern passPtrn = Pattern.compile(passwordPattern);
 
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter username (one word or two words; min 5 chars): ");
            String userName = sc.nextLine().trim().replaceAll("\\s+", " ");
 
            try {
                validateUsername(userName, userPtrn);
                System.out.println("Username is valid");
            } catch (InvalidUsernameException e) {
                System.out.println("Username error" + e.getMessage());
                return;
            }
 
            System.out.print("Enter password (8–20 chars; a-z, A-Z, digit, special; no spaces): ");
            String password = sc.nextLine();
 
            try {
                validatePassword(password, passPtrn);
                System.out.println("Password is valid ");
            } catch (InvalidPasswordException e) {
                System.out.println("Password error " + e.getMessage());
            }
        }
    }
 
    private static void validateUsername(String userName, Pattern p) throws InvalidUsernameException {
        Matcher m = p.matcher(userName);
        if (!m.matches()) {
            throw new InvalidUsernameException(
                "Must be either one word or two words separated by a single space, " +
                "letters only, and at least 5 characters total. Examples: 'Rohan', 'Mary Jane'."
            );
        }
    }
 
    private static void validatePassword(String password, Pattern p) throws InvalidPasswordException {
        Matcher m = p.matcher(password);
        if (!m.matches()) {
            throw new InvalidPasswordException(
                "Password must be 8–20 characters, include at least one lowercase, uppercase, digit, " +
                "and one special from !@#$%^&*()-+_, and contain no whitespace."
            );
        }
    }
}

 
 
 