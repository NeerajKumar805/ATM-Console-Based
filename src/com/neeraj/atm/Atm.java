package com.neeraj.atm;
import java.util.Scanner;

/*CREATED BY NEERAJ KUMAR*/
public class Atm {
	public static int amt = 10000, choice = 0, withdraw_amt = 0, deposit_amt = 0, choiceValue = 0, newPin = 0,
			createdPin = 0, attempts = 0;
	public static String userName = null;
	static Scanner sc = new Scanner(System.in);

	public static void createPin() {
		System.out.println("Please enter your name");
		userName = sc.nextLine();
		System.out.print("Please create your PIN (Only Integers): ");
		newPin = InputAndValidation();
		attempts = 3;
		authentication();
	}

	public static void authentication() {
		System.out.print("Kindly enter your secret PIN : ");
		createdPin = InputAndValidation();
		if (attempts > 0) {
			if (createdPin == newPin)
				menu();
			else {
				System.out.println("\nInvalid PIN!!!, " + attempts-- + " Attempts remaining...");
				authentication();
			}
		} else {
			System.out.println("\nYou have reached the Exeeded attempts!!!");
			System.err.println("Now you have to create a new PIN...");
			createPin();
		}
	}

	public static void menu() {
		do {
			System.out.println("\t\t\t\t\nWelcome back, "+userName);
			System.out.println("\n1. View Balance");
			System.out.println("2. Withdraw");
			System.out.println("3. Deposit");
			System.out.println("4. Logout");
			System.out.println("5. Exit");
			System.out.print("\nPlease choose an operation : ");
			choice = InputAndValidation();
			switch (choice) {
			case 1:
				view();
				break;
			case 2:
				withdraw();
				break;
			case 3:
				deposit();
				break;
			case 4:
				System.out.println("Logout successfully\n");
				attempts = 3;
				authentication();
				break;
			case 5:
				exit();
			default:
				System.out.println("Invalid option, kindly choose the valid option (1-4)");
			}
		} while (choice != 5);
	}

	public static int InputAndValidation() {
		try {
			choiceValue = sc.nextInt();
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("\nYou have entered character value, only Integers are allowed");
			System.out.println("Kindly restart the console, then try again...");
			exit();
		}
		return choiceValue;
	}

	public static void view() {
		System.out.println("Your Balance is : " + amt);
	}

	public static void withdraw() {
		System.out.print("Please enter the ammount to be withdrawn : ");
		withdraw_amt = InputAndValidation();
		if (withdraw_amt > amt) {
			System.out.println("Insufficient Balance!!!");
			System.out.println("Please deposit the amount first");
		} else {
			amt -= withdraw_amt;
			System.out.println("Withdrawn successfully");
		}
		view();
	}

	public static void deposit() {
		System.out.print("Please enter the ammount to be deposited : ");
		deposit_amt = InputAndValidation();
		amt += deposit_amt;
		System.out.println("Desposited successfully");
		view();
	}

	public static void exit() {
		System.out.println("\nThank you for choosing Apna Bank...");
		System.exit(0);
	}

	public static void main(String[] args) {
		System.out.println("************************************************************");
		System.out.println("\t\t\tWelcome to Apna Bank");
		System.out.println("************************************************************\n");
		createPin();
	}
}