import edu.princeton.cs.algs4.*;

public class Permutation {
    public static void main (String[] args) {
	int k = Integer.parseInt(args[0]);
	RandomizedQueue<String> q = new RandomizedQueue<>();
	for (String s : StdIn.readAllStrings()) q.enqueue(s);
	for (int i = 0; i<k; i++) StdOut.println(q.dequeue());}}

