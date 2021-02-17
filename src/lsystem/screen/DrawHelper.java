package lsystem.screen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;

public class DrawHelper {

    public static void placeCamera(GL2 gl, SwingGLCanvas canvas) {
        gl.glRotatef(canvas.camera[4], 1f, 0f, 0f);
        gl.glRotatef(canvas.camera[3], 0f, 1f , 0f);
        gl.glRotatef(canvas.camera[5], 0f, 0f, 1f);
    }

    public static void drawAxes(GL2 gl, GLUT glut){
        gl.glDisable(GL2.GL_LIGHTING);
        gl.glDisable(GL2.GL_LIGHT0);
        gl.glRasterPos3f(0f, 0f, 0f);
        gl.glColor3f(1f, 0f, 0f);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(0f, 0f, 0f);
        gl.glVertex3f(1f, 0f, 0f);

        gl.glVertex3f(0f, 0f, 0f);
        gl.glVertex3f(0f, 1f, 0f);

        gl.glVertex3f(0f, 0f, 0f);
        gl.glVertex3f(0f, 0f, 1f);

        gl.glColor3f(1f, 1f, 1f);
        for(int i = -5; i < 6; i++) {
            gl.glVertex3f(-5f, 0f, i);
            gl.glVertex3f(5f, 0f, i);

            gl.glVertex3f(i, 0, -5f);
            gl.glVertex3f(i, 0, 5f);
        }
        gl.glEnd();
        gl.glRasterPos3f(1.1f, 0.0f, 0.0f);
        glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, 'X');
        gl.glRasterPos3f(0.0f, 1.1f, 0.0f);
        glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, 'Y'); //draw the y axis
        gl.glRasterPos3f(0.0f, 0.0f, 1.1f);
        glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, 'Z'); //draw the z axis
    }

    public static void drawDebugInformation(GL2 gl, GLU glu, GLUT glut, SwingGLCanvas canvas, int window_height, int window_width) {
        gl.glRasterPos3f(0f, 0f, 2f);
        gl.glColor3f(1f,1f, 1f);
        glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18,
                "x=" + canvas.camera[0] + ", y=" + canvas.camera[1] + ", z=" + canvas.camera[2]);
        gl.glDepthMask(true);
    }

    public static void prepareDraw2D(GL2 gl, GLUT glut, SwingGLCanvas canvas) {
        gl.glDisable(GL2.GL_LIGHTING);
        gl.glDisable(GL2.GL_LIGHT0);
    }

    public static void prepareDraw3D(GL2 gl, GLUT glut, SwingGLCanvas canvas) {
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    private static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }
}
