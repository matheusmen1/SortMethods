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
        while (i < TL - 1)
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

    public void geraArquivoOrdenado()
    {


    }

    public void geraArquivoReverso()
    {


    }

    public void geraArquivoRandomico()
    {
        Random random = new Random();
        int num;
        for (int i = 0; i < tamanho; i++)
        {
            num = random.nextInt(10) + 1;
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
            System.out.printf("%d ", aux.getNumero());
            aux.leDoArq(arquivo);
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