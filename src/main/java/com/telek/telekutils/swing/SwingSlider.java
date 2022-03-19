package com.telek.telekutils.swing;

import sun.invoke.util.VerifyAccess;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SwingSlider {

    private JSlider slider;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public SwingSlider() {
        this.slider = new JSlider();
    }


    public SwingSlider(int orientation) {
        this.slider = new JSlider(orientation);
    }


    public SwingSlider(int min, int max) {
        this.slider = new JSlider(min, max);
    }

    public SwingSlider(int min, int max, int value) {
        this.slider = new JSlider(min, max, value);
    }

    public SwingSlider(int orientation, int min, int max, int value) {
        this.slider = new JSlider(orientation, min, max, value);
    }


    ///////////////
    /*  METHODS  */
    ///////////////

    public SwingSlider setBounds(Rectangle bounds){
        this.slider.setBounds(bounds);
        return this;
    }
    public SwingSlider setBackground(Color bgColor){
        this.slider.setBackground(bgColor);
        return this;
    }
    public SwingSlider setForeground(Color fgColor){
        this.slider.setForeground(fgColor);
        return this;
    }
    public SwingSlider setCursor(Cursor cursor){
        this.slider.setCursor(cursor);
        return this;
    }
    public SwingSlider setEnabled(boolean isEnabled){
        this.slider.setEnabled(isEnabled);
        return this;
    }
    public SwingSlider setFont(Font font){
        this.slider.setFont(font);
        return this;
    }
    public SwingSlider setVisible(boolean isVisible){
        this.slider.setVisible(isVisible);
        return this;
    }
    public SwingSlider setLayout(LayoutManager layout){
        this.slider.setLayout(layout);
        return this;
    }
    public SwingSlider setBorder(Border border){
        this.slider.setBorder(border);
        return this;
    }
    public SwingSlider setOpaque(boolean isOpaque){
        this.slider.setOpaque(isOpaque);
        return this;
    }
    public SwingSlider setToolTipText(String toolTipText){
        this.slider.setToolTipText(toolTipText);
        return this;
    }
    public SwingSlider setMinimum(int minimum){
        this.slider.setMinimum(minimum);
        return this;
    }
    public SwingSlider setMaximum(int maximum){
        this.slider.setMaximum(maximum);
        return this;
    }
    public SwingSlider setValue(int value){
        this.slider.setValue(value);
        return this;
    }
    public SwingSlider setOrientation(int orientation){
        this.slider.setOrientation(orientation);
        return this;
    }
    public SwingSlider setPaintLabels(boolean isPaintLabels){
        this.slider.setPaintLabels(isPaintLabels);
        return this;
    }
    public SwingSlider setPaintTicks(boolean isPaintTicks){
        this.slider.setPaintTicks(isPaintTicks);
        return this;
    }
    public SwingSlider setPaintTrack(boolean isPaintTracks){
        this.slider.setPaintTrack(isPaintTracks);
        return this;
    }
    public SwingSlider setMajorTickSpacing(int majorTickSpacing){
        this.slider.setMajorTickSpacing(majorTickSpacing);
        return this;
    }
    public SwingSlider setMinorTickSpacing(int minorTickSpacing){
        this.slider.setMinorTickSpacing(minorTickSpacing);
        return this;
    }
    public SwingSlider addChangeListener(ChangeListener changeListener){
        this.slider.addChangeListener(changeListener);
        return this;
    }
    public SwingSlider setInverted(boolean isInverted){
        this.slider.setInverted(isInverted);
        return this;
    }
    public SwingSlider setSnapToTicks(boolean isSnapToTicks){
        this.slider.setSnapToTicks(isSnapToTicks);
        return this;
    }


    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////


    public JSlider getSlider() {
        return slider;
    }
}
