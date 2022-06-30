package com.mumu.puzzlearea;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickPos implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		int gy = e.getY();
		int gx = e.getX();
		int y = 0;
		int x = 0;
		if(DrawCanvas.grid >= gy||DrawCanvas.grid >= gx||7*DrawCanvas.grid <= gy||9*DrawCanvas.grid <= gx) {
			for(int dy = 8;dy > 0;dy--) {
				if(dy*DrawCanvas.grid > gy) {
					y = dy-1;
				}
			}
			for(int dx = 10;dx > 0;dx--) {
				if(dx*DrawCanvas.grid > gx) {
					x = dx-1;
				}
			}
			System.out.println(gy+","+gx);
			System.out.println(y+","+x);
			
			if(cor(y,x)) {
				new Start().moveTile(y, x);
			}else {
				System.out.println("角");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}


	public boolean cor(int y,int x) {
		boolean tf = true;
		if(x == 0&&y == 0) {
			tf = false;
		}
		if(x == 0&&y == 7) {
			tf = false;
		}
		if(x == 9&&y == 0) {
			tf = false;
		}
		if(x == 9&&y == 7) {
			tf = false;
		}
		
		return tf;
		
	}
}
