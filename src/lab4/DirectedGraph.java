package lab4;

import java.awt.font.ImageGraphicAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

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
        for(int i=0; i<noOfNodes; i++) {
            edgeList.add(new ArrayList<E>());
        }
    }

    public void addEdge(E e) {
        ArrayList<E> edgesFromNode = edgeList.get(e.getSource());
        edgesFromNode.add(e);
    }

    public Iterator<E> shortestPath(int from, int to) {
        PriorityQueue<DijkstraObject> priorityQueue = new PriorityQueue<>(1, new CompDijkstraPath<DijkstraObject>());
        priorityQueue.add(new DijkstraObject(from, new ArrayList<E>(), 0));

        // edgeList has the same size as there are nodes in the graph
        boolean[] isVisited = new boolean[edgeList.size()];

        for(int i=0; i<isVisited.length; i++) {
            isVisited[i] = false;
        }

        while(true) {
            DijkstraObject queueHead = priorityQueue.poll();
            while(queueHead != null && isVisited[queueHead.getPathEndNode()]) {
                queueHead = priorityQueue.poll();
            }


            // Every node in the graph is not connected
            if(queueHead == null)
                return null;

            // We have reached the specified end node
            if (queueHead.getPathEndNode() == to)
                return queueHead.getPath().iterator();

            isVisited[queueHead.getPathEndNode()] = true;

            ArrayList<E> edgesFromNode = edgeList.get(queueHead.getPathEndNode());

            // Adds every edge that goes from the current node to the PriorityQueue
            for(int i=0; i<edgesFromNode.size(); i++) {
                E edge = edgesFromNode.get(i);
                ArrayList<E> path = (ArrayList<E>) queueHead.getPath().clone();
                path.add(edge);
                DijkstraObject obj = new DijkstraObject(edge.getDest(), path, queueHead.getWeight() + edge.getWeight());
                priorityQueue.add(obj);

            }



        }

    }

    public Iterator<E> minimumSpanningTree() {
        // TODO använd Kruskal's algorithm för detta
        return null;
    }

    /**
     * A class for a object in the DijkstraObject used in the PriorityQueue there.
     */
    public class DijkstraObject {

        private int pathEndNode;
        private ArrayList<E> path;
        private double weight;

        public DijkstraObject(int pathEndNode, ArrayList<E> path, double weight) {
            this.pathEndNode = pathEndNode;
            this.path = path;
            this.weight = weight;
        }

        public int getPathEndNode() {
            return pathEndNode;
        }

        public ArrayList<E> getPath() {
            return path;
        }

        public double getWeight() {
            return weight;
        }

    }

}
