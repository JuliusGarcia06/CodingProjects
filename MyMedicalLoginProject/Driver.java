import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Path configurations corresponding exactly to input files
        String patientFile = "patient.csv"; 
        String staffFile = "medicalstaff.csv";

        System.out.println("=================================================");
        System.out.println("   WELCOME TO HOSPITAL CLINIC PORTAL SYSTEM       ");
        System.out.println("=================================================");

        Login loginEngine = new Login(patientFile, staffFile);
        PatientManager manager = null;

        // Prompt loop runs until a valid login is completed successfully
        while (manager == null) {
            System.out.print("\nPlease supply System Account Username: ");
            String user = scanner.nextLine().trim();
            System.out.print("Please supply System Account Password: ");
            String pass = scanner.nextLine().trim();

            try {
                manager = loginEngine.execute(user, pass);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.print("Would you like to try again? (y/n): ");
                String retry = scanner.nextLine().trim();
                if (!retry.equalsIgnoreCase("y")) {
                    System.out.println("System operational run terminated.");
                    scanner.close();
                    return;
                }
            }
        }

        // Active authenticated interactive loop
        boolean sessionActive = true;
        while (sessionActive) {
            System.out.println("\n-----------------------------------------");
            System.out.println("              MAIN MENU SELECTION        ");
            System.out.println("-----------------------------------------");
            System.out.println("1. View Current Account Profile Details");
            System.out.println("2. Lookup Patient Account File (Staff Only)");
            System.out.println("3. Modify Selected Patient Profile Data");
            System.out.println("4. Execute Analytical Compliance Reports");
            System.out.println("5. Securely Log Out of Portal System");
            System.out.print("Enter Choice (1-5): ");

            String mainChoice = scanner.nextLine().trim();
            switch (mainChoice) {
                case "1":
                    manager.viewProfile();
                    break;

                case "2":
                    System.out.print("Enter target Patient ID to locate: ");
                    String searchId = scanner.nextLine().trim();
                    try {
                        manager.lookupPatient(searchId);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3":
                    Patient currentTarget = manager.getCurrentlyViewedPatient();
                    if (currentTarget == null) {
                        System.out.println("Execution Error: You must locate a target patient profile via lookup (Option 2) first.");
                        break;
                    }
                    System.out.println("\nTargeting Selected User: " + currentTarget.getName());
                    System.out.println("1. Modify Account Access Password");
                    System.out.println("2. Modify Profile Formal Registered Name");
                    System.out.println("3. Modify Network Electronic Email Link");
                    System.out.println("4. Modify Medical Clinic Treatment Notes");
                    System.out.print("Select field item to modify (1-4): ");
                    
                    try {
                        int fieldChoice = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Provide verified new field data replacement entry string: ");
                        String newVal = scanner.nextLine().trim();
                        manager.editCurrentlyViewedPatient(fieldChoice, newVal);
                    } catch (Exception e) {
                        System.out.println("Transaction Processing Aborted: " + e.getMessage());
                    }
                    break;

                case "4":
                    System.out.println("\n--- REPORT CONFIGURATION ENGINE ---");
                    System.out.println("1. List of Patients, sorted ascending by id (id, name, email)");
                    System.out.println("2. List of Patients, sorted ascending by name (id, name, email)");
                    System.out.println("3. List of all emails, sorted ascending alphabetically");
                    System.out.println("4. Comprehensive self user attribute list export dump");
                    System.out.print("Select Report Option (1-4): ");
                    
                    try {
                        int reportSelection = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Specify target storage name location filename (e.g., report.txt): ");
                        String outFileName = scanner.nextLine().trim();
                        Reporting.runReport(reportSelection, outFileName, manager);
                    } catch (Exception e) {
                        System.out.println("Processing Aborted: Invalid parameters. " + e.getMessage());
                    }
                    break;

                case "5":
                    System.out.println("Session cleared. Safe system disconnect achieved.");
                    sessionActive = false;
                    break;

                default:
                    System.out.println("Invalid selection entry value.");
            }
        }
        scanner.close();
    }
}
