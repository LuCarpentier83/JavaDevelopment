import java.util.Scanner;

public class Input{
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println( "What is your name ");
        String name = scanner.nextLine();

        System.out.println( "How old are you ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println( "Whats your favorite food ");
        String food = scanner.nextLine();



        System.out.println( "Hello " + name);
        System.out.println( "You are " + age + " yo");
        System.out.println( "You like " + food);
    }
}
