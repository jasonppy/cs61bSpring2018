package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] from;
    private boolean hasCycle;
    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        from = new int[maze.V()];
        hasCycle = false;
        for (int v = 0; v < maze.V(); v++) {
            if (!marked[v]) { dfs(maze, v, v); }
        }
    }


    private void dfs(Maze maze, int v, int prev) {
        marked[v] = true;
        announce();
        for (int w: maze.adj(v)) {
            if (hasCycle) {
                return;
            }
            if (!marked[w]) {
                from[w] = v;
                dfs(maze, w, v);
            }
            else if (w != prev) {
                for (int x = v; x != w; x = from[x]) {
                    edgeTo[x] = from[x];
                    announce();
                edgeTo[w] = v;
                hasCycle = true;
                announce();
                }
            }
        }
    }

    // Helper methods go here
}

