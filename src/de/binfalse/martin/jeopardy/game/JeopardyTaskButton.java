/**
 * 
 */
package de.binfalse.martin.jeopardy.game;

import javax.swing.JButton;



// TODO: Auto-generated Javadoc
/**
 * The Class JeopardyTaskButton.
 *
 * @author Martin Scharm
 */
public class JeopardyTaskButton
	extends JButton
{
	
	/** The task. */
	private JeopardyTask	task;
	
	
	/**
	 * Instantiates a new jeopardy task button.
	 *
	 * @param n the n
	 * @param task the task
	 */
	public JeopardyTaskButton (String n, JeopardyTask task)
	{
		super (n);
		this.task = task;
	}
	
	
	/**
	 * Gets the task.
	 *
	 * @return the task
	 */
	public JeopardyTask getTask ()
	{
		return task;
	}
}
