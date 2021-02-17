package lsystem.screen.listener;

import lsystem.screen.SwingGLCanvas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private final SwingGLCanvas canvas;

    public KeyboardListener(SwingGLCanvas swingGLCanvas) {
        this.canvas = swingGLCanvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("typed" + e.getKeyCode());
        switch (e.getKeyChar()) {
            case 'z':
                break;
            case 's':
                break;
            case 'q':
                break;
            case 'd':
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
