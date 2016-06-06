/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.neu.info6205.stacks;
/**
 *
 * @author avinita{
 */
public class Stacks {
      private int top;
      private final String[] stackArray;
      private final int maxsize;
      
      public Stacks(int size) {
          top=-1;
          maxsize=size;
          stackArray=new String[size];
    
         
      }
      public void push(String s) {
          if(top+1 < maxsize) {
              stackArray[++top]=s;
          }else {
              System.out.println("Stack is full");
             //throw new StackException("Stack overflow");
          }
      }
     
      public String pop() throws StackException {
         
          if(top>=0) {
              return stackArray[top--];
          }
          else {
              throw new StackException("Stack underflow");
          }
      }

      public boolean isEmpty() {
       return top==-1;
      }
      
      public void display() {
          for(int i=0;i<maxsize;i++) {
              System.out.println( stackArray[i]);
          }
      }
      public String peek()throws StackException {
          if(top>=0) {
              return stackArray[top];
          }
          else {
              throw new StackException("Stack underflow");
          }
      }
  
}
