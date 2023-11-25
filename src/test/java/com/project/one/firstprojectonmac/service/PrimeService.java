package com.project.one.firstprojectonmac.service;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class PrimeService {

    public long primeNumber(long i) {
        long result = 0;
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 3;
        }
        int counter = 0;
        long potentialPrimeNumver = 3;
        while (counter != i - 1) {
            LocalDateTime start = LocalDateTime.now();
            result = potentialPrimeNumver;
            if (isAPrime(potentialPrimeNumver)) {
                counter++;
                if (counter % 10000 == 0) {
                    System.out.printf("PrimeCount=%s out of %s at: %s and took: %s%n", counter, i, LocalDateTime.now(), Duration.between(start, LocalDateTime.now()));
                }
            }
            potentialPrimeNumver += 2;
        }

        return result;
    }

    private boolean isAPrime(Long potentialPrimeNumver) {
		if (potentialPrimeNumver == 1 || potentialPrimeNumver == 4 || potentialPrimeNumver % 2 == 0) return false;
        for (int divider = 3; hasNextBatchToCalculate(potentialPrimeNumver / 2, divider); divider++) {
            if (potentialPrimeNumver % divider == 0) {
                return false;
            }
        }
        return true;
    }


    public List<Long> calculatPrimeInRange(long to) {

		List<Long> result = new LinkedList<>();

        if (to >= 2) {
            result.add(2L);
        }

        List<Pair> batches = calculateBatches(to);

		result.addAll(batches.stream().parallel()
				.map(this::findPrimesInRange)
				.flatMap(List::stream)
				.toList());


        return result;
    }

	private List<Long> findPrimesInRange(Pair pair) {
		List<Long> primeFound = new LinkedList<>();
		for (long i = pair.from; i<= pair.to; i++) {
			if (isAPrime(i)) {
				primeFound.add(i);
			}
		}
		return primeFound;
	}

	private List<Pair> calculateBatches(long to) {
        List<Pair> result = new LinkedList<>();
        long liczbaRdzeni = Runtime.getRuntime().availableProcessors();

		if (liczbaRdzeni > to) {
			liczbaRdzeni = to;
		}

        long numberOfBatches = to / liczbaRdzeni;
        long incrementBy = to / numberOfBatches;

		long i = 1;
		boolean hasNextBatchToCalculate = hasNextBatchToCalculate(to, i);
        while ( hasNextBatchToCalculate ) {
            //1-5, 5-10, 10+15
			long end = i + incrementBy - 1;
			Pair pair = getPair(to, hasNextBatchToCalculate, i, end);
			result.add(pair);

			i += incrementBy;
			hasNextBatchToCalculate = hasNextBatchToCalculate(to, i);
        }

        return result;
    }

	private static Pair getPair(long to, boolean hasNextBatchToCalculate, long i, long end) {
		if (hasNextBatchToCalculate) {
			 return new Pair(i, end);
		} else {
			 return new Pair(i, to);
		}
	}

	private static boolean hasNextBatchToCalculate(long to, long i) {
		return i < to;
	}


	record Pair(long from, long to) {
    }

}
