public abstract class Soru implements PuanHesaplayıcı {
    String soruMetni;

    String secenekA;
    String secenekB;
    String secenekC;
    String secenekD;

    int dogruCevap;

    int priority;

    @Override
    public int puanHesapla() {
        return 5 * priority;
    }
}
