import java.util.*;
class stockManagementSystem{
	private static Scanner input = new Scanner(System.in);
	private static String[] cred = {"admin","1234"};
	private static String[][] suppliers = new String[0][0];
	private static String[] itemCat = new String[0];
	private static String[][] items = new String[0][6];
	private static double[] itemPrice = new double[0];
	private final static void clearConsole(){
		final String os = System.getProperty("os.name");
			try {
				if (os.equals("Linux")) {
					System.out.print("\033\143");
			} else if (os.equals("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
			} catch (final Exception e) {
				//handle the exception
				System.err.println(e.getMessage());
			}
	}
	private static int getSupIndex(String supId){
		for(int i = 0; i < suppliers.length; i++){
			if(suppliers[i][0].equals(supId)){
				return i;
			}
		}
		return -1;
	}
	public static void loginPage(){
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		LOGIN PAGE                     |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		do{
			System.out.print("\nUsername: ");
		    String userName = input.next();
		
	 	    boolean isMatched = checkUserNameValidity(cred, userName);
		       if(isMatched){
				   while(true){
					   System.out.print("\nPassword: ");
			           String passWord = input.next();
			
			          boolean isCorrected = checkPw(cred,passWord);
			          if(isCorrected){
				         int option = homePage(); 
				         break;
			          }else{
				        System.out.println("Password is incorrect.please try again!");
			          }
					}
			    }else{
			      System.out.println("Username is incorrect.please try again!");
			  }
		}while(true);
		
	}
	public static boolean checkUserNameValidity(String[] cred, String userName){
		return cred[0].equals(userName);
	}
	public static boolean checkPw(String[] cred, String passWord){
		return cred[1].equals(passWord);
	}
	public static int homePage(){
		clearConsole();
		
		System.out.printf("+----------------------------------------------------------------+\n");
		System.out.printf("|       WELCOME TO THE PIGEON SERVICE STOCK MANAGEMENT SYSTEM    |\n");
		System.out.printf("+----------------------------------------------------------------+\n");
		
		System.out.println();
		
		int op;
		do{
			System.out.print("[1] Change the credentials");
			System.out.println("\t[2] Supplier Manage");
			System.out.print("[3] Stock Manage");
			System.out.println("\t\t[4] Log Out");
			System.out.println("[5] Exit the System");
			System.out.println();
			
			System.out.print("Enter an option to continue > ");
			op = input.nextInt();
			
			switch(op){
				case 1:
					changeCredentials(cred);
					break;
				case 2:
					supplierManage();
					break;
				case 3:
					stockManage();
					break;
				case 4:
					logOut();
					break;
				case 5:
					System.out.println("Exiting the System");
					System.exit(0);
				default:
					System.out.println("Invalid Choice!!");
			}
		}while(op != 5);
		
		return op;
	}
	public static void changeCredentials(String[] cred){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            	    CREDENTIAL MANAGE                  |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		System.out.println();
		
		do{
			System.out.print("\nPlease enter the username to verify it's you: ");
		    String userName = input.next();
		
			boolean isMatched = checkUserNameValidity(cred, userName);
			if(isMatched){
				System.out.println("Hey admin");
				while(true){
					System.out.print("Enter your current password: ");
					String passWord = input.next();
			
					boolean isCorrected = checkPw(cred,passWord);
					if(isCorrected){
						System.out.print("\nEnter your current password: ");
						String pass = input.next();
						System.out.print("Enter your new password: ");
						String newPass = input.next();
				
						checkNewPassword(cred,newPass);
						System.out.print("\nPassword changed successfully!");
						backToHomePage();
			     
					}else{
						System.out.println("Invalid Password.please try again!");
					}
				}
				
			}else{
				System.out.println("incorrect UserName.please try again!");
			}
		}while(true);
		
	}
	public static void checkNewPassword(String[] cred,String newPass){
			for(int i = 0; i < cred.length; i++){
				cred[1] = newPass;
			}
	}
	public static void backToHomePage(){
		L1:
			do{
				System.out.print("Do you want to go home page(Y/N): ");
				char in = input.next().charAt(0);
		        
				if(in == 'Y' || in == 'y'){
					homePage();
				}else if(in == 'N' || in == 'n'){
					changeCredentials(cred);
				}else{
					System.out.println("Invalid Choice! Try again");
					continue L1;
				}
			}while(true);
		
	}
	public static void logOut(){
		clearConsole();
		
		loginPage();
	}
	public static void supplierManage(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		SUPPLIER MANAGE                |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		System.out.println();
		
		int s;
		
		do{
			System.out.print("[1] Add Supplier");
			System.out.println("\t[2] Update Supplier");
			System.out.print("[3] Delete Supplier");
			System.out.println("\t[4] View Supplier");
			System.out.print("[5] Search Supplier");
			System.out.println("\t[6] Home Page");
			
			System.out.println();
			
			System.out.print("Enter an option to continue > ");
			s = input.nextInt();
			
			switch(s){
				case 1:
					addNewSupplier();
					break;
				case 2:
					updateSupplier();
					break;
				case 3:
					deleteSupplier();
					break;
				case 4:
					viewSupplier();
					break;
				case 5:
					searchSupplier();
					break;
				case 6:
					homePage();
					break;
				default:
					System.out.println("Invalid choice!!");
			}
		}while(s != 6);
	}
	public static void addNewSupplier(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		ADD SUPPLIER                   |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		String supId;
	L1:
		do{
			System.out.print("\nEnter Supplier ID: ");
			supId = input.next();
		
			if(isSupIdExists(supId)){
				System.out.println("Already Exists.Try another Supplier ID.");
				continue L1;
			}else{
				System.out.print("Enter Supplier Name: ");
				String supName = input.next();
		
				add(supId,supName);
				System.out.print("Supplier added Successfully!");
				
				addAnotherSupplier();
				
				return;
			}
		
		}while(true);
		
	}
	public static boolean isSupIdExists(String supId){
		for(int i = 0; i < suppliers.length; i++){
			if(suppliers[i][0].equals(supId)){
				return true;
			}
		}
		return false;
	}
	public static void add(String supId,String supName){
		String[][] temp = new String[suppliers.length + 1][2];
		for(int i = 0; i < suppliers.length; i++){
			temp[i] = suppliers[i];
		}
		temp[temp.length - 1][0] = supId;
		temp[temp.length - 1][1] = supName;
		suppliers = temp;
	}
	public static void addAnotherSupplier(){
		L1:
			do{
				System.out.print("Do you want to add another supplier(Y/N): ");
				char in = input.next().charAt(0);
				
				if(in == 'Y' || in == 'y'){
					addNewSupplier();
				}else if(in == 'N' || in == 'n'){
					supplierManage();
				}else{
					System.out.println("Invalid Choice! Try again");
					continue L1;
				}
			}while(true);
		
	}
	public static void updateSupplier(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		UPDATE SUPPLIER                |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		String supId;
	L1:
		do{
			System.out.print("\nSupplier ID: ");
			supId = input.next();
		
			int supIndex = getSupIndex(supId);
			if(supIndex == -1){
				System.out.println("Can't find Supplier ID.Try Again!");
				continue L1;
			
			}else{
				System.out.println("Supplier Name: " + suppliers[supIndex][1]);
			
				System.out.print("\nEnter new Supplier Name: ");
				String supName = input.next();
			
				suppliers[supIndex][1] = supName;
			
				System.out.print("Updated Successfully!");
				updateAnotherSupplier();
				return;
			}
		}while(true);
		
	}
	public static void updateAnotherSupplier(){
		L1:
			do{
				System.out.print("Do you want to update another Supplier(Y/N): ");
				char in = input.next().charAt(0);
			
				if(in == 'Y' || in == 'y'){
					updateSupplier();
				}else if(in == 'N' || in == 'n'){
					supplierManage();
				}else{
					System.out.println("Invalid Choice! Try again");
					continue L1;
				}
			}while(true);
		
	}
	public static void deleteSupplier(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		DELETE SUPPLIER                |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		L1:
			do{
				System.out.print("\nSupplier ID: ");
				String supId = input.next();
		
				boolean isDeleted = deleteSup(supId);
		
				if(isDeleted){
					System.out.print("Deleted Successfully!");
					deleteAnotherSupplier();
				}else{
					System.out.println("Can't find Supplier ID.Try again");
					continue L1;
				}
			}while(true);
	}
	public static boolean deleteSup(String supId){
		int index = -1;
		
		for(int i = 0; i < suppliers.length; i++){
			if(suppliers[i][0].equals(supId)){
				index = i;
				break;
			}
		}
		
		if(index != -1){
			String[][] temp = new String[suppliers.length - 1][2];
			int j = 0;
			for(int i = 0; i < suppliers.length; i++){
				if(i != index){
					temp[j] = suppliers[i];
					j++;
				}
			}
			suppliers = temp;
			return true;
		}
		return false;
	}
	public static void deleteAnotherSupplier(){
		L1:
			do{
				System.out.print("Do you want to delete another Supplier(Y/N): ");
				char in = input.next().charAt(0);
			
				if(in == 'Y' || in == 'y'){
					deleteSupplier();
				}else if(in == 'N' || in == 'n'){
					supplierManage();
				}else{
					System.out.println("Invalid Choice! Try again");
					continue L1;
				}
			}while(true);
	}
	public static void viewSupplier(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		VIEW SUPPLIER                  |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		System.out.println();
		
		System.out.println("+------------------+-----------------+");
		System.out.printf("| %-16s | %-16s|\n", "Supplier ID", "Supplier Name");
		System.out.println("+------------------+-----------------+");
		for(int i = 0; i < suppliers.length; i++){
			System.out.printf("| %-16s | %-16s|\n", suppliers[i][0], suppliers[i][1]);
		}
		System.out.println("+------------------+-----------------+");
		
		System.out.println();
		viewAnotherSupplier();
		
	}
	public static void viewAnotherSupplier(){
		L1:
			do{
				System.out.print("Do you want to go Supplier Manage Page(Y/N): ");
				char in = input.next().charAt(0);
			
				if(in == 'Y' || in == 'y'){
					supplierManage();
				}else if(in == 'N' || in == 'n'){
					viewSupplier();
				}else{
					System.out.println("Invalid choice! Try again");
					continue L1;
				}
			}while(true);
		
	}
	public static void searchSupplier(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		SEARCH SUPPLIER                |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		String supId;
		
	L1:	
		do{
			System.out.print("\nSupplier ID: ");
			supId = input.next();
			
			int supIndex = getSupIndex(supId);
			if(supIndex == -1){
				System.out.println("Can't find Supplier ID.Try Again!");
				continue L1;
			}else{
				System.out.println("\nSupplier ID: " + suppliers[supIndex][0]);
				System.out.println("Supplier Name: " + suppliers[supIndex][1]);
			}
			
			System.out.print("\nSearch Successfull!");
			searchAnotherSupplier();
			
			return;
			
		}while(true);
	}
	public static void searchAnotherSupplier(){
		L1:
			do{
				System.out.print("Do you want to search another supplier(Y/N): ");
				char in = input.next().charAt(0);
			
				if(in == 'Y' || in == 'y'){
					searchSupplier();
				}else if(in == 'N' || in == 'n'){
					supplierManage();
				}else{
					System.out.println("Invalid choice! Try again");
					continue L1;
				}
			}while(true);
		
	}
	public static void stockManage(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		STOCK MANAGE                   |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		System.out.println();
		
		int op;
		
		do{
			System.out.print("[1] Manage Item Categories");
			System.out.println("\t[2] Add Item");
			System.out.print("[3] Get Items Supplier Wise");
			System.out.println("\t[4] View Items");
			System.out.print("[5] Rank Items Per Unit Price");
			System.out.println("\t[6] Home Page");
			
			System.out.println();
			
			System.out.print("Enter an option to continue > ");
			op = input.nextInt();
			
			switch(op){
				case 1:
					manageItemCat();
					break;
				case 2:
					addItem();
					break;
				case 3:
					getItemsSupWise();
					break;
				case 4:
					viewItems();
					break;
				case 5:
					rankItems();
					break;
				case 6:
					homePage();
					break;
				default:
					System.out.println("Invalid choice!!");
			}
		}while(op != 6);
	}
	public static void manageItemCat(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		MANAGE ITEM CATEGORY           |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		System.out.println();
		
		int op;
		
		do{
			System.out.print("[1] Add New Item Category");
			System.out.println("\t[2] Delete Item Category ");
			System.out.print("[3] Update Item Category");
			System.out.println("\t[4] Stock Management");
			
			System.out.println();
			
			System.out.print("Enter an option to continue > ");
			op = input.nextInt();
			
			switch(op){
				case 1:
					addNewItemCat();
					break;
				case 2:
					deleteItemCat();
					break;
				case 3:
					updateItemCat();
					break;
				case 4:
					stockManage();
				default:
					System.out.println("Invalid Choice!");
			}
		}while(op != 4);
	}
	public static void addNewItemCat(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		ADD NEW ITEM CATEGORY          |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		
		String category;
	L1:
		do{
			System.out.print("\nEnter the new Item Category: ");
			category = input.next();
			
			if(isCatExists(category)){
				System.out.println("Category already exists! Try again");
				continue L1;
			}else{
				add(category);
				System.out.print("Added Successfully!");
				addAnotherItemCat();
			}
		}while(true);
	}
	public static boolean isCatExists(String category){
		for(int i = 0; i < itemCat.length; i++){
			if(itemCat[i].equals(category)){
				return true;
			}
		}
		return false;
	}
	public static void add(String category){
		String[] temp = new String[itemCat.length + 1];
		for(int i = 0; i < itemCat.length; i++){
			temp[i] = itemCat[i];
		}
		temp[temp.length - 1] = category;
		itemCat = temp;
	}
	public static void addAnotherItemCat(){
		L1:
			do{
				System.out.print("Do you want to add another item category (Y/N): ");
				char in = input.next().charAt(0);
		
				if(in == 'Y' || in == 'y'){
					addNewItemCat();
				}else if(in == 'N' || in == 'n'){
					manageItemCat();
				}else{
					System.out.println("Invalid Choice! Try again");
					continue L1;
				}
			}while(true);
		
	}
	public static void deleteItemCat(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		DELETE ITEM CATEGORY           |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		
		System.out.print("\nEnter Item Category to delete: ");
		String category = input.next();
		
		boolean isDeleted = delete(category);
		
		if(isDeleted){
			System.out.print("Deleted Successfully!");
			deleteAnotherCat();
		}
	}
	public static boolean delete(String category){
		int index = -1;
		
		for(int i = 0; i < itemCat.length; i++){
			if(itemCat[i].equals(category)){
				index = i;
				break;
			}
		}
		if(index != -1){
			String[] temp = new String[itemCat.length - 1];
			int j = 0;
			for(int i = 0; i < itemCat.length; i++){
				if(i != index){
					temp[j] = itemCat[i];
					j++;
				}
			}
			itemCat = temp;
			return true;
		}
		return false;
	}
	public static void deleteAnotherCat(){
		System.out.print("Do you want to delete another category (Y/N): ");
		char in = input.next().charAt(0);
			
			if(in == 'Y' || in == 'y'){
				deleteItemCat();
			}else{
				manageItemCat();
			} 
	}
	public static void updateItemCat(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		UPDATE ITEM CATEGORY           |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		
		String category;
		L1:
			do{
				System.out.print("\nEnter Item Catergory to Update: ");
				category = input.next();
				
				int catIndex = getCatIndex(category);
				if(catIndex == -1){
					System.out.println("Can't find Item Category.Try Again!");
					continue L1;
				}else{
					System.out.println("Item Category: " + itemCat[catIndex]);
					
					System.out.print("\nEnter New Item Category: ");
					String newCategory = input.next();
					
					itemCat[catIndex] = newCategory;
					
					System.out.print("\nUpdated Successfully!");
					updateAnotherItemCat();
					return;
				}
			}while(true);
	}
	public static int getCatIndex(String category){
		for(int i = 0; i < itemCat.length; i++){
			if(itemCat[i].equals(category)){
				return i;
			}
		}
		return -1;
	}
	public static void updateAnotherItemCat(){
		L1:
			do{
				System.out.print("Do you want to update another Category (Y/N): ");
				char in = input.next().charAt(0);
					
				if(in == 'Y' || in == 'y'){
					updateItemCat();
				}else if(in == 'N' || in == 'n'){
					manageItemCat();
				}else{
					System.out.println("Invalid choice! Try again");
					continue L1;
				}
			}while(true);
		
	}
	public static void addItem(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		    ADD ITEM                   |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		
		if(itemCat.length == 0){
			System.out.println("\nOOPS! It seems that you don't have any item categories in the system.");
			System.out.print("Do you want to add a new Item Category(Y/N): ");
			char in = input.next().charAt(0);
				
			if(in == 'Y' || in == 'y'){
				addNewItemCat();
			}else if(in == 'N' || in == 'n'){
				addItem();
			}
			return;
		}
		if(suppliers.length == 0){
			System.out.println("\nOOPS! It seems that you don't have any suppliers in the system.");
			System.out.print("Do you want to add a new Supplier(Y/N): ");
			char in = input.next().charAt(0);
				
			if(in == 'Y' || in == 'y'){
				addNewSupplier();
			}else if(in == 'N' || in == 'n'){
				addItem();
			}
			return;
		}
		
	L1:
		do{
			System.out.print("Enter Item Code: ");
			String itemCode = input.next();
			System.out.println();
			
			if(isItemCodeExists(itemCode)){
				System.out.println("Item Code already exists.Try again!");
				continue L1;
			}else{
				items = addItem(items);
				items[items.length - 1][0] = itemCode;
				
				displaySupplierList();
				
				System.out.print("Enter the Supplier Number: ");
				int supNum = input.nextInt();
				
				items[items.length - 1][1] = suppliers[supNum - 1][0];
				
				categoryList();
				
				System.out.print("Enter the Category Number > ");
				int catNum = input.nextInt();
				
				items[items.length - 1][2] = itemCat[catNum - 1];
				
				System.out.print("\nDescription: ");
				String desc = input.next();
				
				items[items.length - 1][3] = desc;
				
				System.out.print("Unit Price: ");
				double unitPrice = input.nextDouble();
				
				items[items.length - 1][4] = String.valueOf(unitPrice);
				itemPrice = addItemPrice();
				itemPrice[itemPrice.length - 1] = unitPrice;
				
				System.out.print("Qty On Hand: ");
				int qty = input.nextInt();
				
				items[items.length - 1][5] = String.valueOf(qty);
				
				System.out.print("\nAdded Successfully!");
				addAnotherItem();
				
				break;
			}
		}while(true);
		
		
	}
	public static boolean isItemCodeExists(String itemCode){
		for(int i = 0; i < items.length; i++){
			if(items[i][0].equals(itemCode)){
				return true;
			}
		}
		return false;
	}
	public static void displaySupplierList(){
		System.out.println("Suppliers List: ");
		String line = "+------------+-----------------+-------------------+";

		System.out.println(line);
		System.out.printf("|    %-5s   | %-16s|%16s   |\n", "#", "Supplier ID", "Supplier Name");
		System.out.println(line);

		for (int i = 0; i < suppliers.length; i++) {
			System.out.printf("|    %-5s   | %-16s|   %-16s|\n", (i + 1), suppliers[i][0], suppliers[i][1]);
		}
		System.out.println(line);
	}
	public static String[][] addItem(String[][] items){
		String[][] temp = new String[items.length + 1][6];
		for(int i = 0; i < items.length; i++){
			for(int j = 0; j < items[i].length; j++){
				temp[i][j] = items[i][j];
			}
		}
		return temp;
	}
	public static double[] addItemPrice(){
		double[] temp = new double[itemPrice.length + 1];
		for(int i = 0; i < itemPrice.length; i++){
			temp[i] = itemPrice[i];
		}
		return temp;
	}
	public static void categoryList(){
		System.out.println("\nItem Categories: ");
		String line = "+--------+----------------+";
		
		System.out.println(line);
		System.out.printf("|   %-5s|   %-10s|\n","#", "Category Name");
		System.out.println(line);
		
		for(int i = 0; i < itemCat.length; i++){
			System.out.printf("|   %-5s|   %-10s   |\n",(i + 1), itemCat[i]);
		}
		System.out.println(line);
	}
	public static void addAnotherItem(){
		L1:
			do{
				System.out.print("Do you want to add another Item(Y/N)? ");
				char in = input.next().charAt(0);
		
				if(in == 'Y' || in == 'y'){
					addItem();
				}else if(in == 'N' || in == 'n'){
					stockManage();
				}else{
					System.out.println("Invalid choice! Try again");
					continue L1;
				}
			}while(true);
		
	}
	public static void getItemsSupWise(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		SEARCH SUPPLIER                |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		String supId;
		
	L1:	
		do{
			System.out.print("\nSupplier ID: ");
			supId = input.next();
			
			int supIndex = getSupIndex(supId);
			if(supIndex != -1){
				System.out.println("Supplier Name: " + suppliers[supIndex][1]);
				
				System.out.println();
				
				displayItemsSupplierWise(supId);
				
				System.out.print("Search Successfull! ");
				searchAnotherItem();
				
				break;
			}else{
				System.out.println("Can't Find Supplier.Try Again!");
				continue L1;
			}
		}while(true);
	}
	public static void displayItemsSupplierWise(String supId){
		String line = "+----------+-------------+------------+---------------+-----------+";
		
		System.out.println(line);
		System.out.printf("| %-6s|%-13s| %-10s | %12s  | %-8s  |\n","Item Code","Description","Unit Price",
		"Qty On Hand","Category");
		System.out.println(line);
		
		for(int i = 0; i < items.length; i++){
				if(items[i][1].equals(supId)){
					System.out.printf("| %-6s   |%-13s| %-10s | %-12s  | %-8s  |\n",items[i][0],items[i][3],items[i][4],
					items[i][5],items[i][2]);
				}
			}
		System.out.println(line);
	}
	public static void searchAnotherItem(){
		L1:
			do{
				System.out.print("Do you want to search another(Y/N)? ");
				char in = input.next().charAt(0);
		
				if(in == 'Y' || in == 'y'){
					getItemsSupWise();
				}else if(in == 'N' || in == 'n'){
					stockManage();
				}else{
					System.out.println("Invalid choice! Try again");
					continue L1;
				}
			}while(true);
		
	}
	public static void viewItems(){
		clearConsole();
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		VIEW ITEMS                     |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		System.out.println();
		
		for(int i = 0; i < itemCat.length; i++){
			String line = "+-------+--------+-----------+----------+--------+";
			String category = itemCat[i];
			
			System.out.println(category + ":");
		
			System.out.println(line);
			System.out.printf("| %-6s| %-7s| %-10s| %-9s| %-7s|\n","SID","CODE","DESC","PRICE","QTY");
			System.out.println(line);
		
		
			
			for(int j = 0; j < items.length; j++){
				if(items[j][2].equals(category)){
					System.out.printf("| %-6s| %-7s| %-10s| %-9s| %-7s|\n",items[j][1],items[j][0],items[j][3],
					items[j][4],items[j][5]);
			    }
			}
			System.out.println(line);
			System.out.println();
		}
		backToStockManage();
	}
	public static void backToStockManage(){
		L1:
			do{
				System.out.print("Do you want to go stock manage page(Y/N)? ");
				char in = input.next().charAt(0);
		
				if(in == 'Y' || in == 'y'){
					stockManage();
				}else if(in == 'N' || in == 'n'){
					viewItems();
				}else{
					System.out.println("Invalid choice! Try again");
					continue L1;
				}
			}while(true);
		
	}
	public static void rankItems(){
		clearConsole();
		
		String line = "+-------+-------+-------------+-----------+---------+---------+";
		
		System.out.printf("+------------------------------------------------------+\n");
		System.out.printf("|            		RANKED UNIT PRICE              |\n");
		System.out.printf("+------------------------------------------------------+\n");
		
		System.out.println();
		
		String[][] temp = new String[items.length][6];
		for(int i = 0; i < items.length; i++){
			for(int j = 0; j < items[i].length; j++){
				temp[i][j] = items[i][j];
			}
		}
		
		for(int i = 0; i < temp.length - 1; i++){
			for(int j = 0; j < temp.length - i - 1; j++){
				double price1 = Double.valueOf(temp[j][4]);
				double price2 = Double.valueOf(temp[j + 1][4]);
				if(price1 < price2){
					String[] newTemp = temp[j];
					temp[j] = temp[j + 1];
					temp[j + 1] = newTemp;
				}
			}
		}
		System.out.println(line);
		System.out.printf("| %-6s| %-6s| %-12s| %-10s| %-8s| %-8s|\n","SID","CODE","DESC","PRICE","QTY","CAT");
		System.out.println(line);
		
		for(int i = 0; i < temp.length; i++){
			System.out.printf("| %-6s| %-6s| %-12s| %-10s| %-8s| %-8s|\n",temp[i][1],temp[i][0],temp[i][3],
			temp[i][4],temp[i][5],temp[i][2]);
		}
		System.out.println(line);
		backToStockManageTwo();
	}
	public static void backToStockManageTwo(){
		L1:
			do{
				System.out.print("Do you want to go stock manage page(Y/N)? ");
				char in = input.next().charAt(0);
		
				if(in == 'Y' || in == 'y'){
					stockManage();
				}else if(in == 'N' || in == 'n'){
					rankItems();
				}else{
					System.out.println("Invalid choice!");
					continue L1;
				}
			}while(true);
		}
	public static void main(String args[]){
				loginPage();
	}
}
