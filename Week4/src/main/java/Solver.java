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
	    (new Iterable<Board>() {
		@Override
		public Iterator<Board> iterator () {
		    return new Iterator<Board>() {
			SearchNode curr = new SearchNode(null, 0, last);
			@Override
			public boolean hasNext () {
			    if (curr.previous==null) return false;
			    return true;}
			@Override
			public Board next () {
			    if (!hasNext()) throw new NoSuchElementException();
			    curr = curr.previous;
			    return curr.board;}};}}).forEach(target::add);
	Collections.reverse(target);}
    public boolean isSolvable () {
	return !target.isEmpty();}
    public int moves () {
	return target.size()-1;}
    public Iterable<Board> solution () {
	return isSolvable() ? target : null;}
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
    public SearchNode (final Board board, final int moves, final SearchNode previous) {
	this.board = board;
	this.moves = moves;
	this.previous = previous;}
    public int priority () {
	return board.manhattan() + moves;}
    public int compareTo (SearchNode that) {
	if (this.priority() < that.priority()) return -1;
	if (this.priority() > that.priority()) return +1;
	return 0;}}

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
	    public boolean hasNext () {
		if (pq.isEmpty()) throw new IllegalStateException();
		if (!launched) return true;
		if (!last.board.isGoal()) return true;
		return false;}
	    @Override
	    public SearchNode next () {
		last = pq.delMin();
		for (Board b : last.board.neighbors()) {
		    if (last.previous==null || !last.previous.board.equals(b))
			pq.insert(new SearchNode(b, last.moves+1, last));}
		if (!launched) launched = true;
		return last;}};}}
