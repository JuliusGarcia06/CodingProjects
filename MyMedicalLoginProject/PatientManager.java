import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientManager {
    private final Users loggedInUser;
    private final List<Patient> patients;
    private final String patientFilePath;
    private Patient currentlyViewedPatient;

    public PatientManager(Users loggedInUser, List<Patient> patients, String patientFilePath) {
        this.loggedInUser = loggedInUser;
        this.patients = patients;
        this.patientFilePath = patientFilePath;
        
        // Custom selection sort to order files by ascending ID string values
        sortPatientsById();

        // Patients can only view and update their own accounts
        if (loggedInUser instanceof Patient) {
            this.currentlyViewedPatient = (Patient) loggedInUser;
        }
    }

    public Users getLoggedInUser() { return loggedInUser; }
    public Patient getCurrentlyViewedPatient() { return currentlyViewedPatient; }

    public void viewProfile() {
        System.out.println("\n--- Currently Logged-In User Profile ---");
        System.out.println(loggedInUser.toString());
    }

    public void lookupPatient(String targetId) throws Exception {
        if (loggedInUser instanceof Patient) {
            throw new Exception("Authorization Error: Patients cannot search the global medical records database.");
        }

        int index = binarySearchById(targetId);
        if (index == -1) {
            throw new Exception("Search Error: Patient profile with ID '" + targetId + "' not found.");
        }

        currentlyViewedPatient = patients.get(index);
        System.out.println("\n--- Patient Profile Found ---");
        System.out.println(currentlyViewedPatient.toString());
    }

    public void editCurrentlyViewedPatient(int selection, String newValue) throws Exception {
        if (currentlyViewedPatient == null) {
            throw new Exception("Process Fault: No operational target has been selected for modification yet.");
        }
        
        // Block Patients from tampering with metadata across profiles
        if (loggedInUser instanceof Patient && !currentlyViewedPatient.getId().equals(loggedInUser.getId())) {
            throw new Exception("Security Violation: Access denied.");
        }

        switch (selection) {
            case 1: currentlyViewedPatient.setPassword(newValue); break;
            case 2: currentlyViewedPatient.setName(newValue); break;
            case 3: currentlyViewedPatient.setEmail(newValue); break;
            case 4: currentlyViewedPatient.setTreatmentNotes(newValue); break;
            default: throw new Exception("Invalid modification parameters.");
        }

        saveAllRecordsToFile();
    }

    public void saveAllRecordsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(patientFilePath))) {
            for (Patient p : patients) {
                bw.write(p.toCSVString());
                bw.newLine();
            }
            System.out.println("System Notification: Local physical file registers have been successfully synchronized.");
        } catch (IOException e) {
            System.out.println("Critical Error: Core update could not complete properly: " + e.getMessage());
        }
    }

    // Manual Sort Implementation: Selection Sort by Patient ID
    private void sortPatientsById() {
        int n = patients.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (compareIdStrings(patients.get(j).getId(), patients.get(minIdx).getId()) < 0) {
                    minIdx = j;
                }
            }
            Patient temp = patients.get(minIdx);
            patients.set(minIdx, patients.get(i));
            patients.set(i, temp);
        }
    }

    // Manual Binary Search Implementation
    private int binarySearchById(String targetId) {
        int low = 0;
        int high = patients.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String midId = patients.get(mid).getId();
            int cmp = compareIdStrings(midId, targetId);
            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // String digit helper routine for sorting IDs numerical value
    private int compareIdStrings(String s1, String s2) {
        try {
            int i1 = Integer.parseInt(s1);
            int i2 = Integer.parseInt(s2);
            return Integer.compare(i1, i2);
        } catch (NumberFormatException e) {
            return s1.compareTo(s2);
        }
    }

    // Safe accessors exposed for reporting purposes
    public List<Patient> getRawPatientDataList() { return patients; }
}
