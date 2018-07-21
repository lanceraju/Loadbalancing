//CountDivisorsThread.java
package loadbalancer;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CountDivisorsThread extends Thread {
	private ConcurrentLinkedQueue<SpecialNumbers[]> taskQueue;
	public CountDivisorsThread(ConcurrentLinkedQueue<SpecialNumbers[]> taskQueue) {
		this.taskQueue = taskQueue;
	}
	public void run() {
	    while (true) {
	        SpecialNumbers[] temp = taskQueue.poll();
            if (temp == null)
                break;

            // Compute each SpecialNumbers array index
            for(int i = 0; i <temp.length; i++){
                    temp[i].compute();
		    }
		}
	}
}