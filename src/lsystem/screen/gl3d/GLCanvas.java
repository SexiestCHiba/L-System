package lsystem.screen.gl3d;


public class GLCanvas extends AbstractCanvas {

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
