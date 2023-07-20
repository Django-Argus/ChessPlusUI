package net.argus.chessplus.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.argus.chessplus.core.Location;
import net.argus.chessplus.core.board.ChessBoard;
import net.argus.chessplus.core.pieces.ChessPiece;
import net.argus.chessplus.core.team.Direction;
import net.argus.chessplus.core.team.Team;
import net.argus.chessplus.ui.piece.PieceImage;
import net.argus.util.ThreadManager;

public class ChessBoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1315066335501987380L;
	
	private Location first, second;
	
	public static final Color BLACK = Color.decode("#7a9db1");
	public static final Color WHITE  = Color.decode("#d6e1e7");
	
	private ChessBoard board;
	private int size;
	
	private Team last = Team.BLACK;
	private Team yTeam;
	
	public ChessBoardPanel(ChessBoard board, int size, Team yTeam) {
		this.board = board;
		this.size = size;
		this.yTeam = yTeam;
		setPreferredSize(new Dimension(board.getWigth() * size, board.getHeight() * size));
		addMouseListener(getClickMouseListener());
	}
	
	private MouseListener getClickMouseListener() {
		return new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Location loc = new Location(Character.toString((char) (getX(e.getX() / size) + 97)) + (getY(e.getY() / size)));
				if(first == null && second == null) {
					ChessPiece piece = board.getPiece(loc);
					if(piece == null)
						return;
					
					if(!goodTeam(piece.getTeam()))
						return;

					first = loc;
				}else if(first != null && second == null)
					second = loc;
				else if(first != null && second != null) {
					ChessPiece piece = board.getPiece(loc);
					if(piece == null)
						return;
					
					if(!goodTeam(piece.getTeam()))
						return;
					first = loc;
					second = null;
				}
				
				if(second != null)
					move(first, second);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		};
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.translate(10, 10);
		int offX = 0, offY = 0;
		for(int y = 0; y < board.getHeight(); y++) {
			for(int x = 0; x < board.getWigth(); x++) {
				g.setColor(getColor(new Point(x, y)));
					
				g.fillRect(0, 0, size, size);
				offX += size;
				g.translate(size, 0);
			}
			offX += -size*board.getWigth();
			offY += size;
			g.translate(-size*board.getWigth(), size);
		}
		g.translate(-offX, -offY);
		
		drawPiece(g);
	}
	
	protected void drawPiece(Graphics g) {
		g.translate(0, 3);
		for(int y = 0; y < board.getHeight(); y++) {
			for(int x = 0; x < board.getWigth(); x++) {
				
				ChessPiece piece = board.getPiece(new Location((String) ((char) (getX(x) + 97) + Integer.toString(getY(y)))));
				if(piece != null)
					g.drawImage(PieceImage.getImage(true, piece.getTeam(), piece.getName(), size), 0, 0, null);
				g.translate(size, 0);
			}
			g.translate(-size*board.getWigth(), size);
		}
	}
	
	public static Color getColor(Point p) {
		
		boolean imp = p.x % 2 == 0;
		
		if(imp)
			return p.y % 2 == 0?WHITE:BLACK;
		else
			return p.y % 2 == 0?BLACK:WHITE;
		
	}
	
	public ChessBoard getBoard() {
		return board;
	}
	
	private int getY(int y) {
		if(yTeam != null && yTeam.getDir().equals(Direction.SOUTH))
			return y + 1;
		
		return board.getHeight() - y;
	}
	
	private int getX(int x) {
		if(yTeam != null && yTeam.getDir().equals(Direction.SOUTH))
			return board.getWigth() - x - 1;
		
		return x;
	}
	
	public boolean goodTeam(Team team) {
		if(yTeam != null && yTeam.equals(team))
			return team != last;
		
		return false;
	}
	
	public void move(Location first, Location second) {
		ThreadManager.startThread(new Thread(() -> {
			ChessPiece piece = board.getPiece(first);
			if(board.move(first, second).isOk())
				last = piece.getTeam();

			SwingUtilities.invokeLater(() -> {
				repaint();						
			});			
		}));
	}
	
}
