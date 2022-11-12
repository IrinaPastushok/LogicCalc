package View;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class myLabel extends JLabel
{
	private int x1, x2, y1, y2, id;
	private ArrayList<Integer> points = new ArrayList<>();
	private String name;
	private MouseAdapter mouse1;
	private MouseMotionListener mouse2;
	private int x = 0, y = 0;

	public void setX1(int pos) {x1 = pos;}
	public void setY1(int pos) {y1 = pos;}
	public String getName() {return name;}
	public int getID() {return id;}
	public void setPoints(int p) { points.add(p); }
	public int getPoints(int pos) { return points.get(pos);}
	public int getCountPoints() { return points.size();}
	public void removePoints(int i) {points.remove((Integer)i);}
	public myLabel() {}
	public myLabel(String s, int num, int pos1, int pos2)
	{
		super(s, JLabel.CENTER);
		name = s;
		id = num;
		x1 = pos1;
		y1 = pos2;
		init();
	}
	public void doneAdapters()
	{
		
		MouseAdapter m = new MouseAdapter()
		{
			public void mousePressed(MouseEvent e) 
			{
				x = e.getX();
				y = e.getY();
			}

			public void mouseReleased(MouseEvent e) {}
		};

		MouseMotionListener h = new MouseMotionListener()
		{
			@Override
			public void mouseMoved(MouseEvent arg0) {}

			@Override
			public void mouseDragged(MouseEvent e) 
			{
				JLabel jl = (JLabel) e.getSource();
				jl.setLocation(jl.getX()+e.getX()-x,jl.getY()+e.getY()-y);
			}
		};

		this.addMouseListener(m);
		this.addMouseMotionListener(h);
		setAdapters(m, h);
	}

	public void setAdapters(MouseAdapter m1, MouseMotionListener m2)
	{
		mouse1 = m1;
		mouse2 = m2;
	}

	public void removeAdapters()
	{
		this.removeMouseListener(mouse1);
		this.removeMouseMotionListener(mouse2);
	}
	public void init()
	{
		this.setBackground(Color.MAGENTA);
		this.setBounds(x1,y1,100,50);
		if (name == "NOT") { this.setBackground(Color.ORANGE);}
		if (name == "INPUT1" || name == "INPUT0" || name == "OUTPUT") { this.setBackground(Color.CYAN);}
		
		this.setOpaque(true);
	}

	public void setSize(int x, int y)
	{
		x1 = x;
		x2 = x1 + 100;
		y1 = y;
		y2 = y1 + 50;
	}

	public boolean haveLine(Point pos)
	{
		this.setSize(getX(), getY());
		int tmpX = (int)pos.getX();
		int tmpY = (int) pos.getY();
		if ((tmpX >= x1 && tmpX <= x2) && (tmpY >= y1 && tmpY <= y2)) return true;
		return false;
	}
	public int getX1() {return x1;}
	public int getX2() {return x2;}
	public int getY1() {return y1;}
	public int getY2() {return y2;}
}
