package ArquivoBinario;

import java.io.IOException;

public class PrincipalArquivoBinario
{

    ArquivoJava arqOrd = new ArquivoJava("arqOrd.dat");
    ArquivoJava arqRev = new ArquivoJava("arqRev.dat");
    ArquivoJava arqRand = new ArquivoJava("arqRand.dat");
    ArquivoJava auxRev = new ArquivoJava("tempRev.dat");
    ArquivoJava auxRand = new ArquivoJava("tempRand.dat");
    Tabela tabela = new Tabela("Resultados");
    long tini, tfim, ttotalO;
    double tseg;
    int compOrd, compEqOrd, movOrd, movEqOrd;
    int compRev, compEqRev, movRev, movEqRev;
    int compRand, compEqRand, movRand, movEqRand;
    long tempOrd, tempRev, tempRand;
    int n;
    public PrincipalArquivoBinario() throws IOException {}

    private void gravarLinhaTabela(String metodo) throws IOException {
        tabela.gravaLinha(metodo, compOrd, compEqOrd, movOrd, movEqOrd, tempOrd
                                , compRev, compEqRev, movRev, movEqRev, tempRev
                                , compRand, compEqRand, movRand, movEqRand, tempRand);
    }
    private void exibir(ArquivoJava arqAntes,ArquivoJava arq, String arquivo)
    {
        System.out.println("\n----- "+arquivo+" -----");
        arqAntes.exibirArq();
        arq.exibirArq();
        System.out.printf("Comp: %d\n", arq.getComp());
        System.out.printf("Mov: %d\n", arq.getMov());
        System.out.println("Seg: "+tseg);
    }
    public void gerarTabela() throws IOException {

        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();

        System.out.println("### INSERCAO DIRETA ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.insercaoDiretaArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = n - 1;
        movEqOrd = 3 * (n - 1);
        exibir(arqOrd,arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.insercaoDiretaArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = (n * n + n - 4)/4;
        movEqRev = (n * n + 3 * n - 4)/2;

        exibir(arqRev,auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.insercaoDiretaArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = (n * n + n - 2) / 4;
        movEqRand = (n * n + 9 * n - 10) / 4;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Inserção Direta");

        System.out.println("\n### INSERCAO BINÁRIA ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.insercaoBinariaArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = (int) (n * (Math.log(n) - Math.log(2.71828) + 0.5));
        movEqOrd = 3 * (n - 1);
        exibir(arqOrd,arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.insercaoBinariaArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = (int) (n * (Math.log(n) - Math.log(2.71828) + 0.5));
        movEqRev = (n * n + 3 * n - 4)/2;

        exibir(arqRev,auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.insercaoBinariaArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = (int) (n * (Math.log(n) - Math.log(2.71828) + 0.5));
        movEqRand = (n * n + 9 * n - 10) / 4;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Inserção Binária");

        System.out.println("\n### SELECAO DIRETA ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.selecaoDiretaArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = (n * n - n) / 2;
        movEqOrd = 3 * (n - 1);
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.selecaoDiretaArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = compEqOrd;
        movEqRev = n * n / 4 + 3 * (n - 1);

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.selecaoDiretaArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = compEqOrd;
        movEqRand = (int)(n * (Math.log(n) + 0.577216));
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Seleção Direta");

        System.out.println("\n### Bolha ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.bolhaSortArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = (n * n - n) / 2;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.bolhaSortArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = (n * n - n) / 2;
        movEqRev = 3 * (n * n - n) / 4;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.bolhaSortArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = (n * n - n) / 2;
        movEqRand = 3 * (n * n - n) / 2;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Bolha");

        System.out.println("\n### SHAKE ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.shakeSortArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = (n * n - n) / 2;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.shakeSortArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = (n * n - n) / 2;
        movEqRev = 3 * (n * n - n) / 4;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.shakeSortArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = (n * n - n) / 2;
        movEqRand = 3 * (n * n - n) / 2;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Shake");

        System.out.println("\n### SHELL ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.shellSortArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.shellSortArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.shellSortArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Shell");

        System.out.println("\n### HEAP ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.heapSortArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.heapSortArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.heapSortArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Heap");

        System.out.println("\n### QUICK S/PIVÔ ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.quickSortSemPivoArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.quickSortSemPivoArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.quickSortSemPivoArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Quick S/ PIVÔ");

        System.out.println("\n### QUICK C/PIVÔ ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.quickSortComPivoArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.quickSortComPivoArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.quickSortComPivoArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Quick C/ PIVÔ");

        System.out.println("\n### MERGE 1ª IMPLEMENT ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.mergePrimeiraImplementacaoArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.mergePrimeiraImplementacaoArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.mergePrimeiraImplementacaoArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Merge 1ª Implement");

        System.out.println("\n### MERGE 2ª IMPLEMENT ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.mergeSegundaImplementacaoArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.mergeSegundaImplementacaoArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.mergeSegundaImplementacaoArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Merge 2ª Implement");

        System.out.println("\n### COUNTING ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.countingSortArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.countingSortArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.countingSortArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Counting");

        System.out.println("\n### BUCKET ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.bucketSortArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.bucketSortArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.bucketSortArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Bucket");

        System.out.println("\n### RADIX ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.radixSortArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.radixSortArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.radixSortArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Radix");

        System.out.println("\n### COMB ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.combSortArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.combSortArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.combSortArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Comb");

        System.out.println("\n### GNOME ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.gnomeSortArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.gnomeSortArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.gnomeSortArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Gnome");

        System.out.println("\n### TIM ###");
        // ARQUIVO ORDENADO

        arqOrd.initComp();
        arqOrd.initMov();
        tini = System.currentTimeMillis();
        arqOrd.timSortArq();
        tfim = System.currentTimeMillis();
        compOrd = arqOrd.getComp();
        movOrd = arqOrd.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempOrd = (long) tseg;
        n = arqOrd.filesize();
        compEqOrd = 0;
        movEqOrd = 0;
        exibir(arqOrd, arqOrd, "Arquivo Ordenado");

        // ARQUIVO REVERSO
        auxRev.copiaArquivo(arqRev.getArquivo());

        auxRev.initComp();
        auxRev.initMov();
        tini = System.currentTimeMillis();
        auxRev.timSortArq();
        tfim = System.currentTimeMillis();
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRev = (long) tseg;
        n = auxRev.filesize();
        compEqRev = 0;
        movEqRev = 0;

        exibir(arqRev, auxRev, "Arquivo Reverso");

        // ARQUIVO RANDOMICO
        auxRand.copiaArquivo(arqRand.getArquivo());

        auxRand.initComp();
        auxRand.initMov();
        tini = System.currentTimeMillis();
        auxRand.timSortArq();
        tfim = System.currentTimeMillis();
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000;
        tempRand = (long) tseg;
        n = auxRand.filesize();
        compEqRand = 0;
        movEqRand = 0;
        exibir(arqRand,auxRand, "Arquivo Randômico");
        gravarLinhaTabela("Tim");

        tabela.fechar();
    }


}
