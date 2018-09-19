package ballworld.model.paint.shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

// factory to paint ellipse

public class EllipseShapeFactory implements IShapeFactory {

	public static final EllipseShapeFactory Singleton = new EllipseShapeFactory();

	private EllipseShapeFactory() {
	}

	/**
	 * this method is to make a ellipse shape using the parameters
	 */
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		return new Ellipse2D.Double(x, y, xScale, yScale);
	}
}
