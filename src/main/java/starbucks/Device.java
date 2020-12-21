/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/**
 * Authentication Proxy for App Controller
 */
public class Device implements IApp, IPinAuthObserver {
    
    private static Device theDevice = null;   
    private boolean fourPin = true ;
    private boolean sixPin = false ;
    private String pin = "" ;
    private String name = "" ;
    private int pinlen ;

    private IApp app ;
    private KeyPad kp ;
    private Passcode pc ;
    private PinScreen ps ;
    private Spacer sp ;
    private boolean authenticated = false ;
    private PinEntryMachine pm ;

    public static final int screen_frame_header = 3 ;
    public static final int portrait_screen_width = 15 ;
    public static final int portrait_screen_length = 10 ;
    public static final int landscape_screen_width = 32 ;
    public static final int landscape_screen_length = 6 ;

    /** Orientation mode for Device */
    public enum ORIENTATION_MODE {
        PORTRAIT, LANDSCAPE
    }

    /** Declaration of Orientation mode for Device */
    private ORIENTATION_MODE device_orientation_state ;

    /** get Device orientation mode for the current Frame
     * @return ORIENTATION_MODE */
    public ORIENTATION_MODE getDeviceOrientation() {
        return this.device_orientation_state ;
    }

    public void setPortraitOrientation() {
        this.device_orientation_state = ORIENTATION_MODE.PORTRAIT ;
    }

    public void setLandscapeOrientation() {
        this.device_orientation_state = ORIENTATION_MODE.LANDSCAPE ;
    }

    private Device() { }

    /** Debug Device State */
    public static void debug()
    {
        Device d = Device.getInstance() ;
        System.err.println( "============================================" ) ;
        System.err.println( "--------- D E V I C E  S T A T E  ----------" ) ;
        System.err.println( "============================================" ) ;
        System.err.println( "Pin Option    = " + d.getPinOption() ) ;
        System.err.println( "Stored Pin    = " + d.getPin() ) ;
        System.err.println( "Authenticated = " + d.isAuthenticated() ) ;
        System.err.println( "Orientation   = " + d.getDeviceOrientation() ) ;
        System.err.println( "============================================" ) ;
    }

    /**
     * Get Current Auth State
     * @return Auth T/F
     */
    public String isAuthenticated() {
        return Boolean.toString( authenticated ) ;
    }    

    /**
     * Return the current Pin Option:
     *  0 = User Chosed No Pin
     *  4 = User Chosed 4-digit Pin
     *  6 = User Chosed 6-digit Pin
     * @return Pin Option
     */
    public int getPinOption() {
        if ( fourPin )
            return 4 ;
        else if ( sixPin )
            return 6 ;
        else
            return 0 ;
    }

    /**
     * Get Current Pin
     * @return Pin
     */
    public String getPin() {
        return pin ;
    }


    /**
     * Set Pin
     * @param p New Pin
     */
    private void setPin( String p ) {
        pin = p ;
        int len = p.length() ;
        if ((len < 4) || (len == 5) || (len > 6)) {
            len = 0;
        }
        switch ( len ) {
            case 0:
                fourPin = false ;
                sixPin = false ;
                pinlen = 0;
                this.authenticated = true ;
                break;
            case 4:
                fourPin = true ;
                sixPin = false ;
                pinlen = 4;
                break ;
            case 6:
                fourPin = false ;
                sixPin = true ;
                pinlen = 6 ;
                break ;
            default:
                fourPin = false ;
                sixPin = false ;
                pinlen = 0 ;
        }
    }

    /**
     * Device Reset Pin  
     */
    /*private void clearPin()
    {
        this.pin = "" ;
    }*/

    /**
     * Get Singleton Instance
     * @return Reference to Current Device Config (Create if none exists)
     */
    public synchronized static Device getInstance() {
        if (theDevice == null) {
            return getNewInstance( "1234" ) ;
        }
        else
            return theDevice;
    }

    /**
     * Get New Instance 
     * @return Reference to Device (Create New Singleton)
     */
    public synchronized static Device getNewInstance() {
        return getNewInstance( "1234" ) ;
    }

