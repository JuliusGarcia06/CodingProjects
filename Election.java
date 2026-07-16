/**
 * Auto Generated Java Class.
 */
import java.util.*;

public class Election {
  public static final int DAYS_IN_WEEK = 7;
  public static final int TOTAL_WEEKS = 5;
   public static String getProjectInfo() {
       
        return "Spring 2025 - CS1083 - Section 004 - Project 1 - Election - written by Julius Garcia";
   }

    public static double initPercent(Scanner scnr) {
        System.out.print("Please, input the initial percent: ");
        return scnr.nextDouble();
    }

    public static double dayPerInc(Scanner scnr) {
        System.out.print("Please, input the daily percent increase: ");
        return scnr.nextDouble();
    }

    public static double weekendPerInc(Scanner scnr) {
        System.out.print("Please, input the weekend percent increase: ");
        return scnr.nextDouble();
    }

    public static char chooseLastDay(Scanner scnr) {
        char lastDayOption;
        int lastDay = 0;

        System.out.print("Last day of the month (A-28, B-30, C-31): ");
        lastDayOption = scnr.next().charAt(0);
       
        switch (lastDayOption) {
            case 'A':
                lastDay = 28;
                break;
            case 'B':
                lastDay = 30;
                break;
            case 'C':
                lastDay = 31;
                break;
            default:
                System.out.println("Invalid option. Please enter A, B, or C.");
                return chooseLastDay(scnr);
        }
       
        return lastDayOption;
    }
    public static void displayWeeklyReport(double initialPercent, double dailyPercentInc, double weekendPercentInc, int lastDay) {
     
      System.out.println("Week\tMonday\tTuesday\tWednesday\tThursday\tFriday\tSaturday\tSunday\tTotal/Week");
      double totalMonthPercent = 0.0;

     
       for (int week = 1; week <= TOTAL_WEEKS; week++) {
            double weeklyTotal = 0.0;
            System.out.print(week + "\t");

            for (int day = 1; day <= DAYS_IN_WEEK; day++) {
                double dailyPercent = initialPercent;
               
             
               
                if (day ==1 && week == 1){
                  dailyPercent = dailyPercent;
                }
              else if(day >=1 && day < 6){
                  dailyPercent = (dailyPercent + dailyPercentInc)- dailyPercent;
                }
                else{
                  dailyPercent = (dailyPercent + weekendPercentInc)- dailyPercent;
                }


               
                if (week == TOTAL_WEEKS && day == 4) {
                    System.out.print("0- 0.000\t");
                } else if (week == TOTAL_WEEKS && day > 4) {
                    System.out.print("0- 0.000\t");
                } else {
                    System.out.printf("%d-%.3f\t", (week - 1) * DAYS_IN_WEEK + day, dailyPercent);
                    weeklyTotal += dailyPercent;
                    totalMonthPercent += dailyPercent;
                }
            }

           
            System.out.printf("W%d-%.3f", week, weeklyTotal);
            System.out.println();
        }

       
        System.out.printf("End of Month Percent: %.3f", totalMonthPercent);
    }


     
   


    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
       System.out.println("Spring 2025 - CS1083 - Section 004 - Project 1 - Election - written by Julius Garcia");
       
       
        double initialPercent = initPercent(scnr);
        double dailyPercentInc = dayPerInc(scnr);
        double weekendPercentInc = weekendPerInc(scnr);
        char lastDayOption = chooseLastDay(scnr);
       
       
        
        
       
        displayWeeklyReport(initialPercent, dailyPercentInc, weekendPercentInc, lastDayOption);
        scnr.close();
    }
}
