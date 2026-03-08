package ListaEncadeada;

public class PrincipalListaEncadeada
{

    public void rodarLista()
    {
       Lista lista = new Lista();
       lista.preencherLista();

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

    }


}