    /**
     * Get New Instance
     * @return Reference to Device (Create New Singleton)
     * @param  pin String
     */
    public synchronized static Device getNewInstance( String pin ) {
        theDevice = new Device() ;
        theDevice.setPin( pin ) ;
        theDevice.startUp() ;
        debug() ;
        return theDevice ;
    }

    /**
     * Device Starup Process.
     * Starts Up with Default 4-Pin Option
     */
    public void startUp()
    {
        kp = new KeyPad() ;
        pc = new Passcode() ;
        sp = new Spacer() ;
        ps = new PinScreen() ;
        pm = new PinEntryMachine() ;

        // setup the composite pattern
        ps.addSubComponent( pc ) ;
        ps.addSubComponent( sp ) ;
        ps.addSubComponent( kp ) ;

        // setup the observer pattern
        ((IKeyPadSubject)kp).attach( pc ) ;
        ((IKeyPadSubject)kp).attach( pm ) ;
        ((IPinAuthSubject)pm).registerObserver(this) ;
        pm.addObserver(pc);
        pc.addObserver(kp);

        // get app controller reference
        app = new AppController() ;

        pm.getPinInfo(pin) ;
        pc.pinLenDetails(pinlen);


        // startup in portrait
        this.device_orientation_state = ORIENTATION_MODE.PORTRAIT ;
    }

    /**
    * Switch to Landscape View
    */
    public void landscape() {
        System.err.println("In Landscape mode");
        if ( authenticated )
            setLandscapeOrientation();
            app.landscape() ;
    }

    /**
     * Switch to Portait View
     */
    public void portrait() {
        System.err.println("In Portrait mode");
        if ( authenticated )
            setPortraitOrientation();
            app.portrait() ;
    }

    /**
     * User Touch at X,Y Coordinates
     * @param x X Coordinate
     * @param y Y Coordinate
     */
    public void touch(int x, int y) {
        //System.err.println("In Device touch");
        if ( authenticated )
            app.touch(x, y) ;
        else
            ps.touch(x, y) ;
    }

    /**
     * Display Screen Contents to Terminal
     */
    public void display() {
        System.err.println("In Device display");
        System.out.println( screenContents() ) ;
    }

    /**
     * Get Class Name of Screen
     * @return Class Name of Current Screen
     */
    public String screen() {
        //System.err.println("In Device screen");
        if ( authenticated )
            return app.screen() ;
        else {
            if (ps.name().equals("PinScreen"))
                name = "";
            else
                name = ps.name();
        }
            //return ps.name() ;
       // if (name.equals("MyCards"))
        //{ name = "My Cards" ; }
        return name ;
    }

    /**
     * Get Screen Contents as a String
     * @return Screen Contents of Current Screen
     */
    public String screenContents() {
                if ( authenticated ) {
                    return app.screenContents() ;
                } else {
                    if (ps.name().equals("PinScreen")) {
                        name = ""; }
                    else {
                        name = ps.name();
                    }

            String out = "" ;
            out = "---------------\n" ;
            //out += "   " + ps.name() + "  \n" ;
            out += "   " + name + "  \n" ;
            //out += "----------------\n\n\n" ;
            out += "---------------\n" ;
            out += ps.display() ;
            out += "\n\n\n---------------\n" ;
            dumpLines(out);
            return out ;
        }
    }

    /**
     * Helper Debug Dump to STDERR
     * @param str Lines to print
     */
    private void dumpLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        for ( int i = 0; i<lines.length; i++ ) {
            System.err.println( i + ": " + lines[i] ) ;
        }
    }
    /**
     * Select a Menu Command
     * @param c Menu Option (A, B, C, E, or E)
     */
    public void execute( String c ) {
        System.err.println("In Device execute");
        if ( authenticated )
            app.execute( c ) ;
    }

    /**
     * Navigate to Previous Screen
     */
    public void prev() {
        System.err.println("In Device previous");
        if ( authenticated )
            app.prev() ;
    }

    /**
     * Navigate to Next Screen
     */
    public void next() {
        System.err.println("In Device next");
        if ( authenticated )
            app.next() ;
    }

    /**
     * Receive Authenticated Event from Authenticator
     */
    public void authEvent() {
        System.err.println("In Device authevent");
        this.authenticated = true ;
    }



}
