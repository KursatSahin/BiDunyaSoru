import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

public class OyunYonetimSistemi {
    // TODO - ArrayList yerine ülkeler Map'de tutulacak.
    //ArrayList<Ulke> ulkeler; // Map kullanılabilir, Key ile ülke adları , Value ile soru listeleri tutulur
    ArrayList<Oyuncu> oyuncuListesi;
    // TODO - ArrayList Soru Havuzu oluşturulacak
    // TODO - Queue<Soru> yerine PriorityQueue<Soru> kullanılacak
    
    Map<Ulke,List> ulkeler; // hangi ülkeye hangi soru paketi denk geliyorsa bunu saklıyoruz
    Queue<Soru> sorular; // ülkeye denk gelen soru paketi
    Oyuncu oyuncu; // hangi oyuncuda sıra olduğunu gösterir

    public boolean kayitOl (String kullaniciadi, String sifre) {
        Oyuncu tmpOyuncu = new Oyuncu (kullaniciadi,sifre);

        return oyuncuListesi.add(tmpOyuncu);
    }

    public Oyuncu girisYap(String kullaniciadi, String sifre){
        Oyuncu tmpOyuncu = new Oyuncu (kullaniciadi);

        if ( oyuncuListesi.contains (tmpOyuncu) ) {
            if ( sifre.equals ((oyuncuListesi.get (oyuncuListesi.indexOf (tmpOyuncu))).getPassword ()) ){
                return oyuncuListesi.get (oyuncuListesi.indexOf (tmpOyuncu));
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
