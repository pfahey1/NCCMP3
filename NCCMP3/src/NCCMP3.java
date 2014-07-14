/**
 * @(#)NCCMP3.java
 *
 * NCCMP3 application
 *
 * @author
 * @version 1.00 2012/2/2
 */
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;



public class NCCMP3
{
	public static final int DEFAULT_WIDTH = 700;
	public static final int DEFAULT_HEIGHT = 600;
	public static LibraryPanel Library;
	public static PlayListPanel PlayList;
	public static PlayerPanel Player;
	public static Dimension minSize;
	public static JFrame frame;

	public static void main(String[] args)
	{

		System.out.println("Welcome to NCCMP3"); // PRINT Welcome Message
		NCCMP3 application = new NCCMP3();
		System.out.println("Thank you for using NCCMP3"); // PRINT goodbye Message

	}

	public NCCMP3()
	{
		System.out.println("I am the constructor");
		CreateInterface();
	}

    public void CreateInterface()
    {

    	frame = new JFrame ("NCCMP3");
    	Player = new PlayerPanel();
    	PlayList = new PlayListPanel(Player);
    	Library = new LibraryPanel(PlayList);
		minSize = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setMinimumSize(minSize);
		frame.setLocationRelativeTo(null);

 		frame.add(Library);
 		frame.add(PlayList);
 		frame.add(Player);

    	frame.pack();
      	frame.show();

    }
}

class LibraryPanel extends JPanel
{
	JList list;
	JScrollPane scrollPane;
	File dir;
	PlayListPanel playList;

	public LibraryPanel(PlayListPanel playList01)
	{
		playList = playList01;
		dir = new File("music");
		String[] files = dir.list();
		list = new JList(files);
		scrollPane = new JScrollPane(list);
		this.add(scrollPane);


		list.addMouseListener(new ActionJList(list));
		this.setBackground (Color.green);
		this.setBounds(275,0,525,600);

	}

	class ActionJList extends MouseAdapter
	{
		protected JList list;

  		public ActionJList(JList l)
  		{
  			list = l;
   		}

		public void mouseClicked(MouseEvent e)
		{
   			if(e.getClickCount() == 2)
   			{

     			int index = list.locationToIndex(e.getPoint());
     			ListModel dlm = list.getModel();
     			Object item = dlm.getElementAt(index);
     			list.ensureIndexIsVisible(index);
     			System.out.println("Double clicked on " + item);
     			playList.addToList(item);


     		}
   		}
	}
}



class PlayListPanel extends JPanel
{
	JList list;
	JScrollPane scrollPane;
	File dir;
	String[] play;

	public PlayListPanel(PlayerPanel panel)
	{
		play = new String[50];

		list = new JList(play);
		scrollPane = new JScrollPane(list);

		this.add(scrollPane);
		this.setBackground (Color.yellow);
		this.setBounds(0,200,275,400);

	}

	public boolean addToList(Object addItem)
	{
		boolean flag = true;

		if (flag)
		{
			System.out.println("addToList was ran " + addItem);
			return true;
		}
		else
		{
			return false;
		}
	}
}

class PlayerPanel extends JPanel
{
	public PlayerPanel()
	{

		this.setBackground (Color.red);
		this.setBounds(0,0,275,200);

	}
}



