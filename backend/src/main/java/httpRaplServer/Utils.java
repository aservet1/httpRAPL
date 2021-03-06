package httpRaplServer;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import jRAPL.*;

/** Misc helper methods and things */
class Utils {

	/** Shows how many seconds and milliseconds, then counts up printing every second 
	 *	The overhead of looping and printing is gonna throw off the overall sleep time
	 *	by a few milliseconds (I think).
	*/
	static void sleep_print(int ms) {
		int seconds = (int)(ms/1000);
		int milliseconds = ms % 1000;
		System.out.println("sec: " + seconds + ", " + "msec: " + milliseconds);
		try {
			for (int s = 1; s <= seconds; s++) {
				Thread.sleep(1000);
				System.out.println(s);
			}
			Thread.sleep(milliseconds);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	static String[] diffListToJsonList(EnergyDiff[] objs) {
		String[] jsons = new String[objs.length];
		int i = 0; for (EnergyDiff e : objs) 
			jsons[i++] = Utils.toJSON(e);
		return jsons;
	}
	
	static String[] statsListToJsonList(EnergyStats[] objs) {
		String[] jsons = new String[objs.length];
		int i = 0; for (EnergyStats e : objs) 
			jsons[i++] = Utils.toJSON(e);
		return jsons;
	}

	static String toJSON(EnergyStats e) {
		return String.format("{\"dram\": %f, \"gpu\": %f, \"core\": %f, \"package\": %f, \"timestamp\": %d}", 
		e.getDram(), e.getGpu(), e.getCore(), e.getPackage(), e.getTimestamp().toEpochMilli() );
	}

	static String toJSON(EnergyDiff e) {
		return String.format("{\"dram\": %f, \"gpu\": %f, \"core\": %f, \"package\": %f, \"elapsedtime\": %d}",
		e.getDram(), e.getGpu(), e.getCore(), e.getPackage(), e.getElapsedTime().toMillis() );
	}

}
