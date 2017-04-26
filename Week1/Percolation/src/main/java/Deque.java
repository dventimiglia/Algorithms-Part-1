import java.util.*;

public class Deque<T> implements Iterable<T> {
    private class Node {
	T item;
	Node next;}

    private Node first, last, nexttolast;

    public Deque () {
	first = null;
	last = null;
	nexttolast = null;}

    public boolean isEmpty () {
	if (first==null && last==null && nexttolast==null) return true;
	throw new IllegalStateException();}

    public int size () {
	int n = 0;
	for (T item : this) n++;
	return n;}

    public void addFirst (T item) {
	if (item==null) throw new NullPointerException();
	Node oldfirst = first;
	first = new Node();
	first.item = item;
	first.next = oldfirst;
	if (size()<=1) nexttolast = first;
	if (size()==0) lst = first;}

    public void addLast (T item) {
	if (item==null) throw new NullPointerException();
	Node oldlast = last;
	last = new Node();
	last.item = item;
	last.next = null;
	if (size()==0) {
	    nexttolast = last;
	    first = last;}
	if (size()>=2) {
	    nexttolast = oldlast;}
	oldfirst.next = last;}

    public T removeFirst () {
	if (isEmpty()) throw new NoSuchElementException();
	T item = first.item;
	first = first.next;
	if (isEmpty()) first = null;
	return item;}

    public T removeLast () {
	if (isEmpty()) throw new NoSuchElementException();

	T item = last.item;
	last = first.next;
	if (isEmpty()) last = null;
	return item;}

    public Iterator<T> iterator () {
	return new Iterator<T>() {
	    Node current = first;
	    public boolean hasNext () {return current!=null;}
	    public void remove () {throw new UnsupportedOperationException();}
	    public T next () {
		T item = current.item;
		current = current.next;
		return item;}};}

    public static void main (String[] args) {
	Deque<String> d = new Deque<>();
	d.addFirst("1");
    }
}
