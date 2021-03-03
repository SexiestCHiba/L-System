package lsystem.screen;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import lsystem.screen.listener.JoglEventListener;
import lsystem.screen.listener.JoglMouseListener;
import lsystem.screen.listener.KeyboardListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class SwingGLCanvas {

    public JFrame frame;
    FPSAnimator animator;
    public final GLCanvas glCanvas;
    public float[] camera = {0f, 1f, 5f, // camera pos x,y, z
            0f, 0f, 0f}; // camera rotation yaw(x-axis), pitch(y-axis), roll(z-axis)
    public GLU glu = new GLU();
    public GLUT glut = new GLUT();

    public SwingGLCanvas() {
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        this.glCanvas = new GLCanvas(glCapabilities);
        glCanvas.addGLEventListener(new JoglEventListener(this));
        JoglMouseListener mouse = new JoglMouseListener(this);
        glCanvas.addMouseListener(mouse);
        glCanvas.addMouseMotionListener(mouse);
        glCanvas.addMouseWheelListener(mouse);
        glCanvas.addKeyListener(new KeyboardListener(this));
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
    }

    public void setVisible(boolean bl) {
        if(bl)
            animator.start();
        else
            animator.stop();
        frame.setVisible(bl);
    }
}
