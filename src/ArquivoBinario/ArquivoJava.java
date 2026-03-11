package ArquivoBinario;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class ArquivoJava {

    private String nomearquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;
    private final int tamanho = 10;

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
    public int buscarBinariaArq(int chave, int TL)
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
        for(int i = 10; i > 0; i--)
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