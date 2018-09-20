package paint.shape;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

/**
 * polygon fac
 */
public class PolygonFactory implements IShapeFactory {
	/**
	 * polygon
	 */
	Polygon poly;
	/**
	 * scale factor
	 */
	double scaleFactor;
	/**
	 * affine transform
	 */
	AffineTransform at;

	/**
	 * constructor
	 * @param at AffineTransform 
	 * @param scaleFactor scaleFactor
	 * @param pts points
	 */
	public PolygonFactory(AffineTransform at, double scaleFactor, Point... pts) {
		this.at = at;
		this.scaleFactor = scaleFactor;
		poly = new Polygon();
		for (Point p : pts) {
			poly.addPoint(p.x, p.y);
		}

	}

	/**
	 * make shape method
	 * @param x the center point
	 * @param y center point
	 * @param xScale width/2
	 * @param yScalse height/2
	 * @return  shape
	 */

	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		at.setToTranslation(x, y);
		at.scale(xScale * scaleFactor, yScale * scaleFactor); // optional rotation can be added as well
		return at.createTransformedShape(poly);
	}

}
