package edu.eci.arsw.primefinder;


import java.util.concurrent.TimeUnit;
import java.util.Objects;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		PrimeFinderThread pft=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread pft2 = new PrimeFinderThread(10000001,20000000);
		PrimeFinderThread pft3 = new PrimeFinderThread(20000001,30000000);

		pft.start();
		pft2.start();
		pft3.start();

		long tiempoinicio = System.nanoTime();
		TimeUnit.SECONDS.sleep(5);
		long tiempofinal = System.nanoTime();
		long cuenta = tiempofinal - tiempoinicio;

		pft.suspender();
		pft2.suspender();
		pft3.suspender();

		Scanner scanner = new Scanner(System.in);
		Integer primes = pft.primes() + pft2.primes() + pft3.primes();
		System.out.println("la cantidad de primos encontrados hasta ahora son:" + primes + " " +"Presione Enter para reanudar");
		String in = scanner.nextLine();

		if (Objects.equals(in, "")) {
			pft.reanudar();
			pft2.reanudar();
			pft3.reanudar();
		}
	}
}
