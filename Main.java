public class Main {
    public static void main(String[] args) {
        RBTree tree = new RBTree();
        tree.insert(3);
        tree.insert(5);
        tree.insert(6);
        tree.insert(1);
        tree.inOrder(tree.getRoot());
    }
}
