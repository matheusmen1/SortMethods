import ArquivoBinario.PrincipalArquivoBinario;
import ListaEncadeada.PrincipalListaEncadeada;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException {
        Scanner leitor = new Scanner(System.in);

        PrincipalListaEncadeada mainListaEncadeada = new PrincipalListaEncadeada();
        PrincipalArquivoBinario mainArquivoBinario = new PrincipalArquivoBinario();

        char op;
        do {
            System.out.println("\n### MENU PRINCIPAL ###");
            System.out.println("[A] Lista Encadeada");
            System.out.println("[B] Arquivo Binario");
            System.out.println("[S] Sair");
            op = leitor.nextLine().toUpperCase().charAt(0);
            switch (op)
            {
                case 'A':
                    mainListaEncadeada.rodarLista();
                    break;
                case 'B':
                    mainArquivoBinario.gerarTabela();
                    break;
            }
        }while (op != 'S');
    }
}
