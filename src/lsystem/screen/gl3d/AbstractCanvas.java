package lsystem.screen.gl3d;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;
import lsystem.engine.Element;
import lsystem.engine.Parser;
import lsystem.engine.Rewrite;
import lsystem.screen.Constants;
import lsystem.utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * create an instance of the 3D window
 */
public abstract class AbstractCanvas {

    private Element lSystem;
    /**
     * If value is {@link State#LOAD}, we currently converting the L-System to {@link Element} object
     * otherwise the value is {@link State#FINISH_OR_NULL} 2 possibility:
     *      we waiting for end-user to give axiom and rules from {@link lsystem.screen.main.MainFrame main window}
     *      or we window is open and user see the representation the L-System in 3D
     * @see State
     */
    public State parsedState = State.FINISH_OR_NULL;
    public JFrame frame;
    protected FPSAnimator animator;
    public final GLCanvas glCanvas;
    public GLU glu = new GLU();
    public GLUT glut = new GLUT();
    /**
     * camera position, 6 dimension tab with respectively x, y and z camera pos and yaw, pitch roll camera rotation
     */
    public float[] camera = {0f, 1f, 5f, // camera pos x,y, z
            0f, 0f, 0f}; // camera rotation yaw(x-axis), pitch(y-axis), roll(z-axis)


    protected AbstractCanvas() {
        GLProfile glProfile = GLProfile.getDefault();
        GLCapabilities glCapabilities = new GLCapabilities(glProfile);
        this.glCanvas = new GLCanvas(glCapabilities);
        frame = new JFrame("L-System");
        animator = new FPSAnimator(glCanvas, 60);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                setVisible(false);
                lSystem = null;
                parsedState = State.FINISH_OR_NULL;
                // after setting lSystem to null, the object can be large (up to 4gb during our tests) and it's stored
                // long time in memory
                // the partial gc only clear the object itself and absolutely not all of its children from memory
                System.gc();
            }
        });
        frame.getContentPane().add(glCanvas, BorderLayout.CENTER);
        frame.setSize(Constants.INITIAL_WIDTH, Constants.INITIAL_HEIGHT);
        addEventsListeners();
    }

    protected abstract void addEventsListeners();

    /**
     * if {@code bl} is true, we start the animator and then display the window
     * @param bl
     */
    public void setVisible(boolean bl) {
        if(bl)
            animator.start();
        else
            animator.stop();
        frame.setVisible(bl);
    }

    public Element getLSystem() {
        return lSystem;
    }

    /**
     * get axiom and rule to rewrite and parse it to an {@link Element} object and store it in {@link AbstractCanvas#lSystem}
     * @param axiom give from {@link lsystem.screen.main.Tab}
     * @param rules same
     * @param iterations same
     * @throws NumberFormatException throw by {@link Float#parseFloat(String)}
     */
    public void setLSystem(String axiom, List<Pair<String, String>> rules, int iterations) throws NumberFormatException {
        parsedState = State.LOAD;
        this.lSystem = Parser.parse(Rewrite.rewrite(axiom, rules, iterations));
        parsedState = State.FINISH_OR_NULL;
    }

    /**
     * @see AbstractCanvas#parsedState
     */
    public enum State {
        LOAD,
        FINISH_OR_NULL
    }
}
