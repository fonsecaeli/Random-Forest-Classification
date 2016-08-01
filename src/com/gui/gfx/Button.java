package com.gui.gfx;
import java.awt.Graphics;
import java.awt.Color;
public class Button extends Interactable {
	private String name;
	private BufferedImage image;
	public static final int XBORDER = 2, YBORDER = 2;
	public static final int OFF = 0, HOVER = 1, CLICK = 2;
	
	public Button(int x, int y, String name){
		this(x, y, Font.charWidth*name.length()+(2*XBORDER), Font.charHeight+(2*YBORDER), name);
	}
	
	private Button(int x, int y, int width, int height, String name){
		super(x, y, width, height);
		this.name = name;
		initImage();
		refreshImage(OFF);
	}
	
	public final String getName(){
		return name;
	}
	
	public void onClick(MouseEvent me){
		
	}
	
	public final void render(Screen screen){
		screen.drawImage(image, getX(), getY(), null);
	}
	
	public void refreshImage(int status){
		Graphics g = image.getGraphics();
		
		if (status == OFF)
			g.setColor(new Color(175, 175, 175));
		else if (status == HOVER)
			g.setColor(new Color(75, 75, 75));
		else if (status == CLICK)
			g.setColor(new Color(77, 144, 254));
		else g.setColor(Color.BLACK);
		
		g.drawRect(0, 0, image.getWidth()-1, image.getHeight()-1);
		g.setColor(new Color(255, 255, 255));
		g.drawRect(1, 1, image.getWidth()-2, image.getHeight()-2);
	}
	
	private void initImage(){
		BufferedImage text = Font.stringToBufferedImage(name);
		image = new BUfferedImage(text.getWidth()+(2*XBORDER), text.getHeight+(2*YBORDER), BUfferedImage.TYPE_INT_ARGB);
		image.getGraphics().drawImage(text, XBORDER, YBORDER, null);
	}
}
