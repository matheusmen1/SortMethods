package ArquivoBinario;

public class PrincipalArquivoBinario
{
    ArquivoJava arqRand, arqOrd, arqRev, auxRev, auxRand;

    public void gerarTabela()
    {
        System.out.println("Arquivo Reverso");
        arqRev = new ArquivoJava("arqRev.dat");
        arqRev.geraArquivoReverso();
        arqRev.exibirArq();
        arqRev.countingSortArq();
        arqRev.exibirArq();
        System.out.println();
        System.out.println("Arquivo Randomico");
        arqRand = new ArquivoJava("arqRand.dat");
        arqRand.geraArquivoRandomico();
        arqRand.exibirArq();
        arqRand.countingSortArq();
        arqRand.exibirArq();
    }
}
