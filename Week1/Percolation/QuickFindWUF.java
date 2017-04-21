public class QuickUnionWUF extends QuickFindUF {
    protected int[] sz;

    public void union (int p, int q) {
	if (i==j) return;
	if (sz[i] == sz[j]) {
	    id[i] = j;
	    sz[j] += sz[i];}
	else {
	    id[j] = i;
	    sz[i] = sz[j];}}
