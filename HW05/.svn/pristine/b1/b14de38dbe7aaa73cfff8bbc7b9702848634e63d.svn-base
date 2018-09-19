package ballworld.model.paint;

import java.awt.geom.AffineTransform;
import ballworld.model.Ball;

// paint the ball using with more than one strategy

public abstract class MultiPaintStrategy extends APaintStrategy {
	private APaintStrategy[] paintStrategies;

	/**
	 * Constructor that takes the paint strategies that will part of the composite.
	 * An external AffineTransform is supplied for internal use.
	 * @param pstrats  parameter that are the paint strategies that will make up the composite.
	 */
	public MultiPaintStrategy(APaintStrategy... pstrats) {
		this(new AffineTransform(), pstrats);
	}

	/**
	 * Constructor to set the strategies we use
	 * @param at the affinetransform
	 * @param pstrats Strategies we use
	 */
	public MultiPaintStrategy(AffineTransform at, APaintStrategy... pstrats) {
		super(at);
		this.paintStrategies = pstrats;
	}

	public void init(Ball host) {
		for (APaintStrategy p : paintStrategies) {
			p.init(host);
			p.at = super.getAT();
		}
	}

	public void paintItemfrm(java.awt.Graphics g, Ball host, java.awt.geom.AffineTransform at) {
		int len = paintStrategies.length;
		for (int i = 0; i < len; i++) {
			paintStrategies[i].paintItemfrm(g, host, at);
		}
	}
}
