package ballworld.model.paint;

import java.awt.geom.AffineTransform;
import ballworld.model.Ball;

// reset the image to upright, when necessary
public class UprightImagePaintStrategy extends ImagePaintStrategy {

	public UprightImagePaintStrategy(String name, double factor) {
		this(new AffineTransform(), name, factor);
	}

	// set the image to using this strategy
	public UprightImagePaintStrategy(AffineTransform at, String name, double factor) {
		super(at, name, factor);
	}

	protected void paintCfg(java.awt.Graphics g, Ball ball) {
		super.paintCfg(g, ball);
		double abs = Math.abs(Math.atan2(ball.getVelocity().y, ball.getVelocity().x));
		if (abs >= Math.PI / 2.0) {
			getAT().scale(1.0, -1.0);
		} else {
			return;
		}
	}
}
