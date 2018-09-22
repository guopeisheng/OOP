/**
 * 
 */
package paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import model.ColorBall;

/**
 * shape paint startegy
 *
 */
public class ShapePaintStrategy extends APaintStrategy {
	/**
	 *  shape
	 */
	Shape shape;

	/**
	 * constructor
	 * @param at affine
	 * @param shape shape
	 */
	public ShapePaintStrategy(AffineTransform at, Shape shape) {
		super(at);
		this.shape = shape;
	}

	//	public ShapePaintStrategy(Shape shape) {
	//		this(new AffineTransform(), shape);
	//	}
	/**
	 * init 
	 * @param host ball
	 */
	@Override
	public void init(ColorBall host) {
		// TODO Auto-generated method stub

	}

	/**
	 * paint xfrm
	 * @param g place to draw
	 * @param host ball
	 * @param at affine
	 */
	@Override
	public void paintXfrm(Graphics g, ColorBall host, AffineTransform at) {
		// shape is a predefined by shape fac and has a start point at(0,0) and a fixed area
		// In ApaintStrategy:  at.setToTranslation(host.getLocation().x, host.getLocation().y);
		//                     at.scale(scale, scale);
		//                     at.rotate(host.getVelocity().x, host.getVelocity().y);

		// Returns a new Shape object defined by the geometry of the specified Shape after it has been transformed by this transform.
		((Graphics2D) g).fill(at.createTransformedShape(shape));

	}

}
