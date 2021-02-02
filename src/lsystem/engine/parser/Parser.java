package lsystem.engine.parser;

import java.util.List;

public class Parser {

    private final String axiom;
    private final List<String> rules;
    private final int nbIterations;
    private String[] validStrings = {"]","[",".","+","-","X","Y","Z","x","y","z","0","1","2","3","4","5","6","7","8","9"};

    public Parser(String axiom, List<String> rules,int nbIterations) {
        this.axiom = axiom;
        this.rules = rules;
        this.nbIterations = nbIterations;
    }

    public boolean isCorrect(){
    	return (rulesCorrect() || axiomCorrect());
    }
    private boolean axiomCorrect() {
		
		return false;
	}

	public boolean rulesCorrect() {
    	for(String r : this.rules) {
    		String[] stringsplitted = r.split("");
	        String old = null;
	        int bracket = 0;
	        for (String rules : stringsplitted){
	            for(String string : validStrings){
	            	if (rules == "[")
	            		bracket += 1;
	            	if(rules =="]")
	            		bracket -=1;
	                if(old == "."){
	                    for(int i = 0;i<10;i++){
	                        if(rules == validStrings[i])
	                            return false;
	                    }
	                }
	                old = rules;
	                if(rules == string)
	                    break;
	                if(string == "9" && rules != string)
	                    return false;
	            }
	        }
	    	if (bracket != 0)
	    		return false;
    	}
        return true;
    }
}
