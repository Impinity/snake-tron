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

public class SnakePanel extends JPanel implements ActionListener{
	//Point that stores the heads coordinates
	private static boolean gameStart = true;
	private static Point head;
	private static Point [] body;
	private static char currentDir = 'r';		//The direction the snake is going despite how fast keylistener is
	private static Point dot;
	private int bodySize = 2;
	private int score = 0;
	private char direction = 'r';	//will either be r, l, u, d (right, left, up, down)
	protected Timer timer;
	private Font font;
	
	public SnakePanel()
	{
		timer = new Timer(100, this);
		
		setPreferredSize(new Dimension(500, 500));
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
			currentDir = 'r';
			if(score != 0)
			{
				g.setColor(Color.YELLOW);
				font = new Font("Verdana", Font.BOLD, 24);
				g.setFont(font);
				g.drawString("Final Score: " + Integer.toString(score), 160, 210);
				score = 0;
			}
			g.setColor(Color.BLACK);
			bodySize = 2;
			direction = 'r';
			//Initially head will be here
			head = new Point(240, 240);
			
			//Now we initialize the new point array and add the three defaults
			body = new Point[2500];
			body[0] = new Point(230, 240);
			body[1] = new Point(220, 240);
			setDot();
			font = new Font("Serif", Font.PLAIN, 18);
			g.setFont(font);
			g.drawString("Press 1 for EASY, 2 for MEDIUM, 3 for HARD!",80, 250);
		}
			
		else
		{
			font = new Font("Verdana", Font.BOLD, 20);
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawString(Integer.toString(score), 249, 20);
			g.setColor(Color.GREEN);
			g.fillOval((int) head.getX(), (int) head.getY(), 10, 10);
			g.setColor(Color.BLUE);
			for (int i = 0; i < bodySize; i++)
			{
				g.fillOval((int)body[i].getX(), (int)body[i].getY(), 10, 10);
			}
			g.setColor(Color.YELLOW);
			g.fillRect((int)dot.getX(), (int)dot.getY(), 10, 10);
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		moveSnake();
		if (dotEaten() == true)
		{
			increase();
			setDot();
		}
		repaint();
	}
	
	public void moveSnake()
	{
		Point oldHead = new Point((int)head.getX(), (int)head.getY());
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
		for (int i = bodySize - 1; i > 0; i--)
		{
			body[i].setLocation(body[i - 1].getX(), body[i - 1].getY());
		}
		body[0].setLocation(oldHead.getX(), oldHead.getY());
		if (checkCollide())
			gameStart = true;
	}
	
	//Checks if head is in valid position
	public boolean checkCollide()
	{
		boolean collide = false;
		for (int i = 0; i < bodySize; i++)
		{
			if (head.getX() == body[i].getX() && head.getY() == body[i].getY())
				collide = true;
		}
		if (head.getX() < 0 || head.getY() < 0 || head.getX() > 490 || head.getY() > 490)
			collide = true;
		return collide;
	}
	
	public void setDot()
	{
		int x, y;
		Random rand = new Random();
		x = rand.nextInt(49);
		y = rand.nextInt(49);
		x *= 10;
		y *= 10;
		
		dot = new Point(x, y);
	}
	
	public boolean dotEaten()
	{
		boolean eaten = false;
		if (head.getX() == dot.getX() && head.getY() == dot.getY())
			eaten = true;
		return eaten;
	}
	
	public void increase()
	{
		//Set it out of bounds so it's not seen in that one timer sequence
		body[bodySize] = new Point(505, 505);
		bodySize++;
		score++;
	}
	

	private class KeyAdapt extends KeyAdapter 
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();
			switch(keyCode)
			{
				case KeyEvent.VK_1:
					if (gameStart == true)
					{
						gameStart = false;
						timer.setDelay(100);
						timer.start();
					}
					break;
				case KeyEvent.VK_2:
					if (gameStart == true)
					{
						gameStart = false;
						timer.setDelay(50);
						timer.start();
					}
					break;
				case KeyEvent.VK_3:
					if (gameStart == true)
					{
						gameStart = false;
						timer.setDelay(25);
						timer.start();
					}
					break;
				//making it not exit if you accidentally press down or right while going left or up
				case KeyEvent.VK_UP:
					if (direction != 'd' && gameStart == false)
						direction = 'u';
					break;
				case KeyEvent.VK_DOWN:
					if (direction != 'u' && gameStart == false)
						direction = 'd';
					break;
				case KeyEvent.VK_LEFT:
					if (direction != 'r' && gameStart == false)
						direction = 'l';
					break;
				case KeyEvent.VK_RIGHT:
					if (direction != 'l' && gameStart == false)
						direction = 'r';
					break;
				//adding wasd options
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
