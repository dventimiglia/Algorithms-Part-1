import java.util.*;

public class Deque<T> implements Iterable<T> {
    private class Node {
	T item;
	Node next;
	Node prev;
	public String toString () {
	    return String.format("[%s|%s]", item, reversed ? prev : next);}}

    private Node first, last;
    private boolean reversed;

    public String toString () {
	return String.format("%s", reversed ? last : first);}

    public Deque () {
	first = null;
	last = null;
	reversed = false;}

    public boolean isEmpty () {
	return first==null && last==null;}

    public int size () {
	int n = 0;
	for (T item : this) n++;
	return n;}

    public void reverse () {
	reversed = !reversed;}
    
    public void add (T item) {
	if (item==null) throw new NullPointerException();
	Node newnode = new Node();
	newnode.item = item;
	if (size()==0) {
	    first = newnode;
	    last = newnode;}
	else {
	    if (reversed) {
		last.next = newnode;
		newnode.prev = last;
		last = newnode;}
	    else {
		first.prev = newnode;
		newnode.next = first;
		first = newnode;}}}

    public void addFirst (T item) {
	if (reversed) {
	    reverse();
	    add(item);
	    reverse();}
	else {
	    add(item);}}

    public void addLast (T item) {
	if (reversed) {
	    add(item);}
	else {
	    reverse();
	    add(item);
	    reverse();}}

    public boolean isReversed () {
	return reversed;}

    public T remove () {
	if (size()==0) throw new NoSuchElementException();
	T item = first.item;
	if (size()==1) {
	    first = null;
	    last = null;}
	else {
	    if (reversed) {
		last.prev.next = null;
		last = last.prev;}
	    else {
		first.next.prev = null;
		first = first.next;}}
	return item;}

    public T removeFirst () {
	if (reversed) {
	    reverse();
	    T item = remove();
	    reverse();
	    return item;}
	else return remove();}

    public T removeLast () {
	if (reversed) return remove();
	else {
	    reverse();
	    T item = remove();
	    reverse();
	    return item;}}

    public Iterator<T> iterator () {
	return new Iterator<T>() {
	    Node current = reversed ? last : first;
	    public boolean hasNext () {return current!=null;}
	    public void remove () {throw new UnsupportedOperationException();}
	    public T next () {
		T item = current.item;
		current = reversed ? current.prev : current.next;
		return item;}};}}
