import edu.princeton.cs.algs4.*;
import java.util.*;

public class PointSET {
    private TreeSet<Point2D> points = new TreeSet<>();

    public PointSET () {}
    public boolean isEmpty () {
	return points.isEmpty();}
    public int size () {
	return points.size();}
    public void insert (final Point2D p) {
	if (p==null) throw new NullPointerException("p is null!");
	points.add(p);}
    public boolean contains (final Point2D p) {
	if (p==null) throw new NullPointerException("p is null!");
	return points.contains(p);}
    public void draw () {
	StdDraw.setPenColor(StdDraw.BLACK);
	StdDraw.setPenRadius();
	StdDraw.square(0.5, 0.5, 0.5);
	StdDraw.setPenRadius(0.01);
	for (Point2D p : points) p.draw();}
    public Iterable<Point2D> range (final RectHV rect) {
	if (rect==null) throw new NullPointerException("rect is null!");
	return
	    points
	    .stream()
	    .filter(p ->
		    p.x()>=rect.xmin() &&
		    p.x()<=rect.xmax() &&
		    p.y()>=rect.ymin() &&
		    p.y()<=rect.ymax())::iterator;}
    public Point2D nearest (final Point2D p) {
	if (p==null) throw new NullPointerException("p is null!");
	return
	    points
	    .stream()
	    .reduce((p1, p2) -> p.distanceSquaredTo(p1) < p.distanceSquaredTo(p2) ? p1 : p2)
	    .orElse(null);}
    public static void main (final String[] args) {
        String filename = args[0];
        In in = new In(filename);
        StdDraw.enableDoubleBuffering();
        PointSET brute = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.insert(p);}
        double x0 = 0.0, y0 = 0.0;
        double x1 = 0.0, y1 = 0.0;
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        brute.draw();
        StdDraw.show();}}
