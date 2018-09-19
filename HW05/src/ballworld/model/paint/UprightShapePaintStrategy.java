package ballworld.model.paint;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import ballworld.model.Ball;

//reset the image to upright, when necessary
public class UprightShapePaintStrategy extends ShapePaintStrategy {

	public UprightShapePaintStrategy(Shape shape) {
		this(new AffineTransform(), shape);
	}

	// constructor to set the shape using the paint shape strategy
	public UprightShapePaintStrategy(AffineTransform at, Shape shape) {
		super(at, shape);
	}

	// reset the angle of rotate when bigger than pi
	protected void paintCfg(Graphics g, Ball ball) {
		super.paintCfg(g, ball);
		double abs = Math.abs(Math.atan2(ball.getVelocity().y, ball.getVelocity().x));
		if (abs >= Math.PI / 2.0) {
			getAT().scale(1.0, -1.0);
		}
	}

}
