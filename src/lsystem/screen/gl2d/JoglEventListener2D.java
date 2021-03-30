package lsystem.screen.gl2d;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

import lsystem.engine.Element;
import lsystem.engine.ElementProperties;
import lsystem.screen.AbstractListener;
import lsystem.screen.gl3d.DrawHelper;

public class JoglEventListener2D extends AbstractListener {

	public JoglEventListener2D(SwingGLCanvas2D swingGLCanvas) {
		super(swingGLCanvas);
	}

	@Override
	public void drawLSystem(GL2 gl, Element element) {
		drawAll(gl, element, new lsystem.screen.gl2d.Point2(-1.0f, -1.0f));
	}

	private void drawAll(GL2 gl, Element actual, lsystem.screen.gl2d.Point2 origin) {
		if (actual.property == ElementProperties.DRAW) {
			System.out.println("DESSIN");
			lsystem.screen.gl2d.Point2 newOrigin = new lsystem.screen.gl2d.Point2(origin, actual.getRotation2D());
			DrawHelper.drawStick(gl, origin, newOrigin);
		}
		System.out.println(actual.children.isEmpty());
		for (Element children : actual.children) {
			System.out.println("CHILD");
			drawAll(gl, children, new lsystem.screen.gl2d.Point2(origin, actual.getRotation2D()));
		}
	}

	@Override
	public void display(GLAutoDrawable glAutoDrawable) {

		GL2 gl = glAutoDrawable.getGL().getGL2();

		drawLSystem(gl, canvas.getLSystem());
		drawAll(gl, canvas.getLSystem(), new lsystem.screen.gl2d.Point2(-1.0f, -1.0f));

	}
}
