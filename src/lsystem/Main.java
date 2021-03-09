package lsystem;

import lsystem.screen.gl3d.GLCanvas;
import lsystem.screen.main.MainFrame;

public class Main {

    public static MainFrame mainFrame;
    public static GLCanvas joglFrame;

    public static void main(String[] args) {
        new Thread(() -> {
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }).start();
        new Thread(() -> joglFrame = new GLCanvas()).start();
    }

}
