package lsystem.engine;

import lsystem.utils.Pair;

import java.util.List;

/**
 * Used to rewrite axiom depending of given rules
 */
public class Rewrite {

    /**
     * replace occurrences of left side of {@code rules} to ${#ruleId} in {@code rewritten}
     * @see Pair
     * @param rewritten initial word
     * @param rules list of rules to rewrite {@code rewritten}
     * @return modified rewritten word
     */
    private static String replaceRulesByID(final String rewritten, List<Pair<String, String>> rules) {
        String toRewrite = rewritten;
        for(int j = 0; j < rules.size(); ++j){
            Pair<String, String> pair = rules.get(j);
            toRewrite = toRewrite.replace(pair.getLeft(), "${" + j + "}");
        }
        return toRewrite;
    }

    /**
     * replace every occurrences of ${#ruleId} to the right side of {@code rules} in {@code toRewrite}
     * @see Pair
     * @param toRewrite initial word
     * @param rules list of rules to rewrite {@code toRewrite}
     * @return final rewritten word
     */
    private static String replaceIDByRuleApplication(final String toRewrite, List<Pair<String, String>> rules) {
        String rewritten = toRewrite;
        for(int j = 0; j < rules.size(); ++j){
            rewritten = rewritten.replace("${" + j + "}", rules.get(j).getRight());
        }
        return rewritten;
    }

    /**
     * rewrite {@code axiom} iteratively
     * @param axiom initial word
     * @param rules list of rules to rewrite {@code axiom}
     * @param recurrences number a time we will execute the loop
     * @return rewritten word
     */
    public static String rewrite(String axiom, List<Pair<String, String>> rules, int recurrences) {
        String rewritten = axiom;
        for(int i = 0; i < recurrences; ++i) {
            String toRewrite = replaceRulesByID(rewritten, rules);
            rewritten = replaceIDByRuleApplication(toRewrite, rules);
            System.out.println(i + " / " + recurrences + " : " + rewritten.length());
        }
        rewritten = rewritten.replace("[", "Y[");
        System.out.println(rewritten);
        return rewritten;
    }

}
