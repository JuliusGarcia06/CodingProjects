import java.util.*;
public class tempCheck {
   
    public static double calculateAverage(double[] values){
        double sum = 0;
        for(int i = 0; i < values.length; i++){
            sum+=values[i];
        }
        return sum / values.length;
    }
   
    public static void main(String[]args){
        Scanner input = new Scanner(System.in);

        double[] temps = new double[7];

        System.out.println("Enter the temperatures for the next 7 days");

        for(int i = 0; i < temps.length; i++){
            temps[i] = input.nextDouble();
        }

        double average = calculateAverage(temps);
        System.out.printf("The average temperature for the week is: %.2f°C%n", average);
    }
    
}
