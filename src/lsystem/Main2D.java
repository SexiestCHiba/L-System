package lsystem;

import lsystem.screen.gl2d.SwingGLCanvas2D;
import lsystem.screen.main.MainFrame;

public class Main2D {

    public static void main(String[] args) {

        MainFrame frame = new MainFrame();
        frame.setVisible(true);
        SwingGLCanvas2D canvas = new SwingGLCanvas2D();
        canvas.setVisible(true);
        
    }

}
