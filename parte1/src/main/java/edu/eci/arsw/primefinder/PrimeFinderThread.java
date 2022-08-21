package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	boolean suspend = false;
	
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}


	public void run(){
		for (int i = a; i <= b; i++) {
			if (isPrime(i)) {
				primes.add(i);
				System.out.println(i);
				suspendido();
			}
		}
	}

	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public Integer primes(){
		return getPrimes().size();
	}

	public List<Integer> getPrimes() {
		return primes;
	}


	public void suspender() {
		suspend = true;
	}
	public synchronized void reanudar(){
		suspend = false;
		notifyAll();
	}

	public synchronized void suspendido(){
		while(suspend){
			try {
				wait();
			}catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
