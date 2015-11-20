
// Reaction Timer
// <Your name>
// <Your section>
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReactionTimer extends JFrame
// [Add Listener Code 1]
implements ActionListener {
	// [Base Code]
	// Game state
	final double GAME_DURATION = 30.0; // 30 seconds
	int numRight;
	int numWrong;
	double timeLeft;
	String centerText = "(1)";
	Random rng;
	// [Add Menu Code 1]
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem startItem;
	JMenuItem quitItem;
	// [Add Button Code 1]
	JButton oneButton;
	JButton twoButton;
	JButton threeButton;
	JButton fourButton;
	// [ADD Center Panel Code 1]
	JPanel centerPanel;
	JLabel topLabel;
	JLabel centerLabel;
	JLabel bottomLabel;
	// [Add Timer Code 1]
	final int TIMER_INTERVAL = 250;
	javax.swing.Timer timer;

	// [Base Code]
	public static void main(String args[]) {
		// [Add Your Name and section number Below]
		ReactionTimer rt = new ReactionTimer("Bassam Qoutah, Section 002");
		rt.setVisible(true);
	}

	// [Base Code]
	public ReactionTimer(String title) {
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(title);
		// [Add Menu Code 2]
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menu = new JMenu("Game");
		menuBar.add(menu);
		//
		startItem = new JMenuItem("Start");
		menu.add(startItem);
		//
		quitItem = new JMenuItem("Quit");
		menu.add(quitItem);

		// [Add Button Code 2]
		Font buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		//
		oneButton = new JButton("(1)");
		oneButton.setFont(buttonFont);
		contentPane.add(oneButton, BorderLayout.NORTH);
		//
		twoButton = new JButton("(2)");
		twoButton.setFont(buttonFont);
		contentPane.add(twoButton, BorderLayout.EAST);
		//
		threeButton = new JButton("(3)");
		threeButton.setFont(buttonFont);
		contentPane.add(threeButton, BorderLayout.SOUTH);
		//
		fourButton = new JButton("(4)");
		fourButton.setFont(buttonFont);
		contentPane.add(fourButton, BorderLayout.WEST);
		// [Add Center Panel Code 2]
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		contentPane.add(centerPanel, BorderLayout.CENTER);
		//
		Font bigLabelFont = new Font(Font.SANS_SERIF, Font.BOLD, 48);
		centerLabel = new JLabel("Reaction Timer");
		centerLabel.setFont(bigLabelFont);
		centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(centerLabel, BorderLayout.CENTER);
		//
		Font smallLabelFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
		topLabel = new JLabel(" time: 0 ");
		topLabel.setFont(smallLabelFont);
		topLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		centerPanel.add(topLabel, BorderLayout.NORTH);
		//
		bottomLabel = new JLabel(" hits: 0, misses: 0 ");
		bottomLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bottomLabel.setFont(smallLabelFont);
		centerPanel.add(bottomLabel, BorderLayout.SOUTH);
		// [Add Listener Code 2]
		startItem.addActionListener(this);
		quitItem.addActionListener(this);
		oneButton.addActionListener(this);
		twoButton.addActionListener(this);
		threeButton.addActionListener(this);
		fourButton.addActionListener(this);
		// [Add Timer Code 2]
		timer = new javax.swing.Timer(TIMER_INTERVAL, this);
		timer.start();
		// [Base Code]
		resetGame();
		timeLeft = 0.0;
		updateView();
	}

	// [Base Code]
	public void resetGame() {
		numRight = 0;
		numWrong = 0;
		timeLeft = GAME_DURATION;
		String centerText = "(1)";
		rng = new Random(new Date().getTime());
		updateView();
	}

	// [Base Code]
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		String command = e.getActionCommand();
		System.out.println(command);
		// [Add Menu Code 3]
		if (source == startItem) {
			System.out.println("start");
			resetGame();
		} else if (source == quitItem) {
			System.out.println("quit");
			System.exit(0);
		}
		// [Add Timer Code 3]
		if (source == timer) {
			if (timeLeft > 0.0) {
				timeLeft -= TIMER_INTERVAL / 1000.0;
			}
			updateView();
		}
		// [Add Button Code 3]
		if (timeLeft > 0.0 && command != null && command.equals(centerText)) {
			numRight++;
			int val = rng.nextInt(4) + 1;
			centerText = "(" + val + ")";
			updateView();
		} else if (timeLeft > 0.0 && source instanceof JButton) {
			numWrong++;
			updateView();
		}
		if (timeLeft > 0.0 && command != null && command.equals(centerText)) {
			numRight++;
			int val = rng.nextInt(4) + 1;
			centerText = "(" + val + ")";
			updateView();
		} else if (timeLeft > 0.0 && source instanceof JButton) {
			numWrong++;
			updateView();
		}
	}

	// [Base Code]
	void updateView() {
		// [ADD Center Panel Code 2]
		topLabel.setText(" time: " + (int) timeLeft + " ");
		bottomLabel.setText(" hits: " + numRight + ", misses:" + numWrong + " ");
		if (timeLeft > 0)
			centerLabel.setText(centerText);
		else
			centerLabel.setText("Reaction Timer");
		repaint();
	}
}