/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/**
 * Key Pad Observer Interface
 */
public interface IKeyPadObserver
{
    /**
     * Key Event to Notify Observers 
     * @param numKeys Number of Digits So Far
     * @param key     Key/Digit Pressed
     * @param key1     Key/Digit Pressed
     * @param key2     Key/Digit Pressed
     */
    void keyEventUpdate( int numKeys, String key , String key1, String key2) ;
}
