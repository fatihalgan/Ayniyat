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
public class Debug {
    
    private static Debug debug = new Debug();
    private Debugger debugger = new StandartOutDebugger();
    
    private Debug() {}
    
    public static Debugger instance() {
        return debug.debugger;
    }
}
