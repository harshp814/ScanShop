package client;

import java.util.Scanner;

import adts.Product;
import file_processing.Data;

public class Client {
	
	private final static String JSON_FILE_NAME = "amazon_rawdata.json.gz";
	
	private static Scanner input;
	
	public static void main(String[] args) {
		
		input = new Scanner(System.in);
		
		lineBreak();
		System.out.println("===\t\tWelcome to ScanShop!\t\t===");
		lineBreak();
		
		System.out.println("\nPress ENTER to begin setup");
		input.nextLine();
		
		System.out.print("Setting up data... ");
		
		double time = System.nanoTime();
		Data.init(JSON_FILE_NAME);
		System.out.println("Done.");
		System.out.println("Setup took " + (System.nanoTime() - time) / 1000000000 + " seconds.\n");
		
		while (menu() == true);
		
		System.out.println("\n===\tThank you for using ScanShop!\t===\n");
		/*
		
		
		
		Object prod = Data.searchByBarcode(Long.parseLong("978032"));
		if (prod != null) {
			System.out.println(prod.toString());
			
			System.out.println();
		}
		Product[] ps = Data.searchByTitle("algorithms (4th edition)", 10);
		for (Product p : ps) System.out.println(p);
		*/
	}
	
	private static boolean menu() {
		lineBreak();
		
		System.out.println("\n MAIN MENU: \n");
		System.out.println("\t (1) Search by barcode (UPC or EAN)");
		System.out.println("\t (2) Search by product name");
		System.out.println("\t (3) About ScanShop");
		System.out.println("\t (4) Quit\n");
		
		String in = "";
		
		while (true) {
			System.out.print("Select an option (enter a number): ");
			in = input.next();
		
			if (validMenuOption(in)) break;
			System.out.println("Invalid option entered. Please enter a number between 1 and 4 (inclusive).\n");
		}
		
		System.out.println();
		
		switch (Integer.parseInt(in)) {
		case 1 : searchByBarcode(); break;
		case 2 : searchByName(); break;
		case 3 : displayAbout(); break;
		case 4 : return false;
		}
		
		return true;
		
	}
	

	private static void searchByBarcode() {
		lineBreak();
		
		String in = "";
		
		while (true) {
			System.out.print("\nEnter your barcode (UPC or EAN): ");
			in = input.next();
			
			if (validBarcode(in)) break;
			System.out.println("Invalid barcode entered. Please enter a barcode consisting of only numbers.\n");
		}
		
		System.out.println();
		
		Object res = Data.searchByBarcode(Long.parseLong(in));
		
		if (res == null) System.out.println("A product with the barcode " + in + " could not be found.");
		else System.out.println("Product found! \n\t" + ((Product) res).readableString());
		
		//waitSeconds(1);
		
		System.out.println("Press ENTER to continue");
		input.nextLine();
		input.nextLine();
		
	}
	private static void searchByName() {
		lineBreak();
		
		System.out.print("\nEnter the name of a product: ");
		String in = input.next();
		
		System.out.println("\nRESULTS:");
		for (Product p : Data.searchByTitle(in, 10))
			System.out.println("\t" + p.readableString());
		
		System.out.println("\nPress ENTER to continue");
		input.nextLine();
		input.nextLine();
	}
	private static void displayAbout() {
		lineBreak();
		
		System.out.println();
		System.out.println(	"ScanShop is a product designed for the final project of McMaster's\n" +
							"\"2XB3 - Software Engineering Practice and Experience\" course. \n\n" +
							"Authors: Group 1:\n" +
							"\tHarsh Patel\n" +
							"\tRafay Leghari\n" +
							"\tSaad Khan\n" +
							"\tThomasDykstra\n" +
							"\tDaniel Wolff"	);
		System.out.println();
		System.out.println("Press ENTER to continue");
		input.nextLine();
		input.nextLine();
		
	}
	
	private static boolean validBarcode(String in) {
		
		try {
			long bar = Long.parseLong(in);
		}
		catch (Exception e) { return false;	}
		
		return true;
	}
	private static boolean validMenuOption(String in) {
		
		try {
			int opt = Integer.parseInt(in);
			if (opt < 1 || opt > 4) return false;	
		} 
		catch (Exception e) { return false; }
		
		return true;
	}	
	private static void lineBreak() { System.out.println("==================================================="); }
}