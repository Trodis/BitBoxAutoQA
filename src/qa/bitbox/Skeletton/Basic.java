package qa.bitbox.Skeletton;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.sikuli.script.App;
import org.sikuli.script.Screen;

import qa.bitbox.bitboxhandler.BitBoxRule;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class Basic
{
    private App bitbox;
    private Screen scr;

    @Rule
    public BitBoxRule bitBoxRule = new BitBoxRule();

    @Before
    public void initReferences()
    {
        this.bitbox = bitBoxRule.getBitBox();
        this.scr = bitBoxRule.getScreen();
    }

    @Test
    public void test() throws InterruptedException, IOException, UnsupportedFlavorException
    {

    }
}
