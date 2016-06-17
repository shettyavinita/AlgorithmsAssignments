package BinaryTree;

import static java.lang.Math.max;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author avini
 */
public class BinarySearchTree {

    public static Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public static boolean isEmpty() {
        return root == null;
    }

    public void add(int data) {
        root = add(root, data);
    }

    private Node add(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        } else if (data > node.getData()) {
            node.right = add(node.right, data);
        } else {
            node.left = add(node.left, data);
        }
        return node;
    }

    public int calHeight() {
        return calHeight(root);
    }

    int calHeight(Node node) {
        if (node == null) {
            return -1;
        }
        return 1 + max(calHeight(node.left), calHeight(node.right));
    }

    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node n) {
        if (n != null) {
            inorderTraversal(n.getLeft());
            System.out.print(n.getData() + "\t");
            inorderTraversal(n.getRight());
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(Node n) {
        if (n != null) {
            System.out.print(n.getData() + "\t");
            preOrderTraversal(n.getLeft());
            preOrderTraversal(n.getRight());
        }
    }

    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node n) {
        if (n != null) {
            postorderTraversal(n.getLeft());
            postorderTraversal(n.getRight());
            System.out.print(n.getData() + "\t");
        }
    }

    class Node {

        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

}
