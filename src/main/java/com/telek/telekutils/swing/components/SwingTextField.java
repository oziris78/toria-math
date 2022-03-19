package com.telek.telekutils.swing.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.*;

public class SwingTextField {

    private JTextField textField;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public SwingTextField(JTextField textField){
        this.textField = textField;
    }

    public SwingTextField(SwingTextField swingTextField){
        this.textField = swingTextField.getTextField();
    }

    public SwingTextField() {
        this.textField = new JTextField();
    }

    public SwingTextField(String text) {
        this.textField = new JTextField(text);
    }

    public SwingTextField(int columns) {
        this.textField = new JTextField(columns);
    }

    public SwingTextField(String text, int columns) {
        this.textField = new JTextField(text, columns);
    }


    ///////////////
    /*  METHODS  */
    ///////////////
    public SwingTextField addMouseListener(MouseListener mouseListener){
        this.textField.addMouseListener(mouseListener);
        return this;
    }
    public SwingTextField addMouseMotionListener(MouseMotionListener mouseMotionListener){
        this.textField.addMouseMotionListener(mouseMotionListener);
        return this;
    }
    public SwingTextField addMouseWheelListener(MouseWheelListener mouseWheelListener){
        this.textField.addMouseWheelListener(mouseWheelListener);
        return this;
    }
    public SwingTextField addActionListener(ActionListener actionListener){
        this.textField.addActionListener(actionListener);
        return this;
    }
    public SwingTextField setBounds(Rectangle bounds){
        this.textField.setBounds(bounds);
        return this;
    }
    public SwingTextField setBackground(Color bgColor){
        this.textField.setBackground(bgColor);
        return this;
    }
    public SwingTextField setForeground(Color fgColor){
        this.textField.setForeground(fgColor);
        return this;
    }
    public SwingTextField setCursor(Cursor cursor){
        this.textField.setCursor(cursor);
        return this;
    }
    public SwingTextField setEnabled(boolean isEnabled){
        this.textField.setEnabled(isEnabled);
        return this;
    }
    public SwingTextField setFont(Font font){
        this.textField.setFont(font);
        return this;
    }
    public SwingTextField setVisible(boolean isVisible){
        this.textField.setVisible(isVisible);
        return this;
    }
    public SwingTextField setLayout(LayoutManager layout){
        this.textField.setLayout(layout);
        return this;
    }
    public SwingTextField setBorder(Border border){
        this.textField.setBorder(border);
        return this;
    }
    public SwingTextField setOpaque(boolean isOpaque){
        this.textField.setOpaque(isOpaque);
        return this;
    }
    public SwingTextField setToolTipText(String toolTipText){
        this.textField.setToolTipText(toolTipText);
        return this;
    }
    public SwingTextField setCaret(Caret caret){
        this.textField.setCaret(caret);
        return this;
    }
    public SwingTextField setCaretColor(Color caretColor){
        this.textField.setCaretColor(caretColor);
        return this;
    }
    public SwingTextField setDragEnabled(boolean isDragEnabled){
        this.textField.setDragEnabled(isDragEnabled);
        return this;
    }
    public SwingTextField setDisabledTextColor(Color disabledTextColor){
        this.textField.setDisabledTextColor(disabledTextColor);
        return this;
    }
    public SwingTextField setMargin(Insets margin){
        this.textField.setMargin(margin);
        return this;
    }
    public SwingTextField setSelectionColor(Color selectionColor){
        this.textField.setSelectionColor(selectionColor);
        return this;
    }
    public SwingTextField setSelectedTextColor(Color selectedTextColor){
        this.textField.setSelectedTextColor(selectedTextColor);
        return this;
    }
    public SwingTextField setEditable(boolean isEditable){
        this.textField.setEditable(isEditable);
        return this;
    }
    public SwingTextField setText(String text){
        this.textField.setText(text);
        return this;
    }
    public SwingTextField select(int selecStart, int selecEnd){
        this.textField.select(selecStart, selecEnd);
        return this;
    }
    public SwingTextField setHorizontalAlignment(int horizontalAlignment){
        this.textField.setHorizontalAlignment(horizontalAlignment);
        return this;
    }
    public SwingTextField setColumns(int columns){
        this.textField.setColumns(columns);
        return this;
    }

    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////

    public JTextField getTextField() {
        return textField;
    }

}
