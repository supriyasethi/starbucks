/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** For Pin Digits State */
public class  FourPinDigits implements IPinState
{
    IPinStateMachine machine ;
    private String inputpin;

    /**
     * Constructor
     * @param  m Reference to State Machine
     */
    public FourPinDigits( IPinStateMachine m )
    {
        this.machine = m ;
    }

    /**
     * Backspace Event
     */
    public void backspace() {
        machine.setStateThreePinDigits(null) ;
    }

    /**
     * Digit Entry Event
     * @param digit Digit Value
     */
    public void number( String digit ) {
        if (inputpin.length() == 4) {
            System.err.println( "Digit: " + digit ) ;
            return ;
        }
        else {
            machine.setStateFivePinDigits(digit);
        }

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

    public void pinInfo(String pin) {
        inputpin = pin;
    }

}
