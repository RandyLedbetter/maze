package com.maze_test;

public class Counter{
	 
	  private int total;
	  private final int maxInt;
	  
	  public Counter(){
	    total = 0;
	    maxInt = Integer.MAX_VALUE;
	  }
	  
	  public int total(){
	    return total;
	  }
	  
	  public boolean increment(){
	    if(total < maxInt){
	      total++;
	      return true;
	    }
	    else
	      return false;
	  }
	  
	  public boolean isFull(){
	    if(total < maxInt)
	      return false;
	    else
	      return true;
	  }
	  
}