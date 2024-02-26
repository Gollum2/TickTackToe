import java.util.ArrayList;

public class Spielfeld {
    private ArrayList<ArrayList<Character>> spielfeld = new ArrayList<ArrayList<Character>>();
    Spielfeld(){
        for(ArrayList<Character> l : spielfeld){
            l = new ArrayList<>();
            l.add(' ');
        }
    }

    public int getUserInput(){
        System.out.println("Wähle dein Feld!");
        return 0;
    }
}
