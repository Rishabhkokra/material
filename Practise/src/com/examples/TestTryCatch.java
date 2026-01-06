package com.examples;

public class TestTryCatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(test());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static int test() throws Exception {
		try {
			if(true) {
				throw new Exception("hello....");
			}
			return 1;
		} catch (Exception ex) {
			//System.out.println("excep");
			throw new NullPointerException("i am null...");
		} finally {
			//throw new Exception("hello....");
			System.out.println("test..");
		}
	}

}
