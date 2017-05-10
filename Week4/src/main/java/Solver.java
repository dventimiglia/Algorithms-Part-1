import edu.princeton.cs.algs4.*;
import java.util.*;

public class Solver {
    public MinPQ<SearchNode> pq = new MinPQ<>(new HammingPriority());
    public SearchNode first = null;
    public SearchNode last = null;

    public Solver (Board initial) {
	first = new SearchNode(initial, 0, null);
	last = first;
	pq.insert(last);
	while (!pq.isEmpty() && !last.board.isGoal()) {
	    last = pq.delMin();
	    if (last.previous!=null)
		last.previous.next = last;
	    for (Board b : last.board.neighbors()) {
		if (last.previous==null || !last.previous.board.equals(b))
		    pq.insert(new SearchNode(b, last.moves+1, last));}}}
    public boolean isSolvable () {
	return true;}
    public int moves () {
	return last.moves;}
    public Iterable<Board> solution () {
	return new Iterable<Board>() {
	    @Override
	    public Iterator<Board> iterator () {
		return new Iterator<Board> () {
		    SearchNode curr = first;
		    @Override
		    public boolean hasNext () {
			return curr!=null;}
		    @Override
		    public Board next () {
			if (!hasNext())
			    throw new NoSuchElementException();
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

class SearchNode {
    public Board board;
    public int moves;
    public SearchNode previous;
    public SearchNode next;
    public SearchNode (final Board board, final int moves, final SearchNode previous) {
	this.board = board;
	this.moves = moves;
	this.previous = previous;}
    @Override
    public String toString () {
	StringBuffer sb = new StringBuffer();
	sb.append(String.format("ham:  %s\n", board.hamming()));
	sb.append(String.format("man:  %s\n", board.hamming()));
	sb.append(String.format("mov:  %s\n", moves));
	sb.append(String.format("%s\n", board));
	sb.append(previous==null ? "" : previous);
	return sb.toString();}}

class HammingPriority implements Comparator<SearchNode> {
    @Override
    public int compare (SearchNode o1, SearchNode o2) {
	if (o1.board.hamming() + o1.moves < o2.board.hamming() + o2.moves)
	    return -1;
	if (o1.board.hamming() + o1.moves > o2.board.hamming() + o2.moves)
	    return 1;
	return 0;}}

class ManhattanPriority implements Comparator<SearchNode> {
    @Override
    public int compare (SearchNode o1, SearchNode o2) {
	if (o1.board.manhattan() + o1.moves < o2.board.manhattan() + o2.moves)
	    return -1;
	if (o1.board.manhattan() + o1.moves > o2.board.manhattan() + o2.moves)
	    return 1;
	return 0;}}
