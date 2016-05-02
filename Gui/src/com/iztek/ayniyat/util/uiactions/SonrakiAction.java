package com.iztek.ayniyat.util.uiactions;

import com.iztek.ayniyat.panel.ITanimlarPanel;

public class SonrakiAction implements Command {
/**
 * @author fusun
 */
	public ITanimlarPanel activePanel;
	
	public SonrakiAction(ITanimlarPanel panel) {
		this.activePanel=panel;
	}

	public void execute() {
		activePanel.sonrakiAction();
	}
}
