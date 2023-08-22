package javaQnA;

import java.util.ArrayList;
import java.util.Stack;

// Typical basic calculator problem
public class Calculate {
	public int calculate(String s) {
		// delete white spaces
		s = s.replaceAll(" ", "");
		Stack<String> stack = new Stack<String>();
		char[] arr = s.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= '0' && arr[i] <= '9') {
				sb.append(arr[i]);
				if (i == arr.length - 1) {
					stack.push(sb.toString());
				}
			} else {
				if (sb.length() > 0) {
					stack.push(sb.toString());
					sb = new StringBuilder();
				}
				if (arr[i] != ')') {
					stack.push(new String(new char[] { arr[i] }));
				} else {
					// when meet ')', pop and calculate
					ArrayList<String> t = new ArrayList<String>();
					while (!stack.isEmpty()) {
						String top = stack.pop();
						if (top.equals("(")) {
							break;
						} else {
							t.add(0, top);
						}
					}
					int temp = 0;
					if (t.size() == 1) {
						temp = Integer.valueOf(t.get(0));
					} else {
						temp = innerCalc(t, temp);
					}
					stack.push(String.valueOf(temp));
				}
			}
		}
		ArrayList<String> t = new ArrayList<String>();
		while (!stack.isEmpty()) {
			String elem = stack.pop();
			t.add(0, elem);
		}
		int temp = 0;
		return innerCalc(t, temp);
	}

	private int innerCalc(ArrayList<String> t, int temp) {
		for (int j = t.size() - 1; j > 0; j = j - 2) {
			if (t.get(j - 1).equals("-")) {
				temp += 0 - Integer.valueOf(t.get(j));
			} else {
				temp += Integer.valueOf(t.get(j));
			}
		}
		temp += Integer.valueOf(t.get(0));
		return temp;
	}

	public static void main(String[] args) {
		String calc = "5+ 4 + (7-8)+6";
		System.out.println(calc + "=" + new Calculate().calculate(calc));
	}
}
