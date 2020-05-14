package cn.fantasticmao.demo.java.algorithm;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树
 *
 * @author maomao
 * @since 2017.02.03
 */
public class BinaryTree {

    private static class Node {
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
     * 出现重复关键字时，节点插入到的右子节点处。不过 find() 只能查询多个相同关键字中的第一个。
     * 可以修改 find()，使其查询更多数据区分重复的关键字，不过这样很耗时。
     * 合理的选择：1. 业务中禁止重复的关键字; 2. 修改 insert()，排除重复关键字。
     */
    public BinaryTree insert(int key) {
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
        return this;
    }

    public void delete(int key) {

    }

    public List<Integer> traverse(TraverseType type) {
        List<Integer> keyList = new LinkedList<>();
        switch (type) {
            case PRE:
                return preOrder(root, keyList);
            case IN:
                return inOrder(root, keyList);
            case POST:
                return postOrder(root, keyList);
            default:
                return Collections.emptyList();
        }
    }

    /**
     * 前序遍历
     * <ol>
     *     <li>访问根节点</li>
     *     <li>访问左子节点</li>
     *     <li>访问左右节点</li>
     * </ol>
     */
    private List<Integer> preOrder(Node node, List<Integer> keyList) {
        if (node != null) {
            keyList.add(node.key);
            preOrder(node.leftChild, keyList);
            preOrder(node.rightChild, keyList);
        }
        return keyList;
    }

    /**
     * 中序遍历（可得到所有节点的升序结果）
     * <ol>
     *     <li>访问左子节点</li>
     *     <li>访问根节点</li>
     *     <li>访问右子节点</li>
     * </ol>>
     */
    private List<Integer> inOrder(Node node, List<Integer> keyList) {
        if (node != null) {
            inOrder(node.leftChild, keyList);
            keyList.add(node.key);
            inOrder(node.rightChild, keyList);
        }
        return keyList;
    }

    /**
     * 后序遍历
     * <ol>
     *     <li>访问左子节点</li>
     *     <li>访问右子节点</li>
     *     <li>访问根节点</li>
     * </ol>
     */
    private List<Integer> postOrder(Node node, List<Integer> keyList) {
        if (node != null) {
            postOrder(node.leftChild, keyList);
            postOrder(node.rightChild, keyList);
            keyList.add(node.key);
        }
        return keyList;
    }
}
