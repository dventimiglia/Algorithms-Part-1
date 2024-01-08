import edu.princeton.cs.algs4.*;
import java.awt.*;

public class PercolationVisualizer {
    protected void init () {
	StdDraw.enableDoubleBuffering();}

    // protected void draw (int delay) {
    //     StdDraw.clear();
    //     StdDraw.setPenColor(StdDraw.BLACK);
    //     StdDraw.setXscale(-0.05*n, 1.05*n);
    //     StdDraw.setYscale(-0.05*n, 1.05*n);   // leave a border to write text
    //     StdDraw.filledSquare(n/2.0, n/2.0, n/2.0);

    //     // draw n-by-n grid
    //     int opened = 0;
    //     for (int row = 1; row <= n; row++) {
    //         for (int col = 1; col <= n; col++) {
    //             if (isFull(row, col)) {
    //                 StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
    //                 opened++;
    //             }
    //             else if (isOpen(row, col)) {
    //                 StdDraw.setPenColor(StdDraw.WHITE);
    //                 opened++;
    //             }
    //             else
    //                 StdDraw.setPenColor(StdDraw.BLACK);
    //             StdDraw.filledSquare(col - 0.5, n - row + 0.5, 0.45);
    //         }
    //     }

    //     // write status text
    //     StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
    //     StdDraw.setPenColor(StdDraw.BLACK);
    //     StdDraw.text(0.25*n, -0.025*n, numberOfOpenSites() + " open sites");
    //     if (percolates()) StdDraw.text(0.75*n, -0.025*n, "percolates");
    //     else StdDraw.text(0.75*n, -0.025*n, "does not percolate");
    // 	StdDraw.show();
    //     StdDraw.pause(delay);}
}
