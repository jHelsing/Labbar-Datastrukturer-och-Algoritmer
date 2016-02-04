
/** Doubly-linked list with user access to nodes
 */
public class DLList<E> {
  public class Node implements Comparable {
    /** The contents of the node is public */
    public E elt;

    protected Node prev, next;

    Node() {
      this(null);
    }
    Node(E elt) {
      this.elt = elt;
      prev = next = null;
    }

    public Node getNext() {
      return next;
    }

    public Node getPrev() {
      return prev;
    }

    @Override
    public int compareTo(Object o) {
      Node n = (Node) o;
      //if(this.elt.equals(n.elt))
      //  return 0;
      // Compare the different points to calculate the value.
      // First we calculate the value for this object.
      Double x1 = (Double) this.prev.prev.elt;
      Double y1 = (Double) this.prev.elt;
      Double x = (Double) this.elt;
      Double y = (Double) this.next.elt;
      Double x2 = (Double) this.next.next.elt;
      Double y2 = (Double) this.next.next.next.elt;

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
  
  /** first and last nodes in list, null when list is empty */
  Node first, last;
  
  DLList() {
    first = last = null;
  }
  
  /** inserts an element at the beginning of the list
   * @param e   the new element value
   * @return    the node holding the added element
   */
  public Node addFirst(E e) {
    Node newNode = new Node(e);
    if(first != null) {
      first.prev = newNode;
      newNode.next = first;
      newNode.prev = last;
      first = newNode;
      last.next = first;
    } else {
      first = newNode;
      last = newNode;
    }
    return first;
  }

  /** inserts an element at then end of the list
   * @param e   the new element
   * @return    the node holding the added element
   */
  public Node addLast(E e) {
    if(first == null) {
      return addFirst(e);
    }
    Node oldLast = last;
    last = new Node(e);
    oldLast.next = last;
    last.prev = oldLast;
    last.next = first;
    first.prev = last;
    return last;
  }
  
  /**
   * @return    the node of the list's first element, null if list is empty
   */
  public Node getFirst() {
    return first;
  }
  
  /**
   * @return    the node of the list's last element, null if list is empty
   */
  public Node getLast() {
    return last;
  }
  
  /** Inserts a new element after a specified node
    * @param e   the new element
    * @param l   the node after which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  public Node insertAfter(E e, Node l) {
    Node n = new Node(e);
    Node oldNext = l.next;
    l.next = n;
    n.next = oldNext;
    n.prev = l;
    oldNext.prev = n;
    return n;
  }

  /** inserts a new element before a specified node
    * @param e   the new element
    * @param l   the node before which to insert the element, must be non-null and a node belonging to this list
    * @return    the node holding the inserted element
    */
  public Node insertBefore(E e, Node l) {
    Node node = new Node(e);
    Node oldPrev = l.prev;
    l.prev = node;
    node.next = l;
    node.prev = oldPrev;
    oldPrev.next = node;
    return node;
  }

  /** removes an element
    * @param l   then node containing the element that will be removed, must be non-null and a node belonging to this list
    */
  public void remove(Node l) {
    if(l == first) {
      if(first == last)
        first = null;
      else {
        first = first.getNext();
      }
    }
    if(l.getNext() != null)
      l.getNext().prev = l.getPrev();
    if(l.getPrev() != null)
      l.getPrev().next = l.getNext();
  }
}
