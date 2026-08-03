import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Login {
    private final String patientFile;
    private final String staffFile;

    public Login(String patientFile, String staffFile){
        this.patientFile = patientFile;
        this.staffFile = staffFile;
    }

    public PatientManager execute(String userName, String password) throws Exception{
        List<Patient> patients = loadPatients();
        Users matchedUser = null;

        //1. Search Patient File for credentials
        for (Patient p: patients){
            if(p.getUsername().equals(userName) && p.getPassword().equals(password)){
                matchedUser = p;
                break;
            }
        }

        //2. Search Medical Staff File if not found in patients
        if(matchedUser == null){
            matchedUser = checkStaffCredentials(userName, password);
        }

        //Handle failed authentication
        if(matchedUser == null){
            throw new Exception("Invalid username or password.");
        }
        return new PatientManager(matchedUser, patients, patientFile);
    }

        private List<Patient> loadPatients() {
        List<Patient> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(patientFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] tokens = line.split(",");
                if (tokens.length >= 6) {
                    // FIX: Extract indices correctly from the parsed tokens array
                    list.add(new Patient(
                        tokens[0].trim(), 
                        tokens[1].trim(), 
                        tokens[2].trim(), 
                        tokens[3].trim(), 
                        tokens[4].trim(), 
                        tokens[5].trim()
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not extract patient records: " + e.getMessage());
        }
        return list;
    }

    private MedicalStaff checkStaffCredentials(String user, String pass) {
        try (BufferedReader br = new BufferedReader(new FileReader(staffFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] tokens = line.split(",");
                if (tokens.length >= 6) {
                    // FIX: Check credentials using array index references
                    if (tokens[1].trim().equals(user) && tokens[2].trim().equals(pass)) {
                        return new MedicalStaff(
                            tokens[0].trim(), 
                            tokens[1].trim(), 
                            tokens[2].trim(), 
                            tokens[3].trim(), 
                            tokens[4].trim(), 
                            tokens[5].trim()
                        );
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: Could not read operational staff indexes: " + e.getMessage());
        }
        return null;
    }

    
}
