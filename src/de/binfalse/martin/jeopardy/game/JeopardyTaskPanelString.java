/**
 * 
 */
package de.binfalse.martin.jeopardy.game;

import java.awt.Color;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLEditorKit;

import de.binfalse.martin.jeopardy.x.MainWin;



// TODO: Auto-generated Javadoc
/**
 * The Class JeopardyTaskPanelString.
 *
 * @author Martin Scharm
 */
public class JeopardyTaskPanelString
	extends JeopardyTaskPanel
{
	
	/** The answer. */
	private String			answer;
	
	/** The pane. */
	private JEditorPane	pane;
	
	
	/**
	 * Instantiates a new jeopardy task panel string.
	 *
	 * @param answer the answer
	 */
	public JeopardyTaskPanelString (String answer)
	{
		super ();
		this.answer = answer;
		init ();
	}
	
	
	/**
	 * Inits the.
	 */
	private void init ()
	{
		setBackground (new Color (0, 74, 153));
		setForeground (Color.WHITE);
		pane = new JEditorPane ();
		pane.setEditable (false);
		// HTMLDocument htmlDoc = new HTMLDocument();
		HTMLEditorKit editorKit = new HTMLEditorKit ();
		pane.setEditorKit (editorKit);
		pane.setBackground (new Color (0, 74, 153));
		/*
		 * pane.setForeground (Color.WHITE);
		 * pane.setFont (this.getFont ().deriveFont (28.f));
		 */
		/*
		 * pane.setSize(size);
		 * pane.setMinimumSize(size);
		 * pane.setMaximumSize(size);
		 */
		pane.setOpaque (true);
		pane.setText ("<center><font color='#ffffff' size='28'>" + answer
			+ "</font></center>");
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (this);
		this.setLayout (layout);
		layout.setHorizontalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			layout.createSequentialGroup ().addContainerGap (10, Short.MAX_VALUE)
				.addComponent (pane).addContainerGap (10, Short.MAX_VALUE)));
		layout.setVerticalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			layout.createSequentialGroup ().addContainerGap (10, Short.MAX_VALUE)
				.addComponent (pane).addContainerGap (10, Short.MAX_VALUE)));
	}
	
	
	/* (non-Javadoc)
	 * @see de.binfalse.martin.jeopardy.game.JeopardyTaskPanel#answerFrom(de.binfalse.martin.jeopardy.game.JeopardyPlayer)
	 */
	@Override
	public void answerFrom (JeopardyPlayer p)
	{
		/*
		 * JOptionPane.showMessageDialog(this,
		 * "It's " + p.getPlayerName () + "'s turn!",
		 * "Answering",
		 * JOptionPane.ERROR_MESSAGE);
		 */
	}
	
	
	/* (non-Javadoc)
	 * @see de.binfalse.martin.jeopardy.game.JeopardyTaskPanel#addListener(de.binfalse.martin.jeopardy.x.MainWin)
	 */
	@Override
	public void addListener (MainWin win)
	{
		pane.addMouseListener (win);
	}
	
}
