import java.util.*;

public class RandomizedQueue<T> implements Iterable<T> {
    T[] s;
    int N = 0;

    public RandomizedQueue () {
	this(1);}

    public RandomizedQueue (int capacity) {
	s = (T[]) new Object[capacity];}

    public void push (T item) {
	if (N==s.length) resize(2*s.length);
	s[N++] = item;}

    public T pop () {
	T item = s[--N];
	s[N] = null;
	if (N>0 && N==s.length/4) resize(s.length/2);
	return item;}

    private void resize (int capacity) {
	T[] copy = (T[]) new Object[capacity];
	for (int i = 0; i<N; i++) copy[i] = s[i];
	s = copy;}

    public Iterator<T> iterator () {
	return new Iterator<T> () {
	    private int i = N;
	    public boolean hasNext () {
		return i>0;}
	    public void remove () {
		throw new UnsupportedOperationException();}
	    public T next () {
		if (hasNext()) return s[--i];
		throw new NoSuchElementException();}};}}
