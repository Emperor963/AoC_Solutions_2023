import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

public class Gambling {
    
    int points;
    Scanner scnr;

    public Gambling(Scanner scnr){
        this.points = 0;
        this.scnr = scnr;
    }


    public double reader(){
        String line;
        String[] winner = new String[10];
        String[] cards = new String[25];
        if(true){
            line = scnr.nextLine();
            String[] parts = line.split(":");
            String winnerVal = parts[1].substring(0,30);
            String cardsVal = parts[1].substring(32,parts[1].length());
            winner = winnerVal.trim().split(" ");
            cards = cardsVal.trim().split(" ");
        }

        HashMap<String,Integer> winners = new HashMap();

        for(String wins : winner){
            //System.out.print(wins.trim() + "  ");
            if(wins.equals("")) continue;
            winners.put(wins, Integer.valueOf(wins.trim()));
        }
        //System.out.println();

        double pointsForCard = 0.5;

        for(String values : cards){
            //System.out.print(values.trim() + " ");
            if(winners.containsKey(values)) pointsForCard *= 2;
        }
        //System.out.println();


        if(pointsForCard == 0.5) return 0;

        return pointsForCard;
    }

    public void mainHelper(){
        while(scnr.hasNextLine()){
            this.points += reader();
        }
    }

    public static void main(String[] args) throws FileNotFoundException{

        File file = new File("card.txt");
        try{
            Scanner scnr = new Scanner(file);
            //scnr.nextLine();
            Gambling gamble = new Gambling(scnr);
            gamble.mainHelper();
            System.out.println(gamble.points);
        }catch(FileNotFoundException e){

        }
        
    }
}
