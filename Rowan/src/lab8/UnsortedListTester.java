package lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class UnsortedListTester {

	public static void main(String[] args) throws FileNotFoundException {
		ListArrayBasedPlus<String> list = new ListArrayBasedPlus<>();
		// if args is provided then use file provided in
		// arg0, otherwise user input is required
		// output is to go to file at arg2
		Scanner key;
		boolean written = false;
		PrintStream stdout = System.out;
		if (args.length == 0) {
			key = new Scanner(System.in);
		} else {
			written = true;
			key = new Scanner(new File(args[0]));
			System.setOut(new PrintStream(new File(args[1])));
		}
		int choice = 0;
		while (choice != 7) {
			System.out.println("1. Insert item to list. \n2. Remove item from list. \n3. Get item from list. "
					+ "\n4. Search for a specified item in the list. \n5. Clear list."
					+ "\n6. Print size and content of list. \n7. Exit program.");
			System.out.print("Make your menu selection now: ");
			choice = key.nextInt();
			menu(choice, key, list);
			System.out.println();
		}
		key.close();
		System.setOut(stdout);
		if (written)
			System.out.println("Output written to " + args[1]);
	}

	private static void menu(int choice, Scanner key, ListArrayBasedPlus<String> list) {
		int index;
		switch (choice) {
		case 1:
			System.out.println("You are now inserting an item into the list.");
			System.out.print("\t Enter item : ");
			String item = key.next();
			System.out.print("\t Enter position to insert item in : ");
			index = key.nextInt();
			try {
				list.add(index, item);
			} catch (ListIndexOutOfBoundsException a) {
				System.out.println("Error: Position specified is out of range");
				break;
			}
			System.out.println("Item " + item + " inserted in position " + index + " in the list.");
			break;
		case 2:
			if (list.isEmpty()) {
				System.out.println("Error: list is empty");
				break;
			}
			System.out.print("Enter position to remove item from : ");
			index = key.nextInt();
			try {
				String removed = list.get(index);
				list.remove(index);
				System.out.println("Item " + removed.toString() + " has been removed.");
			} catch (ListIndexOutOfBoundsException e) {
				System.out.println("Error: Position specified is out of range");
			}
			break;
		case 3:
			if (list.isEmpty()) {
				System.out.println("Error: list is empty");
				break;
			}
			System.out.print("Enter position to retrieve item from : ");
			index = key.nextInt();
			try {
				System.out.println("Item " + list.get(index) + " retrieved from position " + index + " in the list.");
			} catch (ListIndexOutOfBoundsException e) {
				System.out.println("Error: Position specified is out of range");
			}
			break;
		case 4:
			if (list.isEmpty())
				System.out.println("List is empty");
			else {
				System.out.print("Enter item to search for : ");
				item = key.next();
				index = sequentialSearch(list, item);
				if (index == -1)
					System.out.println("This item is not in the list.");
				else
					System.out.println("Item " + item + " is located at position " + index + ".");
			}
			break;
		case 5:
			if (list.isEmpty()) {
				System.out.println("List is already empty");
				break;
			}
			list.removeAll();
			System.out.println();
			break;
		case 6:
			if (list.isEmpty()) {
				System.out.println("List is empty");
				break;
			}
			System.out.println("List of size " + list.size() + " has the following items : " + list.toString());
			break;
		case 7:
			System.out.println("Exiting program...Good Bye");
			break;
		default:
			System.out.println("Please enter a valid option for the menu.");
			break;
		}
	}
	public static int sequentialSearch(ListInterface<String> a, String key) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).equals(key))
				return i;
		}
		return -1;
	}

}
