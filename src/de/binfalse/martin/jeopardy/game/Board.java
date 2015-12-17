/**
 * 
 */
package de.binfalse.martin.jeopardy.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 *
 * @author Martin Scharm
 */
public class Board
{
	
	/** The slash. */
	private String														SLASH		= System
																											.getProperty ("file.separator");
	
	/** The pattern. */
	private Pattern														pattern	= Pattern
																											.compile ("^([^:]+):(\\d+):([^:]+):(.+)$");
	
	/** The cats. */
	private HashMap<String, JeopardyCategory>	cats;
	
	
	/**
	 * Read.
	 *
	 * @param dir the dir
	 * @return the vector
	 */
	public Vector<JeopardyCategory> read (String dir)
	{
		cats = new HashMap<String, JeopardyCategory> ();
		File def = new File (dir + SLASH + "board");
		if (def.exists ())
		{
			try
			{
				BufferedReader br = new BufferedReader (new FileReader (def));
				String line;
				while ( (line = br.readLine ()) != null)
				{
					Matcher matcher = pattern.matcher (line);
					if (matcher.find ())
					{
						
						JeopardyCategory c = cats.get (matcher.group (1));
						if (c == null)
						{
							c = new JeopardyCategory (matcher.group (1));
							cats.put (matcher.group (1), c);
							System.out.println (cats.size ());
						}
						
						c.addTask (new JeopardyTask (matcher.group (2), matcher.group (3),
							matcher.group (4), dir));
					}
				}
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			Vector<JeopardyCategory> cv = new Vector<JeopardyCategory> ();
			for (JeopardyCategory c : cats.values ())
			{
				cv.add (c);
			}
			return cv;
		}
		else
		{
			System.out.println ("no board def: " + def.getAbsolutePath ());
			System.exit (1);
		}
		return null;
	}
}
