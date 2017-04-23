import java.util.*;

public class PercolationStats {
    public static void main (String[] args) {
	if (args.length<2) throw new IllegalArgumentException("Two arguments required.");
	int n = Integer.parseInt(args[0]);
	int T = Integer.parseInt(args[1]);
	PercolationStats stats = new PercolationStats(n, T);
	System.out.println(String.format("mean\t\t\t = %s", stats.mean()));
	System.out.println(String.format("stddev\t\t\t = %s", stats.stddev()));
	System.out.println(String.format("95%% confidence interval\t = [%s, %s]", stats.confidenceLo(), stats.confidenceHi()));}

    protected int n, trials;
    protected double[] data;
    protected double mean, stddev;
    protected Random r = new Random();

    public PercolationStats (int n, int trials) {
	if (n<=0) throw new IllegalArgumentException(String.format("n=%s, but must be greater than 0.", n));
	if (trials<=0) throw new IllegalArgumentException(String.format("trials=%s, but must be greater than 0.", trials));
	this.n = n;
	this.trials = trials;
	this.data = new double[trials];
	for (int i=0; i<trials; i++) data[i] = runTrial();
	double acc1 = 0, acc2 = 0;
	for (double x : data) acc1+=x;
	mean = acc1 / trials;
	for (double x : data) acc2+=Math.pow(x - mean, 2);
	stddev = Math.sqrt(acc2 / (trials - 1));}

    public double mean () {
	return mean;}

    public double stddev () {
	return stddev;}

    public double confidenceLo () {
	return mean - 1.96*stddev/Math.sqrt(trials);}

    public double confidenceHi () {
	return mean + 1.96*stddev/Math.sqrt(trials);}

    protected double runTrial () {
	Percolation p = new Percolation(n);
	TreeSet<Integer> openSites = new TreeSet<>();
	for (int i=0; i<n*n; i++) openSites.add(i);
	while (!p.percolates()) openSite(p, openSites);
	double stat = (double)p.numberOfOpenSites() / (n*n);
	return stat;}

    protected void openSite (Percolation p, TreeSet<Integer> openSites) {
	int index = r.nextInt(n*n);
	int col = index % n;
	int row = index / n;
	p.open(row+1, col+1);
	openSites.remove(index);}}

