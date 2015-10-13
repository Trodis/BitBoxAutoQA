package qa.bitbox.bitboxhandler;

/**
 * Created by Trodis on 08.10.2015.
 */


public class Constants
{
    //Clipboard test content
    public static final String CLIPBOARDTESTCONTENT_A = "This is a test content";
    public static final String CLIPBOARDTESTCONTENT_B = "This is a different test content";

    //Error Messages
    public static final String BITBOXQUITERROR = "Killing BitBox was not possible!";
    public static final String BITBOXSTARTERROR = "Starting BitBox was not possible!";

    //Paths
    public static final String FAILEDPATH = "C:\\"+System.getenv("HOMEPATH")+"\\BitBoxTestResults";

    //Registry Guest to Host
    public static final String GET_REGPOLICYTTH = "\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sirrix AG\\" +
            "BitBox\\informationFlows\\GuestToHost\\permissions\\textToHost\\1\"";
    public static final String GET_REGPOLICYDOWNLOAD = "\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sirrix AG\\" +
            "BitBox\\informationFlows\\GuestToHost\\permissions\\download\\1\"";
    public static final String GET_REGPOLICYPRINT = "\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sirrix AG\\" +
            "BitBox\\informationFlows\\GuestToHost\\permissions\\print\\1\"";
    public static final String SET_REGPOLICYTTH = "\\\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sirrix AG\\" +
            "BitBox\\informationFlows\\GuestToHost\\permissions\\textToHost\\1\\\"";
    public static final String SET_REGPOLICYDOWNLOAD = "\\\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sirrix AG\\" +
            "BitBox\\informationFlows\\GuestToHost\\permissions\\download\\1\\\"";
    public static final String SET_REGPOLICYPRINT = "\\\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sirrix AG\\" +
            "BitBox\\informationFlows\\GuestToHost\\permissions\\print\\1\\\"";

    //Registry Host to Guest
    public static final String GET_REGPOLICYTTG = "\"HKLM\\SOFTWARE\\Wow6432Node\\Sirrix AG\\" +
            "BitBox\\informationFlows\\HostToGuest\\permissions\\textToGuest\\1\"";
    public static final String GET_REGPOLICYUPLOAD = "\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sirrix AG\\" +
            "BitBox\\informationFlows\\HostToGuest\\permissions\\upload\\1\"";
    public static final String SET_REGPOLICYTTG = "\\\"HKLM\\SOFTWARE\\Wow6432Node\\Sirrix AG\\" +
            "BitBox\\informationFlows\\HostToGuest\\permissions\\textToGuest\\1\\\"";
    public static final String SET_REGPOLICYUPLOAD = "\\\"HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Sirrix AG\\"+
            "BitBox\\informationFlows\\HostToGuest\\permissions\\upload\\1\\\"";

    //Registr Values
    public static final String ALLOW = "allow";
    public static final String ASKUSER = "askUser";
    public static final String DENY = "deny";
}
