package lab3;


/**
 * @author Jonathan and Amar
 * @version 0.5
 */
public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet {

    private class Node {

        private Comparable elt;

        private Node leftChild, rightChild, parent;

        Node() {
            this(null);
        }

        Node(Comparable elt) {
            this.elt = elt;
            leftChild = rightChild = parent = null;
        }

        public Node getParent() {
            return parent;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

    }

    private int size;
    private Node root;

    public SplayTreeSet() {
        this.size = 0;
        root = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(Comparable x) {
        if(size == 0) {
            // Tree is empty. Add root
            root = new Node(x);
            size++;
            return true;
        }

        Node currentNode = root;

        while (currentNode != null) {
            if(currentNode.elt.compareTo(x) < 0) {

                // We are looking to insert the new to the right of the current node.
                // If the right child is null we can insert the new node there.
                // Otherwise we'll just update the current node to be the right child
                if(currentNode.rightChild == null) {
                    Node node = new Node(x);
                    currentNode.rightChild = node;
                    node.parent = currentNode;
                    splay(node);
                    size++;
                    return true;
                }
                currentNode = currentNode.rightChild;
            } else if(currentNode.elt.compareTo(x) > 0) {
                // We are looking to insert the new node to the left of the current node.
                // If the left child is null we can insert the new node there.
                // Otherwise we'll just update the current node to be the left child.
                if(currentNode.leftChild == null) {
                    Node node = new Node(x);
                    currentNode.leftChild = node;
                    node.parent = currentNode;
                    splay(node);
                    size++;
                    return true;
                }
                currentNode = currentNode.leftChild;
            } else {
                return false;
            }

        }

        return false;
    }

    @Override
    public boolean remove(Comparable x) {
        if(size == 0)
            return false;

        Node nodeToRemove = searchNode(x);
        if(nodeToRemove == null) {
            return false;
        }
        splay(nodeToRemove);

        if(size() == 1){
            root = null;
            size--;
            return true;
        } else if(nodeToRemove.leftChild == null && nodeToRemove.rightChild != null) {
            // We can easily just set the right child of the nodeToRemove to root.
            root = nodeToRemove.rightChild;
            root.parent = null;
            size--;
            return true;
        } else if(nodeToRemove.rightChild == null) {
            // We can easily just set the left child of the nodeToRemove to root.
            root = nodeToRemove.leftChild;
            root.parent = null;
            size--;
            return true;
        } else {
            // Find the largest node were the right child is null in the left child subtree of nodeToRemove.
            // place the right child subtree of nodeToRemove as the right child of the node you found.

            Node currentNode = nodeToRemove.leftChild;
            while (currentNode.rightChild != null) {
                currentNode = currentNode.rightChild;
            }
            // currentNode will be the new root.
            Node leftSubRoot = nodeToRemove.leftChild;
            Node rightSubRoot = nodeToRemove.rightChild;

            root = currentNode;
            root.parent.rightChild = null;
            root.parent = null;
            root.rightChild = rightSubRoot;
            rightSubRoot.parent = root;

            // Find the smallest value in the new tree. (Search for the left-most node with a null left child).
            // Place the leftSubTree there.
            while(currentNode.leftChild != null) {
                currentNode = currentNode.leftChild;
            }
            // currentNode is now the smallest node in the tree!
            if(leftSubRoot != root) {
                currentNode.leftChild = leftSubRoot;
                leftSubRoot.parent = currentNode;
            }
            size--;
            return true;
        }
    }

    @Override
    public boolean contains(Comparable x) {
        if(size() == 0) {
            // The tree is empty, it cannot contain x
            return false;
        }

        Node currentNode = root;
        while(currentNode.elt.compareTo(x) != 0) {
            if(currentNode.elt.compareTo(x) < 0) {
                // The object we are looking for is bigger than the current node. We should look at
                // the right child of the current node.
                if (currentNode.rightChild == null) {
                    return false;
                }
                currentNode = currentNode.rightChild;
            } else if(currentNode.elt.compareTo(x) > 0) {
                // The object we are looking for is smaller than the current node. We should look at
                // the left child of the current node.
                if (currentNode.leftChild == null) {
                    return false;
                }
                currentNode = currentNode.leftChild;
            }
        }
        splay(currentNode);
        return true;
    }

    private Node searchNode(Comparable x) {
        if(size() == 0) {
            return null;
        }

        Node currentNode = root;
        while(currentNode.elt.compareTo(x) != 0 )  {
            if(currentNode.elt.compareTo(x) < 0) {
                if (currentNode.rightChild == null)
                    return null;
                currentNode = currentNode.rightChild;

            } else if (currentNode.elt.compareTo(x) > 0) {
                if (currentNode.leftChild == null)
                    return null;
                currentNode = currentNode.leftChild;
            }
        }
        return currentNode;
    }

    private void leftRotation(Node node) {
        // node is the parent of the node to be put in it's place.
        Node b = node.rightChild;
        node.rightChild = b.leftChild;
        if(b.leftChild != null)
            b.leftChild.parent = node;
        b.leftChild = node;

        b.parent = node.parent;
        if(node != root) {
            if(node.parent.leftChild == node)
                b.parent.leftChild = b;
            else
                b.parent.rightChild = b;
        } else
            root = b;
        node.parent = b;
    }

    private void rightRotation(Node node) {
        Node a = node.leftChild;
        node.leftChild = a.rightChild;
        if(a.rightChild != null)
            a.rightChild.parent = node;
        a.rightChild = node;

        a.parent = node.parent;
        if(node != root) {
            if(node.parent.rightChild == node)
                a.parent.rightChild = a;
            else
                a.parent.leftChild = a;
        } else
            root = a;
        node.parent = a;
    }

    private void splay(Node node) {
        // node is the node that we should have as root.
        while (node != root) {
            if (node.parent == root) {
                if (root.rightChild == node)
                    leftRotation(node.parent);
                else
                    rightRotation(node.parent);
            } else if (node.parent.parent.leftChild == node.parent
                    && node.parent.leftChild == node) {
                // If right zig-zig should be done
                rightRotation(node.parent.parent);
                rightRotation(node.parent);
            } else if (node.parent.parent.rightChild == node.parent
                    && node.parent.rightChild == node) {
                // If left zig-zig should be done
                leftRotation(node.parent.parent);
                leftRotation(node.parent);
            } else if (node.parent.parent.rightChild == node.parent
                    && node.parent.leftChild == node) {
                // if right zig-zag should be done
                rightRotation(node.parent);
                leftRotation(node.parent);
            } else {
                // if left zig-zag should be done
                leftRotation(node.parent);
                rightRotation(node.parent);
            }
        }
    }

}
