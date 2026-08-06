import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DealershipApp {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        List<Customer> customers = new ArrayList<>();
        Inventory inventory = new Inventory();
        List<Sale> sales = new ArrayList<>();
        boolean available;

        int choice = -1;

        while(choice != 0){
            System.out.println("------ Welcome to the Dealership Management System ------");
            System.out.println("1. Manage Customers (Add, Look Up, Remove)");
            System.out.println("2. Manage Inventory (Add, Look Up, Remove)");
            System.out.println("3. Process a Sale");
            System.out.println("4. View Sales History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if(input.hasNextInt()){
                choice = input.nextInt();
                input.nextLine();
            }
            else{
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                input.nextLine();
                continue;
            }

            switch(choice){
                // Customer Management
                case 1:
                    System.out.println("\n--- Customer Menu ---");
                    System.out.println("1. Add Customer");
                    System.out.println("2. Look Up Customer");
                    System.out.println("Choose an option: ");
                    int customerChoice = input.nextInt();

                    if(customerChoice == 1){
                        System.out.print("Enter Customer Name: ");
                        String name = input.next();
                        System.out.print("Enter Customer Email: ");
                        String email = input.next();
                        System.out.print("Enter Customer Phone Number: ");
                        String phoneNumber = input.next();

                        Customer customer = new Customer(name, email, phoneNumber, true);
                        customers.add(customer);
                        System.out.println("Customer added successfully.");
                    }else if(customerChoice ==2){
                        System.out.println("Enter email to search");
                        String email = input.next();
                        boolean found = false;
                        for(Customer customer : customers){
                            if(customer.getEmail().equalsIgnoreCase(email)){
                                System.out.println("Customer found: " + customer.getName() + ", " + customer.getEmail() + ", " + customer.getPhoneNumber());
                                found = true;
                                break;
                            }
                        }
                        if(!found){
                            System.out.println("Customer not found.");
                        }
                    }
                    break;

                // Inventory Management
                case 2:
                    System.out.println("\n--- Inventory Management Menu ---");
                    System.out.println("1. Add Vehicle");
                    System.out.println("2. Remove Vehicle by VIN");
                    System.out.println("3. View Available Vehicles");
                    System.out.print("Choose an option: ");
                    int invChoice = input.nextInt();
                    input.nextLine();

                    if(invChoice == 1){
                        System.out.print("Enter Vehicle VIN: ");
                        String vin = input.nextLine();
                        System.out.print("Enter Vehicle Make: ");
                        String make = input.nextLine();
                        System.out.print("Enter Vehicle Model: ");
                        String model = input.nextLine();
                        System.out.print("Enter Vehicle Year: ");
                        int year = input.nextInt();
                        input.nextLine();
                        System.out.print("Enter Vehicle Color: ");
                        String color = input.nextLine();
                        System.out.print("Enter Vehicle Mileage");
                        long mileage = input.nextLong();
                        System.out.print("Enter Vehicle Price: ");
                        double price = input.nextDouble();
                        input.nextLine();
                        System.out.print("Is the Vehicle Available?");
                        String isAvailable = input.nextLine();
                        if(isAvailable.equals("Yes")){
                            available = true;
                        }else{
                            available = false;
                        }
                        

                        Vehicles vehicle = new Vehicles(vin, make, model, year, price, available, color, mileage);
                        inventory.addVehicle(vehicle);
                    }
                    else if(invChoice == 2){
                        System.out.print("Enter VIN of Vehicle Being Removed");
                        String vin = input.nextLine();
                        inventory.removeVehicle(vin);
                    }
                    else if(invChoice == 3){
                        List<Vehicles> availableVehicles = inventory.getAvailableVehicles();
                        System.out.println("\nAvailable Vehicles");
                        for(Vehicles vehicles : availableVehicles){
                            System.out.println(" - VIN: " + vehicles.getVin() + " | " + vehicles);
                        }
                    }
                    break;

                    case 3:
                        System.out.print("Enter Customer's Email");
                        String custEmail = input.nextLine();
                        Customer buyer = null;
                        for(Customer c : customers){
                            if(c.getEmail().equalsIgnoreCase(custEmail)){
                                buyer = c;
                                break;
                            }
                        }
                        if(buyer == null){
                            System.out.println("Customer not found. Please create a new customer account.");
                            break;
                        }
                        
                        System.out.print("Enter the VIN of the vehicle being purchased: ");
                        String saleVin = input.nextLine();
                        Vehicles vehicleToBuy = null;

                        for(Vehicles v : inventory.getAvailableVehicles()){
                            if(v.getVin().equalsIgnoreCase(saleVin)){
                                vehicleToBuy = v;
                                break;
                            }
                        }

                        if(vehicleToBuy != null){
                            System.out.print("Enter the Sale Price: ");
                            double salePrice = input.nextDouble();
                            input.nextLine();
                            System.out.print("Enter the Sale Date (MM/DD/YYYY): ");
                            String saleDate = input.nextLine();

                            Sale newSale = new Sale(vehicleToBuy, buyer, salePrice, saleDate);
                                sales.add(newSale);
                                vehicleToBuy.setAvailable(false);
                                buyer.addToBuyerHistory(vehicleToBuy);

                                System.out.println("Sale processed successfully");
                            }else{
                                System.out.println("Vehicle not found or is not available.");
                            }
                        
                            break;
                        case 4:
                    // View Sales
                    System.out.println("\n--- Recorded Sales ---");
                    if (sales.isEmpty()) {
                        System.out.println("No sales recorded yet.");
                    } else {
                        for (Sale s : sales) {
                            System.out.println("Date: " + s.getSaleDate() + " | Vehicle: " + s.getVehicle() + " | Buyer: " + s.getCustomer().getName() + " | Price: $" + s.getSalePrice());
                        }
                    }
                    break;

                case 5:
    System.out.println("Exiting application. Goodbye!");
    input.close();
    choice = 0;  // lets the while condition exit naturally
    break;

                default:
                    System.out.println("Invalid option. Choose between 1 and 5.");
            }
        }
    }
}
