/**
 * 
 */
package de.binfalse.martin.jeopardy.x;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

import de.binfalse.martin.jeopardy.game.JeopardyCategory;
import de.binfalse.martin.jeopardy.net.Server;



// TODO: Auto-generated Javadoc
/**
 * The Class StartWindow.
 *
 * @author Martin Scharm
 */
public class StartWindow
	extends JFrame
{
	
	/** The j label1. */
	private javax.swing.JLabel			jLabel1;
	
	/** The j scroll pane1. */
	private javax.swing.JScrollPane	jScrollPane1;
	
	/** The j text area1. */
	private javax.swing.JTextArea		jTextArea1;
	
	/** The start. */
	private JButton									start;
	
	
	/**
	 * Adds the msg.
	 *
	 * @param s the s
	 */
	public void addMsg (String s)
	{
		jTextArea1.append (s + "\n");
		if (this.s.getPlayers ().size () > 1)
			start.setEnabled (true);
		else
			start.setEnabled (false);
	}
	
	/** The cats. */
	private Vector<JeopardyCategory>	cats;
	
	
	/**
	 * Instantiates a new start window.
	 *
	 * @param cats the cats
	 */
	public StartWindow (Vector<JeopardyCategory> cats)
	{
		init ();
		this.cats = cats;
	}
	
	/** The s. */
	private Server	s;
	
	
	/**
	 * Sets the server.
	 *
	 * @param s the new server
	 */
	public void setServer (Server s)
	{
		this.s = s;
	}
	
	
	/**
	 * Start game.
	 */
	private void startGame ()
	{
		s.setClosed ();
		dispose ();
		java.awt.EventQueue.invokeLater (new Runnable ()
		{
			
			public void run ()
			{
				MainWin mw = new MainWin (cats, s.getPlayers (), s);
				mw.setVisible (true);
				s.setMainWin (mw);
			}
		});
	}
	
	
	/**
	 * Inits the.
	 */
	private void init ()
	{
		
		jLabel1 = new javax.swing.JLabel ();
		jScrollPane1 = new javax.swing.JScrollPane ();
		jTextArea1 = new javax.swing.JTextArea ();
		
		setDefaultCloseOperation (javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.getContentPane ().setBackground (new Color (0, 74, 153));
		
		jLabel1.setText ("SBIpardy!");
		jLabel1.setHorizontalAlignment (javax.swing.SwingConstants.CENTER);
		jLabel1.setForeground (Color.WHITE);
		jLabel1.setFont (jLabel1.getFont ().deriveFont (22.f));
		
		jTextArea1.setColumns (20);
		jTextArea1.setRows (15);
		jScrollPane1.setViewportView (jTextArea1);
		jTextArea1.setForeground (new Color (0, 74, 153));
		
		start = new JButton ("start");
		start.setEnabled (false);
		start.addActionListener (new ActionListener ()
		{
			
			@Override
			public void actionPerformed (ActionEvent arg0)
			{
				startGame ();
			}
		});
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (
			getContentPane ());
		getContentPane ().setLayout (layout);
		layout.setHorizontalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			layout
				.createSequentialGroup ()
				.addContainerGap (10, Short.MAX_VALUE)
				.addGroup (
					layout
						.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent (jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE,
							280, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent (start, javax.swing.GroupLayout.PREFERRED_SIZE, 280,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent (jScrollPane1,
							javax.swing.GroupLayout.PREFERRED_SIZE, 280,
							javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap (10, Short.MAX_VALUE)));
		layout.setVerticalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			layout
				.createSequentialGroup ()
				.addGap (68, 68, 68)
				.addComponent (jLabel1)
				.addGap (57, 57, 57)
				.addComponent (jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455,
					Short.MAX_VALUE).addGap (10, 10, 10).addComponent (start)
				.addContainerGap ()));
		
		pack ();
	}
}
