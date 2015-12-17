/**
 * 
 */
package de.binfalse.martin.jeopardy.x;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.binfalse.martin.jeopardy.game.JeopardyCategory;
import de.binfalse.martin.jeopardy.game.JeopardyPlayer;
import de.binfalse.martin.jeopardy.game.JeopardyTask;
import de.binfalse.martin.jeopardy.game.JeopardyTaskButton;
import de.binfalse.martin.jeopardy.game.JeopardyTaskPanel;
import de.binfalse.martin.jeopardy.net.Server;



// TODO: Auto-generated Javadoc
/**
 * The Class MainWin.
 *
 * @author Martin Scharm
 */
public class MainWin
	extends JFrame
	implements ActionListener, MouseListener, KeyListener
{
	
	/** The player panel. */
	private javax.swing.JPanel				playerPanel;
	
	/** The category panel. */
	private javax.swing.JPanel				categoryPanel;
	
	/** The main panel. */
	private javax.swing.JPanel				mainPanel;
	
	/** The j panel2. */
	private javax.swing.JPanel				jPanel2;
	
	/** The players. */
	private Vector<JeopardyPlayer>		players;
	
	/** The categories. */
	private Vector<JeopardyCategory>	categories;
	
	/** The server. */
	private Server										server;
	
	/** The active qst. */
	private JeopardyTask							activeQst;
	
	/** The question panel. */
	private JPanel										questionPanel;
	
	/** The cur task panel. */
	private JPanel										curTaskPanel;
	
	
	/**
	 * Can answer.
	 *
	 * @param p the p
	 * @return true, if successful
	 */
	public boolean canAnswer (JeopardyPlayer p)
	{
		if (activeQst == null)
			return false;
		
		for (int i = 0; i < players.size (); i++)
			if (players.elementAt (i).isActive ())
				return false;
		
		activeQst.answerFrom (p);
		playerPanel.setBackground (new Color (0, 0, 0));
		
		return true;
	}
	
	
	/**
	 * Instantiates a new main win.
	 *
	 * @param cats the cats
	 * @param players the players
	 * @param s the s
	 */
	public MainWin (Vector<JeopardyCategory> cats,
		Vector<JeopardyPlayer> players, Server s)
	{
		this.server = s;
		categories = cats;
		this.players = players;
		/*
		 * players.add (new JeopardyPlayer("Player 1"));
		 * players.add (new JeopardyPlayer("Player 2"));
		 * players.add (new JeopardyPlayer("Player 3"));
		 * players.add (new JeopardyPlayer("Player 4"));
		 * players.add (new JeopardyPlayer("Player 5"));
		 */
		for (int i = 0; i < categories.size (); i++)
		{
			categories.elementAt (i).setListener (this);
		}
		for (int i = 0; i < this.players.size (); i++)
		{
			this.players.elementAt (i).setListener (this);
		}
		/*
		 * categories.add (new JeopardyCategory());
		 * categories.add (new JeopardyCategory());
		 * categories.add (new JeopardyCategory());
		 * categories.add (new JeopardyCategory());
		 * categories.add (new JeopardyCategory());
		 */
		init ();
	}
	
	
	/**
	 * Redraw category panel.
	 */
	private void redrawCategoryPanel ()
	{
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (categoryPanel);
		categoryPanel.setLayout (layout);
		
		SequentialGroup sg = layout.createSequentialGroup ();
		ParallelGroup vg = layout
			.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING);
		for (int i = 0; i < categories.size (); i++)
		{
			sg = sg.addComponent (categories.elementAt (i),
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
			vg = vg.addComponent (categories.elementAt (i),
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
		}
		
		layout.setHorizontalGroup (sg);
		layout.setVerticalGroup (vg);
	}
	
	
	/**
	 * Redraw player scores.
	 */
	private void redrawPlayerScores ()
	{
		
		javax.swing.GroupLayout playerPanelLayout = new javax.swing.GroupLayout (
			playerPanel);
		playerPanel.setLayout (playerPanelLayout);
		
		SequentialGroup hg = playerPanelLayout.createSequentialGroup ()
			.addContainerGap ();
		ParallelGroup vg = playerPanelLayout
			.createParallelGroup (javax.swing.GroupLayout.Alignment.BASELINE);
		for (int i = 0; i < players.size (); i++)
		{
			hg = hg.addComponent (players.elementAt (i));
			if (i < players.size () - 1)
				hg = hg.addPreferredGap (
					javax.swing.LayoutStyle.ComponentPlacement.RELATED,
					javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
			vg = vg.addComponent (players.elementAt (i));
		}
		
		playerPanelLayout.setHorizontalGroup (playerPanelLayout
			.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup (hg.addContainerGap ()));
		
		playerPanelLayout.setVerticalGroup (playerPanelLayout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			javax.swing.GroupLayout.Alignment.TRAILING,
			playerPanelLayout.createSequentialGroup ().addContainerGap ()
				.addGroup (vg).addContainerGap ()));
		/*
		 * playerPanelLayout.setHorizontalGroup(
		 * playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.
		 * LEADING)
		 * .addGroup(playerPanelLayout.createSequentialGroup()
		 * .addContainerGap()
		 * .addComponent(player1)
		 * .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
		 * javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 * .addComponent(player3)
		 * .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
		 * javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 * .addComponent(player2)
		 * .addContainerGap())
		 * );
		 */
		/*
		 * playerPanelLayout.setVerticalGroup(
		 * playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.
		 * LEADING)
		 * .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
		 * playerPanelLayout.createSequentialGroup()
		 * .addContainerGap(13, Short.MAX_VALUE)
		 * .addGroup(playerPanelLayout.createParallelGroup(javax.swing.GroupLayout.
		 * Alignment.BASELINE)
		 * .addComponent(player1)
		 * .addComponent(player2)
		 * .addComponent(player3))
		 * .addContainerGap())
		 * );
		 */
	}
	
	
	/**
	 * Sets the main question panel.
	 */
	public void setMainQuestionPanel ()
	{
		mainPanel.removeAll ();
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (mainPanel);
		mainPanel.setLayout (layout);
		layout.setHorizontalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addComponent (questionPanel,
			javax.swing.GroupLayout.DEFAULT_SIZE,
			javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addComponent (questionPanel,
			javax.swing.GroupLayout.DEFAULT_SIZE,
			javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		// repaint ();
		// mainPanel.repaint ();
	}
	
	
	/**
	 * Sets the main panel.
	 *
	 * @param panel the new main panel
	 */
	private void setMainPanel (JPanel panel)
	{
		if (panel == null)
			panel = curTaskPanel;
		else
			curTaskPanel = panel;
		mainPanel.removeAll ();
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (mainPanel);
		mainPanel.setLayout (layout);
		layout.setHorizontalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addComponent (panel,
			javax.swing.GroupLayout.DEFAULT_SIZE,
			javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addComponent (panel,
			javax.swing.GroupLayout.DEFAULT_SIZE,
			javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		// repaint ();
		// mainPanel.repaint ();
	}
	
	
	/**
	 * Inits the.
	 */
	private void init ()
	{
		setDefaultCloseOperation (javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform (true);
		this.getContentPane ().setBackground (Color.WHITE);
		
		playerPanel = new javax.swing.JPanel ();
		categoryPanel = new javax.swing.JPanel ();
		mainPanel = new javax.swing.JPanel ();
		playerPanel.setBackground (new Color (0, 74, 153));
		setMainPanel (categoryPanel);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (
			getContentPane ());
		getContentPane ().setLayout (layout);
		layout.setHorizontalGroup (layout
			.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
			.addComponent (playerPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
			.addGroup (
				layout.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
					.addComponent (mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			javax.swing.GroupLayout.Alignment.TRAILING,
			layout
				.createSequentialGroup ()
				.addGroup (
					layout
						.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent (mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap (javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent (playerPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.PREFERRED_SIZE)));
		
		redrawPlayerScores ();
		redrawCategoryPanel ();
		
		this.addKeyListener (this);
		
		pack ();
		
		questionPanel = new JPanel ();
		javax.swing.JLabel qLabel = new javax.swing.JLabel ("Your Question?");
		
		javax.swing.GroupLayout qlayout = new javax.swing.GroupLayout (
			questionPanel);
		questionPanel.setLayout (qlayout);
		qlayout.setHorizontalGroup (qlayout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			javax.swing.GroupLayout.Alignment.TRAILING,
			qlayout.createSequentialGroup ().addContainerGap (100, Short.MAX_VALUE)
				.addComponent (qLabel).addContainerGap (100, Short.MAX_VALUE)));
		qlayout.setVerticalGroup (qlayout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			qlayout.createSequentialGroup ().addContainerGap (100, Short.MAX_VALUE)
				.addComponent (qLabel).addContainerGap (100, Short.MAX_VALUE)));
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource () instanceof JeopardyTaskButton)
		{
			JeopardyTask jt = ((JeopardyTaskButton) e.getSource ()).getTask ();
			activeQst = jt;
			JPanel panel = jt.getAnswer ();
			setMainPanel (panel);
			sendClients ("open");
		}
	}
	
	
	/**
	 * Send clients.
	 *
	 * @param s the s
	 */
	private void sendClients (String s)
	{
		System.out.println ("highscore:");
		for (int i = 0; i < players.size (); i++)
		{
			System.out.println (players.elementAt (i).getPlayerName () + " => "
				+ players.elementAt (i).getPoints ());
		}
		for (int i = 0; i < players.size (); i++)
		{
			players.elementAt (i).sendClient (s);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked (MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered (MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited (MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed (MouseEvent e)
	{
		if (e.getSource () instanceof JeopardyPlayer)
		{
			JeopardyPlayer jp = (JeopardyPlayer) e.getSource ();
			// jp.setActive (true);
			if (activeQst != null)
			{
				if (!jp.isActive ())
					return;
				if (e.getButton () == MouseEvent.BUTTON1)
				{
					jp.addPoints (activeQst.getPoints ());
					sendClients ("wait");
					activeQst.setCorrect (jp.getPlayerName ());
					activeQst.setSolved ();
					activeQst = null;
					setMainPanel (categoryPanel);
				}
				else
				{
					jp.addPoints (-1 * activeQst.getPoints ());
					setMainPanel (null);
					sendClients ("open");
					activeQst.setInCorrect (jp.getPlayerName ());
				}
			}
			else
			{
				String s = (String) JOptionPane.showInputDialog (this,
					"Set the Score of " + jp.getPlayerName (), "ReScore",
					JOptionPane.PLAIN_MESSAGE, null, null, "" + jp.getPoints ());
				try
				{
					if (s != null)
						jp.setPoints (Integer.parseInt (s));
				}
				catch (NumberFormatException ex)
				{
					JOptionPane.showMessageDialog (this, "cannot parse " + s,
						"NumberFormatException", JOptionPane.ERROR_MESSAGE);
				}
			}
			jp.setActive (false);
			playerPanel.setBackground (new Color (0, 74, 153));
		}
		if (e.getSource () instanceof JeopardyTaskPanel)
		{
			
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog (this,
				"close question?", "close question", JOptionPane.YES_NO_OPTION))
			{
				sendClients ("wait");
				activeQst.setClosed ();
				activeQst.setSolved ();
				activeQst = null;
				setMainPanel (categoryPanel);
			}
			
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased (MouseEvent e)
	{
		// TODO Auto-generated method stub
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed (KeyEvent e)
	{
		System.out.println (e.getKeyCode ());
		System.out.println (KeyEvent.VK_ESCAPE);
		if (e.getKeyCode () == KeyEvent.VK_ESCAPE && activeQst != null)
		{
			activeQst = null;
			setMainPanel (categoryPanel);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased (KeyEvent e)
	{
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped (KeyEvent e)
	{
	}
	
}
