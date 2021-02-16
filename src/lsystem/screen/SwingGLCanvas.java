package lsystem.screen;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingGLCanvas {

    public SwingGLCanvas() {
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        final GLCanvas glCanvas = new GLCanvas(glCapabilities);
        glCanvas.addGLEventListener(new JoglEventListener());
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
