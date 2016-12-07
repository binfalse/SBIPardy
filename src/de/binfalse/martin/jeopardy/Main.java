/**
 * 
 */
package de.binfalse.martin.jeopardy;

import de.binfalse.martin.jeopardy.game.Board;
import de.binfalse.martin.jeopardy.net.Server;
import de.binfalse.martin.jeopardy.x.MainWin;
import de.binfalse.martin.jeopardy.x.StartWindow;


/**
 * @author Martin Scharm
 *
 */
public class Main
{
	
	/**
	 * 
	 */
	public Main ()
	{
		final Board b = new Board ();
		final StartWindow win = new StartWindow(b.read ("res"));
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
          win.setVisible(true);
      }
  });
		new Server (win).start ();
		
    /*java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
          new MainWin(b.read ("/home/martin/education/dev/Jeopardy/res")).setVisible(true);
      }
  });*/
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the args
	 */
	public static void main (String[] args)
	{
		// TODO Auto-generated method stub
		new Main ();
	}
	
}
