package lsystem.engine;

import lsystem.utils.Pair;

import java.util.HashMap;
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

    public String rewrite() {
        String rewritted = axiom;
        for(int i = 0; i < recurrences; ++i) {
            for(int j = 0; j < rules.size(); ++j){
                Pair<String, String> pair = rules.get(j);
                rewritted = rewritted.replace(pair.getLeft(), pair.getRight());
            }
        }
        return rewritted;
    }

}
