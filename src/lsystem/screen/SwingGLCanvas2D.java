package lsystem.screen;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import lsystem.screen.listener.JoglEventListener;
import lsystem.screen.listener.JoglEventListener2D;
import lsystem.screen.listener.JoglMouseListener;
import lsystem.screen.listener.JoglMouseListener2D;
import lsystem.screen.listener.KeyboardListener;
import lsystem.screen.listener.KeyboardListener2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class SwingGLCanvas2D {

    public final GLCanvas glCanvas;
    public float[] camera = {0f, 1f, 5f, // camera pos x,y, z
            0f, 0f, 0f}; // camera rotation yaw(x-axis), pitch(y-axis), roll(z-axis)
    public GLU glu = new GLU();
    public GLUT glut = new GLUT();

    public SwingGLCanvas2D() {
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        this.glCanvas = new GLCanvas(glCapabilities);
        glCanvas.addGLEventListener(new JoglEventListener2D(this));
        JoglMouseListener2D mouse = new JoglMouseListener2D(this);
        glCanvas.addMouseListener(mouse);
        glCanvas.addMouseMotionListener(mouse);
        glCanvas.addMouseWheelListener(mouse);
        glCanvas.addKeyListener(new KeyboardListener2D(this));
        final JFrame jframe = new JFrame("L-System");
        final FPSAnimator animator = new FPSAnimator(glCanvas, 60);
        jframe.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                jframe.dispose();
            }
        });

        jframe.getContentPane().add(glCanvas, BorderLayout.CENTER);
        jframe.setSize(Constants.WIDTH, Constants.HEIGHT);

        animator.start();
        jframe.setVisible(true);
    }
}
