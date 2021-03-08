package lsystem.screen.gl3d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GLKeyboardListener implements KeyListener {

    private final GLCanvas canvas;


    public GLKeyboardListener(GLCanvas swingGLCanvas) {
        this.canvas = swingGLCanvas;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()) {
            case 'z':
                canvas.camera[2] -= 0.1f*Math.cos(Math.toRadians(canvas.camera[3]));
                canvas.camera[0] += 0.1f*Math.cos(Math.toRadians(90-canvas.camera[3]));
                break;
            case 's':
                canvas.camera[2] += 0.1f*Math.cos(Math.toRadians(canvas.camera[3]));
                canvas.camera[0] -= 0.1f*Math.cos(Math.toRadians(90-canvas.camera[3]));
                break;
            case 'q':
                canvas.camera[2] += 0.1f*Math.cos(Math.toRadians(canvas.camera[3]+90));
                canvas.camera[0] += 0.1f*Math.cos(Math.toRadians(180-canvas.camera[3]));
                break;
            case 'd':
                canvas.camera[2] -= 0.1f*Math.cos(Math.toRadians(canvas.camera[3]+90));
                canvas.camera[0] -= 0.1f*Math.cos(Math.toRadians(180-canvas.camera[3]));
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
