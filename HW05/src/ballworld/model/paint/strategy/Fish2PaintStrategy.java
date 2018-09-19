package ballworld.model.paint.strategy;

import java.awt.Shape;
import java.awt.geom.AffineTransform;

import ballworld.model.paint.ShapePaintStrategy;

import ballworld.model.paint.shape.Fish2PolygonFactory;

public class Fish2PaintStrategy extends ShapePaintStrategy {

	public Fish2PaintStrategy() {
		this(new AffineTransform(), Fish2PolygonFactory.Singleton.makeShape(0.0, 0.0, 1.0, 1.0));
	}

	public Fish2PaintStrategy(java.awt.geom.AffineTransform at, Shape shape) {
		super(at, shape);
	}
}
