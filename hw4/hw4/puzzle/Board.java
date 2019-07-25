package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    private int[][] tiles;
    private int N;
    private int BLANK = 0;
    public Board(int[][] tiles) {
        this.N = tiles[0].length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(tiles[i], 0, this.tiles[i], 0, N);
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i > N - 1 || j < 0 || j > N - 1) {
            throw new IndexOutOfBoundsException("index should be within 0 and " + N + " - 1");
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int hammingEsti = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == BLANK) { continue; }
                if (tileAt(i, j) != i * N + j + 1) { hammingEsti++; }
            }
        }
        return hammingEsti;
    }

    private int[] rightIJ(int x) {
        int[] rightIJ = new int[2];
        rightIJ[1] = (x - 1) % N;
        rightIJ[0] = (x - 1 - rightIJ[1]) / N;
        return rightIJ;
    }
    public int manhattan() {
        int manhattanEsti = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == BLANK) { continue; }
                int[] rightIJ = rightIJ(tileAt(i, j));
                manhattanEsti += Math.abs(i - rightIJ[0]) + Math.abs(j - rightIJ[1]);
            }
        }
        return manhattanEsti;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }

        if (o == null || o.getClass() != this.getClass() || ((Board) o).N != this.N) { return false; }

        Board that = (Board) o;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.tileAt(i, j) != that.tileAt(i, j)) { return false; }
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                hash = hash * 31 + tileAt(i, j);
            }
        }
        return hash;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
