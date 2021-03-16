package lsystem.engine;

import lsystem.utils.Pair;

import java.util.List;

public class Rewrite {


    private static String replaceRulesByID(final String rewritten, List<Pair<String, String>> rules) {
        String toRewrite = rewritten;
        for(int j = 0; j < rules.size(); ++j){
            Pair<String, String> pair = rules.get(j);
            toRewrite = toRewrite.replace(pair.getLeft(), "${" + j + "}");
        }
        return toRewrite;
    }

    private static String replaceIDByRuleApplication(final String toRewrite, List<Pair<String, String>> rules) {
        String rewritten = toRewrite;
        for(int j = 0; j < rules.size(); ++j){
            rewritten = rewritten.replace("${" + j + "}", rules.get(j).getRight());
        }
        return rewritten;
    }

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
