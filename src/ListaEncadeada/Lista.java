package ListaEncadeada;

import java.util.Random;

public class Lista
{
    private No fim;
    private No inicio;

    public Lista() {
    }

    private void inicializa()
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
    private No busca_exaustiva(int info)
    {
        No aux;
        aux = inicio;
        while(aux != null && aux.getInfo() != info)
            aux = aux.getProx();
        if (aux != null)
            return aux;
        return null;
    }
    public void preencherLista()
    {
        Random random = new Random();
        inicializa();
        int num;
        int i = 0;
        No aux;
        num = random.nextInt(10) + 1;
        while (i < 10)
        {
            aux = busca_exaustiva(num);
            while (aux != null)
            {
                num = random.nextInt(10) + 1;
                aux = busca_exaustiva(num);

            }
            inserirNoFinal(num);
            i++;
        }

    }
    public Lista copiar(Lista lista)
    {
        Lista novaLista = new Lista();
        novaLista.inicializa();
        No aux = lista.inicio;
        while (aux != null)
        {
            novaLista.inserirNoFinal(aux.getInfo());
            aux = aux.getProx();
        }

        return novaLista;

    }
    // Métodos de Ordenação
    public void insercaoDireta()
    {
        No aux, pos;
        int auxInfo;
        aux = inicio.getProx();
        while (aux != null) {
            auxInfo = aux.getInfo();
            pos = aux;
            while (pos.getAnt() != null && pos.getAnt().getInfo() > auxInfo)
            {
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }
            pos.setInfo(auxInfo);
            aux = aux.getProx();
        }

    }
    private int contaNo()
    {
        int cont = 0;
        No aux = inicio;
        while (aux != null)
        {
            cont++;
            aux = aux.getProx();
        }
        return cont;

    }

