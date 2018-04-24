import java.util.ArrayList;
import java.util.PriorityQueue;

public class Ulke implements Comparable {

    private int ulkeKodu;
    private String ulkeAdi;
    private String bayrak;
    private ArrayList<Soru> soruHavuzu;
    private PriorityQueue<Soru> soruListesi;
    private ArrayList<Ulke> komsular;

    public Ulke(){

    }
    public Ulke(String ulkeAdi){
        this.ulkeAdi = ulkeAdi;
    }

    public Ulke(int ulkeKodu, String ulkeAdi, String bayrak){
        this.ulkeKodu = ulkeKodu;
        this.ulkeAdi = ulkeAdi;
        this.bayrak = bayrak;
    }

    public int getUlkeKodu() {
        return ulkeKodu;
    }

    public void setUlkeKodu(int ulkeKodu) {
        this.ulkeKodu = ulkeKodu;
    }

    public String getUlkeAdi() {
        return ulkeAdi;
    }

    public void setUlkeAdi(String ulkeAdi) {
        this.ulkeAdi = ulkeAdi;
    }

    public String getBayrak() {
        return bayrak;
    }

    public void setBayrak(String bayrak) {
        this.bayrak = bayrak;
    }

    public ArrayList<Soru> getSoruHavuzu() {
        return soruHavuzu;
    }

    public void setSoruHavuzu(ArrayList<Soru> soruHavuzu) {
        this.soruHavuzu = soruHavuzu;
    }

    public PriorityQueue<Soru> getSoruListesi() {
        return soruListesi;
    }

    public void setSoruListesi(PriorityQueue<Soru> soruListesi) {
        this.soruListesi = soruListesi;
    }

    public ArrayList<Ulke> getKomsular() {
        return komsular;
    }

    public void setKomsular(ArrayList<Ulke> komsular) {
        this.komsular = komsular;
    }

    @Override
    public int compareTo(Object o) {
        if(ulkeKodu == (int)o)
            return 1;
        else
            return 0;
    }
}
