import edu.princeton.cs.algs4.*;

public class Percolation {
    int n;
    boolean[] sites;
    int nopen;
    WeightedQuickUnionUF system;

    public static void main (String[] args) {
	Percolation p = new Percolation(4);}

    public Percolation (int n) {
	if (n<1) throw new IllegalArgumentException();
	this.n = n;
	sites = new boolean[n*n+2];
	nopen = 0;
	system = new WeightedQuickUnionUF(n*n+2);
	sites[0] = true;
	sites[n+1] = true;
	for (int i=1; i<=n; i++) system.union(top(), index(1, i));
	for (int i=1; i<=n; i++) system.union(index(n, i), bot());}

    public void open (int row, int col) {
	sites[index(row, col)] = true;
	nopen++;
	join(row, col, row, col-1);
	join(row, col, row, col+1);
	join(row, col, row-1, col);
	join(row, col, row+1, col);}

    public void join (int row1, int col1, int row2, int col2) {
	try {if (sites[index(row2, col2)]) system.union(index(row1, col1), index(row2, col2));}
	catch (Exception e) {}}

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

    public int index (int row, int col) {
	if (!isInBounds(row) || !isInBounds(col))
	    throw new IndexOutOfBoundsException(String.format("[%s,%s]",row,col));
	return (row-1)*n + 1;}

    public int top () {
	return 0;}

    public int bot () {
	return n;}}
