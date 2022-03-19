package com.telek.telekutils.swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import java.awt.*;


public class TSwingComponents {


    /* No Constructor */
    private TSwingComponents(){}


    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    // core
    public static JPanel getPanel(Rectangle bounds, Color bgColor,
                                  boolean isEnabled, boolean isVisible,
                                  LayoutManager layout, boolean isOpaque){
        JPanel comp = new JPanel();

        comp.setBounds(bounds);
        comp.setBackground(bgColor);
        comp.setEnabled(isEnabled);
        comp.setVisible(isVisible);
        comp.setLayout(layout);
        comp.setOpaque(isOpaque);

        return comp;
    }


    // extension
    public static JPanel getPanel(Rectangle bounds, Color bgColor, Color fgColor,
                                  Cursor cursor, boolean isEnabled, Font font, boolean isVisible,
                                  LayoutManager layout, Border border, boolean isOpaque, String toolTipText)
    {
        JPanel comp = getPanel(bounds, bgColor, isEnabled, isVisible, layout, isOpaque);

        comp.setForeground(fgColor);
        comp.setCursor(cursor);
        comp.setFont(font);
        comp.setBorder(border);
        comp.setToolTipText(toolTipText);

        return comp;
    }


    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////


    public static JLabel getLabel(Rectangle bounds, boolean isEnabled, boolean isVisible, LayoutManager layout,
                                  Border border, boolean isOpaque, String text)
    {
        JLabel comp = new JLabel();

        comp.setBounds(bounds);
        comp.setVisible(isVisible);
        comp.setLayout(layout);
        comp.setBorder(border);
        comp.setOpaque(isOpaque);
        comp.setText(text);
        comp.setEnabled(isEnabled);

        return comp;
    }


    public static JLabel getLabel(Rectangle bounds, boolean isEnabled, boolean isVisible,
                                  LayoutManager layout, Border border, boolean isOpaque, String text,
                                  int horizontalAlignment, int verticalAlignment)
    {
        JLabel comp = getLabel(bounds, isEnabled, isVisible, layout, border, isOpaque, text);

        comp.setHorizontalAlignment(horizontalAlignment);
        comp.setVerticalAlignment(verticalAlignment);
        return comp;
    }


    public static JLabel getLabel(Rectangle bounds, boolean isEnabled, boolean isVisible,
                                  LayoutManager layout, Border border, boolean isOpaque, String text,
                                  int horizontalAlignment, int verticalAlignment, Icon icon, Icon disabledIcon,
                                  int horizontalTextPosition, int verticalTextPosition, int iconTextGap)
    {
        JLabel comp = getLabel(bounds, isEnabled, isVisible, layout, border,
                isOpaque, text, horizontalAlignment, verticalAlignment);

        comp.setIcon(icon);
        comp.setDisabledIcon(disabledIcon);
        comp.setHorizontalTextPosition(horizontalTextPosition);
        comp.setVerticalTextPosition(verticalTextPosition);
        comp.setIconTextGap(iconTextGap);

        return comp;
    }


    public static JLabel getLabel(Rectangle bounds, boolean isEnabled, boolean isVisible,
                                  LayoutManager layout, Border border, boolean isOpaque, String text,
                                  int horizontalAlignment, int verticalAlignment, Icon icon, Icon disabledIcon,
                                  int horizontalTextPosition, int verticalTextPosition, int iconTextGap,
                                  Color bgColor, Color fgColor)
    {
        JLabel comp = getLabel(bounds, isEnabled, isVisible, layout, border,
                isOpaque, text, horizontalAlignment, verticalAlignment, icon, disabledIcon,
                horizontalTextPosition, verticalTextPosition, iconTextGap);

        comp.setBackground(bgColor);
        comp.setForeground(fgColor);

        return comp;
    }



