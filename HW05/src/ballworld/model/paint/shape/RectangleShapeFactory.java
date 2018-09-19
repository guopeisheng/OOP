package ballworld.model.paint.shape;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

// factory to make rectangle

public class RectangleShapeFactory implements IShapeFactory {

	public static final RectangleShapeFactory Singleton = new RectangleShapeFactory();

	private RectangleShapeFactory() {
	}

	// make a rectangle 
	public Shape makeShape(double x, double y, double width, double length) {
		return new Rectangle2D.Double(x, y, width, length);
	}
}
