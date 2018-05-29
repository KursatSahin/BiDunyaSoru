package grup2;

import java.util.ArrayList;
import java.util.Vector;

/** A class to represent a binary search tree.
 *  @author Koffman and Wolfgang
 */

public class BinarySearchTree < E
        extends Comparable < E >>
        extends BinaryTree < E >
        implements SearchTree < E > {
    // Data Fields
    /** Return value from the public add method. */
    protected boolean addReturn;

    /** Return value from the public delete method. */
    protected E deleteReturn;

    public ArrayList<Oyuncu> buyuktenKucugeOyuncuPuanListesi = new ArrayList<Oyuncu>(); // büykten kucuge sıralı oyuncu listesi -Inorder travers
    public ArrayList<E> kucuktenBuyugeOyuncuPuanListesi = new ArrayList<E>();  //kucukten buyuge sıralı oyuncu listesi
    //Methods
    /** Starter method find.
     pre: The target object must implement
     the Comparable interface.
     @param target The Comparable object being sought
     @return The object, if found, otherwise null
     */
    public E find(E target) {
        return find(root, target);
    }

    /** Recursive find method.
     @param localRoot The local subtree�s root
     @param target The object being sought
     @return The object, if found, otherwise null
     */
    private E find(Node < E > localRoot, E target) {
        if (localRoot == null)
            return null;

        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0)
            return localRoot.data;
        else if (compResult < 0)
            return find(localRoot.left, target);
        else
            return find(localRoot.right, target);
    }

    /** Starter method add.
     pre: The object to insert must implement the
     Comparable interface.
     @param item The object being inserted
     @return true if the object is inserted, false
     if the object already exists in the tree
     */
    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }

    @Override
    public boolean contains(E target) {
        return false;
    }

    /** Recursive add method.
     post: The data field addReturn is set true if the item is added to
     the tree, false if the item is already in the tree.
     @param localRoot The local root of the subtree
     @param item The object to be inserted
     @return The new local root that now contains the
     inserted item
     */
    private Node < E > add(Node < E > localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree � insert it.
            addReturn = true;
            return new Node < E > (item);
        }
        else if (item.compareTo(localRoot.data) == 0) {
            // item is equal to localRoot.data
            addReturn = false;
            return localRoot;
        }
        else if (item.compareTo(localRoot.data) < 0) {
            // item is less than localRoot.data
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        }
        else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /** Starter method delete.
     post: The object is not in the tree.
     @param target The object to be deleted
     @return The object deleted from the tree
     or null if the object was not in the tree
     @throws ClassCastException if target does not implement
     Comparable
     */
    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    @Override
    public boolean remove(E target) {
        return false;
    }

    /** Recursive delete method.
     post: The item is not in the tree;
     deleteReturn is equal to the deleted item
     as it was stored in the tree or null
     if the item was not found.
     @param localRoot The root of the current subtree
     @param item The item to be deleted
     @return The modified local root that does not contain
     the item
     */
    private Node < E > delete(Node < E > localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        }
        else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        }
        else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            }
            else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
            }
            else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                }
                else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node�s data with ip.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

