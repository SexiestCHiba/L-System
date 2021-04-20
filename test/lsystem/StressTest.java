package lsystem;

import lsystem.engine.Parser;
import lsystem.screen.gl3d.GLCanvas;
import lsystem.utils.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class StressTest {

    /**
     * require at least 1GB of free ram
     */
    @Test
    public void stressTest1() {
        long millis = System.currentTimeMillis();
        GLCanvas joglFrame = new GLCanvas();
        Parser parser = new Parser("Y", Arrays.asList("Y=X+[[Y]-Y]-X[-XY]+Y", "X=XX"), 12);
        List<Pair<String, String>> lSystemRules = parser.parseRules();
        joglFrame.setLSystem(parser.getAxiom(), lSystemRules, parser.getNbIterations());
        System.out.println(joglFrame.getLSystem());
        System.out.println("time in millisecondes: " + (System.currentTimeMillis() - millis));
        assertNotNull("L-System should not be null", joglFrame.getLSystem());
    }

}
