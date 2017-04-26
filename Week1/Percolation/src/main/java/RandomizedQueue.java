import edu.princeton.cs.algs4.*;
import java.util.*;

public class RandomizedQueue<T> implements Iterable<T>, Cloneable {
    private T[] s;
    private int N = 0;
    private boolean cloned = false;

    private RandomizedQueue (T[] storage) {
	s = storage;
	N = storage.length;
	cloned = true;}

    private RandomizedQueue (int capacity) {
	s = (T[]) new Object[capacity];}

    private void push (T item) {
	if (N==s.length) resize(2*s.length);
	s[N++] = item;}

    private T pop () {
	if (N==0) throw new NoSuchElementException();
	T item = s[--N];
	s[N] = null;
	if (N>0 && N==s.length/4) resize(s.length/2);
	return item;}

    private void resize (int capacity) {
	T[] copy = (T[]) new Object[capacity];
	for (int i = 0; i<N; i++) copy[i] = s[i];
	s = copy;}

    protected Object clone () {
	RandomizedQueue<Object> c = new RandomizedQueue<>(s);
	return c;}

    public RandomizedQueue () {
	this(1);}

    public void enqueue (T item) {
	push(item);}

    public T dequeue () {
	if (N==0) throw new NoSuchElementException();
	int i = StdRandom.uniform(N);
	T item = s[i];
	T replacement = pop();
	s[i] = replacement;
	return item;}

    public T sample () {
	if (N==0) throw new NoSuchElementException();
	return s[StdRandom.uniform(N)];}

    public int size () {
	return N;}

    public Iterator<T> iterator () {
	if (!cloned) return ((RandomizedQueue)clone()).iterator();
	return new Iterator<T> () {
	    private int i = N;
	    public boolean hasNext () {
		return i>0;}
	    public void remove () {
		throw new UnsupportedOperationException();}
	    public T next () {
		if (hasNext()) return s[--i];
		throw new NoSuchElementException();}};}}
