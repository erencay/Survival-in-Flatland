package dev.DualKeys.SIF;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Launcher {

    public String title;
    public int width, height;
    
    public Launcher(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createWindow();
    }
    
    public void createWindow() {
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);
        frame.setFocusable(false);
        frame.setResizable(false);
        frame.setVisible(true);
        
        Canvas canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setVisible(true);
    }
    
    public static void main(String[] args) {
        new Launcher("Survival in Flatland", 800, 608);
    }
    
}