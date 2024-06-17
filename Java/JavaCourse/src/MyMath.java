import javax.swing.*;
import java.util.Scanner;

public class MyMath {
    public static void main(String[] args) {
        double x = 3.23;
        double y = 5.553; // Changed the value of y to be greater than x

        double z = Math.max(x, y);
        double w = Math.abs(y);
        double n = Math.sqrt(x);
        double r = Math.round(x);
        double p = Math.ceil(x);
        double g = Math.floor(x);

        // Hypotenuse program //
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Give the values of opp and adj");

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();

        double hypo = Math.sqrt(a*a+b*b);
        System.out.println("Hypotenuse is " + hypo);

        scanner.close();


        // System.out.println(z); // This will now correctly print the greater value, which is y
    }
}