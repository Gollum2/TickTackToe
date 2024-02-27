import java.util.ArrayList;
import java.util.Scanner;

public class Spielfeld {

    private ArrayList<ArrayList<Character>> spielfeld = new ArrayList<ArrayList<Character>>();
    char symbolP1 = 'X';
    char symbolP2 = 'O';
    boolean player1Turn = true;
    Spielfeld(){
        for(ArrayList<Character> l : spielfeld){
            l = new ArrayList<>();
            l.add(' ');
        }
    }
    Spielfeld(char p1, char p2){
        super();
        symbolP1 = p1;
        symbolP2 = p2;
    }

    public void playersTurn(){
        setUserInput(getUserInput());
    }

    private int[] getUserInput(){
        Scanner scanner = new Scanner(System.in);
        int[] inputs = new int[2];
        do{
            System.out.println("Wähle dein großes Feld!");
            inputs[0] = scanner.nextInt()-1;
            System.out.println("Wähle dein kleines Feld!");
            inputs[1] = scanner.nextInt() - 1;
        }
        while(!inputIsValid(inputs));
        return inputs;
    }

    private void setUserInput(int[] input){
        spielfeld.get(input[0]).set(input[1], getCurrentSymbol());
    }

    private boolean inputIsValid(int[] input){
        if(input[0] < 0 || input[0] > 8 || input[1] < 0 || input[1] > 8){
            return false;
        }
        if(getValue(input) != ' '){
            return false;
        };
        return true;
    }

    private char getCurrentSymbol(){
        return player1Turn ? symbolP1 : symbolP2;
    }

    private char getValue(int[] input){
        return spielfeld.get(input[0]).get(input[1]);
    }
}
