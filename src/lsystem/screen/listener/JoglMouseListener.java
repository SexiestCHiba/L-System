package lsystem.screen.listener;

import com.jogamp.opengl.glu.GLU;
import lsystem.screen.Constants;
import lsystem.screen.SwingGLCanvas;

import java.awt.*;
import java.awt.event.*;

public class JoglMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

    private final GLU glu;
    private final SwingGLCanvas canvas;
    private int button = 0;
    private Point origine;

    public JoglMouseListener(SwingGLCanvas canvas) {
        this.canvas = canvas;
        this.glu = canvas.glu;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(button == 0) {
            button = e.getButton();
            origine = e.getPoint();
        } else {
            button = 0;
            origine = null;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exited " + canvas.camera[0] + ", " + canvas.camera[1]);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(origine != null) {
            double xDiff = (origine.getX() - e.getPoint().getX()) % 360;
            double yDiff = (origine.getY() - e.getPoint().getY()) % 360;
            if(button == 2) {
                canvas.camera[0] += Math.cos(canvas.camera[4] % 360 - Math.PI / 2) * xDiff * 0.01;
                canvas.camera[1] += Math.cos(canvas.camera[3] % 360) * yDiff * 0.01;
                canvas.camera[2] += Math.sin(canvas.camera[4] % 360) * xDiff * 0.01;
            }
            if(button == 3) {
                canvas.camera[3] += xDiff * 0.1;
                canvas.camera[4] += yDiff * 0.1;
            }
            if(button == 1) {
                // canvas.camera[0] += Math.cos(xDiff / Constants.WIDTH + Math.PI / 2) * 1.2;
                // canvas.camera[1] += Math.sin(-yDiff / Constants.WIDTH);
                // canvas.camera[2] += Math.sin(yDiff / Constants.HEIGHT) * 1.2;
            }
            origine = e.getPoint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        canvas.camera[0] += e.getWheelRotation() * 0.25 * canvas.camera[3];
        canvas.camera[1] += e.getWheelRotation() * 0.25 * canvas.camera[4];

        canvas.camera[2] += Math.sin(e.getWheelRotation() + Math.PI / 2) * 0.25;
    }
}
