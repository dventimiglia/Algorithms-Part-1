import edu.princeton.cs.algs4.*;
import java.util.*;

public class FastCollinearPoints {
    List<LineSegment> segs = new ArrayList<>();

    public FastCollinearPoints (Point[] points) {
	Arrays.sort(points);
	for (int i = 0; i<points.length; i++) {
	    Point[] candidates = Arrays.copyOfRange(points, i, points.length);
	    Arrays.sort(candidates, points[i].slopeOrder());
	    double slope = Double.NEGATIVE_INFINITY;
	    int len = 1;
	    for (int j = candidates.length-1; j>=0; j--) {
		if (points[i].slopeTo(candidates[j])==slope)
		    len++;
		else {
		    if (len>=3)
			segs.add(new LineSegment(points[i], candidates[j+len]));
		    len = 1;}
		slope = points[i].slopeTo(candidates[j]);}}}

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
