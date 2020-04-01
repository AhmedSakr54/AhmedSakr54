public class Main {
    public static void main(String[] args) {
        RBTree tree = new RBTree();
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        tree.insert(5);
        tree.insert(9);
        tree.insert(3);
        tree.insert(6);
        tree.insert(7);
//        tree.insert(3);
//        tree.insert(1);
//        tree.insert(2);
        tree.inOrder(tree.getRoot());
        System.out.println(tree.getRoot().data);
//        System.out.println(tree.getRoot().left.data);
//        System.out.println(tree.getRoot().right.data);
//        System.out.println(tree.getRoot().right.left.data);
//        System.out.println(tree.getRoot().right.right.data);
//        System.out.println(tree.getRoot().left.isRed);
//        System.out.println(tree.getRoot().right.isRed);
    }
}
