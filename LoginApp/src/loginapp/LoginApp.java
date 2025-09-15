/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loginapp;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Ebby
 */
public class LoginApp {

   
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== Welcome to the Registration and Login System ===");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Create a username: ");
        String username = scanner.nextLine();

        System.out.print("Create a password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your South African cell phone number (include +27): ");
        String cellNumber = scanner.nextLine();

        // Register user and display message
        String registrationMessage = login.registerUser(username, password, cellNumber, firstName, lastName);
        System.out.println(registrationMessage);

        if (!registrationMessage.equals("User successfully registered!")) {
            System.out.println("Registration failed. Exiting program.");
            return;
        }

        System.out.println("\n=== Login ===");

        System.out.print("Enter your username: ");
        String loginUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = scanner.nextLine();

        String loginMessage = login.returnLoginStatus(loginUsername, loginPassword);
        System.out.println(loginMessage);
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

    
    

