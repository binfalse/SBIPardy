/**
 * 
 */
package de.binfalse.martin.jeopardy.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import de.binfalse.martin.jeopardy.game.JeopardyPlayer;
import de.binfalse.martin.jeopardy.x.StartWindow;



// TODO: Auto-generated Javadoc
/**
 * The Class ServerSpeaker. This will open a thread for each client to talk to
 * it.
 * 
 * @author martin scharm
 * 
 */
public class ServerSpeaker
	extends Thread
{
	
	/** This string is used to split the messages. */
	private final String		SPLITTER	= "!;!";
	
	/** The socket of the client. */
	private Socket					cs;
	
	/** The win. */
	private StartWindow			win;
	
	/** The player. */
	private JeopardyPlayer	player;
	
	/** The out. */
	private PrintWriter			out;
	
	/** The s. */
	private Server					s;
	
	
	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer (JeopardyPlayer player)
	{
		this.player = player;
	}
	
	
	/**
	 * Instantiates a new server speaker.
	 *
	 * @param s the s
	 * @param cs the client socket
	 * @param win the win
	 */
	public ServerSpeaker (Server s, Socket cs, StartWindow win)
	{
		this.s = s;
		this.cs = cs;
		this.win = win;
	}
	
	
	/**
	 * Talk to the client. The protocol is very simple, starting with:
	 * 
	 * <pre>
	 * server> hi!
	 * client> hi server!
	 * </pre>
	 * 
	 * If that was ok the server is listening. There are different commands. To
	 * ask for the number of models that are available the client should send a
	 * message like:
	 * 
	 * <pre>
	 * client> s
	 * server> s42
	 * </pre>
	 * 
	 * in this case there are 42 models available. To get the model number 23 send
	 * the following:
	 * 
	 * <pre>
	 * client> g23
	 * server> gTestModel;TestId;2:2012-04-01;2011-04-01;
	 * </pre>
	 * 
	 * In this case the model has the name <em>TestModel</em> and the id
	 * <em>TestId</em> and there are <em>2</em> versions of the model available,
	 * one from <em>2012-04-01</em> and another one from <em>2011-04-01</em>.
	 * 
	 * That's it... :P
	 */
	private void talk ()
	{
		BufferedReader in;
		try
		{
			out = new PrintWriter (cs.getOutputStream (), true);
			in = new BufferedReader (new InputStreamReader (cs.getInputStream ()));
		}
		catch (IOException e)
		{
			e.printStackTrace ();
			return;
		}
		String inputLine;
		
		out.println ("your name?");
		
		try
		{
			while ( (inputLine = in.readLine ()) != null)
			{
				System.out.println ("read: " + inputLine);
				if (inputLine.startsWith ("my name is "))
				{
					String pname = inputLine.substring (11);
					if (!s.nameOk (pname))
						out.println ("name vergeben");
					else
					{
						win.addMsg ("player " + pname + " joined");
						player.setName (pname);
						
						System.out.println (player.getPlayerName ());
					}
				}
				else if (inputLine.startsWith ("I want"))
				{
					System.out.println ("wants: " + cs.getInetAddress ());
					
					s.playersTurn (player);
					player.setActive (true);
					
				}
				
			}
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}
		s.removePlayer (player);
	}
	
	
	/**
	 * Say.
	 *
	 * @param msg the msg
	 */
	public void say (String msg)
	{
		System.out.println ("saying: " + msg + " to " + cs.getInetAddress ());
		out.println (msg);
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run ()
	{
		talk ();
		try
		{
			System.out.println ("closing connection");
			cs.close ();
			System.out.println ("connection closed");
		}
		catch (IOException e)
		{
			e.printStackTrace ();
		}
	}
	
}
