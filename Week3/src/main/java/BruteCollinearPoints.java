import java.util.*;

public class BruteCollinearPoints {
    List<LineSegment> segs = new ArrayList<>();
    
    public BruteCollinearPoints (Point[] points) {
	if (points==null) throw new NullPointerException();
	Point[] coll = new Point[4];
	for (Point p : points) if (p==null) throw new NullPointerException();
	for (int i = 0; i<points.length; i++)
	    for (int j = 0; j<points.length; j++)
		if (points[i].slopeTo(points[j])==Double.NEGATIVE_INFINITY) throw new IllegalArgumentException();
	for (int i = 0; i<points.length; i++)
	    for (int j = i+1; j<points.length; j++)
		for (int k = j+1; j<points.length; k++)
		    for (int l = k+1; l<points.length; l++) {
			if (points[i].slopeTo(points[j])==points[i].slopeTo(points[k]) &&
			    points[i].slopeTo(points[k])==points[i].slopeTo(points[l])) {
			    coll[0] = points[i];
			    coll[1] = points[j];
			    coll[2] = points[k];
			    coll[3] = points[l];
			    Arrays.sort(coll);
			    segs.add(new LineSegment(coll[0], coll[3]));}}}

    public int numberOfSegments () {
	return segs.size();}

    public LineSegment[] segments () {
	return segs.toArray(new LineSegment[0]);}}
