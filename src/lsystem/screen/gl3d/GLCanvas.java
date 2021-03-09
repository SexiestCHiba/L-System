package lsystem.screen.gl3d;

import lsystem.engine.Element;
import lsystem.screen.AbstractCanvas;


public class GLCanvas extends AbstractCanvas {

    public GLCanvas(Element parsed) {
        super(parsed);
    }

    @Override
    protected void addEventsListeners() {
        glCanvas.addGLEventListener(new GLEventListener(this));
        GLMouseListener mouse = new GLMouseListener(this);
        glCanvas.addMouseListener(mouse);
        glCanvas.addMouseMotionListener(mouse);
        glCanvas.addMouseWheelListener(mouse);
        glCanvas.addKeyListener(new GLKeyboardListener(this));
    }

}
