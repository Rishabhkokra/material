package com.examples;

import java.lang.*;
import java.io.*;
import java.util.*;

class FileRead {
	String strTemp;
	String lock = "test";
	String strOutput;
	String strInput;
	Queue<String> ql = new LinkedList<String>();

	class TextFileReader extends Thread {
		public void run() {
			System.out.println(new Date());
			try {

				BufferedReader br = new BufferedReader(
						new FileReader("/Users/b0224506/resources/Practise/src/com/examples/test.txt"));

				while (true) {
					strTemp = br.readLine();
					if (strTemp == null) {
						br.close();
						ql.offer("eof");
						break;
					}
					synchronized (lock) {
						ql.offer(strTemp);
						lock.notifyAll();
						// this is to prevent out of memory error
						if (ql.size() > 2) {
							try {
								lock.wait();
							} catch (InterruptedException ex) {
							}
						}
					}
				}

			} // outer while end
			catch (IOException ex) {
				ex.printStackTrace();
			} catch (OutOfMemoryError eo) {
				System.out.println("queue size " + ql.size());
			} // outer try end
		}// run method end
	}// TextReader class end

	class TextFileWriter extends Thread {
		public void run() {
			try {
				File f = new File("outputtest.txt");
				f.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				while (!(ql.peek() == "eof")) {
					synchronized (lock) {
						while (ql.peek() == null) {
							try {
								lock.notifyAll();
								lock.wait();
							} catch (InterruptedException ex) {
							}
						}
					} // synchornized block end
					while (ql.peek() != null) {
						strTemp = ql.poll();
						strOutput = strTemp.replaceAll("<", "[");
						strOutput = strOutput.replaceAll(">", "]");
						bw.write(strOutput);
						bw.newLine();
					}
				} // outer while block end
				bw.flush();
				bw.close();
				System.out.println("done");

			} catch (IOException ex) {
				System.out.println("Exception while writing to file" + ex.toString());
			}
		}
	}

	public static void main(String args[]) throws Exception {
		System.out.println(new Date());
		FileRead t = new FileRead();
		TextFileWriter tw = t.new TextFileWriter();
		TextFileReader tr = t.new TextFileReader();

		tr.start();

		System.out.println(new Date());
	}
}