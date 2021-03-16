package lsystem.screen.gl2d;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import lsystem.screen.gl3d.DrawHelper;

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

    @Override
    public void display (GLAutoDrawable glAutoDrawable) {
    	GL2 gl = glAutoDrawable.getGL().getGL2();
    	
    	float xDefault = -1.0f, yDefault = -1.0f;

    	DrawHelper.drawStick(gl, 0.1f, xDefault, yDefault, 0);
    	DrawHelper.drawStick(gl, 0.2f, 1.1f, 1.1f, 90);
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {

    }
}
