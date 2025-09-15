import javax.swing.JOptionPane;
import java.util.regex.Pattern;

public class LoginApp {

    public static void main(String[] args) {
        Login login = new Login();

        JOptionPane.showMessageDialog(null, "=== Welcome to the Registration and Login System ===");

        // Get user input for registration
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String username = JOptionPane.showInputDialog("Create a username:");
        String password = JOptionPane.showInputDialog("Create a password:");
        String cellNumber = JOptionPane.showInputDialog("Enter your South African cell phone number (include +27):");

        // Register user and display message
        String registrationMessage = login.registerUser(username, password, cellNumber, firstName, lastName);
        JOptionPane.showMessageDialog(null, registrationMessage);

        if (!registrationMessage.equals("User successfully registered!")) {
            JOptionPane.showMessageDialog(null, "Registration failed. Exiting program.");
            return;
        }

        JOptionPane.showMessageDialog(null, "\n=== Login ===");

        // Get login input
        String loginUsername = JOptionPane.showInputDialog("Enter your username:");
        String loginPassword = JOptionPane.showInputDialog("Enter your password:");

        String loginMessage = login.returnLoginStatus(loginUsername, loginPassword);
        JOptionPane.showMessageDialog(null, loginMessage);
    }
}

class Login {
    private String registeredUsername;
    private String registeredPassword;
    private String firstName;
    private String lastName;

    // Validate username
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Validate password complexity
    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find() &&
               Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }

    // Validate South African cell number with country code
    public boolean checkCellPhoneNumber(String number) {
        return Pattern.matches("^\\+27[0-9]{9}$", number);
    }

    // Register the user and return message
    public String registerUser(String username, String password, String cellNumber, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        this.registeredUsername = username;
        this.registeredPassword = password;
        this.firstName = firstName;
        this.lastName = lastName;

        return "User successfully registered!";
    }

    public boolean loginUser(String inputUsername, String inputPassword) {
        return inputUsername.equals(registeredUsername) && inputPassword.equals(registeredPassword);
    }

    // Return login message
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
