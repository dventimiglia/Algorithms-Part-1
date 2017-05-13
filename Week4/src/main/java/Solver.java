import edu.princeton.cs.algs4.*;
import java.util.*;

public class Solver {
    private SearchNode first, last;
    private Puzzle p1, p2;
    private List<Board> target = new ArrayList<>();

    public Solver (Board initial) {
	p1 = new Puzzle(initial);
	p2 = new Puzzle(initial.twin());
	Iterator<SearchNode> i1 = p1.iterator();
	Iterator<SearchNode> i2 = p2.iterator();
	while (i1.hasNext() && i2.hasNext()) {last = i1.next(); i2.next();}
	if (i2.hasNext())
	    new Iterable<Board>() {
		@Override
		public Iterator<Board> iterator () {
		    return new Iterator<Board>() {
			SearchNode curr = last;
			@Override
			public boolean hasNext () {
			    if (
    public boolean isSolvable () {
	return last!=null;}
    public int moves () {
	if (!isSolvable()) throw new IllegalStateException();
	List<Board> target = new ArrayList<>();
	solution().forEach(target::add);
	return target.size();}
    public Iterable<Board> solution () {
	return new Iterable<Board>() {
	    @Override
	    public Iterator<Board> iterator () {
		return new Iterator<Board>() {
		    SearchNode curr = p1.first;
		    @Override
		    public boolean hasNext () {
			if (curr.next==null) return false;
			return true;}
		    @Override
		    public Board next () {
			if (!hasNext()) throw new NoSuchElementException();
			Board b = curr.board;
			curr = curr.next;
			return b;}};}};}
    public static void main (String[] args) {
	// create initial board from file
	In in = new In(args[0]);
	int n = in.readInt();
	int[][] blocks = new int[n][n];
	for (int i = 0; i < n; i++)
	    for (int j = 0; j < n; j++)
		blocks[i][j] = in.readInt();
	Board initial = new Board(blocks);
	// solve the puzzle
	Solver solver = new Solver(initial);
	// print solution to standard output
	if (!solver.isSolvable())
	    StdOut.println("No solution possible");
	else {
	    StdOut.println("Minimum number of moves = " + solver.moves());
	    for (Board board : solver.solution())
		StdOut.println(board);}}}

class SearchNode implements Comparable<SearchNode> {
    public Board board;
    public int moves;
    public SearchNode previous;
    public SearchNode next;
    public SearchNode (final Board board, final int moves, final SearchNode previous) {
	this.board = board;
	this.moves = moves;
	this.previous = previous;
	if (this.previous!=null)
	    this.previous.next = this;}
    public int priority () {
	return board.hamming() + moves;}
    public int compareTo (SearchNode that) {
	if (this.priority() < that.priority()) return -1;
	if (this.priority() > that.priority()) return +1;
	return 0;}
    @Override
    public String toString () {
	StringBuffer sb = new StringBuffer();
	sb.append(String.format("priority:  %s\n", priority()));
	sb.append(String.format("%s\n", board));
	return sb.toString();}}

class Puzzle implements Iterable<SearchNode> {
    public SearchNode first = null;
    public Puzzle (Board initial) {
	first = new SearchNode(initial, 0, null);}
    public Iterator<SearchNode> iterator () {
	return new Iterator<SearchNode>() {
	    MinPQ<SearchNode> pq = new MinPQ<>();
	    SearchNode last = first;
	    boolean launched = false;
	    {pq.insert(last);}
	    @Override
	    public String toString () {
		StringBuffer sb = new StringBuffer();
		for (SearchNode sn : pq)
		    sb.append(sn.toString());
		sb.append("------------------");
		return sb.toString();}
	    @Override
	    public boolean hasNext () {
		if (pq.isEmpty()) throw new IllegalStateException();
		if (!launched) return true;
		if (!last.board.isGoal()) return true;
		return false;}
	    @Override
	    public SearchNode next () {
		// System.out.println(this);
		last = pq.delMin();
		for (Board b : last.board.neighbors()) {
		    if (last.previous==null || !last.previous.board.equals(b))
			pq.insert(new SearchNode(b, last.moves+1, last));}
		if (!launched) launched = true;
		return last;}};}}
