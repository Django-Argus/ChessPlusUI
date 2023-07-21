package net.argus.chessplus.ui.piece;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Piece {
	
	private static List<Piece> pieces = new ArrayList<Piece>();
	
	public static final Piece PAWN = new Piece("pawn", 0, 0);
	public static final Piece ROOK = new Piece("rook", 0, 0);
	public static final Piece KNIGHT = new Piece("knight", 0, 0);
	public static final Piece BISHOP = new Piece("bishop", 0, 0);
	public static final Piece QUEEN = new Piece("queen", 0, 0);
	public static final Piece KING = new Piece("king", 0, 0);
	
	private String piece;
	private int woff = 0;
	private int hoff = 0;
	
	public Piece(String piece, int woff, int hoff) {
		this.piece = piece;
		this.woff = woff;
		this.hoff = hoff;
		
		pieces.add(this);
	}
	
	public void draw(Graphics g, Image img) {
		g.drawImage(img, woff, hoff, null);	
	}
	
	public static Piece getPiece(String name) {
		for(Piece p : pieces)
			if(p.piece.toLowerCase().equals(name.toLowerCase()))
				return p;
		return null;
	}	

}
