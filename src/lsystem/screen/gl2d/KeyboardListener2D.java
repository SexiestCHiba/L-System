package lsystem.screen.gl2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener2D implements KeyListener {

    private final SwingGLCanvas2D canvas;


    public KeyboardListener2D(SwingGLCanvas2D swingGLCanvas) {
        this.canvas = swingGLCanvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()) {
            case 'z':
                canvas.camera[2] -= 0.1f;
                break;
            case 's':
                canvas.camera[2] += 0.1f;
                break;
            case 'q':
                canvas.camera[0] -= 0.1f;
                break;
            case 'd':
                canvas.camera[0] += 0.1f;
                break;
            case 'a':
                canvas.camera[3] -= 1;
                break;
            case 'e':
                canvas.camera[3] += 1;
                break;
            case 'w':
                canvas.camera[1] += 0.1f;
                break;
            case 'x':
                canvas.camera[1] -= 0.1f;
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
