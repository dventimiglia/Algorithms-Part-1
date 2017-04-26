import java.util.*;

public class Deque<T> implements Iterable<T> {
    private class Node {
	T item;
	Node next;
	public String toString () {
	    return String.format("[%s|%s]", item, next);}}

    private Node first, last, nexttolast;

    public String toString () {
	return String.format("first:  %s\nnexttolast:  %s\nlast:  %s\n", first, nexttolast, last);}

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
	Node newnode = new Node();
	newnode.item = item;
	if (size()==0) {
	    first = newnode;
	    nexttolast = newnode;
	    last = newnode;}
	else if (size()==1) {
	    newnode.next = first;
	    first = newnode;
	    nexttolast = newnode;
	    last = last;}
	else if (size()==2) {
	    newnode.next = first;
	    first = newnode;
	    nexttolast = last;
	    last = last;}
	else {
	    newnode.next = first;
	    first = newnode;
	    nexttolast = nexttolast;
	    last = last;}}

    public void addLast (T item) {
	if (item==null) throw new NullPointerException();
	Node newnode = new Node();
	newnode.item = item;
	if (size()==0) {
	    first = newnode;
	    nexttolast = newnode;
	    last = newnode;}
	if (size()==1) {
	    newnode.next = null;
	    first = first;
	    nexttolast = first;
	    first.next = newnode;
	    last = newnode;}
	if (size()==2) {
	    newnode.next = null;
	    first = first;
	    nexttolast = last;
	    last.next = newnode;
	    last = newnode;}
	else {
	    newnode.next = null;
	    first = first;
	    nexttolast = last;
	    last.next = newnode;
	    last = newnode;}}

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
