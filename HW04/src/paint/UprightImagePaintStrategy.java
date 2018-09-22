/**
 * 
 */
package paint;

import java.awt.Graphics;

import model.ColorBall;

/**
 * image strategy which keep images upright
 *
 */
public class UprightImagePaintStrategy extends ImagePaintStrategy {

	/**
	 * constructor
	 * @param filename image
	 * @param fill factor
	 */
	public UprightImagePaintStrategy(String filename, double fillFactor) {
		super(filename, fillFactor);
	}

	/**
	 * paint cfg
	 * @param g place to draw
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
