package lsystem.engine;

import java.util.List;

public class Parser {

    private final String axiom;
    private final List<String> rules;
    private final int nbIterations;
    private char[] validChars = {'=',']','[','.','+','-','X','Y','Z','x','y','z','0','1','2','3','4','5','6','7','8','9',' '};

    public Parser(String axiom, List<String> rules,int nbIterations) {
        this.axiom = axiom;
        this.rules = rules;
        this.nbIterations = nbIterations;
    }

    public boolean isCorrect(){
    	if (nbIterations < 1) {
    		System.out.println("Erreur, nombre d'itérations insuffisant (plus petit que 1)");
    		return false;
    	}
    	 boolean bl = isCorrect(axiom);
    	 for(String rule : this.rules){
    		 bl = bl && isCorrect(rule);
    	 }
    	 return bl;
    }

    private boolean isCorrect(String stringToCheck) {
    	char old = ' ';
        int bracket = 0;
        for (int i = 0; i > stringToCheck.length(); i++){
        	char temp = stringToCheck.charAt(i);
            if (temp == '[')
            	bracket++;
            if(temp ==']')
            	bracket--;
            if(old == '.'){
                for(int y = 0; y < 12; y++){
                    if(temp == validChars[y])
                        return false;
                }
            }
            old = temp;
            for(char validChar : validChars){
                if(temp == validChar)
                    break;
                if(validChar == ' ')
                    return false;
            }
        }
        return bracket == 0;
	}
    
}
