package com.project.one.firstprojectonmac;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.one.firstprojectonmac.service.PrimeService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

public class FirstProjectOnMacApplicationTests {
	
	private final PrimeService primeService = new PrimeService();



	@ParameterizedTest
	@CsvSource(value = {"1:2", "2:3", "3:5", "5:11", "100:541", "10000:104729", "100000:1299709"}, delimiter = ':') //, "10000:104729", "100000:1299709", "1000000:15485863"}
	void first_prime_number(int primeIndex, int expected) {
		
		int result = primeService.primeNumber(primeIndex);
		
		assertThat(result).isEqualTo(expected);

	}

	@ParameterizedTest
	@CsvSource(value = {"2:2", "3:2,3", "5:2,3,5", "10:2,3,5,7"}, delimiter = ':') //, "10000:104729", "100000:1299709", "1000000:15485863"}
	void prime_numbers_parallel(long primeNumbersToValue, String expectedResult) {
		List<Long> listOfPrimeNumbers = primeService.calculatPrimeInRange(primeNumbersToValue);

		assertThat(listOfPrimeNumbers).containsAll(
				Arrays.stream(
						expectedResult
							.split(",")
						).map(Long::valueOf)
						.toList()
		);
	}
	
}
