package javaQnA;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class BossFight {

	class Warrior {
		Integer health;
		Integer damage;

		Warrior(int health, int damage) {
			this.health = health;
			this.damage = damage;
		}
	}

	public double getMaxDamageDealt(int N, int[] H, int[] D, int B) {
		int maxHealth = Arrays.stream(H).max().getAsInt();
		int maxDamage = Arrays.stream(D).max().getAsInt();
		int minHealth = Arrays.stream(H).min().getAsInt();
		int minDamage = Arrays.stream(D).min().getAsInt();
		if (N < 2 || N > 500000 || B < 1 || B > 1000000000 || minHealth < 1 || maxHealth > 1000000000 || minDamage < 1
				|| maxDamage > 1000000000) {
			return 0.0;
		}
		LinkedList<Warrior> warriors = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			warriors.add(new Warrior(H[i], D[i]));
		}
		warriors.sort(new Comparator<Warrior>() {

			@Override
			public int compare(Warrior o1, Warrior o2) {
				return o2.health.compareTo(o1.health);
			}

		});
		double maxScenario1 = Math.max(calculateDamage(B, warriors.get(0), warriors.get(1)),
				calculateDamage(B, warriors.get(1), warriors.get(0)));
		if (warriors.size() > 2) {
			maxScenario1 = Math.max(maxScenario1, Math.max(calculateDamage(B, warriors.get(0), warriors.get(2)),
					calculateDamage(B, warriors.get(2), warriors.get(0))));
		}
		if (warriors.size() > 3) {
			maxScenario1 = Math.max(maxScenario1, Math.max(calculateDamage(B, warriors.get(0), warriors.get(3)),
					calculateDamage(B, warriors.get(3), warriors.get(0))));
		}
		warriors.sort(new Comparator<Warrior>() {

			@Override
			public int compare(Warrior o1, Warrior o2) {
				return o2.damage.compareTo(o1.damage);
			}

		});
		double maxScenario2 = Math.max(calculateDamage(B, warriors.get(0), warriors.get(1)),
				calculateDamage(B, warriors.get(1), warriors.get(0)));
		if (warriors.size() > 2) {
			maxScenario2 = Math.max(maxScenario2, Math.max(calculateDamage(B, warriors.get(0), warriors.get(2)),
					calculateDamage(B, warriors.get(2), warriors.get(0))));
		}
		if (warriors.size() > 3) {
			maxScenario2 = Math.max(maxScenario2, Math.max(calculateDamage(B, warriors.get(0), warriors.get(3)),
					calculateDamage(B, warriors.get(3), warriors.get(0))));
		}
		return Double.parseDouble(String.format("%.6f", Math.max(maxScenario1, maxScenario2)));
	}

	private double calculateDamage(int B, Warrior warriorFront, Warrior warriorBack) {
		double maxDamageDealt;
		// first round
		maxDamageDealt = ((double) warriorFront.health / B) * warriorFront.damage
				+ ((double) warriorFront.health / B) * warriorBack.damage;
		// second round
		maxDamageDealt += ((double) warriorBack.health / B) * warriorBack.damage;
		return maxDamageDealt;
	}

	// These are the tests we use to determine if the solution is correct.
	// You can add your own at the bottom.
	int test_case_number = 1;

	void check(double expected, double output) {
		boolean result = (expected == output);
		char rightTick = '\u2713';
		char wrongTick = '\u2717';
		if (result) {
			System.out.println(rightTick + " Test #" + test_case_number);
		} else {
			System.out.print(wrongTick + " Test #" + test_case_number + ": Expected " + expected);
			System.out.print(" Your output: " + output);
			System.out.println();
		}
		test_case_number++;
	}

	private void run() {
		check(6.500000, getMaxDamageDealt(3, new int[] { 2, 1, 4 }, new int[] { 3, 1, 2 }, 4));
		check(62.750000, getMaxDamageDealt(4, new int[] { 1, 1, 2, 100 }, new int[] { 1, 2, 1, 3 }, 8));
		check(62.750000, getMaxDamageDealt(4, new int[] { 1, 1, 2, 3 }, new int[] { 1, 2, 1, 100 }, 8));
	}

	public static void main(String[] args) throws IOException {
		new BossFight().run();
	}

}
