package priv.mm.algorithms;

/**
 * 平衡二叉树
 *
 * @author maomao
 * @since 2017.02.03
 */
public class BinaryTree {
    Node root;

    public Node find(int key) {
        if (root != null) {
        }
        return null;
    }

    public void insert(int key, String data) {
        Node node = new Node(key, data);


    }

    public void delete(int key) {

    }

    static class Node {
        int key;
        String data;
        Node leftChild;
        Node rightChild;

        Node(int key, String data) {
            this.key = key;
            this.data = data;
        }
    }
}
