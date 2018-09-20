package paint.strategy;

import java.awt.geom.AffineTransform;

import paint.ShapePaintStrategy;

import paint.shape.Fish1PolygonFactory;

/**
 *  fish1 paint strategy
 */
public class Fish1PaintStrategy extends ShapePaintStrategy {
	/**
	 * constructor
	 */
	public Fish1PaintStrategy() {
		this(new AffineTransform(), 0, 0, 5, 3);
	}

	/**
	 * constructor
	 * @param at The AffineTransform to use for internal calculations
	   * @param x floating point x-coordinate 
	   * @param y floating point y-coordinate
	   * @param width floating point x-radius 
	   * @param height floating point y-radius 
	 */
	public Fish1PaintStrategy(AffineTransform at, double x, double y, double width, double height) {
		super(at, Fish1PolygonFactory.Singleton.makeShape(x, y, width, height));
	}

}
