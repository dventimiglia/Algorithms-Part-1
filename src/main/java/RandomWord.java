import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = null;
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String candidate = StdIn.readString();
            if (StdRandom.bernoulli(1./(i+1))) champion = candidate;
        }
        System.out.println(champion);
    }
}
