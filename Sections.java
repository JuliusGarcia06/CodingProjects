import java.util.*;
public class Sections{
public static final int MAX_GRADE = 100;
    public static double [] gGrades = new double[MAX_GRADE];
    public static final Scanner gIN = new Scanner(System.in);

    public static void main(String[]args){
        System.out.println("UTSA - Spring 2026 - CS1083 - Section 003 - Project 2 - written by Julius Garcia");
        System.out.print("Please, enter the class size: ");
        int gClassSize = gIN.nextInt();


        int choice;
        while(true){
            System.out.println("\nMAIN MENU");
            System.out.println("0 - Exit, 1 - List, 2 - Report, 3 - Add/Modify Grade, 4 - Swap Grades");
            System.out.print("Select an option : ");
            choice = gIN.nextInt();
            if(choice == 0){
                System.out.println("Farewell!");
                break;
            }
            if(choice < 0 || choice > 4){
                System.out.println("Value out of range, please, try again");
            }else if(choice == 1){
                listGrades(gClassSize);
            }else if(choice == 2){
                report(gClassSize);
            }else if(choice == 3){
                addModifyGrade(gClassSize);
            }else if(choice == 4){
                swapGrades(gClassSize);
            }
            }
        }
        
        
    
    
    public static void listGrades(int gClassSize){
        System.out.println("LIST OF GRADES");
        for(int i = 0; i < gClassSize; i++){
            System.out.printf("Grade[%d] : %.2f%n", i, gGrades[i]);
        }
    }
    
    public static int getGradesLetter(char letter, int gClassSize){
        int count = 0;
        for(int i = 0; i < gClassSize; i ++){
            double grade = gGrades[i];

            if(letter == 'A' && grade >= 90 && grade <= 100) count++;
            else if(letter == 'B' && grade >= 80 && grade <90) count++;
            else if(letter == 'C' && grade >= 70 && grade < 80) count++;
            else if(letter == 'D' && grade >= 60 && grade < 70) count++;
            else if(letter == 'F' && grade < 60) count++;
        }
        return count;
    }

    public static void report(int gClassSize){
        System.out.println("GRADES REPORT");

        System.out.println("F : " + getGradesLetter('F', gClassSize));
        System.out.println("D : " + getGradesLetter('D', gClassSize));
        System.out.println("C : " + getGradesLetter('C', gClassSize));
        System.out.println("B : " + getGradesLetter('B', gClassSize));
        System.out.println("A : " + getGradesLetter('A', gClassSize));
    }

    public static void addModifyGrade(int gClassSize){
        int index;
        double grade;

        do{
            System.out.printf("Enter an index between 0 and %d: ", gClassSize - 1);
            index = gIN.nextInt();
            if(index < 0 || index >= gClassSize){
                System.out.println("Value out of range, please, try again");
            }
        }while(index < 0 || index >= gClassSize);
        
        System.out.printf("The current value of the grade in index %d is: %.1f%n", index, gGrades[index]);

        do{
            System.out.print("Enter the grade you want to assign (0.00 - 100.00) : ");
            grade = gIN.nextDouble();
            if(grade < 0.0 || grade > 100.0){
                System.out.println("Value out of range, please, try again");
            }System.out.printf("The current value of the grade in index %d is: %.1f%n", index, gGrades[index]);
        } while(grade < 0.0 || grade > 100.0);

        gGrades[index] = grade;
    }

    public static void swapGrades(int gClassSize) {
    int idxFrom, idxTo;

    // 1. Get and validate the first index
    do {
        System.out.printf("Enter the index from (0 to %d) : ", gClassSize - 1);
        idxFrom = gIN.nextInt();
        if (idxFrom < 0 || idxFrom >= gClassSize) {
            System.out.println("Value out of range, please, try again");
        }
    } while (idxFrom < 0 || idxFrom >= gClassSize);

    // 2. Get and validate the second index (must be different)
    do {
        System.out.printf("Enter the index to (0 to %d) that is not %d : ", gClassSize - 1, idxFrom);
        idxTo = gIN.nextInt();
        if (idxTo < 0 || idxTo >= gClassSize || idxTo == idxFrom) {
            System.out.println("Value out of range or same as first index, please, try again");
        }
    } while (idxTo < 0 || idxTo >= gClassSize || idxTo == idxFrom);

    // 3. Call the helper method to swap
    swapValues(idxFrom, idxTo);
    }

    public static void swapValues(int f, int t) {
    double temp = gGrades[f]; // Temporarily hold the first value
    gGrades[f] = gGrades[t];  // Move second value to first position
    gGrades[t] = temp;        // Put temp value into second position
    }
}