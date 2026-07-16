import java.util.Scanner;

public class GroceryListManager {

    // Global constants for array capacity
    private static final int MAX_ITEMS = 50;

    // Parallel arrays to maintain the list data
    private static String[] itemArray = new String[MAX_ITEMS];
    private static boolean[] checkOffArray = new boolean[MAX_ITEMS];
    private static int numberOfItemsInList = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        System.out.println("Welcome to Grocery List Management!");

        // Loop until the user chooses the Exit option (5)
        while (choice != 5) {
            displayMenu();
            System.out.print("Please enter the number of an option above: ");
            
            // Validate that the user entered an integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the remaining newline character
                System.out.println(); // Formatting space

                switch (choice) {
                    case 1:
                        addItem(scanner);
                        break;
                    case 2:
                        removeItem(scanner);
                        break;
                    case 3:
                        checkOffItem(scanner);
                        break;
                    case 4:
                        printList();
                        break;
                    case 5:
                        exitProgram();
                        break;
                    default:
                        System.out.println("Invalid selection. Please choose a number between 1 and 5.\n");
                }
            } else {
                System.out.println("\nInvalid input. Please enter a valid number.\n");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        scanner.close();
    }

    /**
     * Displays the 1-5 numbered menu options to the user.
     */
    private static void displayMenu() {
        System.out.println("1. Add Item to your Grocery List");
        System.out.println("2. Remove Item from your Grocery List");
        System.out.println("3. \"Check Off\" an Item from your Grocery List");
        System.out.println("4. Display your Grocery List");
        System.out.println("5. Exit");
    }

    /**
     * Finds the index of an item in the array using a case-insensitive match.
     * Returns the index if found, or -1 if the item does not exist.
     */
    private static int findItemIndex(String itemName) {
        for (int i = 0; i < numberOfItemsInList; i++) {
            if (itemArray[i].equalsIgnoreCase(itemName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Action 1: Adds a unique item to the end of the grocery list.
     */
    private static void addItem(Scanner scanner) {
        if (numberOfItemsInList >= MAX_ITEMS) {
            System.out.println("Your grocery list is full! Cannot add more items.\n");
            return;
        }

        System.out.print("Enter the name of the item you wish to add: ");
        String newItem = scanner.nextLine().trim();

        if (newItem.isEmpty()) {
            System.out.println("Item name cannot be empty.\n");
            return;
        }

        // Check if the item already exists (case-insensitive)
        if (findItemIndex(newItem) != -1) {
            System.out.println("The item \"" + newItem + "\" already exists on your list.\n");
        } else {
            // Add to the end of the current items list
            itemArray[numberOfItemsInList] = newItem;
            checkOffArray[numberOfItemsInList] = false; // Default to "not checked off"
            numberOfItemsInList++; // Increment list counter
            System.out.println("\"" + newItem + "\" has been added to the list.\n");
        }
    }

    /**
     * Action 2: Removes an item either by its exact text name or its display number.
     * Shifts remaining items left to prevent gaps.
     */
    private static void removeItem(Scanner scanner) {
        if (numberOfItemsInList == 0) {
            System.out.println("Your list is currently empty. Nothing to remove.\n");
            return;
        }

        System.out.print("Enter the name or the number of the item you wish to remove: ");
        String input = scanner.nextLine().trim();
        int targetIndex = -1;

        // Check if input is a valid list number
        if (input.matches("\\d+")) {
            int listNumber = Integer.parseInt(input);
            if (listNumber >= 1 && listNumber <= numberOfItemsInList) {
                targetIndex = listNumber - 1; // Convert 1-based display to 0-based index
            }
        } else {
            // Otherwise, treat input as an item name string
            targetIndex = findItemIndex(input);
        }

        // Process removal if target is found
        if (targetIndex != -1) {
            String removedItem = itemArray[targetIndex];
            
            // Shift elements to the left to close the gap
            for (int i = targetIndex; i < numberOfItemsInList - 1; i++) {
                itemArray[i] = itemArray[i + 1];
                checkOffArray[i] = checkOffArray[i + 1];
            }
            
            // Clean up the leftover last element reference and decrement count
            itemArray[numberOfItemsInList - 1] = null;
            checkOffArray[numberOfItemsInList - 1] = false;
            numberOfItemsInList--;

            System.out.println("\"" + removedItem + "\" has been removed from the list.\n");
        } else {
            System.out.println("Item or number does not exist on the list.\n");
        }
    }

    /**
     * Action 3: Marks a specific item as "checked off" (true) by name or number.
     */
    private static void checkOffItem(Scanner scanner) {
        if (numberOfItemsInList == 0) {
            System.out.println("Your list is currently empty.\n");
            return;
        }

        System.out.print("Enter the name or the number of the item you wish to check off: ");
        String input = scanner.nextLine().trim();
        int targetIndex = -1;

        // Check if input is a valid list number
        if (input.matches("\\d+")) {
            int listNumber = Integer.parseInt(input);
            if (listNumber >= 1 && listNumber <= numberOfItemsInList) {
                targetIndex = listNumber - 1;
            }
        } else {
            // Treat input as an item name string
            targetIndex = findItemIndex(input);
        }

        // Mark item if valid index found
        if (targetIndex != -1) {
            checkOffArray[targetIndex] = true;
            System.out.println("\"" + itemArray[targetIndex] + "\" is now checked off!\n");
        } else {
            System.out.println("Item or number does not exist on the list.\n");
        }
    }

    /**
     * Action 4: Formats and displays the items currently inside the list.
     */
    private static void printList() {
        System.out.println("--- Current Grocery List ---");
        if (numberOfItemsInList == 0) {
            System.out.println("(The list is empty)");
        } else {
            for (int i = 0; i < numberOfItemsInList; i++) {
                // Uses 'x' if true (checked off) and '-' if false (not checked off)
                String statusMarker = checkOffArray[i] ? "x" : "-";
                System.out.println((i + 1) + ". " + statusMarker + " " + itemArray[i]);
            }
        }
        System.out.println("----------------------------\n");
    }

    /**
     * Action 5: Prints final list status, shows goodbye message, and closes program.
     */
    private static void exitProgram() {
        System.out.println("Final Grocery List Status:");
        printList();
        System.out.println("Goodbye!");
    }
}