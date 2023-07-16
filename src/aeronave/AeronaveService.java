package aeronave;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class AeronaveService {

	private final EntradaDeDados leitor = new EntradaDeDados();
	private final String DIGITE_OPCAO_DESEJADA = "Digite a opção desejada: ";
	private final String OPCAO_SAIR = "x";
	private final String OPCAO_CADASTRAR_EM_LOTE = "1";
	private final String OPCAO_LISTAR_FUNCIONARIOS = "2";
	private final String OPCAO_BUSCA_POR_CPF_CNPJ = "3";
	private final String OPCAO_BUSCA_POR_NOME_PROPRIETARIO = "4";
	private final String ORDERNAR = "5";
	private final String REMOVE_DUPLICADOS_NULOS = "6";
	private final String TOTAL_REGISTROS = "7";

	List<Aeronave> aeronaves = new ArrayList<>();

	private void iniciaApp() {
		carregaNomeApp();
	}

	private void carregaMenu() {
		iniciaApp();

		System.out.println("********  DIGITE A OPÇÃO DESEJADA   ******");
		pularLinha(1);

		System.out.println("1 - CADASTRO EM LOTE (CSV)");
		System.out.println("2 - LISTAR AERONAVES");
		System.out.println("3 - PESQUISAR POR CPF OU CNPJ DO PROPRIETARIO");
		System.out.println("4 - BUSCAR POR NOME DO PROPRIETARIO");
		System.out.println("5 - ORDERNAR LISTA");
		System.out.println("6 - REMOVE DUPLICADOS");
		System.out.println("7 - TOTAL REGISTROS");

		System.out.println("X - SAIR");
	}

	private void carregaNomeApp() {
		System.out.println("******************************************");
		System.out.println("*********** AERONAVES .CSV **************");
		System.out.println("******************************************");
	}

	private void finalizaApp() {
		System.out.println("Até logo!!");
	}

	private void opcaoInvalida() {
		System.out.println("Opção INVÁLIDA. Tente novamente.");
	}

	public void processar() {

		String opcaoDigitada = obterEntradaDoUsuario(leitor);

		while (!escolheuSair(opcaoDigitada)) {
			tratarOpcaoSelecionada(opcaoDigitada);
			opcaoDigitada = obterEntradaDoUsuario(leitor);
		}

		finalizaApp();

	}

	private String obterEntradaDoUsuario(EntradaDeDados leitor) {
		carregaMenu();
		System.out.print(DIGITE_OPCAO_DESEJADA);
		return leitor.obterEntrada().toLowerCase();
	}

	private boolean escolheuSair(String opcaoDigitada) {
		return opcaoDigitada.equals(OPCAO_SAIR);
	}

	private void tratarOpcaoSelecionada(String opcaoDigitada) {
		switch (opcaoDigitada) {
		case OPCAO_SAIR:
			break;
		case OPCAO_CADASTRAR_EM_LOTE:
			carregarAeronavesEmLote();
			pularLinha(2);

			break;
		case OPCAO_LISTAR_FUNCIONARIOS:
			listarAeronaves();
			pularLinha(2);
			break;

		case OPCAO_BUSCA_POR_CPF_CNPJ:
			buscaPorCpfCnpj();
			pularLinha(2);

			break;
		case OPCAO_BUSCA_POR_NOME_PROPRIETARIO:
			buscaPorNome();
			pularLinha(2);
			break;
		case ORDERNAR:
			ordernar(true);
			pularLinha(2);
			break;

		case REMOVE_DUPLICADOS_NULOS:
			remove();
			pularLinha(2);
			break;
		case TOTAL_REGISTROS:
			totalRegistro();
			pularLinha(2);
			break;
		default:
			opcaoInvalida();
			break;
		}
	}

	private void totalRegistro() {
		System.out.println("TOTAL REGISTRO = " + aeronaves.size());

	}

	private void remove() {
//		List<Aeronave> distinctElements = aeronaves.stream()
//				.filter(distinctByKey(p -> p.getProprietario() + " " + p.getProprietario()))
//				.collect(Collectors.toList());
//
//		aeronaves = distinctElements;

		Set<Aeronave> set = new HashSet<>(aeronaves);
		List<Aeronave> aero = new ArrayList<>(set);
		ordernar(false);
		aeronaves = aero;
		System.out.println("Removidos com sucesso!");
		// listarAeronaves(distinctElements);

	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	private void ordernar(boolean menu) {

		aeronaves.sort(Comparator.comparing(Aeronave::getProprietario));
		// List<Aeronave> listOrnada =aeronaves.stream().sorted((a1, a2) ->
		// a1.getProprietario().compareTo(a2.getProprietario())).collect(Collectors.toList());
		// listarAeronaves(aeronaves);
		if (menu)
			System.out.println("LISTA ORDERNADA COM SUCESSO!!");
	}

	private void buscaPorCpfCnpj() {

		List<Aeronave> listaAeronave = new ArrayList<>();
		System.out.print("Digite o cpf ou cnpj do proprietario da aeronave: ");
		String cpfCnpj = leitor.obterEntrada();
		for (Aeronave aeronave : aeronaves) {
			if (cpfCnpj.equals(aeronave.getCpfCNPJ()))
				listaAeronave.add(aeronave);
		}

		if (Objects.nonNull(listaAeronave))
			listarAeronaves(listaAeronave);
		else

			System.out.println("Nenhuma aeronave localizado para proproprietario : " + cpfCnpj);
	}

	private void buscaPorNome() {

		List<Aeronave> listaAeronave = new ArrayList<>();
		System.out.print("Digite o nome do proprietario da aeronave: ");
		String nome = leitor.obterEntrada();
		for (Aeronave aeronave : aeronaves) {
			if (nome.equals(aeronave.getProprietario()))
				listaAeronave.add(aeronave);
		}

		if (Objects.nonNull(listaAeronave))
			listarAeronaves(listaAeronave);
		else

			System.out.println("Nenhuma aeronave localizado para proproprietario : " + nome);
	}

	private void listarAeronaves() {
		StringBuilder sb = new StringBuilder();

		if (aeronaves.isEmpty()) {
			sb.append("[]");
		} else {
			sb.append("[\n");
			for (Aeronave aeronave : aeronaves) {
				sb.append("\t").append(aeronave).append(",\n");
			}
			sb.setLength(sb.length() - 2); // Remover a vírgula extra após o último funcionário
			sb.append("\n]");
		}

		System.out.println(sb);
	}

	private void listarAeronaves(List<Aeronave> list) {
		StringBuilder sb = new StringBuilder();
		pularLinha(2);

		if (list.isEmpty()) {
			sb.append("[]");
		} else {
			sb.append("[\n");
			for (Aeronave aeronave : list) {
				sb.append("\t").append(aeronave).append(",\n");
			}
			sb.setLength(sb.length() - 2); // Remover a vírgula extra após o último funcionário
			sb.append("\n]");
		}

		System.out.println(sb);
	}

	private void carregarAeronavesEmLote() {
		aeronaves = new CarregarDadosExternos().listAeronaves();

		// this.inserirFuncionario(removerDuplicados(novosFuncionarios));

	}

	public void pularLinha(int numeroDeLinhas) {
		for (int i = 1; i <= numeroDeLinhas; i++) {
			System.out.println();
		}
	}

}
