//Result.java
package loadbalancer;

public class Result {
    private int smallestPerf;
    private int smallestPrime;
    private int smallestNonPrime;
    private boolean emptyset;
    private int variance;

	Result(int prime, int nonprime, int perfect, int variance, boolean flag) {
	    this.smallestPrime = prime;
	    this.smallestNonPrime = nonprime;
	    this.smallestPerf = perfect;
	    this.variance = variance;
	    this.emptyset = flag;
	}

	/* Condensed the getter and setter methods */
	public int getSmallestPrime() { return smallestPrime; }
	public int getSmallestNonPrime() { return smallestNonPrime; }
	public int getSmallestPerfect() { return smallestPerf; }
	public void setSmallestPrime(int x) { this.smallestPrime = x; }
	public void setSmallestNonPrime(int x) { this.smallestNonPrime = x; }
	public void setSmallestPerf(int x) { this.smallestPerf = x; }
	public int getVariance(){ return this.variance; }
	public void display(){
	    if (!emptyset){
	        System.out.println("\n*SpecialNum*::\tPrime: " +this.smallestPrime+ "\t\tNon-Prime: "+
                this.smallestNonPrime + "\t\tPerfect: " + this.smallestPerf +"\n\t\tVARIANCE: "
                +this.variance);
            }
            else{
                System.out.println("SpecialNumber: 0");
            }
	}
	public int calculateVariance(){
	    int mean = (this.smallestNonPrime + this.smallestPrime + this.smallestPerf)/3;
	    int temp1 = (int)Math.pow(mean-this.smallestNonPrime, 2) ;
	    int temp2 = (int)Math.pow(mean-this.smallestPrime, 2) ;
	    int temp3 = (int)Math.pow(mean-this.smallestPerf, 2) ;
	    int lastTemp = (temp1 + temp2 + temp3)/3;
	    return lastTemp;
	}
        public boolean notEmpty(){
            // nonPrime range [1, 10000].
            return (this.smallestNonPrime >0);
        }
}