import edu.princeton.cs.algs4.*;
import java.util.*;

public class Board {
    private int[][] blocks;
    private int blank;

    public Board (final int[][] blocks) {
	this.blocks = copyOf(blocks);
	for (int i = 0; i<dimension()*dimension(); i++)
	    if (this.blocks[i/dimension()][i%dimension()]==0) {
		blank = i;
		break;}}

    private int[][] copyOf (final int[][] blocks) {
	int[][] copy = new int[blocks.length][blocks[0].length];
	for (int i = 0; i<blocks.length; i++)
	    for (int j = 0; j<blocks[i].length; j++)
		copy[i][j] = blocks[i][j];
	return copy;}

    private int blank () {
	return blank;}

    private int wrap (final int a, final int mod) {
	return a>=0 ? a % mod : wrap(mod+a, mod);}

    private int wrap (final int a) {
	return wrap(a, dimension());}

    private int row (final int a) {
	return wrap(a-1, dimension()*dimension())/dimension()+1;}

    private int col (final int a) {
	return wrap(a-1, dimension()*dimension())%dimension()+1;}

    private int cell (final int row, final int col) {
	return blocks[wrap(row-1)][wrap(col-1)];}

    private int cell (final int a) {
	return cell(row(a), col(a));}

    private int hamming (final int row, final int col) {
	return cell(row, col)==0 ? 0 :
	    cell(row, col)==((row-1)*dimension()+col) ? 0 : 1;}

    private int hamming (final int a) {
	return hamming(row(a), col(a));}

    private int abs (final int a) {
	return a>=0 ? a : -1*a;}

    private int manhattan (final int row, final int col) {
	return cell(row, col)==0 ? 0 :
	    abs(row(cell(row, col)) - row) +
	    abs(col(cell(row, col)) - col);}

    private int manhattan (final int a) {
	return manhattan(row(a), col(a));}

    public int dimension () {
	return blocks.length;}

    public int hamming () {
	int errors = 0;
	for (int i = 1; i<=dimension()*dimension(); i++)
	    errors+=hamming(i);
	return errors;}

    public int manhattan () {
	int errors = 0;
	for (int i = 1; i<=dimension()*dimension(); i++)
	    errors+=manhattan(i);
	return errors;}

    public boolean isGoal () {
	if (hamming()!=0 || manhattan()!=0)
	    if (hamming()*manhattan()==0)
		throw new IllegalStateException();
	return hamming()==0;}

    private Board twin (final int x1, final int y1, final int x2, final int y2) {
	int[][] t = copyOf(blocks);
	t[wrap(y1)][wrap(x1)] = blocks[wrap(y2)][wrap(x2)];
	t[wrap(y2)][wrap(x2)] = blocks[wrap(y1)][wrap(x1)];
	return new Board(t);}

    private Board neighbor (final int direction) {
	int x = blank()%dimension();
	int y = blank()/dimension();
	switch (direction%4) {
	case 0 : return twin(x,y,x,y-1);
	case 1 : return twin(x,y,x+1,y);
	case 2 : return twin(x,y,x,y+1);
	case 3 : return twin(x,y,x-1,y);
	default : throw new IllegalStateException();}}

    public Board twin () {
	return blank/dimension()==0 ? twin(1,0,1,1) : twin(0,0,0,1);}

    public boolean equals (final Object that) {
	if (this==that) return true;
	if (!(that instanceof Board)) return false;
	if (that==null) return false;
	if (dimension()!=((Board)that).dimension()) return false;
	for (int i = 0; i<dimension(); i++)
	    for (int j = 0; j<dimension(); j++)
		if (blocks[i][j]!=((Board)that).blocks[i][j]) return false;
	return true;}

    public Iterable<Board> neighbors () {
	int[] directions;
	if (blank()==0)
	    directions = new int[]{1, 2};
	else if (blank()/dimension()==0 && (blank()+1)%dimension()==0)
	    directions = new int[]{2, 3};
	else if (blank()==dimension()*dimension() - 1)
	    directions = new int[]{0, 3};
	else if (blank()/dimension()==(dimension() - 1) && blank()%dimension()==0)
	    directions = new int[]{0, 1};
	else if (blank()/dimension()==0)
	    directions = new int[]{1, 2, 3};
	else if (blank()%dimension()==(dimension() - 1))
	    directions = new int[]{0, 2, 3};
	else if (blank()/dimension()==(dimension() - 1))
	    directions = new int[]{0, 1, 3};
	else if (blank()%dimension()==0)
	    directions = new int[]{0, 1, 2};
	else
	    directions = new int[]{0, 1, 2, 3};
	ArrayList<Board> n = new ArrayList<>();
	for (int d : directions)
	    n.add(neighbor(d));
	return n;}

    public String toString () {
	StringBuffer sb = new StringBuffer();
	for (int[] row : blocks) 
	    sb.append(String.format(" %s  %s  %s\n", row[0], row[1], row[2]));
	return sb.toString();}

    public static void main (final String[] args) {
	Board b = new Board(new int[][]{{1,0,2},
					{3,4,5},
					{6,7,8}});
	System.out.println("Dimension:");
	System.out.println(b.dimension());
	System.out.println("Board:");
	System.out.println(b);
	System.out.println("Neighbors:");
	for (Board n : b.neighbors())
	    System.out.println(n);}}
