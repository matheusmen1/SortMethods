package ListaEncadeada;

public class Pilha
{
    private NoPilha topo;

    public Pilha()
    {

    }

    public void inicializar()
    {
        topo = null;
    }
    public void push(int esq, int dir)
    {
        NoPilha noPilha = new NoPilha(esq, dir, null);
        if (topo == null)
            topo = noPilha;
        else
        {
            noPilha.setProx(topo);
            topo = noPilha;
        }
    }
    public NoPilha pop()
    {
        NoPilha noPilha = null;
        if (topo != null)
        {
            noPilha = topo;
            topo = topo.getProx();

        }
        return noPilha;
    }
    public boolean isEmpty()
    {
        return topo == null;
    }
}
