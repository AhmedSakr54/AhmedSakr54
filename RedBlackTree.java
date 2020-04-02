public class RedBlackTree {
    private Node root;
    private Node nill;
    private int size;
    class Node {
        Comparable data;
        Node parent;
        Node left;
        Node right;
        boolean isRed;
    }

    public RedBlackTree() {
        nill = new Node();
        nill.isRed = false;
        nill.left = null;
        nill.right = null;
        root = nill;
    }

    private Node searchTreeHelper(Node node, Comparable key) {
        if (node == nill || key == node.data) {
            return node;
        }

        if (node.data.compareTo(key) > 0) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    private void correctTree(Node node) {
        Node uncle;
        while (node.parent.isRed) {
            if (node.parent == node.parent.parent.right) {
                uncle = node.parent.parent.left;
                if (uncle.isRed) {
                    uncle.isRed = false;
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    leftRotate(node.parent.parent);
                }
            } else {
                uncle = node.parent.parent.right;
                if (uncle.isRed) {
                    uncle.isRed = false;
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rightRotate(node.parent.parent);
                }
            }
            if (node == root) {
                break;
            }
        }
        root.isRed = false;
    }

    public Node searchTree(int k) {
        return searchTreeHelper(this.root, k);
    }

    public void leftRotate(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        if (temp.left != nill) {
            temp.left.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            this.root = temp;
        } else if (node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.left = node;
        node.parent = temp;
    }

    public void rightRotate(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        if (temp.right != nill) {
            temp.right.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            this.root = temp;
        } else if (node == node.parent.right) {
            node.parent.right = temp;
        } else {
            node.parent.left = temp;
        }
        temp.right = node;
        node.parent = temp;
    }
    private void insertIntoTree(Comparable key) {
        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = nill;
        node.right = nill;
        node.isRed = true;

        Node parent = null;
        Node newNode = this.root;

        while (newNode != nill) {
            parent = newNode;
            if (node.data.compareTo(newNode.data) < 0) {
                newNode = newNode.left;
            } else {
                newNode = newNode.right;
            }
        }

        node.parent = parent;
        if (parent == null) {
            this.root = node;
        } else if (node.data.compareTo(parent.data) < 0) {
            parent.left = node;
        } else {
            parent.right = node;
        }

        if (node.parent == null) {
            node.isRed = false;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        correctTree(node);
    }

    public void insert(Comparable key) {
        insertIntoTree(key);
        this.size++;
    }

    public Node getRoot() {
        return this.root;
    }

    public int getSize() {
        return this.size;
    }
    public boolean search(Node node, Comparable data) {
        if (node == nill)
            return false;
        if (node.data.compareTo(data) > 0)
            return search(node.left, data);
        if (node.data.compareTo(data) < 0)
            return search(node.right, data);
        return true;
    }
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
            return leftheight;
        else
            return rightheight;

    }
}
