package starbucks;

/** CardNum Screen Component */
public class CardNum implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver
{
    ITouchEventHandler nextHandler ;
    private int count = 0;
    private String cardkey ="";

    /**Constructor */
    public CardNum() {
    }
    /**
     * Display "Echo Feedback" on Keys entered so far
     * @return value
     */
    @Override
    public String display() {
        String value = "" ;
        String value1 = "";
        StringBuffer sb = new StringBuffer();
        if (cardkey.length() > 9) {
            value = "[" + cardkey.substring(0,9) + "]";
        } else {
            if (count == 0) {
                value = "[]";
            } else {
                value = "[" + cardkey + "]";
            }
        }
        int nameLen = value.length();
        if (nameLen < 16) {
            int pad = (16 - nameLen) / 2;
            sb.append(padSpaces(pad));
        }
        sb.append(value);
        value1 = sb.toString();
        //notifyObserver();
        return value1;
        //return String.format("%n %8s",value);
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
    /**
     * Add Sub Component (Not used)
     * @param c Sub Component to Add
     */
    @Override
    public void addSubComponent(IDisplayComponent c) {

    }

    /**
     * Key Event Update
     * @param c   Count of Keys So Far
     * @param key Last key Pressed
     * @param key1 cardNum key Pressed
     * @param key2 cardCode key Pressed
     */
    @Override
    public void keyEventUpdate(int c, String key, String key1, String key2) {
        System.err.println( "Key: " + key ) ;
        cardkey = key1;
        count = c ;
    }

    /**
     * Touch Event Ignored by Passcode
     * @param x Touch X
     * @param y Touch Y
     */
    @Override
    public void touch(int x, int y) {

        if (( y==4) || (y==1) || (x == 5))
            {
                System.err.println( "Card number Touched at (" + x + ", " + y + ")" ) ;
            }
            else
            {
                if ( nextHandler != null )
                    nextHandler.touch(x,y) ;
            }
    }

    /**
     * Set Next Touch Handler
     * @param next Touch Event Handler
     */
    @Override
    public void setNext(ITouchEventHandler next) {
        nextHandler = next ;
    }

}
