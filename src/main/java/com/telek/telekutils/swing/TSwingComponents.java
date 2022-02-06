package com.telek.telekutils.swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import java.awt.*;


public class TSwingComponents {



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
    


    
    public static JLabel getLabel(Rectangle bounds, Color bgColor, Color fgColor,
                                  Cursor cursor, boolean isEnabled, Font font, boolean isVisible,
                                  LayoutManager layout, Border border, boolean isOpaque, String toolTipText,
                                  String text, Icon disabledIcon, Icon icon,
                                  int horizontalAlignment, int verticalAlignment,
                                  int horizontalTextPosition, int verticalTextPosition, int iconTextGap)
    {
        JLabel comp = new JLabel();

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
        comp.setText(text);
        comp.setDisabledIcon(disabledIcon);
        comp.setIcon(icon);
        comp.setHorizontalAlignment(horizontalAlignment);
        comp.setVerticalAlignment(verticalAlignment);
        comp.setHorizontalTextPosition(horizontalTextPosition);
        comp.setVerticalTextPosition(verticalTextPosition);
        comp.setIconTextGap(iconTextGap);

        return comp;
    }
    
    


    public static JPanel getPanel(Rectangle bounds, Color bgColor, Color fgColor,
                                  Cursor cursor, boolean isEnabled, Font font, boolean isVisible,
                                  LayoutManager layout, Border border, boolean isOpaque, String toolTipText)
    {
        JPanel comp = new JPanel();


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
