/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;


import static starbucks.Device.landscape_screen_width;

/** Settings Screen */
public class Settings extends Screen {
    private AddCard addcard;
    private IScreen mycards;
    private IFrame frame;
    private String name ="" ;
    private KeyPad keyPad ;
    private Spacer spacer ;
    private CardNum cardNum ;
    private CardCode cardCode ;
    private String current;
    private int framelen = 0;

    /** Constructor */
    public Settings() {
        frame = new Frame();
        mycards = new MyCards();
        current = "portrait";
    }

    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name() {
        name = "   Settings";
        return name;
    }
    /**
     * Get Display Contents
     * @return out
     */
    public String display() {

        String value1 = " Add Card";
        String value2 = " Delete Card";
        String value3 = " Billing";
        String value4 = " Passcode";
        String value5 = " About|Terms";
        String value6 = " Help";
        String out = "";
        current = frame.getStrategy();
        if (current.equals("portrait")) {
            framelen = 15;
        } else if (current.equals("landscape")) {
            framelen = landscape_screen_width;
        }
        int nameLen = value1.length();
        if (nameLen < framelen) {
            int pad = (framelen - nameLen) / 2;
            out += padSpaces(pad);
        }
        out += value1 + "\n" ;
        nameLen = value2.length();
        if (nameLen < framelen) {
            int pad = (framelen - nameLen) / 2;
            out += padSpaces(pad);
        }
        out += value2 + "\n";
        nameLen = value3.length();
        if (nameLen < framelen) {
            int pad = (framelen - nameLen) / 2;
            out += padSpaces(pad);
        }
        out += value3 + "\n";
        nameLen = value4.length();
        if (nameLen < framelen) {
            int pad = (framelen - nameLen) / 2;
            out += padSpaces(pad);
        }
        out += value4 + "\n\n";
        nameLen = value5.length();
        if (nameLen < framelen) {
            int pad = (framelen - nameLen) / 2;
            out += padSpaces(pad);
        }
        out += value5 + "\n";
        nameLen = value6.length();
        if (nameLen < framelen) {
            int pad = (framelen - nameLen) / 2;
            out += padSpaces(pad);
        }
        out += value6 + "\n";

        return out;
    }

    /** Add Pad spaces for the display
     * @param num integer number
     * @return String
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
    /**
     * Set Frame
     * @param f Frame
     */
    public void setFrame(IFrame f) {
        frame = f;
    }

    /**
     * Set screen with screen name
     * @param mc Screen
     */
    public void setScreen(IScreen mc ) {
        mycards = mc;
    }

    /**
     * Set Card Screen
     * @param a AddCard
     */
    /*public void setCardScrn(AddCard a) {
        addcard = new AddCard();
        addcard = a;
    }*/

    /**
     * Send Touch Events to the Chain
     * @param x Touch X Coord.
     * @param y Touch Y Coord.
     */
    public void touch(int x, int y) {
        System.err.println("KeyPad Touched at (" + x + ", " + y + ")");
        if ((x == 1 && y == 1) || (x == 2 && y == 1) || (x == 3 && y == 1)) {
            createAddCard();
        }
    }

    /** create AddCard screen */
    public void createAddCard() {
        cardNum = new CardNum() ;
        cardCode = new CardCode() ;
        addcard = new AddCard();
        keyPad = new KeyPad() ;
        spacer = new Spacer();
        ((IKeyPadSubject)keyPad).attach( cardNum ) ;
        ((IKeyPadSubject)keyPad).attach( cardCode ) ;
        //cardCode.addObserver(addcard);
        //cardNum.addObserver(addcard);
        frame.setCurrentScreen(addcard);
        addcard.setFrame(frame);
        addcard.setNext(mycards, "1");
        addcard.setScreen(this,keyPad);
        addcard.addSubComponent(cardNum) ;
        addcard.addSubComponent(cardCode) ;
        addcard.addSubComponent(spacer) ;
        addcard.addSubComponent(keyPad) ;
        ((IKeyPadSubject)keyPad).attach( addcard ) ;
    }
    /** go to next Screen  not used*/
    public void next() {
        System.err.println("KeyPad pressed for next");
    }

    /** go to previous Screen */
    public void prev() {
        System.err.println("KeyPad pressed for prev");
    }
}