import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class JMathPractice extends Thread implements ActionListener {
	public static JFrame jfram;
	public static JPanel panel, panel1, panel2, panel3;
	public static int score, time, difficulty;
	public static JTextField answer;
	public static JLabel difficultyLabel, equationLabel, pointLabel, equation,
			points;
	public static JRadioButton ten, twenty, fifty, hundred, oneMin, twoMin,
			threeMin;
	public static JCheckBox add, sub, mult, div;
	public static JButton start, enter;
	public static Random r = new Random();
	public static int number1, number2, ans;
	public static boolean green = false, red = false;
	public static Timer timer, flash;
	public static String choose = "";

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new JMathPractice();
			}
		});
	}

	JMathPractice() {
		jfram = new JFrame("Math Practice");
		jfram.setLayout(new FlowLayout());
		jfram.setSize(300, 200);
		jfram.setLocationRelativeTo(null);
		jfram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		// Intialize variables
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5, 1));
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(5, 1));
		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(4, 1));
		equationLabel = new JLabel("Equation:");
		equationLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		equation = new JLabel();
		equation.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		equation.setHorizontalAlignment(SwingConstants.CENTER);
		equation.setBorder(BorderFactory.createDashedBorder(Color.BLACK));

		answer = new JTextField();
		answer.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		answer.setHorizontalAlignment(SwingConstants.CENTER);
		answer.setColumns(10);

		enter = new JButton("Enter");
		enter.setActionCommand("enter");
		enter.addActionListener(this);
		enter.setFont(new Font("Times New Roman", Font.PLAIN, 20));

		// Display time
		pointLabel = new JLabel("Points:");
		pointLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

		points = new JLabel("New label");
		points.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		points.setHorizontalAlignment(SwingConstants.CENTER);
		points.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,
				Color.BLACK, Color.white));

		timer = new Timer(1000, this);
		timer.setActionCommand("time");
		timer.setRepeats(true);
		

		// Intialize Buttons
		start = new JButton("Start");
		start.setActionCommand("start");
		start.addActionListener(this);
		start.setSize(new Dimension(350, 35));

		// Intialize difficulty level buttons
		ten = new JRadioButton("Up to 10");
		ten.setSelected(true);
		twenty = new JRadioButton("Up to 20");
		fifty = new JRadioButton("Up to 50");
		hundred = new JRadioButton("Up to 100");

		ButtonGroup difficult = new ButtonGroup();
		difficult.add(ten);
		difficult.add(twenty);
		difficult.add(fifty);
		difficult.add(hundred);

		// Intialize type of calculation buttons
		add = new JCheckBox("Addition");
		add.setSelected(true);
		sub = new JCheckBox("Subtraction");
		mult = new JCheckBox("Multiplication");
		div = new JCheckBox("Division");

		// Intialize time buttons
		oneMin = new JRadioButton("1 Minutes");
		oneMin.setSelected(true);
		twoMin = new JRadioButton("2 Minutes");
		threeMin = new JRadioButton("3 Minutes");

		ButtonGroup timeType = new ButtonGroup();
		timeType.add(oneMin);
		timeType.add(twoMin);
		timeType.add(threeMin);

		panel1.add(new JLabel("Difficulty:"));
		panel1.add(ten);
		panel1.add(twenty);
		panel1.add(fifty);
		panel1.add(hundred);

		panel2.add(new JLabel("Operations:"));
		panel2.add(add);
		panel2.add(sub);
		panel2.add(mult);
		panel2.add(div);

		panel3.add(new JLabel("Time:"));
		panel3.add(oneMin);
		panel3.add(twoMin);
		panel3.add(threeMin);

		jfram.add(panel1);
		jfram.add(panel2);
		jfram.add(panel3);
		jfram.add(start);
		jfram.getRootPane().setDefaultButton(enter);
		jfram.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		timer.start();
		String command = ae.getActionCommand();
		String operation = "";
		switch (command) {
		case "start":
			// Reintialize JFram
			jfram.remove(start);
			jfram.remove(panel1);
			jfram.remove(panel2);
			jfram.remove(panel3);
			layout();
			jfram.add(panel);
			jfram.revalidate();
			jfram.setSize(420, 270);
			jfram.repaint();

			// check difficulties
			if (ten.isSelected()) {
				difficulty = 10;
			} else if (twenty.isSelected()) {
				difficulty = 20;
			} else if (fifty.isSelected()) {
				difficulty = 50;
			} else if (hundred.isSelected()) {
				difficulty = 100;
			}

			// check Operation
			if (add.isSelected()) {
				choose += "+";
			}
			if (sub.isSelected()) {
				choose += "-";
			}
			if (mult.isSelected()) {
				choose += "*";
			}
			if (div.isSelected()) {
				choose += "/";
			}

			// check Time
			if (oneMin.isSelected()) {
				time = 60 * 1000;
			} else if (twoMin.isSelected()) {
				time = 60 * 2000;
			} else if (threeMin.isSelected()) {
				time = 60 * 3000;
			}

			// randominze the operation if multiple selections
			operation = randomizeOperation();
			// handle operations
			switch (operation) {
			case "+":
				points.setText("" + 0);
				addLoop();
				break;
			case "-":
				points.setText("" + 0);
				subLoop();
				break;
			case "*":
				points.setText("" + 0);
				multLoop();
				break;
			case "/":
				points.setText("" + 0);
				divLoop();
				break;
			}
			break;
		case "enter":
			if (!(answer.getText().equals(""))) {
				int incoming = Integer.parseInt(answer.getText());
				if (incoming == ans) {
					points.setForeground(Color.GREEN);
					score += 10;
					points.setText("" + score + "pts.");
					answer.setText("");
					operation = randomizeOperation();
					if (operation.equals("+")) {
						addLoop();
					} else if (operation.equals("-")) {
						subLoop();
					} else if (operation.equals("*")) {
						multLoop();
					} else if (operation.equals("/")) {
						divLoop();
					}
				} else {
					points.setForeground(Color.RED);
					score -= 10;
					points.setText("" + score + "pts.");
					answer.setText("");
					operation = randomizeOperation();
					if (operation.equals("+")) {
						addLoop();
					} else if (operation.equals("-")) {
						subLoop();
					} else if (operation.equals("*")) {
						multLoop();
					} else if (operation.equals("/")) {
						divLoop();
					}
				}
			} else {
				JOptionPane.showMessageDialog(jfram, "No Answer");
				answer.requestFocus();
			}
			break;
		case "time":
			if (time != 0) {
				updateTime();
			} else {
				timer.stop();
				enter.setEnabled(false);
				answer.setEnabled(false);
				JOptionPane.showMessageDialog(jfram, "Your Score is: " + score);
				System.exit(0);
			}
			break;
		}
	}

	public void addLoop() {
		jfram.getContentPane().setBackground(new Color(255, 102, 102));
		panel.setBackground(new Color(255, 102, 102));
		enter.setBackground(new Color(204, 0, 204));
		equationLabel.setText("Add:");
		number1 = r.nextInt(difficulty);
		number2 = r.nextInt(difficulty);
		equation.setText("<html>" + "&nbsp;" + "&nbsp;" + "&nbsp;" + "&nbsp;"
				+ number1 + "<br>+" + "&nbsp;" + "&nbsp;" + number2
				+ "<br>------");
		ans = number1 + number2;
	}

	public void subLoop() {
		panel.setBackground(new Color(153, 153, 153));
		jfram.getContentPane().setBackground(new Color(153, 153, 153));
		enter.setBackground(new Color(51, 0, 204));
		equationLabel.setText("Sub:");
		number1 = r.nextInt(difficulty);
		number2 = r.nextInt(difficulty);
		equation.setText("<html>" + "&nbsp;" + "&nbsp;" + "&nbsp;" + "&nbsp;"
				+ number1 + "<br>-" + "&nbsp;" + "&nbsp;" + "&nbsp;" + number2
				+ "<br>------");
		ans = number1 - number2;
	}

	public void multLoop() {
		panel.setBackground(new Color(0, 153, 153));
		jfram.getContentPane().setBackground(new Color(0, 153, 153));
		enter.setBackground(new Color(204, 0, 51));
		equationLabel.setText("Mul:");
		number1 = r.nextInt(difficulty);
		number2 = r.nextInt(difficulty);
		equation.setText("<html>" + "&nbsp;" + "&nbsp;" + "&nbsp;" + "&nbsp;"
				+ number1 + "<br>x" + "&nbsp;" + "&nbsp;" + number2
				+ "<br>------");
		ans = number1 * number2;
	}

	public void divLoop() {
		panel.setBackground(new Color(153, 153, 0));
		jfram.getContentPane().setBackground(new Color(153, 153, 0));
		enter.setBackground(new Color(0, 0, 0));
		enter.setForeground(Color.WHITE);
		equationLabel.setText("Div:");
		number1 = r.nextInt(difficulty);
		number2 = r.nextInt(difficulty);
		equation.setText("<html>" + "&nbsp;" + "&nbsp;" + "&nbsp;" + "&nbsp;"
				+ number1 + "<br>/" + "&nbsp;" + "&nbsp;" + "&nbsp;" + number2
				+ "<br>------");
		ans = number1 / number2;
	}

	public void updateTime() {
		time -= 1000;
		String temp = String.format("%d sec",
				TimeUnit.MILLISECONDS.toSeconds(time));
		pointLabel.setText("Time: " + temp);
	}

	public String randomizeOperation() {
		int index = r.nextInt(choose.length());
		return "" + choose.charAt(index);
	}

	private void layout() {
		GroupLayout gl_contentPane = new GroupLayout(panel);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																equationLabel,
																GroupLayout.PREFERRED_SIZE,
																67,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								equation,
																								GroupLayout.DEFAULT_SIZE,
																								134,
																								Short.MAX_VALUE)
																						.addComponent(
																								answer,
																								GroupLayout.PREFERRED_SIZE,
																								134,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				gl_contentPane
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								pointLabel)
																						.addComponent(
																								points,
																								GroupLayout.PREFERRED_SIZE,
																								100,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(79)))
										.addGap(21))
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addGap(30)
										.addComponent(enter,
												GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(156, Short.MAX_VALUE)));
		gl_contentPane
				.setVerticalGroup(gl_contentPane
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPane
										.createSequentialGroup()
										.addComponent(equationLabel,
												GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_contentPane
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				pointLabel)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				points,
																				GroupLayout.PREFERRED_SIZE,
																				104,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_contentPane
																		.createSequentialGroup()
																		.addComponent(
																				equation,
																				GroupLayout.PREFERRED_SIZE,
																				86,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				answer,
																				GroupLayout.PREFERRED_SIZE,
																				37,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(enter,
												GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE)
										.addGap(56)));
		panel.setLayout(gl_contentPane);

	}

}
