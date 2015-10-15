package qa.bitbox.Skeletton;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.sikuli.script.App;
import org.sikuli.script.Screen;

import qa.bitbox.bitboxhandler.BitBoxGeneralTestRule;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class Basic
{
    private App bitbox;
    private Screen scr;

    @Rule
    public BitBoxGeneralTestRule bitBoxGeneralTestRule = new BitBoxGeneralTestRule();

    @Before
    public void initReferences()
    {
        this.bitbox = bitBoxGeneralTestRule.getBitBox();
        this.scr = bitBoxGeneralTestRule.getScreen();
    }

    @Test
    public void test() throws InterruptedException, IOException, UnsupportedFlavorException
    {

    }
}
