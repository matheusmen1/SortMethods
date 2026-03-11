package ArquivoBinario;

public class PrincipalArquivoBinario
{
    ArquivoJava arqRand, arqOrd, arqRev, auxRev, auxRand;

    public void gerarTabela()
    {
        arqRev = new ArquivoJava("arqRev.dat");
        arqRev.geraArquivoReverso();
        arqRev.exibirArq();
        arqRev.gnomeSortArq();
        arqRev.exibirArq();
    }
}
