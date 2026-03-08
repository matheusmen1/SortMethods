import ArquivoBinario.PrincipalArquivoBinario;
import ListaEncadeada.PrincipalListaEncadeada;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner leitor = new Scanner(System.in);

        PrincipalListaEncadeada mainListaEncadeada = new PrincipalListaEncadeada();
        PrincipalArquivoBinario mainArquivoBinario = new PrincipalArquivoBinario();

        char op;
        do {
            System.out.println();
            System.out.println("[A] Rodar Métodos de Ordenação Em Lista Encadeada");
            System.out.println("[B] Gerar Tabela (Métodos de Ordenação Em Arquivo Binário)");
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
