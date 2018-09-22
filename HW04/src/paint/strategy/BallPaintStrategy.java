package paint.strategy;

import java.awt.Graphics;

import model.ColorBall;
import model.IPaintStrategy;

/**
 * ball paint strategy
 */
public class BallPaintStrategy implements IPaintStrategy {
	/**
	 * constructor
	 */
	public BallPaintStrategy() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * init
	 * @param host ball
	 */
	@Override
	public void init(ColorBall host) {

	}

	/**
	 * paint method
	 * @param g paint
	 * @param host ball
	 */
	@Override
	public void paint(Graphics g, ColorBall host) {
		g.setColor(host.getColor());
		g.fillOval(host.getLocation().x - host.getRadius(), host.getLocation().y - host.getRadius(),
				2 * host.getRadius(), 2 * host.getRadius());

	}

}
