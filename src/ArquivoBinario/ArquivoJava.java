package ArquivoBinario;
import ListaEncadeada.NoPilha;
import ListaEncadeada.Pilha;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class ArquivoJava {

    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;
    private int tamanho = 1024;

    public ArquivoJava(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile("arquivos/"+nomearquivo, "rw");
            this.nomearquivo = nomearquivo;
        } catch (IOException e)
        { }
    }

    public void copiaArquivo(RandomAccessFile arquivoOrigem)
    {
       try {

           arquivoOrigem.seek(0);
           this.truncate(0);
           Registro reg = new Registro();
           while (arquivoOrigem.getFilePointer() < arquivoOrigem.length())
           {
               reg.leDoArq(arquivoOrigem);
               reg.gravaNoArq(arquivo);
           }
       }catch (IOException e){}
    }


    public RandomAccessFile getArquivo() {
        return arquivo;
    }
    public void truncate(long pos) //desloca eof
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc)
        { }
    }

    public boolean eof()
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        } catch (IOException e)
        { }
        return (retorno);
    }

    public void seekArq(int pos)
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e)
        { }
    }

    public int filesize()
    {
        try{
            return (int) arquivo.length()/Registro.length();
        }catch (IOException e)
        {
            return 0;
        }
    }

    public void initComp()
    {
        this.comp = 0;
    }

    public void initMov()
    {
        this.mov = 0;

    }

    public int getComp()
    {
        return comp;
    }

    public int getMov()
    {
        return mov;
    }

    // Métodos de Ordenação

    public void insercaoDiretaArq()
    {
        int TL = filesize();
        int pos;
        int i = 1;
        Registro reg = new Registro();
        Registro regA = new Registro();
        while (i < TL)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            pos = i;
            boolean flag = true;
            while (pos > 0 && flag)
            {
                flag = false;
                seekArq(pos - 1);
                regA.leDoArq(arquivo);
                comp++;
                if (regA.getNumero() > reg.getNumero())
                {
                    seekArq(pos);
                    regA.gravaNoArq(arquivo);
                    mov++;
                    flag = true;
                    pos--;
                }

            }

            seekArq(pos);
            reg.gravaNoArq(arquivo);
            mov++;
            i++;
        }

    }
    private int buscarBinariaArq(int chave, int TL)
    {
        int ini = 0, fim = TL - 1, meio = (ini + fim)/2;
        Registro regMeio = new Registro();
        seekArq(meio);
        regMeio.leDoArq(arquivo);
        comp++;
        while (ini < fim && regMeio.getNumero() != chave)
        {
            comp++;
            if (chave > regMeio.getNumero())
            {
                ini = meio + 1;
            }
            else
            {
                fim = meio - 1;
            }
            meio = (ini + fim)/2;
            seekArq(meio);
            regMeio.leDoArq(arquivo);
            comp++;
        }
        comp++;
        if (chave > regMeio.getNumero())
            return meio + 1;
        return meio;

    }
    public void insercaoBinariaArq()
    {
        int TL = filesize();
        int i = 1;
        int pos, j;
        Registro regAux = new Registro();
        Registro reg = new Registro();
        while (i < TL)
        {
            seekArq(i);
            regAux.leDoArq(arquivo);
            pos = buscarBinariaArq(regAux.getNumero(), i);
            j = i;
            while (j > pos)
            {
                seekArq(j - 1);
                reg.leDoArq(arquivo);

                seekArq(j);
                reg.gravaNoArq(arquivo);
                mov++;
                j--;
            }

            seekArq(pos);
            regAux.gravaNoArq(arquivo);
            mov++;
            i++;
        }

    }

    public void selecaoDiretaArq()
    {
        Registro regA = new Registro();
        Registro regB = new Registro();
        int TL = filesize();
        for (int i = 0; i < TL - 1; i++)
        {
            for (int j = i + 1; j < TL; j++)
            {
                seekArq(i);
                regA.leDoArq(arquivo);

                seekArq(j);
                regB.leDoArq(arquivo);

                comp++;
                if (regA.getNumero() > regB.getNumero())
                {
                    seekArq(j);
                    regA.gravaNoArq(arquivo);
                    mov++;
                    seekArq(i);
                    regB.gravaNoArq(arquivo);
                    mov++;
                }


            }

        }

    }

    public void bolhaSortArq()
    {
        Registro regA = new Registro();
        Registro regB = new Registro();
        int TL2 = filesize();
        boolean flag = true;
        while (TL2 > 1 && flag)
        {
            flag = false;
            int i = 0;
            while (i < TL2 - 1)
            {
                seekArq(i);
                regA.leDoArq(arquivo);

                seekArq(i + 1);
                regB.leDoArq(arquivo);
                comp++;
                if (regA.getNumero() > regB.getNumero())
                {
                    seekArq(i);
                    regB.gravaNoArq(arquivo);
                    mov++;
                    seekArq(i + 1);
                    regA.gravaNoArq(arquivo);
                    mov++;
                    flag = true;
                }

                i++;
            }
            TL2--;

        }

    }
    public void shakeSortArq()
    {
        Registro regA = new Registro();
        Registro regB = new Registro();
        int ini = 0;
        int fim = filesize() - 1;
        boolean flag = true;
        while (ini < fim && flag)
        {
            flag = false;
            int i = ini;
            while (i < fim)
            {
                seekArq(i);
                regA.leDoArq(arquivo);

                seekArq(i + 1);
                regB.leDoArq(arquivo);
                comp++;
                if (regA.getNumero() > regB.getNumero())
                {
                    seekArq(i);
                    regB.gravaNoArq(arquivo);
                    mov++;
                    seekArq(i + 1);
                    regA.gravaNoArq(arquivo);
                    mov++;
                    flag = true;

                }
                i++;
            }
            if (flag)
            {
                fim--;
                i = fim;
                flag = false;
                while (i > ini)
                {
                    seekArq(i);
                    regA.leDoArq(arquivo);

                    seekArq(i - 1);
                    regB.leDoArq(arquivo);
                    comp++;
                    if (regA.getNumero() < regB.getNumero())
                    {
                        seekArq(i);
                        regB.gravaNoArq(arquivo);
                        mov++;
                        seekArq(i - 1);
                        regA.gravaNoArq(arquivo);
                        mov++;
                        flag = true;

                    }

                    i--;
                }
                ini++;

            }

        }

    }
    public void heapSortArq()
    {
        int TL = filesize();
        int pai, F1, maiorF;
        Registro regA = new Registro();
        Registro regB = new Registro();
        Registro regMaiorF = new Registro();
        Registro regPai = new Registro();
        while (TL > 1)
        {
            pai = TL/2 - 1;
            while (pai >= 0)
            {
                F1 = pai*2 + 1;
                maiorF = F1;
                if (F1 + 1 < TL)
                {
                    seekArq(F1);
                    regA.leDoArq(arquivo);

                    seekArq(F1 + 1);
                    regB.leDoArq(arquivo);
                    comp++;
                    if (regB.getNumero() > regA.getNumero())
                        maiorF = F1 + 1;

                }
                seekArq(maiorF);
                regMaiorF.leDoArq(arquivo);

                seekArq(pai);
                regPai.leDoArq(arquivo);
                comp++;
                if (regMaiorF.getNumero() > regPai.getNumero())
                {
                    seekArq(maiorF);
                    regPai.gravaNoArq(arquivo);
                    mov++;
                    seekArq(pai);
                    regMaiorF.gravaNoArq(arquivo);
                    mov++;
                }

                pai--;
            }

            TL--;
            seekArq(0);
            regA.leDoArq(arquivo);

            seekArq(TL);
            regB.leDoArq(arquivo);

            seekArq(TL);
            regA.gravaNoArq(arquivo);
            mov++;
            seekArq(0);
            regB.gravaNoArq(arquivo);
            mov++;
        }

    }
    private int calcDist(int n, int TL)
    {
        int dist = 1;
        while (dist < TL)
            dist = n * dist + 1;
        return dist / n;
    }
    public void shellSortArq()
    {
        int TL = filesize();
        int n = 3, i, pos;
        int dist = calcDist(n, TL);
        Registro regA = new Registro();
        Registro regB = new Registro();
        while (dist > 0)
        {
            i = dist;
            while (i < TL)
            {
                seekArq(i);
                regA.leDoArq(arquivo);
                pos = i;
                boolean flag = true;
                while (pos >= dist && flag)
                {
                    flag = false;
                    seekArq(pos - dist);
                    regB.leDoArq(arquivo);
                    comp++;
                    if (regA.getNumero() < regB.getNumero())
                    {
                        seekArq(pos);
                        regB.gravaNoArq(arquivo);
                        mov++;
                        pos = pos - dist;
                        flag = true;
                    }
                }
                seekArq(pos);
                regA.gravaNoArq(arquivo);
                mov++;
                i++;
            }
            dist = dist / n;
        }

    }
    public void gnomeSortArq()
    {
        int TL = filesize();
        int pos = 0;
        Registro regA = new Registro();
        Registro regB = new Registro();
        while (pos < TL)
        {
            seekArq(pos);
            regA.leDoArq(arquivo);

            seekArq(pos - 1);
            regB.leDoArq(arquivo);
            comp++;
            if (pos != 0 && regA.getNumero() < regB.getNumero())
            {
                seekArq(pos);
                regB.gravaNoArq(arquivo);
                mov++;
                seekArq(pos - 1);
                regA.gravaNoArq(arquivo);
                mov++;
                pos--;
            }
            else
                pos++;


        }
    }
    public void quickSortSemPivoArq()
    {
        quickSortSPArq(0,filesize() - 1);
    }

    private void quickSortSPArq(int ini, int fim)
    {
        int i = ini, j = fim;
        Registro regA = new Registro();
        Registro regB = new Registro();
        boolean flag = true;
        while (i < j)
        {
            seekArq(j);
            regB.leDoArq(arquivo);
            seekArq(i);
            regA.leDoArq(arquivo);

            if (flag)
            {
                comp++;
                while (i < j && regA.getNumero() <= regB.getNumero())
                {
                    i++;
                    seekArq(i);
                    regA.leDoArq(arquivo);
                    comp++;

                }
            }
            else
            {
                comp++;
                while (i < j && regB.getNumero() >= regA.getNumero())
                {
                    j--;
                    seekArq(j);
                    regB.leDoArq(arquivo);
                    comp++;

                }
            }

            if (i < j)
            {
                seekArq(j);
                regA.gravaNoArq(arquivo);
                mov++;
                seekArq(i);
                regB.gravaNoArq(arquivo);
                mov++;
                flag = !flag;
            }
        }

        if (ini < i - 1)
            quickSortSPArq(ini, i - 1);
        if (fim > j + 1)
            quickSortSPArq(j + 1, fim);
    }
    public void quickSortComPivoArq()
    {
        quickSortCPArq(0, filesize() - 1);
    }
    private void quickSortCPArq(int ini, int fim)
    {
        int i = ini, j = fim, meio = (ini + fim) / 2, pivo;
        Registro regA = new Registro();
        Registro regB = new Registro();
        seekArq(meio);
        regA.leDoArq(arquivo);
        pivo = regA.getNumero();
        while (i < j)
        {
            seekArq(i);
            regA.leDoArq(arquivo);
            seekArq(j);
            regB.leDoArq(arquivo);
            comp++;
            while (regA.getNumero() < pivo)
            {
                i++;
                seekArq(i);
                regA.leDoArq(arquivo);
                comp++;
            }

            comp++;
            while (regB.getNumero() > pivo)
            {
                j--;
                seekArq(j);
                regB.leDoArq(arquivo);
                comp++;
            }


            if (i <= j)
            {
                seekArq(j);
                regA.gravaNoArq(arquivo);
                mov++;
                seekArq(i);
                regB.gravaNoArq(arquivo);
                mov++;
                i++;
                j--;
            }
        }
        if (ini < j)
            quickSortCPArq(ini, j);
        if (fim > i)
            quickSortCPArq(i, fim);
    }
    private int acharMaior(int fim)
    {
        Registro reg = new Registro();
        int i = 0, maior = 0;
        while (i < fim)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            if (reg.getNumero() > maior)
            {
                maior = reg.getNumero();
            }
            i++;
        }
        return maior;

    }
    public void countingSortArq()
    {
        int TL = filesize(), aux, pos;
        int maior = acharMaior(TL);
        int[] vet = new int[maior + 1];
        Registro reg = new Registro();
        int i = 0;
        while (i < TL)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            vet[reg.getNumero()]++;
            i++;
        }
        i = 0;
        pos = 0;
        while (i < maior + 1)
        {
            aux = vet[i];
            int j = 0;
            while (j < aux)
            {
                reg.setNumero(i);
                seekArq(pos);
                reg.gravaNoArq(arquivo);
                mov++;
                pos++;
                j++;
            }
            i++;
        }
    }
    public void mergePrimeiraImplementacaoArq()
    {
        int TL = filesize();
        Registro[] vet1 = new Registro[TL/2];
        Registro[] vet2 = new Registro[TL/2];
        int seq = 1, i,j,k, tam_seq;
        while (seq < TL)
        {
            i = 0;
            while (i < TL/2)
            {
                seekArq(i);
                Registro regA = new Registro();
                regA.leDoArq(arquivo);

                seekArq((TL/2) + i);
                Registro regB = new Registro();
                regB.leDoArq(arquivo);
                vet1[i] = regA;
                vet2[i] = regB;
                i++;
            }
            i = 0; j = 0; k = 0;
            tam_seq = seq;
            while (k < TL)
            {
                while (i < tam_seq && j < tam_seq)
                {
                    comp++;
                    if (vet1[i].getNumero() < vet2[j].getNumero())
                    {
                        seekArq(k);
                        vet1[i].gravaNoArq(arquivo);
                        mov++;
                        k++;
                        i++;
                    }
                    else
                    {
                        seekArq(k);
                        vet2[j].gravaNoArq(arquivo);
                        mov++;
                        k++;
                        j++;
                    }

                }
                while (i < tam_seq)
                {
                    seekArq(k);
                    vet1[i].gravaNoArq(arquivo);
                    mov++;
                    k++;
                    i++;
                }
                while (j < tam_seq)
                {
                    seekArq(k);
                    vet2[j].gravaNoArq(arquivo);
                    mov++;
                    k++;
                    j++;
                }
                tam_seq = tam_seq + seq;
            }

            seq = seq * 2;

        }

    }
    public void mergeSegundaImplementacaoArq()
    {
        int TL = filesize();
        Pilha P1 = new Pilha();
        Pilha P2 = new Pilha();
        P1.inicializar();
        P2.inicializar();
        P1.push(0,TL - 1);
        NoPilha noPilha;
        int esq, dir, meio;
        while (!P1.isEmpty())
        {
            noPilha = P1.pop();
            esq = noPilha.getEsq();
            dir = noPilha.getDir();
            if (esq < dir)
            {
                meio = (esq + dir) / 2;
                P1.push(esq, meio);
                P1.push(meio + 1, dir);
                P2.push(esq, dir);

            }

        }
        while (!P2.isEmpty())
        {
            noPilha = P2.pop();
            esq = noPilha.getEsq();
            dir = noPilha.getDir();
            meio = (esq + dir) / 2;
            fusaoSegundaImplementacaoArq(esq, meio, meio + 1, dir, TL);

        }

    }

    private void fusaoSegundaImplementacaoArq(int ini1, int fim1, int ini2, int fim2, int TL)
    {
        int i = ini1, j = ini2, k = 0;
        Registro[] vet = new Registro[TL];
        while (i <= fim1 && j <= fim2)
        {
            seekArq(i);
            Registro regA = new Registro();
            regA.leDoArq(arquivo);

            seekArq(j);
            Registro regB = new Registro();
            regB.leDoArq(arquivo);
            comp++;
            if (regA.getNumero() < regB.getNumero())
            {
                vet[k++] = regA;
                i++;
            }
            else
            {
                vet[k++] = regB;
                j++;
            }
        }
        while (i <= fim1)
        {
            seekArq(i);
            Registro regA = new Registro();
            regA.leDoArq(arquivo);
            vet[k++] = regA;
            i++;
        }
        while (j <= fim2)
        {
            seekArq(j);
            Registro regB = new Registro();
            regB.leDoArq(arquivo);
            vet[k++] = regB;
            j++;
        }

        for (i = 0; i < k; i++)
        {
            seekArq(ini1+i);
            vet[i].gravaNoArq(arquivo);
            mov++;
        }
    }
    public void radixSortArq()
    {
        ArquivoJava[] baldes = new ArquivoJava[10]; // 0  a 9
        int TL = filesize();
        int maior = acharMaior(TL);
        int exp = 1, indice;
        Registro registro = new Registro();
        for(int i = 0; i < baldes.length; i++)
        {
            String nome = "arq" +i+".dat";
            baldes[i] = new ArquivoJava(nome);
            baldes[i].truncate(0);
        }
        while (maior / exp > 0)
        {
            int i = 0;
            while (i < TL)
            {
                seekArq(i);
                registro.leDoArq(arquivo);
                indice = (registro.getNumero()) / exp % 10;
                baldes[indice].inserirRegNoFinal(registro);
                mov++;
                i++;
            }
            i = 0;
            seekArq(0);
            while (i < 10)
            {
                int tam = baldes[i].filesize();
                int j = 0;
                while (j < tam)
                {
                    baldes[i].seekArq(j);
                    registro.leDoArq(baldes[i].arquivo);
                    registro.gravaNoArq(arquivo);
                    mov++;
                    j++;
                }
                baldes[i].truncate(0);
                i++;
            }

            exp = exp * 10;
        }


    }
    public void bucketSortArq()
    {
        int TL = filesize();
        int maior = acharMaior(TL);
        int normalizado, indice;
        ArquivoJava[] baldes = new ArquivoJava[10];
        Registro registro = new Registro();
        for(int i = 0; i < 10; i++)
        {
            String nome = "arq" +i+".dat";
            baldes[i] = new ArquivoJava(nome);
            baldes[i].truncate(0);
        }
        seekArq(0);
        int i = 0;
        while (i < TL)
        {
            registro.leDoArq(arquivo);
            normalizado = registro.getNumero() / (maior + 1);
            indice = TL * normalizado;
            baldes[indice].inserirRegNoFinal(registro);
            mov++;
            i++;
        }
        i = 0;
        while (i < baldes.length)
        {
            if (baldes[i].filesize() > 0)
            {
                baldes[i].insercaoDiretaArq();
                comp = comp + baldes[i].getComp();
            }
            i++;
        }
        i = 0;
        seekArq(0);
        while (i < baldes.length)
        {
            int tam = baldes[i].filesize();
            int j = 0;
            while (j < tam)
            {
                baldes[i].seekArq(j);
                registro.leDoArq(baldes[i].arquivo);
                registro.gravaNoArq(arquivo);
                mov++;
                j++;
            }
            i++;
        }
    }
    public void combSortArq()
    {
        int TL = filesize();
        int gap = TL;
        boolean flag = true;
        Registro regA = new Registro();
        Registro regB = new Registro();
        while (gap != 1 || flag)
        {
            gap = (int) (gap / 1.3);
            if (gap < 1)
                gap = 1;
            flag = false;
            seekArq(0);
            int i = 0;
            int auxGap = gap;
            while (auxGap < TL)
            {
                seekArq(i);
                regA.leDoArq(arquivo);

                seekArq(auxGap);
                regB.leDoArq(arquivo);
                comp++;
                if (regA.getNumero() > regB.getNumero())
                {
                    seekArq(i);
                    regB.gravaNoArq(arquivo);
                    mov++;
                    seekArq(auxGap);
                    regA.gravaNoArq(arquivo);
                    mov++;
                    flag = true;
                }
                i++;
                auxGap++;
            }

        }

    }
    private int calcMinRun(int n, int minRun)
    {
        int r = 0;
        while (n >= minRun)
        {
            r = r + (n % 2);
            n = n/2;
        }
        return n + r;
    }

    public void insercaoDiretaTimArq(int ini, int fim)
    {
        int pos;
        int i = ini + 1;
        Registro reg = new Registro();
        Registro regA = new Registro();
        while (i < fim + 1)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            pos = i;
            boolean flag = true;
            while (pos > ini && flag)
            {
                flag = false;

                seekArq(pos - 1);
                regA.leDoArq(arquivo);
                comp++;
                if (regA.getNumero() > reg.getNumero())
                {
                    seekArq(pos);
                    regA.gravaNoArq(arquivo);
                    mov++;
                    flag = true;
                    pos--;
                }

            }

            seekArq(pos);
            reg.gravaNoArq(arquivo);
            mov++;
            i++;
        }

    }
    private void mergeTimArq(int esq, int meio, int dir)
    {
        int TL1 = meio - esq + 1;
        int TL2 = dir - meio;
        Registro[] vet1 = new Registro[TL1];
        Registro[] vet2 = new Registro[TL2];

        for(int i = 0; i < TL1; i++)
        {
            Registro registro = new Registro();
            seekArq(i + esq);
            registro.leDoArq(arquivo);
            vet1[i] = registro;
        }
        for(int i = 0; i < TL2; i++)
        {
            Registro registro = new Registro();
            seekArq(i + meio + 1);
            registro.leDoArq(arquivo);
            vet2[i] = registro;
        }
        int k = 0, i = 0, j = 0;
        while (i < TL1 && j < TL2)
        {
            comp++;
            if (vet1[i].getNumero() < vet2[j].getNumero())
            {
                seekArq(esq + k);;
                vet1[i].gravaNoArq(arquivo);
                mov++;
                k++;
                i++;
            }
            else
            {
                seekArq(esq + k);
                vet2[j].gravaNoArq(arquivo);
                mov++;
                k++;
                j++;
            }
        }
        while (i < TL1)
        {
            seekArq(esq + k);;
            vet1[i].gravaNoArq(arquivo);
            mov++;
            k++;
            i++;
        }
        while (j < TL2)
        {
            seekArq(esq + k);
            vet2[j].gravaNoArq(arquivo);
            mov++;
            k++;
            j++;
        }
    }
    public void timSortArq()
    {
        int TL = filesize();
        int minRun = calcMinRun(TL, 32);
        int i = 0;
        int fim;
        while (i < TL)
        {
            if (i + minRun - 1 < TL - 1)
            {
                fim = i + minRun - 1;
            }
            else
            {
                fim = TL - 1;
            }
            insercaoDiretaTimArq(i, fim);
            i = i + minRun;
        }
        int tamanho = minRun, dir, meio;
        while (tamanho < TL)
        {
            for (int esq = 0; esq < TL; esq += 2 * tamanho)
            {
                if (esq + tamanho - 1 < TL - 1)
                    meio = esq + tamanho - 1;
                else
                    meio = TL - 1;

                if (esq + 2 * tamanho - 1 < TL - 1)
                    dir = esq + 2 * tamanho - 1;
                else
                    dir = TL - 1;
                if (meio < dir)
                    mergeTimArq(esq, meio, dir);

            }
            tamanho = tamanho * 2;
        }
    }

    public void geraArquivoOrdenado()
    {
        for(int i = 0; i < tamanho; i++)
        {
            Registro reg = new Registro(i);
            seekArq(i);
            reg.gravaNoArq(arquivo);

        }

    }
    public void geraArquivoReverso()
    {
        int j = 0;
        for(int i = tamanho; i > 0; i--)
        {
            Registro reg = new Registro(i);
            seekArq(j);
            reg.gravaNoArq(arquivo);
            j++;
        }

    }

    public void geraArquivoRandomico()
    {
        Random random = new Random();
        int num;
        for (int i = 0; i < tamanho; i++)
        {
            num = random.nextInt(tamanho) + 1;
            Registro reg = new Registro(num);
            seekArq(i);
            reg.gravaNoArq(arquivo);
        }
    }

    public void inserirRegNoFinal(Registro reg)
    {
        seekArq(filesize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }

    public void exibirArq()
    {
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof())
        {
            aux.leDoArq(arquivo);
            System.out.printf("%d ", aux.getNumero());
            i++;
        }
        System.out.println();
    }

    public void exibirUmRegistro(int pos)
    {
        Registro aux = new Registro();
        seekArq(pos);
        System.out.println("Posicao " + pos);
        aux.leDoArq(arquivo);
    }


}