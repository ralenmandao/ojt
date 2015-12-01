////////////////////////////////////////////////////////////////////////////////
//  Course:   CSC 249 Fall 2015
//  Section:  (your section number)
//
//  Project:  Lab07
//  File:     MyStack
//   
//  Name:     (your first and last name)
//  Email:    (your Wake Tech Email Address)
////////////////////////////////////////////////////////////////////////////////
import java.util.*;
public class MyQueue<E extends Comparable<E>>
{
      private LinkedList<E> list = new LinkedList<E>();

      public void enqueue(E e)
      {
          list.addLast(e);
      }

      public E dequeue()
      {
          return list.removeFirst();
      }

      public int getSize()
      {
          return list.size();
      }

      public boolean isEmpty()
      {
          return list.size() == 0;
      }

      @Override
      public String toString()
      {
          return "Queue: " + list.toString();
      }
}
