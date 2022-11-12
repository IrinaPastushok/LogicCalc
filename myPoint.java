package View;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class myPoint
{
    private Point start = null, end = null;

    public myPoint(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }
    public String toString()
    {
        return "Start" + start + "End" + end;
    }
    public Point getStart() { return start;}

    public Point getEnd() { return end;}
}
