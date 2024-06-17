package src;

public class Arrays {
    public static void main(String[] args){

        String[] car =  {"camaro", "corvette", "tesla", "audi"};
        car[0] = "bmw";
        System.out.println(car[0]);

        String[] moto = new String[3];
        moto[0] = "kawasaki";
        moto[1] = "yamaha";
        moto[2] = "ducati";
        System.out.println(moto[0]);

        for (int i =0; i< moto.length; i++){
            System.out.println(moto[i]);
        }

    }
}
