import edu.princeton.cs.algs4.*;
import java.awt.*;

public class Percolation {
    public static void main (String[] args) {
	Percolation p = new Percolation(4);}

    public int n;
    public boolean[] sites;
    public int nopen;
    public WeightedQuickUnionUF system;

    public Percolation (int n) {
	if (n<1) throw new IllegalArgumentException();
	this.n = n;
	sites = new boolean[n*n+2];
	nopen = 0;
	system = new WeightedQuickUnionUF(n*n+2);
	for (int i=0; i<sites.length; i++) sites[i] = false;
	sites[top()] = true;
	sites[bot()] = true;}
	// for (int i=1; i<=n; i++) if (sites[index(1, i)]) system.union(top(), index(1, i));
	// for (int i=1; i<=n; i++) if (sites[index(1, i)]) system.union(index(n, i), bot());}

    public void open (int row, int col) {
	if (!isOpen(row, col)) {
	    sites[index(row, col)] = true;
	    nopen++;
	    join(row, col, row, col-1);
	    join(row, col, row, col+1);
	    join(row, col, row-1, col);
	    join(row, col, row+1, col);}}

    public boolean isOpen (int row, int col) {
	return sites[index(row, col)];}

    public boolean isFull (int row, int col) {
	return isOpen(row, col) && system.connected(top(), index(row, col));}

    public int numberOfOpenSites () {
	return nopen;}

    public boolean percolates () {
	return system.connected(top(), bot());}

    private boolean isInBounds (int n) {
	return n>=1 && n<=this.n;}

    private int index (int row, int col) {
	if (!isInBounds(col)) throw new IndexOutOfBoundsException(String.format("[%s,%s]",row,col));
	if (row==0) return top();
	if (row==n+1) return bot();
	return (row-1)*n + col;}

    private int top () {
	return 0;}

    private int bot () {
	return n*n+1;}

    private void join (int row1, int col1, int row2, int col2) {
	try {if (sites[index(row2, col2)] && sites[index(row1, col1)])
		system.union(index(row1, col1), index(row2, col2));}
	catch (Exception e) {}}}
