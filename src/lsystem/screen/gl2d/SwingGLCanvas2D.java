package lsystem.screen.gl2d;

import lsystem.screen.AbstractCanvas;


public class SwingGLCanvas2D extends AbstractCanvas {

    @Override
    protected void addEventsListeners() {
        glCanvas.addGLEventListener(new JoglEventListener2D(this));
    }
}
