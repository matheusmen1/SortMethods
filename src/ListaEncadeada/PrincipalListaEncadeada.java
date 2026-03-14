package ListaEncadeada;

public class PrincipalListaEncadeada
{

    public void rodarLista()
    {
       Lista lista = new Lista();
       lista.preencherListaComRepeticao();

       System.out.println();
       System.out.println("### Metodos de Ordenacao ###");
       System.out.println();
       System.out.println("--- Insercao Direta ---- ");
       Lista L1 = lista.copiar(lista);
       L1.exibir();
       L1.insercaoDireta();
       L1.exibir();
       System.out.println("------------------------");
       System.out.println();
       System.out.println("--- Insercao Binaria --- ");
       Lista L2 = lista.copiar(lista);
       L2.exibir();
       L2.insercaoBinaria();
       L2.exibir();
       System.out.println("------------------------");
       System.out.println();
       System.out.println("---- Selecao Direta ---- ");
       Lista L3 = lista.copiar(lista);
       L3.exibir();
       L3.selecaoDireta();
       L3.exibir();
       System.out.println("------------------------");
       System.out.println();
       System.out.println("--------- Bolha --------");
       Lista L4 = lista.copiar(lista);
       L4.exibir();
       L4.bolhaSort();
       L4.exibir();
       System.out.println("------------------------");
       System.out.println();
       System.out.println("--------- Shake -------- ");
       Lista L5 = lista.copiar(lista);
       L5.exibir();
       L5.shakeSort();
       L5.exibir();
       System.out.println("------------------------");
       System.out.println();
       System.out.println("--------- Heap ---------");
       Lista L6 = lista.copiar(lista);
       L6.exibir();
       L6.heapSort();
       L6.exibir();
       System.out.println("------------------------");
       System.out.println();
       System.out.println("--------- Shell --------");
       Lista L7 = lista.copiar(lista);
       L7.exibir();
       L7.shellSort();
       L7.exibir();
       System.out.println("------------------------");
       System.out.println();
       System.out.println("--------- Gnome --------");
       Lista L8 = lista.copiar(lista);
       L8.exibir();
       L8.gnomeSort();
       L8.exibir();
       System.out.println("------------------------");
       System.out.println();
       System.out.println("---- Quick Sem Pivo ----");
       Lista L9 = lista.copiar(lista);
       L9.exibir();
       L9.quickSortSemPivo();
       L9.exibir();
       System.out.println("------------------------");
       System.out.println();
       System.out.println("---- Quick Com Pivo ----");
       Lista L10 = lista.copiar(lista);
       L10.exibir();
       L10.quickSortComPivo();
       L10.exibir();
       System.out.println("------------------------");
       System.out.println();
       System.out.println("------- Counting -------");
       Lista L11 = lista.copiar(lista);
       L11.exibir();
       L11.countingSort();
       L11.exibir();
       System.out.println("------------------------");
    }


}