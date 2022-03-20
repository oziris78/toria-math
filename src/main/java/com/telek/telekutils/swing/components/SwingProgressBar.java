package com.telek.telekutils.swing.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ProgressBarUI;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public class SwingProgressBar {

    private JProgressBar bar;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public SwingProgressBar(){
        this.bar = new JProgressBar();
    }

    public SwingProgressBar(int orient) {
        this.bar = new JProgressBar(orient);
    }

    public SwingProgressBar(int min, int max) {
        this.bar = new JProgressBar(min, max);
    }

    public SwingProgressBar(int orient, int min, int max) {
        this.bar = new JProgressBar(orient, min, max);
    }

    ///////////////
    /*  METHODS  */
    ///////////////

    public SwingProgressBar addChangeListener(ChangeListener l){
        this.bar.addChangeListener(l);
        return this;
    }
    public SwingProgressBar setBorderPainted(boolean b){
        this.bar.setBorderPainted(b);
        return this;
    }
    public SwingProgressBar setIndeterminate(boolean newValue){
        this.bar.setIndeterminate(newValue);
        return this;
    }
    public SwingProgressBar setMaximum(int n){
        this.bar.setMaximum(n);
        return this;
    }
    public SwingProgressBar setOrientation(int newOrientation){
        this.bar.setOrientation(newOrientation);
        return this;
    }
    public SwingProgressBar setString(String s){
        this.bar.setString(s);
        return this;
    }
    public SwingProgressBar setStringPainted(boolean b){
        this.bar.setStringPainted(b);
        return this;
    }
    public SwingProgressBar setUI(ProgressBarUI ui){
        this.bar.setUI(ui);
        return this;
    }
    public SwingProgressBar setValue(int n){
        this.bar.setValue(n);
        return this;
    }
    public SwingProgressBar setBounds(Rectangle bounds){
        this.bar.setBounds(bounds);
        return this;
    }
    public SwingProgressBar setBackground(Color bgColor){
        this.bar.setBackground(bgColor);
        return this;
    }
    public SwingProgressBar setForeground(Color fgColor){
        this.bar.setForeground(fgColor);
        return this;
    }
    public SwingProgressBar setCursor(Cursor cursor){
        this.bar.setCursor(cursor);
        return this;
    }
    public SwingProgressBar setEnabled(boolean isEnabled){
        this.bar.setEnabled(isEnabled);
        return this;
    }
    public SwingProgressBar setFont(Font font){
        this.bar.setFont(font);
        return this;
    }
    public SwingProgressBar setVisible(boolean isVisible){
        this.bar.setVisible(isVisible);
        return this;
    }
    public SwingProgressBar addActionListener(ChangeListener changeListener){
        this.bar.addChangeListener(changeListener);
        return this;
    }
    public SwingProgressBar setLayout(LayoutManager layout){
        this.bar.setLayout(layout);
        return this;
    }
    public SwingProgressBar setBorder(Border border){
        this.bar.setBorder(border);
        return this;
    }
    public SwingProgressBar setOpaque(boolean isOpaque){
        this.bar.setOpaque(isOpaque);
        return this;
    }
    public SwingProgressBar setToolTipText(String toolTipText){
        this.bar.setToolTipText(toolTipText);
        return this;
    }
    public SwingProgressBar setMinimum(int minimum){
        this.bar.setMinimum(minimum);
        return this;
    }
    public SwingProgressBar addMouseListener(MouseListener mouseListener){
        this.bar.addMouseListener(mouseListener);
        return this;
    }
    public SwingProgressBar addMouseMotionListener(MouseMotionListener mouseMotionListener){
        this.bar.addMouseMotionListener(mouseMotionListener);
        return this;
    }
    public SwingProgressBar addMouseWheelListener(MouseWheelListener mouseWheelListener){
        this.bar.addMouseWheelListener(mouseWheelListener);
        return this;
    }



    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////

    public JProgressBar getBar() {
        return bar;
    }
}
