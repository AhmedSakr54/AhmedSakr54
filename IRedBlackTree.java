public interface IRedBlackTree {
    /*
        to search for a specific element in the tree and return true if found
        the Comparable class to make the tree accept any data type
        (String, Integer, Double)
        to compare between 2 elements of the comparable class we use
        obj1.compareTo(obj2)
        which will return:
        1 if obj1 is bigger than obj2
        0 if obj1 is equal to obj2
        -1 if obj1 is smaller than obj2
     */
    boolean search(RBTree.Node node,Comparable data);

    /*
        would probably contain a call to a private method
        private void insert(Node parent, Node newNode);
        which would start the insert process:
            - checkColor.
            - correctColor if needed.
            - rotateTree if needed.
     */
    void insert(Comparable data);


    // calculate and return the height of the tree
    int calculateHeight();
}
