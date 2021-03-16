package lsystem.engine;

import lsystem.Type;
import lsystem.utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private final String axiom;
    private final List<String> rules;
    private final int nbIterations;
    private final char[] validChars = {'=',']','[','.','+','-','X','Y','x','y','z','0','1','2','3','4','5','6','7','8','9',' '};

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
        if(type == Type.RULE && !stringToCheck.contains("="))
            return false;
    	char old = ' ';
        int bracket = 0;
        boolean equalsSymbolFound = false;
        for (int i = 0; i < stringToCheck.length(); i++) {
        	char temp = stringToCheck.charAt(i);
            if (temp == '[')
                bracket++;
            if(temp == ']') {
                bracket--;
                if(stringToCheck.charAt(i - 1) == '[')
                    return false;
            }

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
     * Used by {@link Rewrite#rewrite(String, List, int)} )}
     * @return a list of rules with the left and right sides separated by a {@link lsystem.utils.Pair Pair}
     */
    public List<Pair<String, String>> parseRules() {
    	List<Pair<String, String>> rules = new ArrayList<>();
    	this.rules.forEach(rule -> {
    		String[] str = rule.split("=");
    		rules.add(new Pair<>(str[0], str[1]));
    	});
    	return rules;
    }

    // TODO: 03/03/2021 to finish
    public static Element parse(String word) throws NumberFormatException {
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', '+', '-'};
        Element root = null;
        Element workingElement = null;
        String number = "";
        List<Element> bracket = new ArrayList<>();
        float[] appliedRotation = new float[3];
        Element lastCreatedElement = null;

        for(int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            ElementProperties property = Arrays.stream(ElementProperties.values()).filter(p -> p.getChar() == c).findFirst().orElse(null);
            if(property != null) {
                if(property.getDirection() == -1) {
                    if(!number.isEmpty()) {
                        float n = getFloat(number);
                        number = "";
                        appliedRotation[0] = n;
                    }
                    if(workingElement == null) {
                        workingElement = new Element(property, null);
                        lastCreatedElement = workingElement;
                        root = workingElement;
                    } else {
                        Element element = new Element(property, workingElement, appliedRotation);
                        lastCreatedElement = element;
                        appliedRotation = new float[]{0f, 0f, 0f};
                        workingElement.children.add(element);
                        workingElement = lastCreatedElement;
                    }
                } else {
                    float n = getFloat(number);
                    number = "";
                    appliedRotation[property.getDirection()] = n;
                }
            } else {
                if(c == '[') {
                    workingElement = lastCreatedElement;
                    bracket.add(lastCreatedElement);
                } else if(c == ']') {
                    assert workingElement != null && !bracket.isEmpty();
                    workingElement = bracket.remove(bracket.size() - 1);
                } else {
                    for(char n : numbers) {
                        if(c == n) {
                            number += c;
                            break;
                        }
                    }
                }
            }
        }
        return root;
    }

    private static float getFloat(String number) throws NumberFormatException {
        float n;
        if(number.equals("") || number.equals("+"))
            n = 0.25f;
        else if(number.equals("-")) {
            n = -0.25f;
        }else{
            System.out.println(number);
            n = Float.parseFloat(number);
        }
        return n;
    }

    
}
