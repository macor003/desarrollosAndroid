public class test1 {

    public static void main(String[] args) {
        if (!isNumeric("1092")) {
            System.out.println("No es numero");
        } else {
            System.out.println("Es numero");
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

}
