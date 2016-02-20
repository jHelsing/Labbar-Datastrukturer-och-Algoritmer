import java.util.Comparator;
import java.util.PriorityQueue;

public class Lab2b implements Comparator<Object> {

    public static double[] simplifyShape(double[] poly, int k) {
          DLList<Double> polyList = new DLList<Double>();
          //Add every element in poly to the doubly linked list
          for(int i=poly.length-1; i>=0; i--) {
              polyList.addFirst((Double) poly[i]);
          }

          // Create priority queue and add all X-coordinates to it. It will be sorted in the order of having the node with
          // the lowest value at the head.
          PriorityQueue<Object> valueList = new PriorityQueue<>((poly.length/2)-2, new Lab2b());
          Object currentNode = (Object) polyList.getFirst().getNext().getNext();
          for(int i=2; i<poly.length-2; i+=2) {
              // Adds every x-point except first and last
              valueList.add(currentNode);
              currentNode = (Object) ((DLList.Node) currentNode).getNext().getNext();
          }

          // Trim the polyList until it has the requested nbr of nodes
          int size = poly.length/2;
          while(size > k) {
              // Get the node with the lowest value and remove it (the X value) and it's Y-value.
              // Gets the next and prev nodes first.
              DLList.Node nodeToRemove = (DLList.Node) valueList.poll(); // The X node to remove
              Object prevXNode = (Object) nodeToRemove.getPrev().getPrev(); // Node to update
              Object nextXNode = (Object) nodeToRemove.getNext().getNext(); // Node to update
              polyList.remove(nodeToRemove.getNext()); // Remove Y
              polyList.remove(nodeToRemove); // Remove X

              // Adds the connecting nodes to the priority list to have their value recalculated
              // Does not add the new values if they are the start or the last point.
              if (((DLList.Node) prevXNode) != polyList.first) {
                  valueList.remove(prevXNode);
                  valueList.add(((DLList.Node) prevXNode));
              }

              if (((DLList.Node) nextXNode) != polyList.last.prev) {
                  valueList.remove(nextXNode);
                  valueList.add(((DLList.Node) nextXNode));
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


    /**
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     * <p/>
     * In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.<p>
     * <p/>
     * The implementor must ensure that <tt>sgn(compare(x, y)) ==
     * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>compare(x, y)</tt> must throw an exception if and only
     * if <tt>compare(y, x)</tt> throws an exception.)<p>
     * <p/>
     * The implementor must also ensure that the relation is transitive:
     * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
     * <tt>compare(x, z)&gt;0</tt>.<p>
     * <p/>
     * Finally, the implementor must ensure that <tt>compare(x, y)==0</tt>
     * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
     * <tt>z</tt>.<p>
     * <p/>
     * It is generally the case, but <i>not</i> strictly required that
     * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * first argument is less than, equal to, or greater than the
     * second.
     * @throws NullPointerException if an argument is null and this
     *                              comparator does not permit null arguments
     * @throws ClassCastException   if the arguments' types prevent them from
     *                              being compared by this comparator.
     */
    @Override
    public int compare(Object o1, Object o2) {
        DLList.Node n = (DLList.Node) o2;
        DLList.Node n1 = (DLList.Node) o1;
        Double x1 = (Double) n1.prev.prev.elt;
        Double y1 = (Double) n1.prev.elt;
        Double x = (Double) n1.elt;
        Double y = (Double) n1.next.elt;
        Double x2 = (Double) n1.next.next.elt;
        Double y2 = (Double) n1.next.next.next.elt;

        Double l1 = Math.sqrt((x-x1)*(x-x1) + (y-y1)*(y-y1));
        Double l2 = Math.sqrt((x2-x)*(x2-x) + (y2-y)*(y2-y));
        Double l3 = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        Double result1 = l1+l2-l3;

        // Calculate the value for the parameter object.
        x1 = (Double) n.prev.prev.elt;
        y1 = (Double) n.prev.elt;
        x = (Double) n.elt;
        y = (Double) n.next.elt;
        x2 = (Double) n.next.next.elt;
        y2 = (Double) n.next.next.next.elt;
        l1 = Math.sqrt((x-x1)*(x-x1) + (y-y1)*(y-y1));
        l2 = Math.sqrt((x2-x)*(x2-x) + (y2-y)*(y2-y));
        l3 = Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
        Double result2 = l1+l2-l3;
        // Compare these 2 value and return the result
        return result1.compareTo(result2);
    }
}
