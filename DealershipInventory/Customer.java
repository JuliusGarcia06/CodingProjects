import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private boolean newCustomer;
    private List<Vehicles> buyerHistory;
    private List<Vehicles> sellerHistory;

    public Customer(String name, String email, String phoneNumber, boolean newCustomer) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.newCustomer = newCustomer;
        this.buyerHistory = new ArrayList<>();
        this.sellerHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(boolean newCustomer) {
        this.newCustomer = newCustomer;
    }

    public List<Vehicles> getBuyerHistory() {
        return buyerHistory;
    }

    public void addToBuyerHistory(Vehicles vehicle) {
        buyerHistory.add(vehicle);
    }

    public List<Vehicles> getSellerHistory() {
        return sellerHistory;
    }

    public void addToSellerHistory(Vehicles vehicle) {
        sellerHistory.add(vehicle);
    }

    public void displayCustomerInfo() {
        System.out.println("Customer Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("New Customer: " + (newCustomer ? "Yes" : "No"));
        System.out.println("Buyer History: ");
        for (Vehicles vehicle : buyerHistory) {
            System.out.println(" - " + vehicle);
        }
        System.out.println("Seller History: ");
        for (Vehicles vehicle : sellerHistory) {
            System.out.println(" - " + vehicle);
        }
    }
}