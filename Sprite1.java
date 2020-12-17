import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite1 	 {
		
	private BufferedImage sprite;
	public int x;
	public int y;
	public static int frames=0;
	public static int maxframes= 4;
	public static int curAnimation=1;
	public static int maxAnimation=6;
	public boolean right;
	public boolean left;
	public boolean up;
	public int upDown=1;
    int maxAceler = 8;
    int aceler=maxAceler;
    int contFrames = maxAceler;
    int speed = 2;
    private int cRL=1;
	
	public void tick() {
	
		//pulo;
		if(up) {
			curAnimation = 0;
		// upDown = 1 sigmifica a subida;
			if(upDown == 1) {
				//na Classe Game o var fica presente como uma variavel que muda o release;
				Game.var = true;
				
				y = y - aceler;
				// a cada tick y-10, y-10-9.....;
				aceler--;
				contFrames--;
			
			}
//se a altura maxima foi alcançada a variavel upDown se altera pra decida;	
// para se concluir o salto, na parte da decida se coloca como 
//de 13 a 0, são 14 unidades, e não 13, por isso se coloca, 
//contFrames <= 1;
	if(contFrames <= 0) { 
		
		upDown = -1;
		aceler = 0;
		
		}
		
	if(upDown == -1) {
		
//operação depois 13 depois if(contFrames == 13), não foi somado o 13;
//se o if(contFrames==13) fosse colocado antes da operação, depois q 
//fosse encerrado a execução do if seria feito mais operações, por isso não funfa;
				
		        y = y + aceler;
				aceler++;
				contFrames++;
				
				if(contFrames>maxAceler) {
				
					up = false;
					upDown=1;
					aceler=maxAceler;
			
				
				}
				
			}
			
		}
		
		
		if(right) {
			
				if(x > 160 -14) {x=x-speed;}
		x=x+speed;
		
		frames++;
		if (frames >= maxframes) {
			frames = 0;
			
			curAnimation++;
			
			if(curAnimation >= maxAnimation) {
				curAnimation=2;
			}}
		}
		if(left) {
			
			
			if(x<0) {x=x+speed;}
			x=x-speed;
			
			
			frames++;
			if (frames >= maxframes) {
				frames = 0;
				curAnimation++;
				
				if(curAnimation >= maxAnimation) {
					curAnimation=2;
				}}
			
		}
			
				
		}
		

	
	public void render(Graphics h, BufferedImage[] player) {
		
		if(right && left) {System.out.println("oi");
		if(cRL==1) {
		h.drawImage(player[1],x,y,cRL*24,37,null);}
		else {h.drawImage(player[1],x+24,y,cRL*24,37,null);}
		}
		
else if(up) {
			
			
			
			if(cRL == 1) {
			h.drawImage(player[0],x,y,cRL*24,31,null);}
		
		else {
			h.drawImage(player[0],x+24,y,cRL*24,31,null);}
			
		}
		
		else if(right) {
			cRL = 1;
			
			
			if(curAnimation == 0) {
			h.drawImage(player[curAnimation],x,y,cRL*16,37,null);}
			if(curAnimation == 1) {
				h.drawImage(player[curAnimation],x,y,cRL*24,37,null);}
			if(curAnimation == 2) {
				h.drawImage(player[curAnimation],x,y,cRL*24,37,null);}
			if(curAnimation == 3) {
				h.drawImage(player[curAnimation],x,y,cRL*22,37,null);}
			if(curAnimation == 4) {
				h.drawImage(player[curAnimation],x,y,cRL*22,37,null);}
			if(curAnimation == 5) {
				h.drawImage(player[curAnimation],x,y,cRL*22,37,null);}
			
				
		}
		
		
		else if(left){
		cRL = -1;
		
		if(curAnimation == 0) {
			h.drawImage(player[curAnimation],x+16,y,cRL*16,37,null);}
			if(curAnimation == 1) {
				h.drawImage(player[curAnimation],x+24+12,y,cRL*24,37,null);}
			if(curAnimation == 2) {
				h.drawImage(player[curAnimation],x+24+12,y,cRL*24,37,null);}
			if(curAnimation == 3) {
				h.drawImage(player[curAnimation],x+22+11,y,cRL*22,37,null);}
			if(curAnimation == 4) {
				h.drawImage(player[curAnimation],x+22+11,y,cRL*22,37,null);}
			if(curAnimation == 5) {
				h.drawImage(player[curAnimation],x+22+11,y,cRL*22,37,null);}
		}
		
		
		
		else {
			
			if(cRL==1) {
				
					
						h.drawImage(player[1],x,y,cRL*24,37,null);
			}
		
		else {
			
				
					h.drawImage(player[1],x+24,y,cRL*24,37,null);
		
	}
		h.dispose();	
	}}
	
	public Sprite1(String path, int x, int y) {
		this.x = x;
		this.y = y;
		
		try {
			sprite = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	BufferedImage getSprite(int x, int y, int width, int height) {
		return sprite.getSubimage(x, y, width, height);
	}

}
