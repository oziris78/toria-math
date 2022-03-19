package com.telek.telekutils.swing.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class SwingRadioButton {


    private JRadioButton button;


    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public SwingRadioButton(JRadioButton button){
        this.button = button;
    }

    public SwingRadioButton(SwingRadioButton swingRadioButton){
        this.button = swingRadioButton.getButton();
    }

    public SwingRadioButton(){
        this.button = new JRadioButton();
    }

    public SwingRadioButton(String text){
        this.button = new JRadioButton(text);
    }

    public SwingRadioButton(Icon icon){
        this.button = new JRadioButton(icon);
    }

    public SwingRadioButton(String text, Icon icon){
        this.button = new JRadioButton(text, icon);
    }


    ///////////////
    /*  METHODS  */
    ///////////////


    public SwingRadioButton setBounds(Rectangle bounds){
        this.button.setBounds(bounds);
        return this;
    }

    public SwingRadioButton setBackground(Color bgColor){
        this.button.setBackground(bgColor);
        return this;
    }

    public SwingRadioButton setForeground(Color fgColor){
        this.button.setForeground(fgColor);
        return this;
    }

    public SwingRadioButton setCursor(Cursor cursor){
        this.button.setCursor(cursor);
        return this;
    }

    public SwingRadioButton setEnabled(boolean isEnabled){
        this.button.setEnabled(isEnabled);
        return this;
    }

    public SwingRadioButton setFont(Font font){
        this.button.setFont(font);
        return this;
    }

    public SwingRadioButton setVisible(boolean isVisible){
        this.button.setVisible(isVisible);
        return this;
    }

    public SwingRadioButton setLayout(LayoutManager layout){
        this.button.setLayout(layout);
        return this;
    }

    public SwingRadioButton setBorder(Border border){
        this.button.setBorder(border);
        return this;
    }

    public SwingRadioButton setOpaque(boolean isOpaque){
        this.button.setOpaque(isOpaque);
        return this;
    }

    public SwingRadioButton addMouseListener(MouseListener mouseListener){
        this.button.addMouseListener(mouseListener);
        return this;
    }
    public SwingRadioButton addMouseMotionListener(MouseMotionListener mouseMotionListener){
        this.button.addMouseMotionListener(mouseMotionListener);
        return this;
    }
    public SwingRadioButton addMouseWheelListener(MouseWheelListener mouseWheelListener){
        this.button.addMouseWheelListener(mouseWheelListener);
        return this;
    }
    public SwingRadioButton addActionListener(ActionListener actionListener){
        this.button.addActionListener(actionListener);
        return this;
    }
    public SwingRadioButton addChangeListener(ChangeListener changeListener){
        this.button.addChangeListener(changeListener);
        return this;
    }
    public SwingRadioButton addItemListener(ItemListener itemListener){
        this.button.addItemListener(itemListener);
        return this;
    }

    public SwingRadioButton setText(String text){
        this.button.setText(text);
        return this;
    }


    public SwingRadioButton setToolTipText(String toolTipText){
        this.button.setToolTipText(toolTipText);
        return this;
    }

    public SwingRadioButton setIcon(Icon icon){
        this.button.setIcon(icon);
        return this;
    }

    public SwingRadioButton setIconTextGap(int iconTextGap){
        this.button.setIconTextGap(iconTextGap);
        return this;
    }

    public SwingRadioButton setPressedIcon(Icon pressedIcon){
        this.button.setPressedIcon(pressedIcon);
        return this;
    }

    public SwingRadioButton setSelectedIcon(Icon selectedIcon){
        this.button.setSelectedIcon(selectedIcon);
        return this;
    }

    public SwingRadioButton setFocusable(boolean isFocusable){
        this.button.setFocusable(isFocusable);
        return this;
    }

    public SwingRadioButton setContentAreaFilled(boolean isContentAreaFilled){
        this.button.setContentAreaFilled(isContentAreaFilled);
        return this;
    }

    public SwingRadioButton setRolloverIcon(Icon rolloverIcon){
        this.button.setRolloverIcon(rolloverIcon);
        return this;
    }

    public SwingRadioButton setRolloverEnabled(boolean isRolloverEnabled){
        this.button.setRolloverEnabled(isRolloverEnabled);
        return this;
    }

    public SwingRadioButton setRolloverSelectedIcon(Icon rolloverSelectedIcon){
        this.button.setRolloverSelectedIcon(rolloverSelectedIcon);
        return this;
    }

    public SwingRadioButton setDisabledSelectedIcon(Icon disabledSelectedIcon){
        this.button.setDisabledSelectedIcon(disabledSelectedIcon);
        return this;
    }

    public SwingRadioButton setMargin(Insets margin){
        this.button.setMargin(margin);
        return this;
    }

    public SwingRadioButton setMultiClickThreshhold(long multiClickThreshhold){
        this.button.setMultiClickThreshhold(multiClickThreshhold);
        return this;
    }

    public SwingRadioButton setBorderPainted(boolean isBorderPainted){
        this.button.setBorderPainted(isBorderPainted);
        return this;
    }

    public SwingRadioButton setHorizontalAlignment(int horizontalAlignment){
        this.button.setHorizontalAlignment(horizontalAlignment);
        return this;
    }

    public SwingRadioButton setVerticalAlignment(int verticalAlignment){
        this.button.setVerticalAlignment(verticalAlignment);
        return this;
    }

    public SwingRadioButton setHorizontalTextPosition(int horizontalTextPosition){
        this.button.setHorizontalTextPosition(horizontalTextPosition);
        return this;
    }

    public SwingRadioButton setVerticalTextPosition(int verticalTextPosition){
        this.button.setVerticalTextPosition(verticalTextPosition);
        return this;
    }


    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////


    public JRadioButton getButton() {
        return button;
    }

}
