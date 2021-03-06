/**
 * 
 */
package paint.shape;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * ellipse shape fac
 *
 */
public class EllipseShapeFactory implements IShapeFactory {
	/**
	 * singleton
	 */
	public static final EllipseShapeFactory Singleton = new EllipseShapeFactory();

	/**
	 * constructor
	 */
	public EllipseShapeFactory() {

	}

	@Override
	/**
	 * make an ellipse shape
	 * @param x the center point of the Ellipse
	 * @param y center point of Ellipse
	 * @param xScale width/2
	 * @param yScalse height/2
	 */
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		//		double	height
		//		The overall height of the Ellipse2D.
		//		double	width
		//		The overall width of this Ellipse2D.
		//		double	x
		//		The X coordinate of the upper-left corner of the framing rectangle of this Ellipse2D.
		//		double	y
		//		The Y coordinate of the upper-left corner of the framing rectangle of this Ellipse2D.

		return new Ellipse2D.Double(x - xScale, y - yScale, xScale * 2, yScale * 2);
	}

}
