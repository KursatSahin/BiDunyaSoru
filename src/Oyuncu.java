import java.util.ArrayList;
import java.util.Objects;

public class Oyuncu {

    private String kullaniciAdi;
    private String sifre;
    private int bakiye;
    private int toplamPuan;
    public ArrayList<Ulke> gecilenUlkeler;

    public Oyuncu(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = null;
        bakiye = 0;
        toplamPuan = 0;
        gecilenUlkeler = new ArrayList<>();
    }

    public Oyuncu(String kullaniciAdi, String sifre) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        bakiye = 0;
        toplamPuan = 0;
        gecilenUlkeler = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oyuncu)) return false;
        Oyuncu oyuncu = (Oyuncu) o;
        return Objects.equals(kullaniciAdi, oyuncu.kullaniciAdi) && Objects.equals(sifre, oyuncu.sifre);
    }
}
