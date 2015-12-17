/**
 * 
 */
package de.binfalse.martin.jeopardy.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLEditorKit;

import de.binfalse.martin.jeopardy.x.MainWin;



// TODO: Auto-generated Javadoc
/**
 * The Class JeopardyTaskPanelSound.
 *
 * @author Martin Scharm
 */
public class JeopardyTaskPanelSound
	extends JeopardyTaskPanel
{
	
	/** The slash. */
	private String			SLASH	= System.getProperty ("file.separator");
	
	/** The answer. */
	private String			answer;
	
	/** The clip. */
	Clip								clip;
	
	/** The pane. */
	private JEditorPane	pane;
	
	/** The play panel. */
	private JPanel			playPanel;
	
	
	/**
	 * Instantiates a new jeopardy task panel sound.
	 *
	 * @param answer the answer
	 * @param dir the dir
	 */
	public JeopardyTaskPanelSound (String answer, String dir)
	{
		super ();
		this.answer = dir + SLASH + answer;
		System.out.println (this.answer);
		init ();
		File f = new File (this.answer);
		
		try
		{
			AudioInputStream in = AudioSystem.getAudioInputStream (f);
			
			AudioInputStream din = null;
			AudioFormat baseFormat = in.getFormat ();
			AudioFormat decodedFormat = new AudioFormat (
				AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate (), 16,
				baseFormat.getChannels (), baseFormat.getChannels () * 2,
				baseFormat.getSampleRate (), false);
			
			din = AudioSystem.getAudioInputStream (decodedFormat, in);
			clip = AudioSystem.getClip ();
			clip.open (din);
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			System.exit (1);
		}
		
	}
	
	
	/**
	 * Start playing.
	 */
	private void startPlaying ()
	{
		clip.start ();
	}
	
	
	/**
	 * Pause playing.
	 */
	private void pausePlaying ()
	{
		clip.stop ();
	}
	
	
	/**
	 * Stop playing.
	 */
	private void stopPlaying ()
	{
		clip.stop ();
		clip.setFramePosition (0);
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
		pane
			.setText ("<center><font color='#ffffff' size='28'>listen carefully!</font></center>");
		
		playPanel = new javax.swing.JPanel ();
		playPanel.setBackground (new Color (0, 74, 153));
		JButton stopBtn = new javax.swing.JButton ("stop");
		JButton pauseBtn = new javax.swing.JButton ("pause");
		JButton playBtn = new javax.swing.JButton ("play");
		stopBtn.setBackground (Color.WHITE);
		stopBtn.setForeground (new Color (0, 74, 153));
		pauseBtn.setBackground (Color.WHITE);
		pauseBtn.setForeground (new Color (0, 74, 153));
		playBtn.setBackground (Color.WHITE);
		playBtn.setForeground (new Color (0, 74, 153));
		
		stopBtn.addActionListener (new ActionListener ()
		{
			
			@Override
			public void actionPerformed (ActionEvent arg0)
			{
				stopPlaying ();
			}
		});
		pauseBtn.addActionListener (new ActionListener ()
		{
			
			@Override
			public void actionPerformed (ActionEvent arg0)
			{
				pausePlaying ();
			}
		});
		playBtn.addActionListener (new ActionListener ()
		{
			
			@Override
			public void actionPerformed (ActionEvent arg0)
			{
				startPlaying ();
			}
		});
		
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout (
			playPanel);
		playPanel.setLayout (jPanel1Layout);
		jPanel1Layout.setHorizontalGroup (jPanel1Layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			javax.swing.GroupLayout.Alignment.TRAILING,
			jPanel1Layout.createSequentialGroup ().addGap (0, 371, Short.MAX_VALUE)
				.addComponent (playBtn)
				.addPreferredGap (javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent (pauseBtn)
				.addPreferredGap (javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent (stopBtn)));
		jPanel1Layout.setVerticalGroup (jPanel1Layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			javax.swing.GroupLayout.Alignment.TRAILING,
			jPanel1Layout
				.createSequentialGroup ()
				.addContainerGap (24, Short.MAX_VALUE)
				.addGroup (
					jPanel1Layout
						.createParallelGroup (javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent (stopBtn).addComponent (pauseBtn)
						.addComponent (playBtn)).addContainerGap ()));
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout (this);
		this.setLayout (layout);
		layout.setHorizontalGroup (layout
			.createSequentialGroup ()
			.addContainerGap (10, Short.MAX_VALUE)
			.addGroup (
				layout
					.createParallelGroup (javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup (
						layout.createParallelGroup (
							javax.swing.GroupLayout.Alignment.TRAILING).addComponent (
							playPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addComponent (pane)).addContainerGap (10, Short.MAX_VALUE));
		layout.setVerticalGroup (layout.createParallelGroup (
			javax.swing.GroupLayout.Alignment.LEADING).addGroup (
			layout
				.createSequentialGroup ()
				.addContainerGap (10, Short.MAX_VALUE)
				.addComponent (pane)
				.addPreferredGap (javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent (playPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap (10, Short.MAX_VALUE)));
		
		/*
		 * setBackground (new Color (0, 74, 153));
		 * setForeground (Color.WHITE);
		 * 
		 * ImageIcon image = new ImageIcon (answer);
		 * JLabel label = new JLabel ("", image, JLabel.CENTER);
		 * 
		 * javax.swing.GroupLayout layout = new javax.swing.GroupLayout (this);
		 * this.setLayout (layout);
		 * layout.setHorizontalGroup (layout.createParallelGroup (
		 * javax.swing.GroupLayout.Alignment.LEADING).addGroup (
		 * layout.createSequentialGroup ().addContainerGap (10, Short.MAX_VALUE)
		 * .addComponent (label).addContainerGap (10, Short.MAX_VALUE)));
		 * layout.setVerticalGroup (layout.createParallelGroup (
		 * javax.swing.GroupLayout.Alignment.LEADING).addGroup (
		 * layout.createSequentialGroup ().addContainerGap (10, Short.MAX_VALUE)
		 * .addComponent (label).addContainerGap (10, Short.MAX_VALUE)));
		 */
	}
	
	
	/* (non-Javadoc)
	 * @see de.binfalse.martin.jeopardy.game.JeopardyTaskPanel#answerFrom(de.binfalse.martin.jeopardy.game.JeopardyPlayer)
	 */
	@Override
	public void answerFrom (JeopardyPlayer p)
	{
		pausePlaying ();
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
		playPanel.addMouseListener (win);
		pane.addMouseListener (win);
	}
	
}
