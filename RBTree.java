public class RBTree implements IRedBlackTree {
    private int size; // size will be incremented with each insertion
    private Node root;

    public class Node {
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
    public RBTree() {
        this.size = 0;
        this.root = null;
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
                System.out.println("help");
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
    public boolean search(Node node, Comparable data) {
        if (node == null)
            return false;
        if (node.data.compareTo(data) > 0)
            return search(node.left, data);
        if (node.data.compareTo(data) < 0)
            return search(node.right, data);
        return true;
    }

    private Node getUncle (Node node) {
        Node grandFather = node.parent.parent;
        if (grandFather.right != node.parent)
            return grandFather.right;
        else
            return grandFather.left;
    }
    private void correctTree(Node node) {
        Node uncle = getUncle(node);
        if (node.parent.parent == null)
            return;
        if (uncle == null || !uncle.isRed)
            rotateTree(node);
        else {
            node.parent.parent.left.isRed = false;
            node.parent.parent.right.isRed = false;
            node.parent.parent.isRed = true;
        }
    }

    private void checkColor(Node node) {
        if (this.root == node) {
            node.isRed = false;
            return;
        }
        else if (node.parent == null)
            return;
        if (node.parent.isRed) {
            correctTree(node);
        }
    }
    private Node insert(Node parent, Comparable data) {
        Node newNode = new Node(data);
        if(parent == null) {
            parent = newNode;
        }
        else if(parent.data.compareTo(data) > 0) {
            parent.left = insert(parent.left, data);
            parent.left.parent = parent;
            checkColor(parent.left);
        }
        else if(parent.data.compareTo(data) < 0) {
            parent.right = insert(parent.right, data);
            parent.right.parent = parent;
            checkColor(parent.right);
        }
        return parent;
    }
    private void insert(Node parent, Node newNode) {
        if (parent.data.compareTo(newNode.data) > 0) {
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent;
            }
            else
                insert(parent.left, newNode);
        }
        else if (parent.data.compareTo(newNode.data) < 0){
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
            }
            else
                insert(parent.right, newNode);
        }
        checkColor(newNode);
    }


    @Override
    public void insert(Comparable data) {
//        if (this.root == null) {
////            this.root = new Node(data);
////        }
////        else {
////            this.root.isRed = false;
////            this.root = insert(this.root, data);
////        }
////        this.size++;
////        this.root.isRed = false;

        Node node = new Node(data);
        if (this.root == null) {
            this.root = node;
            this.root.isRed = false;
        }
        else
            insert(this.root, node);
        this.size++;
        this.root.isRed = false;
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
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(" "+node.data + "   ------   " + node.isRed);
            inOrder(node.right);
        }
    }




}
