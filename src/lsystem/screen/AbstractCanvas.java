package lsystem.screen;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import lsystem.engine.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class AbstractCanvas {

    private final Element lsystem;
    public JFrame frame;
    protected FPSAnimator animator;
    public final GLCanvas glCanvas;
    public GLU glu = new GLU();
    public GLUT glut = new GLUT();
    public float[] camera = {0f, 1f, 5f, // camera pos x,y, z
            0f, 0f, 0f}; // camera rotation yaw(x-axis), pitch(y-axis), roll(z-axis)


    protected AbstractCanvas(Element parsed) {
        this.lsystem = parsed;
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        this.glCanvas = new GLCanvas(glCapabilities);
        frame = new JFrame("L-System");
        animator = new FPSAnimator(glCanvas, 60);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        frame.getContentPane().add(glCanvas, BorderLayout.CENTER);
        frame.setSize(Constants.WIDTH, Constants.HEIGHT);
        addEventsListeners();
    }

    protected abstract void addEventsListeners();

    public void setVisible(boolean bl) {
        if(bl)
            animator.start();
        else
            animator.stop();
        frame.setVisible(bl);
    }
}
