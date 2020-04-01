public class RBTree implements IRedBlackTree {
    private int size; // size will be incremented with each insertion
    private Node root;

    private class Node {
        Comparable data;
        Node parent;
        Node left;
        Node right;
        boolean isRed;

        Node(Comparable data) {
            this.data = data;
            isRed = true; // assume every newly inserted node is red
            parent = left = right = null;
        }
    }

    public int getSize() {
        return this.size;
    }
    public Node getRoot() {
        return this.root;
    }
    /*
        there are four connections that we have to make sure that they change
        1- change the parent of the node and make it the node's left child
        2- make the left child the parent and make the parent of the node the left child's parent
        3- right child of the left child of the node must be the left child of the node
        4- this right child's parent must change to the node
        5- connect the node and the left child together
     */
    private void rightRotate(Node node) {
        Node temp = node.left;
        node.left = temp.right; // <---- (3)
        if (temp.right != null)
            node.left.parent = node;    // <---- (4)
        if (node.parent == null) {
            // node is a root
            this.root = temp;   // <---- (2)
            // temp is the new root. So, no parent
            temp.parent = null;
        }
        else {
            temp.parent = node.parent;  // <---- (2)
            if (node.parent.right != node) {
                // node is a left child
                node.parent.left = temp;    // <---- (1)
            }
            else {
                // node is a right child
                node.parent.right = temp;   // <---- (1)
            }
        }
        temp.right = node;      //  <---- (5)
        node.parent = temp;    //  <---- (5)
        // the color change that happens after a rotation
        temp.isRed = false;
        node.isRed = true;
    }
    /*
        there are five connections that we have to make sure that they change
        1- change the parent of the node and make it the node's right child
        2- make the right child the parent and make the parent of the node the right child's parent
        3- left child of the right child of the node must be the right child of the node
        4- this left child's parent must change to the node
        5- connect the node and the right child together
     */
    private void leftRotate(Node node) {
        Node temp = node.right;
        node.right = temp.left; // <---- (3)
        if (temp.left != null)
            node.right.parent = node;    // <---- (4)
        if (node.parent == null) {
            // node is a root
            this.root = temp;   // <---- (2)
            // temp is the new root. So, no parent
            temp.parent = null;
        }
        else {
            temp.parent = node.parent;  // <---- (2)
            if (node.parent.right != node) {
                // node is a left child
                node.parent.left = temp;    // <---- (1)
            }
            else {
                // node is a right child
                node.parent.right = temp;   // <---- (1)
            }
        }
        temp.left = node;       //  <---- (5)
        node.parent = temp;    //   <---- (5)
        // the color change that happens after a rotation
        temp.isRed = false;
        node.isRed = true;
    }
    private void left_rightRotate(Node node) {
        leftRotate(node.left);
        rightRotate(node);
    }
    private void right_leftRotate(Node node) {
        rightRotate(node.right);
        leftRotate(node);
    }
    /*
        the parameter is the node that created the problem in the tree i.e. the node that made the tree contain 2 consecutive red nodes
     */
    private void rotateTree(Node node) {
        if (node.parent.right != node) {
            if (node.parent.parent.right != node.parent) {
                // the node is left child and it's parent is a left child
                rightRotate(node.parent.parent);
            } else
                // the node is a left child and it's parent is a right child
                right_leftRotate(node.parent.parent);
        } else {
            if (node.parent.parent.left != node.parent)
                // the node is a right child and it's parent is a right child
                leftRotate(node.parent.parent);
            else
                // the node is a right child and it's parent is a left child
                left_rightRotate(node.parent.parent);
        }
    }

    @Override
    public boolean search(Comparable data) {
        return false;
    }

    @Override
    public void insert(Comparable data) {

    }

    @Override
    public int calculateHeight() {
        int leftheight=0;
        int rightheight=0;
        Node temp = this.root;
        while(temp != null){

            leftheight ++;
            temp = temp.left;

        }
        temp=this.root;
        while(temp != null){

            rightheight ++;
            temp = temp.right;

        }

        if(leftheight >= rightheight)
            return leftheight+1;
        else
            return rightheight+1;

    }




}
