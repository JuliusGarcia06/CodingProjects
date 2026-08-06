public class Sale {
    private Vehicles vehicle;
    private Customer customer;
    private double salePrice;
    private String saleDate;

    public Sale(Vehicles vehicle, Customer customer, double salePrice, String saleDate) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.salePrice = salePrice;
        this.saleDate = saleDate;
    }

    public Vehicles getVehicle(){
        return vehicle;
    }

    public void setVehicle(Vehicles vehicle){
        this.vehicle = vehicle;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public double getSalePrice(){
        return salePrice;
    }

    public void setSalePrice(double salePrice){
        this.salePrice = salePrice;
    }

    public String getSaleDate(){
        return saleDate;
    }

    public void setSaleDate(String saleDate){
        this.saleDate = saleDate;
    }
}