package ballworld.model;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import util.IDispatcher;
import util.IObserver;

public class Ball implements IObserver<IBallCmd> {
	IUpdateStrategy<IBallCmd> uptStrategy;
	IInteractStrategy<IBallCmd> itrStrategy;

	// container where this ball would be painted on.
	public Component container;

	// center of this ball.
	public Point center;

	// radius of this ball. 
	public int radius;

	// color of this ball.
	public Color color;

	// velocity of ball
	public Point velocity;

	// PaintStrategy of the ball
	protected IPaintStrategy paintStrategy;

	// constructor of the ball
	public Ball(Component dispPane, Point center, int radi, Color color, Point vel) {
		this.container = dispPane;
		this.center = new Point((int) center.getX() - radius, (int) center.getY() - radius);
		this.radius = radi;
		this.color = color;
		this.velocity = vel;
	}

	// get the ball's radius
	public int getRadius() {
		return radius;
	}

	// set the ball's radius
	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void set_interactStrategy(IInteractStrategy<IBallCmd> interactStrategy) {
		this.itrStrategy = interactStrategy;
	}

	public IInteractStrategy<IBallCmd> get_interactStrategy() {
		return itrStrategy;
	}

	// get the ball's center
	public Point getLocation() {
		return center;
	}

	// get the ball's velocity
	public Point getVelocity() {
		return velocity;
	}

	// set the ball's velocity
	public void setVelocity(int x, int y) {
		this.velocity.x = x;
		this.velocity.y = y;
	}

	// get the Ball's color
	public Color getColor() {
		return color;
	}

	// set the color
	public void setColor(Color color) {
		this.color = color;
	}

	// get the canvas 
	public Component getCanvas() {
		return container;
	}

	// set ball bouncing 
	protected void bounce() {
		Point bounds = new Point(this.container.getWidth(), this.container.getHeight());

		if (this.center.x + this.radius >= bounds.x) {
			this.velocity.x = -this.velocity.x;
			this.center.x = 2 * (bounds.x - radius) - this.center.x;
		}
		if (this.center.y + this.radius >= bounds.y) {
			this.velocity.y = -this.velocity.y;
			this.center.y = 2 * (bounds.y - radius) - this.center.y;
		}
		if (this.center.x - this.radius < 0) {
			this.velocity.x = -this.velocity.x;
			this.center.x = 2 * radius - this.center.x;
		}
		if (this.center.y - this.radius < 0) {
			this.velocity.y = -this.velocity.y;
			this.center.y = 2 * radius - this.center.y;
		}
	}

	// use the given _strategy on the ball
	public void setUpdateStrategy(IUpdateStrategy<IBallCmd> strategy) {
		this.uptStrategy = strategy;
		strategy.init(this);
	}

	// get the ball's UpdateStrategy
	public IUpdateStrategy<IBallCmd> getUpdateStrategy() {
		return this.uptStrategy;
	}

	public void setPaintStrategy(IPaintStrategy _pstrategy) {
		this.paintStrategy = _pstrategy;
		this.paintStrategy.init(this);
	}

	// get the ball's PaintStrategy
	public IPaintStrategy getPaintStrategy() {
		return this.paintStrategy;
	}

	// move the ball
	protected void move() {
		this.center.translate((int) this.velocity.x, (int) this.velocity.y);
	}

	// paint ball on the panel.
	protected void paintBall(Graphics g) {
		this.paintStrategy.paint(g, this);
	}

	// update
	@Override
	public void update(IDispatcher<IBallCmd> o, IBallCmd cmd) {
		cmd.apply(this, o);
	}

	public void updateState(IDispatcher<IBallCmd> dispatcher) {
		uptStrategy.updateState(this, dispatcher);
	}

	/**
	 * @param ball the other ball
	 * @param dispatcher the main dispatcher
	 */
	public void interactWith(Ball ball, IDispatcher<IBallCmd> dispatcher) {
		if (itrStrategy == null) {
			IInteractStrategy.nullStrategy.interact(this, ball, dispatcher);
		} else {
			itrStrategy.interact(this, ball, dispatcher);
		}
	}

}
