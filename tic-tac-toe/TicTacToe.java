import java.awt.Color;
import java.awt.Dimension;
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

public class TicTacToe extends JPanel implements ActionListener {
    // logic variables
    boolean playerx;
    boolean gameDone = false;
    int winner = -1;
    int player1wins = 0, player2wins = 0;
    int[][] board = new int[3][3];

    // paint variables
    int linewidth = 5;
    int linelength = 270;
    int x = 15, y = 100; // location of the first line
    int offset = 95;
    int a = 0;
    int b = 5;
    int selX = 0, selY = 0;

    // colors

    Color turtle = new Color(0xFCF1F1);
    Color orange = new Color(0xFCF1F1);
    Color offwhite = new Color(0x1A1C20);
    Color darkgrey = new Color(0xF9813A);

    // components

    JButton jbutton;

    public TicTacToe() {
        Dimension size = new Dimension(420, 300);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        addMouseListener(new XOListener());
        jbutton = new JButton("Play Again?");
        jbutton.addActionListener(this);
        jbutton.setBounds(315, 210, 100, 30);
        add(jbutton);
        resetGame();
        //jbutton.setVisible(false);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.getContentPane();

        TicTacToe tPanel = new TicTacToe();
        frame.add(tPanel);

        frame.addWindowFocusListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e)
            {
                try{
                    File file = new File("score.txt");
                    Scanner sc = new Scanner(file);
                    tPanel.setPlayer1wins(Integer.parseInt(sc.nextLine()));
                    tPanel.setPlayer2wins(Integer.parseInt(sc.nextLine()));
                    sc.close();
                }
                catch(IOException io)
                {
                    File file = new File("score.txt");
                }
            }
            public void WindowClosing(WindowEvent e)
            {
                try
                {
                    PrintWriter pw = new PrintWriter("score.txt");
                    pw.write(tPanel.player1wins + "\n");
                    pw.write(tPanel.player1wins + "\n");
                    pw.close();
                }
                
                    catch(FileNotFoundException fe)
                    {}
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resetGame();
    }

    public JButton getJButton() {
        return jbutton;
    }

    public void setPlayer1wins(int a) {
        player1wins = a;
    }

    public void setPlayer2wins(int a) {
        player2wins = a;
    }