    public static JLabel getLabel(Rectangle bounds, boolean isEnabled, boolean isVisible,
                                  LayoutManager layout, Border border, boolean isOpaque, String text,
                                  int horizontalAlignment, int verticalAlignment, Icon icon, Icon disabledIcon,
                                  int horizontalTextPosition, int verticalTextPosition, int iconTextGap,
                                  Color bgColor, Color fgColor, Cursor cursor, Font font, String toolTipText)
    {
        JLabel comp = getLabel(bounds, isEnabled, isVisible, layout, border,
                isOpaque, text, horizontalAlignment, verticalAlignment, icon, disabledIcon,
                horizontalTextPosition, verticalTextPosition, iconTextGap, bgColor, fgColor);

        comp.setCursor(cursor);
        comp.setFont(font);
        comp.setToolTipText(toolTipText);

        return comp;
    }


    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////




    public static JButton getButton(Rectangle bounds, Color bgColor, Color fgColor,
                                    Cursor cursor, boolean isEnabled, Font font, boolean isVisible,
                                    LayoutManager layout, Border border, boolean isOpaque, String toolTipText,
                                    Icon icon, int iconTextGap, Icon pressedIcon, Icon selectedIcon,
                                    boolean isFocusable, boolean isContentAreaFilled, Icon rolloverIcon,
                                    boolean isRolloverEnabled, Icon rolloverSelectedIcon, Icon disabledSelectedIcon,
                                    Insets margin, long multiClickThreshhold, boolean isBorderPainted,
                                    int horizontalAlignment, int verticalAlignment,
                                    int horizontalTextPosition, int verticalTextPosition)
    {
        JButton comp = new JButton();
        
        comp.setBounds(bounds);
        comp.setBackground(bgColor);
        comp.setForeground(fgColor);
        comp.setCursor(cursor);
        comp.setEnabled(isEnabled);
        comp.setFont(font);
        comp.setVisible(isVisible);
        comp.setLayout(layout);
        comp.setBorder(border);
        comp.setOpaque(isOpaque);
        comp.setToolTipText(toolTipText);
        comp.setIcon(icon);
        comp.setIconTextGap(iconTextGap);
        comp.setPressedIcon(pressedIcon);
        comp.setSelectedIcon(selectedIcon);
        comp.setFocusable(isFocusable);
        comp.setContentAreaFilled(isContentAreaFilled);
        comp.setRolloverIcon(rolloverIcon);
        comp.setRolloverEnabled(isRolloverEnabled);
        comp.setRolloverSelectedIcon(rolloverSelectedIcon);
        comp.setDisabledSelectedIcon(disabledSelectedIcon);
        comp.setMargin(margin);
        comp.setMultiClickThreshhold(multiClickThreshhold);
        comp.setBorderPainted(isBorderPainted);
        comp.setHorizontalAlignment(horizontalAlignment);
        comp.setVerticalAlignment(verticalAlignment);
        comp.setHorizontalTextPosition(horizontalTextPosition);
        comp.setVerticalTextPosition(verticalTextPosition);
        
        return comp;
    }
    
    


    public static JFrame getFrame(Rectangle bounds, Color bgColor, Color fgColor,
                                  Cursor cursor, boolean isEnabled, Font font, boolean isVisible,
                                  LayoutManager layout, float opacity, Image iconImage,
                                  String title, boolean isResizable, Container contentPane)
    {
        JFrame comp = new JFrame();

        comp.setBounds(bounds);
        comp.setBackground(bgColor);
        comp.setForeground(fgColor);
        comp.setCursor(cursor);
        comp.setEnabled(isEnabled);
        comp.setFont(font);
        comp.setVisible(isVisible);
        comp.setLayout(layout);
        comp.setOpacity(opacity);

        comp.setIconImage(iconImage);
        comp.setTitle(title);
        comp.setResizable(isResizable);
        comp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        comp.setContentPane(contentPane);

        
        return comp;
    }
    


    





    




