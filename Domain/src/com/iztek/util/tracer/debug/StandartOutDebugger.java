/*
 * Created on 11.Nis.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.util.tracer.debug;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StandartOutDebugger implements Debugger {

    private boolean debugOn = true;
    
    public StandartOutDebugger() {
        super();
    }
    public StandartOutDebugger(boolean debugOn) {
        super();
        this.debugOn = debugOn;
    }
    /**
     * @param debugOn The debugOn to set.
     */
    public void setDebugOn(boolean debugOn) {
        this.debugOn = debugOn;
    }
    /* (non-Javadoc)
     * @see com.iztek.util.tracer.debug.Debug#println(java.lang.String)
     */
    public void println(String msg) {
        if(debugOn) System.out.println(msg);
    }

    /* (non-Javadoc)
     * @see com.iztek.util.tracer.debug.Debug#println(java.lang.Throwable)
     */
    public void println(Throwable t) {
        if(debugOn) System.out.println(t.getMessage());
    }

    /* (non-Javadoc)
     * @see com.iztek.util.tracer.debug.Debug#print(java.lang.String)
     */
    public void print(String msg) {
        if(debugOn) System.out.print(msg);
    }

    /* (non-Javadoc)
     * @see com.iztek.util.tracer.debug.Debug#print(java.lang.Throwable)
     */
    public void print(Throwable t) {
        if(debugOn) System.out.print(t.getMessage());
    }

}