    private No deslocarPonteiro(int pos)
    {
        No aux = inicio;
        int i = 0;
        while (i < pos)
        {
            aux = aux.getProx();
            i++;
        }
        return aux;
    }
    public int buscaBinaria(int chave, int TL)
    {
        int ini = 0, fim = TL - 1, meio = fim / 2;
        No noMeio;
        noMeio = deslocarPonteiro(meio);
        while (ini < fim && chave != noMeio.getInfo())
        {
            if (chave > noMeio.getInfo())
                ini = meio + 1;
            else
                fim = meio - 1;

            meio = (ini+fim)/2;
            noMeio = deslocarPonteiro(meio);
        }
        if (chave > noMeio.getInfo())
            return meio + 1;
        return meio;

    }
    public void insercaoBinaria()
    {
        int auxInfo, pos;
        No no;
        int TL = contaNo();
        int i = 1;
        while (i < TL)
        {
            no = deslocarPonteiro(i);
            auxInfo = no.getInfo();
            pos = buscaBinaria(auxInfo, i);
            int j = i;
            while (j > pos)
            {
                no.setInfo(no.getAnt().getInfo());
                no = no.getAnt();
                j--;
            }
            no = deslocarPonteiro(pos);
            no.setInfo(auxInfo);
            i++;
        }

    }
    public void selecaoDireta()
    {
        No pi = inicio;
        No pj;
        int aux;
        while (pi != null)
        {
            pj = pi.getProx();
            while (pj != null)
            {
                if (pi.getInfo() > pj.getInfo())
                {
                    aux = pi.getInfo();
                    pi.setInfo(pj.getInfo());
                    pj.setInfo(aux);
                }
                pj = pj.getProx();

            }
            pi = pi.getProx();
        }

    }
    public void bolhaSort()
    {
        No noFim = fim;
        No noIni;
        int auxInfo;
        boolean flag = true;
        while (noFim.getAnt() != null && flag)
        {
            flag = false;
            noIni = inicio;
            while (noIni != noFim)
            {
                if (noIni.getInfo() > noIni.getProx().getInfo())
                {
                    auxInfo = noIni.getInfo();
                    noIni.setInfo(noIni.getProx().getInfo());
                    noIni.getProx().setInfo(auxInfo);
                    flag = true;
                }
                noIni = noIni.getProx();

            }
            noFim = noFim.getAnt();
        }
    }
    public void shakeSort()
    {
        No noFim = fim;
        No noIni = inicio;
        No aux;
        int auxInfo;
        boolean flag = true;
        while (noIni != noFim && flag)
        {
            flag = false;
            aux = noIni;
            while (aux != noFim)
            {
                if (aux.getInfo() > aux.getProx().getInfo())
                {
                    auxInfo = aux.getInfo();
                    aux.setInfo(aux.getProx().getInfo());
                    aux.getProx().setInfo(auxInfo);
                    flag = true;
                }
                aux = aux.getProx();
            }
            if (flag)
            {
                flag = false;
                noFim = noFim.getAnt();
                aux = noFim;
                while (aux != noIni)
                {
                    if (aux.getInfo() < aux.getAnt().getInfo())
                    {
                        auxInfo = aux.getInfo();
                        aux.setInfo(aux.getAnt().getInfo());
                        aux.getAnt().setInfo(auxInfo);
                        flag = true;
                    }

                    aux = aux.getAnt();
                }

                noIni = noIni.getProx();
            }

        }

    }
    public void heapSort()
    {
        int TL2 = contaNo();
        int pai, F1, auxInfo, F2;
        No noIni = inicio;
        No noFim = fim;
        No noPai;
        No noF1;
        No noMaiorF;
        while (noFim.getAnt() != null)
        {
            pai = TL2 / 2 - 1;
            noPai = deslocarPonteiro(pai);
            while (noPai != null)
            {
                F1 = pai * 2 + 1;
                noF1 = deslocarPonteiro(F1);
                noMaiorF = noF1;
                F2 = F1 + 1;
                if (F2 < TL2 && deslocarPonteiro(F2).getInfo() > noF1.getInfo())
                    noMaiorF = deslocarPonteiro(F2);
                if (noMaiorF.getInfo() > noPai.getInfo())
                {
                    auxInfo = noMaiorF.getInfo();
                    noMaiorF.setInfo(noPai.getInfo());
                    noPai.setInfo(auxInfo);

                }
                noPai = noPai.getAnt();
                pai--;
            }

            auxInfo = noIni.getInfo();
            noIni.setInfo(noFim.getInfo());
            noFim.setInfo(auxInfo);

            noFim = noFim.getAnt();
            TL2--;
        }
    }
    public int calcDist(int n, int TL)
    {
        int dist = 1;
        while (dist < TL)
            dist = n * dist + 1;
        return dist / n;
    }
    public No deslocarPonteiroParaTras(No atual, int dist)
    {
        int i = 0;
        while (atual != null && i < dist)
        {
            atual = atual.getAnt();
            i++;
        }
        return atual;

    }
    public void shellSort()
    {
        int TL = contaNo(), auxInfo;
        int n = 3;
        int i, pos;
        int dist = calcDist(n, TL);
        No aux, noPos, noPosDist;
        while (dist > 0)
        {
            i = dist;
            while (i < TL)
            {
                aux = deslocarPonteiro(i);
                auxInfo = aux.getInfo();
                noPos = aux;
                pos = i;
                boolean flag = true;
                while (pos >= dist && flag)
                {
                    flag = false;
                    noPosDist = deslocarPonteiroParaTras(noPos, dist);
                    if (auxInfo < noPosDist.getInfo())
                    {
                        noPos.setInfo(noPosDist.getInfo());
                        noPos = noPosDist;
                        pos = pos - dist;
                        flag = true;
                    }

                }
                noPos.setInfo(auxInfo);
                i++;
            }
            dist = dist / n;
        }

    }

    public void gnomeSort()
    {
        No pos = inicio;
        int aux;
        while (pos != null)
        {
            if (pos != inicio && pos.getInfo() < pos.getAnt().getInfo())
            {
                aux = pos.getInfo();
                pos.setInfo(pos.getAnt().getInfo());
                pos.getAnt().setInfo(aux);

                pos = pos.getAnt();
            }
            else
                pos = pos.getProx();
        }

    }
    public void exibir()
    {
        if (inicio == null)
            System.out.println("Lista Vazia");
        else
        {
            No aux;
            aux = inicio;
            while (aux != null)
            {
                System.out.printf("%d ", aux.getInfo());
                aux = aux.getProx();
            }
        }
        System.out.println();
    }



}
