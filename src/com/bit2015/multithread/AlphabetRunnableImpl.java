package com.bit2015.multithread;

public class AlphabetRunnableImpl implements Runnable {

	public void run() {
		for(char c='A';c<='Z';c++){
			System.out.print(" "+c);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
