package net.argus.chessplus.ui;

import javax.swing.JFrame;

import net.argus.chessplus.core.team.Team;
import net.argus.chessplus.ui.piece.PieceImage;

public class ChessFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3301280957658229714L;
	
	public ChessFrame() {
		super("Chess Plus");
		setDefaultCloseOperation(3);
		setSize(700, 700);
		setLocationRelativeTo(null);
		setIconImage(PieceImage.getImage(true, Team.WHITE, "knight", 30));
	}

}
