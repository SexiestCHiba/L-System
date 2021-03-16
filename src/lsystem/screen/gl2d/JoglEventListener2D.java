package lsystem.screen.gl2d;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

import javafx.scene.control.Tab;
import lsystem.engine.Element;
import lsystem.engine.ElementProperties;
import lsystem.screen.gl3d.DrawHelper;
import lsystem.screen.gl3d.GLCanvas;
import lsystem.utils.Pair;

public class JoglEventListener2D implements GLEventListener {

    private final SwingGLCanvas2D canvas;

    private final GLU glu;
    private final GLUT glut;
    private int fps;

    public JoglEventListener2D(SwingGLCanvas2D swingGLCanvas) {
        this.canvas = swingGLCanvas;
        this.glu = canvas.glu;
        this.glut = canvas.glut;
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0f, 0f, 0f, 1.0f);

        new Thread(() -> {
            while (canvas.frame.isVisible()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(fps);
                fps = 0;
            }
        }).start();
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {
    }
    
    public void drawAll (GL2 gl, Element actual, lsystem.screen.gl2d.Point origin) {
    	if (actual.property == ElementProperties.DRAW) {
    		System.out.println("DESSIN");
    		lsystem.screen.gl2d.Point newOrigin = new lsystem.screen.gl2d.Point (origin, actual.getRotation2D());
    		DrawHelper.drawStick(gl, origin, newOrigin);
    	}
    	System.out.println(actual.children.isEmpty());
    	for (Element children : actual.children) {
    		System.out.println("CHILD");
    		drawAll(gl, children, new lsystem.screen.gl2d.Point (origin, actual.getRotation2D()));
    	}
    }

    @Override
    public void display (GLAutoDrawable glAutoDrawable) {
    	/* Element str = new Element(ElementProperties.DRAW, null, new int[]{0, 0, 0});
    	
    	Element child1 = new Element(ElementProperties.DRAW, str, new int[]{0, 0, 0});
    	Element child2 = new Element(ElementProperties.DRAW, str, new int[]{45, 0, 0});
    	str.children.add(child1);
    	str.children.add(child2);
    	Element child11 = new Element(ElementProperties.DRAW, child1, new int[]{225, 0, 0});
    	Element child12 = new Element(ElementProperties.DRAW, child1, new int[]{270, 0, 0});
    	child1.children.add(child11);
    	child1.children.add(child12);*/
    	
    	GL2 gl = glAutoDrawable.getGL().getGL2();
    		
    	drawAll (gl, canvas.getLSystem(), new lsystem.screen.gl2d.Point(-1.0f, -1.0f));

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {

    }
}
