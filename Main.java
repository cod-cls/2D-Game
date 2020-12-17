import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable, KeyListener{

	
private boolean isrunning;
private Thread thread;

public static JFrame frame;
private int WIDTH = 160, HEIGHT = 120, SCALE = 3;

private BufferedImage layer;

public BufferedImage player;
public Type sheet;

public Main() {
	
	this.addKeyListener(this);
	
	sheet = new Type("palito.png", 20, 60);
	player = sheet.getSprite(1,1,16,16);
	
	initgame();
	layer = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
	
}
	

	public static void main(String[] args) {

		new Main().start();
		
	}
	
public void initgame(){
	
	setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
	frame = new JFrame("game");
	frame.add(this);
	frame.setResizable(true);
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	
}
	
	
	public void start() {
		
	      isrunning = true;
	      thread = new Thread(this);
	      thread.start();
	}
	
	
	public void tick() {
		
		sheet.tick();
	
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		
		}
		
	Graphics h = layer.getGraphics();
	
	h.setColor(new Color(2,2,56));
	h.fillRect(0, 0, WIDTH, HEIGHT);
	
	sheet.render(player,h);
	
	
	h = bs.getDrawGraphics();
	h.drawImage(layer,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
	h.dispose();
	bs.show();
	}
	
	
	
	public void run() {
		
		
		long now;
		double amountTicks = 60.0;
		double ns = 1000000000/amountTicks;
		double delta = 0;
		int frames = 0;
		long lastTime = System.nanoTime();
		double timer = System.currentTimeMillis();
	
			while(isrunning) {
				
				now = System.nanoTime();
				delta += (now - lastTime)/ns;
				lastTime = now;
				
				if(delta >= 1) {
				tick();
				render();
				delta = 0;
				frames++;
				}
				
				if(System.currentTimeMillis() - timer >= 1000) {
					
					//System.out.println("FPS: " + frames);
					timer = System.currentTimeMillis();
					frames = 0;
				}
			}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
		sheet.right = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			sheet.left = true;
			}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			sheet.right = false;
			}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			sheet.left = false;
			}
	}


	
	
	}
	
	
	
	
	
