import edu.princeton.cs.algs4.*;
import java.util.*;

public class FastCollinearPoints {
    List<LineSegment> segs = new ArrayList<>();

    public FastCollinearPoints (Point[] points) {
	Arrays.sort(points);
	int i = 0;
	for (Point o : points) {
	    i++;
	    Point[] candidates = Arrays.copyOfRange(points, i, points.length);
	    Arrays.sort(candidates, o.slopeOrder());
	    double oldslope = Double.NEGATIVE_INFINITY;
	    Point oldc = o;
	    int len = 1;
	    for (Point newc : candidates) {
		double newslope = o.slopeTo(newc);
		if (newslope==oldslope) len++;
		if (newslope!=oldslope && len>=3) segs.add(new LineSegment(o, oldc));
		if (newslope!=oldslope) len=1;
		oldc = newc;
		oldslope = newslope;}}}

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
