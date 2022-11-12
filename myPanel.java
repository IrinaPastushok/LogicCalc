package View;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class myPanel extends JPanel
{
    private ArrayList<myLabel> labels = new ArrayList<>();
    private ArrayList<myPoint> list = new ArrayList<>();
    private ArrayList<Integer> isLine = new ArrayList<>();
    private ArrayCircuit model = new ArrayCircuit();
    private Point start, end;
    private int x = 0, y = 0;
    private boolean isDrawing = false;
    private boolean canMove = true;
    private int countLabels = 0;
    private boolean delete1 = false;
    private boolean delete2 = false;
    private MouseAdapter mouse1;
    private MouseMotionListener mouse2;
    private MouseAdapter d1;
    private boolean getRes = false;
    private boolean haveOut = false;
    private boolean errors = false;
    private StringBuilder sErr = new StringBuilder("");
    private JButton button8 = new JButton("OUTPUT");

    public void deleteSErr() {sErr.delete(0, sErr.capacity() - 1);}
    public void setColorButton8() {button8.setBackground(Color.RED);}
    public void setHaveOut(boolean t) {haveOut = t;}
    public int getSizeLabels() {return labels.size();}
    public myLabel getLabels(int i) {return labels.get(i);}
    public int getSizeList() {return list.size();}
    public myPoint getList(int i) {return list.get(i);}
    public void addList(myPoint h) {list.add(h);}
    public int getIsLine(int i) {return isLine.get(i);}
    public void addIsLine(int i) {isLine.add((Integer) i);}
    public boolean getCanMove() {return canMove;}
    public boolean getDelete1() {return delete1;}
    public boolean getDelete2() {return delete2;}
    public boolean getGetRes() {return getRes;}
    public boolean getHaveOut() {return haveOut;}
    public JButton getJButton() {return button8;}
    public myPanel()
    {
        start = null;
        end = null;
        addButton();
    }

    public void drawMyLine()
    {
        MouseAdapter m = new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent arg0) {}

            @Override
            public void mouseEntered(MouseEvent arg0) {}

            @Override
            public void mouseExited(MouseEvent arg0) {}

            @Override
            public void mousePressed(MouseEvent me)
            {
                if(!isDrawing)
                {
                    start = me.getPoint();
                    isDrawing = !isDrawing;
                }
                else
                {
                    end = me.getPoint();
                    myPoint pt = new myPoint(start, end);
                    list.add(pt);
                    isLine.add(1);
                    int i = 0;
                    myLabel s;
                    myLabel e;
                    int begin = -1;
                    int finish = -1;
                    for (; i < labels.size(); i++)
                    {
                        if (labels.get(i).haveLine(pt.getStart()) == true)
                        {
                            begin = i;
                        }
                        if (labels.get(i).haveLine(pt.getEnd()) == true)
                        {
                            finish = i;
                        }
                    }
                    if ((begin == -1 || finish == -1) || (begin == finish))
                    {
                        list.remove(list.size() - 1);
                        isLine.remove(isLine.size() - 1);

                        repaint();
                    }
                    else
                    {
                        labels.get(begin).setPoints(list.size() - 1);
                        labels.get(finish).setPoints(list.size() - 1);
                    }
                    start = null;
                    end = null;
                    isDrawing = false;
                }
            }

            @Override
            public void mouseReleased(MouseEvent me){ }
        };
        addMouseListener(m);
        mouse1 = m;

        MouseMotionListener h = new MouseMotionListener()
        {
            @Override
            public void mouseDragged(MouseEvent me) {}

            @Override
            public void mouseMoved(MouseEvent me)
            {
                end = me.getPoint();
                repaint();
            }
        };
        addMouseMotionListener(h);
        mouse2 = h;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        super.paintComponent(g2);
        g.setColor(Color.GREEN);
        for (int i = 0; i < list.size(); i++)
        {
            if (isLine.get(i) ==1 )
            {
                myPoint p =list.get(i);
                g.drawLine((int) p.getStart().getX(), (int) p.getStart().getY(), (int) p.getEnd().getX(),
                        (int) p.getEnd().getY());
            }
        }
        if ((start != null) && (end != null))
        {
            g.drawLine(start.x, start.y, end.x, end.y);
        }
    }

    public void addButton()
    {
        JButton button0 = new JButton("NOT");
        button0.setBackground(Color.ORANGE);
        button0.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && delete2 == false && getRes == false)
                {
                    move("NOT", countLabels,1000, 50);
                    repaint();
                }
            }
        });
        this.add(button0);

        JButton button1 = new JButton("AND");
        button1.setBackground(Color.MAGENTA);
        button1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && delete2 == false && getRes == false)
                {
                    move("AND",countLabels, 1000, 50);
                    repaint();
                }
            }
        });
        this.add(button1);

        JButton button2 = new JButton("OR");
        button2.setBackground(Color.MAGENTA);
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && delete2 == false && getRes == false)
                {
                    move("OR",countLabels, 1000, 50);
                    repaint();
                }
            }
        });
        this.add(button2);

        JButton button3 = new JButton("->");
        button3.setBackground(Color.MAGENTA);
        button3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && delete2 == false && getRes == false)
                {
                    move("->", countLabels, 1000, 50);
                    repaint();
                }
            }
        });
        this.add(button3);

        JButton button5 = new JButton("+");
        button5.setBackground(Color.MAGENTA);
        button5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && delete2 == false && getRes == false)
                {
                    move("+", countLabels, 1000, 50);
                    repaint();
                }
            }
        });
        this.add(button5);

        JButton button6 = new JButton("~");
        button6.setBackground(Color.MAGENTA);
        button6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && delete2 == false && getRes == false)
                {
                    move("~", countLabels, 1000, 50);
                    repaint();
                }
            }
        });
        this.add(button6);

        JButton button70 = new JButton("INPUT0");
        button70.setBackground(Color.CYAN);
        button70.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && delete2 == false && getRes == false)
                {
                    move("INPUT0", countLabels, 1000, 50);
                    repaint();
                }
            }
        });
        this.add(button70);

        JButton button71 = new JButton("INPUT1");
        button71.setBackground(Color.CYAN);
        button71.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && delete2 == false && getRes == false)
                {
                    move("INPUT1", countLabels, 1000, 50);
                    repaint();
                }
            }
        });
        this.add(button71);

        button8.setBackground(Color.CYAN);
        button8.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && delete2 == false && getRes == false
                        && haveOut == false)
                {
                    haveOut = true;
                    button8.setBackground(Color.RED);
                    move("OUTPUT", countLabels,1000, 50);
                    repaint();
                }
            }
        });
        this.add(button8);

        JButton button9 = new JButton("LINE");
        button9.setBackground(Color.CYAN);
        button9.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (delete1 == false && delete2 == false && getRes == false)
                {
                    if (canMove == true)
                    {
                        drawMyLine();
                        canMove = false;
                        button9.setBackground(Color.RED);
                        for (int i = 0; i < labels.size(); i++) { labels.get(i).removeAdapters();}
                    }
                    else
                    {
                        canMove = true;
                        removeMouseListener(mouse1);
                        removeMouseMotionListener(mouse2);
                        button9.setBackground(Color.CYAN);
                        for (int i = 0; i < labels.size(); i++)
                        {
                            if (labels.get(i).getCountPoints() == 0)
                            {
                                labels.get(i).doneAdapters();
                            }
                        }
                    }
                    repaint();
                }
            }
        });
        this.add(button9);

        JButton buttonD1 = new JButton("RELISE LABEL"); //удаляет линии у лейбла
        buttonD1.setBackground(Color.CYAN);
        buttonD1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete2 == false && getRes == false)
                {
                    MouseAdapter m = new MouseAdapter()
                    {
                        @Override
                        public void mouseClicked(MouseEvent arg0) {}

                        @Override
                        public void mouseEntered(MouseEvent arg0) {}

                        @Override
                        public void mouseExited(MouseEvent arg0) {}

                        @Override
                        public void mousePressed(MouseEvent me)
                        {
                            Point r = me.getPoint();
                            int tmpID = -1;
                            int pos = -1;
                            for (int i = 0; i < labels.size(); i++)
                            {
                                if (labels.get(i).haveLine(r) == true)
                                {
                                    tmpID = labels.get(i).getID();
                                    pos = i;
                                    break;
                                }
                            }
                            if (tmpID != -1)
                            {
                                for (int i = 0; i < list.size(); i++)
                                {
                                    if (labels.get(pos).haveLine(list.get(i).getStart()) == true
                                            || labels.get(pos).haveLine(list.get(i).getEnd()) == true)
                                    {
                                        isLine.set(i, 0);
                                    }
                                }
                                for (int i = 0; i < labels.size(); i++)
                                {
                                    for (int j = 0; j < list.size(); j++)
                                    {
                                        if ((labels.get(i).haveLine(list.get(j).getStart()) == true
                                                ||labels.get(i).haveLine(list.get(j).getEnd()) == true) &&
                                                isLine.get(j) == 0)
                                        {
                                            labels.get(i).removePoints(j);
                                        }
                                    }
                                }
                                repaint();
                            }
                        }

                        @Override
                        public void mouseReleased(MouseEvent me) {  }
                    };


                    if (delete1 == false)
                    {
                        addMouseListener(m);
                        d1 = m;
                        delete1 = true;
                        buttonD1.setBackground(Color.RED);
                        for (int i = 0; i < labels.size(); i++) { labels.get(i).removeAdapters();}
                    }
                    else
                    {
                        removeMouseListener(d1);
                        delete1 = false;
                        buttonD1.setBackground(Color.CYAN);
                        for (int i = 0; i < labels.size(); i++)
                        {
                            if (labels.get(i).getCountPoints() == 0)
                            {
                                labels.get(i).doneAdapters();
                            }
                        }
                    }
                    repaint();
                }
            }
        });
        this.add(buttonD1);

        JButton buttonD2 = new JButton("DELETE LABEL"); //удаляет лейбл и прилежащие к нему линии
        buttonD2.setBackground(Color.CYAN);
        buttonD2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && getRes == false)
                {
                    MouseAdapter m = new MouseAdapter()
                    {
                        @Override
                        public void mouseClicked(MouseEvent arg0) {}

                        @Override
                        public void mouseEntered(MouseEvent arg0) {}

                        @Override
                        public void mouseExited(MouseEvent arg0) {}

                        @Override
                        public void mousePressed(MouseEvent me)
                        {
                            Point r = me.getPoint();
                            int tmpID = -1;
                            int pos = -1;
                            for (int i = 0; i < labels.size(); i++)
                            {
                                if (labels.get(i).haveLine(r) == true)
                                {
                                    tmpID = labels.get(i).getID();
                                    pos = i;
                                    break;
                                }
                            }
                            if (tmpID != -1)
                            {
                                if (labels.get(pos).getName() == "OUTPUT")
                                {
                                    haveOut = false; button8.setBackground(Color.CYAN);
                                }
                                for (int i = 0; i < list.size(); i++)
                                {
                                    if (labels.get(pos).haveLine(list.get(i).getStart()) == true
                                            || labels.get(pos).haveLine(list.get(i).getEnd()) == true)
                                    {
                                        isLine.set(i, 0);
                                    }
                                }
                                for (int i = 0; i < labels.size(); i++)
                                {
                                    for (int j = 0; j < list.size(); j++)
                                    {
                                        if ((labels.get(i).haveLine(list.get(j).getStart()) == true
                                                ||labels.get(i).haveLine(list.get(j).getEnd()) == true) &&
                                                isLine.get(j) == 0)
                                        {
                                            labels.get(i).removePoints(j);
                                        }
                                    }
                                }
                                remove(labels.get(pos));
                                labels.remove(pos);
                                revalidate();
                                repaint();
                            }
                        }

                        @Override
                        public void mouseReleased(MouseEvent me) {  }
                    };


                    if (delete2 == false)
                    {
                        addMouseListener(m);
                        d1 = m;
                        delete2 = true;
                        buttonD2.setBackground(Color.RED);
                        for (int i = 0; i < labels.size(); i++) { labels.get(i).removeAdapters();}
                    }
                    else
                    {
                        removeMouseListener(d1);
                        delete2 = false;
                        buttonD2.setBackground(Color.CYAN);
                        for (int i = 0; i < labels.size(); i++)
                        {
                            if (labels.get(i).getCountPoints() == 0)
                            {
                                labels.get(i).doneAdapters();
                            }
                        }
                    }
                    repaint();
                }
            }
        });
        this.add(buttonD2);

        JButton button10 = new JButton("GET RES");
        button10.setBackground(Color.GREEN);
        button10.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (canMove == true && delete1 == false && delete2 == false && labels.size() != 0)
                {
                    if (getRes == false)
                    {
                        getRes = true;
                        button10.setBackground(Color.RED);
                        doneBack();
                        //doneConnections();
                        errors = model.findingErrors(sErr);
                        if (errors == true)
                        {
                            StringBuilder tmp = new StringBuilder("ERRORS!\nCAN'T CALCULATE(\n");
                            tmp.append(sErr);
                            String s = new String(tmp);
                            JOptionPane.showMessageDialog(myPanel.this, s);
                        }
                        else
                        {
                            model.bfs();

                            /*StringBuilder sb = new StringBuilder("Res: ");
                            String s = Integer.toString(model.getArrayQueue(model.getSizeArrayQueue() - 1).getOutput());
                            sb.append(s);
                            sb.append("\n");
                            for (int i = 0; i < model.getSizeArray(); i++)
                            {
                                sb.append(model.getArray(i).getOperation());
                            }
                            
                            String t = new String(sb);*/
                            //JOptionPane.showMessageDialog(myPanel.this, "<html><h2>RES</h2><i>))))</i>");
                            JOptionPane.showMessageDialog(myPanel.this, model.BuildString());
                        }
                    }
                    else
                    {
                        getRes = false;
                        button10.setBackground(Color.GREEN); 
                        if (errors == true) {sErr.delete(0, sErr.capacity() - 1);}
                        model.clearClass(errors);
                        /*if (errors == false)
                        {
                            for (int i = 0; i < model.getSizeArray(); i++)
                            {
                                model.getArray(i).inputsClear();
                                model.getArray(i).outputsClear();
                                model.getArray(i).rollBack();
                                model.arrayQueueClear();
                            }
                        }
                        else
                        {
                            for (int i = 0; i < model.getSizeArray(); i++)
                            {
                                sErr.delete(0, sErr.capacity() - 1);
                                model.getArray(i).inputsClear();
                                model.getArray(i).outputsClear();
                            }
                        }
                        model.clearArray();*/
                    }
                }
                repaint();
            }
        });
        this.add(button10);
    }

    public void doneBack()
    {
        for (int i = 0; i < labels.size(); i++)
        {
            model.addArray(labels.get(i).getName());
        }
        for (int i = 0; i < list.size(); i++)
        {
            if (isLine.get(i) == 1)
            {
                myPoint tmp = list.get(i);
                int begin = 0, finish = 0;
                for (int j = 0; j < labels.size(); j++)
                {
                    if (labels.get(j).haveLine(tmp.getStart()) == true) {begin = j;}
                    if (labels.get(j).haveLine(tmp.getEnd()) == true) {finish = j;}
                }
                model.getArray(begin).addOutputs(model.getArray(finish));
                model.getArray(finish).addInputs(model.getArray(begin));
            }
        }
    }
    /*public void bfs()
    {
        for (int i = 0; i < model.getSizeArray(); i++)
        {
            if (model.getArray(i).getName() == "INPUT0" || model.getArray(i).getName() == "INPUT1")
            {
                model.addArrayQueue(model.getArray(i));
                model.getArray(i).doStep();
            }
        }
        int i = 0;
        boolean checkOutput = false;
        while(checkOutput == false)
        {
            boolean checkReady = true;

            if (model.getArrayQueue(i).getIsReady() == false)
            {
                checkReady = false;
                Circuit tmp = model.getArrayQueue(i);
                model.addArrayQueue(tmp);
            }
            if (checkReady == true)
            {
                if (model.getArrayQueue(i).getName() == "OUTPUT")
                {
                    checkOutput = true;
                }
                for(int j = 0; j < model.getArrayQueue(i).getSizeOutputs(); j++)
                {
                    if (model.getArrayQueue(i).getOutputs(j).getInQueue() == false)
                    {
                        model.addArrayQueue(model.getArrayQueue(i).getOutputs(j));
                    }
                    model.getArrayQueue(i).getOutputs(j).setInput(model.getArrayQueue(i).getOutput());
                    model.getArrayQueue(i).getOutputs(j).doStep();
                }
            }
            i++;
        }
    }

    public boolean findingErrors()
    {
        boolean haveOutput = false;
        boolean haveInput = false;
        boolean haveErr = false;
        for (int i = 0; i < model.getSizeArray(); i++)
        {
            if(model.getArray(i).getSizeInputs() != model.getArray(i).getMaxIns())
            {
                haveErr = true;
                sErr.append("Errors In Inputs! ");
                sErr.append(model.getArray(i).getName());
                sErr.append("\n");
            }
            if (model.getArray(i).getName() == "OUTPUT")
            {
                haveOutput = true;
                if (model.getArray(i).getSizeOutputs() > 0)
                {
                    haveErr = true;
                    sErr.append("OUTPUT Can't Have Outputs!\n");
                }
            }
            if (((model.getArray(i).getSizeOutputs() == 0 || model.getArray(i).getSizeInputs() == 0) &&
                    (model.getArray(i).getName() != "OUTPUT" && model.getArray(i).getName() != "INPUT1"
                            && model.getArray(i).getName() != "INPUT0")) || (model.getArray(i).getSizeOutputs() == 0
                    &&(model.getArray(i).getName() == "INPUT1"
                    || model.getArray(i).getName() == "INPUT0")))
            {
                haveErr = true;
                sErr.append("Empty Operation! ");
                sErr.append(model.getArray(i).getName());
                sErr.append("\n");
            }
            if (model.getArray(i).getName() == "INPUT1" || model.getArray(i).getName() == "INPUT0")
            {
                haveInput = true;
            }
        }
        if (haveInput == false)
        {
            sErr.append("Haven't Input!");
            haveErr = true;
        }
        if (haveOutput == false)
        {
            sErr.append("Haven't Output!");
            haveErr = true;
        }
        return haveErr;
    }

    public void doneConnections()
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (isLine.get(i) == 1)
            {
                myPoint tmp = list.get(i);
                int begin = 0, finish = 0;
                for (int j = 0; j < labels.size(); j++)
                {
                    if (labels.get(j).haveLine(tmp.getStart()) == true) {begin = j;}
                    if (labels.get(j).haveLine(tmp.getEnd()) == true) {finish = j;}
                }
                model.getArray(begin).addOutputs(model.getArray(finish));
                model.getArray(finish).addInputs(model.getArray(begin));
            }
        }
    }*/

    public void move(String s, int id, int t1, int t2)
    {
        this.setLayout(null);
        myLabel label = new myLabel(s, id, t1, t2);
        countLabels++;
        this.add(label);
        label.doneAdapters();
        labels.add(label);
        this.repaint();
    }
}
