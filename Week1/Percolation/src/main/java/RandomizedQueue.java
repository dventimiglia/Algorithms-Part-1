import edu.princeton.cs.algs4.*;
import java.util.*;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int N = 0;
    private boolean kloned = false;

    private RandomizedQueue (Item[] copy) {
	s = copy;
	N = copy.length;
	kloned = true;}

    private RandomizedQueue (int capacity) {
	s = (Item[]) new Object[capacity];}

    private void push (Item item) {
	if (item==null) throw new NullPointerException();
	if (N==s.length) resize(2*s.length);
	s[N++] = item;}

    private Item pop () {
	if (N==0) throw new NoSuchElementException();
	Item item = s[--N];
	s[N] = null;
	if (N>0 && N==s.length/4) resize(s.length/2);
	return item;}

    private void resize (int capacity) {
	Item[] copy = (Item[]) new Object[capacity];
	for (int i = 0; i<N; i++) copy[i] = s[i];
	s = copy;}

    private Object klone () {
	Item[] copy = (Item[]) new Object[N];
	for (int i = 0; i<N; i++) copy[i] = s[i];
	RandomizedQueue<Object> c = new RandomizedQueue<>(copy);
	return c;}

    public RandomizedQueue () {
	this(1);}

    public boolean isEmpty () {
	return N==0;}

    public void enqueue (Item item) {
	push(item);}

    public Item dequeue () {
	if (N==0) throw new NoSuchElementException();
	int i = StdRandom.uniform(N);
	Item last = s[N-1];
	s[N-1] = s[i];
	s[i] = last;
	return pop();}

    public Item sample () {
	if (N==0) throw new NoSuchElementException();
	return s[StdRandom.uniform(N)];}

    public int size () {
	return N;}

    public Iterator<Item> iterator () {
	if (!kloned) return ((RandomizedQueue)klone()).iterator();
	return new Iterator<Item> () {
	    public boolean hasNext () {
		return N>0;}
	    public void remove () {
		throw new UnsupportedOperationException();}
	    public Item next () {
		if (hasNext()) return dequeue();
		throw new NoSuchElementException();}};}}
