package ballworld.model.paint.strategy;

import java.awt.geom.AffineTransform;

import ballworld.model.paint.APaintStrategy;
import ballworld.model.paint.AnimatePaintStrategy;

/**
 * paint a animate fish using the animate paint strategy
 */
public class SwimFishPaintStrategy extends AnimatePaintStrategy {
	/**
	 *
	 */
	public SwimFishPaintStrategy() {
		this(new AffineTransform(), new APaintStrategy[] { new Fish1PaintStrategy(), new Fish2PaintStrategy() });
	}

	public SwimFishPaintStrategy(AffineTransform at, APaintStrategy... pointStrategies) {
		super(at, pointStrategies);
	}

}