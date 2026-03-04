package ListaEncadeada;

public class Lista
{
    private No fim;
    private No inicio;

    public Lista() {
    }

    public void inicializa()
    {
        this.fim = null;
        this.inicio = null;
    }

    public void inserirNoInicio(int info)
    {
        No no = new No(null, inicio, info);
        if(inicio == null)
        {
            inicio = no;
            fim = no;
        }
        else
        {
            inicio.setAnt(no);
            inicio = no;

        }
    }
    public void inserirNoFinal(int info)
    {
        No no = new No(fim, null, info);
        if (fim == null)
        {
            inicio = no;
            fim = no;
        }
        else
        {
            fim.setProx(no);
            fim = no;
        }
    }
    public No busca_exaustiva(int info)
    {
        No aux;
        aux = inicio;
        while(aux != null && aux.getInfo() != info)
            aux = aux.getProx();
        if (aux != null)
            return aux;
        return null;
    }
    public void exibir()
    {
        if (inicio == null)
            System.out.println("ListaEncadeada  ListaEncadeada.Lista Vazia");
        else
        {
            No aux;
            aux = inicio;
            while (aux != null)
            {
                System.out.println(aux.getInfo());
                aux = aux.getProx();
            }
        }
    }
    public void remover(int info)
    {
        No no = busca_exaustiva(info);
        if (no != null)
        {
            if (inicio == fim)
                inicio = fim = null;
            else
            if(inicio == no)
            {
                inicio = no.getProx();
                inicio.setAnt(null);
            }
            else
            if (fim == no)
            {
                fim = no.getAnt();
                fim.setProx(null);
            }
            else
            {
                no.getAnt().setProx(no.getProx());
                no.getProx().getAnt().setAnt(no.getAnt());

            }
            System.out.println("Elemento Excluido");
        }
        else
            System.out.println("Elemento Nao Encontrado");

    }
}
