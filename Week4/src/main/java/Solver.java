import edu.princeton.cs.algs4.*;
import java.util.*;

public class Solver {
    public Board initial = null;

    public Solver (Board initial) {
	this.initial = initial;}
    public boolean isSolvable () {
	return true;}
    public int moves () {
	int moves = 0;
	for (Board b : solution())
	    moves++;
	return moves;}
    public Iterable<Board> solution () {
	return new Iterable<Board> () {
	    @Override
	    public Iterator<Board> iterator () {
		return new Iterator<Board> () {
		    Iterator<SearchNode> i1 = (new Puzzle(initial)).iterator();
		    Iterator<SearchNode> i2 = (new Puzzle(initial)).iterator();
		    @Override
		    public boolean hasNext () {
			return i1.hasNext() && i2.hasNext();}
		    public Board next () {
			return i1.hasNext() ? i2.next().board : i1.next().board;}};}};}
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

class Puzzle implements Iterable<SearchNode> {
    public SearchNode first = null;
    public Puzzle (Board initial) {
	first = new SearchNode(initial, 0, null);}
    public Iterator<SearchNode> iterator () {
	return new Iterator<SearchNode>() {
	    MinPQ<SearchNode> pq = new MinPQ<>(new HammingPriority());
	    SearchNode last = first;
	    {pq.insert(last);}
	    @Override
	    public boolean hasNext () {
		return !pq.isEmpty() && !last.board.isGoal();}
	    @Override
	    public SearchNode next () {
		last = pq.delMin();
		for (Board b : last.board.neighbors()) {
		    if (last.previous==null || !last.previous.board.equals(b))
			pq.insert(new SearchNode(b, last.moves+1, last));}
		return last;}};}}
