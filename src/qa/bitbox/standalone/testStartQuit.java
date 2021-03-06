package qa.bitbox.standalone;

import static org.junit.Assert.*;
import org.junit.*;
import org.sikuli.script.*;
import qa.bitbox.bitboxhandler.*;

public class testStartQuit
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
    public void testStartAndQuitWithKey() throws InterruptedException, FindFailed
    {

        scr.wait("firefox_menu_button.PNG", 120);
        scr.type("q", KeyModifier.CTRL);
        assertTrue(scr.waitVanish("bitbox_status_bar.PNG", 10));
    }

    @Test
    public void testStartAndQuitWithQuitButton() throws InterruptedException, FindFailed
    {
        scr.wait("firefox_menu_button.PNG", 120);
        scr.click("firefox_menu_button.PNG", 1);
        scr.click("firefox_quit_button.PNG", 1);
        assertTrue(scr.waitVanish("bitbox_status_bar.PNG", 10));
    }

    @Test
    public void testStartAndQuitWithXButton() throws InterruptedException, FindFailed
    {
        scr.wait("firefox_menu_button.PNG", 120);
        scr.find("bitbox_window_decoration.png").click("decoration_x_button.png");
        assertTrue(scr.waitVanish("bitbox_status_bar.PNG", 10));
    }
}
