package ballworld.model.paint;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import ballworld.model.Ball;

//paint a animate shape 
public class AnimatePaintStrategy extends APaintStrategy {

	protected APaintStrategy[] paintStrategies;
	int count = 0;

	public AnimatePaintStrategy(AffineTransform at, APaintStrategy... ps) {
		super(at);
		paintStrategies = ps;
	}

	@Override
	public void init(Ball host) {
		super.init(host);
		for (APaintStrategy p : paintStrategies) {
			p.init(host);
			p.at = super.at;
		}

	}

	// paint several shapes
	@Override
	public void paintItemfrm(Graphics g, Ball host, AffineTransform at) {
		paintStrategies[count].paintItemfrm(g, host, at);
		count = (count + 1) % paintStrategies.length;
	}

}
