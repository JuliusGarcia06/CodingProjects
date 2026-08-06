public class Vehicles {
    private String vin;
    private String make;
    private String model;
    private int year;
    private double price;
    private boolean isAvailable;
    private String color;
    private long mileage;

    public Vehicles(String vin, String make, String model, int year, double price, boolean isAvailable, String color, long mileage) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.isAvailable = isAvailable;
        this.color = color;
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    @Override
    public String toString() {
        return year + " " + make + " " + model + " $" + price;
    }
}