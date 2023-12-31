package javaQnA;

import java.util.List;

// Typical atoi implementation problem
public class Atoi {
	public int atoi(String str) {
		if (str == null || str.length() < 1)
			return 0;
		// trim white spaces
		str = str.trim();
		char flag = '+';
		// check negative or positive
		int i = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			i++;
		} else if (str.charAt(0) == '+') {
			i++;
		}
		// use double to store result
		double result = 0;
		// calculate value
		while (str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			result = result * 10 + (str.charAt(i) - '0');
			i++;
		}
		if (flag == '-')
			result = -result;
		// handle max and min
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		return (int) result;
	}

	public void run() {
		var stringList = List.of("-9823", "+2783652", "122", "012");
		for (String s : stringList)
			System.out.println(s + " atoi=" + atoi(s));

	}

	public static void main(String[] args) {
		Atoi atoi = new Atoi();
		atoi.run();
	}
}
