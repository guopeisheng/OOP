package util;

// used to create smoothly varying sinusoidal numerical data.
public class SineMaker {

	// the midpoint of the min and max values.
	private double mid;

	// the amplitude of the sinusoidal output
	private double amplitude;

	// the amplitude of change when update
	private double change;

	private double theta = -Math.PI / 2.0;

	// a constructer to set object's state
	public SineMaker(double min, double max, double delta) {
		mid = (max + min) / 2.0;
		amplitude = (max - min) / 2.0;
		change = delta;
	}

	// returns different value when update 
	public double getDblVal() {
		double result = mid + amplitude * Math.sin(theta);
		theta += change; // shorthand for _theta = _theta + _delta
		return result;
	}

	// same as getDblVal, but returns the result rounded to the nearest integer.
	public int getIntVal() {
		return (int) Math.round(getDblVal());
	}
}
