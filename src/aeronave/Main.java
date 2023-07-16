package aeronave;

public class Main {

	public static void main(String[] args) {
		try(EntradaDeDados leitor = new EntradaDeDados()) {
            new AeronaveService().processar();
        }
	}

}
