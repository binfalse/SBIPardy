/**
 * 
 */
package de.binfalse.martin.jeopardy.game;

import java.awt.Color;

import javax.swing.JLabel;

import de.binfalse.martin.jeopardy.net.ServerSpeaker;
import de.binfalse.martin.jeopardy.x.MainWin;



// TODO: Auto-generated Javadoc
/**
 * The Class JeopardyPlayer.
 *
 * @author Martin Scharm
 */
public class JeopardyPlayer
	extends JLabel
{
	
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -4611041599251891588L;
	
	/** The points. */
	private int								points;
	
	/** The pname. */
	private String						pname;
	
	/** The ss. */
	ServerSpeaker							ss;
	
	/** The active. */
	private boolean						active;
	
	
	/**
	 * Instantiates a new jeopardy player.
	 *
	 * @param ss the ss
	 */
	public JeopardyPlayer (ServerSpeaker ss)
	{
		super ("");
		active = false;
		this.pname = "";
		this.setForeground (Color.WHITE);
		points = 0;
		rescore ();
		this.ss = ss;
		ss.setPlayer (this);
	}
	
	
	/**
	 * Send client.
	 *
	 * @param msg the msg
	 */
	public void sendClient (String msg)
	{
		System.out.println ("saying: " + msg + " to client");
		ss.say (msg);
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.Component#setName(java.lang.String)
	 */
	public void setName (String name)
	{
		this.pname = name;
		rescore ();
	}
	
	
	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive ()
	{
		return active;
	}
	
	
	/**
	 * Sets the active.
	 *
	 * @param act the new active
	 */
	public void setActive (boolean act)
	{
		active = act;
		if (act)
			this.setForeground (Color.YELLOW);
		else
			this.setForeground (Color.WHITE);
		
	}
	
	
	/**
	 * Gets the player name.
	 *
	 * @return the player name
	 */
	public String getPlayerName ()
	{
		return pname;
	}
	
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints ()
	{
		return points;
	}
	
	
	/**
	 * Rescore.
	 */
	private void rescore ()
	{
		this.setText (pname + ": " + points);
	}
	
	
	/**
	 * Sets the points.
	 *
	 * @param points the new points
	 */
	public void setPoints (int points)
	{
		this.points = points;
		rescore ();
	}
	
	
	/**
	 * Adds the points.
	 *
	 * @param points the points
	 */
	public void addPoints (int points)
	{
		/*
		 * if (!active)
		 * return;
		 */
		this.points += points;
		rescore ();
	}
	
	
	/**
	 * Sets the listener.
	 *
	 * @param ma the new listener
	 */
	public void setListener (MainWin ma)
	{
		this.addMouseListener (ma);
		this.addKeyListener (ma);
	}
}
