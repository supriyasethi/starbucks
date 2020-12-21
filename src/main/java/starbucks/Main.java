/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import java.io.Console;
import java.util.Scanner;

/**
 * Main Entry Point.
 */
final class Main {

    /**
     * Prevent Construction.
     */
    private Main() {
        // Utility Class
        return ;
    }
    //String l ;
    /**
     * Main App Entry Point.
     * @param args No args expected.
     */
    public static void main(final String[] args) {
        //System.err.println( "Args: " + args ) ;
        //Device d = Device.getNewInstance("1") ;
        Device d = Device.getInstance() ;
        IApp app = (IApp) d ;
        Console c = System.console();
        //Scanner sc = new Scanner(System.in);
        String msg = "" ;
        for (;;) {
            System.out.print("\033[H\033[2J") ; // clear the screen
            System.out.flush() ;
            System.out.println(app.screenContents()) ;
            System.out.println( msg ) ;
            System.out.print("=> ") ;
            String ch = c.readLine() ;       // get user command
            //String ch = sc.nextLine();
            String cmd = ch.toLowerCase() ;  // convert to lower case
            cmd = cmd.replaceAll("\\s","") ; // remove all whitespaces
            /* process commands */
            msg = cmd ;
            if ( cmd.startsWith("touch") ) {
                String parms = cmd.replaceFirst("touch", "") ;
                parms = parms.substring(1) ;
                parms = parms.substring(0, parms.length() - 1) ;
                String[] values = parms.split(",") ;
                //System.err.println( "Value: " + values ) ;
                String x = values[0] ;
                String y = values[1] ;
                msg = "touch: x="+x + " y="+y ;
                System.err.println("Inside main KeyPad Touched at (" + x + ", " + y + ")");
                app.touch( Integer.parseInt(x), Integer.parseInt(y) ) ;
            } else if ( cmd.equals("a") || cmd.equals("b") 
                        || cmd.equals("c") || cmd.equals("d")
                        || cmd.equals("e")
                ) {
                String selection = cmd.toUpperCase() ;
                msg = "selected: " + selection ;
                app.execute( selection ) ;

            } else if ( cmd.startsWith("prev") ) {
                msg = "cmd: previous" ;
                app.prev() ;
            } else if ( cmd.startsWith("next") ) {
                msg = "cmd: next" ;
                app.next() ;
            } else if (cmd.equalsIgnoreCase( "portrait" )) {
                System.err.println("Portrait mode selected");
                app.portrait() ;
            } else if (cmd.equalsIgnoreCase( "landscape" )) {
                System.err.println("Landscape mode selected");
                app.landscape() ;
            } else if ( cmd.startsWith("login") ) {
                app.touch(1,5) ;  // 1
                app.touch(2,5) ;  // 2
                app.touch(3,5) ;  // 3
                app.touch(1,6) ;  // 4
            } else if ( cmd.startsWith("addcard") ) {
                app.execute("E");  // Settings
                app.touch(1, 1); // Add New Card
            } else if ( cmd.startsWith("payment") ) {
                app.touch(3,3) ; // Add New Card
            } else if ( cmd.startsWith("x") ) {
                app.touch(3,8) ; // Add New Card
            } else if ( cmd.startsWith("cardnum") ) {
                app.touch(1,2) ;  // Settings
                app.touch(1, 5);
                app.touch(2, 5);
                app.touch(3, 5);
                app.touch(1, 6);
                app.touch(2, 6);
                app.touch(3, 6);
                app.touch(1, 7);
                app.touch(2, 7);
                app.touch(3, 7);
            } else if ( cmd.startsWith("cardcode") ) {
                app.touch(2,3) ;  // Settings
                app.touch(1, 5);
                app.touch(2, 5);
                app.touch(3, 5);
            } else if ( cmd.startsWith("1") ) {
                app.touch(1,5) ;  // 1
            } else if ( cmd.startsWith("2") ) {
                app.touch(2, 5);  // 2
            } else if ( cmd.startsWith("3") ) {
                app.touch(3, 5);  // 3
            } else if ( cmd.startsWith("4") ) {
                app.touch(1, 6);  // 4
            } else if ( cmd.startsWith("5") ) {
                app.touch(2, 6);  // 5
            } else if ( cmd.startsWith("6") ) {
                app.touch(3, 6);  // 6
            } else if ( cmd.startsWith("7") ) {
                app.touch(1, 7);  // 7
            } else if ( cmd.startsWith("8") ) {
                app.touch(2, 7);  // 8
            } else if ( cmd.startsWith("9") ) {
                app.touch(3, 7);  // 9
            } else if ( cmd.startsWith("0") ) {
                app.touch(2, 8);  // 0
            } else {
                msg = "" ;  
            }
        }
    }
}

