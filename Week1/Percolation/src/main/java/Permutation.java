import edu.princeton.cs.algs4.*;

public class Permutation {
    public static void main (String[] args) {
	int k = Integer.parseInt(args[0]);
	RandomizedQueue<String> q = new RandomizedQueue<>();
	for (int i = 0; i<k; i++) q.enqueue(StdIn.readString());
	for (String s : q) StdOut.println(s);}}

