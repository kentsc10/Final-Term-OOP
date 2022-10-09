package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.freshWater;
import model.saltWater;

public class Main {
	
	Scanner scan = new Scanner(System.in);
	ArrayList<transaction> transactions = new ArrayList<>();
	ArrayList<saltWater> saltwater = new ArrayList<>();
	ArrayList<freshWater> freshwater = new ArrayList<>();
	Connect connect = Connect.getConnection();
	
	public void init() {
		String query = "Select * FROM saltwaterfish";
		ResultSet rs = connect.executeQuery(query);
		try {
			while(rs.next()==true) {
				saltWater salty = new saltWater(rs.getString("FishID"),rs.getString("FishName"),rs.getFloat("FishSize"),rs.getInt("FishSpeed"),rs.getString("FishDepthTolerance"));
				saltwater.add(salty);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init2() {
		String query1 = "Select * FROM freshwaterfish";
		ResultSet rs = connect.executeQuery(query1);
		try {
			while(rs.next()==true) {
				freshWater freshy = new freshWater(rs.getString("FishID"),rs.getString("FishName"),rs.getFloat("FishSize"),rs.getInt("FishSpeed"),rs.getInt("FishAlgaeTolerance"));
				freshwater.add(freshy);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void purchase() {
		String email;
		int qty = 0;
		String fishType;
		int index = 0;
		do {
			System.out.print("Input User Email [Ends with '.com' | contains '@']: ");
			email = scan.nextLine();
		}while(!email.endsWith(".com") && email.contains("@"));
		do {
			System.out.print("Input Quantity [Must be Greater than 0]: ");
			qty = scan.nextInt();scan.nextLine();
		}while(qty<=0);
		do {
			System.out.print("Input Fish Type [Freshwater|Saltwater] (Case Sensitive): ");
			fishType = scan.nextLine();
		}while(!fishType.equals("Freshwater") && !fishType.equals("Saltwater"));
		if(fishType.equals("Freshwater")) {
			System.out.println("==========================================");
			System.out.println("Saltwater Fish");
			System.out.println("==========================================");
			System.out.println("No.| FishID | Fish Name | Fish Size | Fish Speed | Algae Tolerance |");
			System.out.println("==========================================");
			for(int i=0;i<saltwater.size();i++) {
				System.out.println((i+1) + " " + freshwater.get(i).getFishID() + " " + freshwater.get(i).getFishName() + " " + freshwater.get(i).getFishSize() + " " +freshwater.get(i).getFishSpeed() + " " + freshwater.get(i).getFishAlgae());
			}
			System.out.print("Input Fish Index to Purchase[1-6]: ");
			index = scan.nextInt();scan.nextLine();
			double price = (double) freshwater.get((index-1)).getFishSize() * (double) freshwater.get((index-1)).getFishSpeed() * qty * (int) freshwater.get((index-1)).getFishAlgae();
			System.out.println("Fish ID: " + freshwater.get((index-1)).getFishID());
			System.out.println("Fish Name: " + freshwater.get((index-1)).getFishName());
			System.out.println("Fish Size: " + freshwater.get((index-1)).getFishSize());
			System.out.println("Fish Speed: " + freshwater.get((index-1)).getFishSpeed());
			System.out.println("Fish price: " + price);
			System.out.println("Press Enter to continue...");
			int id=0;
			if(transactions.isEmpty()) {
				id = 1;
			}else {
				id = (transactions.size() + 1);
				
			}
			String query = String.format("INSERT INTO transaction VALUES('%d','%s','%d','%s')",id,email,qty,freshwater.get((index-1)).getFishID());
			connect.executeUpdate(query);
			transaction trans = new transaction(id,email,qty,freshwater.get((index-1)).getFishID());
			transactions.add(trans);
		}else if(fishType.equals("Saltwater")){
			System.out.println("==========================================");
			System.out.println("Saltwater Fish");
			System.out.println("==========================================");
			System.out.println("No.| FishID | Fish Name | Fish Size | Fish Speed | Depth Tolerance |");
			System.out.println("==========================================");
			for(int i=0;i<saltwater.size();i++) {
				System.out.println((i+1) + " " + saltwater.get(i).getFishID() + " " + saltwater.get(i).getFishName() + " " + saltwater.get(i).getFishSize() + " " + saltwater.get(i).getFishSpeed() + " " + saltwater.get(i).getFishDepth());
			}
			System.out.print("Input Fish Index to Purchase[1-6]: ");
			index = scan.nextInt();scan.nextLine();
			double price = (double) saltwater.get((index-1)).getFishSize() * (double) saltwater.get((index-1)).getFishSpeed() * qty * 2.5;
			System.out.println("Fish ID: " + saltwater.get((index-1)).getFishID());
			System.out.println("Fish Name: " + saltwater.get((index-1)).getFishName());
			System.out.println("Fish Size: " + saltwater.get((index-1)).getFishSize());
			System.out.println("Fish Speed: " + saltwater.get((index-1)).getFishSpeed());
			System.out.println("Fish price: " + price);
			System.out.println("Press Enter to continue...");
			int id=0;
			if(transactions.isEmpty()) {
				id = 1;
			}else {
				id = (transactions.size() + 1);
			}
			
			String query = String.format("INSERT INTO transaction VALUES('%d','%s','%d','%s')",id,email,qty,saltwater.get((index-1)).getFishID());
			connect.executeUpdate(query);
			transaction trans = new transaction(id,email,qty,saltwater.get((index-1)).getFishID());
			transactions.add(trans);
			
		}
		
	}
	
	public void viewTrans(ArrayList<transaction> transactions) {
		if(transactions.isEmpty()) {
			System.out.println("===================");
			System.out.println("Transaction ID | User Email | Quantity | FishID | Fish Name");
			System.out.println("===================");
			System.out.println("No Transaction Data Available");
			System.out.println("===================");
		}else {
			System.out.println("===================");
			System.out.println("Transaction ID | User Email | Quantity | FishID | Fish Name");
			System.out.println("===================");
			for(int i=0;i<transactions.size();i++) {
				System.out.println(transactions.get(i).getTransactionID() + " | " + transactions.get(i).getUserEmail() + " | " + transactions.get(i).getQty() + " | " + transactions.get(i).getFishID());
			}
		}
	}
	
	public void deleteTrans(ArrayList<transaction> transactions) {
		if(transactions.isEmpty()) {
			System.out.println("===================");
			System.out.println("Transaction ID | User Email | Quantity | FishID | Fish Name");
			System.out.println("===================");
			System.out.println("No Transaction Data Available");
			System.out.println("===================");
		}else {
			int del=0;
			System.out.println("===================");
			System.out.println("Transaction ID | User Email | Quantity | FishID | Fish Name");
			System.out.println("===================");
			for(int i=0;i<transactions.size();i++) {
				System.out.println(transactions.get(i).getTransactionID() + " | " + transactions.get(i).getUserEmail() + " | " + transactions.get(i).getQty() + " | " + transactions.get(i).getFishID() + " | ");
			}
			System.out.print("Input Transaction ID to be Cancelled: ");
			del=scan.nextInt();scan.nextLine();
			String query = String.format("DELETE FROM transaction WHERE TransactionID = %d", del);
			connect.executeUpdate(query);
			transactions.remove((del-1));
			System.out.println("Transaction Cancelled");
		}
	}
	
	public Main() {
		// TODO Auto-generated constructor stub
		init();
		init2();
		int menu=0;
		do {
			System.out.println("Exotic Fish Store");
			System.out.println("===========================");
			System.out.println("1. Purchase Exotic Fish");
			System.out.println("2. View Transaction");
			System.out.println("3. Cancel Transaction");
			System.out.println("4. Exit Menu");
			System.out.print("Input [1-4]: ");
			menu = scan.nextInt();scan.nextLine();
			switch (menu) {
			case 1:
				purchase();
				break;

			case 2:
				viewTrans(transactions);
				break;
			case 3:
				deleteTrans(transactions);
				break;
			}
		}while(menu!=4);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

}
