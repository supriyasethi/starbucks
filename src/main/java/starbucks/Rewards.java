/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Rewards Screen */
public class Rewards extends Screen
{

    /** Constructor */
    public Rewards()
    {

    }

    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name() {
        return " Rewards";
    }
    /**
     * Display "Echo Feedback" on keys entered so far
     * @return value
     */
    public String display()
    {
        String out="";
        out += "Make Every\n";
        out += "Visit Count";
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
