// we need to create an ATM System which will be handling following operations
// 1. Deposit
// 2. Withdraw
// 3. Check Balence
// 4. Transfer
// 5. close
// Date of Creation = 08-Nov-2023
// Octa Net Internship Project
import java.util.*;
class User{
    String userID;
    int pin;
    double balence;
    
    User(String userID, int pin, double balence){
        this.userID = userID;
        this.pin = pin;
        this.balence  = balence;
    }
    // getter methods
    public String getUserID(){
        return userID;
    }
    public int getPin(){
        return this.pin;
    }
    public double getBalence(){
        return this.balence;
    }
    // deposite Mehtod
    public void deposit(double amount){
        balence += amount;
    }

    // Withdraw Methodn
    public void withdraw(User currentUser, double amount){
        if(amount > balence)
            System.out.println("Insufficient Balence");
        else if(amount < 0){
            System.out.println("Enter Valid amount");
        }
        else{
            balence -= amount;
            System.out.println("Withdrawn Amount Rs : "+ amount);
        }
    }
    // Transfer Method
    public void transfer(User reciever, double amount){
        if(reciever == null)
            System.out.println("User not found");
        else if(amount <= 0)
            System.out.println("Enter valid amount");
        else if(amount > balence)
            System.out.println("Insufficient Fund");
        else{
            balence -= amount;
            reciever.deposit(amount); 
            System.out.println("Transafered Amount Rs : "+amount);
        }
    }

}
class ATMSystem{
    private static HashMap<String, User> users = new HashMap<>();
    public static User currentUser = null;
    public static void main(String[] args) {
        initializeUser();
        login();
    }
    public static void initializeUser(){
        users.put("Asif@123",new User("Asif@1234",1234,20000));
        users.put("Aqib@121", new User("Aqib@121",1111,15000));
        users.put("Asif@222",new User("Asif@222",1234,10000));
        users.put("M@321", new User("M@321",2712,30000));
    }
    public static void login(){
        Scanner sc = new Scanner(System.in);
        try{
        System.out.println("_______________________");
        System.out.print("Enter User ID : ");
        
        String uID = sc.nextLine();
        System.out.print("Enter ATM PIN : ");
        int pin = sc.nextInt();
        
        User user = users.get(uID);
        if(user != null && user.getPin() == pin){
            currentUser = user;
            showOperations();
        }
        else{
            System.out.println("------------------------");
            System.out.println("Invalid User ID or PIN, Please try again");
            login();
        }
        }
        catch(Exception e){
            System.out.println("------------------------");
            System.out.println("Invalid Pin, Please Enter Correct User ID and PIN");
            sc.nextLine();
            login();
        }
    }
    public static void showOperations(){
        Scanner sc = new Scanner(System.in);
        
        while(currentUser != null){
            System.out.println("------------------------");
            System.out.println("Hello Welcome to ATM System, Please Enter Your choice");
            System.out.println("    ------------------------");
            System.out.println("    |    1 : Check Balence |");
            System.out.println("    |    2 : Deposite      |");
            System.out.println("    |    3 : Withdraw      |");
            System.out.println("    |    4 : Transfer      |");
            System.out.println("    |    5 : Exit          |");
            System.out.println("    ------------------------");
            
            int choice;
            try{
                System.out.print("Enter Your Choice : ");
                choice = sc.nextInt();
            }
            catch(Exception e){
                System.out.println("Invlaid Choice, Please Enter a valid choice");
                sc.nextLine();
                continue;
            }
            switch (choice) {
            // case 1 for Checking Balence
                case 1: System.out.println("    ------------------------");
                        System.out.println("Your Balence is Rs: "+currentUser.getBalence());
                    break;
            //case 2 for Depositin the Money    
                case 2: System.out.println("    ------------------------");
                        System.out.print("Enter Amount for Deposite Rs : ");
                        double amount;
                        try{
                            amount = sc.nextDouble();
                        }
                        catch(Exception e){
                            System.out.println("Please, Enter valid Amount");
                            continue;
                        }
                        if(amount > 0){
                            currentUser.deposit(amount);
                            System.out.println("Deposited Rs: "+amount+" Successfully ");
                        }
                        else{
                            System.out.println("Invalid Amount, Please Enter Positive amount");
                            continue;
                        }
                    break;

            
                
        // case 3 for withdrawing of money        
                case 3: System.out.println("    ------------------------");
                        System.out.print("Enter Amount for Withdrawal Rs : ");
                        double withdrawAmount;
                        try{
                            withdrawAmount = sc.nextDouble();
                        }
                        catch(Exception e){
                            System.out.println("Invalid Amount, Please Enter valid Amount");
                            sc.nextLine();
                            continue;
                        }
                        currentUser.withdraw(currentUser, withdrawAmount);
                    break;
                // case 4 for Transfering money to another user
                case 4: System.out.println("    ------------------------");
                        System.out.print("Enter Recipient's UserID : ");
                    sc.nextLine();
                        String recipientUserID;
                        try{
                            recipientUserID = sc.nextLine();
                        }
                        catch(Exception e){
                            System.out.println("Enter Valid Recipient's User ID");
                            sc.nextLine();
                            continue;
                        }

                        User recipient = users.get(recipientUserID);
                        if(recipient != null){
                            System.out.println("    ------------------------");
                            System.out.print("Enter Amount for Transfer Rs :");
                            double transferAmount;
                            try{
                                transferAmount = sc.nextDouble();
                            }
                            catch(Exception e){
                                System.out.print("Enter valid Amount");
                                sc.nextLine();
                                continue;
                            }
                            currentUser.transfer(recipient, transferAmount);
                        }
                        else{
                            System.out.println("Invalid Recipient's UserID, Please try again");
                            continue;
                        }
                    break;
                // case 5 for closing the ATM System -> so we initialize the currentUser as null
                case 5: currentUser = null;
                        System.out.println("    ------------------------");
                        System.out.println("Thank You for choosing ATM System, Have a Nice Day!");
                    break;

                // default case if user enters wrong choice input
                default:System.out.println("    ------------------------"); 
                        System.out.println("Invalid Choice you have made, Please Enter Correct Choice");
                    break;
            }
            
        }
       
    }
}