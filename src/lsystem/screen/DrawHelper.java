package lsystem.screen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.gl2.GLUT;

public class DrawHelper {

	public static void placeCamera(GL2 gl, SwingGLCanvas canvas) {
		gl.glRotatef(canvas.camera[4], 1f, 0f, 0f);
		gl.glRotatef(canvas.camera[3], 0f, 1f, 0f);
		gl.glRotatef(canvas.camera[5], 0f, 0f, 1f);
	}
	
	public static void placeCamera(GL2 gl, SwingGLCanvas2D canvas) {
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
		for (int i = -25; i < 26; i++) {
			gl.glVertex3f(-25f, 0f, i);
			gl.glVertex3f(25f, 0f, i);

			gl.glVertex3f(i, 0, -25f);
			gl.glVertex3f(i, 0, 25f);
		}
		gl.glEnd();
		gl.glRasterPos3f(1.1f, 0.0f, 0.0f);
		glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, 'X');
		gl.glRasterPos3f(0.0f, 1.1f, 0.0f);
		glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, 'Y'); // draw the y axis
		gl.glRasterPos3f(0.0f, 0.0f, 1.1f);
		glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_18, 'Z'); // draw the z axis
	}

	public static void drawRectangularPrism(GL2 gl, float leftBottomX, float leftBottomY, float leftBottomZ,
			float rightUpX, float rightUpY, float rightUpZ) {
		gl.glPushMatrix();
		gl.glBegin(GL2.GL_QUADS);
		gl.glVertex3f(leftBottomX, leftBottomY, leftBottomZ);
		gl.glVertex3f(rightUpX, leftBottomY, leftBottomZ);
		gl.glVertex3f(rightUpX, rightUpY, leftBottomZ);
		gl.glVertex3f(leftBottomX, rightUpY, leftBottomZ);

		gl.glVertex3f(leftBottomX, leftBottomY, leftBottomZ);
		gl.glVertex3f(leftBottomX, leftBottomY, rightUpZ);
		gl.glVertex3f(leftBottomX, rightUpY, rightUpZ);
		gl.glVertex3f(leftBottomX, rightUpY, leftBottomZ);

		gl.glVertex3f(rightUpX, leftBottomY, leftBottomZ);
		gl.glVertex3f(rightUpX, leftBottomY, rightUpZ);
		gl.glVertex3f(rightUpX, rightUpY, rightUpZ);
		gl.glVertex3f(rightUpX, rightUpY, leftBottomZ);

		gl.glVertex3f(leftBottomX, leftBottomY, rightUpZ);
		gl.glVertex3f(rightUpX, leftBottomY, rightUpZ);
		gl.glVertex3f(rightUpX, rightUpY, rightUpZ);
		gl.glVertex3f(leftBottomX, rightUpY, rightUpZ);
		gl.glEnd();
		gl.glPopMatrix();
	}

	public static void drawDebugInformation(GL2 gl, GLUT glut, SwingGLCanvas canvas) {
		gl.glRasterPos3f(canvas.camera[0], canvas.camera[1], canvas.camera[2] - 1);
		gl.glColor3f(1f, 1f, 1f);
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18,
				"x=" + canvas.camera[0] + ", y=" + canvas.camera[1] + ", z=" + canvas.camera[2] + "\n yaw = "
						+ canvas.camera[3] + "  pitch = " + canvas.camera[4] + " roll = " + canvas.camera[5]);
	}
	
	public static void drawDebugInformation(GL2 gl, GLUT glut, SwingGLCanvas2D canvas) {
		gl.glRasterPos3f(canvas.camera[0], canvas.camera[1], canvas.camera[2] - 1);
		gl.glColor3f(1f, 1f, 1f);
		glut.glutBitmapString(GLUT.BITMAP_HELVETICA_18,
				"x=" + canvas.camera[0] + ", y=" + canvas.camera[1] + ", z=" + canvas.camera[2] + "\n yaw = "
						+ canvas.camera[3] + "  pitch = " + canvas.camera[4] + " roll = " + canvas.camera[5]);
	}

	public static void drawStick(GL2 gl, float echelle, float x, float y, int angle) {
		angle = angle - ((angle / 360) * 360);
		switch (angle) {
		case -315:
			angle = 45;
			;
		case -270:
			angle = 90;
			;
		case -225:
			angle = 135;
			;
		case -180:
			angle = 180;
			;
		case -135:
			angle = 225;
			;
		case -90:
			angle = 270;
			;
		case -45:
			angle = 315;
			;
		}
		
		// Direction
		int newX=0, newY=0;
		switch (angle) {
		case 0:
			newX = 1;
			newY = 1;
			break;
		case 45:
			newX = 1;
			newY = 0;
			break;
		case 90:
			newX = 1;
			newY = -1;
			break;
		case 135:
			newX = 0;
			newY = -1;
			break;
		case 180:
			newX = -1;
			newY = -1;
			break;
		case 225:
			newX = -1;
			newY = 0;
			break;
		case 270:
			newX = -1;
			newY = 1;
			break;
		case 315:
			newX = 0;
			newY = 1;
			break;
		}
		gl.glBegin(GL2.GL_LINES);
		gl.glVertex2f(x, y);
		gl.glVertex2f(x + (echelle * newX), y + (echelle * newY));
		gl.glEnd();
		/* System.out.println("X : " +(x + (echelle * newX)));
		System.out.println("Y : " +(y + (echelle * newY)));
		System.out.println("angle : " +angle);
		System.out.println("newX : " +newX);
		System.out.println("newY : " +newY);*/
	}

}
