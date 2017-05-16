import edu.princeton.cs.algs4.*;
import java.util.*;

public class PointSET {
    private TreeSet<Point2D> points = new TreeSet<>();

    public PointSET () {}
    public boolean isEmpty () {
	return points.isEmpty();}
    public int size () {
	return points.size();}
    public void insert (Point2D p) {
	points.add(p);}
    public boolean contains (Point2D p) {
	return points.contains(p);}
    public void draw () {
	StdDraw.setPenColor(StdDraw.BLACK);
	StdDraw.setPenRadius();
	StdDraw.square(0.5, 0.5, 0.5);
	StdDraw.setPenRadius(0.01);
	for (Point2D p : points) p.draw();}
    public Iterable<Point2D> range (RectHV rect) {return null;}
    public Point2D nearest (Point2D p) {return null;}
    public static void main (String[] args) {
        String filename = args[0];
        In in = new In(filename);
        StdDraw.enableDoubleBuffering();
        PointSET brute = new PointSET();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.insert(p);}
        double x0 = 0.0, y0 = 0.0;      // initial endpoint of rectangle
        double x1 = 0.0, y1 = 0.0;      // current location of mouse
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        brute.draw();
        StdDraw.show();
    }}
