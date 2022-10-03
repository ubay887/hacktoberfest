import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class SlideShow implements ActionListener {
	public static String imgList[] = null;
	public static JLabel jlab;
	JFrame jfram;
	JButton button;
	Timer timer;
	JScrollPane scroll;
	double seconds;
	int count = 1;

	public static void main(String[] args) throws Exception {
		// Read from a file
		ArrayList<String> arrayImg = new ArrayList<String>();
		File file = new File("Slideshow.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		while ((line = br.readLine()) != null) {
			arrayImg.add(line);
		}
		fileReader.close();
		imgList = arrayImg.toArray(new String[arrayImg.size()]);

		// Start the Program;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new SlideShow();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	SlideShow() throws Exception {
		jfram = new JFrame("H.Singh's Slideshow");
		jfram.setSize(800, 600);
		jfram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if (imgList.length == 0) {
			jlab = new JLabel();
			jlab.setText("Image List Empty");
		} else {

			jlab = new JLabel(new ImageIcon(new URL(imgList[0])));

			timer = new Timer(1000, this);
			timer.start();
			seconds = 3;

		}
		scroll = new JScrollPane(jlab);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jfram.add(scroll, BorderLayout.CENTER);
		jfram.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (count == imgList.length)
			count = 0;
		if (--seconds <= 0) {
			if (imgList.length == 1) {
				try {
					jlab.setIcon(new ImageIcon(new URL(imgList[0])));
				} catch (Exception e) {
				}
				seconds = 3;
				timer.restart();
			}
			try {
				jlab.setIcon(new ImageIcon(new URL(imgList[count])));
			} catch (Exception e) {
			}
			timer.restart();
			seconds = 3;
			count++;
		}

	}
}
