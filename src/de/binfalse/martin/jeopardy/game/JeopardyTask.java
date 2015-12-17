/**
 * 
 */
package de.binfalse.martin.jeopardy.game;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLEditorKit;

import de.binfalse.martin.jeopardy.x.MainWin;



// TODO: Auto-generated Javadoc
/**
 * The Class JeopardyTask.
 *
 * @author Martin Scharm
 */
public class JeopardyTask
	extends JPanel
	implements Comparable<JeopardyTask>
{
	
	/** The task panel. */
	private JeopardyTaskPanel		taskPanel;
	
	/** The button. */
	private JeopardyTaskButton	button;
	
	/** The result. */
	private JEditorPane					result;
	
	/** The score. */
	private int									score;
	
	/** The answer. */
	private String							answer;
	
	
	/**
	 * Instantiates a new jeopardy task.
	 *
	 * @param score the score
	 * @param type the type
	 * @param answer the answer
	 * @param dir the dir
	 */
	public JeopardyTask (String score, String type, String answer, String dir)
	{
		super ();
		this.setBackground (new Color (0, 74, 153));
		// this.setForeground (Color.WHITE);
		// this.setFont (this.getFont ().deriveFont (22.f));
		
		this.answer = answer;
		this.score = Integer.parseInt (score);
		solved = new Vector<String> ();
		init ();
		if (type.equals ("string"))
			taskPanel = new JeopardyTaskPanelString (answer);
		else if (type.equals ("image"))
			taskPanel = new JeopardyTaskPanelImage (answer, dir);
		else if (type.equals ("sound"))
			taskPanel = new JeopardyTaskPanelSound (answer, dir);
		else
		{
			System.out.println ("don't know type " + type);
			System.exit (1);
		}
	}
	
	
	/**
	 * Answer from.
	 *
	 * @param p the p
	 */
	public void answerFrom (JeopardyPlayer p)
	{
		taskPanel.answerFrom (p);
	}
	
	
	/**
	 * Sets the listener.
	 *
	 * @param al the new listener
	 */
	public void setListener (MainWin al)
	{
		button.addActionListener (al);
		taskPanel.addKeyListener (al);
		taskPanel.addMouseListener (al);
	}
	
	
	/**
	 * Inits the.
	 */
	private void init ()
	{
		
		button = new JeopardyTaskButton ("" + score, this);
		button.setBackground (new Color (0, 74, 153));
		button.setForeground (Color.WHITE);
		button.setFont (this.getFont ().deriveFont (22.f));
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (this);
		this.setLayout (layout);
		layout.setHorizontalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addComponent (button,
			javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE));
		layout.setVerticalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addComponent (button,
			javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE));
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo (JeopardyTask jt)
	{
		if (score > jt.score)
			return 1;
		if (score < jt.score)
			return -1;
		return 0;
	}
	
	/** The solved. */
	private Vector<String>	solved;
	
	
	/*
	 * private void reText ()
	 * {
	 * if (solved.size () < 1)
	 * {
	 * this.setFont (this.getFont ().deriveFont (22.f));
	 * this.setText ("" + score);
	 * }
	 * else
	 * {
	 * String text = "";
	 * for (int i = 0; i < solved.size (); i++)
	 * text += solved.elementAt (i) + "\n";
	 * this.setFont (this.getFont ().deriveFont (10.f));
	 * this.setText (text);
	 * }
	 * }
	 */
	
	/**
	 * Sets the solved.
	 */
	public void setSolved ()
	{
		this.removeAll ();
		
		setBorder (javax.swing.BorderFactory.createTitledBorder (null, "" + score,
			javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
			javax.swing.border.TitledBorder.DEFAULT_POSITION, null,
			java.awt.Color.YELLOW));
		
		result = new JEditorPane ();
		result.setEditable (false);
		HTMLEditorKit editorKit = new HTMLEditorKit ();
		result.setEditorKit (editorKit);
		result.setBackground (new Color (0, 74, 153));
		result.setOpaque (true);
		
		String text = "";
		for (int i = 0; i < solved.size (); i++)
			text += "" + solved.elementAt (i) + "&nbsp;&nbsp;&nbsp;";
		
		result.setText ("<center><font size='10px'>" + text + "</font></center>");
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (this);
		this.setLayout (layout);
		layout.setHorizontalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addComponent (result,
			javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE));
		layout.setVerticalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addComponent (result,
			javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE));
	}
	
	
	/**
	 * Sets the closed.
	 */
	public void setClosed ()
	{
		solved.add ("<font color='white' size='10px'><b>closed</b></font> ");
	}
	
	
	/**
	 * Sets the correct.
	 *
	 * @param name the new correct
	 */
	public void setCorrect (String name)
	{
		solved.add ("<font color='yellow' size='10px'>+" + name + "</font> ");
	}
	
	
	/**
	 * Sets the in correct.
	 *
	 * @param name the new in correct
	 */
	public void setInCorrect (String name)
	{
		solved.add ("<font color='red' size='10px'>-" + name + "</font> ");
	}
	
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints ()
	{
		return score;
	}
	
	
	/**
	 * Gets the answer.
	 *
	 * @return the answer
	 */
	public JPanel getAnswer ()
	{
		return taskPanel;
		/*
		 * JPanel panel = new JPanel ();
		 * panel.setBackground (new Color (0, 74, 153));
		 * panel.setForeground (Color.WHITE);
		 * JEditorPane pane = new JEditorPane();
		 * pane.setEditable(false);
		 * //HTMLDocument htmlDoc = new HTMLDocument();
		 * HTMLEditorKit editorKit = new HTMLEditorKit();
		 * pane.setEditorKit(editorKit);
		 * pane.setBackground (new Color (0, 74, 153));
		 * pane.setForeground (Color.WHITE);
		 * pane.setFont (this.getFont ().deriveFont (28.f));
		 * pane.setSize(size);
		 * pane.setMinimumSize(size);
		 * pane.setMaximumSize(size);
		 * pane.setOpaque(true);
		 * pane.setText("<center><font color='#ffffff' size='28'>"+answer+
		 * "</font></center>");
		 * 
		 * javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
		 * panel.setLayout(layout);
		 * layout.setHorizontalGroup(
		 * layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		 * .addGroup(layout.createSequentialGroup()
		 * .addContainerGap(10, Short.MAX_VALUE)
		 * .addComponent(pane)
		 * .addContainerGap(10, Short.MAX_VALUE))
		 * );
		 * layout.setVerticalGroup(
		 * layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		 * .addGroup(layout.createSequentialGroup()
		 * .addContainerGap(10, Short.MAX_VALUE)
		 * .addComponent(pane)
		 * .addContainerGap(10, Short.MAX_VALUE))
		 * );
		 * return panel;
		 */
	}
	
}
