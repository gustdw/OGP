package circleHitTest;

/**
 * Point object contains an x- & y-coordinate
 * @immutable
 */
class Point {
	private int x, y;
	
	/**
	 * Creates an instance of the object 'Point' 
	 * @post | this.getX() == x
	 * @post | this.getY() == y
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	int getX() {return this.x;}
	
	int getY() {return this.y;}
	
	double distanceTo(Point point) {
		return Math.sqrt( (this.getX() - point.getX())^2 + (this.getY() - point.getY())^2 );
	}
}

/**
 * Checks whether or not a given Point is inside of a to-be specified area near the circle.
 * @return | true || false
 */
abstract class CircleHitTest {
	abstract boolean containsPoint(Circle circle, Point testPoint);
}

/**
 * Circle object that is defined by a center of type Point and a radius of type int.
 * @immutable 
 */
class Circle {
	/**
	 * @invar | radius >= 0
	 * @invar | center != null
	 */
	Point center;
	int radius;
	
	/**
	 * @post | this.getCenter() == center
	 * @post | this.getRadius() == radius
	 */
	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}
	
	Point getCenter() {return this.center;}
	int getRadius() {return this.radius;}
}

/**
 * Checks whether or not a given Point lies within the circle
 */
class PreciseCircleHitTest extends CircleHitTest {
	Point testPoint;
	Circle circle;
	
	/**
	 * @throws NullPointerException | circle == null
	 * @throws NullPointerException | testPoint == null
	 * 
	 * @post | this.getCircle() == circle
	 * @post | this.getPoint() == testPoint
	 */
	public PreciseCircleHitTest(Circle circle, Point testPoint) {
		if (circle == null)
			throw new NullPointerException("Circle object is null");
		if (testPoint == null)
			throw new NullPointerException("testPoint object is null");
		this.circle = circle;
		this.testPoint = testPoint;
	}
	
	Circle getCircle() {return this.circle;}
	Point getPoint() {return this.testPoint;}
	/**
	 * Checks whether or not a given Point lies within the circle, including the border
	 * @return | testPoint.distanceTo(circle.getCenter()) <= circle.getRadius()
	 */
	public boolean containsPoint(Circle circle, Point testPoint) {
		return (testPoint.distanceTo(circle.getCenter()) < circle.getRadius());
	}
}


/**
 * Checks whether or not a given Point lies within a bounding box around the circle. This is a square with its sides parallel with it's axis.
 */
class FastCircleHitTest extends CircleHitTest {
	Point testPoint;
	Circle circle;
	
	/**
	 * @pre | circle != null
	 * @pre | testPoint != null
	 * @post | this.getCircle() == circle
	 * @post | this.getPoint() == testPoint
	 */
	public FastCircleHitTest(Circle circle, Point testPoint) {
		this.circle = circle;
		this.testPoint = testPoint;
	}
	
	Circle getCircle() {return this.circle;}
	Point getPoint() {return this.testPoint;}
	/**
	 * Checks whether or not a given Point lies within a bounding box around the circle. This is a square with its sides parallel with it's axis.
	 * @return | (testPoint.getX() >= (circle.getCenter().getX() - circle.getRadius()) && 
					testPoint.getX() < (circle.getCenter().getX() + circle.getRadius()) &&
					testPoint.getY() >= (circle.getCenter().getY() - circle.getRadius()) &&
					testPoint.getY() < (circle.getCenter().getY() + circle.getRadius()) )
	 */
	boolean containsPoint(Circle circle, Point testPoint) {
		return (testPoint.getX() >= (circle.getCenter().getX() - circle.getRadius()) && 
				testPoint.getX() < (circle.getCenter().getX() + circle.getRadius()) &&
				testPoint.getY() >= (circle.getCenter().getY() - circle.getRadius()) &&
				testPoint.getY() < (circle.getCenter().getY() + circle.getRadius()));
	}
}