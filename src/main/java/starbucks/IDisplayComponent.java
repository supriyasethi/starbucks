/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/** Display Component Interface */
public interface IDisplayComponent
{
    /**
     * Return Display Component Contents
     * @return Screen Display */
    String display() ;

    /**
     * Add A Child Component
     * @param c Child Component
     */
    void addSubComponent( IDisplayComponent c ) ;
}
