package lsystem.screen.gl3d;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import lsystem.utils.Pair;

import java.util.LinkedList;
import java.util.Random;

public class GLEventListener implements com.jogamp.opengl.GLEventListener {

    private final GLCanvas canvas;
    private final float[] light_0_ambient = {0.01f, 0.01f, 0.01f, 0.01f};
    private final float[] light_0_diffuse = {1.0f, 1.0f, 1.0f, 1.0f};
    private final float[] light_0_specular = {1.0f,1.0f, 1.0f, 1.0f};
    private final float[] light_0_position = {1000f, 1000f, 1000f, 1f};

    private final float[] material_specular = {0.8f, 0.8f, 0.8f, 0.8f};

    private float angle = 0f;
    private final LinkedList<Pair<Integer, Integer>> prismPosition = new LinkedList<>();

    private final GLU glu;
    private final GLUT glut;
    private int fps;

    public GLEventListener(GLCanvas swingGLCanvas) {
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
        for(int i = -50; i < 51; ++i) {
            for(int j = -50; j < 51; ++j) {
                if(new Random().nextFloat() < 0.05) {
                    prismPosition.add(new Pair<>(i, j));
                }
            }
        }
        System.out.println(prismPosition.size() * 8);
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
    public void display(GLAutoDrawable glAutoDrawable) {
        for (int i = 0; i < canvas.camera.length; i++) {
            canvas.camera[i] = canvas.camera[i] % 360;
        }
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, 0);
        DrawHelper.placeCamera(gl, canvas);
        glu.gluLookAt(canvas.camera[0], canvas.camera[1], canvas.camera[2], canvas.camera[0], canvas.camera[1], canvas.camera[2] - 1, 0f, 1f, 0f);

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

        angle = (angle + 0.1f) % 360;

        gl.glColor3f(0f, 0.5f, 1f);
        gl.glPushMatrix();
        for(Pair<Integer, Integer> pair : prismPosition) {
            DrawHelper.drawRectangularPrism(gl, pair.getLeft(), 0f, pair.getRight(), pair.getLeft() + 1, 1f, pair.getRight() + 1);
        }
        gl.glPopMatrix();
        gl.glTranslatef(0f, 0f, 0f);

        DrawHelper.drawAxes(gl, glut);
        DrawHelper.drawDebugInformation(gl, glut, canvas);
        gl.glFlush();
        fps++;
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glViewport(x, y, width, height);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60.0f, (float) width / height, 0.1f, 1000.0f);

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_0_position, 0);
        gl.glColorMaterial(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT_AND_DIFFUSE);
        gl.glMateriali(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, 90);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, material_specular, 0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }
}
