package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.*;

/**
 * Classe principale per eseguire {@link myTest}
 * <p>
 *
 * Precondition: I metodi di Object devono essere correttamente funzionanti
 *
 * @version JUnit 4.13.2
 * @version Harmcrest: 1.3
 * @version JVM from JME CLDC 1.1
 *
 * @author Luca Mainardi
 */
public class TestRunner {
	private static int totalTests = 0;

	/**
	 * Metodo main, invoca tutti i test di {@link myTest.TestList} e segnala il
	 * numero di test eseguiti con successo e quello di eventuali test falliti
	 * 
	 * @param args - non usato
	 */
	public static void main(String[] args) {
		Result res;

		System.out.println("**Test in execution..**\n");

		System.out.println("\nTest of ListAdapter..");
		res = JUnitCore.runClasses(TestList.class);
		esitoTest(res);

		System.out.println("\n*** All the " + totalTests + " tests have been completed +**");
	}

	/**
	 * Stampa il risultato di ogni test suite
	 * <p>
	 * Per ogni suite viene indicato quanti test sono stati eseguiti e quanti sono
	 * falliti
	 * 
	 * @param res - risultato dell'invocazione della classe di test
	 */
	private static void esitoTest(Result res) {
		totalTests += res.getRunCount();
		System.out.print("Of " + res.getRunCount() + " tests ");
		if (res.wasSuccessful()) {
			System.out.println("all are with a positive result");
		} else {
			System.out.println("failed " + res.getFailureCount() + " tests");
			List<Failure> fails = res.getFailures();
			Iterator<Failure> iter = fails.iterator();
			while (iter.hasNext()) {
				System.out.println(iter.next().toString());
			}
		}
	}
}
