package lsystem.screen.gl3d;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;
import lsystem.engine.Element;
import lsystem.engine.ElementProperties;

/**
 * static function use when to draw some figures
 */
public class DrawHelper {

	public static void placeCamera(GL2 gl, AbstractCanvas canvas) {
		gl.glRotatef(canvas.camera[4], 1f, 0f, 0f);
		gl.glRotatef(canvas.camera[3], 0f, 1f, 0f);
		gl.glRotatef(canvas.camera[5], 0f, 0f, 1f);
	}

	public static void drawAxes(GL2 gl, GLUT glut) {
		gl.glRasterPos3f(0f, 0f, 0f);
		gl.glColor3f(255f, 0f, 0f);
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex3f(0f, 0f, 0f);
		gl.glVertex3f(1f, 0f, 0f);

		gl.glVertex3f(0f, 0f, 0f);
		gl.glVertex3f(0f, 1f, 0f);

		gl.glVertex3f(0f, 0f, 0f);
		gl.glVertex3f(0f, 0f, 1f);

		gl.glColor3f(255f, 255f, 255f);
		int limit = 20;
		for (int i = -limit; i < limit + 1; i++) {
			gl.glVertex3f(-limit, 0f, i);
			gl.glVertex3f(limit, 0f, i);

			gl.glVertex3f(i, 0, -limit);
			gl.glVertex3f(i, 0, limit);
		}
		gl.glEnd();
		gl.glRasterPos3f(1.1f, 0.0f, 0.0f);
		glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, 'X');
		gl.glRasterPos3f(0.0f, 1.1f, 0.0f);
		glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, 'Y'); // draw the y axis
		gl.glRasterPos3f(0.0f, 0.0f, 1.1f);
		glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, 'Z'); // draw the z axis
	}


	@SuppressWarnings("unused")
	public static void drawDebugInformation(GL2 gl, GLUT glut, AbstractCanvas canvas) {
		gl.glRasterPos3f(canvas.camera[0], canvas.camera[1], canvas.camera[2] - 1);
		gl.glColor3f(1f, 1f, 1f);
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18,
				"x=" + canvas.camera[0] + ", y=" + canvas.camera[1] + ", z=" + canvas.camera[2] + "\n yaw = "
						+ canvas.camera[3] + "  pitch = " + canvas.camera[4] + " roll = " + canvas.camera[5]);
	}



}
