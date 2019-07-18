package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // when a site x = site[i][j] is full it should be
    // a = site[i-1][j], b = site[i+1][j], c = site[i][j-1], d = site[i][j+1]
    // we have a b, b c, c d, d x are connected

    // when a site x = site[i][j] is open, set it from 0 to 1, when a b c d is also
    //
    private int N;
    private int[][] s;
    private int cnt;
    private WeightedQuickUnionUF uf;

    public Percolation(int N) {
        // create a N-by-N grid, with all sites initially block
        if (N < 0) throw new IllegalArgumentException("N should be positive!");
        this.N = N;
        uf = new WeightedQuickUnionUF(N * N + 2);
        s = new int[N][N];
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    public void open(int row, int col) {
        // open the site (row, col) if it is not open already
        if (row >= N - 1 || row < 0 || col >= N - 1 || col < 0) {
            throw new IndexOutOfBoundsException("invalid row or col index")
        }
        if (!isOpen(row, col)) {
            cnt += 1;
            s[row][col] = 1;
            if (row == 0) {
                uf.union(xyTo1D(row, col), uf.find(N * N));
                if (isOpen(row + 1, col)) { uf.union(xyTo1D(row, col), xyTo1D(row, col + 1)); }
            } else if (row == N - 1) {
                uf.union(xyTo1D(row, col), uf.find(N * N + 1));
                if (isOpen(row - 1, col)) { uf.union(xyTo1D(row, col), xyTo1D(row, col - 1)); }
            } else {
                if (isOpen(row + 1, col)) {
                    uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
                }
                if (isOpen(row - 1, col)) {
                    uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                }
                if (col + 1 < N && isOpen(row, col + 1)) {
                    uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
                }
                if (col - 1 > 0 && isOpen(row, col - 1)) {
                    uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row >= N - 1 || row < 0 || col >= N - 1 || col < 0) {
            throw new IndexOutOfBoundsException("invalid row or col index")
        }
        return s[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        if (row >= N - 1 || row < 0 || col >= N - 1 || col < 0) {
            throw new IndexOutOfBoundsException("invalid row or col index")
        }
        // is the site (row, col) full?
        return uf.connected(xyTo1D(row, col), uf.find(N * N));
    }

    public int numberOfOpenSites() {
        // number of open sites
        return cnt;
    }

    public boolean percolates() {
        // does the system percolates
        return uf.connected(uf.find(N * N), uf.find(N * N + 1));
    }

//    public static void main(String[] args) {
//        // use for unit testing (not required)
//        Percolation p = new Percolation(10);
//        System.out.println(p.cnt);
//    }

}
