import edu.princeton.cs.algs4.*;
import java.util.*;

public class Board {
    private int[][] blocks;
    private int blank;
    private int manhattan = -1;
    private int hamming = -1;

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
	if (hamming<0) {
	    hamming = 0;
	    for (int i = 1; i<=dimension()*dimension(); i++)
		hamming+=hamming(i);}
	return hamming;}

    public int manhattan () {
	if (manhattan<0) {
	    manhattan = 0;
	    for (int i = 1; i<=dimension()*dimension(); i++)
		manhattan+=manhattan(i);}
	return manhattan;}

    public boolean isGoal () {
	return manhattan()==0;}

    private Board twin (final int r1, final int c1, final int r2, final int c2) {
	int[][] t = copyOf(blocks);
	t[wrap(r1)][wrap(c1)] = blocks[wrap(r2)][wrap(c2)];
	t[wrap(r2)][wrap(c2)] = blocks[wrap(r1)][wrap(c1)];
	return new Board(t);}

    private Board neighbor (final int direction) {
	int c = blank()%dimension();
	int r = blank()/dimension();
	switch (direction%4) {
	case 0 : return twin(r,c,r-1,c);
	case 1 : return twin(r,c,r,c+1);
	case 2 : return twin(r,c,r+1,c);
	case 3 : return twin(r,c,r,c-1);
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

    public String toString() {
	StringBuilder s = new StringBuilder();
	s.append(dimension() + "\n");
	for (int i = 0; i < dimension(); i++) {
	    for (int j = 0; j < dimension(); j++) {
		s.append(String.format("%2d ", blocks[i][j]));
	    }
	    s.append("\n");
	}
	return s.toString();
    }

    // public String toString () {
    // 	StringBuffer sb = new StringBuffer();
    // 	sb.append(String.format("%d\n", dimension()));
    // 	String fmt = String.join(" ", Collections.nCopies(dimension(), "%s")) + "\n";
    // 	for (int[] row : blocks) {
    // 	    int col = 0;
    // 	    for (int cell : row) {
    // 		sb.append(String.format(col==0 ? "%2d" : "%3d", cell));
    // 		col++;}
    // 	    sb.append("\n");}
    // 	return sb.toString();}

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
