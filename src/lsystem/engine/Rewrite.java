package lsystem.engine;

import lsystem.utils.Pair;

import java.util.List;

public class Rewrite {

    private final String axiom;
    private final List<Pair<String, String >> rules;
    private final int recurrences;

    public Rewrite(String axiom, List<Pair<String, String>> rules, int recurrences) {
        this.axiom = axiom;
        this.rules = rules;
        this.recurrences = recurrences;
    }


    private String replaceRulesByID(final String rewritten) {
        String toRewrite = rewritten;
        for(int j = 0; j < rules.size(); ++j){
            Pair<String, String> pair = rules.get(j);
            toRewrite = toRewrite.replace(pair.getLeft(), "${" + j + "}");
        }
        return toRewrite;
    }

    private String replaceIDByRuleApplication(final String toRewrite) {
        String rewritten = toRewrite;
        for(int j = 0; j < rules.size(); ++j){
            rewritten = rewritten.replace("${" + j + "}", rules.get(j).getRight());
        }
        return rewritten;
    }

    public String rewrite() {
        String rewritten = axiom;
        for(int i = 0; i < recurrences; ++i) {
            String toRewrite = replaceRulesByID(rewritten);
            rewritten = replaceIDByRuleApplication(toRewrite);
        }
        return rewritten.replace("[", "Y[");
    }

}
