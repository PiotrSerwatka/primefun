package com.project.one.firstprojectonmac.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class PrimeService {

	public int primeNumber(int i) {
		int result = 0;
		if (i == 1) {
			return 2;
		}
		if (i == 2) {
			return 3;
		}
		int counter = 0;
		int potentialPrimeNumver = 3;
		while (counter != i-1){
			LocalDateTime start = LocalDateTime.now();
			result = potentialPrimeNumver;
			boolean notAPrime = false;
			for (int divider = 3 ; divider < potentialPrimeNumver / 2; divider++){
				if (potentialPrimeNumver % divider == 0) {
					notAPrime = true;
					break;
				}
			}
			if (!notAPrime) {
				counter++;
				if (counter %10000 == 0) {
					System.out.printf("PrimeCount=%s out of %s at: %s and took: %s%n", counter, i, LocalDateTime.now(), Duration.between(start,LocalDateTime.now()));
				}
			}
			potentialPrimeNumver+=2;
		}

		return result;
	}


	public List<Long> calculatPrimeInRange(long to) {

		if (to == 2) {
			return List.of(2L);
		}
		if (to == 3) {
			return List.of(2L, 3L);
		}

		List<Long> result = new LinkedList<>();

		List<Pair> batches = calculateBatches(to);



		return result;
	}

	private List<Pair> calculateBatches(long to) {
		List<Pair> result = new LinkedList<>();
		int liczbaRdzeni = 2;//Runtime.getRuntime().availableProcessors();

		long numberOfBatches = to/liczbaRdzeni;
		long incrementBy = to / numberOfBatches;

		for (long i = 1 ; i <= numberOfBatches; i+=incrementBy) {
			//1-5, 5-10, 10+15
            long end = i + incrementBy - 1;
			Pair pair = new Pair(i, end);
			result.add(pair);
		}

		return result;
	}


	record Pair(long from, long to){}

}
