package javaQnA;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryExercise {
	enum QueryType {
		SET(1), GET(2);

		int type;

		QueryType(int type) {
			this.type = type;
		}

		int getType() {
			return type;
		}
	}

	class Query {
		int type;
		int index;

		Query(int type, int index) {
			this.type = type;
			this.index = index;
		}
	}

	int[] answerQueries(List<Query> queries, int N) {
		if (N < 1 || N > 1000000000 || queries.size() < 1 || queries.size() > 500000) {
			return null;
		}
		boolean[] booleanArray = new boolean[N];
		Long getCount = queries.stream().filter(q -> q.type == QueryType.GET.type).collect(Collectors.counting());
		int[] output = new int[getCount.intValue()];
		int outputIndex = 0;
		for (Query q : queries) {
			if (q.type == QueryType.GET.type) {
				if (!booleanArray[q.index - 1]) {
					int indexFound = getOutputFirstTrue(N, booleanArray);
					output[outputIndex] = indexFound >= q.index ? indexFound : -1;
				} else {
					int indexFound = getOutputFirstTrue(N, booleanArray);
					output[outputIndex] = indexFound >= q.index ? indexFound : -1;
				}
				++outputIndex;
			} else {
				booleanArray[q.index - 1] = true;
			}
		}
		return output;
	}

	private int getOutputFirstTrue(int N, boolean[] booleanArray) {
		int findFirstTrueIndex = 0;
		while (findFirstTrueIndex < N && !booleanArray[findFirstTrueIndex++])
			;
		--findFirstTrueIndex;
		int indexToReturn = findFirstTrueIndex < N && booleanArray[findFirstTrueIndex] ? findFirstTrueIndex + 1 : -1;
		return indexToReturn;
	}

	// These are the tests we use to determine if the solution is correct.
	// You can add your own at the bottom.
	int test_case_number = 1;

	void check(int[] expected, int[] output) {
		boolean result = Arrays.equals(expected, output);
		char rightTick = '\u2713';
		char wrongTick = '\u2717';
		if (result) {
			System.out.println(rightTick + " Test #" + test_case_number);
		} else {
			System.out.print(wrongTick + " Test #" + test_case_number + ": Expected " + Arrays.toString(expected));
			System.out.print(" Your output: " + Arrays.toString(output));
			System.out.println();
		}
		test_case_number++;
	}

	private void run() {
		check(new int[] { -1, 2, -1, 2 }, answerQueries(
				List.of(new Query(2, 3), new Query(1, 2), new Query(2, 1), new Query(2, 3), new Query(2, 2)), 5));
	}

	public static void main(String[] args) throws IOException {
		new QueryExercise().run();
	}
}
