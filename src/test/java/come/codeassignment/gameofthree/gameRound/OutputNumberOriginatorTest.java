package come.codeassignment.gameofthree.gameRound;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OutputNumberOriginatorTest {

    OutputNumberOriginator outputNumberOriginator;

    @Before
    public void setup() {
        outputNumberOriginator = new OutputNumberOriginator();
    }

    @Test
    public void should_saveAndRestore_when_theDataIsProvided() {
        OutputNumberMem outputNumberMem = new OutputNumberMem(0, 3, false, "PLAYER 1");
        OutputNumberMem outputNumberMem1 = outputNumberOriginator.save(
                outputNumberMem.getAddition(),
                outputNumberMem.getResult(),
                outputNumberMem.isWinner,
                outputNumberMem.playerName);
        outputNumberOriginator.restore(new OutputNumberMem(0, 9, false, "PLAYER 1"));
        assertTrue("The result should be changed after restoring", outputNumberMem1.getResult() != 9);
    }

}