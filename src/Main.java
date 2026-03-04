import ListaEncadeada.Lista;
import ListaEncadeada.No;

import java.util.Scanner;

public class Main
{

    private Lista lista;
    private Scanner leitor;

    public static void main(String[] args)
    {
        Main aplicacao = new Main();
        aplicacao.lista = new Lista();
        aplicacao.leitor = new Scanner(System.in);
        aplicacao.inserir();
        //aplicacao.buscar();
        aplicacao.removerElemento();
    }
    public void inserir()
    {
        lista.inicializa();
        System.out.println("Digite um numero para inserir na lista:");

        int num = leitor.nextInt();
        while(num != 0)
        {
            lista.inserirNoFinal(num);
            System.out.println("Digite um numero para inserir na lista:");
            leitor = new Scanner(System.in);
            num = leitor.nextInt();

        }
        lista.exibir();

    }
    public void buscar()
    {
        int chave;
        System.out.println("Digite um elemento para buscar:");
        chave = leitor.nextInt();
        No no = lista.busca_exaustiva(chave);
        if (no != null)
            System.out.println("Elemento Encontrado: "+no.getInfo());
        else
            System.out.println("Elemento Nao Encontrado");
    }
    public void removerElemento()
    {
        int chave;
        System.out.println("Digite um elemento para excluir:");
        chave = leitor.nextInt();
        lista.remover(chave);
        lista.exibir();
    }

}