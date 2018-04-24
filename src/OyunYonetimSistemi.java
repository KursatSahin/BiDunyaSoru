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
    
        void puanTablosunaPuanEkle(){
        puanTablosu.add(oyuncu);
    }

    void ulkeOku() throws IOException {

        String line=null;
        String[] token=null;

        try {

            FileReader test = null;
            test = new FileReader("ulkeler.csv");
            BufferedReader countryReader = new BufferedReader(test);

            while ((line = countryReader.readLine()) != null) {

                token = line.split(";");

                Ulke ulke = new Ulke();

                ulke.setUlkeKodu(Integer.parseInt(token[0]));
                ulke.setUlkeAdi(token[1]);
                ulke.setBayrak(token[2]);

                for (int j = 3; j < token.length; j++) {
                    ulke.getKomsular().add(new Ulke(token[j]));
                }

                ulkeler.put(ulke.getUlkeAdi(), ulke);

                FileReader test2 = null;
                String path = new String(ulke.getUlkeAdi() + ".csv");
                test2 = new FileReader(path);

                BufferedReader qeustionReader = new BufferedReader(test2);

                token = null;
                line = null;

                while ((line = qeustionReader.readLine()) != null) {

                    Soru soru = new Soru();

                    token = line.split(";");

                    soru.setSoruMetni(token[0]);
                    soru.setSecenekA(token[1]);
                    soru.setSecenekB(token[2]);
                    soru.setSecenekC(token[3]);
                    soru.setSecenekD(token[4]);
                    soru.setDogruCevap(Integer.parseInt(token[5]));
                    soru.setPriority(Integer.parseInt(token[6]));

                    ulkeler.get(ulke.getUlkeAdi()).getSoruHavuzu().add(soru);
                    soru = null;
                }

                countryReader.close();
                qeustionReader.close();
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException ex) {
            System.out.println("IOException was detected!!");
        }
    }

}
