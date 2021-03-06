// Tester.java
package loadbalancer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Tester {
    //Static variables used for looping, task-thread generation, and array
    //instantiation.
    static int MAX = 10000;
    static int numberOfThreads = 4;
    static int numberOfTasks = 100;
    static int remainingTasks = MAX % numberOfTasks;
    static int differential = MAX/numberOfTasks;
    static SpecialNumbers a[] = null;
   

	public static void main(String[] args) throws InterruptedException {
        Result result = null;
        LinkedBlockingQueue<Result> resultQueue = new LinkedBlockingQueue<Result>();
        ConcurrentLinkedQueue<SpecialNumbers[]> taskQueue = new ConcurrentLinkedQueue<>();
        Result tempSm = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < (numberOfTasks); i++) {
                    
		// Last iteration of the for loop adds remaining tasks to the
        // final task created; if (amountoftasks / taskcreated) is
        // divisible with no remainder, adds 0.
        if (i==numberOfTasks-1){ differential+= remainingTasks;}
            a = new SpecialNumbers[differential];
            for (int j = 0; j <(differential); j++){
                a[j] = new SpecialNumbers(resultQueue);
            }
            taskQueue.add(a);
            a = null;
        }
        CountDivisorsThread[] workers = new CountDivisorsThread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++)
            workers[i] = new CountDivisorsThread(taskQueue);

        System.out.println("Threads starting"); // ---- marker
        for (int i = 0; i < numberOfThreads; i++)
			workers[i].start();
        for (int i = 0; i < numberOfThreads; i++)
			workers[i].join();
        System.out.println("Threads joined");  // ---- marker
        /*  The program generates an incredible amount of objects, so
         *  naturally, the variance generated by any SpecialNumber Object
         *  that is unique will be smaller than the currentVariance
         *  instantiation. */
        int currentVariance = 100000000;
        tempSm = (new Result(0,0,0,0, true));
        while(resultQueue.size()>0) {
            result = resultQueue.take();
            if ((result.getVariance() < currentVariance) && result.notEmpty()) {
                currentVariance = result.getVariance();
                tempSm = new Result(result.getSmallestPrime(), result.getSmallestNonPrime(), result.getSmallestPerfect(),
                        currentVariance,false);
                }
                result.display();
        }//while loop - - - - - - - - - - - - - - - - - - - - - - - - -
	    long elapsedTime = System.currentTimeMillis() - startTime;
            if (tempSm!=null){
                System.out.println("\nThe smallest Object variance is... "); 
                tempSm.display();
            }
            //fail-safe, just in the event the 'result Object' was null and trying to be accessed.
            else if (tempSm==null){
                System.out.println("No Object of appropriate Variance found");
            }
            System.out.println("\nTotal elapsed time:  " + (elapsedTime / 1000.0) + " seconds.\n");
	}
}
// end of tester.java-----------------------------------------------------------
