import edu.princeton.cs.algs4.*;
import java.util.*;

public class Board {
    private int[][] blocks;
    private int blank;
    public Board (int[][] blocks) {
	this.blocks = copyOf(blocks);
	for (int i = 0; i<dimension()*dimension(); i++)
	    if (this.blocks[i/dimension()][i%dimension()]==0) {
		blank = i;
		break;}}
    public int[][] copyOf (int[][] blocks) {
	int[][] copy = new int[blocks.length][blocks[0].length];
	for (int i = 0; i<blocks.length; i++)
	    for (int j = 0; j<blocks[i].length; j++)
		copy[i][j] = blocks[i][j];
	return copy;}
    public int blank () {
	return blank;}
    public int dimension () {
	return blocks.length;}
    public int hamming () {return -1;}
    public int manhattan () {return -1;}
    public boolean isGoal () {return false;}
    public int normalize (int a) {
	return a>=0 ? a % dimension() : normalize(dimension()+a);}
    public Board twin (int x1, int y1, int x2, int y2) {
	int[][] t = copyOf(blocks);
	t[normalize(y1)][normalize(x1)] = blocks[normalize(y2)][normalize(x2)];
	t[normalize(y2)][normalize(x2)] = blocks[normalize(y1)][normalize(x1)];
	return new Board(t);}
    public Board twin () {
	return blank/dimension()==0 ? twin(1,0,1,1) : twin(0,0,0,1);}
    public Board neighbor (int direction) {
	int x = blank()%dimension();
	int y = blank()/dimension();
	switch (direction%4) {
	case 0 : return twin(x,y,x,y-1);
	case 1 : return twin(x,y,x+1,y);
	case 2 : return twin(x,y,x,y+1);
	case 3 : return twin(x,y,x-1,y);
	default : throw new IllegalStateException();}}
    public boolean equals (Object that) {
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
    public static void main (String[] args) {
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

