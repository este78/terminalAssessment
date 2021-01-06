import java.util.Random;

public class FCWGame {
    //var declaration
    //The Computer Words (no peeking!)
    String[] words ={"algorithm", "application", "backup", "bit", "buffer", "bandwidth", "broadband", "bug", "binary", "browser", "bus", "cache", "command", "computer", "cookie", "compiler", "cyberspace", "compress", "configure", "database", "digital", "data", "debug", "desktop", "disk", "domain", "decompress", "development", "download", "dynamic", "email", "encryption", "firewall", "flowchart", "file", "folder", "graphics", "hyperlink", "host", "hardware", "icon", "inbox", "internet", "kernel", "keyword", "keyboard", "laptop", "login", "logic", "malware", "motherboard", "mouse", "mainframe", "memory", "monitor", "multimedia", "network", "node", "offline", "online", "path", "process", "protocol", "password", "phishing", "platform", "program", "portal", "privacy", "programmer", "queue", "resolution", "root", "restore", "router", "reboot", "runtime", "screen", "security", "shell", "snapshot", "spam", "screenshot", "server", "script", "software", "spreadsheet", "storage", "syntax", "table", "template", "thread", "terminal", "username", "virtual", "virus", "web", "website", "window", "wireless"};

    char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    char[] vowels = {'a','e','i','o','u'};
    char[] validLetters;
    String[] roundWords;
    String[] guessedWords;
    FCWPlayer p1;
    FCWPlayer p2;
    Random random;
    //constructor
    public FCWGame(FCWPlayer p1,FCWPlayer p2){
        this.p1 = p1;
        this.p2 = p2;
        validLetters = new char[12];
        guessedWords = new String[20];
        random = new Random();
    }
    //setters
    //set the playing letters
    public void setValidLetters(){
        int index;
        //use random to select letters from the alphabet array
        //assing the selected letter to the valid letters for the round
        for(int i=0;i<2;i++){
			index= random.nextInt(4);
            validLetters[i]= vowels[index];
		}
        for(int i=2;i<validLetters.length;i++){
            index= random.nextInt(25);
            validLetters[i]= alphabet[index];
        }
    }
    //Fill an array with all the player's guesses
    public void setGuessedWords(FCWPlayer p){
        String playerGuess = p.getCurrentGuess();
        int counter =0;
        //find the first empty space in the array
        while(guessedWords[counter]!=null){
            counter++;
        }
        //assign the player guess to the empty space
        if(counter<guessedWords.length){
            guessedWords[counter]=playerGuess;
        }
        //if the counter goes out of bounds create a bigger array with the same contents as the old array plus the last word guessed
        else{
            String[] temp = new String[guessedWords.length+10];
            for(int i=0; i<guessedWords.length ;i++){
                temp[i]=guessedWords[i];
            }
            temp[guessedWords.length]=playerGuess;
            guessedWords = temp;
        }
    }
    //computing
    //validation of player input
    public boolean isInputValid(FCWPlayer p){
        //control array based on player input
        String input = p.getCurrentGuess();
        char[] controlArray = new char [input.length()];
        for(int i=0;i<input.length();i++){
            controlArray[i]= input.charAt(i);
        }

        //control array based on the letters selected by the game
        char[] tempLetters = validLetters.clone();
        //boolean to be returned
        boolean isValid = true;

        //accept null answers
        if(input.equals("")){
            return isValid;
        }

        //do not accept already guessed words
        for(int i=0;i<guessedWords.length;i++){
            if(input.equals(guessedWords[i])){
                return isValid = false;
            }
        }

        //compare both control array with each other, delete any matched chars
        for(int i=0;i<controlArray.length;i++){
            for(int j=0;j<tempLetters.length;j++){
                if(controlArray[i]==tempLetters[j]){
                    controlArray[i]=0;
                    tempLetters[j]=0;
                }
            }
        }
        //cycle through the player input control array, if not empty invalidate input
        for(int i=0;i<controlArray.length;i++){
            if(controlArray[i]!=0){
				return isValid = false;
            }
        }
        return isValid;
    }
    //scoring words
    public void scoreWords(FCWPlayer p){
        //get the player's current guess
        String input = p.getCurrentGuess();
        //check if word is a match
        boolean matchedWord =false;
        //cycle through the answer array looking for a match
        for(int i=0;i<words.length;i++){
            if(input.equals(words[i])){
                //count the vowels
                int numVowels =0;
                for(int j=0; j<input.length();j++){
                    if(input.charAt(j)=='a'||input.charAt(j)=='e'||input.charAt(j)=='i'||input.charAt(j)=='o'||input.charAt(j)=='u'){
                        numVowels =+ 1;
                    }
                }
                //convert vowels into points, assing points to player
                int points = numVowels*2;
                p.setRoundScore(points);
                p.setTotalScore(points);
                matchedWord=true;
                break;
            }
        }
        //this if statement will avoid reseting the points of a player with a matched input
        if(matchedWord==false){
            p.setRoundScore(0);
            p.setTotalScore(0);
        }
    }
    //getters
    public char[] getValidLetters(){
        return validLetters;
    }

}
