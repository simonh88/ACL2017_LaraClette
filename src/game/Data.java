package game;


import java.io.*;
import java.util.ArrayList;

public class Data {

    private final String pathToScore;
    //Les topScore premiers scores sont save
    private final int topScore;

    public Data(){
        pathToScore = "scores/Scores.txt";
        topScore = 10;
    }
    /**
     *
     * @param chrono
     * @return la place du score
     */
    public int setScore(long chrono){
        //System.out.println("Chrono : "+ chrono);
        String thisLine;
        ArrayList<Integer> scores = new ArrayList<>(11);
        boolean add = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathToScore));
            while ((thisLine = br.readLine()) != null) { // while loop begins here
                System.out.println("Ligne : "+thisLine);
                if(!thisLine.equals("")) {

                    if ((Integer.parseInt(thisLine) > chrono) && !add) {
                        System.out.println("Add score :" +chrono);
                        scores.add((int) chrono);
                        add = true;
                    }

                    scores.add(Integer.parseInt(thisLine));
                }
            } // end while
            if((scores.size() == 0) || (scores.size()<topScore) && !add ) scores.add((int)chrono);
            br.close();
            saveScore(scores);

        } // end try
        catch (IOException e) {
            System.err.println("Error: " + e);
        }
        return 0;
    }

    private void saveScore(ArrayList<Integer> scores){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(pathToScore)));
            for(int i = 0; i<scores.size() && i<topScore; i++){
                System.out.println("Pos i :"+ i + " score : "+scores.get(i));
                bw.write(""+scores.get(i));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadScore(){

    }


    public void reiniScores(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(pathToScore)));
            bw.write("");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
