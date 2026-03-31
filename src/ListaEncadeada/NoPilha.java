package ListaEncadeada;

public class NoPilha
{
    private int esq;
    private int dir;
    private NoPilha prox;

    public NoPilha(int esq, int dir, NoPilha prox) {
        this.esq = esq;
        this.dir = dir;
        this.prox = prox;
    }

    public int getEsq() {
        return esq;
    }

    public void setEsq(int esq) {
        this.esq = esq;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public NoPilha getProx() {
        return prox;
    }

    public void setProx(NoPilha prox) {
        this.prox = prox;
    }
}
