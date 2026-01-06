package com.tesco.test;

public class TwoPairSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 int arr[] = {0, -1, 2, -3, 1};
		 int target=-2;
         int start=0,end=arr.length-1;
         int index=0;
         while(start!=end) {
        	 
        	 if(arr[start]+arr[end]==target)
        	 {
        		 System.out.println("Yes");
        		 break;
        	 }
         }
         

	}

}
