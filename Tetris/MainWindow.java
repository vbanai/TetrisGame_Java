package Tetris;

import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.concurrent.Executors;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame mainFrame;
	private JTextArea gameArea; 
	private TetrisGame tetrisGame;
	private JTextField scoreTextField;
	private JTextArea nextBlockTextArea;
	private JTextField gameStateLabel;


	/**
	 * Launching the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.mainFrame.setVisible(true);
					Executors.newSingleThreadExecutor().execute(new TetrisGame(window));  //one thread
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}
	
	public void setTetrisGame(TetrisGame tetrisGame) {
		this.tetrisGame=tetrisGame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("Tetris");
		mainFrame.setBounds(100, 100, 537, 600);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0,0));
		
		JPanel eastPanel = new JPanel();
		mainFrame.getContentPane().add(eastPanel, BorderLayout.EAST);
		GridBagLayout gbl_eastPanel = new GridBagLayout();
		gbl_eastPanel.columnWidths = new int[] {50};
		gbl_eastPanel.rowHeights = new int[] {30, 30, 30, 30, 30};
		gbl_eastPanel.columnWeights = new double[]{0.0};
		gbl_eastPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		eastPanel.setLayout(gbl_eastPanel);
		
		JButton pauseOrResumeButton = new JButton("Pause");
		pauseOrResumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pauseOrResumeButton.getText().equals("Pause")) {
					pauseOrResumeButton.setText("Resume");
					tetrisGame.pause();
				}else {
					pauseOrResumeButton.setText("Pause");
					tetrisGame.resume();			
				}
				gameArea.requestFocusInWindow();
			}
		});
		GridBagConstraints gbc_startButton = new GridBagConstraints();
		gbc_startButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_startButton.insets = new Insets(0, 0, 0, 5);
		gbc_startButton.gridx = 0;
		gbc_startButton.gridy = 0;
		eastPanel.add(pauseOrResumeButton, gbc_startButton);
		
		JLabel scoreLabel = new JLabel("Point:");
		GridBagConstraints gbc_scoreLabel = new GridBagConstraints();
		gbc_scoreLabel.anchor = GridBagConstraints.WEST;
		gbc_scoreLabel.insets = new Insets(0, 0, 0, 5);
		gbc_scoreLabel.gridx = 0;
		gbc_scoreLabel.gridy = 1;
		eastPanel.add(scoreLabel, gbc_scoreLabel);
		
		scoreTextField = new JTextField();
		scoreTextField.setText("0");
		scoreTextField.setEditable(false);
		GridBagConstraints gbc_scoreTextField = new GridBagConstraints();
		gbc_scoreTextField.anchor = GridBagConstraints.WEST;
		gbc_scoreTextField.insets=new Insets(0,0,0,5);
		gbc_scoreTextField.gridx = 0;
		gbc_scoreTextField.gridy = 2;
		eastPanel.add(scoreTextField, gbc_scoreTextField);
		scoreTextField.setColumns(10);
		
		JLabel nextBlockLabel = new JLabel("Next:");
		GridBagConstraints gbc_nextBlockLabel = new GridBagConstraints();
		gbc_nextBlockLabel.anchor = GridBagConstraints.WEST;
		gbc_nextBlockLabel.insets = new Insets(0, 0, 0, 5);
		gbc_nextBlockLabel.gridx = 0;
		gbc_nextBlockLabel.gridy = 3;
		eastPanel.add(nextBlockLabel, gbc_nextBlockLabel);
		
		gameStateLabel = new JTextField();
		gameStateLabel.setText("");
		gameStateLabel.setEditable(false);
		GridBagConstraints gbc_gameStateLabel = new GridBagConstraints();
		gbc_gameStateLabel.anchor = GridBagConstraints.WEST;
		gbc_gameStateLabel.insets=new Insets(0,0,0,5);
		gbc_gameStateLabel.gridx = 0;
		gbc_gameStateLabel.gridy = 7;
		eastPanel.add(gameStateLabel, gbc_gameStateLabel);
		gameStateLabel.setColumns(10);
		
		
		
		nextBlockTextArea = new JTextArea();
		nextBlockTextArea.setEditable(false);
		nextBlockTextArea.setFont(new Font("Consolas", Font.BOLD, 31));
		nextBlockTextArea.setHighlighter(null);  //disable selection
		GridBagConstraints gbc_nextBlockTextArea = new GridBagConstraints();
		gbc_nextBlockTextArea.anchor = GridBagConstraints.NORTHWEST;
		gbc_nextBlockTextArea.gridx = 0;
		gbc_nextBlockTextArea.gridy = 4;
		eastPanel.add(nextBlockTextArea, gbc_nextBlockTextArea);
		
		gameArea = new JTextArea();
		gameArea.setFont(new Font("Consolas", Font.BOLD, 31));
		gameArea.setEditable(false);
		gameArea.setHighlighter(null);  //disable selection
		gameArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent keyEvent) {
				int pressedKeyCode=keyEvent.getKeyCode();
				switch(pressedKeyCode) {
				case KeyEvent.VK_LEFT: 
					tetrisGame.moveBlockLeft();
				break;
				case KeyEvent.VK_RIGHT: 
					tetrisGame.moveBlockRight();
				break;
				case KeyEvent.VK_DOWN: 
					tetrisGame.dropBlock();
				break;
				case ' ':
					tetrisGame.rotate();
					break;
				}
			}
			});
		mainFrame.getContentPane().add(gameArea, BorderLayout.CENTER);
	}

	public JTextArea getGameArea() {
		return gameArea;
	}

	public JTextField getScoreTextField() {
		return scoreTextField;
	}
	
	
	public JTextArea getNextBlockTextArea() {
		return nextBlockTextArea;
	}
	
	public JTextField getGameStateLabel() {
		return gameStateLabel;
	}
	
}
