import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Type {

	
	boolean right;
	boolean left;
	int x;
	int y;
	private BufferedImage type;	
	
	
	
	
	
	public Type(String path, int x, int y) {
		this.x = x;
		this.y = y;
	try {
		type = ImageIO.read(getClass().getResource(path));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void tick() {
		if(right) {x++;}
		if(left) {x--;}
	}
	
	public void render(BufferedImage player, Graphics h) {
		h.drawImage(player,x,y,16,16, null);
	}
	
	
	public BufferedImage getSprite(int x, int y, int width, int height ) {	
		return type.getSubimage(x, y, width, height);
	}
	
	
	
	
	
}
