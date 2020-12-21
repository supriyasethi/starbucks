/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Screen Interface */
public interface IScreen
{

    /**
     * Send touch events to screen
     * @param x Touch X
     * @param y Touch Y
     */
    void touch(int x, int y) ;              

    /**
     * Displays screen components
     * @return Screen Display
     */
    String display() ;

    /**
     * Returns name of screen
     * @return Screen Name
     */
    String name() ;                         

    /**
     * Navigate to next screen
     */
    void next() ;                           

    /**
     * Navigate to previous screen
     */
    void prev() ;      

    /**
     * Set next screen with action name
     * @param s Screen
     * @param n Action
     */
    void setNext(IScreen s, String n ) ;    

    /**
     * Set previous screen with action name
     * @param s Screen
     * @param n Action
     */
    void setPrev(IScreen s, String n ) ;

    /**
     * Set screen with screen name
     * @param screen Screen
     */
    void setScreen(IScreen screen);

    /**
     * Set Frame
     * @param frame Frame
     */
    void setFrame(IFrame frame);

    /**
     * Set Card Screen
     * @param addcard AddCard
     */
    void setCardScrn(AddCard addcard);

    /** update card balance
     * @param v - used to send the card balance
     * @param cardkey - used to send cardkey as parameter
     **/
    void setCardBal(String cardkey, double v);

    /** initialize card balance
     * @param cardkey - used to send cardkey as parameter
     * @param v - send the card balance
     */
    void initializeCard(String cardkey, double v);
}
