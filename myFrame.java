package View;
import Model.*;
import io.logicWriter;
import io.logicReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class myFrame extends JFrame
{
    private String fileNameForSave;
    private String fileNameForOpen;
    private boolean openFile = false;
    private boolean saveFile = false;
    private myPanel panel = new myPanel();

    public String getFileNameForSave() {return fileNameForSave;}
    public String getFileNameForOpen() {return fileNameForOpen;}
    public boolean getOpenFile() {return openFile;}
    public boolean getSaveFile() {return saveFile;}
    public myFrame()
    {
        super("LogicCalc");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenu();
        this.add(panel);
        panel.setBounds(50, 50, 1200, 500);
        this.setBounds(50, 50, 1200, 500);
        this.setVisible(true);
    }

    public void createMenu()
    {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        jMenuBar.add(file);
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        file.add(open);
        file.add(save);
        open.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (panel.getSizeLabels() != 0)
                {
                    JOptionPane.showMessageDialog(myFrame.this, "Panel Must Be Empty!");
                }
                if (panel.getCanMove() == true && panel.getDelete1() == false && panel.getDelete2() == false
                        && panel.getGetRes() == false && panel.getSizeLabels() == 0)
                {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int flag = fileChooser.showOpenDialog(myFrame.this);
                    if (flag == 0)
                    {
                        File file = fileChooser.getSelectedFile();
                        fileNameForOpen = file.getPath();
                        openFile = true;
                        if (checkFileName(fileNameForOpen) == false)
                        {
                            JOptionPane.showMessageDialog(myFrame.this, "Uncorrect File Extension\nOnly .txt");
                            openFile = false;
                        }
                        if (openFile == true)
                        {
                            logicReader reader = new logicReader(fileNameForOpen);
                            try
                            {
                                reader.loadFromFile(panel);
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        });
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (panel.getCanMove() == true && panel.getDelete1() == false && panel.getDelete2() == false
                        && panel.getGetRes() == false && panel.getSizeLabels() > 0)
                {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int flag = fileChooser.showSaveDialog(myFrame.this);
                    if (flag == 0)
                    {
                        File file = fileChooser.getSelectedFile();
                        fileNameForSave = file.getPath();
                        saveFile = true;
                        if (checkFileName(fileNameForSave) == false)
                        {
                            JOptionPane.showMessageDialog(myFrame.this, "Uncorrect File Extension");
                            saveFile = false;
                        }
                        if (saveFile == true)
                        {
                            logicWriter writer = new logicWriter(fileNameForSave);
                            try
                            {
                                writer.saveToFile(panel);
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                        }
                    }
                }

            }
        });
        this.setJMenuBar(jMenuBar);
        this.revalidate();
    }

    public boolean checkFileName(String str)
    {
        String substr = new String(".txt");
        return str.contains(substr);
    }
}

