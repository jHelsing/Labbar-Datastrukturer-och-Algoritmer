package lab4;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Jonathan and Amar
 * @version 0.2
 */
public class DirectedGraph<E extends Edge> {

    /**
     * We originate from a collection of nodes. Where each index in the outer ArrayList corresponds to a specific node.
     * The ArrayList that's at a specific index in the outer ArrayList contains all the edges that has the same source
     * node as the ArrayLists index in the outer ArrayList.
     */
    private ArrayList<ArrayList<E>> edgeList;

    public DirectedGraph(int noOfNodes) {
        edgeList = new ArrayList<>(noOfNodes);
    }

    public void addEdge(E e) {
        ArrayList<E> edgesFromNode = edgeList.get(e.getSource());
        edgesFromNode.add(e);
    }

    public Iterator<E> shortestPath(int from, int to) {
        // TODO använd Dijkstra's algorithm för detta
        return null;
    }

    public Iterator<E> minimumSpanningTree() {
        // TODO använd Kruskal's algorithm för detta
        return null;
    }

    /**
     * A class for a object in the DijkstraObject used in the PriorityQueue there.
     */
    public class DijkstraObject {

        private int endNode;
        private ArrayList<E> path;
        private double weight;

        public DijkstraObject(int endNode, ArrayList<E> path, double weight) {
            this.endNode = endNode;
            this.path = path;
            this.weight = weight;
        }

        public int getEndNode() {
            return endNode;
        }

        public ArrayList<E> getPath() {
            return path;
        }

        public double getWeight() {
            return weight;
        }

    }

}
