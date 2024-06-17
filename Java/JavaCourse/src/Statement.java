import java.util.Scanner;

public class Statement {
    public static void main(String[] args){

        Scanner askAge = new Scanner(System.in);

        int age = askAge.nextInt();


        if (age >= 75 && age >= 18) {
            System.out.println(" You are retired");
        }
        else if(age >= 18) {
            System.out.println(" You are an adult");
        }
        else {
            System.out.println(" You are not an adult");
        }



    }
}
