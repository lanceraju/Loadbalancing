//SpecialNumbers.java
package loadbalancer;


import java.util.Random;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;


public class SpecialNumbers {
    Random RNG = new Random();
	int perfectNum;
	int primeNum;
	int nonPrimeNum;
	LinkedBlockingQueue<Result> resultQueue;
        
	SpecialNumbers(LinkedBlockingQueue<Result> resultQueue) {
	    generate(RNG.nextInt(100));
	    primeNum = RNG.nextInt(10000)+1;
	    nonPrimeNum = RNG.nextInt(10000)+1;
	    this.resultQueue = resultQueue;
	}
	public void generate(int a){
	    if (a >=0 && a<31){
	        this.perfectNum =RNG.nextInt(50)+1; // Range[1,50]
        }
        else if (a>30 && a<81){
	        this.perfectNum=RNG.nextInt(400)+51; // Range:[51, 450]
        }
        else{
	        this.perfectNum=RNG.nextInt(50)+401; // Range:[451, 500]
        }
	}
	public boolean determinePrime(int x){
	    int modulo = (int)Math.sqrt(x);
	    for (int i = 2; i <=modulo; i++){
	        if (x % i == 0){
	            return false;
	        }
	    } // for block - - - - - - - - - - - - - - - - - - - - - - - - - -
            return true;
	}
	public boolean isPerfect(int x){
	    int temporary = 1;
	    if (x==1 || x==2)
	        return false;
	    for (int i = 2; i < x; i++){
	        if (x % i == 0){
	            temporary+= i;
	            //-------temphold.push(i);
            }
	    } // for block - - - - - - - - - - - - - - - - - - - - - - - - - -
        return (temporary== x);
	}
	public int calculateVariance(){
	    // I could've done the calculation without declaring variable but it looked
        // really messy, so I created 5 temp variables.
        int mean = (this.primeNum+ this.nonPrimeNum+ this.perfectNum)/3;
        int temp1 = (int)Math.pow(mean-this.primeNum, 2) ;
        int temp2 = (int)Math.pow(mean-this.nonPrimeNum, 2) ;
        int temp3 = (int)Math.pow(mean-this.perfectNum, 2) ;
        int lastTemp = (temp1 + temp2 + temp3)/3;
        return lastTemp;
			
        // The calculation without declaring a variable.
        // It's more efficient to use this simple return, but just for
        // presentation sake in this project, I'll leave it neater!
        // return ((int)Math.pow(mean-this.primeNum, 2)  + (int)Math.pow(mean-this.nonPrimeNum, 2) + (int)Math.pow(mean-this.perfectNum, 2))/3
        }

	public void compute() {
	    int variance = calculateVariance();
	    //determinePrime() is used for primeNum and nonPrimeNum, so negating
        //the function in the if-statement is necessary.
        if (determinePrime(this.primeNum) && !determinePrime(this.nonPrimeNum)
                && isPerfect(this.perfectNum)){ resultQueue.add(new Result(this.primeNum, this.nonPrimeNum,
                this.perfectNum, variance, false));
        }
        else{
            resultQueue.add(new Result(0,0,0,0, true));
        }
	}
}
