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

        switch (e.getKeyChar()) {
            case 'z':
                if(Math.abs(canvas.camera[3]) < 90 || Math.abs(canvas.camera[3])>270 ){
                    canvas.camera[2] -= 0.1f;
                    canvas.camera[0] += 0.1f*Math.tan(Math.toRadians(canvas.camera[3]));
                }else{
                    canvas.camera[2] += 0.1f;
                    canvas.camera[0] -= 0.1f*Math.tan(Math.toRadians(canvas.camera[3]));
                }
                break;
            case 's':
                if(Math.abs(canvas.camera[3]) < 90 || Math.abs(canvas.camera[3])>270 ){
                    canvas.camera[2] += 0.1f;
                    canvas.camera[0] -= 0.1f*Math.tan(Math.toRadians(canvas.camera[3]));
                }else{
                    canvas.camera[2] -= 0.1f;
                    canvas.camera[0] += 0.1f*Math.tan(Math.toRadians(canvas.camera[3]));
                }
                break;
            case 'q':
                if(Math.abs(canvas.camera[3]) < 90 || Math.abs(canvas.camera[3])>270 ){
                    canvas.camera[0] -= 0.1f;
                    canvas.camera[2] -= 0.1f*Math.tan(Math.toRadians(canvas.camera[3]));
                }else{
                    canvas.camera[0] += 0.1f;
                    canvas.camera[2] -= 0.1f*Math.tan(Math.toRadians(canvas.camera[3]));
                }
                break;
            case 'd':
                if(Math.abs(canvas.camera[3]) < 180 || Math.abs(canvas.camera[3])>0 ){
                    canvas.camera[0] += 0.1f;
                    canvas.camera[2] += 0.1f*Math.tan(Math.toRadians(canvas.camera[3]));
                }else{
                    canvas.camera[0] -= 0.1f;
                    canvas.camera[2] += 0.1f*Math.tan(Math.toRadians(canvas.camera[3]));
                }
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
