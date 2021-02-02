package lsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Axiom: ");
        String axiom = scanner.next();
        System.out.println("Règles (laissez vide quand vous avez fini): ");
        List<String> rules = new ArrayList<>();
        while(rules.isEmpty() || !rules.get(rules.size() - 1).equals("")) {
            rules.add(scanner.next());
        }
    }

}
