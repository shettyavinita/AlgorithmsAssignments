/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.info62015.testmain;

import edu.neu.info6205.doubyLinkedList.DoublyLinkedList;
import edu.neu.info6205.linkedList.LinkedList;
import java.util.Random;

/**
 *
 * @author avini
 */
public class LinkedListSortingAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //code for adding into linked list and sorting it
        int n = getRandomNumberInRange(0, 100);
        //int n=4;
        LinkedList l = new LinkedList();
        
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx Linked List Sorting xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");   
   
//        LinkedList lp=new LinkedList();
//        lp.add(1);
//        lp.add(2);
//        lp.add(3);
//        lp.display();
//        lp.remove();
//        lp.display();
        
        System.out.println("Linked List Bubble Sort Implementation"+"\n");

        if (n > 1) {
            int pass = 0;
            System.out.println("The linked list would consist of " + n + " elements");

            for (int i = 0; i <n; i++) {
                l.add(getRandomNumberInRange(0, 49999));//since it should be less than 50000
            }
          
            //System.out.println("Adding in LL took"+"\t"+diff);
            System.out.println("The Elements of unsorted Linked List are as follows: ");
            
            l.display();
            System.out.println("");
          
            //since bubble sort function just push the highest value down...it has to be done recursively so I have used for loop here
            for (int i = 0; i < n - pass; i++) {
                l.slBubbleSort();
                pass++;
                System.out.println("This is Pass" + pass);
                l.display();
                System.out.println("");
            }
            
   

            System.out.println("The Sorted List is");
            l.display();
            System.out.println("\n No of Exchanges\t"+l.Exchange());
//            System.out.println("\n No of Compares\t"+l.Compare());
        } else if (n == 0) {
            System.out.println("No elements in Linked List to sort");
        } else {
            System.out.println("The Linked List has only 1 element");
            l.display();
        }
        
        System.out.println("\n\n\n"+"xxxxxxxxxxxxxxxxxxxxxx Doubly Linked List Sorting xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//        
        System.out.println("\n"+"Doubly Linked List Bubble Sort Implementation"+"\n");

        //code for adding into doubly Linked List and sorting it
        
        int m = getRandomNumberInRange(0, 100);
        //int m=4;
        int pas=0;
        DoublyLinkedList dll = new DoublyLinkedList();
        System.out.println("");
        System.out.println("The Doubly linked list would consist of " + m + " unsorted elements which are");
        
        for (int i = 0; i < m; i++) {
            dll.add(getRandomNumberInRange(0, 49999));//since it should be less than 50000

        }
    
       
      
        dll.display();
        System.out.println("");
       
        if (m > 0) {
            for (int i = 0; i < m - pas; i++) {
                dll.dllBubbleSort();
                pas++;
                System.out.println("This is Pass" + pas);
                dll.display();
                System.out.println("");
            }
            
        } else if (m == 0) {
            System.out.println("No elements in List");
        } else {
            System.out.println("The Doubly Linked List has only 1 element");

        }
       
        System.out.println("\n No of Exchanges\t"+dll.DLExchange());

    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min; //return r.nextInt(max+1);
    }

}
