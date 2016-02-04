import java.util.ArrayList;
import java.util.PriorityQueue;

public class Lab2b {
  public static double[] simplifyShape(double[] poly, int k) {
      DLList<Double> polyList = new DLList<Double>();
      //Add every element in poly to the doubly linked list
      for(int i=poly.length-1; i>=0; i--) {
          polyList.addFirst((Double) poly[i]);
      }
      // Create priority queue and add all X-coordinates to it. It will be sorted in the order of having the node with
      // the lowest value at the head.
      PriorityQueue<DLList.Node> valueList = new PriorityQueue<>((poly.length/2)-4);
      DLList.Node currentNode = polyList.getFirst().getNext().getNext();
      for(int i=2; i<poly.length-3; i+=2) {
          // Adds every x-point except first and last
          valueList.add(currentNode);
          currentNode = currentNode.getNext().getNext();
      }

      // Trim the polyList until it has the requested nbr of points
      int size = poly.length/2;
      while(size > k) {
          // Get the node with the lowest value and remove it (the X value) and it's Y-value.
          // Gets the next and prev nodes first.
          DLList.Node nodeToRemove = valueList.poll();
          DLList.Node prevXNode = nodeToRemove.getPrev().getPrev();
          DLList.Node nextXNode = nodeToRemove.getNext().getNext();
          polyList.remove(nodeToRemove.getNext());
          polyList.remove(nodeToRemove);

          // Adds the connecting nodes to the priority list to have their value recalculated
          // Does not add the new values if they are the start or the last point.
          if(prevXNode != polyList.first) {
              ArrayList<DLList.Node> nodeToUpdate = new ArrayList<>(1);
              nodeToUpdate.add(prevXNode);
              valueList.remove(nodeToUpdate);
              valueList.add(prevXNode);
          }
          if(nextXNode != polyList.last.prev) {
              ArrayList<DLList.Node> nodeToUpdate = new ArrayList<>(1);
              nodeToUpdate.add(nextXNode);
              valueList.remove(nodeToUpdate);
              valueList.add(nextXNode);
          }
          size--;
      }
      // Converts our doubly linked list to an array and returns it as a result
      double[] result = new double[k*2];
      for(int i=0; i<result.length; i++) {
          DLList.Node n = polyList.getFirst();
          result[i] = (double) n.elt;
          polyList.remove(n);
      }
      return result;

  }

}
