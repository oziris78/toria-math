package com.telek.telekutils.swing.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingFrame {

    private JFrame frame;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public SwingFrame(JFrame frame){
        this.frame = frame;
    }

    public SwingFrame(SwingFrame swingFrame){
        this.frame = swingFrame.getFrame();
    }

    public SwingFrame(){
        this.frame = new JFrame();
    }

    public SwingFrame(String title){
        this.frame = new JFrame(title);
    }

    ///////////////
    /*  METHODS  */
    ///////////////


    public SwingFrame setBounds(Rectangle bounds){
        this.frame.setBounds(bounds);
        return this;
    }
    public SwingFrame setBackground(Color bgColor){
        this.frame.setBackground(bgColor);
        return this;
    }
    public SwingFrame setForeground(Color fgColor){
        this.frame.setForeground(fgColor);
        return this;
    }
    public SwingFrame setCursor(Cursor cursor){
        this.frame.setCursor(cursor);
        return this;
    }
    public SwingFrame setEnabled(boolean isEnabled){
        this.frame.setEnabled(isEnabled);
        return this;
    }
    public SwingFrame setFont(Font font){
        this.frame.setFont(font);
        return this;
    }
    public SwingFrame setVisible(boolean isVisible){
        this.frame.setVisible(isVisible);
        return this;
    }
    public SwingFrame setLayout(LayoutManager layout){
        this.frame.setLayout(layout);
        return this;
    }
    public SwingFrame setIconImage(Image iconImage){
        this.frame.setIconImage(iconImage);
        return this;
    }
    public SwingFrame setTitle(String title){
        this.frame.setTitle(title);
        return this;
    }
    public SwingFrame setResizable(boolean isResizable){
        this.frame.setResizable(isResizable);
        return this;
    }
    public SwingFrame setDefaultCloseOperation(int closeOperation){
        this.frame.setDefaultCloseOperation(closeOperation);
        return this;
    }
    public SwingFrame setContentPane(Container contentPane){
        this.frame.setContentPane(contentPane);
        return this;
    }
    public SwingFrame setOpacity(float opacity){
        this.frame.setOpacity(opacity);
        return this;
    }
    public SwingFrame addMouseListener(MouseListener mouseListener){
        this.frame.addMouseListener(mouseListener);
        return this;
    }
    public SwingFrame addMouseMotionListener(MouseMotionListener mouseMotionListener){
        this.frame.addMouseMotionListener(mouseMotionListener);
        return this;
    }
    public SwingFrame addMouseWheelListener(MouseWheelListener mouseWheelListener){
        this.frame.addMouseWheelListener(mouseWheelListener);
        return this;
    }

    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////


    public JFrame getFrame() {
        return frame;
    }
}
