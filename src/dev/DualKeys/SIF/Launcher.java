package dev.DualKeys.SIF;

import javax.swing.*;
import java.awt.*;

public class Launcher {

    public JFrame frame;
    public Canvas canvas;

    public String title;
    public int width, height;

    public Launcher(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        createWindow();
    }

    public void createWindow() {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public static void main(String[] args) {
        new Game("Survival in Flatland", 800, 672);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

}
