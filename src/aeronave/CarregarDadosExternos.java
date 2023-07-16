package aeronave;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CarregarDadosExternos {

	private static String CAMINHO_ARQUIVO = "src/resources/dados_aeronaves.csv";

	public List<Aeronave> listAeronaves() {

		List<Aeronave> aeronaves = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {

			br.readLine(); // Remover o cabeçalho

			String linha;
			while ((linha = br.readLine()) != null) {

				Aeronave aeronave = new Aeronave();

				
				String[] dados = linha.split(";");
				aeronave.setMarca(dados[0].trim());
				aeronave.setProprietario(dados[1].trim());
				aeronave.setOutroProprietario(dados[2].trim());
				aeronave.setSgUF(dados[3].trim());
				aeronave.setCpfCNPJ(dados[4].trim());
				aeronave.setNomeOperador(dados[5].trim());
				aeronave.setOperadorUF(dados[6].trim());
				aeronave.setCpfCGC(dados[7].trim());
				aeronave.setNumeroCertificadoMatricula(dados[8].trim());
				aeronave.setNumeroSerie(dados[9].trim());
				aeronave.setCdCategoria(dados[10].trim());
				aeronave.setCodigoTipo(dados[11].trim());
				aeronave.setDescricaoModelo(dados[12].trim());
				aeronave.setNomeFabrica(dados[13].trim());
				aeronave.setCodigoCLS(dados[14].trim());
				aeronave.setNumeroPMD(dados[15].trim());
				aeronave.setCodigoTipoIcao(dados[16].trim());
				aeronave.setNumeroTripulacao(dados[17].trim());
				aeronave.setNumeroPassageiros(dados[18].trim());
				aeronave.setNumeroAssentos(dados[19].trim());
				aeronave.setNumeroAno(dados[20].trim());
				aeronave.setDataValidadeCVA(dados[21].trim());
				aeronave.setDataValidadeCA(dados[22].trim());
				aeronave.setDataCancelamento(dados[23].trim());
				aeronave.setDescricaoMotivoCancelamento(dados[24].trim());
				aeronave.setCodigoInterno(dados[25].trim());
				aeronave.setCodigoMarcaNac1(dados[26].trim());
				aeronave.setCodigoMarcaNac2(dados[27].trim());
				aeronave.setCodigoMarcaNac3(dados[28].trim());
				aeronave.setCodigoMarcaEstrangeira(dados[29].trim());
				aeronave.setDescricaoGravame(dados[30].trim());
				
				System.out.println("Aeronave = "+ aeronave.getMarca()+"adicionado " );
				aeronaves.add(aeronave);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return aeronaves;

	}

}
