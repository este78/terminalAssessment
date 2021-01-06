import javax.swing.JOptionPane;

public class FindComputerWordsApp {
    public static void main(String[] args) {
        //vars
        //objects
        FCWPlayer p1 = new FCWPlayer("Player1");
        FCWPlayer p2 = new FCWPlayer("Player2");
        FCWGame game = new FCWGame(p1,p2);
        int gameOver= 0;
        //compute
        do{
            game.setValidLetters();
            for(int index=0;index<game.getValidLetters().length ;index++){
                System.out.print(game.getValidLetters()[index]+" ");
            }
            do{
                p1.setCurrentGuess();
				System.out.println(p1.getCurrentGuess());
            }while(!game.isInputValid(p1));
           game.setGuessedWords(p1);
           game.scoreWords(p1);
           System.out.println("Player1 Round Score: " + p1.getRoundScore());
           System.out.println("Player1 Total Score: " +p1.getTotalScore());
			do{
	                p2.setCurrentGuess();
					System.out.println(p2.getCurrentGuess());
            }while(!game.isInputValid(p2));
            game.setGuessedWords(p2);
            game.scoreWords(p2);
            System.out.println("Player2 Round Score: " + p2.getRoundScore());
            System.out.println("Player2 Total Score: " +p2.getTotalScore());
            gameOver= JOptionPane.showConfirmDialog(null, "Play Again?");
        }while(gameOver==0);


        //output
    }
}
