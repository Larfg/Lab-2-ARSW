package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}


	public void run(){
		Timer timer = new Timer();

		for (int i = a; i <= b; i++) {
			if (isPrime(i)) {
				primes.add(i);
				System.out.println(i);
			}
		}
		TimerTask esperar = new TimerTask() {
			@Override
			public void run() {
				synchronized (this){
					try {
						parar();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		};

		timer.schedule(esperar,1000);


	}



	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public void parar() throws InterruptedException {
		wait(10000);
	}
	Integer primes(){
		return getPrimes().size();
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	
	
	
}
