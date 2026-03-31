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

    private void inserirNoInicio(int info)
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
    private void inserirNoFinal(int info)
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
        num = random.nextInt(16);
        while (i < 16) // multiplo de 2
        {
            aux = busca_exaustiva(num);
            while (aux != null)
            {
                num = random.nextInt(16);
                aux = busca_exaustiva(num);

            }
            inserirNoFinal(num);
            i++;
        }

    }
    public void preencherListaComRepeticao()
    {
        Random random = new Random();
        inicializa();
        int num;
        int i = 0;
        while (i < 16) // multiplo de 2
        {
            num = random.nextInt(16);
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
    private int buscaBinaria(int chave, int TL)
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
    private int calcDist(int n, int TL)
    {
        int dist = 1;
        while (dist < TL)
            dist = n * dist + 1;
        return dist / n;
    }
    private No deslocarPonteiroParaTras(No atual, int dist)
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
    public void quickSortSemPivo()
    {
        quickSortSP(inicio, fim);
    }
    private void quickSortSP(No ini, No fim)
    {
        No pi = ini, pj = fim;
        int aux;
        while (pi != pj)
        {
            while (pi != pj && pi.getInfo() <= pj.getInfo())
                pi = pi.getProx();
            aux = pi.getInfo();
            pi.setInfo(pj.getInfo());
            pj.setInfo(aux);

            while (pj != pi && pj.getInfo() >= pi.getInfo())
                pj = pj.getAnt();

            aux = pi.getInfo();
            pi.setInfo(pj.getInfo());
            pj.setInfo(aux);

        }
        if (ini != pi && ini != pi.getAnt())
            quickSortSP(ini, pi.getAnt());

        if (fim != pj && fim != pj.getProx())
            quickSortSP(pj.getProx(), fim);
    }
    private No deslocarPonteiroParaFrente(No ini, int pos)
    {
        No aux = ini;
        int i = 0;
        while (aux != null && i < pos)
        {
            aux = aux.getProx();
            i++;
        }
        return aux;
    }
    private int contarIntervalo(No ini, No fim)
    {
        No aux = ini;
        int cont = 0;
        while (aux != fim)
        {
            aux = aux.getProx();
            cont++;
        }
        return cont;
    }
    private int obterPivoMeio(No ini, No fim)
    {
        int pos = contarIntervalo(ini, fim);
        return deslocarPonteiroParaFrente(ini, pos /2).getInfo();
    }
    private int achaPos(No no)
    {
        int cont = 0;
        No aux = inicio;
        while (aux != no)
        {
            cont++;
            aux = aux.getProx();
        }
        return cont;
    }
    public void quickSortComPivo()
    {
        quickSortCP(inicio, fim);
    }
    private void quickSortCP(No noIni, No noFim)
    {
        No pi = noIni, pj = noFim;
        int i = achaPos(noIni), j = achaPos(noFim), ini = i, fim = j;
        int pivo = obterPivoMeio(noIni, noFim), aux;
        while (i < j)
        {
            while (pi.getInfo() < pivo)
            {
                pi = pi.getProx();
                i++;
            }

            while (pj.getInfo() > pivo)
            {
                pj = pj.getAnt();
                j--;
            }

            if (i <= j)
            {
                aux = pi.getInfo();
                pi.setInfo(pj.getInfo());
                pj.setInfo(aux);

                pi = pi.getProx();
                pj = pj.getAnt();

                i++;
                j--;

            }

        }

        if (ini < j)
            quickSortCP(noIni, pj);

        if (fim > i)
            quickSortCP(pi, noFim);

    }

    private int acharMaior(No inicio)
    {
        No aux = inicio;
        int maior = 0;
        while (aux != null)
        {
            if (aux.getInfo() > maior)
                maior = aux.getInfo();
            aux = aux.getProx();
        }
        return maior;
    }
    public void countingSort()
    {
        int maior = acharMaior(inicio), auxInfo, i, j;
        No ini;
        int[] vet = new int[maior + 1];
        ini = inicio;
        while (ini != null)
        {
            vet[ini.getInfo()]++;
            ini = ini.getProx();
        }
        ini = inicio;
        i = 0;
        while (ini != null)
        {
           auxInfo = vet[i];
           j = 0;
           while (j < auxInfo)
           {
               ini.setInfo(i);
               ini = ini.getProx();
               j++;
           }
           i++;
        }
    }
    public void mergeSortPrimeiraImplementacao()
    {
        int seq = 1, TL = contaNo(), metade = TL / 2, i, j, aux, auxSeq;
        int[] vet1 = new int[metade];
        int[] vet2 = new int[metade];
        No ini, meio, pk;
        while (seq < TL)
        {
            i = 0;
            ini = inicio;
            meio = deslocarPonteiroParaFrente(ini, metade);
            while (i < metade)
            {
                vet1[i] = ini.getInfo();
                vet2[i] = meio.getInfo();
                ini = ini.getProx();
                meio = meio.getProx();
                i++;
            }
            i = 0;
            j = 0;
            auxSeq = seq;
            pk = inicio;
            while (pk != null)
            {
                while (i < auxSeq && j < auxSeq)
                {
                    if (vet1[i] < vet2[j])
                        aux = vet1[i++];
                    else
                        aux = vet2[j++];


                    pk.setInfo(aux);
                    pk = pk.getProx();
                }
                while (i < auxSeq)
                {
                    pk.setInfo(vet1[i++]);
                    pk = pk.getProx();
                }
                while (j < auxSeq)
                {
                    pk.setInfo(vet2[j++]);
                    pk = pk.getProx();
                }
                auxSeq = auxSeq + seq;
            }
            seq = seq * 2;
        }

    }
    public void bucketSort()
    {
        int TL = contaNo(), maior = acharMaior(inicio), normalizado, indice;
        Lista[] baldes = new Lista[TL];
        for(int i = 0; i < TL; i++)
            baldes[i] = new Lista();

        No aux = inicio;
        while (aux != null)
        {
            normalizado = aux.getInfo() / (maior + 1);
            indice = TL * normalizado;
            baldes[indice].inserirNoFinal(aux.getInfo());
            aux = aux.getProx();
        }
        int i = 0;
        while (i < TL)
        {
            if (baldes[i].inicio != null)
                baldes[i].insercaoDireta();
            i++;
        }
        i = 0;
        aux = inicio;
        while (i < TL)
        {
            No noBalde;
            noBalde = baldes[i].inicio;
            while (noBalde != null)
            {
                aux.setInfo(noBalde.getInfo());
                aux = aux.getProx();
                noBalde = noBalde.getProx();
            }
            i++;
        }

    }
    public void radixSort()
    {
        Lista[] baldes = new Lista[10];
        int maior = acharMaior(inicio);
        int exp = 1, indice, info;
        No aux, no;
        for (int i = 0; i < baldes.length; i++)
            baldes[i] = new Lista();
        while (maior / exp > 0)
        {
            aux = inicio;
            while (aux != null)
            {
                indice = (aux.getInfo() / exp ) % 10;
                baldes[indice].inserirNoFinal(aux.getInfo());
                aux = aux.getProx();
            }
            int i = 0;
            aux = inicio;
            while (i < 10)
            {
                no = baldes[i].inicio;
                while (no != null)
                {
                    info = no.getInfo();
                    aux.setInfo(info);
                    aux = aux.getProx();
                    no = no.getProx();
                }
                baldes[i].inicio = baldes[i].fim = null;
                i++;
            }
            exp = exp * 10;
        }
    }
    public void mergeSortSegundaImplementacao()
    {
        Pilha pilha = new Pilha();
        pilha.inicializar();
        Pilha pilha2 = new Pilha();
        pilha2.inicializar();
        int TL = contaNo(),esq, dir, meio;
        pilha.push(0, TL - 1);
        NoPilha noPilha;
        while (!pilha.isEmpty())
        {
            noPilha = pilha.pop();
            esq = noPilha.getEsq();
            dir = noPilha.getDir();
            if (esq < dir)
            {
                meio = (esq + dir) / 2;
                pilha.push(esq, meio);
                pilha.push(meio + 1, dir);
                pilha2.push(esq, dir);

            }
        }
        while (!pilha2.isEmpty())
        {
            noPilha = pilha2.pop();
            esq = noPilha.getEsq();
            dir = noPilha.getDir();
            meio = (esq + dir) / 2;
            fusaoSegundaImplementacao(esq, meio, meio + 1, dir, TL);
        }
    }

    private void fusaoSegundaImplementacao(int ini1, int fim1, int ini2, int fim2, int TL)
    {
        int i = ini1, j = ini2, k = 0;
        int[] vet = new int[TL];
        No pi = deslocarPonteiroParaFrente(inicio, ini1);
        No pj = deslocarPonteiroParaFrente(inicio, ini2);
        No pk = deslocarPonteiroParaFrente(inicio, ini1);
        while (i <= fim1 && j <= fim2)
        {
            if (pi.getInfo() < pj.getInfo())
            {
                vet[k++] = pi.getInfo();
                pi = pi.getProx();
                i++;
            }
            else
            {
                vet[k++] = pj.getInfo();
                pj = pj.getProx();
                j++;
            }

        }
        while (i <= fim1)
        {
            vet[k++] = pi.getInfo();
            pi = pi.getProx();
            i++;
        }
        while (j <= fim2)
        {
            vet[k++] = pj.getInfo();
            pj = pj.getProx();
            j++;
        }
        for (i = 0; i < k; i++)
        {
            pk.setInfo(vet[i]);
            pk = pk.getProx();
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
