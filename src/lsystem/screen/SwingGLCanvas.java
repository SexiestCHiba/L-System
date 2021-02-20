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
import lsystem.utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Random;

public class SwingGLCanvas {

    public final GLCanvas glCanvas;
    public float[] camera = {0f, 1f, 5f, // camera pos x,y, z
            0f, 0f, 0f}; // camera rotation yaw(x-axis), pitch(y-axis), roll(z-axis)
    public GLU glu = new GLU();
    public GLUT glut = new GLUT();
    public LinkedList<Pair<Integer, Integer>> prismPosition = new LinkedList<>();

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

        for(int i = -50; i < 51; ++i) {
            for(int j = -50; j < 51; ++j) {
                if(new Random().nextFloat() < 0.05) {
                    prismPosition.add(new Pair<Integer, Integer>(i, j));
                }
            }
        }

        animator.start();
        jframe.setVisible(true);
    }
}
