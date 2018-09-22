package paint.shape;

import java.awt.Shape;

/**
 * shape fac interface
 */
public interface IShapeFactory {
	/**
	 * make shape method
	 * @param x the center point
	 * @param y center point
	 * @param xScale width/2
	 * @param yScalse height/2
	 * @return shape
	 */
	public Shape makeShape(double x, double y, double xScale, double yScale);
}
