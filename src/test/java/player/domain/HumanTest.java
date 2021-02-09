package player.domain;

import come.codeassignment.gameofthree.player.domain.Human;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HumanTest {

    Human human;

    @Before
    public void setup(){
        human = new Human();
    }

    @Test
    public void shouldSetAnIdAndName(){

        human.register();
        assertTrue("The register method should create a player and then set a Name",
                human.getName().contains("PLAYER "));

        assertEquals("The register method should create a player and then set an Id",
                36, human.getId().length());
    }
}