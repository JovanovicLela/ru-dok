package dsw.rudok.app.repository.elements;

import dsw.rudok.app.gui.swing.view.painters.RectanglePainter;

import java.awt.*;
import java.awt.geom.Point2D;

public class RectangleElement extends SlotDevice {

    public RectangleElement(Point2D position, Dimension size, Stroke stroke, Paint paint, Color strokeColor) {
        super(position, size, stroke, paint, strokeColor, 5, 3);
        elementPainter = new RectanglePainter(this);
    }

    public static SlotDevice createDefault(Point2D pos, int elemNo){
        Point2D position = (Point2D) pos.clone();

        Paint fill = Color.WHITE;

        RectangleElement rectangleElement=new RectangleElement(position,
                new Dimension(80,40),
                new BasicStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL ),
                fill,
                Color.BLACK);

        rectangleElement.setName("Rectangle" + elemNo);
        return rectangleElement;
    }

}
