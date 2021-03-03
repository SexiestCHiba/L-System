package lsystem.screen.gl2d;

import lsystem.screen.AbstractCanvas;


public class SwingGLCanvas2D extends AbstractCanvas {

    public SwingGLCanvas2D() {
        super();
    }

    @Override
    protected void addEventsListeners() {
        glCanvas.addGLEventListener(new JoglEventListener2D(this));
        JoglMouseListener2D mouse = new JoglMouseListener2D(this);
        glCanvas.addMouseListener(mouse);
        glCanvas.addMouseMotionListener(mouse);
        glCanvas.addMouseWheelListener(mouse);
        glCanvas.addKeyListener(new KeyboardListener2D(this));
    }
}
