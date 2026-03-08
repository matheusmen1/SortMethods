package ArquivoBinario;

public class PrincipalArquivoBinario
{
    ArquivoJava arqRand, arqOrd, arqRev, auxRev, auxRand;

    public void gerarTabela()
    {
        arqRand = new ArquivoJava("arqRand.dat");
        arqRand.geraArquivoRandomico();
        arqRand.exibirArq();
        arqRand.insercaoDiretaArq();
        arqRand.exibirArq();
    }
}
