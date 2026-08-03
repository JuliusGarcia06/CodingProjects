import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reporting {
    
    public static void runReport(int choice, String destFile, PatientManager manager) {
        List<Patient> baseList = manager.getRawPatientDataList();
        List<Patient> reportList = new ArrayList<>(baseList); // Clone container references

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(destFile))) {
            switch (choice) {
                case 1: // Ascending Order via Patient Identifier String
                    sortPatientsById(reportList);
                    bw.write("=== PATIENT ID ASCENDING REPORT ==="); bw.newLine();
                    for (Patient p : reportList) {
                        bw.write("ID: " + p.getId() + " | Name: " + p.getName() + " | Email: " + p.getEmail());
                        bw.newLine();
                    }
                    break;

                case 2: // Ascending Order Alphabetically via Name Attributes
                    sortPatientsByName(reportList);
                    bw.write("=== PATIENT NAME ASCENDING REPORT ==="); bw.newLine();
                    for (Patient p : reportList) {
                        bw.write("ID: " + p.getId() + " | Name: " + p.getName() + " | Email: " + p.getEmail());
                        bw.newLine();
                    }
                    break;

                case 3: // Alphabetically Tracked Directory List of All Addresses
                    List<String> emails = extractUniqueEmails(reportList);
                    sortStringList(emails);
                    bw.write("=== GLOBAL EMAIL SYSTEM REPORT ==="); bw.newLine();
                    for (String em : emails) {
                        bw.write(em);
                        bw.newLine();
                    }
                    break;

                case 4: // Comprehensive Extraction Dump
                    bw.write("=== PERSONAL USER PROFILE TRANSACTION DUMP ==="); bw.newLine();
                    bw.write(manager.getLoggedInUser().toString());
                    bw.newLine();
                    break;

                default:
                    System.out.println("Processing Aborted: Choice index error.");
                    return;
            }
            System.out.println("Success: Compiled reporting data saved to target '" + destFile + "'.");
        } catch (IOException e) {
            System.out.println("Reporting Pipeline Failure: " + e.getMessage());
        }
    }

    private static void sortPatientsById(List<Patient> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (compareIds(list.get(j).getId(), list.get(min).getId()) < 0) min = j;
            }
            Patient t = list.get(min); list.set(min, list.get(i)); list.set(i, t);
        }
    }

    private static int compareIds(String s1, String s2) {
        try { return Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2)); }
        catch (NumberFormatException e) { return s1.compareTo(s2); }
    }

    private static void sortPatientsByName(List<Patient> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getName().compareToIgnoreCase(list.get(min).getName()) < 0) min = j;
            }
            Patient t = list.get(min); list.set(min, list.get(i)); list.set(i, t);
        }
    }

    private static List<String> extractUniqueEmails(List<Patient> list) {
        List<String> result = new ArrayList<>();
        for (Patient p : list) {
            String email = p.getEmail().trim();
            boolean exists = false;
            for (String s : result) {
                if (s.equalsIgnoreCase(email)) { exists = true; break; }
            }
            if (!exists && !email.isEmpty()) result.add(email);
        }
        return result;
    }

    private static void sortStringList(List<String> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).compareToIgnoreCase(list.get(min)) < 0) min = j;
            }
            String t = list.get(min); list.set(min, list.get(i)); list.set(i, t);
        }
    }
}
