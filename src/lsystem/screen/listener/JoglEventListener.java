package lsystem.screen.listener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import lsystem.screen.DrawHelper;
import lsystem.screen.SwingGLCanvas;

public class JoglEventListener implements GLEventListener {


    private final SwingGLCanvas canvas;
    private final float[] light_0_ambient = {0.01f, 0.01f, 0.01f, 0.01f};
    private final float[] light_0_diffuse = {1.0f, 1.0f, 1.0f, 1.0f};
    private final float[] light_0_specular = {1.0f,1.0f, 1.0f, 1.0f};
    private final float[] light_0_position = {100f, 0f, 10f, 1f};

    private float[] material_specular = {0.8f, 0.8f, 0.8f, 0.8f};

    private float angle = 0f;

    private final GLU glu;
    private final GLUT glut;
    private int width;
    private int height;

    public JoglEventListener(SwingGLCanvas swingGLCanvas) {
        this.canvas = swingGLCanvas;
        this.glu = canvas.glu;
        this.glut = canvas.glut;
    }


    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0f, 0f, 0f, 1.0f);

        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_NORMALIZE);
        gl.glEnable(GL2.GL_COLOR_MATERIAL);

        gl.glClearDepth(1.0f);
        gl.glShadeModel(GL2.GL_SMOOTH);

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, light_0_ambient, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light_0_diffuse, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, light_0_specular, 0);

        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, 0);
        DrawHelper.placeCamera(gl, canvas);
        // x, y, z, x of where the camera looks at, y of where the camera looks at, z of where the camera looks at
        glu.gluLookAt(canvas.camera[0], canvas.camera[1], canvas.camera[2], canvas.camera[0], canvas.camera[1], canvas.camera[2] - 1, 0f, 1f, 0f);
        DrawHelper.prepareDraw3D(gl, glut, canvas);

        gl.glPushMatrix();
        gl.glTranslatef(0f, 0f, -4f);
        gl.glRotatef(angle, 0f, 1f, 0f);
        gl.glColor3f(1.0f, 0.0f, 1.0f);
        glut.glutSolidSphere(2f, 20, 20);
        gl.glPopMatrix();

        gl.glPushMatrix();
        gl.glTranslatef(2f, 0.75f, 1.25f);
        gl.glRotatef(angle, 0f, 1f, 0f);
        gl.glColor3f(0.5f, 0.0f, 1.0f);
        glut.glutSolidSphere(0.75f, 20, 20);
        gl.glPopMatrix();

        angle += 0.1f;
        angle %= 360f;
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_0_position, 0);


        gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
        gl.glMateriali(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 90);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, material_specular, 0);
        DrawHelper.drawAxes(gl, glut);
        DrawHelper.prepareDraw2D(gl, glut, canvas);
        DrawHelper.drawDebugInformation(gl, glu, glut, canvas, glAutoDrawable.getSurfaceHeight(), glAutoDrawable.getSurfaceWidth());
        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glViewport(x, y, width, height);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60.0f, (float) width/height, 0.1f, 1000.0f);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }
}
