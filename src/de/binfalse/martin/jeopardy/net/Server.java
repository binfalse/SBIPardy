/**
 * 
 */
package de.binfalse.martin.jeopardy.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import de.binfalse.martin.jeopardy.game.JeopardyPlayer;
import de.binfalse.martin.jeopardy.x.MainWin;
import de.binfalse.martin.jeopardy.x.StartWindow;



// TODO: Auto-generated Javadoc
/**
 * The Class Server.
 *
 * @author Martin Scharm
 */
public class Server
	extends Thread
{
	
	/** The server socket. */
	private ServerSocket						serverSocket;
	
	/** The win. */
	private StartWindow							win;
	
	/** The mwin. */
	private MainWin									mwin;
	
	/** The players. */
	private Vector<JeopardyPlayer>	players;
	
	/** The open. */
	private boolean									open;
	
	
	/**
	 * Instantiates a new server.
	 *
	 * @param win the win
	 */
	public Server (StartWindow win)
	{
		open = true;
		serverSocket = null;
		this.win = win;
		players = new Vector<JeopardyPlayer> ();
		win.setServer (this);
	}
	
	
	/**
	 * Name ok.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public boolean nameOk (String name)
	{
		for (int i = 0; i < players.size (); i++)
		{
			if (players.elementAt (i).getPlayerName ().equals (name))
				return false;
		}
		return true;
	}
	
	
	/**
	 * Sets the closed.
	 */
	public void setClosed ()
	{
		open = false;
	}
	
	
	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public Vector<JeopardyPlayer> getPlayers ()
	{
		return players;
	}
	
	
	/**
	 * Listen for requests from the website. Currently we listen at :6677, but
	 * this might change at anytime..
	 */
	public void listen ()
	{
		try
		{
			serverSocket = new ServerSocket (1234);
		}
		catch (IOException e)
		{
			System.out.println ("Could not listen on port: 1234");
			return;
		}
		
		while (serverSocket.isBound () && open)
		{
			Socket clientSocket = null;
			try
			{
				clientSocket = serverSocket.accept ();
				if (!open)
				{
					clientSocket.close ();
					return;
				}
				System.out.println ("accept from client "
					+ clientSocket.getInetAddress ());
				win.addMsg ("accept from client " + clientSocket.getInetAddress ());
				ServerSpeaker cs = new ServerSpeaker (this, clientSocket, win);
				cs.start ();
				players.add (new JeopardyPlayer (cs));
				
				win.addMsg ("num players: " + players.size ());
			}
			catch (IOException e)
			{
				System.out.println ("failed to accept from client..");
			}
		}
	}
	
	
	/**
	 * Sets the main win.
	 *
	 * @param mwin the new main win
	 */
	public void setMainWin (MainWin mwin)
	{
		this.mwin = mwin;
	}
	
	
	/**
	 * Players turn.
	 *
	 * @param p the p
	 */
	public void playersTurn (JeopardyPlayer p)
	{
		if (!mwin.canAnswer (p))
			return;
		for (int i = 0; i < players.size (); i++)
		{
			if (players.elementAt (i) == p)
			{
				players.elementAt (i).sendClient ("your turn");
				mwin.setMainQuestionPanel ();
			}
			else
				players.elementAt (i).sendClient ("wait");
		}
		
	}
	
	
	/**
	 * Removes the player.
	 *
	 * @param p the p
	 */
	public void removePlayer (JeopardyPlayer p)
	{
		for (int i = 0; i < players.size (); i++)
		{
			if (players.elementAt (i) == p)
			{
				players.remove (i);
				win.addMsg ("disconnect from client " + p.getPlayerName ());
				win.addMsg ("num players: " + players.size ());
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run ()
	{
		listen ();
	}
}
