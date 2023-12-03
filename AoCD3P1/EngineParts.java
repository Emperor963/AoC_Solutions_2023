import java.util.Scanner;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class EngineParts {

    private Scanner scnr;
    //private File toRead;
    private HashMap<Character, Character> symbols = new HashMap();
    private char[][] input;
    public int sum;


    public EngineParts(Scanner scnr) throws FileNotFoundException{
        this.scnr = scnr;
        this.sum = 0;

        symbols.put('@','@');
        symbols.put('#','#');
        symbols.put('$','$');
        symbols.put('%','%');
        symbols.put('&','&');
        symbols.put('+','+');
        symbols.put('=','=');
        symbols.put('*','*');
        symbols.put('/','/');

        input = new char[140][140];
    }

    public void fileReader(){
        int i = 0;
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            //System.out.println(line);
            input[i] = line.toCharArray();
            i++;
        }
    }

    public void parser(int lineNumber){
        
        for(int i = 0; i < 140; i++){
            if(!symbols.containsKey(input[lineNumber][i])) continue;
            int hundreds = 0;
            int tens = 0;
            int ones = 0;
            //Case where the number comes right after the special character
            if(i + 1 < 139 && Character.isDigit(input[lineNumber][i+1])){

                hundreds = 100 * Character.getNumericValue(input[lineNumber][i+1]);
                if(i+2 < 139 && Character.isDigit(input[lineNumber][i+2])) tens = 10 * Character.getNumericValue(input[lineNumber][i+2]);
                if(i+3 < 139 && Character.isDigit(input[lineNumber][i+3])) ones = 1 * Character.getNumericValue(input[lineNumber][i+3]);

                int number = hundreds + tens + ones;

                if(tens == 0 && ones == 0) number = number/100;
                if(ones == 0) number = number/10;

                //System.out.println(number);
                this.sum += number;
            }

            //Case where the number comes right before the special character
            if(i-1 > 0 && Character.isDigit(input[lineNumber][i-1])){

                ones = 1* Character.getNumericValue(input[lineNumber][i-1]);
                if(i-2 > 0 && Character.isDigit(input[lineNumber][i-2])) tens = 10 * Character.getNumericValue(input[lineNumber][i-2]);
                if(i-3 > 0 && Character.isDigit(input[lineNumber][i-3])) hundreds = 100 * Character.getNumericValue(input[lineNumber][i-3]);

                int number = hundreds + tens + ones;

                if(tens == 0 && ones == 0) number = number/100;
                if(ones == 0) number = number/10;

                //System.out.println(number);
                this.sum += number;
            }

            //Case where number is upper left to symbol
            if(i-1 > 0 && Character.isDigit(input[lineNumber - 1][i-1])){

                ones = 1* Character.getNumericValue(input[lineNumber - 1][i-1]);
                if(i-2 > 0 && Character.isDigit(input[lineNumber - 1][i-2])) tens = 10 * Character.getNumericValue(input[lineNumber - 1][i-2]);
                if(i-3 > 0 && Character.isDigit(input[lineNumber - 1][i-3])) hundreds = 100 * Character.getNumericValue(input[lineNumber - 1][i-3]);

                int number = hundreds + tens + ones;

                if(tens == 0 && ones == 0) number = number/100;
                if(ones == 0) number = number/10;

                //System.out.println(number);
                this.sum += number;
            }
            //Case where number is upper right to symbol
            if(i+1 < 139 && Character.isDigit(input[lineNumber - 1][i+1])){

                hundreds = 100 * Character.getNumericValue(input[lineNumber - 1][i+1]);
                if(i+2 < 139 && Character.isDigit(input[lineNumber - 1][i+2])) tens = 10 * Character.getNumericValue(input[lineNumber - 1][i+2]);
                if(i+3 < 139 && Character.isDigit(input[lineNumber - 1][i+3])) ones = 1 * Character.getNumericValue(input[lineNumber - 1][i+3]);

                int number = hundreds + tens + ones;

                if(tens == 0 && ones == 0) number = number/100;
                if(ones == 0) number = number/10;

                //System.out.println(number);
                this.sum += number;
            }
            //Case where number is lower left to symbol
            if(i-1 > 0 && Character.isDigit(input[lineNumber + 1][i-1])){

                ones = 1* Character.getNumericValue(input[lineNumber + 1][i-1]);
                if(i-2 > 0 && Character.isDigit(input[lineNumber + 1][i-2])) tens = 10 * Character.getNumericValue(input[lineNumber + 1][i-2]);
                if(i-3 > 0 && Character.isDigit(input[lineNumber + 1][i-3])) hundreds = 100 * Character.getNumericValue(input[lineNumber + 1][i-3]);

                int number = hundreds + tens + ones;

                if(tens == 0 && ones == 0) number = number/100;
                if(ones == 0) number = number/10;

                //System.out.println(number);
                this.sum += number;
            }
            //Case where number is lower right to symbol
            if(i+1 < 139 && Character.isDigit(input[lineNumber + 1][i+1])){

                hundreds = 100 * Character.getNumericValue(input[lineNumber + 1][i+1]);
                if(i+2 < 139 && Character.isDigit(input[lineNumber + 1][i+2])) tens = 10 * Character.getNumericValue(input[lineNumber + 1][i+2]);
                if(i+3 < 139 && Character.isDigit(input[lineNumber + 1][i+3])) ones = 1 * Character.getNumericValue(input[lineNumber + 1][i+3]);

                int number = hundreds + tens + ones;

                if(tens == 0 && ones == 0) number = number/100;
                if(ones == 0) number = number/10;

                //System.out.println(number);
                this.sum += number;
            }

            //TODO: Number is directly above symbol

            //TODO: Number is directly below symbol

        }
    }

    public void mainHelper(){
        for(int i = 2; i < 139; i++){
           fileReader();
           parser(i);
        }
    }


    public static void main(String[] args) throws FileNotFoundException{

        File file = new File("Engine.txt");
        Scanner scan = new Scanner(file);
        
        int sum = 0;
        try{
            EngineParts engine = new EngineParts(scan);
            engine.mainHelper();
            sum = engine.sum;

        }catch(FileNotFoundException e){
            System.out.println("Error during fetching file");
        }

        System.out.println(sum);
    }

}
