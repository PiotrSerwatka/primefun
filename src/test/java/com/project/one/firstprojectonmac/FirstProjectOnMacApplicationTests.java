package com.project.one.firstprojectonmac;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

import com.project.one.firstprojectonmac.service.PrimeService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class FirstProjectOnMacApplicationTests {
	
	private final PrimeService primeService = new PrimeService();



	@ParameterizedTest
	@CsvSource(value = {"1:2", "2:3", "3:5", "5:11", "100:541", "100000:1299709"}, delimiter = ':') //, "10000:104729", "100000:1299709", "10000:104729", "100000:1299709", "1000000:15485863"}
	void first_prime_number(int primeIndex, int expected) {
		
		long result = primeService.primeNumber(primeIndex);
		
		assertThat(result).isEqualTo(expected);

	}

	@ParameterizedTest
	@CsvSource(value = {"2:1,2", "3:2,3", "5:3,5", "10:4,7", "1299709:100000,1299709"}, delimiter = ':') //, "10000:104729", "100000:1299709", "1000000:15485863"}
	void prime_numbers_parallel(long primeNumbersToValue, String expectedResult) {

		List<Long> listOfPrimeNumbers = primeService.calculatPrimeInRange(primeNumbersToValue);

		assertThat(format("%s,%s", listOfPrimeNumbers.size(),listOfPrimeNumbers.getLast()))
				.isEqualTo(expectedResult);

	}
	
}
