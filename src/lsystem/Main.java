package lsystem;

import lsystem.engine.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = null;
        int nbIterations = 0;
        while(parser == null || parser.isCorrect()) {
            if(parser != null)
                System.out.println("Vos règles ou votre axiome ne sont pas correctement écrites, veuillez recommencer");
            System.out.println("Axiome: ");
            String axiom = scanner.next();
            System.out.println("Règles: (\"finish\" quand vous avez fini): ");
            List<String> rules = new ArrayList<>();
            while(rules.isEmpty() || !rules.get(rules.size() - 1).equals("finish")) {
                rules.add(scanner.next());
            }
            rules.remove(rules.size() - 1);
            parser = new Parser(axiom, rules,nbIterations);
        }

        scanner.close();
    }

}
