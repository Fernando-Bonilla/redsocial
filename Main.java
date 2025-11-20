
public class Main {
    public static void main(String[] args) {
        System.out.println("Holitas");

        Producto prod = new Producto("pepe",  100.25);
        System.out.println(prod.getName());

        int[] num = new int[4];
        for(int i = 0 ; i < num.length; i++){
            System.out.println(num[i]);
        }
        
        testeo();
    }

    public static void testeo (){
        System.out.println("function testeo");

        int[] arr = new int[4];

        /* int i = 0;
        do {
            
            for(i = 0; i < 10; i++){
                arr[arr.length - 1] = i;


                System.out.println(printearElArray(arr)); 
            }
            arr[arr.length - 2] += 1;

            //System.out.println("asd");
        }while(giroYSigo(arr));        
         */

    }

    /* public static int[] noMessi(int[] arrayMenos){

    }
 */
    public static String printearElArray(int[] arraySet){
        String clave = "";
        for(int i = 0; i < arraySet.length; i++ ) {
            clave += arraySet[i];
        }
        return clave;
    }

    public static boolean giroYSigo(int[] arrys){

        for(int i = 0; i < arrys.length; i++){
            if(arrys[i] != 9){
                return true;
            }
        }
        return false;
    }
}