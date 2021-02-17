package lsystem;

import lsystem.engine.Parser;
import lsystem.engine.Rewrite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lsystem.screen.MainFrame;
import lsystem.screen.SwingGLCanvas;

public class Main {

    public static void main(String[] args) {

        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        /*
        SwingGLCanvas canvas = new SwingGLCanvas();

        final Scanner scanner = new Scanner(System.in);
        String axiom = null;
        Parser parser = null;
        final List<String> rules = new ArrayList<>();
        int nbIterations = 0;
        while(parser == null || !parser.isCorrect()) {
            if(parser != null)
                System.out.println("Vos règles ou votre axiome ne sont pas correctement écrites, veuillez recommencer");
            System.out.println("Axiome: ");
            axiom = scanner.next();
            System.out.println("Règles: (\"finish\" quand vous avez fini): ");
            while(rules.isEmpty() || !rules.get(rules.size() - 1).equals("finish")) {
                rules.add(scanner.next());
            }
            rules.remove(rules.size() - 1);
            System.out.println("Nombre d'itérations: ");
            nbIterations = scanner.nextInt();
            parser = new Parser(axiom, rules,nbIterations);
        }
        System.out.println("Réécriture, veuillez patientez...");
        Rewrite rewriter = new Rewrite(axiom, parser.parseRules(), nbIterations);
        final String word = rewriter.rewrite();
        System.out.println(word);
        scanner.close();*/
        
    }

}
