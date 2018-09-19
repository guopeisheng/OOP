package ballworld.model.paint.strategy;

import java.awt.geom.AffineTransform;

import ballworld.model.paint.ShapePaintStrategy;
import ballworld.model.paint.shape.RectangleShapeFactory;

public class RectanglePaintStrategy extends ShapePaintStrategy {

	public RectanglePaintStrategy() {
		this(new AffineTransform(), 0, 0, 8.0 / 3.0, 2.0 / 3.0);
	}

	/**
	 * using Factory to paint a rectangle shape
	 * and transform it using affinetransform
	 * @param at The AffineTransform to use
	 * @param x  The x-coordinate of the center of the prototype rectangle
	 * @param y  The y-coordinate of the center of the prototype rectangle
	 * @param width The half-width of the rectangle, i.e. the width as measured from the center.
	 * @param height  The half-hieght of the rectangle, i.e. the height as measured from the center.
	 */
	public RectanglePaintStrategy(AffineTransform at, double x, double y, double width, double height) {
		super(at, RectangleShapeFactory.Singleton.makeShape(x, y, width, height));
	}
}
