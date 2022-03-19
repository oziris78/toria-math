package com.telek.telekutils.swing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwingButton {

    private JButton button;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public SwingButton(){
        this.button = new JButton();
    }

    public SwingButton(String text){
        this.button = new JButton(text);
    }

    public SwingButton(Icon icon){
        this.button = new JButton(icon);
    }

    public SwingButton(String text, Icon icon){
        this.button = new JButton(text, icon);
    }


    ///////////////
    /*  METHODS  */
    ///////////////


    public SwingButton setBounds(Rectangle bounds){
        this.button.setBounds(bounds);
        return this;
    }

    public SwingButton setBackground(Color bgColor){
        this.button.setBackground(bgColor);
        return this;
    }

    public SwingButton setForeground(Color fgColor){
        this.button.setForeground(fgColor);
        return this;
    }

    public SwingButton setCursor(Cursor cursor){
        this.button.setCursor(cursor);
        return this;
    }

    public SwingButton setEnabled(boolean isEnabled){
        this.button.setEnabled(isEnabled);
        return this;
    }

    public SwingButton setFont(Font font){
        this.button.setFont(font);
        return this;
    }

    public SwingButton setVisible(boolean isVisible){
        this.button.setVisible(isVisible);
        return this;
    }

    public SwingButton setLayout(LayoutManager layout){
        this.button.setLayout(layout);
        return this;
    }

    public SwingButton setBorder(Border border){
        this.button.setBorder(border);
        return this;
    }

    public SwingButton setOpaque(boolean isOpaque){
        this.button.setOpaque(isOpaque);
        return this;
    }

    public SwingButton setToolTipText(String toolTipText){
        this.button.setToolTipText(toolTipText);
        return this;
    }

    public SwingButton setIcon(Icon icon){
        this.button.setIcon(icon);
        return this;
    }

    public SwingButton setIconTextGap(int iconTextGap){
        this.button.setIconTextGap(iconTextGap);
        return this;
    }

    public SwingButton setPressedIcon(Icon pressedIcon){
        this.button.setPressedIcon(pressedIcon);
        return this;
    }

    public SwingButton setSelectedIcon(Icon selectedIcon){
        this.button.setSelectedIcon(selectedIcon);
        return this;
    }

    public SwingButton setFocusable(boolean isFocusable){
        this.button.setFocusable(isFocusable);
        return this;
    }

    public SwingButton setContentAreaFilled(boolean isContentAreaFilled){
        this.button.setContentAreaFilled(isContentAreaFilled);
        return this;
    }

    public SwingButton setRolloverIcon(Icon rolloverIcon){
        this.button.setRolloverIcon(rolloverIcon);
        return this;
    }

    public SwingButton setRolloverEnabled(boolean isRolloverEnabled){
        this.button.setRolloverEnabled(isRolloverEnabled);
        return this;
    }

    public SwingButton setRolloverSelectedIcon(Icon rolloverSelectedIcon){
        this.button.setRolloverSelectedIcon(rolloverSelectedIcon);
        return this;
    }

    public SwingButton setDisabledSelectedIcon(Icon disabledSelectedIcon){
        this.button.setDisabledSelectedIcon(disabledSelectedIcon);
        return this;
    }

    public SwingButton setMargin(Insets margin){
        this.button.setMargin(margin);
        return this;
    }

    public SwingButton setMultiClickThreshhold(long multiClickThreshhold){
        this.button.setMultiClickThreshhold(multiClickThreshhold);
        return this;
    }

    public SwingButton setBorderPainted(boolean isBorderPainted){
        this.button.setBorderPainted(isBorderPainted);
        return this;
    }

    public SwingButton setHorizontalAlignment(int horizontalAlignment){
        this.button.setHorizontalAlignment(horizontalAlignment);
        return this;
    }

    public SwingButton setVerticalAlignment(int verticalAlignment){
        this.button.setVerticalAlignment(verticalAlignment);
        return this;
    }

    public SwingButton setHorizontalTextPosition(int horizontalTextPosition){
        this.button.setHorizontalTextPosition(horizontalTextPosition);
        return this;
    }

    public SwingButton setVerticalTextPosition(int verticalTextPosition){
        this.button.setVerticalTextPosition(verticalTextPosition);
        return this;
    }


    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////


    public JButton getButton() {
        return button;
    }

}
