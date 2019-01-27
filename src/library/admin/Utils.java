package library.admin;

public class Utils {

	/*
	 * long newNumber in den numbers array einfügen
	 */
	public static long[] addNumber(long[] numbers, long newNumber) {
		if (numbers == null) {
			numbers = new long[1];
			numbers[0] = newNumber;
			return numbers;
		}
		long[] newNumbers = new long[numbers.length + 1];
		System.arraycopy(numbers, 0, newNumbers, 0, numbers.length);
		newNumbers[numbers.length] = newNumber;
		return newNumbers;
	}

	/*
	 * array1 und array2 zusammenfügen
	 */
	public static long[] addNumbers(long[] array1, long[] array2) {
		if (array1 == null || array1.length == 0)
			return array2;
		if (array2 == null || array2.length == 0)
			return array1;
		long[] newNumbers = new long[array1.length + array2.length];
		System.arraycopy(array1, 0, newNumbers, 0, array1.length);
		System.arraycopy(array2, array1.length, newNumbers, 0, array2.length);
		return newNumbers;
	}
}

