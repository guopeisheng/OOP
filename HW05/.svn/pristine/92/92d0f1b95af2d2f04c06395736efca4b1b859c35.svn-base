package ballworld.model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import ballworld.model.Ball;
import ballworld.model.IPaintStrategy;

// APaintStrategy use AffineTransform 

public abstract class APaintStrategy implements IPaintStrategy {
	protected AffineTransform at;

	public APaintStrategy(AffineTransform at) {
		this.at = at;
	}

	protected AffineTransform getAT() {
		return at;
	}

	// override paint method
	// transform item's shape by affinetransform

	public void paint(Graphics g, Ball ball) {

		double scale = ball.getRadius();
		at.setToTranslation(ball.getLocation().x, ball.getLocation().y);

		at.scale(scale, scale);
		at.rotate(ball.getVelocity().x, ball.getVelocity().y);

		g.setColor(ball.getColor());
		paintCfg(g, ball);
		paintItemfrm(g, ball, at);
	}

	public abstract void paintItemfrm(Graphics g, Ball ball, AffineTransform at);

	protected void paintCfg(Graphics g, Ball ball) {

	}

	public void init(Ball ball) {
	}

}
