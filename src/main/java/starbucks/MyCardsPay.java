/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;

import static starbucks.Device.*;


/** My Card Pay Screen */
public class MyCardsPay extends Screen
{
    private String name = "";
    private IScreen mc;
    private IFrame frame;
    private String current = "";
    private int framelen = 0;
    private double cardbal;
    private String cardnum;

    /** Constructor */
    public MyCardsPay()
    {
        cardnum = "[000000000]";
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
        String value1 = cardnum;
        String value2 = " Scan Now";
        String out = "";
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        System.err.println("Inside  MyCardsPay display");
        current = frame.getStrategy();
        if (current.equals("portrait")) {
            framelen = 15;
        } else if (current.equals("landscape")) {
            framelen = landscape_screen_width;
            sb.append(" ");
        }

        int nameLen = value1.length();
        if (nameLen < framelen) {
            int pad = (framelen - nameLen) / 2;
            sb.append(padSpaces(pad));
        }
        sb.append(value1);
        sb.append("\n\n\n");
        nameLen = value2.length();
        if (nameLen < framelen) {
            int pad = (framelen - nameLen) / 2;
            sb.append(padSpaces(pad));
        }
        sb.append(value2);
        out = sb.toString();


        return out  ;
    }

    /**
     * Add paded spaces
     * @return String
     * @param num Integer
     */
    private String padSpaces(int num) {
        String spaces = "" ;
        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i<num; i++ )
            sb.append(" ");
        spaces = sb.toString();
        return spaces ;
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
        System.err.println("Screen : MyCardsPay");
        if ((x == 2 && y == 2) || (x == 3 && y == 2)) {
            System.err.println(("Balance " + cardbal));
                  if (cardbal > 1.50) {
                    cardbal = cardbal - 1.50;
                    mc.setCardBal(cardnum, cardbal);
            } }
            if (x == 3 && y == 3) {
                frame.setCurrentScreen(mc);
            }
    }

    /** go to next screen */
    public void next() {
        System.err.println("KeyPad Touched at next");
    }
    /** go to previous screen */
    public void prev() {
        System.err.println("KeyPad Touched at prev");
        frame.setCurrentScreen(mc) ;
    }

    /** update card balance
     * @param v - used to send the card balance
     * @param cardk - used to send cardkey as parameter
     **/
    @Override
    public void setCardBal(String cardk, double v) {
        if (cardk.equals("")) {
            cardnum = "[000000000]";
        } else {
        cardnum = "[" + cardk + "]"; }
        cardbal = v;
    }
}

