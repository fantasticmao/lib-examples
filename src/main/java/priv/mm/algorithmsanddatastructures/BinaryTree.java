package priv.mm.algorithmsanddatastructures;

/**
 * 平衡二叉树
 *
 * @author maomao
 * @since 2017.02.03
 */
public class BinaryTree {
    private class Node {
        int key;
        Node leftChild;
        Node rightChild;

        Node(int key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"key\":" + key +
                    ", \"leftChild\":" + leftChild +
                    ", \"rightChild\":" + rightChild +
                    '}';
        }
    }

    enum TraverseType {
        PRE, // 前序遍历
        IN, // 中序遍历
        POST // 后序遍历
    }

    private Node root;

    public Node find(int key) {
        Node currentNode = root;
        while (true) {
            if (currentNode == null) {
                return null;
            }
            if (currentNode.key == key) {
                return currentNode;
            } else if (currentNode.key > key) {
                currentNode = currentNode.leftChild;
            } else {
                currentNode = currentNode.rightChild;
            }
        }
    }

    /**
     * 出现重复关键字时，节点插入到的右子节点处。不过find()只能查询多个相同关键字中的第一个。
     * 可以修改find()，使其查询更多数据区分重复的关键字，不过这样很耗时。
     * 合理的选择：1.业务中禁止重复的关键字;2.修改insert()，排除重复关键字。
     */
    public void insert(int key) {
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
        } else {
            Node currentNode = root;
            while (true) {
                if (currentNode.key > key) {
                    if (currentNode.leftChild == null) {
                        currentNode.leftChild = newNode;
                        break;
                    }
                    currentNode = currentNode.leftChild;
                } else {
                    if (currentNode.rightChild == null) {
                        currentNode.rightChild = newNode;
                        break;
                    }
                    currentNode = currentNode.rightChild;
                }
            }
        }
    }

    public void delete(int key) {

    }

    public void traverse(TraverseType type) {
        switch (type) {
            case PRE:
                preOrder(root);
                break;
            case IN:
                inOrder(root);
                break;
            case POST:
                postOrder(root);
                break;
        }
    }

    /**
     * 前序遍历
     */
    private void preOrder(Node node) {
        if (node != null) {
            System.out.printf(node.key + " ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    /**
     * 中序遍历二叉搜索树，可得到所有节点的升序结果。
     * 1.递归左子节点
     * 2.访问本节点
     * 3.递归右子节点
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.leftChild);
            System.out.printf(node.key + " ");
            inOrder(node.rightChild);
        }
    }

    /**
     * 后序遍历
     */
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.printf(node.key + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(2);
        tree.insert(3);
        tree.insert(1);
        tree.insert(4);
        System.out.println(tree.root);
        tree.traverse(TraverseType.PRE);
        System.out.println();
        tree.traverse(TraverseType.IN);
        System.out.println();
        tree.traverse(TraverseType.POST);
    }
}
