package ballworld.model.Strategy;

import java.awt.Point;
import java.awt.geom.Point2D;

import ballworld.model.Ball;
import ballworld.model.IBallCmd;
import ballworld.model.IUpdateStrategy;
import util.IDispatcher;

/**
 * close ball will be absorbed by magnetic to each other
 * @author GPS
 *
 */
public class MagneticStrategy implements IUpdateStrategy<IBallCmd> {

	public void updateState(final Ball context, IDispatcher<IBallCmd> dispatcher) {
		dispatcher.dispatch(new IBallCmd() { //give this command to all balls in dispatcher
			@Override
			public void apply(Ball other, IDispatcher<IBallCmd> disp) {
				if (context != other) {
					if (context.getLocation().distance(other.getLocation()) <= context.getRadius() + other.getRadius()
							+ 55) {
						Double rMass = reducedMass(context.getRadius() * context.getRadius(),
								other.getRadius() * other.getRadius());
						Point2D.Double imp = impulse(context.getLocation(), context.getVelocity(), other.getLocation(),
								other.getVelocity(), rMass, context.getLocation().distance(other.getLocation()),
								context.getRadius() + other.getRadius()
										- context.getLocation().distance(other.getLocation()));
						updateCollision(context, other, imp.x, imp.y, disp);
						updateCollision(other, context, -imp.x, -imp.y, disp);
					}
				}
			}
		});
	}

	/**
	 * Returns the reduced mass of the two balls (m1*m2)/(m1+m2) Gives correct
	 * result if one of the balls has infinite mass.
	 * @param mSource   Mass of the source ball
	 * @param mTarget   Mass of the target ball
	 * @return return a double value
	 */
	protected double reducedMass(double mSource, double mTarget) {
		if (mSource == Double.POSITIVE_INFINITY)
			return mTarget;
		if (mTarget == Double.POSITIVE_INFINITY)
			return mSource;
		else
			return (mSource * mTarget) / (mSource + mTarget);
	}

	/**
	 * The amount to add to the separation distance to insure that the two balls
	 * are beyond collision distance
	 */
	private double Nudge = 1.1;

	/**
	 * Calculates the impulse (change in momentum) of the collision in the
	 * direction from the source to the target This method calculates the
	 * impulse on the source ball. The impulse on the target ball is the
	 * negative of the result. Also moves source ball out of collision range
	 * along normal direction. The change in velocity of the source ball is the
	 * impulse divided by the source's mass The change in velocity of the target
	 * ball is the negative of the impulse divided by the target's mass
	 *
	 * Operational note: Even though theoretically, the difference in velocities
	 * of two balls should be co-linear with the normal line between them, the
	 * discrete nature of animations means that the point where collision is
	 * detected may not be at the same point as the theoretical contact point.
	 * This method calculates the rebound directions as if the two balls were
	 * the appropriate radias such that they had just contacted
	 * _at_the_point_of_collision_detection_. This may give slightly different
	 * rebound direction than one would calculate if they contacted at the
	 * theoretical point given by t heir actual radias.
	 * @param lSource   Location of the source ball
	 * @param vSource   Velocity of the source ball
	 * @param lTarget   Location of the target ball
	 * @param vTarget   Velocity of the target ball
	 * @param reducedMass   Reduced mass of the two balls
	 * @param distance  Distance between the two balls.
	 * @param deltaR  The minimum allowed separation(sum of the ball radii) minus the actual separation(distance between ball centers). Should be a
	 *            positive value.  This is the amount of overlap of the balls as measured along the line between their centers.
	 * @return a Point2D.Double value
	 */
	protected Point2D.Double impulse(Point lSource, Point vSource, Point lTarget, Point vTarget, double reducedMass,
			double distance, double deltaR) {

		double nx = ((double) (lTarget.x - lSource.x)) / distance;
		double ny = ((double) (lTarget.y - lSource.y)) / distance;

		double dvn = (vTarget.x - vSource.x) * nx + (vTarget.y - vSource.y) * ny;

		// move the source ball beyond collision range of the target ball, along
		// the normal direction.
		lSource.translate((int) Math.ceil(-nx * (Nudge * deltaR)), (int) Math.ceil(-ny * (Nudge * deltaR)));
		return new Point2D.Double(2.0 * reducedMass * dvn * nx, 2.0 * reducedMass * dvn * ny);
	}

	/**
	 * Updates the velocity of the source ball, given an impulse, then uses the
	 * context's interactWith method to determine the post collision behavior, from the context
	 * ball's perspective. The change in velocity is the impulse divided by the (source) ball's mass. To change
	 * the velocity of the target ball, switch the source and target input
	 * parameters and negate the impulse values.   This will also run the post collision behavior from
	 * the other perspective.
	 * @param context   The ball to update
	 * @param target   The ball being collided with
	 * @param x   x-coordinate of the impulse
	 * @param y   y-coordinate of the impulse
	 * @param dispatcher   a IDispatcher instance
	 */
	protected void updateCollision(Ball context, Ball target, double x, double y, IDispatcher<IBallCmd> dispatcher) {
		int prevContext = context.getRadius() * context.getRadius();

		context.getVelocity().translate((int) Math.round(x * 0.6 / prevContext),
				(int) Math.round(y * 0.6 / prevContext));
		context.interactWith(target, dispatcher);
	}

	@Override
	public void init(Ball context) {
	}

}