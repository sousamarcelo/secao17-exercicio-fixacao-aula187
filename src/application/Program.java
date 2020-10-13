package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Produto;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Produto> lista = new ArrayList<Produto>();
		Produto produto = null;

		System.out.print("Digite o caminho do arquivo para leitura: ");
		String caminho = sc.nextLine();

		File file = new File(caminho);		
		
		Boolean sucesso = new File(file.getParent() + "\\out").mkdir();
		System.out.println("pasta criada com sucesso" + sucesso);

		File file2 = new File(file.getParent() + "\\out\\sumary.csv");

		System.out.println();

		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

			String line = br.readLine();
			while (line != null) {
				// System.out.println(line);
				String[] vect = line.split(";");
				String nome = vect[0];
				Double preco = Double.parseDouble(vect[1]);
				Integer quantidade = Integer.parseInt(vect[2]);
				produto = new Produto(nome, preco, quantidade);
				lista.add(produto);
				line = br.readLine();

				try (BufferedWriter bw = new BufferedWriter(new FileWriter(file2, true))) {
					for (Produto pr : lista) {
						bw.write(pr.toString() + "\n");
						System.out.println();
					}

				} catch (Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}
			}

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}

		sc.close();

	}

}
