package com.telek.telekutils.swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.Caret;
import java.awt.*;

public class SwingTextArea {

    private JTextArea textArea;

    ////////////////////
    /*  CONSTRUCTORS  */
    ////////////////////

    public SwingTextArea(){
        this.textArea = new JTextArea();
    }

    public SwingTextArea(String text) {
        this.textArea = new JTextArea(text);
    }

    public SwingTextArea(int rows, int columns) {
        this.textArea = new JTextArea(rows, columns);
    }

    public SwingTextArea(String text, int rows, int columns) {
        this.textArea = new JTextArea(text, rows, columns);
    }



    ///////////////
    /*  METHODS  */
    ///////////////

    public SwingTextArea setBounds(Rectangle bounds){
        this.textArea.setBounds(bounds);
        return this;
    }
    public SwingTextArea setBackground(Color bgColor){
        this.textArea.setBackground(bgColor);
        return this;
    }
    public SwingTextArea setForeground(Color fgColor){
        this.textArea.setForeground(fgColor);
        return this;
    }
    public SwingTextArea setCursor(Cursor cursor){
        this.textArea.setCursor(cursor);
        return this;
    }
    public SwingTextArea setEnabled(boolean isEnabled){
        this.textArea.setEnabled(isEnabled);
        return this;
    }
    public SwingTextArea setFont(Font font){
        this.textArea.setFont(font);
        return this;
    }
    public SwingTextArea setVisible(boolean isVisible){
        this.textArea.setVisible(isVisible);
        return this;
    }
    public SwingTextArea setLayout(LayoutManager layout){
        this.textArea.setLayout(layout);
        return this;
    }
    public SwingTextArea setBorder(Border border){
        this.textArea.setBorder(border);
        return this;
    }
    public SwingTextArea setOpaque(boolean isOpaque){
        this.textArea.setOpaque(isOpaque);
        return this;
    }
    public SwingTextArea setToolTipText(String toolTipText){
        this.textArea.setToolTipText(toolTipText);
        return this;
    }
    public SwingTextArea setCaret(Caret caret){
        this.textArea.setCaret(caret);
        return this;
    }
    public SwingTextArea setCaretColor(Color caretColor){
        this.textArea.setCaretColor(caretColor);
        return this;
    }
    public SwingTextArea setDragEnabled(boolean isDragEnabled){
        this.textArea.setDragEnabled(isDragEnabled);
        return this;
    }
    public SwingTextArea setDisabledTextColor(Color disabledTextColor){
        this.textArea.setDisabledTextColor(disabledTextColor);
        return this;
    }
    public SwingTextArea setMargin(Insets margin){
        this.textArea.setMargin(margin);
        return this;
    }
    public SwingTextArea setSelectionColor(Color selectionColor){
        this.textArea.setSelectionColor(selectionColor);
        return this;
    }
    public SwingTextArea setSelectedTextColor(Color selectedTextColor){
        this.textArea.setSelectedTextColor(selectedTextColor);
        return this;
    }
    public SwingTextArea setEditable(boolean isEditable){
        this.textArea.setEditable(isEditable);
        return this;
    }
    public SwingTextArea setText(String text){
        this.textArea.setText(text);
        return this;
    }
    public SwingTextArea select(int selecStart, int selecEnd){
        this.textArea.select(selecStart, selecEnd);
        return this;
    }
    public SwingTextArea setColumns(int cols){
        this.textArea.setColumns(cols);
        return this;
    }
    public SwingTextArea setRows(int rows){
        this.textArea.setRows(rows);
        return this;
    }
    public SwingTextArea setWrapStyleWord(boolean isWrapStyleWord){
        this.textArea.setWrapStyleWord(isWrapStyleWord);
        return this;
    }
    public SwingTextArea setLineWrap(boolean isLineWrap){
        this.textArea.setLineWrap(isLineWrap);
        return this;
    }
    public SwingTextArea setTabSize(int tabSize){
        this.textArea.setTabSize(tabSize);
        return this;
    }


    ///////////////////////////
    /*  GETTERS AND SETTERS  */
    ///////////////////////////

    public JTextArea getTextArea() {
        return textArea;
    }

}
