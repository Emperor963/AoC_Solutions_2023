import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class ElfGames {

    private Scanner scnr;
    private File readFile;
    public int reds;
    public int blues;
    public int greens;


    public ElfGames(File readFile, int reds, int blues, int greens) throws FileNotFoundException{
        if(readFile == null) throw new FileNotFoundException("File was not found");
        
        this.readFile = readFile;
        this.scnr = new Scanner(readFile);
        this.reds = reds;
        this.blues = blues;
        this.greens = greens;
    }


    public String[] gameReader(){
        String line = scnr.nextLine();
        String[] game;
        String[] parts = new String[100];
        try{
            game = line.split(":");
            game[1] = game[1].trim();
            parts = game[1].split(";");
        }catch(Exception e){
            System.out.println("Error while parsing file.");
        }
        return parts;
    }

    public boolean verifier(String[] parts){
        String[] balls = new String[3];
        boolean verifier = true;
        //System.out.println("Hello?");
        for(int i = 0; i < parts.length; i++){
            //System.out.println(parts[i]);
            balls = parts[i].split(",");
            boolean roundIsPossible = verifierPerRound(balls);
            //System.out.println(roundIsPossible);
            verifier = verifier && roundIsPossible;
            //System.out.println(verifier + "  verifier");
        }

        return verifier;
    }

    public boolean verifierPerRound(String[] balls){
        boolean possible = true;

        for(int i = 0; i < balls.length; i++){
            //System.out.println(balls[i]);
            if(balls[i].contains("red")){
                int result = this.reds - Integer.parseInt(balls[i].substring(0, balls[i].indexOf('r')).trim());
                //System.out.println(result + "   red");
                if(result < 0) return false;//possible = possible || false; 
            }
            if(balls[i].contains("blue")){
                int result = this.blues - Integer.parseInt(balls[i].substring(0, balls[i].indexOf('b')).trim());
                //System.out.println(result + "   blue");
                if(result < 0) return false;//possible = possible || false;
            }
            if(balls[i].contains("green")){
                int result = this.greens - Integer.parseInt(balls[i].substring(0, balls[i].indexOf('g')).trim());
                //System.out.println(result + "   green");
                if(result < 0) return false;//possible = possible || false;
            }
        }
        return possible;
    }

    public int mainHelper(){
        int gameCounter = 0;
        int successes = 0;
        while(scnr.hasNextLine()){
            //System.out.println("What");
            gameCounter++;
            String [] parts = gameReader();
            //System.out.println(parts[1]);
            if(verifier(parts)) successes += gameCounter;
        }

        return successes;
    }

    public static void main(String[] args) throws FileNotFoundException{
        int reds= 12;
        int blues = 14;
        int greens = 13;
        File file = new File("AoC-D2P1.txt");
        int success = 0;
        try{
            ElfGames game = new ElfGames(file, reds, blues, greens);
            success = game.mainHelper();
        }catch(FileNotFoundException e){
            System.out.println("File was not found");
        }

        System.out.println(success);
    }
}
