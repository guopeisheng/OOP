package ballworld.model.paint.strategy;

import ballworld.model.Ball;
import ballworld.model.IPaintStrategy;

// strategy to paint the ball on canvas
// this class inherit paintstrategy

public class BallPaintStrategy implements IPaintStrategy {

	// override 'paint' method
	// make a ball shape and transform it

	public void paint(java.awt.Graphics g, Ball ball) {
		g.setColor(ball.getColor());
		g.fillOval((int) ball.getLocation().getX() - (int) ball.getRadius(),
				(int) ball.getLocation().getY() - (int) ball.getRadius(), 2 * (int) ball.getRadius(),
				2 * (int) ball.getRadius());

	}

	@Override
	public void init(Ball ball) {
		// no-op
	}
}
