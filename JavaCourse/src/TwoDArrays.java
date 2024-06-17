package src;

public class TwoDArrays {
    public static void main(String[] args){

        String[][] cars = new String[3][3];

        String[][] example =  { {"camaro", "corvette", "silverado"},
                                {"camaro", "corvette", "silverado"},
                                {"camaro", "corvette", "silverado"} };

        cars[0][0] = "camaro" ;
        cars[0][1] = "corvette" ;
        cars[0][2] = "silverado" ;
        cars[1][0] = "mustang" ;
        cars[1][1] = "ranger" ;
        cars[1][2] = "cadillac" ;
        cars[2][0] = "lambo" ;
        cars[2][1] = "ferrari" ;
        cars[2][2] = "tesla" ;

        for (int i = 0; i < cars.length; i++) {
            System.out.println();
            for (int j = 0; j < cars[i].length; j++) {
                System.out.print(cars[i][j]+ " ");
            }
        }
    }
}
