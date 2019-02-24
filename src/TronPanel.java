//Jean Remy Bougeois-Cruz

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Random;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import javax.swing.JPanel;

public class TronPanel extends JPanel implements ActionListener{
	//Point that stores the heads coordinates
	private boolean gameStart = true;;
	private static Point head;
	private static Point head2;
	private static Point [] body;
	private static Point [] body2;
	private static char currentDir = 'u';
	private static char currentDir2 = 'u';
	private static int bodySize = 1;
	private static int bodySize2 = 1;
	private static char direction = 'u';	//will either be r, l, u, d (right, left, up, down)
	private static char direction2 = 'u';
	private static int whoWon = 0; //0 for no one 1 for Red, 2 for Blue
	protected Timer timer;
	private Font font;
	
	public TronPanel()
	{
		timer = new Timer(100, this);
		
		setPreferredSize(new Dimension(1000, 600));
		setBackground(Color.GRAY);
		/*To respond to key events it needs to be focusable cause
		it's a JPanel*/
		setFocusable(true);
		
		addKeyListener(new KeyAdapt());
		
	}

	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		/*Basically gameStart is only true when the game first starts or when a game finishes
		So the next timer cycle will repaint it with gameStart equaling true*/ 
		if(gameStart == true)
		{
			timer.stop();
			
			//We're going to announce who won
			if(whoWon == 1)
			{
				g.setColor(Color.RED);
				font = new Font("Verdana", Font.BOLD, 24);
				g.setFont(font);
				g.drawString("Red Player Won!", 380, 350);
				whoWon = 0;
			}
			if(whoWon == 2)
			{
				g.setColor(Color.BLUE);
				font = new Font("Verdana", Font.BOLD, 24);
				g.setFont(font);
				g.drawString("Blue Player Won!", 370, 350);
				whoWon = 0;
			}
			g.setColor(Color.BLACK);
			bodySize = 1;
			bodySize2 = 1;
			direction = 'u';
			direction2 = 'u';
			currentDir = 'u';
			currentDir2 = 'u';
			//Initially head will be here
			head = new Point(240, 490);
			head2 = new Point(740, 490);
			
			//Now we initialize the new point array and add the three defaults
			body = new Point[6000];
			body2 = new Point[6000];
			body[0] = new Point(240, 480);
			body2[0] = new Point(740, 480);
			font = new Font("Serif", Font.PLAIN, 18);
			g.setFont(font);
			g.drawString("Press Space to Start",410, 400);
		}
			
		else
		{
			font = new Font("Verdana", Font.BOLD, 20);
			g.setFont(font);
			g.setColor(Color.RED);
			g.fillOval((int) head.getX(), (int) head.getY(), 10, 10);
			for (int i = 0; i < bodySize; i++)
			{
				g.fillOval((int)body[i].getX(), (int)body[i].getY(), 10, 10);
			}
			g.setColor(Color.BLUE);
			g.fillOval((int) head2.getX(), (int) head2.getY(), 10, 10);
			for (int i = 0; i < bodySize2; i++)
			{
				g.fillOval((int)body2[i].getX(), (int)body2[i].getY(), 10, 10);
			}
			
			g.setColor(Color.YELLOW);
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		moveSnake();
		repaint();
	}
	
