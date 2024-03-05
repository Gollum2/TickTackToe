import java.util.ArrayList;

public class Spielfeld {
    ArrayList<ArrayList<Character>> spielfeld = new ArrayList<ArrayList<Character>>();
    Object flag=new Object();
    int[] zahlen;
    char symbolP1 = 'X';
    char symbolP2 = 'O';
    boolean player1Turn = false;

    Spielfeld() {
        for (int i = 0; i < 9; i++) {
            spielfeld.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                spielfeld.get(i).add('_');
            }
            spielfeld.get(i).add('_');//an der 10 stelle wird gespeichert ob es durchgespielt wurde
            //damit man nicht x mal neu überprüfen muss
        }
    }

    void printfeld() {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                line.append(" " + spielfeld.get(i * 3 + j).get(0) + " ");
                line.append(" " + spielfeld.get(i * 3 + j).get(1) + " ");
                if (j != 2) {
                    line.append(" " + spielfeld.get(i * 3 + j).get(2) + " ").append("|");
                } else {
                    line.append(" " + spielfeld.get(i * 3 + j).get(2) + " ");
                }
            }
            line.append("\n");
            for (int j = 0; j < 3; j++) {
                line.append(" " + spielfeld.get(i * 3 + j).get(3) + " ");
                line.append(" " + spielfeld.get(i * 3 + j).get(4) + " ");
                if (j != 2) {
                    line.append(" " + spielfeld.get(i * 3 + j).get(5) + " ").append("|");
                } else {
                    line.append(" " + spielfeld.get(i * 3 + j).get(5) + " ");
                }
            }
            line.append("\n");
            for (int j = 0; j < 3; j++) {
                line.append(" " + spielfeld.get(i * 3 + j).get(6) + " ");
                line.append(" " + spielfeld.get(i * 3 + j).get(7) + " ");
                if (j != 2) {
                    line.append(" " + spielfeld.get(i * 3 + j).get(8) + " ").append("|");
                } else {
                    line.append(" " + spielfeld.get(i * 3 + j).get(8) + " ");
                }

            }
            if (i != 2)
                line.append("\n").append("-----------------------------\n");
        }
        System.out.println(line);
    }


    Spielfeld(char p1, char p2) {
        this();
        if (p1 == '_' || p1=='!') {
            System.out.println("NICHT ERLAUBT");
            return;
        }
        if (p2 == '_' || p2=='!') {
            System.out.println("NICHT ERLAUBT");
            return;
        }
        symbolP1 = p1;
        symbolP2 = p2;
    }

    void game() throws InterruptedException {
        int feld = -1;
        char erg;
        System.out.println("game start");
        while (true) {
            int[] temp;
            temp=getUserInput(feld);
            if (!(temp[0]==-1 && temp[1]==-1)) {
                setUserInput(temp);
                //todo ich kann auf schon fertigen ticktaktoes spielen. unsere webseite sieth scheise aus
                smallgamefinished(temp[0]);
//            System.out.println(Arrays.deepToString(spielfeld.toArray()));
                if (spielfeld.get(temp[1]).get(9) == '_') {
                    feld = temp[1];
                } else {
                    feld = -1;
                }
                if ((erg = biggamechecking()) != '_') {
                    System.out.println("WE HAVE A WINNER");
                    System.out.println("player " + erg);
                }
                player1Turn = !player1Turn;
                printfeld();
                System.out.println("TURN: " + getCurrentSymbol());
            }
            synchronized (flag){
                flag.notify();
            }
        }
    }

    char biggamechecking() {
        char erg = '_';
        for (int i = 0; i < 3; i++) {
            if (spielfeld.get(i).get(9) == spielfeld.get(i + 3).get(9)
                    && spielfeld.get(i + 3).get(9) == spielfeld.get(i + 6).get(9)) {
                erg = spielfeld.get(i).get(9);
            }
        }
        for (int i = 0; i < 3; i++) {
            if (spielfeld.get(i * 3).get(9) == spielfeld.get(i * 3 + 1).get(9)
                    && spielfeld.get(i * 3 + 1).get(9) == spielfeld.get(i * 3 + 2).get(9)) {
                erg = spielfeld.get(i * 3).get(9);
            }
        }
        if (spielfeld.get(0).get(9) == spielfeld.get(4).get(9)
                && spielfeld.get(4).get(9) == spielfeld.get(8).get(9)) {
            erg = spielfeld.get(4).get(9);
        }
        if (spielfeld.get(2).get(9) == spielfeld.get(4).get(9)
                && spielfeld.get(4).get(9) == spielfeld.get(6).get(9)) {
            erg = spielfeld.get(4).get(9);
        }
        return erg;
    }

    boolean smallgamefinished(int numerofsmalgame) {
        boolean flag=false;
        for (int i = 0; i < 9; i++) {
            if(spielfeld.get(numerofsmalgame).get(i)=='_'){
                flag=true;
            }
        }
        if(flag==false){
            spielfeld.get(numerofsmalgame).set(9,'!');
            return true;
        }
        boolean finished = false;
        for (int i = 0; i < 3; i++) {
            if (spielfeld.get(numerofsmalgame).get(i) == spielfeld.get(numerofsmalgame).get(i + 3)
                    && spielfeld.get(numerofsmalgame).get(i + 3) == spielfeld.get(numerofsmalgame).get(i + 6)) {
                finished = true;
                spielfeld.get(numerofsmalgame).set(9, spielfeld.get(numerofsmalgame).get(i));
            }
        }
        for (int i = 0; i < 3; i++) {
            if (spielfeld.get(numerofsmalgame).get(i * 3) == spielfeld.get(numerofsmalgame).get(i * 3 + 1)
                    && spielfeld.get(numerofsmalgame).get(i * 3 + 1) == spielfeld.get(numerofsmalgame).get(i * 3 + 2)) {
                finished = true;
                spielfeld.get(numerofsmalgame).set(9, spielfeld.get(numerofsmalgame).get(i * 3));
            }
        }
        if (spielfeld.get(numerofsmalgame).get(0) == spielfeld.get(numerofsmalgame).get(4)
                && spielfeld.get(numerofsmalgame).get(4) == spielfeld.get(numerofsmalgame).get(8)) {
            finished = true;
            spielfeld.get(numerofsmalgame).set(9, spielfeld.get(numerofsmalgame).get(4));

        }
        if (spielfeld.get(numerofsmalgame).get(2) == spielfeld.get(numerofsmalgame).get(4)
                && spielfeld.get(numerofsmalgame).get(4) == spielfeld.get(numerofsmalgame).get(6)) {
            finished = true;
            spielfeld.get(numerofsmalgame).set(9, spielfeld.get(numerofsmalgame).get(4));
        }
        return finished;
    }

    private int[] getUserInput(int feld) throws InterruptedException {
        int[] inputs = new int[2];
        synchronized (this){
            wait();
        }
        inputs=zahlen;
//        do {
            if (feld == -1) {
                System.out.println("Wähle dein großes Feld!");
            } else {
                System.out.println("DU MUSST AUF FELD " + (feld+1) + " Spielen");
                if(inputs[0]!=feld){
                    return new int[]{-1,-1};
                }
            }
            System.out.println("Wähle dein kleines Feld!");
//        }
//        while (!inputIsValid(inputs));
        if(!inputIsValid(inputs)){
            return new int[]{-1,-1};
        }
        return inputs;
    }

    private void setUserInput(int[] input) {
        spielfeld.get(input[0]).set(input[1], Character.valueOf(getCurrentSymbol()));
    }

    private boolean inputIsValid(int[] input) {
        if (input[0] < 0 || input[0] > 8 || input[1] < 0 || input[1] > 8) {
            return false;
        }
        if (getValue(input) != '_') {
            printfeld();
            System.out.println("Besetzt");
            return false;
        }
        if (getValue(new int[]{input[0], 9}) != '_') {
            printfeld();
            System.out.println("Fertig");
            return false;
        }
        return true;
    }

    private char getCurrentSymbol() {
        return player1Turn ? symbolP1 : symbolP2;
    }

    private char getValue(int[] input) {
        return spielfeld.get(input[0]).get(input[1]);
    }

}
