import edu.princeton.cs.algs4.*;
import java.util.*;

public class FastCollinearPoints {
    List<LineSegment> segs = new ArrayList<>();

    public FastCollinearPoints (Point[] points) {
	for (int i = 0; i<points.length; i++) {
	    Point[] others = new Point[points.length-i];
	    for (int j = i+1; j<points.length; j++)
		others[j-(i+1)] = points[j];
	    Arrays.sort(others, points[i].slopeOrder());
	    int low = 0;
	    int high = 0;
	    double slope = Double.NEGATIVE_INFINITY;
	    for (int j = 0; j<others.length; j++)
		if (points[j].slopeTo(others[j])==slope)
		    high++;
		else {
		    low = high;
		    segs.add(new LineSegment(points[i], others[j-1]));}}}

    public int numberOfSegments () {
	return segs.size();}

    public LineSegment[] segments () {
	return segs.toArray(new LineSegment[0]);}

    public static void main (String[] args) {
	In in = new In(args[0]);
	int n = in.readInt();
	Point[] points = new Point[n];
	for (int i = 0; i < n; i++) {
	    int x = in.readInt();
	    int y = in.readInt();
	    points[i] = new Point(x, y);}
	StdDraw.enableDoubleBuffering();
	StdDraw.setXscale(0, 32768);
	StdDraw.setYscale(0, 32768);
	for (Point p : points) p.draw();
	StdDraw.show();
	FastCollinearPoints collinear = new FastCollinearPoints(points);
	for (LineSegment segment : collinear.segments()) {
	    StdOut.println(segment);
	    segment.draw();}
	StdDraw.show();}}
