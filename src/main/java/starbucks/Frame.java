/* (c) Copyright 2018 Paul Nguyen. All Rights Reserved */

package starbucks ;

import static starbucks.Device.landscape_screen_width;

/**
 * Frame Class -- Container for Screens
 */
public class Frame implements IFrame
{
    private IScreen current ;
    private IMenuInvoker menuA = new MenuOption() ;
    private IMenuInvoker menuB = new MenuOption() ;
    private IMenuInvoker menuC = new MenuOption() ;
    private IMenuInvoker menuD = new MenuOption() ;
    private IMenuInvoker menuE = new MenuOption() ;
    private IOrientationStrategy portraitStrategy ;
    private IOrientationStrategy landscapeStrategy ;
    private IOrientationStrategy currentStrategy ;
    //private String name = "";

    public Frame() {

    }

    /**
     * Return Screen Name
     * @return Screen Name
     */
    public String screen() {
       return current.name();
    }

    /** Switch to Landscape Strategy */
    public void landscape() {
        System.err.println("In landscape mode");
        String nm = current.name().trim();
        System.out.println(nm);
        if ((nm.equals("Payments")) || (nm.equals("Rewards")) || (nm.equals("Find Store")) || (nm.equals("Settings"))){
            currentStrategy = portraitStrategy;
        } else {
            currentStrategy = landscapeStrategy;
        }
    }

    /** Switch to Portrait Strategy */
    public void portrait()  {
        System.err.println("In portrait mode");
        currentStrategy = portraitStrategy ; }

    /** Nav to Previous Screen */
    public void previousScreen() {
        //System.err.println("In Frame prev");
        if ( current != null )
            current.prev() ;
    }

    /** Nav to Next Screen */
    public void nextScreen() {
        //System.err.println("In Frame next");
        if ( current != null )
            current.next() ;
    }



   /**
     * Helper Debug Dump to STDERR
     * @param str Lines to print
     */
    private void dumpLines(String str) {
          String[] lines = str.split("\r\n|\r|\n");
          for ( int i = 0; i<lines.length; i++ ) {
            System.err.println( i + ": " + lines[i] ) ;
          }
    }

    /**
     * Helper:  Count lines in a String 
     * @param  str Lines to Count
     * @return     Number of Lines Counted
     */
    private int countLines(String str){

        /*
          // this approach doesn't work in cases: "\n\n"
          String lines = str ;
          String[] split = lines.split("\r\n|\r|\n") ;
          return split.length ;
        */

        if (str == null || str.isEmpty()) {
                return 0;
            }

        int lines = 0;
        int pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
                lines++;
        }

