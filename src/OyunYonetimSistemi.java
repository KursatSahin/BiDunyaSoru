import java.util.ArrayList;
import java.util.Queue;

public class OyunYonetimSistemi {
    // TODO - ArrayList yerine ülkeler Map'de tutulacak.
    ArrayList<Ulke> ulkeler; // Map kullanılabilir, Key ile ülke adları , Value ile soru listeleri tutulur
    ArrayList<Oyuncu> oyuncular;
    // TODO - ArrayList Soru Havuzu oluşturulacak
    // TODO - Queue<Soru> yerine PriorityQueue<Soru> kullanılacak
    Queue<Soru> sorular;
    Oyuncu oyuncu;

    public boolean kayitOl (String kullaniciadi, String sifre) {
        Oyuncu tmpOyuncu = new Oyuncu (kullaniciadi,sifre);

        return oyuncular.add(tmpOyuncu);
    }

    public Oyuncu girisYap(String kullaniciadi, String sifre){
        Oyuncu tmpOyuncu = new Oyuncu (kullaniciadi);

        if ( oyuncular.contains (tmpOyuncu) ) {
            if ( sifre.equals ((oyuncular.get (oyuncular.indexOf (tmpOyuncu))).getPassword ()) ){
                return oyuncular.get (oyuncular.indexOf (tmpOyuncu));
            } else {
                //System.out.println ("Incorrect password, Please try again!");
                return null;
            }
        } else {
            //System.out.println ("Username is invalid, Please register this user!");
            return null;
        }
    }

    void soruPaketiOlustur(){

    }

    int puanHesaplama(){

     return 0;
    }

    void oyunuBaslat(){

    }

    void ulkeSec(){

    }

    void oyunuKaydet(){

    }

    void oyunuYukle(){

    }

}
