// User.java
public abstract class Users {
    private final String id;
    private final String username;
    private String password;
    private String name;
    private String email;

    public Users(String id, String username, String password, String name, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setPassword(String password) { this.password = password; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public abstract String toString();
    
    // Helper method to write clean data records back to files
    public abstract String toCSVString();
}

// Patient.java
class Patient extends Users {
    private String treatmentNotes;

    public Patient(String id, String username, String password, String name, String email, String treatmentNotes) {
        super(id, username, password, name, email);
        this.treatmentNotes = treatmentNotes;
    }

    public String getTreatmentNotes() { return treatmentNotes; }
    public void setTreatmentNotes(String treatmentNotes) { this.treatmentNotes = treatmentNotes; }

    @Override
    public String toString() {
        return "Patient [ID: " + getId() + ", Username: " + getUsername() + ", Name: " + getName() + 
               ", Email: " + getEmail() + ", Treatment Notes: " + treatmentNotes + "]";
    }

    @Override
    public String toCSVString() {
        return getId() + "," + getUsername() + "," + getPassword() + "," + getName() + "," + getEmail() + "," + treatmentNotes;
    }
}

// MedicalStaff.java
class MedicalStaff extends Users {
    private String department;

    public MedicalStaff(String id, String username, String password, String name, String email, String department) {
        super(id, username, password, name, email);
        this.department = department;
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "Medical Staff [ID: " + getId() + ", Username: " + getUsername() + ", Name: " + getName() + 
               ", Email: " + getEmail() + ", Department: " + department + "]";
    }

    @Override
    public String toCSVString() {
        return getId() + "," + getUsername() + "," + getPassword() + "," + getName() + "," + getEmail() + "," + department;
    }
}
