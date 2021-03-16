package lsystem;

import java.util.ArrayList;

import lsystem.screen.gl2d.SwingGLCanvas2D;
import lsystem.screen.main.MainFrame;
import lsystem.utils.Pair;

public class Main2D {
	
	/*public static MainFrame mainFrame;
    public static SwingGLCanvas2D joglFrame;
    public static void main(String[] args) {
        new Thread(() -> {
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }).start();
        new Thread(() -> joglFrame = new SwingGLCanvas2D()).start();
    } */

    public static void main(String[] args) {

        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        SwingGLCanvas2D canvas = new SwingGLCanvas2D();
        canvas.setVisible(true);
        new Thread(() ->{
    		SwingGLCanvas2D joglFrame = new SwingGLCanvas2D();
            String axiom="X";
            ArrayList<Pair<String, String>> rules = new ArrayList<>();
            rules.add(new Pair<>("X=Y+[[X]-X]-Y[-YX]+X", "Y=YY"));
            joglFrame.setLSystem(axiom,rules,5);
            joglFrame.setVisible(true);
            }).start();
    }

}
