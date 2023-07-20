package net.argus.chessplus.ui.piece;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class Piece {
	
	protected Image img;
	protected int woff = 0;
	protected int hoff = 0;
	
	public Piece(Image img) {
		this.img = img;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(img, woff, hoff, null);
		
	}

}
