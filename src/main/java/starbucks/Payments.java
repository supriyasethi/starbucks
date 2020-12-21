/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Payments Screen */
public class Payments extends Screen
{


    /** Constructor */
    public Payments()
    {

    }

    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name() {
        return "  Payments";
    }
    /**
     * Display options on Screen
     * @return value
     */
    public String display()
    {
        String out = "";
        out += "Find Store\n";
        out += "Enable Payments";
        return out  ;
    }

    /**
     * Touch Event Ignored by Passcode
     * @param x Touch X
     * @param y Touch Y
     */
    public void touch(int x, int y) {
        System.err.println("KeyPad Touched at (" + x + ", " + y + ")"); }
}
