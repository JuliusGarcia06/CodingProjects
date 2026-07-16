import java.util.*;

public class Tariff {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Spring 2026 - CS1083 - Section 003 - Project 1 - Tariff - written by Julius Garcia\n");

        // Variables
        double basePriceItem, percTariff1, percTariff2, percTariff3;
        int firstDay, secondDay, thirdDay;
        double currentPrice = 0.0;
        double monthlyTotal = 0.0; // Track total for the month
        int day = 1;

        // User Input
        System.out.print("Input the base price of the imported item: ");
        basePriceItem = scnr.nextDouble();
        System.out.print("Input the first tariff day: ");
        firstDay = scnr.nextInt();
        System.out.print("Input the % starting first tariff day: ");
        percTariff1 = scnr.nextDouble();
        System.out.print("Input the second tariff day: ");
        secondDay = scnr.nextInt();
        System.out.print("Input the % starting second tariff day: ");
        percTariff2 = scnr.nextDouble();
        System.out.print("Input the third tariff day: ");
        thirdDay = scnr.nextInt();
        System.out.print("Input the % starting third tariff day: ");
        percTariff3 = scnr.nextDouble();
        
        //Table header
        System.out.println("\nPrice Change Based on Tariffs\n");
        System.out.printf("%-11s %-11s %-11s %-11s %-11s %-11s %-11s %-11s%n", 
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "AVG/Week");
        //Create table
        for (int i = 1; i <= 5; i++) {
            double weeklySum = 0;
            int validDaysInWeek = 0;

            for (int j = 1; j <= 7; j++) {
                if (day <= 30) {
                    // Logic to determine price based on the current day
                    if (day >= thirdDay) {
                        currentPrice = basePriceItem + (basePriceItem * (percTariff3 / 100));
                    } else if (day >= secondDay) {
                        currentPrice = basePriceItem + (basePriceItem * (percTariff2 / 100));
                    } else if (day >= firstDay) {
                        currentPrice = basePriceItem + (basePriceItem * (percTariff1 / 100));
                    } else {
                        currentPrice = basePriceItem;
                    }

                    System.out.printf("%02d-%8.2f ", day, currentPrice);
                    weeklySum += currentPrice;
                    monthlyTotal += currentPrice; // Accumulate for the month
                    validDaysInWeek++;
                    day++;
                } else {
                    // Padding for days past 30
                    System.out.printf("00-%8.2f ", 0.00);
                }
            }
            
            // Calculate and print the weekly average
            double weekAvg = (validDaysInWeek > 0) ? (weeklySum / validDaysInWeek) : 0;
            System.out.printf("W%02d-%8.2f%n", i, weekAvg);
        }

        // Print final Monthly Average
        double monthlyAvg = monthlyTotal / 30.0;
        System.out.printf("\nAverage per month: %.2f%n", monthlyAvg);
    }
}
