package view;

import java.util.List;
import java.util.Scanner;

import controller.ListItemHelper;
import model.ListItem;

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListItemHelper lih = new ListItemHelper();//construct the ListItemHelper class

		private static void addAnItem() {		//gets info from the user and passes it to the helper
			System.out.print("Enter a store: ");
			String store = in.nextLine();
			System.out.print("Enter an item: ");
			String item = in.nextLine();
			
			ListItem toAdd = new ListItem(store, item);	//constructs an entity with args, as a new entity
			lih.insertItem(toAdd);						//passes the new entity

		}

		private static void deleteAnItem() {	//gets info on an entry to delete, and passes it to the helper
			System.out.print("Enter the store to delete: ");
			String store = in.nextLine();
			System.out.print("Enter the item to delete: ");
			String item = in.nextLine();
			
			ListItem toDelete = new ListItem(store, item);
			lih.deleteItem(toDelete);
		}

		private static void editAnItem() {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Store");
			System.out.println("2 : Search by Item");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ListItem> foundItems;				//makes a list of ListItem Entities
			if (searchBy == 1) {					//depending on what the user puts in, the program creates different variables
				System.out.print("Enter the store name: ");		//then it reduces to a list of items or stores that are plausable
				String storeName = in.nextLine();
				foundItems = lih.searchForItemByStore(storeName);
			} else {
				System.out.print("Enter the item: ");
				String itemName = in.nextLine();
				foundItems = lih.searchForItemByItem(itemName);
			}

			if (!foundItems.isEmpty()) {		//continues if there is at least one editable entry
				System.out.println("Found Results.");
				for (ListItem l : foundItems) {		//prints each entry by retrieving each Entity's data
					System.out.println("ID: " + l.getId() + " - " + l.returnItemDetails());
				}
				System.out.print("Which ID to edit: ");	//the computer can't assume the same parameter will always be wrong
				int idToEdit = in.nextInt();			//if you put in a store, then you need to be able to change either the-
								//store or the item, the user should be able to put in either the correct or incorrect parameter
				ListItem toEdit = lih.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getItem() + " from " + toEdit.getStore());
				System.out.println("1 : Update Store");
				System.out.println("2 : Update Item");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {					//based on a numeric input from the user, the program starts to process
					System.out.print("New Store: ");	//the store or item data change
					String newStore = in.nextLine();
					toEdit.setStore(newStore);
				} else if (update == 2) {
					System.out.print("New Item: ");
					String newItem = in.nextLine();
					toEdit.setItem(newItem);
				}

				lih.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {	//on class execution, the runMenu function is ran
			runMenu();

		}

		public static void runMenu() {	//this is ran after every function, and stops the program if the user-
																	//does not input a number between 1 and 4
			boolean goAgain = true;
			System.out.println("--- Welcome to our awesome shopping list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an item");
				System.out.println("*  2 -- Edit an item");
				System.out.println("*  3 -- Delete an item");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {				//returns a table of entities
			List<ListItem> allItems = lih.showAllItems();	//make a list of entities
			for(ListItem l : allItems){					//returns each entity, one per loop
				System.out.println(l.returnItemDetails());	//print the entity that is being looped on
			}

		}

	}