        return lines ;
    }

    /** 
     * Helper:  Pad lines for a Screen 
     * @param  num Lines to Padd
     * @return     Padded Lines
     */
    private String padLines(int num) {
        String lines = "" ;
        StringBuffer sb = new StringBuffer();
        for ( int i = 0; i<num; i++ ) {
            System.err.print(".") ;
            sb.append("\n");
            //lines += "\n" ;
        }
        lines = sb.toString();
        System.err.println("") ;
        return lines ;
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

    /** Constructor */
    public Frame(IScreen initial) {
        System.err.println("In Frame ");
        current = initial;

        portraitStrategy = new IOrientationStrategy() {

            /**
             * Display Screen Contents
             *
             * @param s Reference to Screen
             */
            public void display(IScreen s) {
                System.out.println(contents(s));
            }

            /**
             * Return String / Lines for Frame and Screen
             *
             * @param s [description]
             * @return [description]
             */
            public String contents(IScreen s) {
                System.err.println("Inside portrait");
                String out = "";
                out += "===============\n";
                int nameLen = s.name().length();
                if (nameLen < 14) {
                    int pad = (14 - nameLen) / 2;
                    out += padSpaces(pad);
                }
                out += s.name() + "\n";
                out += "===============\n";
                String screen = s.display() + "\n";
                int cnt1 = countLines(screen);
                int pad1 = (10 - cnt1) / 2;
                //System.err.println( "cnt1: " + cnt1 ) ;
                //System.err.println( "pad1: " + pad1 ) ;
                out += padLines(pad1);
                out += screen;
                //dumpLines( out ) ;
                int cnt2 = countLines(out);
                int pad2 = 13 - cnt2;
                //System.err.println( "cnt2: " + cnt2 ) ;                
                //System.err.println( "pad2: " + pad2 ) ;
                //dumpLines( out ) ;
                String padlines = padLines(pad2);
                out += padlines;
                out += "===============\n";
                out += "[A][B][C][D][E]\n";
                dumpLines(out);
                return out;
            }

            /**
             * Select Command A
             */
            public void selectA() {
                System.err.println("Key pressed at MyCards") ;
                menuA.invoke();
            }

            /**
             * Select Command B
             */
            public void selectB() {
                System.err.println("Key pressed at Payments") ;
                menuB.invoke();
            }

            /**
             * Select Command C
             */
            public void selectC() {
                System.err.println("Key pressed at MyRewards") ;
                menuC.invoke();
            }

            /**
             * Select Command D
             */
            public void selectD() {
                System.err.println("Key pressed at FindStores") ;
                menuD.invoke();
            }

            /**
             * Select Command E
             */
            public void selectE() {
                System.err.println("Key pressed at Settings") ;
                menuE.invoke();
            }

        }
    ;

        landscapeStrategy = new IOrientationStrategy() 
        {
            /**
             * Display Screen Contents
             * @param s Reference to Screen
             */
            public void display(IScreen s)
            {
                System.out.println( contents(s) ) ;
            }         

           /**
             * Display Contents of Frame + Screen 
             * @param  s Screen to Display
             * @return   Contents for Screen
             */
            public String contents(IScreen s) 
            { 
                String out = "" ;
                out += "================================\n" ;
                int nameLen = s.name().length();
                if (nameLen < 30) {
                    int pad = (30 - nameLen) / 2;
                    out += padSpaces(pad);
                }
                out += s.name() + "\n";
                //out += "  " + s.name() + "  \n" ;
                out += "================================\n" ;
                out += s.display() + "\n\n\n"  ;
                out += "================================\n\n\n" ;
                dumpLines( out ) ;
                return out ;
            }

             /** Don't Respond in Landscaope Mode */
            public void selectA() {  }

            /** Don't Respond in Landscaope Mode */
            public void selectB() {  }

            /** Don't Respond in Landscaope Mode */
            public void selectC() {  }

            /** Don't Respond in Landscaope Mode */
            public void selectD() {  }

            /** Don't Respond in Landscaope Mode */
            public void selectE() {  }

        } ;

        /* set default layout strategy */
        currentStrategy = portraitStrategy ;
    }

    /**
     * Change the Current Screen
     * @param s Screen Object
     */
    public void setCurrentScreen( IScreen s )
    {
        current = s ;
    }

    /**
     * Configure Selections for Command Pattern 
     * @param slot A, B, ... E
     * @param c    Command Object
     */
    public void setMenuItem( String slot, IMenuCommand c )
    {
        if ( "A".equals(slot) ) { menuA.setCommand(c) ;  }
        if ( "B".equals(slot) ) { menuB.setCommand(c) ; }
        if ( "C".equals(slot) ) { menuC.setCommand(c) ; }
        if ( "D".equals(slot) ) { menuD.setCommand(c) ; } 
        if ( "E".equals(slot) ) { menuE.setCommand(c) ; }

    }

    /** 
     * Send Touch Event
     * @param x X Coord
     * @param y Y Coord
     */
    public void touch(int x, int y)
    {
        //System.err.println("In Frame touch");
        if (currentStrategy == portraitStrategy) {
            if (current != null)
                current.touch(x, y);
        }
    }

    /**
     * Get Contents of the Frame + Screen 
     * @return Frame + Screen Contents
     */
    public String contents() 
    {
        //System.err.println("In Frame contents");
        if ( current != null )
        {
            return currentStrategy.contents( current ) ; 
        } 
        else 
        {
            return "" ;
        }
    }

    /** Display Contents of Frame + Screen */
    public void display()
    {
        //System.err.println("In Frame display");
        if ( current != null )
        {
            currentStrategy.display( current ) ;
        }
    }

    /** Get the current screen Strategymode
     * @return String
     */
    public String getStrategy() {
        if (currentStrategy == portraitStrategy) {
            return "portrait"; }
        else { return "landscape"; }
    }


    /**
     *  Execute a Command
     * @param c Command
       */
    public void cmd( String c )
    {
        if ( "A".equals(c) ) { selectA() ; }
        if ( "B".equals(c) ) { selectB() ; }
        if ( "C".equals(c) ) { selectC() ; }
        if ( "D".equals(c) ) { selectD() ; }        
        if ( "E".equals(c) ) { selectE() ; }

    }

    /** Select Command A */
    public void selectA() { currentStrategy.selectA() ;  }

    /** Select Command B */
    public void selectB() { currentStrategy.selectB() ;  }

    /** Select Command C */
    public void selectC() { currentStrategy.selectC() ;  }

    /** Select Command D */
    public void selectD() { currentStrategy.selectD() ;  }

    /** Select Command E */
    public void selectE() { currentStrategy.selectE() ;  }



}
