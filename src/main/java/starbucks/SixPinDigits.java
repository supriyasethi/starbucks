package starbucks;

/** Six Pin Digits State */
public class SixPinDigits implements IPinState {

    IPinStateMachine machine ;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */
    public SixPinDigits( IPinStateMachine m )
    {
        this.machine = m ;
    }

    /**
     * Backspace Event
     */
    public void backspace() {
        machine.setStateFivePinDigits(null) ;
    }

    /**
     * Digit Entry Event
     * @param digit Digit Value
     */
    public void number( String digit ) {
        System.err.println( "Digit: " + digit ) ;
        return ;
    }

    /**
     * Valid Pin Event
     */
    public void validPin() {
        return ;
    }

    /**
     * Invalid Pin Event
     */
    public void invalidPin() {
        machine.setStateNoPinDigits() ;
    }

}
