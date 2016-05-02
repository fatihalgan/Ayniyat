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
public interface Debugger {
    public void println(String msg);
    public void println(Throwable t);
    public void print(String msg);
    public void print(Throwable t);
}
