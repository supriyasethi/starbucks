/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks;
/**
 * Add New Card Screen
 */
public class AddCard extends Screen implements IKeyPadObserver
{
    private IFrame frm;
    private IScreen stgs;
    private IScreen mycard;
    private String cardkey = "";
    private String cardcode = "";
    private KeyPad keypad;


    /** Constructor */
    public AddCard() {
        frm = new Frame();
        mycard = new MyCards();
        stgs = new Settings();
        keypad = new KeyPad();
    }

    /** get an object of the
     ** @param  f IFrame
     **/
    public void setFrame(IFrame f) {
        frm = f;
    }

    /** get an object of previous Screen
     * @param keyPad - passing the keypad object
     * @param screen - passing the Settings object
     **/
    public void setScreen(IScreen screen, KeyPad keyPad) {
        stgs = screen;
        keypad = keyPad;

    }
    /** get the object of next screen
     * @param mc
     * @param s
     **/
    public void setNext(IScreen mc, String s) {
        mycard = mc ;
    }

    /**
     * Get Class Name of Current Screen
     * @return Class Name of Current Screen
     */
    public String name() {

        /**name field for updated name of current screen*/
        System.err.println("Inside  AddCard name");
        return "  Add Card";
    }

    /** go to previous screen */
    public void prev() {
        System.err.println("Key pressed at prev");
        frm.setCurrentScreen(stgs) ;
    }

    /** go to next screen when pressed next*/
    public void next() {
        System.err.println("Key pressed at next");
        if ((cardkey.length() == 9) && (cardcode.length() == 3)) {
            if (!cardkey.equals("000000000")) {
                System.err.println("Inside  Add Card display to go to My Card");
                mycard.initializeCard(cardkey, 20.00);
                frm.setCurrentScreen(mycard);
            } else { validateCardDetails(); }
        } else { validateCardDetails();}
    }

    /** send card details to keypad */
    public void validateCardDetails() {
        System.err.println("Inside  Add Card display after next");
        cardkey = "";
        cardcode = "";
        int cnt = 0;
        keypad.setCardDetails(cardkey, cardcode);
        keypad.setCount(cnt);
    }
    /** get Card code details*/
    public void cardCodeDetails(String cc) {
        cardcode = cc;
    }

    /** get Card key details*/
    public void cardKeyDetails(String ck) {
        cardkey = ck;
    }

    /**
     * Key Event Update
     * @param numKeys Count of Keys So Far
     * @param key Last key Pressed
     * @param key1 cardNum key Pressed
     * @param key2 cardCode key Pressed
     */
    @Override
    public void keyEventUpdate(int numKeys, String key, String key1, String key2) {
        cardkey = key1;
        cardcode = key2;
        if (cardkey.length() > 9) {
            cardkey = cardkey.substring(0,9);
        }
        if (cardcode.length() > 3) {
            cardcode = cardcode.substring(0,3);
        }
    }
}
