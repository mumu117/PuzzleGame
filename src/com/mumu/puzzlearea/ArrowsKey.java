package com.mumu.puzzlearea;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowsKey implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			new Start().movePlayer(0);
			break;
		case KeyEvent.VK_DOWN:
			new Start().movePlayer(1);
			break;
		case KeyEvent.VK_LEFT:
			new Start().movePlayer(2);
			break;
		case KeyEvent.VK_RIGHT:
			new Start().movePlayer(3);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
