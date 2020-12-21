/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Passcode Screen Component */
public class Passcode implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver
{
    ITouchEventHandler nextHandler ;
    private int count = 0;
    private boolean invalidlogin = false;
    private KeyPad keypad;
    private int pinlen;

    /**
     * Touch Event Ignored by Passcode
     * @param x Touch X
     * @param y Touch Y
     */
    public void touch(int x, int y) 
    {
        if ( y==2 )
        {
            System.err.println( "Passcode Touched at (" + x + ", " + y + ")" ) ; 
        }
        else
        {
            if ( nextHandler != null )
                nextHandler.touch(x,y) ;
        }
    }
    
    /**
     * Set Next Touch Handler
     * @param next Touch Event Handler
     */
    public void setNext( ITouchEventHandler next) 
    { 
        nextHandler = next ;
    }


    /**
     * Display "Echo Feedback" on Pins enterred so far
     * @return out
     */
    public String display()
    {
        String out = "";
        if (pinlen == 4) {
           out = displayFourPin();
        }
        else {
            out = displaySixPin(); }

         return out  ;
    }

    /**
     * Display Four Digit pin screen
     * @return value
     */
    public String displayFourPin() {
        String value = "" ;
        switch (count) {
            case 0:
                if (invalidlogin) {
                    value = "  Invalid Pin\n\n  [_][_][_][_]";
                    invalidlogin = false;
                } else {
                    value = "\n\n  [_][_][_][_]"; }
                break;
            case 1:
                value = "\n\n  [*][_][_][_]";
                break;
            case 2:
                value = "\n\n  [*][*][_][_]";
                break;
            case 3:
                value = "\n\n  [*][*][*][_]";
                break;
            case 4:
                value = "\n\n  [*][*][*][*]";
                break;
            default:
                value = "\n\n  [_][_][_][_]";
        }
        return value;
    }

    /**
     * Display Six Digit pin screen
     * @return value
     */
    public String displaySixPin() {
        String value = "";
        switch (count) {
            case 0:
                if (invalidlogin) {
                    value = "  Invalid Pin \n\n  _ _ _ _ _ _";
                    invalidlogin = false;
                } else {
                    value = "\n\n  _ _ _ _ _ _";
                }
                break;
            case 1:
                value = "\n\n  * _ _ _ _ _";
                break;
            case 2:
                value = "\n\n  * * _ _ _ _";
                break;
            case 3:
                value = "\n\n  * * * _ _ _";
                break;
            case 4:
                value = "\n\n  * * * * _ _";
                break;
            case 5:
                value = "\n\n  * * * * * _";
                break;
            case 6:
                value = "\n\n  * * * * * *";
                break;
            default:
                value = "\n\n  _ _ _ _ _ _";
        }
        return value;
    }

    public void addObserver(KeyPad kp)
    {
        keypad = kp ;
    }
    /**
     * Add Sub Component (Not used)
     * @param c Sub Component to Add
     */
    public void addSubComponent( IDisplayComponent c ) 
    {
        
    }

    /**
     * Key Event Update
     * @param c   Count of Keys So Far
     * @param key Last key Pressed
     * @param key1 cardNum key Pressed
     * @param key2 cardCode key Pressed
     */
    public void keyEventUpdate( int c, String key, String key1, String key2 )
    {
        System.err.println( "Key: " + key ) ;
        count = c ;
    }

    /**
     * Receive Authenticated Event from Authenticator
     * @param pincount integer
     */
     public void getPinCount(int pincount) {
        count = pincount;
        invalidlogin = true;
        notifyObserver();
    }

    /**
     * Receive Length of the Pin to be entered
     * @param pinlength integer
     */
    public void pinLenDetails(int pinlength) {
        pinlen = pinlength;
    }

    /**
     * Notify Observers
     */
    public void notifyObserver() {
         keypad.setCount(count);
    }
}
