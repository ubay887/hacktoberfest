//
//	Name:		Singh, Hardeep
//	Project:	3
//	Due:		February 17,2015
//	Course:		CS-245-01-w15
//	Description:
//				Show picture, name, and email of a contact from file
//
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class Rolodex implements ActionListener {
	public static JFrame jfram;
	public static JMenuBar menubar;
	public static JMenu file;
	public static JMenu tabs;
	public static JMenu help;
	public static JMenu placement;
	public static JMenu layoutPolicy;
	public static JTabbedPane jtab;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Rolodex();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	Rolodex() throws Exception {
		jfram = new JFrame("Rolodex");
		jfram.setSize(500, 190);
		jfram.setLocationRelativeTo(null);
		jfram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menubar = new JMenuBar();
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		tabs = new JMenu("Tabs");
		tabs.setMnemonic(KeyEvent.VK_T);
		help = new JMenu("Help");
		placement = new JMenu("Placement");
		layoutPolicy = new JMenu("Layout Policy");
		jtab = new JTabbedPane();
		jtab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		// Create MenuItems for all the menus and set Action Listeners
		JMenuItem open = new JMenuItem("Open");
		open.setEnabled(false);
		JMenuItem exit = new JMenuItem("Exit");
		exit.setMnemonic(KeyEvent.VK_X);
		exit.setActionCommand("exit");
		exit.addActionListener(this);
		JMenuItem top = new JMenuItem("Top");
		top.setActionCommand("top");
		top.addActionListener(this);
		JMenuItem right = new JMenuItem("Right");
		right.setActionCommand("right");
		right.addActionListener(this);
		JMenuItem bottom = new JMenuItem("Bottom");
		bottom.setActionCommand("bottom");
		bottom.addActionListener(this);
		JMenuItem left = new JMenuItem("Left");
		left.setActionCommand("left");
		left.addActionListener(this);
		JMenuItem scroll = new JMenuItem("Scroll");
		scroll.setActionCommand("scroll");
		scroll.addActionListener(this);
		JMenuItem wrap = new JMenuItem("Wrap");
		wrap.setActionCommand("wrap");
		wrap.addActionListener(this);
		JMenuItem defaut = new JMenuItem("Defaults");
		defaut.setMnemonic(KeyEvent.VK_D);
		defaut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.CTRL_MASK));
		defaut.setActionCommand("default");
		defaut.addActionListener(this);
		JMenuItem about = new JMenuItem("About");
		about.setActionCommand("about");
		about.addActionListener(this);

		// Fill file menu
		file.add(open);
		file.addSeparator();
		file.add(exit);

		// Fill tabs menu
		placement.add(top);
		placement.add(right);
		placement.add(bottom);
		placement.add(left);

		layoutPolicy.add(scroll);
		layoutPolicy.add(wrap);

		tabs.add(placement);
		tabs.add(layoutPolicy);
		tabs.addSeparator();
		tabs.add(defaut);

		// Fill help menu
		help.add(about);

		// Hard code the first tab to Personal Information
		JPanel jpan = new JPanel();
		jpan.setLayout(new GridLayout(1, 2));
		JPanel iPanel = new JPanel();
		JPanel tPanel = new JPanel();
		iPanel.add(new JLabel(new ImageIcon("nopic.jpg")));
		tPanel.add(new JLabel("Name: "));
		tPanel.add(new JTextField("Hardeep Singh", 15));
		tPanel.add(new JLabel("Email: "));
		tPanel.add(new JTextField("hardeep07@yahoo.com", 15));
		jpan.add(iPanel);
		jpan.add(tPanel);
		jtab.addTab("Singh, Hardeep", jpan);

		// read from file and create tabs
		Scanner s = new Scanner(new File("contacts.txt"));
		while (s.hasNextLine()) {
			JPanel tPan = new JPanel();
			tPan.setLayout(new GridLayout(1,2));
			JPanel iTemp = new JPanel();
			JPanel tTemp = new JPanel();
			String line = s.nextLine();
			String[] info = line.split("~");
			if(line.isEmpty())	{} else { 
				//Manage Image from file
				if(info[2].equals(""))	{
					info[2] = "nopic.jpg";
				}
				if(!new File(info[2]).exists())	{ info[2] = "nopic.jpg"; }
				iTemp.add(new JLabel(new ImageIcon(info[2])));
				
				//Manage Name from file
				tTemp.add(new JLabel("Name: "));
				tTemp.add(new JTextField(info[0], 15));
				
				//Manage Email from file
				tTemp.add(new JLabel("Email: "));
				tTemp.add(new JTextField(info[1], 15));
				
				//add panels
				tPan.add(iTemp);
				tPan.add(tTemp);
				jtab.addTab(info[0], tPan);
			}
			
		}

		// Fill menubar
		menubar.add(file);
		menubar.add(tabs);
		menubar.add(help);
		jfram.setJMenuBar(menubar);
		jfram.add(jtab);
		jfram.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("exit")) {
			System.exit(0);
		} else if (ae.getActionCommand().equals("top")) {
			jtab.setTabPlacement(JTabbedPane.TOP);
		} else if (ae.getActionCommand().equals("right")) {
			jtab.setTabPlacement(JTabbedPane.RIGHT);
		} else if (ae.getActionCommand().equals("bottom")) {
			jtab.setTabPlacement(JTabbedPane.BOTTOM);
		} else if (ae.getActionCommand().equals("left")) {
			jtab.setTabPlacement(JTabbedPane.LEFT);
		} else if (ae.getActionCommand().equals("scroll")) {
			jtab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		} else if (ae.getActionCommand().equals("wrap")) {
			jtab.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		} else if (ae.getActionCommand().equals("default")) {
			jtab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			jtab.setTabPlacement(JTabbedPane.TOP);
		} else if (ae.getActionCommand().equals("about")) {
			JOptionPane.showMessageDialog(jfram, "<HTML>Rolodex version 0.1"
					+ "<br>(c) 2015 H.Singh");
		}
	}
}
