import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener{
	
	
	private int [] snakeXLength=new int[750];
	private int [] snakeYLength=new int[750];
	
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	
	private ImageIcon titleImage;
	
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon leftmouth;
	
	private int lengthofsnake=3;
	
	private Timer timer;
	private int delay=100;
	
	private int moves=0;
	
	private ImageIcon snakeimage;
	
	private int[] enemyXpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,55,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] enemyYpos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,55,575,600};
	
	private ImageIcon enemyimage;
	
	private Random random=new Random();
	
	private int xpos=random.nextInt(34);
	private int ypos=random.nextInt(22);
	
	private int score=0;
	
	public Gameplay() {
		
		addKeyListener(this);           //here this refers to the object which is implementing KeyListener class....that is "Gameplay"
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);           //here this refers to the object which is implementing KeyListener class....that is "Gameplay"
		timer.start();
		
		
	}
	
	public void paint(Graphics g) {
		
		if(moves==0)
		{
			snakeXLength[2]=50;
			snakeXLength[1]=75;
			snakeXLength[0]=100;
			
			
			snakeYLength[2]=100;
			snakeYLength[1]=100;
			snakeYLength[0]=100;
		}
		
		//draw title image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851,55);
		
		//draw title image
		titleImage=new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//draw border for playing area or gameplay;
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		//draw background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//score
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Scores: "+score, 780, 30);
		
		//length of snake
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Length: "+lengthofsnake, 780, 50);
		
		//drawing snake
		rightmouth=new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
		
		
		//for length of snake
		for(int a=0;a<lengthofsnake;a++)
		{
			if(a==0&&right)
			{
				rightmouth=new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
			}
			if(a==0&&left)
			{
				leftmouth=new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
			}
			if(a==0&&down)
			{
				downmouth=new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
			}
			if(a==0&&up)
			{
				upmouth=new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
			}
			
			
			if(a!=0)
			{
				snakeimage=new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakeXLength[a], snakeYLength[a]);
			}
			
			
		}
		
		enemyimage=new ImageIcon("enemy.png");
		
		if((enemyXpos[xpos]==snakeXLength[0])&&enemyYpos[ypos]==snakeYLength[0])
		{
			score++;
			
			lengthofsnake++;
			xpos=random.nextInt(34);
			ypos=random.nextInt(22);
		}
		
		enemyimage.paintIcon(this, g, enemyXpos[xpos], enemyYpos[ypos]);

		
		for(int b=1;b<lengthofsnake;b++)
		{
			if(snakeXLength[b]==snakeXLength[0] && snakeYLength[b]==snakeYLength[0])
			{
				right=false;
				up=false;
				down=false;
				left=false;
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game Over", 300, 300);
				
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Space to RESTART", 350, 340);
				
			}
		}
		
		
		g.dispose();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		if(right)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeYLength[r+1]=snakeYLength[r];        //placing position of head to its next index
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakeXLength[r]=snakeXLength[r]+25;
				}
				else
				{
					snakeXLength[r]=snakeXLength[r-1];
				}
				
				if(snakeXLength[r]>850)
				{
					snakeXLength[r]=25;
				}
			}
			repaint();
		}
		if(up)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeXLength[r+1]=snakeXLength[r];        //placing position of head to its next index
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakeYLength[r]=snakeYLength[r]-25;
				}
				else
				{
					snakeYLength[r]=snakeYLength[r-1];
				}
				
				if(snakeYLength[r]<75)
				{
					snakeYLength[r]=625;
				}
			}
			repaint();
		}
		
		if(left)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeYLength[r+1]=snakeYLength[r];        //placing position of head to its next index
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakeXLength[r]=snakeXLength[r]-25;
				}
				else
				{
					snakeXLength[r]=snakeXLength[r-1];
				}
				
				if(snakeXLength[r]<25)
				{
					snakeXLength[r]=850;
				}
			}
			repaint();
		}
		if(down)
		{
			for(int r=lengthofsnake-1;r>=0;r--)
			{
				snakeXLength[r+1]=snakeXLength[r];        //placing position of head to its next index
			}
			for(int r=lengthofsnake;r>=0;r--)
			{
				if(r==0)
				{
					snakeYLength[r]=snakeYLength[r]+25;
				}
				else
				{
					snakeYLength[r]=snakeYLength[r-1];
				}
				
				if(snakeYLength[r]>625)
				{
					snakeYLength[r]=75;
				}
			}
			repaint();
		}
	
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}
		
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			moves=0;
			score=0;
			lengthofsnake=3;
			repaint();
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right=true;
			if(!left)
			{
				right=true;
			}
			else
			{
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			moves++;
			left=true;
			if(!right)
			{
				left=true;
			}
			else
			{
				left=false;
				right=true;
			}
			up=false;
			down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			moves++;
			up=true;
			if(!down)
			{
				up=true;
			}
			else
			{
				up=false;
				down=true;
			}
			left=false;
			right=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			moves++;
			down=true;
			if(!up)
			{
				down=true;
			}
			else
			{
				up=true;
				down=false;
				
			}
			left=false;
			right=false;
		}
		
		
		
	}

		
	

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	
}
