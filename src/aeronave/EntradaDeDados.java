package aeronave;

import java.util.Locale;
import java.util.Scanner;

public class EntradaDeDados implements AutoCloseable {

	private Scanner scanner;

	public EntradaDeDados() {
		scanner = new Scanner(System.in);
		scanner.useLocale(Locale.US);
	}

	public String obterEntrada() {
		return scanner.nextLine();
	}

	@Override
	public void close() {
		scanner.close();
	}
}
