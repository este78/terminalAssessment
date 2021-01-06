import javax.swing.JOptionPane;

public class FCWPlayer {
    //var declaration
    private String currentGuess;
    private int roundScore;
    private int totalScore;
    private String name;
    //constructor
    public FCWPlayer(String name){
		this.name=name;
        currentGuess="";
        roundScore = 0;
        totalScore = 0;
    }
    //setters
    public void setCurrentGuess(){
        currentGuess =  (JOptionPane.showInputDialog(null, name +", Enter your guess.")).toLowerCase();
    }
    public void setRoundScore(int score){
        roundScore = score;
    }
    public void setTotalScore(int score){
        totalScore = totalScore + score;
    }
    //compute
    //getters
    public int getRoundScore(){
        return roundScore;
    }
    public int getTotalScore(){
        return totalScore;
    }
    public String getCurrentGuess(){
        return currentGuess;
    }

}
