package ArquivoBinario;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class ArquivoJava {

    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;
    private final int tamanho = 1024;

    public ArquivoJava(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e)
        { }
    }

    public void copiaArquivo(RandomAccessFile arquivoOrigem)
    {

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

                if (regA.getNumero() > reg.getNumero())
                {
                    seekArq(pos);
                    regA.gravaNoArq(arquivo);
                    flag = true;
                    pos--;
                }

            }

            seekArq(pos);
            reg.gravaNoArq(arquivo);
            i++;
        }

    }
    private int buscarBinariaArq(int chave, int TL)
    {
        int ini = 0, fim = TL - 1, meio = (ini + fim)/2;
        Registro regMeio = new Registro();
        seekArq(meio);
        regMeio.leDoArq(arquivo);
        while (ini < fim && regMeio.getNumero() != chave)
        {
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
        }
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
                j--;
            }

            seekArq(pos);
            regAux.gravaNoArq(arquivo);
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

                if (regA.getNumero() > regB.getNumero())
                {
                    seekArq(j);
                    regA.gravaNoArq(arquivo);

                    seekArq(i);
                    regB.gravaNoArq(arquivo);

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

                if (regA.getNumero() > regB.getNumero())
                {
                    seekArq(i);
                    regB.gravaNoArq(arquivo);

                    seekArq(i + 1);
                    regA.gravaNoArq(arquivo);
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
            int i = ini;
            while (i < fim)
            {
                seekArq(i);
                regA.leDoArq(arquivo);

                seekArq(i + 1);
                regB.leDoArq(arquivo);

                if (regA.getNumero() > regB.getNumero())
                {
                    seekArq(i);
                    regB.gravaNoArq(arquivo);

                    seekArq(i + 1);
                    regA.gravaNoArq(arquivo);
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

                    if (regA.getNumero() < regB.getNumero())
                    {
                        seekArq(i);
                        regB.gravaNoArq(arquivo);

                        seekArq(i - 1);
                        regA.gravaNoArq(arquivo);
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
                    if (regB.getNumero() > regA.getNumero())
                        maiorF = F1 + 1;

                }
                seekArq(maiorF);
                regMaiorF.leDoArq(arquivo);

                seekArq(pai);
                regPai.leDoArq(arquivo);
                if (regMaiorF.getNumero() > regPai.getNumero())
                {
                    seekArq(maiorF);
                    regPai.gravaNoArq(arquivo);

                    seekArq(pai);
                    regMaiorF.gravaNoArq(arquivo);

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

            seekArq(0);
            regB.gravaNoArq(arquivo);
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
                    if (regA.getNumero() < regB.getNumero())
                    {
                        seekArq(pos);
                        regB.gravaNoArq(arquivo);
                        pos = pos - dist;
                        flag = true;
                    }
                }
                seekArq(pos);
                regA.gravaNoArq(arquivo);
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

            if (pos != 0 && regA.getNumero() < regB.getNumero())
            {
                seekArq(pos);
                regB.gravaNoArq(arquivo);

                seekArq(pos - 1);
                regA.gravaNoArq(arquivo);

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

                while (i < j && regA.getNumero() <= regB.getNumero())
                {
                    i++;
                    seekArq(i);
                    regA.leDoArq(arquivo);

                }
            }
            else
            {
                while (i < j && regB.getNumero() >= regA.getNumero())
                {
                    j--;
                    seekArq(j);
                    regB.leDoArq(arquivo);

                }
            }

            if (i < j)
            {
                seekArq(j);
                regA.gravaNoArq(arquivo);
                seekArq(i);
                regB.gravaNoArq(arquivo);
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

            while (regA.getNumero() < pivo)
            {
                i++;
                seekArq(i);
                regA.leDoArq(arquivo);
            }


            while (regB.getNumero() > pivo)
            {
                j--;
                seekArq(j);
                regB.leDoArq(arquivo);
            }


            if (i <= j)
            {
                seekArq(j);
                regA.gravaNoArq(arquivo);
                seekArq(i);
                regB.gravaNoArq(arquivo);
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
                maior = reg.getNumero();
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
                pos++;
                j++;
            }
            i++;
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