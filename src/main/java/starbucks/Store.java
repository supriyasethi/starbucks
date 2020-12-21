/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Store Screen */
public class Store extends Screen
{
    private String name = "";
    /** Constructor */
    public Store()
    {

    }
    /**
     * Display "Echo Feedback" on keys entered so far
     * @return value
     */
    public String display()
    {
        String value = "\n         X\n" ;
        value += "   X\n" ;
        value += "       X\n" ;
        value += "      X\n" ;
        value += "  X\n" ;
        value += "           X\n" ;
        value += "  X\n" ;
        return value  ;
    }
    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name() {

        name = this.getClass().getName().split("\\.")[1] ;
        if (name.equals("Store")) {
            name = "  Find Store";
        }
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
