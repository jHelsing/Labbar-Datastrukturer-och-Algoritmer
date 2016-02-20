
/** Doubly-linked list with user access to nodes
 */
public class DLList<E> {
  public class Node {
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
        first = last = null;
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
