package net.argus.chessplus.ui;

import net.argus.chessplus.core.board.ChessBoard;
import net.argus.chessplus.core.board.basic.BasicChessBoard;
import net.argus.chessplus.core.board.basic.BasicChessPromote;
import net.argus.chessplus.core.pieces.ChessPiece;
import net.argus.chessplus.core.pieces.Queen;
import net.argus.chessplus.core.team.Team;

public class TestUI {
	
	public static void main(String[] args) {
		ChessFrame fen = new ChessFrame();
		
		ChessBoardPanel pan = new ChessBoardPanel(new ChessBoard(new BasicChessBoard(new BasicChessPromote() {
			
			@Override
			public ChessPiece promote(ChessBoard board, ChessPiece piece) {
				return new Queen(board);
			}
		})), 80, Team.WHITE);
		fen.setContentPane(pan);
		
		fen.setVisible(true);
	}

}
