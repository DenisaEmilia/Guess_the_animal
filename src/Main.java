import animals.Dialog;

public class Main {

    public static void main(String[] args){

        String type = args.length > 0 && args[0].equals("-type") ? args[1] : "json";

         new Dialog(type);

    }
}
