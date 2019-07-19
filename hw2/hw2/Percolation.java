package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;

public class Percolation {
    // things to keep in mind:
    // 1. break a problem into pieces
    // 2. add comments
    // 3. reuse code and make your code more obvious

    // for the backwash problem, since when the grid is percolated, then the
    // top is connected to the bottom, and then when we open just a site besides
    // bottom (thus connect it with bottom), it will be connected to the top automatically
    // and this is called backwash

    // the idea is that we create a new `uf` that does not contain the bottom,
    // and we determine whether a site is connected to the top based on this new uf
    // if some neighbor of a site X is connected to the top in the new `uf`, then we
    // are sure that when we open X and connected it to that open neighbor, then
    // X is connected to the top
    // and when determine whether X is full, we should check if X is connected with the Top
    // so we check if ufWithoutBottom.connected(X, Top);
    // but when checking if the system is percolate, we check if uf.connected(top, bottom);

    // so when I call the function `open`
    // I need to first, check if the input is within the bound, if true,
    // check if the site is already opened, if false, set the site to be opened
    // then connected this site with its open neighbors by connectToOpenNeighbors()

    // how to design the connectToOpenNeighbors()?
    // first make sure that we use both uf and ufWithoutBottom,
    // for x in OpenNeighbors(), let site connect to x in uf,
    // then check if x is connected with the top in ufWithoutBottom, if so,
    // connected the site with the top in ufWithoutBottom
    private int N;
    private boolean[] sites;
    private int openSites, top, bottom;
    private WeightedQuickUnionUF uf, ufWithoutBottom;

    public Percolation(int N) {
        // create a N-by-N grid, with all sites initially block
        if (N < 0) { throw new IllegalArgumentException("N should be positive!"); }
        this.N = N;
        sites = new boolean[N * N];
        top = N * N;
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufWithoutBottom = new WeightedQuickUnionUF(N * N + 1);
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    private void validateIndex(int row, int col) {
        if (row >= N  || row < 0 || col >= N  || col < 0) {
            throw new IndexOutOfBoundsException("invalid row or col index");
        }
    }

    private Iterable<Integer> openNeighbors(int row, int col) {
        List<Integer> on = new ArrayList<>();
        if (row == 0) {on.add(top);}
        if (row == N - 1) {on.add(bottom);}
        if (row + 1 < N && isOpen(row + 1, col)) {
            on.add(xyTo1D(row + 1, col));
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            on.add(xyTo1D(row - 1, col));
        }
        if (col + 1 < N && isOpen(row, col + 1)) {
            on.add(xyTo1D(row, col + 1));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            on.add(xyTo1D(row, col - 1));
        }
        return on;
    }

    public void open(int row, int col) {
        validateIndex(row, col);
        if (isOpen(row, col)) { return; }
        int target = xyTo1D(row, col);
        sites[target] = true;
        openSites++;
        for (Integer x: openNeighbors(row, col)) {
            uf.union(target, x);
            if (x != N * N + 1) {
                ufWithoutBottom.union(target, x);
                if (ufWithoutBottom.connected(x, top)) {
                    ufWithoutBottom.union(target, top);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validateIndex(row, col);
        return sites[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        validateIndex(row, col);
        // is the site (row, col) full?
        return ufWithoutBottom.connected(xyTo1D(row, col), top);
    }

    public int numberOfOpenSites() {
        // number of open sites
        return openSites;
    }

    public boolean percolates() {
        // does the system percolates
        return uf.connected(top, bottom);
    }

    public static void main(String[] args) {
        Percolation perc = new Percolation(10);
        perc.open(0,0);
        System.out.println(perc.openSites);
    }
}
