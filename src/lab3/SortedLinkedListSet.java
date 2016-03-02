package lab3;

/**
 * Uses a doubly linked list.
 *
 * @author Jonathan and Amar.
 * @version 1.0
 */
public class SortedLinkedListSet<E extends Comparable<? super E>> implements SimpleSet<E> {

    private class Node {
        /**
         * The contents of the node is public
         */
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

    private int size;
    private Node first,last;

    public SortedLinkedListSet() {
        first = new Node(null);
        last = new Node(null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E x) {
        if(this.size() == 0) {
            Node node = new Node(x);
            first = node;
            last = node;
            size++;
            return true;
        }
        if(!contains(x)) {
            if(first.elt.compareTo(x) == 1) {
                // Add first
                Node newNode = new Node(x);
                newNode.next = first;
                newNode.prev = last;
                first.prev = newNode;
                last.next = newNode;
                first = newNode;
                size++;
                return true;
            } else if (last.elt.compareTo(x) == -1) {
                // Add last
                Node newNode = new Node(x);
                newNode.next = first;
                newNode.prev = last;
                last.next = newNode;
                first.prev = newNode;
                last = newNode;
                size++;
                return true;
            } else {
                Node currentNode = first.getNext();
                for(int i=1; i<size; i++) {
                    if(currentNode.elt.compareTo(x) == 1) {
                        // Add new node before currentNode
                        Node newNode = new Node(x);
                        newNode.prev = currentNode.prev;
                        newNode.next = currentNode;
                        currentNode.getPrev().next = newNode;
                        currentNode.prev = newNode;
                        size++;
                        return true;
                    }
                    // Get next node
                    currentNode = currentNode.getNext();
                }
            }

        }
        return false;
    }

    @Override
    public boolean remove(E x) {
        if(this.size() == 0)
            return false;

        if(first.elt.compareTo(x) == 0) {
            // Remove first element
            if(first == last) {
                first = new Node(null);
                last = new Node(null);
                size--;
                return true;
            }
            first = first.getNext();
            first.prev = last;
            last.next = first;
            size--;
            return true;
        }
        if(last.elt.compareTo(x) == 0) {
            // Remove last element
            last = last.getPrev();
            last.next = first;
            first.prev = last;
            size--;
            return true;
        }

        // Search the list to find x
        Node currentNode = first.getNext();
        for(int i=1; i<size-1; i++) {
            if(currentNode.elt.compareTo(x) == 0) {
                // We have found the node to remove
                currentNode.getPrev().next = currentNode.next;
                currentNode.getNext().prev = currentNode.prev;
                size--;
                return true;
            }
            currentNode = currentNode.getNext();
        }

        return false;
    }

    @Override
    public boolean contains(E x) {
        if(this.size() == 0)
            return false;

        if(size() == 1) {
            if(first.elt.compareTo(x) == 0) {
                return true;
            }
            return false;
        }

        int middle = (size/2)-1;
        Node middleNode = first;

        for(int i=0; i<middle; i++) {
            middleNode = middleNode.getNext();
        }

        if(x.compareTo(middleNode.elt) == -1) {
            // x is less than the middle value: we need to search for x to the left of the middle
            Node currentNode = first;
            for(int i=0; i<middle; i++) {
                if(x.compareTo(currentNode.elt) == 0)
                    return true;
                currentNode = currentNode.getNext();
            }
        } else if(x.compareTo(middleNode.elt) == 1) {
            // x is more than the middle value: we need to search for x to the right of the middle
            Node currentNode = middleNode.getNext();
            for(int i=middle+1; i<size; i++) {
                if(x.compareTo(currentNode.elt) == 0)
                    return true;
                currentNode = currentNode.getNext();
            }
        } else {
            // the middle element is the element we are looking for
            return true;
        }
        return false;
    }
}
