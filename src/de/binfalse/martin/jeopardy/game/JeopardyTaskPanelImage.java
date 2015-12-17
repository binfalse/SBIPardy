/**
 * 
 */
package de.binfalse.martin.jeopardy.game;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import de.binfalse.martin.jeopardy.x.MainWin;



// TODO: Auto-generated Javadoc
/**
 * The Class JeopardyTaskPanelImage.
 *
 * @author Martin Scharm
 */
public class JeopardyTaskPanelImage
	extends JeopardyTaskPanel
{
	
	/** The slash. */
	private String	SLASH	= System.getProperty ("file.separator");
	
	/** The answer. */
	private String	answer;
	
	/** The label. */
	private JLabel	label;
	
	
	/**
	 * Instantiates a new jeopardy task panel image.
	 *
	 * @param answer the answer
	 * @param dir the dir
	 */
	public JeopardyTaskPanelImage (String answer, String dir)
	{
		super ();
		this.answer = dir + SLASH + answer;
		init ();
	}
	
	
	/**
	 * Inits the.
	 */
	private void init ()
	{
		setBackground (new Color (0, 74, 153));
		setForeground (Color.WHITE);
		
		ImageIcon image = new ImageIcon (answer);
		label = new JLabel ("", image, JLabel.CENTER);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (this);
		this.setLayout (layout);
		layout.setHorizontalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			layout.createSequentialGroup ().addContainerGap (10, Short.MAX_VALUE)
				.addComponent (label).addContainerGap (10, Short.MAX_VALUE)));
		layout.setVerticalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			layout.createSequentialGroup ().addContainerGap (10, Short.MAX_VALUE)
				.addComponent (label).addContainerGap (10, Short.MAX_VALUE)));
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
		// TODO Auto-generated method stub
		label.addMouseListener (win);
	}
	
}
