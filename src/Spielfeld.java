import java.util.ArrayList;
import java.util.Scanner;

public class Spielfeld {
    private ArrayList<ArrayList<Character>> spielfeld = new ArrayList<ArrayList<Character>>();

    char symbolP1 = 'X';
    char symbolP2 = 'O';
    boolean player1Turn = true;

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
        if (p1 == '_') {
            System.out.println("NICHT ERLAUBT");
            return;
        }
        if (p2 == '_') {
            System.out.println("NICHT ERLAUBT");
            return;
        }
        symbolP1 = p1;
        symbolP2 = p2;
    }

    void game() {
        int feld = -1;
        char erg;
        while (true) {
            int[] temp = getUserInput(feld);
            setUserInput(temp);
            smallgamefinished(temp[0]);
            if (spielfeld.get(temp[1]).get(9) == '_') {
                feld = temp[1];
            } else {
                feld = -1;
            }
            if((erg=biggamechecking())!='_'){
                System.out.println("WE HAVE A WINNER");
                System.out.println("player "+erg);
            }
            player1Turn = !player1Turn;
            printfeld();
            System.out.println("TURN: "+getCurrentSymbol());
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

    private int[] getUserInput(int feld) {
        Scanner scanner = new Scanner(System.in);
        int[] inputs = new int[2];
        do {
            if (feld == -1) {
                System.out.println("Wähle dein großes Feld!");
                inputs[0] = scanner.nextInt() - 1;
            } else {
                System.out.println("DU MUSST AUF FELD " + (feld+1) + " Spielen");
                inputs[0] = feld;
            }
            System.out.println("Wähle dein kleines Feld!");
            inputs[1] = scanner.nextInt() - 1;
        }
        while (!inputIsValid(inputs));
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
        System.out.println("return true");
        return true;
    }

    private char getCurrentSymbol() {
        return player1Turn ? symbolP1 : symbolP2;
    }

    private char getValue(int[] input) {
        return spielfeld.get(input[0]).get(input[1]);
    }

}
