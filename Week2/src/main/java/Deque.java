import java.util.*;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
	Item item;
	Node next;
	Node prev;
	@Override
	public String toString () {
	    return String.format("[%s|%s]", item, reversed ? prev : next);}}

    private Node first, last;
    private int N;
    private boolean reversed;

    private void reverse () {
	reversed = !reversed;}
    
    private void add (Item item) {
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
		first = newnode;}}
	N++;}

    private boolean isReversed () {
	return reversed;}

    private Item remove () {
	if (size()==0) throw new NoSuchElementException();
	if (size()==1) {
	    Item item = first.item;
	    first = null;
	    last = null;
	    N--;
	    return item;}
	if (reversed) {
	    Item item = last.item;
	    last.prev.next = null;
	    last = last.prev;
	    N--;
	    return item;}
	else {
	    Item item = first.item;
	    first.next.prev = null;
	    first = first.next;
	    N--;
	    return item;}}

    public Deque () {
	first = null;
	last = null;
	reversed = false;
	N = 0;}

    public boolean isEmpty () {
	return N==0;}

    @Override
    public String toString () {
	return String.format("%s", reversed ? last : first);}

    public int size () {
	return N;}

    public void addFirst (Item item) {
	if (reversed) {
	    reverse();
	    add(item);
	    reverse();}
	else {
	    add(item);}}

    public void addLast (Item item) {
	if (reversed) {
	    add(item);}
	else {
	    reverse();
	    add(item);
	    reverse();}}

    public Item removeFirst () {
	if (reversed) {
	    reverse();
	    Item item = remove();
	    reverse();
	    return item;}
	else return remove();}

    public Item removeLast () {
	if (reversed) return remove();
	else {
	    reverse();
	    Item item = remove();
	    reverse();
	    return item;}}

    public Iterator<Item> iterator () {
	return new Iterator<Item>() {
	    Node current = reversed ? last : first;
	    public boolean hasNext () {return current!=null;}
	    public void remove () {throw new UnsupportedOperationException();}
	    public Item next () {
		if (hasNext()) {
		    Item item = current.item;
		    current = reversed ? current.prev : current.next;
		    return item;}
		throw new NoSuchElementException();}};}}
