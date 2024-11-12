import java.util.ArrayList;
import java.util.Scanner;

class Appointment {
    int year, month, date;
    String description;

    Appointment(int year, int month, int date, String description) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Date: " + date + "-" + month + "-" + year + ", Description: " + description;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Appointment> appointments = new ArrayList<>();
        ArrayList<Appointment> reminders = new ArrayList<>();

        System.out.println("This is server running....!");
        System.out.println("Hi! Welcome....this is Revanth\nHow can I help you?\n");

        while (true) {
            System.out.println("Enter: \n1. Schedule Appointment\n2. Schedule Reminder\n3. View Appointments\n4. View Reminders\n5. Exit");
            System.out.print("Enter your choice: ");
            int n = in.nextInt();

            switch (n) {
                case 1: // Schedule Appointment
                    System.out.println("Enter the year, followed by month and date:");
                    int year = in.nextInt();
                    int month = in.nextInt();
                    int date = in.nextInt();

                    if (isValidDate(year, month, date)) {
                        System.out.print("Enter the details to schedule appointment: ");
                        in.nextLine(); // Consume newline character
                        String desc = in.nextLine();
                        appointments.add(new Appointment(year, month, date, desc));
                        System.out.println("Appointment scheduled successfully.");
                    } else {
                        System.out.println("Invalid date entered.");
                    }
                    break;

                case 2: // Schedule Reminder
                    System.out.println("Enter the year, followed by month and date:");
                    year = in.nextInt();
                    month = in.nextInt();
                    date = in.nextInt();

                    if (isValidDate(year, month, date)) {
                        System.out.print("Enter the details to schedule reminder: ");
                        in.nextLine(); // Consume newline character
                        String desc = in.nextLine();
                        reminders.add(new Appointment(year, month, date, desc));
                        System.out.println("Reminder scheduled successfully.");
                    } else {
                        System.out.println("Invalid date entered.");
                    }
                    break;

                case 3: // View Appointments
                    System.out.println("Scheduled Appointments:");
                    if (appointments.isEmpty()) {
                        System.out.println("No appointments scheduled.");
                    } else {
                        for (Appointment appointment : appointments) {
                            System.out.println(appointment);
                        }
                    }
                    break;

                case 4: // View Reminders
                    System.out.println("Scheduled Reminders:");
                    if (reminders.isEmpty()) {
                        System.out.println("No reminders scheduled.");
                    } else {
                        for (Appointment reminder : reminders) {
                            System.out.println(reminder);
                        }
                    }
                    break;

                case 5: // Exit
                    System.out.println("Exiting... Goodbye!");
                    in.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Helper function to validate date
    public static boolean isValidDate(int year, int month, int date) {
        if (year < 2025) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        if (month == 2) {
            return date >= 1 && date <= 29; // No leap year check for simplicity
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return date >= 1 && date <= 30;
        } else {
            return date >= 1 && date <= 31;
        }
    }
}