	public void moveSnake()
	{
		Point oldHead = new Point((int)head.getX(), (int)head.getY());
		Point oldHead2 = new Point((int)head2.getX(), (int)head2.getY());
		switch(direction)
		{
		case 'u':
			if (currentDir != 'd')
			{
				head.setLocation(head.getX(),head.getY() - 10);
				currentDir = 'u';
			}
			else
			{
				head.setLocation(head.getX(), head.getY() + 10);
				currentDir = 'd';
			}
			break;
		case 'd':
			if (currentDir != 'u')
			{
				head.setLocation(head.getX(),head.getY() + 10);
				currentDir = 'd';
			}
			else
			{
				head.setLocation(head.getX(),head.getY() - 10);
				currentDir = 'u';
			}
			break;
		case 'l':
			if (currentDir != 'r')
			{
				head.setLocation(head.getX() - 10,head.getY());
				currentDir = 'l';
			}
			else
			{
				head.setLocation(head.getX() + 10,head.getY());
				currentDir = 'r';
			}
			break;
		case 'r':
			if (currentDir != 'l')
			{
				head.setLocation(head.getX() + 10,head.getY());
				currentDir = 'r';
			}
			else
			{
				head.setLocation(head.getX() - 10,head.getY());
				currentDir = 'l';
			}
			break;
		}
		//Direction2 
		switch(direction2)
		{
		case 'u':
			if (currentDir2 != 'd')
			{
				head2.setLocation(head2.getX(),head2.getY() - 10);
				currentDir2 = 'u';
			}
			else
			{
				head2.setLocation(head2.getX(), head2.getY() + 10);
				currentDir2 = 'd';
			}
			break;
		case 'd':
			if (currentDir2 != 'u')
			{
				head2.setLocation(head2.getX(),head2.getY() + 10);
				currentDir2 = 'd';
			}
			else
			{
				head2.setLocation(head2.getX(),head2.getY() - 10);
				currentDir2 = 'u';
			}
			break;
		case 'l':
			if (currentDir2 != 'r')
			{
				head2.setLocation(head2.getX() - 10,head2.getY());
				currentDir2 = 'l';
			}
			else
			{
				head2.setLocation(head2.getX() + 10,head2.getY());
				currentDir2 = 'r';
			}
			break;
		case 'r':
			if (currentDir2 != 'l')
			{
				head2.setLocation(head2.getX() + 10,head2.getY());
				currentDir2 = 'r';
			}
			else
			{
				head2.setLocation(head2.getX() - 10,head2.getY());
				currentDir2 = 'l';
			}
			break;
		}
		body[bodySize] = new Point((int) oldHead.getX(), (int)oldHead.getY());
		body2[bodySize2] = new Point((int) oldHead2.getX(), (int)oldHead2.getY());
		++bodySize;
		++bodySize2;
		if (checkCollide())
			gameStart = true;
	}
	
	//Checks if head is in valid position
	public boolean checkCollide()
	{
		boolean collide = false;
		for (int i = 1; i < bodySize; i++)
		{
			if (head.getX() == body[i].getX() && head.getY() == body[i].getY())
			{
				collide = true;
				whoWon = 2;
			}
			else if (head2.getX() == body[i].getX() && head2.getX() == body[i].getY())
			{
				collide = true;
				whoWon = 1;
			}
		}
		for (int i = 1; i < bodySize2; i++)
		{
			if (head2.getX() == body2[i].getX() && head2.getY() == body2[i].getY())
			{
				collide = true;
				whoWon = 1;
			}
			else if (head.getX() == body2[i].getX() && head.getY() == body2[i].getY())
			{
				collide = true;
				whoWon = 2;
			}
		}
		//Out of bounds checks
		if (head2.getX() < 0 || head2.getY() < 0 || head2.getX() > 990 || head2.getY() > 590)
		{
			collide = true;
			whoWon = 1;
		}
		if (head.getX() < 0 || head.getY() < 0 || head.getX() > 990 || head.getY() > 590)
		{
			collide = true;
			whoWon = 2;
		}
		if (head.getX() == head2.getX() && head.getY() == head2.getY())
		{
			collide = true;
			whoWon = 0;
		}
		return collide;
	}
	
	

	private class KeyAdapt extends KeyAdapter 
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			switch(keyCode)
			{
				case KeyEvent.VK_SPACE:
					if (gameStart == true)
					{
						gameStart = false;
						timer.setDelay(50);
						timer.start();
					}
					break;
				//making it not exit if you accidentally press down or right while going left or up
				case KeyEvent.VK_UP:
					//gamestart false so we cant change direction before game starts
					if (direction2 != 'd' && gameStart == false)
						direction2 = 'u';
					break;
				case KeyEvent.VK_DOWN:
					if (direction2 != 'u' && gameStart == false)
						direction2 = 'd';
					break;
				case KeyEvent.VK_LEFT:
					if (direction2 != 'r' && gameStart == false)
						direction2 = 'l';
					break;
				case KeyEvent.VK_RIGHT:
					if (direction2 != 'l' && gameStart == false)
						direction2 = 'r';
					break;
				//adding WASD options
				case KeyEvent.VK_W:
					if (direction != 'd' && gameStart == false)
						direction = 'u';
					break;
				case KeyEvent.VK_S:
					if (direction != 'u' && gameStart == false)
						direction = 'd';
					break;
				case KeyEvent.VK_A:
					if (direction != 'r' && gameStart == false)
						direction = 'l';
					break;
				case KeyEvent.VK_D:
					if (direction != 'l' && gameStart == false)
						direction = 'r';
					break;
			}
		}
	}
}
