import java.util.Scanner;

public class BankTrans{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double balance = 140.57;
        boolean status = true;

        while (status) {
            System.out.println("\nSelect an Option:");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Transfer Money");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
 
            switch (choice) {
                case 1:
                    System.out.println("Executing: Creating a new account...");
                    break;

                case 2:
                    System.out.println("Executing: User Login...");
                    break;

                case 3:
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = sc.nextDouble();
                    System.out.println("Executing: Transferring " + transferAmount);
                    break;

                case 4:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    if (withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;

                case 5:
                    System.out.println("Current Balance: Rs " + balance);
                    break;

                case 6:
                        status = false;
                        break;

                default:
                    System.out.println("Invalid choice! Please select 1-6.");
            }
        }
    }
}