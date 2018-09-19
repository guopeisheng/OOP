package ballworld.model.paint.shape;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

// Factory to make polygon obj
class PolygonFactory implements IShapeFactory {

	Polygon polygon;
	AffineTransform at;
	double scaleFactor;

	// set the parameters we need to make a polygon
	public PolygonFactory(double scaleFactor, Point... pointers) {
		polygon = new Polygon();
		for (Point p : pointers) {
			polygon.addPoint(p.x, p.y);
		}
		at = new AffineTransform();
		this.scaleFactor = scaleFactor;
	}

	// override makeShape methods to make a polygon
	@Override
	public Shape makeShape(double x, double y, double xScale, double yScale) {
		at.setToTranslation(x, y);
		at.scale(xScale * scaleFactor, yScale * scaleFactor);
		return at.createTransformedShape(polygon);
	}

}
