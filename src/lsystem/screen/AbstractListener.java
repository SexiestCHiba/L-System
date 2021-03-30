package lsystem.screen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import lsystem.engine.Element;

public abstract class AbstractListener implements GLEventListener {

    protected final AbstractCanvas canvas;
    protected final GLU glu;
    protected final GLUT glut;
    protected int fps;

    public AbstractListener(AbstractCanvas swingGLCanvas) {
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
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glViewport(x, y, width, height);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60.0f, (float) width / height, 0.1f, 1000.0f);
    }

    public abstract void drawLSystem(GL2 gl, Element element);
}
