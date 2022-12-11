package a22.climoilou.mono2.tp1.rd_pm_ih.vue.services;

import java.util.Random;

public interface SlowHelper {

	public static void slow(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			System.out.println("de retour du sleep par annulation");
		}
	}

	public static void slowRandom(int minMillis, int maxMillis) {
		SlowHelper.slow(new Random().nextInt(maxMillis - minMillis) + minMillis);
	}
}
