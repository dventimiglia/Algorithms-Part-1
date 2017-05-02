import edu.princeton.cs.algs4.*;
import java.util.*;

public class BruteCollinearPoints {
    List<LineSegment> segs = new ArrayList<>();
    
    public BruteCollinearPoints (Point[] points) {
	if (points==null) throw new NullPointerException();
	Point[] coll = new Point[4];
	for (Point p : points) if (p==null) throw new NullPointerException();
	for (int i = 0; i<points.length; i++)
	    for (int j = i+1; j<points.length; j++)
		if (points[i].slopeTo(points[j])==Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
	for (int i = 0; i<points.length; i++)
	    for (int j = i+1; j<points.length; j++)
		for (int k = j+1; k<points.length; k++)
		    for (int l = k+1; l<points.length; l++) {
			if (points[i].slopeTo(points[j])==points[i].slopeTo(points[k]) &&
			    points[i].slopeTo(points[j])==points[i].slopeTo(points[l])) {
			    coll[0] = points[i];
			    coll[1] = points[j];
			    coll[2] = points[k];
			    coll[3] = points[l];
			    Arrays.sort(coll);
			    segs.add(new LineSegment(coll[0], coll[3]));}}}

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
	BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	for (LineSegment segment : collinear.segments()) {
	    StdOut.println(segment);
	    segment.draw();}
	StdDraw.show();
    }}
