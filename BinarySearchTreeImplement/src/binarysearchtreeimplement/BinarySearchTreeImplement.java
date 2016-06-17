/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarysearchtreeimplement;

import BinaryTree.BinarySearchTree;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author avini
 */
public class BinarySearchTreeImplement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
        System.out.println("Please enter the no. of times to run the program ");
        Scanner sc = new Scanner(System.in);
        int no = sc.nextInt();
        int h = 0;
        int n = getRandomNumberInRange(0, 100);
        System.out.println("No of Elements in Tree would be " + n);
        for (int i = 0; i <= no - 1; i++) {
            h = h + mainFunc(n);
        }
        System.out.println("Average height is " + h / no);
        }
        catch(Exception e){
            System.out.println("Input mismatch exception. Please enter a number");
        }

    }

    public static int mainFunc(int n) {

        //System.out.println("Please enter the no of items to add in the tree");
        //Scanner sc = new Scanner(System.in);
        int height = 0;
        try {
            //n = sc.nextInt();
            if (n != 0) {
                int a[] = new int[n];
                for (int i = 0; i <= n - 1; i++) {
                    //a[i] = getRandomNumberInRange(0, 100);
                    a[i] = i;
                }
                print(a);
                System.out.println("");
                System.out.println("After the  Knuth Shuffle the elements are as follows:");
                knuthShuffle(a);
                print(a);
                System.out.println("");
                BinarySearchTree binTree = new BinarySearchTree();
                //System.out.println(binTree.isEmpty());
                for (int i = 0; i <= n - 1; i++) {
                    binTree.add(a[i]);
                }
                System.out.println("Inorder Traversal");
                binTree.inorderTraversal();
                System.out.println("");
                System.out.println("Preorder Traversal");
                binTree.preOrderTraversal();
                System.out.println("");
                System.out.println("PostOrder Traversal");
                binTree.postorderTraversal();
                System.out.println("");
                System.out.println("Height of tree is" + "\t" + binTree.calHeight());
                height = binTree.calHeight();
            } else {
                System.out.println("There is nothing to add in the Tree so the tree is empty");
              
            }
        } catch (Exception e) {
            System.out.println("The value you have input is not a valid integer");
        }

        return height;
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min; //return r.nextInt(max+1);
    }
    public static final Random generator = new Random();

    public static void knuthShuffle(int[] a) {
        int n = a.length;
        while (n > 1) {
            int m = generator.nextInt(n--);
            int temp = a[n];
            a[n] = a[m];
            a[m] = temp;
        }
    }

    public static void print(int a[]) {
        int n = a.length;
        for (int i = 0; i <= n - 1; i++) {
            System.out.print(a[i] + "\t");

        }
    }
}
