package src;

public class Wrapper {
    public static void main(String[] args){

        Boolean a = true;


        
        Character b = '@';  // primitive -> wrapper (enveloppe)
        Integer c = 123;
        Double d = 3.14;
        String e = "Lucas";

        if (b=='@'){
            System.out.println("This is true");
        }

        a.booleanValue();
        System.out.println(a);

        if (c.compareTo(1455433)==0){
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }

    }


}
