/**
 * 
 */
package de.binfalse.martin.jeopardy.game;

import javax.swing.JPanel;

import de.binfalse.martin.jeopardy.x.MainWin;



// TODO: Auto-generated Javadoc
/**
 * The Class JeopardyTaskPanel.
 *
 * @author Martin Scharm
 */
public abstract class JeopardyTaskPanel
	extends JPanel
{
	
	// public abstract JPanel getPanel ();
	/**
	 * Answer from.
	 *
	 * @param p the p
	 */
	public abstract void answerFrom (JeopardyPlayer p);
	
	
	/**
	 * Adds the listener.
	 *
	 * @param win the win
	 */
	public abstract void addListener (MainWin win);
	
}
