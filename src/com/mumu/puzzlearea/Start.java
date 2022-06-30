package com.mumu.puzzlearea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Start {
	
	static JFrame MFrame;
	static JFrame SFrame;
	private static int[][] BArr;
	private static int[][] PlayerP;
	private static int[][] BArrTF;
	private static int[][] allSet;
	private static int[][][] allSets;
	private static int pTurn = 0;
	private static int[] cnt;
	
	
	public static void main(String[] args) {
		new Start().GameSet();
	}
	
	public void GameSet() {
		MFrame = new MFrame("化学式ゲーム",1250,1080,0,0);
		MFrame.setVisible(true);
		SFrame = new MFrame("ミッション",1920-1250,1080,1250,0);
		//SFrame.setVisible(true);
		
		int[][] BArr = new int[8][10];
		int[][] BArrTF = new int[8][10];
		
		BArr =  Board(BArr,0,0,20);
		BArr =  Board(BArr,0,9,20);
		BArr =  Board(BArr,7,0,20);
		BArr =  Board(BArr,7,9,20);
		//BArr =  Board(BArr,3,4,20);
		//BArr =  Board(BArr,4,5,20);
		
		int[] kinds = {10,11,12,13,14,15,16,17,18,19};
		int[] numbs = {20,17,10,05,05,05,05,03,03,03};
		String[] icos = {"H","O","C","N","Cl","Na","Ag","Fe","Cu","S"};
		ArrayList<Integer> RArray = new ArrayList<Integer>();
		
		for(int i = 0;i < kinds.length;i++) {
			for(int j = 0;j < numbs[i];j++) {
				RArray.add(kinds[i]);
			}
		}
		
		Collections.shuffle(RArray);
		//System.out.println(RArray.size());
		int ry = 0;
		int rx = 0;
		
		for(int r:RArray) {
			//System.out.println(r);
			while(BArr[ry][rx] == 20) {
				rx++;
				if(rx > 9) {
					rx=0;
					ry++;
				}
			}
			//System.out.println(ry+","+rx);
			BArr = Board(BArr, ry, rx, r);
			BArrTF = Board(BArrTF, ry, rx, 1);
			rx++;
			if(rx > 9) {
				rx=0;
				ry++;
			}
		}
		
		int[][] PlayerP = new int[8][10];
		PlayerP[0][0] = 1;
		PlayerP[0][9] = 2;
		PlayerP[7][0] = 3;
		PlayerP[7][9] = 4;
		// 1   2   3   4   5    6    7    8    9    10
		//"H","O","C","N","Cl","Na","Ag","Fe","Cu","S"
		int[][] allSet = new int[24][10];
		allSet[0] = new int[]{2,0,0,0,0,0,0,0,0,0};//H2
		allSet[1] = new int[]{0,2,0,0,0,0,0,0,0,0};//O2
		allSet[2] = new int[]{2,1,0,0,0,0,0,0,0,0};//H2O
		allSet[3] = new int[]{1,0,0,0,1,0,0,0,0,0};//HCl
		allSet[4] = new int[]{0,2,1,0,0,0,0,0,0,0};//CO2
		allSet[5] = new int[]{0,0,0,2,0,0,0,0,0,0};//N2
		allSet[6] = new int[]{0,0,0,0,2,0,0,0,0,0};//Cl2
		allSet[7] = new int[]{0,1,0,0,0,0,0,0,1,0};//CuO
		allSet[8] = new int[]{0,0,0,0,1,1,0,0,0,0};//NaCl
		allSet[9] = new int[]{0,0,0,0,1,0,1,0,0,0};//AgCl
		allSet[10] = new int[]{1,1,0,0,0,1,0,0,0,0};//NaOH
		allSet[11] = new int[]{0,0,0,0,0,0,0,0,1,1};//CuS
		allSet[12] = new int[]{0,0,0,0,0,0,0,1,0,1};//FeS
		allSet[13] = new int[]{2,0,0,0,0,0,0,0,0,1};//H2S
		allSet[14] = new int[]{3,0,0,1,0,0,0,0,0,0};//NH3
		allSet[15] = new int[]{4,0,1,0,0,0,0,0,0,0};//CH4
		allSet[16] = new int[]{0,1,0,0,0,0,2,0,0,0};//Ag2O
		allSet[17] = new int[]{0,0,0,0,2,0,0,0,1,0};//CuCl2
		allSet[18] = new int[]{0,0,0,0,2,0,0,1,0,0};//FeCl2
		allSet[19] = new int[]{0,0,0,0,0,0,2,0,0,1};//Ag2S
		allSet[20] = new int[]{1,3,1,0,0,1,0,0,0,0};//NaHCO3
		allSet[21] = new int[]{0,3,1,0,0,2,0,0,0,0};//Na2CO3
		allSet[22] = new int[]{2,4,0,0,0,0,0,0,0,1};//H2SO4
		allSet[23] = new int[]{0,0,7,0,0,0,0,0,0,0};//Diamond
		
		int[][][] allSets = new int[4][24][10];
		for(int i = 0;i < allSets.length;i++) {
			allSets[i] = allSet;
		}
		
		//OutArr(BArr);
		DrawCanvas dc = new DrawCanvas(BArr,numbs,icos,PlayerP,BArrTF);
		dc.addMouseListener(new ClickPos());
		MFrame.addKeyListener(new ArrowsKey());
		MFrame.add(dc);
		
		Start.BArr = BArr;
		Start.BArrTF = BArrTF;
		Start.PlayerP = PlayerP;
		Start.allSet = allSet;
		Start.allSets = allSets;
		MFrame.setVisible(true);
		
		String[] colTeams = {"赤","青","緑","橙"};
		String[] compAll = {"水素分子","酸素分子","水","塩化水素","二酸化炭素","窒素分子","塩素分子","酸化銅","塩化ナトリウム","塩化銀","水酸化ナトリウム","硫化銅","硫化鉄","硫化水素","アンモニア","メタン","酸化銀","塩化銅","塩化鉄","硫化銀","炭酸水素ナトリウム","炭酸ナトリウム","硫酸","ダイヤモンド(7つの原子)"};
		System.out.println("length:"+compAll.length);
		
		SFrame.setLayout(null);
		
		JLabel[][] labels = new JLabel[2][4];
		Font font1 = new Font("MS ゴシック",Font.PLAIN,28);
		for(int i = 0;i < labels[0].length;i++) {
			JLabel lab1 = new JLabel(colTeams[i]);
			lab1.setForeground(DrawCanvas.PlayerC[i]);
			lab1.setBounds(50, i*150,720,100);
			lab1.setFont(font1);
			labels[0][i] = lab1;
			JLabel lab2 = labelSet(compAll);
			lab2.setBounds(100, i*150+50,670,100);
			lab2.setFont(font1);
			labels[1][i] = lab2;
			
			SFrame.add(labels[0][i]);
			SFrame.add(labels[1][i]);
		}
		SFrame.setVisible(true);
	}

	public JLabel labelSet(String[] compAll) {
		Random r = new Random();
		JLabel l = new JLabel();
		l.setText(compAll[r.nextInt(compAll.length)]+"をつくれ！");
		return l;
	}
	
	public boolean comp() {
		boolean tf = false;
		
		
		return tf;
		
	}

	public void moveTile(int py,int px) {
		int clTile = BArr[py][px];
		int plTile = PlayerP[py][px];
		int tfTile = BArrTF[py][px];
		int dir = dir(py, px);
		System.out.println("角じゃない");
		System.out.println();
		
		switch(dir) {
		case 0:
			moveNum(-1, 0, py, px, BArr.length, clTile, plTile,tfTile);
			break;
		case 1:
			moveNum(+1, 0, py, px, BArr.length, clTile, plTile,tfTile);
			break;
		case 2:
			moveNum(0, -1, py, px, BArr[0].length, clTile, plTile,tfTile);
			break;
		case 3:
			moveNum(0, +1, py, px, BArr[0].length, clTile, plTile,tfTile);
			break;
		}
	}
	
	public void moveNum(int y,int x,int py,int px,int n,int ct,int pt,int tt) {
		for(int i = 0;i < n;i++) {
			System.out.println(i);
			if(i == n-1) {
				BArr[py-(y*i)][px-(x*i)] = ct;
				PlayerP[py-(y*i)][px-(x*i)] = pt;
				BArrTF[py-(y*i)][px-(x*i)] = tt;
				
				break;
			}
			
			BArr[py-(y*i)][px-(x*i)] = BArr[py-(y*i)-y][px-(x*i)-x];
			PlayerP[py-(y*i)][px-(x*i)] = PlayerP[py-(y*i)-y][px-(x*i)-x];
			BArrTF[py-(y*i)][px-(x*i)] = BArrTF[py-(y*i)-y][px-(x*i)-x];
		
		}
		pTurn++;
		if(pTurn > 3) {
			pTurn = 0;
		}
		
		MFrame.setVisible(false);
		MFrame.setVisible(true);
	}
	
	// 0 
	//2 3
	// 1
	//↓↓↓
	public int dir(int py,int px) {
		int d = 0;
		if(py == 0) {
			d = 0;
		}
		if(py == 7) {
			d = 1;
		}
		if(px == 0) {
			d = 2;
		}
		if(px == 9) {
			d = 3;
		}
		
		return d; 
	}
	
	public void movePlayer(int dir) {
		switch(dir) {
		case 0:
			movePla(-1,0,indY(),indX());
			break;
		case 1:
			movePla(+1,0,indY(),indX());
			break;
		case 2:
			movePla(0,-1,indY(),indX());
			break;
		case 3:
			movePla(0,+1,indY(),indX());
			break;
		}
		
		//System.out.println(pTurn);
	}
	
	public int indY() {
		
		int n = 0;
		
		for(int dy = 0;dy < PlayerP.length;dy++) {
			for(int dx = 0;dx < PlayerP[dy].length;dx++) {
				if(PlayerP[dy][dx] == pTurn+1) {
					n = dy;
					break;
					
				}
			}
		}
		return n;
	}
	
	public int indX() {
		
		int n = 0;
		
		for(int dy = 0;dy < PlayerP.length;dy++) {
			for(int dx = 0;dx < PlayerP[dy].length;dx++) {
				if(PlayerP[dy][dx] == pTurn+1) {
					n = dx;
					break;
					
				}
			}
		}
		return n;
	}
	
	public void movePla(int y,int x,int py,int px) {
		boolean tf = true;
		System.out.println((py+y)+","+(px+x));
		if(py+y > 7||py+y < 0) {
			tf = false;
		}
		if(px+x > 9||px+x < 0) {
			tf = false;
		}
		if(tf) {
			if(PlayerP[py+y][px+x] == 0&&BArrTF[py+y][px+x] != 0) {
				PlayerP[py][px] = 0;
				PlayerP[py+y][px+x] = pTurn+1;
				BArrTF[py+y][px+x] = 0;
				
				pTurn++;
				if(pTurn > 3) {
					pTurn = 0;
				}
				MFrame.setVisible(false);
				MFrame.setVisible(true);
			}
		}
	}
	
	public int[][] Board(int[][] boa,int y,int x,int n) {
		boa[y][x] = n;//10~20
		
		
		return boa;
		
	}
	
	public void OutArr(int[][] obj) {
		for(int i = 0;i < obj.length;i++) {
			System.out.println(Arrays.toString(obj[i]));
		}
	}

}

