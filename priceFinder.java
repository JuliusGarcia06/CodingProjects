import java.util.*;
public class priceFinder {
    public static void main(String[]args){
        Scanner scnr = new Scanner(System.in);
        boolean found = false;
        double[] prices = {19.99, 5.50, 42.00, 10.00, 15.75};

        System.out.println("Enter a price that you would like to search for");
        double search = scnr.nextDouble();

        for(int i = 0; i < prices.length; i++){

            if(prices[i] == search){
                found = true;
                break;
            }
        }
        if(found){
            System.out.println("Price Found!");
        }else{
            System.out.println("Uh Oh, Not Quite Right!");
        }
    }
    
}
