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


    private String replaceRulesByID(final String rewritted) {
        String toRewrite = rewritted;
        for(int j = 0; j < rules.size(); ++j){
            Pair<String, String> pair = rules.get(j);
            toRewrite = toRewrite.replace(pair.getLeft(), "${" + j + "}");
        }
        return toRewrite;
    }

    private String replaceIDByRuleApplication(final String toRewrite) {
        String rewritted = toRewrite;
        for(int j = 0; j < rules.size(); ++j){
            rewritted = rewritted.replace("${" + j + "}", rules.get(j).getRight());
        }
        return rewritted;
    }

    public String rewrite() {
        String rewritted = axiom;
        for(int i = 0; i < recurrences; ++i) {
            String toRewrite = replaceRulesByID(rewritted);
            rewritted = replaceIDByRuleApplication(toRewrite);
        }
        return rewritted;
    }

}
