/**
 * 
 */
package de.binfalse.martin.jeopardy.game;

import java.awt.Color;
import java.util.Collections;
import java.util.Vector;

import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JPanel;

import de.binfalse.martin.jeopardy.x.MainWin;



// TODO: Auto-generated Javadoc
/**
 * The Class JeopardyCategory.
 *
 * @author Martin Scharm
 */
public class JeopardyCategory
	extends JPanel
{
	
	/** The cat name. */
	private javax.swing.JLabel		catName;
	
	/** The tasks. */
	private Vector<JeopardyTask>	tasks;
	
	
	/**
	 * Instantiates a new jeopardy category.
	 *
	 * @param name the name
	 */
	public JeopardyCategory (String name)
	{
		super ();
		tasks = new Vector<JeopardyTask> ();
		init (name);
		reRender ();
	}
	
	
	/**
	 * Sets the listener.
	 *
	 * @param al the new listener
	 */
	public void setListener (MainWin al)
	{
		for (int i = 0; i < tasks.size (); i++)
		{
			tasks.elementAt (i).setListener (al);
		}
	}
	
	
	/**
	 * Adds the task.
	 *
	 * @param t the t
	 */
	public void addTask (JeopardyTask t)
	{
		tasks.add (t);
		Collections.sort (tasks);
		reRender ();
	}
	
	
	/**
	 * Re render.
	 */
	private void reRender ()
	{
		
		javax.swing.GroupLayout cat1Layout = new javax.swing.GroupLayout (this);
		setLayout (cat1Layout);
		
		ParallelGroup hg = cat1Layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addComponent (catName,
			javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
		SequentialGroup vg = cat1Layout.createSequentialGroup ().addComponent (
			catName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, 30);
		
		for (int i = 0; i < tasks.size (); i++)
		{
			hg = hg.addComponent (tasks.elementAt (i),
				javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE);
			vg = vg.addComponent (tasks.elementAt (i),
				javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);
		}
		
		cat1Layout.setHorizontalGroup (cat1Layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			javax.swing.GroupLayout.Alignment.TRAILING,
			cat1Layout.createSequentialGroup ().addGroup (hg)));
		cat1Layout.setVerticalGroup (cat1Layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			javax.swing.GroupLayout.Alignment.TRAILING, vg));
	}
	
	
	/**
	 * Inits the.
	 *
	 * @param name the name
	 */
	private void init (String name)
	{
		this.setBackground (new Color (0, 74, 153));
		catName = new javax.swing.JLabel (name);
		
		catName.setHorizontalAlignment (javax.swing.SwingConstants.CENTER);
		catName.setForeground (Color.WHITE);
		catName.setFont (catName.getFont ().deriveFont (22.f));
	}
}