/**** EXERCISE ****/

    /** Find the node that is the
     inorder predecessor and replace it
     with its left child (if any).
     post: The inorder predecessor is removed from the tree.
     @param parent The parent of possible inorder
     predecessor (ip)
     @return The data in the ip
     */
    private E findLargestChild(Node < E > parent) {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        }
        else {
            return findLargestChild(parent.right);
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                      //
    //     //Added new methods for leader Table List                                                        //
    //                                                                                                      //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Balanced edilmiş treeyi kucuktenBuyugeOyuncuPuanListesine sort edilmiş şekilde doldurur.

    public void traverseInOrder(Node<E> node)
    {
        if (node == null)
            return ;

        /* first recur on left child */
        traverseInOrder(node.left);

        /* then print the data of node */
        kucuktenBuyugeOyuncuPuanListesi.add(node.data);

        /* now recur on right child */
        traverseInOrder(node.right);
    }

    //kucukten buyuge olan puan listesini buyuktenKucugeOyuncuPuanListesi ne doldurur
    public ArrayList<Oyuncu> buyuktenKucugePuanListesiAl(){
      //  Oyuncu[] sortedOyuncular = new Oyuncu[100];

//        for (int i = kucuktenBuyugeOyuncuPuanListesi.size()-1; 0 <= i ; i--) {
//            buyuktenKucugeOyuncuPuanListesi.add(kucuktenBuyugeOyuncuPuanListesi.get(i));
//        }

       // sortedOyuncular = kucuktenBuyugeOyuncuPuanListesi.toArray();
        Oyuncu[] sortedOyuncular = new Oyuncu[kucuktenBuyugeOyuncuPuanListesi.size()];
        sortedOyuncular = kucuktenBuyugeOyuncuPuanListesi.toArray(sortedOyuncular);
        mergeSort(sortedOyuncular);

        for ( int i=0 ; i< sortedOyuncular.length ; i++ ) {


            buyuktenKucugeOyuncuPuanListesi.add(sortedOyuncular[i]);
        }

        return buyuktenKucugeOyuncuPuanListesi;
    }


    public static < E  extends Comparable < E >> void mergeSort(E[] table) {
        // A table with one element is sorted already.
        if (table.length > 1) {
            // Split table into halves.
            int halfSize = table.length / 2;
            E[] leftTable = (E[])new Comparable[halfSize];
            E[] rightTable =
                    (E[])new Comparable[table.length - halfSize];
            System.arraycopy(table, 0, leftTable, 0, halfSize);
            System.arraycopy(table, halfSize, rightTable, 0,
                    table.length - halfSize);

            // Sort the halves.
            mergeSort(leftTable);
            mergeSort(rightTable);

            // Merge the halves.
            merge(table, leftTable, rightTable);
        }
    }

    /** Merge two sequences.
     pre: leftSequence and rightSequence are sorted.
     post: outputSequence is the merged result and is sorted.
     @param outputSequence The destination
     @param leftSequence The left input
     @param rightSequence The right input
     */
    private static < E extends Comparable < E >> void merge(E[] outputSequence,  E[] leftSequence,  E[] rightSequence) {
        int i = 0; // Index into the left input sequence.
        int j = 0; // Index into the right input sequence.
        int k = 0; // Index into the output sequence.

        // While there is data in both input sequences
        while (i < leftSequence.length && j < rightSequence.length) {
            // Find the smaller and
            // insert it into the output sequence.
            if (leftSequence[i].compareTo(rightSequence[j]) > 0) { // buyukten kucuge sıralı yaptık
                outputSequence[k++] = leftSequence[i++];
            }
            else {
                outputSequence[k++] = rightSequence[j++];
            }
        }
        // assert: one of the sequences has more items to copy.
        // Copy remaining input from left sequence into the output.
        while (i < leftSequence.length) {
            outputSequence[k++] = leftSequence[i++];
        }
        // Copy remaining input from right sequence into output.
        while (j < rightSequence.length) {
            outputSequence[k++] = rightSequence[j++];
        }
    }


    //  added new methods for balanced tree

    /* This function traverse the skewed binary tree and
       stores its nodes pointers in vector nodes[] */
    void storeBSTNodes(Node<E> root, Vector<Node<E>> nodes)
    {
        // Base case
        if (root == null)
            return;

        // Store nodes in Inorder (which is sorted
        // order for BST)
        storeBSTNodes(root.left, nodes);
        nodes.add(root);
        storeBSTNodes(root.right, nodes);
    }

    /* Recursive function to construct binary tree */
    Node buildTreeUtil(Vector<Node<E>> nodes, int start, int end) {
        // base case
        if (start > end)
            return null;

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        Node node = nodes.get(mid);

        /* Using index in Inorder traversal, construct
           left and right subtress */
        node.left = buildTreeUtil(nodes, start, mid - 1);
        node.right = buildTreeUtil(nodes, mid + 1, end);

        return node;
    }

    // This functions converts an unbalanced BST to
    // a balanced BST
    Node buildTree(Node<E> root)
    {
        // Store nodes of given BST in sorted order
        Vector<Node<E>> nodes = new Vector<Node<E>>();
        storeBSTNodes(root, nodes);

        // Constucts BST from nodes[]
        int n = nodes.size();
        return buildTreeUtil(nodes, 0, n - 1);
    }
}
