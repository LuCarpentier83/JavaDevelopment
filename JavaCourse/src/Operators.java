import java.util.Scanner;

public class Operators {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Give me a temperature in Celsius");
        int temp = scanner.nextInt();

        if(temp>30) {
            System.out.println("It is hot here");
        }
        else if (temp >= 20 && temp <= 30){
            System.out.println("It is warm outside");
        }
        else{
            System.out.println("It is cold outside");
        }
        System.out.println("Press Q or q to leave the program");
        String answer = scanner.next();

        /*

        if ( answer.equals("Q") || answer.equals("q")) {
            System.out.println("You left the game");
        }
        else {
            System.out.println("You're still there *pew pew*");
        }

        */

        if ( !answer.equals("Q") && !answer.equals("q")) {
            System.out.println("You're still there *pew pew*");
        }
        else {
            System.out.println("You left the game");


        }
    }
}
