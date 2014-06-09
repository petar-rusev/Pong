
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class gameLoop extends Applet implements Runnable,KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int scoreOne=0;
	public int scoreTwo=0;
	public int x,y;
	public int x1,y1;
	public int xball,yball;
	public int dxball=2;
	public int dyball=2;
	public Image OffScreen;
	public Graphics d;
	public boolean up,down;
	public boolean up1,down1;
	public BufferedImage background,playerOne,playerTwo,ball;
	public static boolean gameRunning=true;
	
   
	public void run(){
		requestFocus();// doesn't need to click on game to run it
		
		try {
		background =ImageIO.read(new File("table.png"));
		playerOne=ImageIO.read(new File("Paddle_1.png"));
		playerTwo=ImageIO.read(new File("Paddle_2.png"));
		ball=ImageIO.read(new File("ball.png"));
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(gameRunning){
			
			//movement of the players
			if(up==true){
				y-=7;
			}
			if(down==true){
				y+=7;
			}
			repaint();
			if(up1==true){
				y1-=7;
			}
			if(down1==true){
				y1+=7;
			}
			
			//ball collision x axis.
			if(xball+dxball>548-ball.getWidth()){
				xball=548-ball.getWidth();
				dxball=-dxball;
			}
			else{
				xball+=dxball;
			}
			if(xball+dxball<0){
				xball=0;
				dxball=-dxball;
			}
			else{
				xball+=dxball;
			}
			//ball collision y axis.
			if(yball+dyball>439-ball.getHeight()){
				yball=439-ball.getHeight();
				dyball=-dyball;
			}
			else{
				yball+=dyball;
			}
			if(yball+dyball<0){
				yball=0;
				dyball=-dyball;
			}
			else{
				yball+=dyball;
			}
			//Player One Paddle Collision with ball
			
			
			//player One Paddle Collision with wall
			if(y>=439-playerOne.getHeight()){
				y=439-playerOne.getHeight();
				
			}
			if(y<=0){
				y=0;
				
			}
			//Player Two Paddle Collision with wall
			if(y1>=439-playerOne.getHeight()){
				y1=439-playerOne.getHeight();
				
			}
			if(y1<=0){
				y1=0;
				
			}
			Rectangle boundbox1=new Rectangle(x,y,playerOne.getWidth(),playerOne.getHeight());
			Rectangle boundBox2=new Rectangle(x1,y1,playerTwo.getWidth(),playerTwo.getHeight());
			Rectangle boundboxB=new Rectangle(xball,yball,ball.getWidth(),ball.getHeight());
			
			if(boundbox1.intersects(boundboxB)){
				xball=xball+10;
				yball=yball+10;
				dxball=-dxball;
				dyball=-dyball;
				play(getCodeBase(),"tick.au");
			}
			repaint();
			if(boundBox2.intersects(boundboxB)){
				xball=xball-10;
				yball=yball-10;
				dxball=-dxball;
				dyball=-dyball;
				play(getCodeBase(),"tick.au");
			}
			repaint();
			
			if(xball==0){
				scoreOne++;
				xball=548/2;
				yball=439/2;
				
			}
			if(xball==468){
				scoreTwo++;
				xball=548/2;
				yball=439/2;
				
			}
			
			
			try{
				Thread.sleep(20);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		//giving players movement
		if (keyCode == KeyEvent.VK_UP) {
			down1=false;
			up1 = true;
			
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			up1=false;
			down1=true;			
		}
		if (keyCode == KeyEvent.VK_W) {
			down=false;
			up= true;
			
		}
		if (keyCode == KeyEvent.VK_S) {
			up=false;
			down= true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
int keyCode = e.getKeyCode();
		
		//improving players movement for released key
		if (keyCode == KeyEvent.VK_UP) {
			down1 = false;
			up1 = false;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			down1 = false;
			up1 = false;
		}
		
		if (keyCode == KeyEvent.VK_W) {
			down = false;
			up = false;
		}
		if (keyCode == KeyEvent.VK_S) {
			down = false;
			up = false;
		}
		//exit button in the game
		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
	}

	
	
}