    public static JSlider getSlider(Rectangle bounds, Color bgColor, Color fgColor,
                                    Cursor cursor, boolean isEnabled, Font font, boolean isVisible,
                                    LayoutManager layout, Border border, boolean isOpaque, String toolTipText,
                                    int minimum, int maximum, int value, int orientation, boolean isPaintLabels,
                                    boolean isPaintTicks, boolean isPaintTracks, int majorTickSpacing,
                                    int minorTickSpacing, ChangeListener changeListener, boolean isInverted,
                                    boolean isSnapToTicks)
    {
        JSlider comp = new JSlider();

        comp.setBounds(bounds);
        comp.setBackground(bgColor);
        comp.setForeground(fgColor);
        comp.setCursor(cursor);
        comp.setEnabled(isEnabled);
        comp.setFont(font);
        comp.setVisible(isVisible);
        comp.setLayout(layout);
        comp.setBorder(border);
        comp.setOpaque(isOpaque);
        comp.setToolTipText(toolTipText);
        comp.setMinimum(minimum);
        comp.setMaximum(maximum);
        comp.setValue(value);
        comp.setOrientation(orientation);
        comp.setPaintLabels(isPaintLabels);
        comp.setPaintTicks(isPaintTicks);
        comp.setPaintTrack(isPaintTracks);
        comp.setMajorTickSpacing(majorTickSpacing);
        comp.setMinorTickSpacing(minorTickSpacing);
        comp.addChangeListener(changeListener);
        comp.setInverted(isInverted);
        comp.setSnapToTicks(isSnapToTicks);
        
        return comp;
    }
    
    


    public static JTextArea getTextArea(Rectangle bounds, Color bgColor, Color fgColor,
                                        Cursor cursor, boolean isEnabled, Font font, boolean isVisible,
                                        LayoutManager layout, Border border, boolean isOpaque, String toolTipText,
                                        Insets margin, Caret caret, Color caretColor, boolean isDragEnabled,
                                        Color disabledTextColor, Color selectionColor, Color selectedTextColor,
                                        boolean isEditable, String text, int selecStart, int selecEnd, int cols,
                                        int rows, boolean isWrapStyleWord, boolean isLineWrap, int tabSize)
    {
        JTextArea comp = new JTextArea();

        comp.setBounds(bounds);
        comp.setBackground(bgColor);
        comp.setForeground(fgColor);
        comp.setCursor(cursor);
        comp.setEnabled(isEnabled);
        comp.setFont(font);
        comp.setVisible(isVisible);
        comp.setLayout(layout);
        comp.setBorder(border);
        comp.setOpaque(isOpaque);
        comp.setToolTipText(toolTipText);
        comp.setCaret(caret);
        comp.setCaretColor(caretColor);
        comp.setDragEnabled(isDragEnabled);
        comp.setDisabledTextColor(disabledTextColor);
        comp.setMargin(margin);
        comp.setSelectionColor(selectionColor);
        comp.setSelectedTextColor(selectedTextColor);
        comp.setEditable(isEditable);
        comp.setText(text);
        comp.select(selecStart, selecEnd);
        comp.setColumns(cols);
        comp.setRows(rows);
        comp.setWrapStyleWord(isWrapStyleWord);
        comp.setLineWrap(isLineWrap);
        comp.setTabSize(tabSize);

        return comp;
    }
    
    


    public static JTextField getTextField(Rectangle bounds, Color bgColor, Color fgColor,
                                          Cursor cursor, boolean isEnabled, Font font, boolean isVisible,
                                          LayoutManager layout, Border border, boolean isOpaque, String toolTipText,
                                          Insets margin, Caret caret, Color caretColor, boolean isDragEnabled,
                                          Color disabledTextColor, Color selectionColor, Color selectedTextColor,
                                          boolean isEditable, String text, int selecStart, int selecEnd, int horizontalAlignment, int columns)
    {
        JTextField comp = new JTextField();

        comp.setBounds(bounds);
        comp.setBackground(bgColor);
        comp.setForeground(fgColor);
        comp.setCursor(cursor);
        comp.setEnabled(isEnabled);
        comp.setFont(font);
        comp.setVisible(isVisible);
        comp.setLayout(layout);
        comp.setBorder(border);
        comp.setOpaque(isOpaque);
        comp.setToolTipText(toolTipText);

        comp.setCaret(caret);
        comp.setCaretColor(caretColor);
        comp.setDragEnabled(isDragEnabled);
        comp.setDisabledTextColor(disabledTextColor);
        comp.setMargin(margin);
        comp.setSelectionColor(selectionColor);
        comp.setSelectedTextColor(selectedTextColor);
        comp.setEditable(isEditable);
        comp.setText(text);
        comp.select(selecStart, selecEnd);

        comp.setHorizontalAlignment(horizontalAlignment);
        comp.setColumns(columns);

        return comp;
    }
    
    



}
