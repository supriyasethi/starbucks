package starbucks;

/** Card code Screen Component */
public class CardCode implements ITouchEventHandler, IDisplayComponent, IKeyPadObserver
{

    ITouchEventHandler nextHandler ;
    private int count = 0;
    private String cardcode = "" ;

    /**Constructor */
    public CardCode() {
           }

    /**
     * Display "Echo Feedback" on keys entered so far
     * @return value
     */
    @Override
    public String display() {
        String value = "" ;
        String value1 = "";
        StringBuffer sb = new StringBuffer();
        if (cardcode.length() > 3) {
            value = "[" + cardcode.substring(0,3) + "]";
        } else {
        if ( (count == 0 ) || (cardcode.length() == 0))
        {
            value = "[]" ;}
        else {
         value = "[" + cardcode + "]"; }
         }
        int nameLen = cardcode.length();
        if (nameLen < 14) {
            int pad = (14 - nameLen) / 2;
            sb.append(padSpaces(pad));
        }
        sb.append(value);
        value1 = sb.toString();
        return value1;
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
        cardcode = key2;
        count = c ;
    }

    /**
     * Touch Event Ignored by Passcode
     * @param x Touch X
     * @param y Touch Y
     */
    @Override
    public void touch(int x, int y) {
        if (( y==4) || (y==1))
        {
            System.err.println( "Card code Touched at (" + x + ", " + y + ")" ) ;
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
