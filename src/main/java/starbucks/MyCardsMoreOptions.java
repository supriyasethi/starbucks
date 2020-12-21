/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card More Options Screen */
public class MyCardsMoreOptions extends Screen
{
    private String name = "" ;

    /** Constructor */
    public MyCardsMoreOptions()
    {
    }

    /**
     * Display "Echo Feedback" on keys entered so far
     * @return value
     */
    public String display()
    {
        String value = "\nRefresh\n" ;
        value += "Reload\n" ;
        value += "Auto Reload\n" ;
        value += "Transactions\n" ;
        return value  ;
    }


    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name() {

        name = "  My Cards" ;
        return name;
    }

    /**
     * Touch Event Ignored by Passcode
     * @param x Touch X
     * @param y Touch Y
     */
    public void touch(int x, int y) {
        System.err.println("KeyPad Touched at (" + x + ", " + y + ")"); }

}
