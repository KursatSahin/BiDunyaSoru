package grup2;

import java.util.ArrayList;

public class Ulke implements Comparable {
    public class Coordinate {
        private float latitude;
        private float longitude;

        public Coordinate(float latitude, float longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Coordinate() {
            this.latitude = 1;
            this.longitude = 1;
        }

        public float getLatitude() {
            return latitude;
        }

        public void setLatitude(float latitude) {
            this.latitude = latitude;
        }

        public float getLongitude() {
            return longitude;
        }

        public void setLongitude(float longitude) {
            this.longitude = longitude;
        }
    }

    private int ulkeKodu;
    private String ulkeAdi;
    private String bayrak;
    private Coordinate kordinatlar;
    private ArrayList<Soru> soruHavuzu;
    private ArrayList<String> komsular;

    public Ulke(String ulkeAdi){
        this.ulkeAdi = ulkeAdi;
        this.ulkeKodu=0;
        this.bayrak="";
        this.kordinatlar = new Coordinate();
        this.soruHavuzu = new ArrayList<>();
        this.komsular= new ArrayList<>();
    }

    public Ulke(){
        this.ulkeAdi = " ";
        this.ulkeKodu= 0;
        this.bayrak= " ";
        this.kordinatlar = new Coordinate();
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

    public Ulke(int ulkeKodu, String ulkeAdi, String bayrak, Coordinate kordinatlar){
        this.ulkeKodu = ulkeKodu;
        this.ulkeAdi = ulkeAdi;
        this.bayrak = bayrak;
        this.kordinatlar = kordinatlar;
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

    public Coordinate getKordinatlar() {
        return kordinatlar;
    }

    public void setKordinatlar(Coordinate kordinatlar) {
        this.kordinatlar = kordinatlar;
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