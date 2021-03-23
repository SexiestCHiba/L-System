package lsystem.screen.gl3d;

import com.jogamp.opengl.glu.GLU;

import java.awt.*;
import java.awt.event.*;

public class GLMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {

    private final GLU glu;
    private final GLCanvas canvas;
    private int button = 0;
    private Point origine;
    private final float MULTIPLIER = 0.25f;

    public GLMouseListener(GLCanvas canvas) {
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
            double xDiff = origine.getX() - e.getPoint().getX();
            double yDiff = origine.getY() - e.getPoint().getY();
            /* if(button == 1) {
                canvas.camera[0] += Math.cos(canvas.camera[3]) * xDiff * 0.01;
                canvas.camera[1] += Math.cos(canvas.camera[4]) * yDiff * 0.01;
                canvas.camera[2] += Math.sin(canvas.camera[3]) * xDiff * 0.01;
            } */
            if(button == 3) {
                canvas.camera[3] += xDiff * 0.1;
                canvas.camera[4] += yDiff * 0.1;
            }
            origine = e.getPoint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        canvas.camera[2] += Math.cos(Math.toRadians(canvas.camera[4]))*MULTIPLIER* e.getWheelRotation()*Math.cos(Math.toRadians(Math.abs(360-canvas.camera[3])));
        canvas.camera[0] -= Math.cos(Math.toRadians(canvas.camera[4]))*MULTIPLIER* e.getWheelRotation()*Math.cos(Math.toRadians(Math.abs(450-canvas.camera[3])));
        canvas.camera[1] += (1-Math.cos(Math.toRadians(canvas.camera[4])))*MULTIPLIER* e.getWheelRotation()*Math.cos(Math.toRadians(Math.abs(360-canvas.camera[4])));

    }
}
