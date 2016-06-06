/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.info6205.linkedList;

import java.util.Iterator;

/**
 *
 * @author avini
 */
public class LinkedList {

    private Node head;
    private int size;
    int exchange = 0;
    //int compare=0;

    public LinkedList() {
        head = new Node(0);
        size = 0;

    }

    public void add(int o) {

        Node n = new Node(o);
        Node curNode = head;
        if (head.getNext() != null) {
            while (curNode.getNext() != null) {
                curNode = curNode.getNext();
            }
        }
        curNode.setNext(n);
        size++;
    }

//    public void remove(){
//        Node n=head;
//        Node p=null;
//        if(head==null){
//        System.out.println("Linked List is empty");
//           
//        }
//        else {
//            while(n.next!=null){
//             p=n;
//            n=n.getNext();
//            }
//             p.setNext(null);
//             size--;
//        }
//       
//    }
    public int size() {
        return size;
    }

    public void display() {
        Node n = head.getNext();
        for (int i = 0; i < size(); i++) {
            System.out.print(n.getData() + "\t");
            n = n.getNext();
        }
    }

//    public void slBubbleSort() {
//
//    }
    public void slBubbleSort() {
        Node current, post, previous, position;
        position = new Node(0);
        position.next = head;
        head = position;

        while (position.next != null) {
            current = position.next;
            previous = position;
            post = current.next;
            while (post != null) {
                if (current.getData() < post.getData()) {
                    previous = current;
                    current = post;
                    post = post.next;
                } else {
                    Node temp = post.next;
                    post.next = previous.next;
                    previous.next = current.next;
                    current.next = temp;
                    previous = post;
                    post = temp;
                    exchange++;
                }

            }

            position = position.next;
        }

        head = head.next;
    }

    public int Exchange() {
        return exchange;
    }

    private class Node {

        Node next;
        int data;

        public Node(int data) {

            this.data = data;
        }

        public Node(int data, Node next) {
            this.next = next;
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }
}
