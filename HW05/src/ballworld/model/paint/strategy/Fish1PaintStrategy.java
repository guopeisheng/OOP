package ballworld.model.paint.strategy;

import java.awt.Shape;
import java.awt.geom.AffineTransform;

import ballworld.model.paint.ShapePaintStrategy;
import ballworld.model.paint.shape.Fish1PolygonFactory;

public class Fish1PaintStrategy extends ShapePaintStrategy {
	// make a fish using factory
	public Fish1PaintStrategy() {
		this(new AffineTransform(), Fish1PolygonFactory.Singleton.makeShape(0, 0, 1, 1));
	}

	public Fish1PaintStrategy(AffineTransform at, Shape shape) {
		super(at, shape);
	}

}
