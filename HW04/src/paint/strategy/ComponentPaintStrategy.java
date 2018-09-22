/**
 * 
 */
package paint.strategy;

import java.awt.Graphics;

import model.ColorBall;
import paint.MultiPaintStrategy;

/**
 * component strategy
 *
 */
public class ComponentPaintStrategy extends MultiPaintStrategy {

	/**
	 * constructor
	 */
	public ComponentPaintStrategy() {
		super(new Fish1PaintStrategy(), new EllipsePaintStrategy());

	}

	/**
	 * paint cfg method
	 * @param g draw
	 * @param host ball
	 */
	@Override
	public void paintCfg(Graphics g, ColorBall host) {
		super.paintCfg(g, host);
		if (Math.abs(Math.atan2(host.getVelocity().y, host.getVelocity().x)) > Math.PI / 2.0) {
			at.scale(1.0, -1.0);
		}
	}

}
