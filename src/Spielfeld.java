import java.util.ArrayList;

public class Spielfeld {

    private ArrayList<ArrayList<Character>> spielfeld = new ArrayList<ArrayList<Character>>();
    Spielfeld(){
        for (int i = 0; i < 9; i++) {
            spielfeld.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                spielfeld.get(i).add('_');
            }
        }
        for(ArrayList<Character> l : spielfeld){
            l = new ArrayList<>();
            l.add(' ');
        }
    }

    void printfeld(){
        StringBuilder line= new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                line.append(" "+spielfeld.get(i * 3 + j).get(0)+" ");
                line.append(" "+spielfeld.get(i * 3 + j).get(1)+" ");
                if(j!=2) {
                    line.append(" " + spielfeld.get(i * 3 + j).get(2) + " ").append("|");
                }else{
                    line.append(" " + spielfeld.get(i * 3 + j).get(2) + " ");

                }
            }
            line.append("\n");
            for (int j = 0; j < 3; j++) {
                line.append(" "+spielfeld.get(i * 3 + j).get(3)+" ");
                line.append(" "+spielfeld.get(i * 3 + j).get(4)+" ");
                if(j!=2) {
                    line.append(" " + spielfeld.get(i * 3 + j).get(5) + " ").append("|");
                }else{
                    line.append(" " + spielfeld.get(i * 3 + j).get(5) + " ");

                }
            }
            line.append("\n");
            for (int j = 0; j < 3; j++) {
                line.append(" "+spielfeld.get(i * 3 + j).get(6)+" ");
                line.append(" "+spielfeld.get(i * 3 + j).get(7)+" ");
                if(j!=2){
                    line.append(" "+spielfeld.get(i * 3 + j).get(8)+" ").append("|");
                }else{
                    line.append(" "+spielfeld.get(i * 3 + j).get(8)+" ");
                }

            }
            if(i!=2)
            line.append("\n").append("-----------------------------\n");
        }
        System.out.println(line);
    }

}