class MFrame extends JFrame {
	
	public MFrame(String title,int width,int height,int x,int y) {
		setTitle(title);
		setSize(width,height);
		setLocation(x,y);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

class DrawCanvas extends JPanel{
	Image img = null;
	int[][] BArr = null;
	int[] numbs = null;
	String[] icos = null;
	static int grid = 125;
	static Color[] PlayerC = {Color.RED,Color.BLUE,Color.GREEN,Color.ORANGE};
	int[][] PlayerP = null;
	int[][] BArrTF = null;
	
	public DrawCanvas(int[][] BArr,int[] numbs,String[] icos,int[][] PlayerP,int[][] BArrTF) {
		this.BArr = BArr;
		this.numbs = numbs;
		this.icos = icos;
		this.PlayerP = PlayerP;
		this.BArrTF = BArrTF;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int dy = 0;dy < BArr.length;dy++) {
			for(int dx = 0;dx < BArr[dy].length;dx++) {
				int ind = BArr[dy][dx]-10;
				
				if(ind >= 10) {
					img = Toolkit.getDefaultToolkit().getImage("IMG1//break.png");
				}else {
					img = Toolkit.getDefaultToolkit().getImage("IMG"+String.valueOf(BArrTF[dy][dx])+"//"+icos[ind]+".png");
				}
				//System.out.println(ind);
				//img = Toolkit.getDefaultToolkit().getImage("IMG//"+"H"+".png");
				//画像の表示
				g.drawImage(img,dx*grid,dy*grid,grid,grid,this);
				
				if(PlayerP[dy][dx] != 0) {
					Color col = PlayerC[PlayerP[dy][dx]-1];
					g.setColor(col);
					g.fillOval(dx*grid+38,dy*grid+38,49,49);
				}
			}
		}
	}
}
