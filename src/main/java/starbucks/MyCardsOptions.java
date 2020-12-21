/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

/** My Card Options Screen */
public class MyCardsOptions extends Screen
{
    private String name = "" ;
    private IScreen mc;
    private IScreen mycardsmoreoptions ;
    private IFrame frame;

    /** Constructor */
    public MyCardsOptions()
    {
        frame = new Frame();
        mc = new MyCards();
    }

    /** get an object of the
     ** @param  f IFrame
     **/
    public void setFrame(IFrame f) {
        frame = f;
    }

    /** get an object of previous Screen
     * @param  s IScreen
     **/
    public void setScreen(IScreen s) {
        mc = s;
    }

    /**
     * Display "Echo Feedback" on keys entered so far
     * @return value
     */
    public String display()
    {
        String value = "\nReload\n" ;
        value += "Refresh\n" ;
        value += "More Options\n" ;
        value += "Cancel\n" ;
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
        System.err.println("KeyPad Touched at (" + x + ", " + y + ")");
        if ((x == 1 && y == 7) || (x == 2 && y == 7) || (x == 3 && y == 7)) {
            System.err.println("KeyPad Touched at (" + x + ", " + y + ")");
            mycardsmoreoptions = new MyCardsMoreOptions();
            frame.setCurrentScreen(mycardsmoreoptions);
            mycardsmoreoptions.setFrame(frame);
            mycardsmoreoptions.setScreen(mc);
        }
    }

    /** go to next screen */
    public void next() {

    }

    /** go to previous screen */
    public void prev() {
        //frame.setCurrentScreen(mc);

    }

   
}
