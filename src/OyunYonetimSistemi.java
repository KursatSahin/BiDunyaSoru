import com.sun.source.tree.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

public class OyunYonetimSistemi {

    ArrayList<Oyuncu> oyuncuListesi;

    // TODO - ArrayList Soru Havuzu oluşturulacak
    ArrayList<Soru> soruHavuzu;

    BinarySearchTree puanTablosu; // birden fazla oyuncunun score larını saklamak için yazıldı

    Map<String,Ulke> ulkeler; // hangi ülkeye hangi soru paketi denk geliyorsa bunu saklıyoruz
    PriorityQueue<Soru> sorular; // ülkeye denk gelen soru paketi -> mevcut oyuncuya sorulacak sorular
    Oyuncu oyuncu; // hangi oyuncuda sıra olduğunu gösterir

    public boolean kayitOl (String kullaniciadi, String sifre) {

        Oyuncu tmpOyuncu = new Oyuncu (kullaniciadi,sifre);

        if (oyuncuListesi.contains(tmpOyuncu)) {
            return false;
        }

        return oyuncuListesi.add(tmpOyuncu);
    }

    public Oyuncu girisYap(String kullaniciadi, String sifre){
        Oyuncu tmpOyuncu = new Oyuncu (kullaniciadi);

        if ( oyuncuListesi.contains (tmpOyuncu) ) {
            if ( sifre.equals ((oyuncuListesi.get (oyuncuListesi.indexOf (tmpOyuncu))).getSifre()) ){
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

    void ulkeSec(int ulkeKodu){
        if(ulkeler.containsKey(ulkeKodu)){
            soruHavuzu = ulkeler.get(ulkeKodu).soruHavuzu;
        }
    }

    void oyunuKaydet(){

    }

    void oyunuYukle(){

    }

}
