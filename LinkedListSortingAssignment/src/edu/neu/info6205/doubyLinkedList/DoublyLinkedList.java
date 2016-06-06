/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.info6205.doubyLinkedList;

/**
 *
 * @author avini
 */
public class DoublyLinkedList {

    protected Node head;
    protected Node tail;
    public int size;
    public int exchange = 0;

    //DLL construtor
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(int val) {
        Node n = new Node(val, null, null);
        if (head == null) {
            head = n;
            tail = head;
        } else {
            n.setPrevious(tail);
            tail.setNext(n);
            tail = n;

        }
        size++;
    }

    public void dllBubbleSort() {
        Node current, post, previous, position;
        position = new Node();
        position.next = head;
        head = position;
        while (position.next != null) {
            current = position.next;
            previous = position;
            post = current.next;

            while (post != null) {
                if ((int) current.getData() < (int) post.getData()) {
                    post = post.next;
                    current = current.next;
                    previous = previous.next;
                } else {
                    //swapping the higher value to higher position in the DLL
                    Node temp = post.next;
                    Node t = current.next;
                    post.next = previous.next;
                    previous.next = current.next;
                    current.next = temp;
                    current.previous = post.previous;
                    post.previous = position.next; 
                    post.getNext().setPrevious(t);
                    previous = post;
                    post = temp;
                    exchange++;

//                    Node temp=previous.next;
//                    post.previous=current.previous;
//                    previous.next=current.next;
//                    current.next=post.next;
//                    post.next=temp;
//                
                }

            }
            position = position.next;
        }
        head = head.next;
    }

    public int DLExchange() {
        return exchange;
    }

    public void display() {
        Node n = head;
        for (int i = 0; i < size(); i++) {
            System.out.print(n.getData() + "\t");
            n = n.getNext();
        }
    }

    public int size() {
        return size;
    }

    //check if the list is empty
    public boolean isEmpty() {
        return head == null;
    }

    //get the size of the list
    public int getSize() {
        return size;
    }

    class Node {

        protected int data;
        protected Node next, previous;

        public Node() {
            next = null;
            previous = null;
            data = 0;
        }

        public Node(int d, Node n, Node p) {
            data = d;
            next = n;
            previous = p;
        }

        public void setNext(Node n) {
            next = n;
        }

        public void setPrevious(Node p) {
            previous = p;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setData(int d) {
            data = d;
        }

        public int getData() {
            return data;
        }
    }
}
