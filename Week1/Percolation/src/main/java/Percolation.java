import edu.princeton.cs.algs4.*;
import java.awt.*;

public class Percolation {
    public static void main (String[] args) {
	Percolation p = new Percolation(4);}

    protected int n;
    protected boolean[] sites;
    protected int nopen;
    protected WeightedQuickUnionUF system;
    protected boolean visualize = false;

    public Percolation (int n) {
	this(n, false);}

    public Percolation (int n, boolean visualize) {
	if (n<1) throw new IllegalArgumentException();
	this.n = n;
	this.visualize = visualize;
	sites = new boolean[n*n+2];
	nopen = 0;
	system = new WeightedQuickUnionUF(n*n+2);
	for (int i=0; i<sites.length; i++) sites[i] = false;
	sites[top()] = true;
	sites[bot()] = true;
	for (int i=1; i<=n; i++) system.union(top(), index(1, i));
	for (int i=1; i<=n; i++) system.union(index(n, i), bot());
	if (this.visualize) {
	    init();
	    draw(100);}}

    public void open (int row, int col) {
	if (!isOpen(row, col)) {
	    sites[index(row, col)] = true;
	    nopen++;
	    join(row, col, row, col-1);
	    join(row, col, row, col+1);
	    join(row, col, row-1, col);
	    join(row, col, row+1, col);
	    if (visualize) draw(100);}}

    public boolean isOpen (int row, int col) {
	return sites[index(row, col)];}

    public boolean isFull (int row, int col) {
	return !isOpen(row, col);}

    public int numberOfOpenSites () {
	return nopen;}

    public boolean percolates () {
	return system.connected(top(), bot());}

    public boolean isInBounds (int n) {
	return n>=1 && n<=this.n;}

    protected int index (int row, int col) {
	if (!isInBounds(row) || !isInBounds(col))
	    throw new IndexOutOfBoundsException(String.format("[%s,%s]",row,col));
	return (row-1)*n + col;}

    protected int top () {
	return 0;}

    protected int bot () {
	return n*n+1;}

    protected void init () {
	StdDraw.enableDoubleBuffering();}

    protected void draw (int delay) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-0.05*n, 1.05*n);
        StdDraw.setYscale(-0.05*n, 1.05*n);   // leave a border to write text
        StdDraw.filledSquare(n/2.0, n/2.0, n/2.0);

        // draw n-by-n grid
        int opened = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (isFull(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                }
                else if (isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    opened++;
                }
                else
                    StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledSquare(col - 0.5, n - row + 0.5, 0.45);
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.25*n, -0.025*n, numberOfOpenSites() + " open sites");
        if (percolates()) StdDraw.text(0.75*n, -0.025*n, "percolates");
        else StdDraw.text(0.75*n, -0.025*n, "does not percolate");
	StdDraw.show();
        StdDraw.pause(delay);}

    protected void join (int row1, int col1, int row2, int col2) {
	try {if (sites[index(row2, col2)]) system.union(index(row1, col1), index(row2, col2));}
	catch (Exception e) {}}
}
