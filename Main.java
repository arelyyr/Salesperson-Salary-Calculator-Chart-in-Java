/* Program Description: This program is designed to take in the amount of sales a salesperson made, take 9% of that amount, add $200 as that's their base salary, and save the total. The program takes the total amount and categorizes it into ranges of salary ranges. The program then prints out the ranges and the amount of salespeople that earned that salary. The program loops to take in as many inputs as the user may want, and whenever the user chooses to stop they can enter -1. The program also prints out an appluase message as it randomized to choose between four messages provided. 
 */

import java.util.Random;
import java.util.Scanner;

public class Main {
   enum Applause {
      EXCELLENT, AMAZING, SUPER, FANTASTIC
   }

   // Symbolic constants
   private static final double BASE_SALARY = 200.0;
   private static final double COMMISSION_RATE = 0.09;
   private static final int SENTINEL_VALUE = -1;

   public static void main(String[] args) {
      double[] salaryRanges = new double[]{200,300,400,500,
      600,700,800,900,1000};
      int[] countRanges = new int[salaryRanges.length];

      // Display initial table
      printSalaryTable(salaryRanges, countRanges);

      Scanner scanner = new Scanner(System.in);

      double salesAmount = 0;

      while (salesAmount != SENTINEL_VALUE) {
         salesAmount = getSalesAmount(scanner);

         if (salesAmount >= 0) {
            double totalSalary = BASE_SALARY+(COMMISSION_RATE * salesAmount);

            updateSalaryRanges(salaryRanges, countRanges, totalSalary);
         }
      }

      // Display final table
      printSalaryTable(salaryRanges, countRanges);

      // Choose applause randomly
      applaudSalesforceRandomly();

      scanner.close();
   }
   // Print to ask for sales amount
   private static double getSalesAmount(Scanner scanner) {
      System.out.print("\nEnter sales amount (-1 to stop): ");

      if (scanner.hasNextDouble()) {
         return scanner.nextDouble();
      } 
      else {
         System.out.println("Invalid input. Please enter a valid number."); // input validation (just for practice)
         scanner.next();
         return getSalesAmount(scanner); 
      }
   }
   // Updates the table
   private static void updateSalaryRanges(double[] salaryRanges, int[] 
   countRanges, double totalSalary) {
      for (int i = 0; i < salaryRanges.length; i++) {
         if (totalSalary >= salaryRanges[i] && totalSalary < (i == 
         salaryRanges.length - 1 ? Double.POSITIVE_INFINITY : 
         salaryRanges[i + 1])) {
            countRanges[i]++;
            break;
            }
      }
   }
   // Prints the table
   private static void printSalaryTable(double[] salaryRanges, int[] 
   countRanges) {
      System.out.println("\nRange\t\t\tNumber");
      for (int i = 0; i < salaryRanges.length; i++) {
         String rangeString = i == salaryRanges.length - 1 ? 
         String.format("$%.0f and over", salaryRanges[i]) : 
         String.format("$%.0f-$%.0f", salaryRanges[i], salaryRanges[i + 
         1] - 1);
            System.out.printf("%-20s%d\n", rangeString, countRanges[i]);
      }
   }
   // Prints random applause
   private static void applaudSalesforceRandomly() {
      Applause[] applauseOptions = Applause.values();
      Random random = new Random();
      Applause selectedApplause = 
      applauseOptions[random.nextInt(applauseOptions.length)];

      System.out.println(selectedApplause + "!");
   }
}