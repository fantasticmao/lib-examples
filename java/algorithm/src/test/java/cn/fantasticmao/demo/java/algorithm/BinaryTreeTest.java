package cn.fantasticmao.demo.java.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * BinaryTreeTest
 *
 * @author fantasticmao
 * @since 2020-05-14
 */
public class BinaryTreeTest {
    private final BinaryTree tree;

    public BinaryTreeTest() {
        this.tree = new BinaryTree();
        /*
         * 二叉搜索树的数据
         *
         *      6
         *    /   \
         *   2     7
         *  / \     \
         * 1   4     8
         *    / \    /
         *   3   5  9
         */
        tree.insert(6)
            .insert(2)
            .insert(1)
            .insert(4)
            .insert(3)
            .insert(5)
            .insert(7)
            .insert(8)
            .insert(9);
    }

    @Test
    public void preOrder() {
        List<Integer> expect = List.of(6, 2, 1, 4, 3, 5, 7, 8, 9);
        List<Integer> actual = tree.traverse(BinaryTree.TraverseType.PRE);
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void inOrder() {
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> actual = tree.traverse(BinaryTree.TraverseType.IN);
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void postOrder() {
        List<Integer> expect = List.of(1, 3, 5, 4, 2, 9, 8, 7, 6);
        List<Integer> actual = tree.traverse(BinaryTree.TraverseType.POST);
        Assert.assertEquals(expect, actual);
    }
}
