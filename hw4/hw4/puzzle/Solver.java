package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;


public class Solver {

    private class searchNode implements Comparable<searchNode>{
        private WorldState ws;
        private int numMoves;
        private searchNode prev;
        private int estimatedDistanceToGoal;
        private searchNode(WorldState ws, int numMoves, searchNode prev) {
            this.ws = ws;
            this.numMoves = numMoves;
            this.prev = prev;
            this.estimatedDistanceToGoal = -1;
        }

        private int getEstimatedDistanceToGoal() {
            if (estimatedDistanceToGoal == -1) {
                estimatedDistanceToGoal = this.ws.estimatedDistanceToGoal();
            }
            return estimatedDistanceToGoal;
        }

        public int compareTo(searchNode sn) {
            return this.getEstimatedDistanceToGoal() + this.numMoves
                    - sn.getEstimatedDistanceToGoal() - sn.numMoves;
        }


    }

    private MinPQ<searchNode> pq = new MinPQ<>();
    private Stack<WorldState> moveSequence = new Stack<>();
    private int moves;
    private searchNode currentNode;
    public Solver(WorldState initial) {
        searchNode initialNode = new searchNode(initial, 0, null);
        pq.insert(initialNode);
        while (!pq.isEmpty()) {
            currentNode = pq.delMin();
            if (currentNode.ws.isGoal()) {
                break;
            }
            for (WorldState ws: currentNode.ws.neighbors()) {
                if (currentNode.prev == null || !ws.equals(currentNode.prev.ws)) {
                    pq.insert(new searchNode(ws, currentNode.numMoves + 1, currentNode));
                }
            }
        }
        while (currentNode != null) {
            moves++;
            moveSequence.push(currentNode.ws);
            currentNode = currentNode.prev;
        }
        moves--;

    }




    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        return moveSequence;
    }
}
