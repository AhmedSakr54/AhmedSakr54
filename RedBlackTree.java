public class RedBlackTree {
    private Node root;
    private Node nill;
    private int size;

    class Node {
        String data;
        Node parent;
        Node left;
        Node right;
        boolean isRed;
        public Node(String key) {
            parent = null;
            data = key;
            left = nill;
            right = nill;
            isRed = true;
        }
    }

    public RedBlackTree() {
        nill = new Node(null);
        nill.isRed = false;
        nill.left = null;
        nill.right = null;
        root = nill;
    }

    private void colorChange(Node parent, Node grandFather, Node uncle) {
        if (uncle != null)
            uncle.isRed = false;
        grandFather.isRed = true;
        parent.isRed = false;
    }

    private void correctTree(Node node) {
        Node uncle;
        while (node.parent.isRed) {
            if (node.parent == node.parent.parent.right) {
                uncle = node.parent.parent.left;
                if (uncle.isRed) {
                    colorChange(node.parent, node.parent.parent, uncle);
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    colorChange(node.parent, node.parent.parent, uncle);
                    leftRotate(node.parent.parent);
                }
            } else {
                uncle = node.parent.parent.right;
                if (uncle.isRed) {
                    colorChange(node.parent, node.parent.parent, uncle);
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    colorChange(node.parent, node.parent.parent, uncle);
                    rightRotate(node.parent.parent);
                }
            }
            if (node == root) {
                break;
            }
        }
        this.root.isRed = false;
    }


    private void leftRotate(Node node) {
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

    private void rightRotate(Node node) {
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
    private void insertIntoTree(String key) {
        Node node = new Node(key);

        Node parent = null;
        Node newNode = this.root;

        while (newNode != nill) {
            parent = newNode;
            if (node.data.compareToIgnoreCase(newNode.data) < 0) {
                newNode = newNode.left;
            } else {
                newNode = newNode.right;
            }
        }
        node.parent = parent;
        if (parent == null) {
            this.root = node;
        } else if (node.data.compareToIgnoreCase(parent.data) < 0) {
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

    public void insert(String key) {
        insertIntoTree(key);
        this.size++;
    }

    public Node getRoot() {
        return this.root;
    }

    public int getSize() {
        return this.size;
    }


    public boolean search(Node node, String data) {
        if (node == nill)
            return false;
        if (node.data.compareToIgnoreCase(data) > 0)
            return search(node.left, data);
        if (node.data.compareToIgnoreCase(data) < 0)
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

    public void inOrder(Node node) {
        if (node != null && node != nill) {
            inOrder(node.left);
            System.out.println(" "+node.data + "   ------   " + node.isRed);
            inOrder(node.right);
        }
    }


}
