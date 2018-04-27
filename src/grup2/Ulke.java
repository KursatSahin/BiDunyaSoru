package grup2;

import java.util.ArrayList;

public class Ulke implements Comparable {

    private int ulkeKodu;
    private String ulkeAdi;
    private String bayrak;
    private ArrayList<Soru> soruHavuzu;
    private ArrayList<String> komsular;

    public Ulke(String ulkeAdi){
        this.ulkeAdi = ulkeAdi;
        this.ulkeKodu=0;
        this.bayrak="";
        this.soruHavuzu = new ArrayList<>();
        this.komsular= new ArrayList<>();
    }

    public Ulke(){
        this.ulkeAdi = " ";
        this.ulkeKodu= 0;
        this.bayrak= " ";
        this.soruHavuzu = new ArrayList<>();
        this.komsular= new ArrayList<>();
    }

    public Ulke(int ulkeKodu, String ulkeAdi, String bayrak){
        this.ulkeKodu = ulkeKodu;
        this.ulkeAdi = ulkeAdi;
        this.bayrak = bayrak;
        this.soruHavuzu = new ArrayList<>();
        this.komsular= new ArrayList<>();
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

    public ArrayList<String> getKomsular() {
        return komsular;
    }

    public void setKomsular(ArrayList<String> komsular) {
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