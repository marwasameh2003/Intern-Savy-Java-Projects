//	Just One Byte
//	Tic Tac Toe Java GUI
	 
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.io.*;
import java.util.Scanner;

public class GamePanel extends JPanel implements ActionListener {

	// logic variables
	boolean playerX;
	boolean gameDone = false;
	int winner = -1;
	int player1wins = 0, player2wins = 0;
	int[][] board = new int[3][3];

	// paint variables
	int lineWidth = 5;
	int lineLength = 270;
	int x = 15, y = 100; // location of first line
	int offset = 95; // square width
	int a = 0;
	int b = 5;
	int selX = 0;
	int selY = 0;

	// COLORS
	Color turtle = new Color(0x80bdab);
	Color orange = new Color(0xfdcb9e);
	Color offwhite = new Color(0xf7f7f7);
	Color darkgray = new Color(0x3f3f44);

	// COMPONENTS
	JButton jButton;

	// CONSTRUCTOR
	public GamePanel() {
	}

	public void resetGame() {
	}

	public void paintComponent(Graphics page) {
	}

	public void drawBoard(Graphics page) {
	}

	public void drawUI(Graphics page) {
	}

	public void drawGame(Graphics page) {
	}

	public void checkWinner() {
	}

	public JButton getJButton() {	return jButton; }

	public void setPlayerXWins(int a) {
		player1wins = a;
	}

	public void setPlayerOWins(int a) {
		player2wins = a;
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tic Tac Toe");
		frame.getContentPane();

		GamePanel gamePanel = new GamePanel();
		frame.add(gamePanel);

		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				try {
					File file = new File("score.txt");
					Scanner sc = new Scanner(file);
					gamePanel.setPlayerXWins(Integer.parseInt(sc.nextLine()));
					gamePanel.setPlayerOWins(Integer.parseInt(sc.nextLine()));
					sc.close();
				} catch (IOException io) {
					// file doesnt exist
					File file = new File("score.txt");
				}
			}

			public void windowClosing(WindowEvent e) {
				try {
					PrintWriter pw = new PrintWriter("score.txt");
					pw.write("");
					pw.write(gamePanel.player1wins + "\n");
					pw.write(gamePanel.player2wins + "\n");
					pw.close();
				} catch (FileNotFoundException e1) { }
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	private class XOListener implements MouseListener {

		public void mouseClicked(MouseEvent event) {
			selX = -1;
			selY = -1;
			if (gameDone == false) {
				a = event.getX();
				b = event.getY();
				int selX = 0, selY = 0;
				if (a > 12 && a < 99) {
					selX = 0;
				} else if (a > 103 && a < 195) {
					selX = 1;
				} else if (a > 200 && a < 287) {
					selX = 2;
				} else {
					selX = -1;
				}

				if (b > 12 && b < 99) {
					selY = 0;
				} else if (b > 103 && b < 195) {
					selY = 1;
				} else if (b > 200 && b < 287) {
					selY = 2;
				} else {
					selY = -1;
				}
				if (selX != -1 && selY != -1) {

					if (board[selX][selY] == 0) {
						if (playerX) {
							board[selX][selY] = 1;
							playerX = false;
						} else {
							board[selX][selY] = 2;
							playerX = true;
						}
						checkWinner();
						System.out.println(" CLICK= x:" + a + ",y: " + b + "; selX,selY: " + selX + "," + selY);

					}
				} else {
					System.out.println("invalid click");
				}
			}
		}

		public void mouseReleased(MouseEvent event) {}
		public void mouseEntered(MouseEvent event) {}
		public void mouseExited(MouseEvent event) {}
		public void mousePressed(MouseEvent event) {}
	}

	@Override
	public void actionPerformed(ActionEvent e) { resetGame(); }

}