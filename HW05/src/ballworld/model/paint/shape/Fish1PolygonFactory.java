package ballworld.model.paint.shape;

import java.awt.Point;

// Factory to make fish shape with open mouse

public class Fish1PolygonFactory extends PolygonFactory {
	/**
	 *
	 */
	public static final Fish1PolygonFactory Singleton = new Fish1PolygonFactory();

	// override the constructor of polygon factory with using specific points

	public Fish1PolygonFactory() {
		super(0.1,
				new Point[] { new Point(-7, 4), new Point(-5, 0), new Point(0, 4), new Point(3, 4), new Point(5, 3),
						new Point(6, 2), new Point(7, 1), new Point(6, 0), new Point(7, -1), new Point(6, -2),
						new Point(5, -3), new Point(3, -4), new Point(0, -4), new Point(-5, 0), new Point(-7, -4) });
	}
}
