package lab3;

import com.sun.tools.corba.se.idl.constExpr.Not;

/**
 * @author Jonathan and Amar
 * @version 0.5
 */
public class SplayTreeSet<E extends Comparable<? super E>> implements SimpleSet {

    private class Node {
        /**
         * The contents of the node is public
         */
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

        public Comparable getElt() {
            return elt;
        }

        public void setElt(Comparable c) {
            elt = c;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void setLeftChild(Node child) {
            this.leftChild = child;
        }

        public void setRightChild(Node child) {
            this.rightChild = child;
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

        if(!contains(x)) {
            Node currentNode = root;

            while (currentNode != null) {
                if(currentNode.elt.compareTo(x) < 0) {
                    // We are looking to insert the new to the right of the current node.
                    // If the right child is null we can insert the new node there.
                    // Otherwise we'll just update the current node to be the right child
                    if(currentNode.getRightChild() == null) {
                        Node node = new Node(x);
                        currentNode.setRightChild(node);
                        node.setParent(currentNode);
                        splay(node);
                        size++;
                        return true;
                    }
                    currentNode = currentNode.getRightChild();
                } else if(currentNode.elt.compareTo(x) > 0) {
                    // We are looking to insert the new node to the left of the current node.
                    // If the left child is null we can insert the new node there.
                    // Otherwise we'll just update the current node to be the left child.
                    if(currentNode.getLeftChild() == null) {
                        Node node = new Node(x);
                        currentNode.setLeftChild(node);
                        node.setParent(currentNode);
                        splay(node);
                        size++;
                        return true;
                    }
                    currentNode = currentNode.getLeftChild();
                }

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


        if(nodeToRemove.getLeftChild() == null) {
            // We can easily just set the right child of the nodeToRemove to root.
            root = nodeToRemove.getRightChild();
            root.setParent(null);
            size--;
            return true;
        } else if(nodeToRemove.getRightChild() == null) {
            // We can easily just set the left child of the nodeToRemove to root.
            root = nodeToRemove.getLeftChild();
            root.setParent(null);
            size--;
            return true;
        } else {
            // Find the largest node were the right child is null in the left child subtree of nodeToRemove.
            // place the right child subtree of nodeToRemove as the right child of the node you found.

            Node currentNode = nodeToRemove.getLeftChild();
            while (currentNode.getRightChild() != null) {
                System.out.println("remove first loop");
                currentNode = currentNode.getRightChild();
            }
            // currentNode will be the new root.
            Node leftSubRoot = nodeToRemove.getLeftChild();
            Node rightSubRoot = nodeToRemove.getRightChild();

            root = currentNode;
            currentNode.getParent().setRightChild(null);
            currentNode.setParent(null);
            root.setRightChild(rightSubRoot);
            rightSubRoot.setParent(root);

            // Find the smallest value in the new tree. (Search for the left-most node with a null left child).
            // Place the leftSubTree there.
            System.out.println("remove 2nd loop");
            while(currentNode.getLeftChild() != null) {
                currentNode = currentNode.getLeftChild();
            }
            // currentNode is now the smallest node in the tree!
            currentNode.setLeftChild(leftSubRoot);
            leftSubRoot.setParent(currentNode);
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
        System.out.println("CONTAINS LOOP");
        while(currentNode.elt.compareTo(x) != 0) {
            if(currentNode == null) {
                System.out.println("NULL");
            }
            if(currentNode.elt.compareTo(x) < 0) {
                // The object we are looking for is bigger than the current node. We should look at
                // the right child of the current node.
                if (currentNode.getRightChild() == null) {
                    return false;
                }
                currentNode = currentNode.getRightChild();
            } else if(currentNode.elt.compareTo(x) > 0) {
                // The object we are looking for is smaller than the current node. We should look at
                // the left child of the current node.
                if (currentNode.getLeftChild() == null) {
                    return false;
                }
                currentNode = currentNode.getLeftChild();
            }
        }
        return true;
    }

    private Node searchNode(Comparable x) {
        if(size() == 0) {
            return null;
        }

        Node currentNode = root;
        System.out.println("SEARCH NODE");
        while(currentNode.elt.compareTo(x) != 0 )  {
            if(currentNode.elt.compareTo(x) < 0) {
                if (currentNode.getRightChild() == null)
                    return null;
                currentNode = currentNode.getRightChild();

            } else if (currentNode.elt.compareTo(x) > 0) {
                if (currentNode.getLeftChild() == null)
                    return null;
                currentNode = currentNode.getLeftChild();
            }
        }
        return currentNode;
    }

    private void leftRotation(Node node) {
        // node is the parent of the node to be put in it's place.
        Node b = node.getRightChild();
        node.setRightChild(b.getLeftChild());
        if(b.getLeftChild() != null)
            b.getLeftChild().setParent(node);
        b.setLeftChild(node);

        b.setParent(node.getParent());
        if(node != root) {
            if(node.getParent().getLeftChild() == node)
                b.getParent().setLeftChild(b);
            else
                b.getParent().setRightChild(b);
        } else
            root = b;
        node.setParent(b);
    }

    private void rightRotation(Node node) {
        Node a = node.getLeftChild();
        node.setLeftChild(a.getRightChild());
        if(a.getRightChild() != null)
            a.getRightChild().setParent(node);
        a.setRightChild(node);

        a.setParent(node.getParent());
        if(node != root) {
            if(node.getParent().getRightChild() == node)
                a.getParent().setRightChild(a);
            else
                a.getParent().setLeftChild(a);
        } else
            root = a;
        node.setParent(a);
    }

    private void splay(Node node) {
        // node is the node that we should have as root.
        while (node != root) {
            if (node.getParent() == root) {
                if (root.getRightChild() == node)
                    leftRotation(node.getParent());
                else
                    rightRotation(node.getParent());
            } else if (node.getParent().getParent().getLeftChild() == node.getParent()
                    && node.getParent().getLeftChild() == node) {
                // If right zig-zig should be done
                rightRotation(node.getParent().getParent());
                rightRotation(node.getParent());
            } else if (node.getParent().getParent().getRightChild() == node.getParent()
                    && node.getParent().getRightChild() == node) {
                // If left zig-zig should be done
                leftRotation(node.getParent().getParent());
                leftRotation(node.getParent());
            } else if (node.getParent().getParent().getRightChild() == node.getParent()
                    && node.getParent().getLeftChild() == node) {
                // if right zig-zag should be done
                rightRotation(node.getParent());
                leftRotation(node.getParent());
            } else {
                // if left zig-zag should be done
                leftRotation(node.getParent());
                rightRotation(node.getParent());
            }
        }
    }

}
