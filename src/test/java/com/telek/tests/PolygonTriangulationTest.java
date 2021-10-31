package com.telek.tests;

import com.telek.telekmath.core.geometry.points.TPoint2D;
import com.telek.telekmath.core.geometry.shapes.TPolygon;
import com.telek.telekmath.core.geometry.shapes.TTriangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PolygonTriangulationTest {

    private static TPolygon polygon;
    private static TTriangle[] triangles;

    public static void main(String[] args) {


        polygon = new TPolygon(
            new TPoint2D(1,1),
            new TPoint2D(10,1),
            new TPoint2D(15,20),
            new TPoint2D(20,25),
            new TPoint2D(12,30),
            new TPoint2D(5,22)
        );

        polygon.scale(10);
        polygon.moveBy(100, 100);

        triangles = polygon.triangulate();

        new PolygonTriangulationTest().UI();
    }

    private void UI(){
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        frame.setContentPane(new MyPanel());
    }

    private static class MyPanel extends JPanel{

        boolean drawTriangulation = false;

        public MyPanel(){
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    drawTriangulation = !drawTriangulation;
                    repaint();
                }
                @Override public void mousePressed(MouseEvent e) {}
                @Override public void mouseReleased(MouseEvent e) {}
                @Override public void mouseEntered(MouseEvent e) {}
                @Override public void mouseExited(MouseEvent e) {}
            });

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.BLACK);
            TPoint2D[] points = polygon.getPoints();
            for (int i = 0; i < points.length; i++) {
                TPoint2D currentPoint = points[i];
                TPoint2D nextPoint = points[ (i+1) % points.length ];
                g2d.fillOval( (int) currentPoint.x, (int) currentPoint.y, 5, 5);
                g2d.drawLine( (int) currentPoint.x,(int)  currentPoint.y, (int) nextPoint.x, (int) nextPoint.y  );
            }


            if(drawTriangulation){
                g2d.setColor(Color.RED);
                for (int i = 0; i < triangles.length; i++) {
                    TTriangle currentTriangle = triangles[i];
                    TPoint2D p1 = currentTriangle.getPoint1();
                    TPoint2D p2 = currentTriangle.getPoint2();
                    TPoint2D p3 = currentTriangle.getPoint3();
                    g2d.drawLine( (int) p1.x,(int)  p1.y, (int) p2.x, (int) p2.y  );
                    g2d.drawLine( (int) p1.x,(int)  p1.y, (int) p3.x, (int) p3.y  );
                    g2d.drawLine( (int) p3.x,(int)  p3.y, (int) p2.x, (int) p2.y  );
                }
            }





        }


    }


}
