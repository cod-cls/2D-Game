import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isrunning;
	private Thread thread;
	
	public static JFrame frame;
	 static int WIDTH= 160;
	 static int HEIGHT= 120;
	private final int SCALE= 5;
	
	private BufferedImage image;//criando uma imagem
	private Sprite1 sheet;
	public static BufferedImage[] player;
	private Sprite1 fund;
	public static BufferedImage fundo;
	
	public static boolean var;
	
	
	public Game() {
		this.addKeyListener(this);
		//sheet = new Sprite1("/palito.png",20,88);
		sheet = new Sprite1("NES - Shadow of the Ninja Blue Shadow - Hayate.png",20,67);
		player = new BufferedImage[6];
		
		fund = new Sprite1("NES - Ninja Hattori-kun Ninja wa Shuugyou de Gozaru no Maki JPN - Stage Segments.png",0,0);
		fundo = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		fundo = fund.getSprite(0, 193, 160, 113);
		
		//player[0]= sheet.getSprite(1, 1, 16, 16);
		//player[1]= sheet.getSprite(19,1, 16,16);
		//player[2]= sheet.getSprite(37, 1, 16, 16);
		//player[3]= sheet.getSprite(55, 1, 16, 16);
		
		player[0]= sheet.getSprite(1, 98, 24, 31);
		player[1]= sheet.getSprite(18, 5, 24,37);
		player[2]= sheet.getSprite(43, 5, 24, 37);
		player[3]= sheet.getSprite(68, 5, 22, 37);
		player[4]= sheet.getSprite(90, 5, 22, 37);
		player[5]= sheet.getSprite(113,5, 22,37);
				
		
	initgame();
	image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	start();	
	}

	public void initgame() {
		
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame = new JFrame("Game");
		frame.add(this);//pega todas os atributos do canvas
		frame.setResizable(true);//usuario redimencionar a tela
		frame.pack();//calcular dimensões
		frame.setLocationRelativeTo(null);//janela fica no centro 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//quando clicar pra fechar, o programa para de rodar, ja que pode-se rodar o programa sem estar com a janela
		frame.setVisible(true);//quando inicializar janela estar visivel
		
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
	public void tick() {
		
		 sheet.tick();
	}
	
	
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();//sequencia de buffers pra otimizar a renderização
		
		if(bs == null) {//se não existir o bufferstrategy irá ser criado um
			
			this.createBufferStrategy(3);
			return;
		}
		
		/*
		 método de renderização usando bufferstrategy
		 Graphics g = bs.getDrawGraphics();// agora conseguimos renderizar em nossa tela
		g.setColor(new Color(19,19,19));
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		bs.show();//mostra os graficos*/
		
		Graphics h = image.getGraphics();// primeiro se desenha na sua imagem, depois na tela
		
		h.drawImage(fundo, 0, 0, WIDTH,HEIGHT,null);
		
			
		h.setFont(new Font( "Arial", Font.BOLD,10));
		h.setColor(new Color(255,0,0));
		//h.drawString("", 60, 19);
		
		Graphics2D h2 = (Graphics2D) h;
	
		h2.rotate(Math.toRadians(0),28,92);
	   // sheet.render(h2, player);
		sheet.render(h, player);
	    
	    
	   
		h.dispose();
		
		
		h = bs.getDrawGraphics();
		
		h.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
		
		bs.show();
		
	}
	
	
	public synchronized void start() {
		thread = new Thread(this);
		isrunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		
		
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/ amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isrunning) {
			
			long now = System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime = now;
			if(delta>=1) {
				tick();
				render();
				frames++;
				delta=0;}
			if(System.currentTimeMillis()-timer>1000) {
				//System.out.println("FPS: "+ frames);
				frames = 0;
				timer = System.currentTimeMillis();
			}
			
		}
		
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
if(e.getKeyCode() == KeyEvent.VK_UP) {
			
			sheet.up = true;}
		
if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			sheet.right = true;}
			
if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				
				sheet.left = true;}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
if(e.getKeyCode() == KeyEvent.VK_UP) {
			
			sheet.up = var;}

if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			sheet.right = false;}
			
if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				
				sheet.left = false;}
		

	}
	
}
