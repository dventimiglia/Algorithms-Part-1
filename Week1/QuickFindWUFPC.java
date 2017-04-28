public class QuickFindWUFPC extends QuickFindWUF {
    private int root (int i) {
	while (i!=id[i]) {
	    id[i] = id[id[i]];
	    i = id[i];}
	return i;}}
