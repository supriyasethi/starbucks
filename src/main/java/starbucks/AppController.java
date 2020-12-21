/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

/**
 * Main App Controller Class
 */
public class AppController implements IApp {

    private IScreen mycards ;
    private IScreen store ;
    private IScreen rewards ;
    private IScreen payments ;
    private IScreen settings;
    private IMenuCommand displayMyCards ;
    private IMenuCommand displayPayments ;
    private IMenuCommand displayRewards ;
    private IMenuCommand doStore ;
    private IMenuCommand displaySettings;
    private IFrame frame ;

    public AppController() {
        mycards = new MyCards() ;
        store = new Store() ;
        rewards = new Rewards() ;
        payments = new Payments() ;
        settings = new Settings() ;
        frame = new Frame( mycards ) ;

        // setup command pattern
        displayMyCards  = new MenuCommand() ;
        displayPayments = new MenuCommand() ;
        displayRewards  = new MenuCommand() ;
        doStore         = new MenuCommand() ;
        displaySettings = new MenuCommand() ;
        displayMyCards.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( mycards ) ;
              }
        }
        ) ;
        displayPayments.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( payments ) ;
              }
        }
        ) ;
        displayRewards.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( rewards ) ;
              }
        }
        ) ;
        doStore.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen( store ) ; }
        }
        );
        displaySettings.setReceiver(
          new IMenuReceiver() {
              /** Command Action */
              public void doAction() {
                  frame.setCurrentScreen(settings);
              }
          }
        ) ;

        frame.setMenuItem ( "A", displayMyCards ) ;
        frame.setMenuItem ( "B", displayPayments ) ;
        frame.setMenuItem ( "C", displayRewards ) ;
        frame.setMenuItem ( "D", doStore ) ;
        frame.setMenuItem ( "E", displaySettings ) ;
        settings.setFrame(frame);
        //settings.setCardScrn(addcard) ;
        settings.setScreen(mycards);
        mycards.setFrame(frame);
        mycards.setScreen(mycards);
    }


    /**
      * Switch to Landscape Mode
      */
    public void landscape() {
        frame.landscape() ;
    }

    /**
     * Switch to Portait Mode
     */
    public void portrait() {
        frame.portrait() ;
    }

    /**
     * Send In Touch Events
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y) {
        //System.err.println("In AppController touch");
        frame.touch(x, y) ;
    }

    /**
     * Display Current Screen
     */
    public void display() {

        //System.err.println("In AppController display");
        frame.display() ;
    }

    /**
     * Execute Menu Bar Command
     * @param c Menu Bar Option (A, B, C, D or E)
     */
    public void execute( String c ) {
        //System.err.println("In AppController execute");
        frame.cmd( c ) ;
    }

    /**
     * Navigate to Previous Screen
     */
    public void prev() {
        //System.err.println("In AppController prev");
        frame.previousScreen() ;
    }

    /**
     * Navigate to Next Screen
     */
    public void next() {
        //System.err.println("In AppController next");
        frame.nextScreen() ;
    }

    /**
     * Get Current Screen Name
     * @return Screen Name
     */
    public String screen() {
        //System.err.println("In AppController screen");
        return frame.screen() ;
    }

    /**
     * Get Current Screen Contents
     * @return Current Screen Contents
     */
    public String screenContents() {
        //System.err.println("In AppController screencontents");
        return frame.contents() ;
    }

}
