package ballworld.model.paint.shape;

import java.awt.Point;

// Factory to make fish shape with mouse not open

public class Fish2PolygonFactory extends PolygonFactory {

	public static final Fish2PolygonFactory Singleton = new Fish2PolygonFactory();

	// override the constructor of polygon factory using specific points
	public Fish2PolygonFactory() {
		super(0.1,
				new Point[] { new Point(-7, 4), new Point(-5, 0), new Point(0, 4), new Point(3, 4), new Point(5, 3),
						new Point(6, 2), new Point(7, 0), new Point(6, -2), new Point(5, -3), new Point(3, -4),
						new Point(0, -4), new Point(-5, 0), new Point(-7, -4) });
	}

}
