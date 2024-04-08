package CircleHitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CircleHitTestTest {
    public static void main(String[] args) {
        // Creating a point and a circle for testing
        Point center = new Point(5, 5);
        Circle circle = new Circle(center, 3);
        Point insidePoint = new Point(6, 6);
        Point outsidePoint = new Point(10, 10);

        // Testing PreciseCircleHitTest
        PreciseCircleHitTest preciseTest = new PreciseCircleHitTest(circle, insidePoint);
        System.out.println("PreciseCircleHitTest: Inside point test result: " + preciseTest.containsPoint(circle, insidePoint)); // Should print true
        System.out.println("PreciseCircleHitTest: Outside point test result: " + preciseTest.containsPoint(circle, outsidePoint)); // Should print false

        // Testing FastCircleHitTest
        FastCircleHitTest fastTest = new FastCircleHitTest(circle, insidePoint);
        System.out.println("FastCircleHitTest: Inside point test result: " + fastTest.containsPoint(circle, insidePoint)); // Should print true
        System.out.println("FastCircleHitTest: Outside point test result: " + fastTest.containsPoint(circle, outsidePoint)); // Should print false
    }
}

