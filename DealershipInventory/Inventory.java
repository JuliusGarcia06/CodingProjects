import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Vehicles> vehicles;

    public Inventory() {
        vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicles vehicle) {
        vehicles.add(vehicle);
        System.out.println(vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel() + " has been added to the inventory.");
    }

    public boolean removeVehicle(String vin) {
        for (Vehicles vehicle : vehicles) {
            if (vehicle.getVin().equalsIgnoreCase(vin)) {
                vehicles.remove(vehicle);
                System.out.println(vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel() + " has been removed from the inventory.");
                return true;
            }
        }
        System.out.println("Vehicle with VIN " + vin + " not found in the inventory.");
        return false;
    }

    public List<Vehicles> getAvailableVehicles() {
        List<Vehicles> availableVehicles = new ArrayList<>();
        for (Vehicles vehicle : vehicles) {
            if (vehicle.isAvailable()) {
                availableVehicles.add(vehicle);
            }
        }
        return availableVehicles;
    }
}