/*
 * Created on 11.Nis.2005
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.iztek.util.tracer.debug;

import javax.swing.text.JTextComponent;

/**
 * @author Fatih Algan
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SwingUIDebugger implements Debugger {

    private JTextComponent comp = null;
    private boolean debugOn = true;
    
    public SwingUIDebugger(JTextComponent comp) {
        this.comp = comp;
    }
    
    public SwingUIDebugger(JTextComponent comp, boolean debugOn) {
        this.comp = comp;
        this.debugOn = debugOn;
    }
    
    /* (non-Javadoc)
     * @see com.iztek.util.tracer.debug.Debug#println(java.lang.String)
     */
    public void println(String msg) {
        comp.setText(msg);
    }

    /* (non-Javadoc)
     * @see com.iztek.util.tracer.debug.Debug#println(java.lang.Throwable)
     */
    public void println(Throwable t) {
        comp.setText(t.getMessage());
    }

    /* (non-Javadoc)
     * @see com.iztek.util.tracer.debug.Debug#print(java.lang.String)
     */
    public void print(String msg) {
        comp.setText(msg);
    }

    /* (non-Javadoc)
     * @see com.iztek.util.tracer.debug.Debug#print(java.lang.Throwable)
     */
    public void print(Throwable t) {
        comp.setText(t.getMessage());
    }

    /**
     * @param debugOn The debugOn to set.
     */
    public void setDebugOn(boolean debugOn) {
        this.debugOn = debugOn;
    }
}
