import java.util.ArrayList;
import java.util.Scanner;

// Class for user information
class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

// Class for booking information
class Booking {
    private String userEmail;
    private String reservationDate;
    private String reservationTime;

    public Booking(String userEmail, String reservationDate, String reservationTime) {
        this.userEmail = userEmail;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    @Override
    public String toString() {
        return "Booking for " + userEmail + " on " + reservationDate + " at " + reservationTime;
    }
}

// Main class for the reservation system
public class ReservationSystem {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();
    private static User loggedInUser = null;  // Track logged-in user

    // User registration
    public static void registerUser(String name, String email, String password) {
        users.add(new User(name, email, password));
        System.out.println("User registered successfully!");
    }

    // User login
    public static void loginUser(String email, String password) {
        if (loggedInUser != null) {
            System.out.println("You are already logged in as " + loggedInUser.getName() + ".");
            return;
        }

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful! Welcome, " + loggedInUser.getName() + ".");
                return;
            }
        }
        System.out.println("Invalid email or password.");
    }

    // User logout
    public static void logoutUser() {
        if (loggedInUser == null) {
            System.out.println("No user is currently logged in.");
        } else {
            System.out.println("Goodbye, " + loggedInUser.getName() + "! You have logged out.");
            loggedInUser = null;
        }
    }

    // Booking reservation
    public static void makeReservation(String date, String time) {
        if (loggedInUser == null) {
            System.out.println("You need to login first.");
        } else {
            bookings.add(new Booking(loggedInUser.getEmail(), date, time));
            System.out.println("Reservation made successfully!");
        }
    }

    // View bookings
    public static void viewReservations() {
        if (loggedInUser == null) {
            System.out.println("You need to login first.");
        } else {
            System.out.println("Reservations for " + loggedInUser.getEmail() + ":");
            for (Booking booking : bookings) {
                if (booking.getUserEmail().equals(loggedInUser.getEmail())) {
                    System.out.println(booking);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Online Reservation System ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Make a Reservation");
            System.out.println("5. View Reservations");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter your email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter your password: ");
                    String password = sc.nextLine();
                    registerUser(name, email, password);
                    break;

                case 2:
                    if (loggedInUser == null) {
                        System.out.print("Enter your email: ");
                        String loginEmail = sc.nextLine();
                        System.out.print("Enter your password: ");
                        String loginPassword = sc.nextLine();
                        loginUser(loginEmail, loginPassword);
                    } else {
                        System.out.println("You are already logged in.");
                    }
                    break;

                case 3:
                    logoutUser();
                    break;

                case 4:
                    System.out.print("Enter reservation date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    System.out.print("Enter reservation time (HH:MM): ");
                    String time = sc.nextLine();
                    makeReservation(date, time);
                    break;

                case 5:
                    viewReservations();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
