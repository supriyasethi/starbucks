/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import static starbucks.Device.landscape_screen_width;

/** My Cards Screen */
public class MyCards extends Screen
{
    private String name = "";
    private IScreen mycardspay;
    private IScreen mycardsoptions;
    private IScreen mycardsmoreoptions;
    private IFrame frame;
    private String current;
    private int framelen = 0;
    private double cardbal = 0.00 ;
    private String cardk = "";

    /** Constructor */
    public MyCards() {
        frame = new Frame();
        current = "portrait";
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
        //mc = s;
    }

    /**
     * Touch Event Ignored by Passcode
     * @param x Touch X
     * @param y Touch Y
     */
    public void touch(int x, int y) {
        System.err.println("KeyPad Touched at (" + x + ", " + y + ")");
        System.err.println("Screen : MyCardsMain");
        if (x == 3 && y == 3) {
            System.err.println("Moving to My Cards Pay");
            mycardspay = new MyCardsPay();
            mycardspay.setCardBal(cardk, cardbal);
            frame.setCurrentScreen(mycardspay);
            mycardspay.setFrame(frame);
            mycardspay.setScreen(this);
        }
        if (x == 2 && y == 4) {
            System.err.println("Moving to My Cards Options");
            mycardsoptions = new MyCardsOptions();
            frame.setCurrentScreen(mycardsoptions);
            mycardsoptions.setFrame(frame);
            mycardsoptions.setScreen(this);
        }
        if ((x == 1 && y == 7) || (x == 2 && y == 7) || (x == 3 && y == 7)) {
            System.err.println("Moving to My Cards More Options");
            mycardsmoreoptions = new MyCardsMoreOptions() ;
            frame.setCurrentScreen(mycardsmoreoptions);
            mycardsmoreoptions.setFrame(frame);
            mycardsmoreoptions.setScreen(this);
        }

    }

    /**
     * Display "Echo Feedback" on keys entered so far
     * @return value
     */
    public String display()
    {
        String value1 = "";
        StringBuffer sb = new StringBuffer();
        System.err.println("Inside  MyCards display");
        current = frame.getStrategy();
        if (current.equals("portrait")) {
            framelen = 15;
        } else if (current.equals("landscape")) {
            framelen = landscape_screen_width;
            sb.append("\n\n");
        }
        String cardbals = String.format("%,.2f", cardbal);
        String value = " $" +cardbals;
        int nameLen = value.length();
        if (nameLen < framelen) {
            int pad = (framelen - nameLen) / 2;
            sb.append(padSpaces(pad));
        }
        sb.append(value);
        if (current.equals("landscape")) {
            sb.append("\n");
        }
        value1 = sb.toString();
        return value1;
    }

    /**
     * Helper:  Pad Spaces for a Line
     * @param  num Num Spaces to Pad
     * @return     Padded Line
     */
    private String padSpaces(int num) {
        String spaces = "" ;
        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i<num; i++ )
            sb.append(" ");
        //spaces += " " ;
        spaces = sb.toString();
        return spaces ;
    }

    /** initialize card balance
     * @param cardkey  - used to send cardkey as parameter
     * @param cardb - card balance to be sent
     */
    public void initializeCard(String cardkey, double cardb) {
        cardk = cardkey;
        cardbal = cardb;
    }

    /** update card balance
     * @param v - used to send the card balance
     * @param s - used to send cardkey as parameter
     **/
    @Override
    public void setCardBal(String s, double v) {
        cardbal = v;
    }

    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name() {
        System.err.println("Inside  MyCards name");
        name = this.getClass().getName().split("\\.")[1] ;
        if (name.equals("MyCards")) {
            name = "  My Cards";
        }
        return name;
    }
}
