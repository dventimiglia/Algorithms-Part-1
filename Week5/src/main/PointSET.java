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
	for (Point2D p : this) p.draw();}
    public Iterable<Point2D> range (RectHV rect) {return null;}
    public Point2D nearest (Point2D p) {return null;}
    public static void main (String[] args) {}}
