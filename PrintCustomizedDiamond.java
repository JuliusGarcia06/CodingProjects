import java.util.*;
public class PrintCustomizedDiamond {
    public static void main(String[]args){
        Scanner scnr = new Scanner(System.in);

        char c;
        int size;
        int i;
        int j;

        //Get Letter
        System.out.print("Enter a letter:");
        c = scnr.next().charAt(0);

        //Input Validation Loop
        size = 0;
        while(true){
            System.out.print(" Enter a size (even number no less than 6):");
            size = scnr.nextInt();
            if(size >=6 && size % 2 == 0){
                System.out.println(" ");
                break; //Valid Input, Exit Loop
            }
        }

        //Print Top Half
        for(i = 1; i <= size / 2; i++){
            //Print Leading Spaces
            for(j = 1; j <= (size/2-i); j++){
            System.out.print(" ");
        }
        //Print Characters
        for(j = 1; j <= (i*2); j++){
            System.out.print(c);
        }
        System.out.println(""); //Prints New Line
    }
        //Print Bottom Half Inverted
        for(i = size/2; i >= 1; i--){
            //Print Leading Spaces
            for(j = 1; j <= size/2-i; j++){
                System.out.print(" ");
            }
            //Print Characters
            for(j = 1; j<= (i*2); j++){
                System.out.print(c);
            }
            System.out.println("");
        }
        scnr.close();
    }
}
    

