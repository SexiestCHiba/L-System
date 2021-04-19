package lsystem;

import lsystem.engine.Parser;
import lsystem.engine.Rewrite;
import lsystem.screen.gl3d.GLCanvas;
import lsystem.utils.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestLSystem {

    @Test
    public void parserTest() {
        GLCanvas joglFrame = new GLCanvas();
        Parser parser = new Parser("X", Arrays.asList("X=XY", "Y=X"), 5);
        List<Pair<String, String>> lSystemRules = parser.parseRules();
        joglFrame.setLSystem(parser.getAxiom(), lSystemRules, parser.getNbIterations());
        System.out.println(joglFrame.getLSystem());
        assertNotNull("L-System should not be null", joglFrame.getLSystem());
    }

    @Test
    public void rewriteTest(){
        Parser parser = new Parser("X", Arrays.asList("X=XY", "Y=X"), 7);
        String result = Rewrite.rewrite(parser.getAxiom(), parser.parseRules(), parser.getNbIterations());
        assertEquals("Not equals", "XYXXYXYXXYXXYXYXXYXYXXYXXYXYXXYXXY", result);
    }

    @Test
    public void incorrectTest() {
        Parser parser = new Parser("X", Arrays.asList("X=XY", "y=X"), 3);
        assertTrue(parser.isCorrect());
        parser = new Parser("X", Arrays.asList("X+XY" , "Y=X"), 3);
        assertFalse(parser.isCorrect());
        parser = new Parser("X", Arrays.asList("X=X++Y", "Y=X"), 3);
        assertTrue(parser.isCorrect());
        try {
            assertNotNull(Parser.parse(Rewrite.rewrite("X", new Parser("X", Arrays.asList("X=XY", "Y=X"), 3).parseRules(), 3)));
        } catch (NumberFormatException err) {
            System.out.println("fail");
            err.printStackTrace();
            fail();
        }
    }

}
