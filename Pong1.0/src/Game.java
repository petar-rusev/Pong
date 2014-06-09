
import java.awt.Graphics;

public class Game extends gameLoop {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int width=548;
	public int height=439;
	
	public void init(){
		x=0;
		y=height/2;
		x1=468;
		y1=height/2;
		setSize(width,height);
		Thread th=new Thread(this);
		th.start();
		OffScreen=createImage(width,height);
		d=OffScreen.getGraphics();
		addKeyListener(this);
		
	}
	public void paint(Graphics g){
		
		d.clearRect(0,0,548,439);
		d.drawImage(background,0,0,this);
		d.drawImage(playerOne,x,y,this);
		d.drawImage(playerTwo,x1,y1,this);
		d.drawImage(ball,xball,yball,this);
		g.drawImage(OffScreen,0,0,this);
		
	}
	public void update(Graphics g){
		paint(g);
	}
	
}