    public void resetGame() {
        playerx = true;
        winner = -1;
        gameDone = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
        getJButton().setVisible(false);// this is to hide the button while the game is on
    }

    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        drawBoard(page);
        drawUI(page);
        drawGame(page);
    }

    public void drawBoard(Graphics page) {
        setBackground(turtle);
        page.setColor(darkgrey);
        // these comming for lines are the four lines that form the board
        page.fillRoundRect(x, y, linelength, linewidth, 5, 30);
        page.fillRoundRect(x, y + offset, linelength, linewidth, 5, 30);
        page.fillRoundRect(y, x, linewidth, linelength, 30, 5);
        page.fillRoundRect(y + offset, x, linewidth, linelength, 30, 5);

    }

    public void drawUI(Graphics page) {
        page.setColor(darkgrey);
        page.fillRect(300, 0, 120, 300);
        Font font = new Font("Helvetica", Font.PLAIN, 20);
        page.setFont(font);

        page.setColor(orange);
        page.drawString("Win Count", 310, 30);
        page.drawString(": " + player1wins, 362, 70);
        page.drawString(": " + player2wins, 362, 105);

        ImageIcon xIcon = new ImageIcon("pngegg (68).png");
        Image ximage = xIcon.getImage();
        Image newxImage = ximage.getScaledInstance(27, 27, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newxicon = new ImageIcon(newxImage);
        page.drawImage(newxicon.getImage(), 44 + offset * 1 + 190, 47 + offset * 0, null);

        page.setColor(offwhite);
        page.fillOval(43 + 190 + offset, 80, 30, 30);
        page.setColor(darkgrey);
        page.fillOval(49 + 190 + offset, 85, 19, 19);

        page.setColor(offwhite);
        Font font1 = new Font("Serif", Font.ITALIC, 18);
        page.setFont(font1);

        if (gameDone) {
            // showwinner
            if (winner == 1) {
                ImageIcon xic = new ImageIcon("pngegg (68).png");
                Image ximg = xic.getImage();
                Image newximg = ximg.getScaledInstance(27, 27, java.awt.Image.SCALE_SMOOTH);
                ImageIcon newxic = new ImageIcon(newximg);
                page.drawImage(newxic.getImage(), 50 + offset * 1 + 200, 160 + offset * 0, null);

                page.drawString("The winner is ",310, 150);
                page.drawImage(ximage, 150, 160, null);

            } else if (winner == 2) {
                page.drawString("The winner is ", 310, 150);
                page.setColor(offwhite);
                page.fillOval(332, 160, 50, 50);
                page.setColor(darkgrey);
                page.fillOval(342, 170, 30, 30);
            } else if (winner == 3) {
                page.drawString("It's a tie", 330, 178);
            }
        } else {
            Font font2 = new Font("Serif", Font.ITALIC, 20);
            page.setFont(font2);
            page.drawString("It's", 350, 160);
            if (playerx) {
                page.drawString("X's turn", 325, 180);
            } else {
                page.drawString("O's turn", 325, 180);
            }
        }

        Image cookie = Toolkit.getDefaultToolkit().getImage("play.png");
        page.drawImage(cookie, 345, 235, 30, 30, this);
        Font c = new Font("Courier", Font.BOLD + Font.CENTER_BASELINE, 13);
        page.setFont(c);
        page.drawString("Just One Turn ", 310, 280);

    }

    public void drawGame(Graphics page) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {

                } else if (board[i][j] == 1) {
                    ImageIcon xIcon = new ImageIcon("pngegg (68).png");
                    Image ximage = xIcon.getImage();
                    Image newxImage = ximage.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon newxicon = new ImageIcon(newxImage);
                    page.drawImage(newxicon.getImage(), 40 + offset * i, 40 + offset * j, null);
                } else if (board[i][j] == 2) {
                    page.setColor(offwhite);
                    page.fillOval(30 + offset * i, 30 + offset * j, 50, 50);
                    page.setColor(turtle);
                    page.fillOval(40 + offset * i, 40 + offset * j, 30, 30);

                }
            }
        }
        repaint();
    }

    public class XOListener implements MouseListener
    {
        public void mouseClicked(MouseEvent event) {
            // the indices the user clickes
            selX = -1;
            selY = -1;
            if(gameDone == false)
            {
                a = event.getX();
                b = event.getY();
                int selX = 0, selY = 0;
                //System.out.println("Clicked => x: "+ a + ", y: "+b);
                if(a>12 && a<99)
                {
                    selX = 0;
                }
                else if(a>103 && a<195)
                {
                    selX = 1;
                }
                else if(a>200 && a<287)
                {
                    selX = 2;
                }
                else
                {
                    selX = -1; // so the user cannot click the lines
                }
                if(b>12 && b<99)
                {
                    selY = 0;
                }
                else if(b>103 && b<195)
                {
                    selY = 1;
                }
                else if(b>200 && b<287)
                {
                    selY = 2;
                }
                else
                {
                    selY = -1; // so the user cannot click the lines
                }
                if(selX != -1 && selY != -1)
                {
                    if(board[selX][selY] ==0)
                    {
                        if(playerx)
                        {
                            board[selX][selY]=1;
                            playerx = false;
                        }
                        else
                        {
                            board[selX][selY]=2;
                            playerx = true;
                        }
                        checkWinner();
                    }
                }
                else
                {
                    // the cell is not empty
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
        }

    }
    public void checkWinner() {
		if (gameDone == true) {
			System.out.print("gameDone");
			return;
		}
		// vertical
		int temp = -1;
		if ((board[0][0] == board[0][1])
				&& (board[0][1] == board[0][2])
				&& (board[0][0] != 0)) {
			temp = board[0][0];
		} else if ((board[1][0] == board[1][1])
				&& (board[1][1] == board[1][2])
				&& (board[1][0] != 0)) {
			temp = board[1][1];
		} else if ((board[2][0] == board[2][1])
				&& (board[2][1] == board[2][2])
				&& (board[2][0] != 0)) {
			temp = board[2][1];

			// horizontal
		} else if ((board[0][0] == board[1][0])
				&& (board[1][0] == board[2][0])
				&& (board[0][0] != 0)) {
			temp = board[0][0];
		} else if ((board[0][1] == board[1][1])
				&& (board[1][1] == board[2][1])
				&& (board[0][1] != 0)) {
			temp = board[0][1];
		} else if ((board[0][2] == board[1][2])
				&& (board[1][2] == board[2][2])
				&& (board[0][2] != 0)) {
			temp = board[0][2];

			// diagonal
		} else if ((board[0][0] == board[1][1])
				&& (board[1][1] == board[2][2])
				&& (board[0][0] != 0)) {
			temp = board[0][0];
		} else if ((board[0][2] == board[1][1])
				&& (board[1][1] == board[2][0])
				&& (board[0][2] != 0)) {
			temp = board[0][2];
		} else {

			// CHECK FOR A TIE
			boolean notDone = false;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j] == 0) {
						notDone = true;
						break;
					}
				}
			}
			if (notDone == false) {
				temp = 3;
			}
		}
		if (temp > 0) {
			winner = temp;
			if (winner == 1) {
				player1wins++;
				System.out.println("winner is X");
			} else if (winner == 2) {
				player2wins++;
				System.out.println("winner is O");
			} else if (winner == 3) {
				System.out.println("It's a tie");
			}
			gameDone = true;
			getJButton().setVisible(true);
		}
	}

}