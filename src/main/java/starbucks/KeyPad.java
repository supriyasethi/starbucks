/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import java.util.ArrayList;

/** Key Pad */
public class KeyPad implements ITouchEventHandler, IDisplayComponent, IKeyPadSubject
{
    ITouchEventHandler nextHandler ;
    private ArrayList<IKeyPadObserver> observers ;
    int countPinDigits = 0 ;
    String lastKey = "" ;
    String cardkey = "" ;
    String cardkey1 = "" ;
    String classname = "";
    private boolean cardno = true;
    private boolean codeno = false;

    public KeyPad()
    {
        observers = new ArrayList<IKeyPadObserver>() ;
    }

    /**
     * Touch Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) {
        classname = getCallerClass();
        if ((x==1 && y== 2) || (x==2 && y==2) || (x==3 && y==2))
        {  System.err.println( "KeyPad Touched at (" + x + ", " + y + ")" ) ;
            System.err.println("Focus changed to Cardnumber");
            cardno = true;
            codeno = false;}
        else if (x==2 && y==3) {
            System.err.println( "KeyPad Touched at (" + x + ", " + y + ")" ) ;
            System.err.println("Focus changed to Cardcode");
            codeno = true;
            cardno = false; }
        if ( y > 4 )
        {
            System.err.println( "KeyPad Touched at (" + x + ", " + y + ")" ) ; 
            this.lastKey = getKey( x, y ) ;

            if ((y != 8)) {
                if ((Integer.parseInt(this.lastKey) > 9) || (x > 4) || (x <= 0)) {
                    return;
                }
            }

            if ( x==3 && y==8   )
            {
                if (countPinDigits > 0) {
                countPinDigits-- ; }
                if (classname.equals("starbucks.CardCode"))  {
                if (codeno) {
                    if (cardkey1.isEmpty()) { return; } else {
                    cardkey1 = cardkey1.substring(0, cardkey1.length() - 1); ;
                } }
                else if(cardno){
                        if (cardkey.isEmpty()) { return; } else {
                    cardkey = cardkey.substring(0, cardkey.length() - 1); ;
                }
                } }
            }
            else if ( (x < 4 ) && (y < 8) || (x==2 && y==8))
            {
                countPinDigits++ ;
                if (classname.equals("starbucks.CardCode")) {
                    if (codeno) {
                        cardkey1 += lastKey;
                        if (cardkey1.length() > 3) {
                            cardkey1 = cardkey1.substring(0,3);
                        }
                    } else if (cardno) {
                        cardkey += lastKey;
                        if (cardkey.length() > 9) {
                            cardkey = cardkey.substring(0,9);
                        }
                    }
                }
            }

            notifyObservers() ;            
        }
        else
        {
            if ( nextHandler != null )
                nextHandler.touch(x,y) ;
        }
    }

    /**
     *  Get Last Caller class name
     * @return class name
     */
    private static String getCallerClass() {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String clazzName = stackTrace[4].getClassName();

    return clazzName;}

    /**
     *  Get Last Key Pressed 
     * @return Last Key
     */
    public String lastKey() { 
        System.err.println( "Key Pressed: " + this.lastKey ) ;
        return this.lastKey ; 
    }

    /**
     * Get Key Number from (X,Y) Touch Coord's
     * @param  x [description]
     * @param  y [description]
     * @return   [description]
     */
    private String getKey( int x, int y )
    {
        int kx = 0, ky = 0 ;
        kx = x;
        ky = y-4 ;
        if ( kx==3 && ky ==4 )
            return "X" ;
        else if ( kx==2 && ky == 4 )
            return "0" ;
        else if ( kx==1 && ky ==4 )
            return " " ;
        else
            return Integer.toString(kx+3*(ky-1)) ;   
    }

    /*
    kx = 1, ky = 1  ==> 1
    kx = 1, ky = 2  ==> 4
    kx = 1, ky = 3  ==> 7

    kx = 2, ky = 1  ==> 2
    kx = 2, ky = 2  ==> 5
    kx = 2, ky = 3  ==> 8

    kx = 3, ky = 1  ==> 3
    kx = 3, ky = 2  ==> 6
    kx = 3, ky = 3  ==> 9

    n = kx + 3 * (ky-1)

    */

    /**
     * Set Next Touch Event Handler
     * @param next Event Handler */
    public void setNext( ITouchEventHandler next) { 
        nextHandler = next ;
    }

    /**
     * Get Key Pad Display
     * @return output
     */
    public String display()
    {
        //return " [1] [2] [3]\n [4] [5] [6]\n [7] [8] [9]\n [_] [0] [X]"  ;
        
        String output =  "  [1] [2] [3]\n" ;
               output += "  [4] [5] [6]\n" ;
               output += "  [7] [8] [9]\n" ;
               output += "  [_] [0] [X]" ;

        return output ;
    }

    /**
     * Add Sub Component (Not used)
     * @param c Display Component
     */
    public void addSubComponent( IDisplayComponent c ) 
    {
    }

    /**
     * Attach a Key Pad Observer
     * @param obj Observer
     */
    public void attach( IKeyPadObserver obj ) 
    {
        observers.add( obj ) ;
    }

    /**
     * Remove Key Pad Observer
     * @param obj Observer
     */
    public void removeObserver( IKeyPadObserver obj )
    {
        int i = observers.indexOf(obj) ;
        if ( i >= 0 )
            observers.remove(i) ;
    }

    /**
     * Notify all Observers of Update Event
     */
    public void notifyObservers( )
    {
        for (int i=0; i<observers.size(); i++)
        {
            IKeyPadObserver observer = observers.get(i) ;
            observer.keyEventUpdate( countPinDigits, lastKey, cardkey, cardkey1 ) ;
        }
    }

    /**
     * Set count
     * @param  count Integer
     */
    public void setCount(int count) {
        countPinDigits = count;
        this.lastKey = " ";
        notifyObservers();
    }

    /**
     * Set count
     * @param  ck String
     * @param  cc String
     */
    public void setCardDetails(String ck, String cc) {
        cardkey1 = ck;
        cardkey = cc;
        notifyObservers();
    }

}
