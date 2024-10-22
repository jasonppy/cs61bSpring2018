package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] results;
    private double T;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        //perform T independent experiments on an N-by-N grid
        if (N < 0) { throw new IllegalArgumentException("N should be positive!"); }
        results = new double[T];
        this.T = T;
        for (int t = 0; t < T; t++) {
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                perc.open(row, col);
            }
            results[t] = (perc.numberOfOpenSites() * 1.0) / (N * N);
        }

    }
    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(results);
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(results);
    }
    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return mean() - stddev() * 1.96 / Math.sqrt(T);
    }
    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return mean() + stddev() * 1.96 / Math.sqrt(T);
    }

//    public static void main(String[] args) {
//        PercolationFactory pf = new PercolationFactory();
//        PercolationStats stat = new PercolationStats(100, 1000, pf);
//        System.out.println(stat.mean()/10000.0);
//    }


}
