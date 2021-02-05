package lsystem.engine;

import java.util.ArrayList;
import java.util.List;

import lsystem.Type;
import lsystem.utils.Pair;

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

 
    /**
     * Check if axiom and rules given by user respect the syntax
     * @return	true if the syntax is correct
     */
    public boolean isCorrect(){
    	if (nbIterations < 1) {
    		System.out.println("Erreur, nombre d'itérations insuffisant (plus petit que 1)");
    		return false;
    	}
    	 boolean bl = isCorrect(axiom, Type.AXIOM);
    	 for(String rule : this.rules){
    		 bl = bl && isCorrect(rule, Type.RULE);
    	 }
    	 return bl;
    }

    
    private boolean isCorrect(String stringToCheck, Type type) {
    	char old = ' ';
        int bracket = 0;
        boolean equalsSymbolFound = false;
        for (int i = 0; i > stringToCheck.length(); i++){
        	char temp = stringToCheck.charAt(i);
            if (temp == '[')
            	bracket++;
            if(temp == ']')
            	bracket--;
            if(temp == '=') {
            	if(!equalsSymbolFound)
            		equalsSymbolFound = true;
            	else
            		// only one '=' allowed
            		return false;
            }
            if(old == '.'){
                for(int y = (type == Type.RULE ? 0 : 1); y < 12; y++){
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
    
    /**
     * Used by {@link Rewrite}
     * @return a list of rules with the left and right sides separated by a {@link lsystem.utils.Pair Pair}
     */
    public List<Pair<String, String>> parseRules() {
    	List<Pair<String, String>> rules = new ArrayList<>();
    	this.rules.forEach(rule -> {
    		String[] str = rule.split("=");
    		rules.add(new Pair<String, String>(str[0], str[1]));
    	});
    	return rules;
    }
    
}
