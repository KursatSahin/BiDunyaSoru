package grup2;

import java.io.*;
import java.util.*;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class OyunYonetimSistemi {

    ArrayList<Oyuncu> oyuncuListesi;
    ArrayList<Soru> soruHavuzu;
    BinarySearchTree puanTablosu; // birden fazla oyuncunun score larını saklamak için yazıldı
    Map<String,Ulke> ulkeler; // hangi ülkeye hangi soru paketi denk geliyorsa bunu saklıyoruz
    PriorityQueue<Soru> sorular; // ülkeye denk gelen soru paketi -> mevcut oyuncuya sorulacak sorular
    Oyuncu oyuncu; // hangi oyuncuda sıra olduğunu gösterir
    Ulke simdikiUlke;

    public OyunYonetimSistemi() {
        this.oyuncuListesi = new ArrayList<>();
        this.soruHavuzu =  new ArrayList<>();
        this.puanTablosu = new BinarySearchTree();
        this.ulkeler = new HashMap();
        this.sorular = new PriorityQueue<>();
    }

    /**
     * Kullanicinin girdigi kullanici adi daha onceden kullanilmamis ise, girilen kullanici adi
     * ve sifre kombinasyonu ile bir kullanici kaydi olusturulur.
     *
     * @param kullaniciadi - Kullanici adi
     * @param sifre - Kullanici sifresi
     * @return - Eger boyle bir kullanici kaydi daha once olusturulmamissa kullanici olusturulur ve True return edilir
     *           Kullanici kaydi daha once olusturulmussa False return edilir.
     */
    public boolean kayitOl (String kullaniciadi, String sifre) {

        Oyuncu tmpOyuncu = new Oyuncu (kullaniciadi,sifre);

        if (oyuncuListesi.contains(tmpOyuncu)) {
            return false;
        }

        return oyuncuListesi.add(tmpOyuncu);
    }

    /**
     * Kullanicinin girdigi kullanici adi ve sifre bilgilerini kontrol ederek giris
     * yapilip yapilamayacagini kotrol eder.
     *
     * @param kullaniciadi - Kullanici adi
     * @param sifre - Kullanici sifresi
     * @return - Eger kullanici bilgileri dogru ise True, yanlis ise False return edilir
     */
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

    // This method deprecated after creating json file
    @Deprecated
    public void setOyuncuListesi(ArrayList<Oyuncu> oyuncuListesi){
        this.oyuncuListesi = oyuncuListesi;
    }

    // TODO - Guncellenecek..
    void soruPaketiOlustur(int sorusayisi){

        int index;

        Set<Soru> soru = new HashSet<Soru>();

        Random rand = new Random();


        while(soru.size() < sorusayisi){
            index = rand.nextInt(soruHavuzu.size()-1);

            soru.add(soruHavuzu.get(index));
        }

        Iterator iter = soru.iterator();

        while(iter.hasNext()){
            sorular.add((Soru)iter.next());
        }
    }

    // TODO - Guncellenebilir !?
    int puanHesaplama(Soru soru,int cevap){

        if(soru.getDogruCevap() == cevap)
            return soru.getPriority()*5;
        else
            return 0;
    }

    // TODO - Guncellenecek..
    void oyunuBaslat(){

    }

    /**
     * Kullanicinin sectigi ulkeyi simdiki ulke olarak atar.
     * Ayrica simdiki ulkenin sorularini kullanilacak olan soru havuzuna tasir.
     *
     * @param ulkeadi - Kullanicinin sectigi ulkenini adi
     */
    void ulkeSec(String ulkeadi){

        if(ulkeler.containsKey(ulkeadi)){
            simdikiUlke = ulkeler.get(ulkeadi);
            soruHavuzu = ulkeler.get(ulkeadi).getSoruHavuzu();
        }
    }

    /**
     * Guncellenen oyun bilgilerini dosyaya kadeder
     */
    void oyunuKaydet(){
        writeUsersIntoJson();
    }

    /**
     * Gecmis oyun bilgilerini dosyadan yukler
     */
    void oyunuYukle(){
        try {
            ulkeOku();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOyuncuListesi(readUsersFromJson());
    }

    // TODO - Guncellenecek..
    void puanTablosunaPuanEkle(){
        puanTablosu.add(oyuncu);
    }

    // TODO - Guncellenecek..
    public ArrayList<Oyuncu> readUsersFromJson(){
//        ArrayList<Oyuncu> oyuncuListesi = new ArrayList<Oyuncu>();
//
//        JSONParser parser = new JSONParser();
//        Object obj = parser.parse(oyuncuListesi);
//        JSONArray array = (JSONArray)obj;

        JSONParser parser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray)parser.parse(new FileReader("data/" + "users.json"));
//            System.out.println(pagesObject.get("id"));
//            System.out.println(pagesObject.get("pages").getClass().getName());
//            JSONArray jsonArray= (JSONArray) pagesObject.get("pages");
            for (int i = 0; i < jsonArray.size(); i++) {
                System.out.println(i+ ": " + jsonArray.get(i));
            }

            ArrayList<Oyuncu> oyuncuListesi = new ArrayList<Oyuncu>();
            for(int i=0; i<jsonArray.size(); i++){
                //oyuncuListesi.add((Oyuncu)jsonArray.get(i));
                System.out.println(i+ ":"+jsonArray.get(i) );
                JSONObject obj2 = (JSONObject)jsonArray.get(i);



                System.out.println(obj2);

                obj2.get("kullaniciAdi");
                obj2.get("sifre");
                obj2.get("toplamPuan");
                obj2.get("bakiye");
                obj2.get("gecilenUlkeler");
                ArrayList<String> gecilenUlkeler = new ArrayList<String>();

                String line=null;
                String[] token=null;

                line = obj2.get("gecilenUlkeler").toString();
                token = line.split(",");

                for (int j = 0; j < token.length; j++) {
                    gecilenUlkeler.add(token[j]);
                }


                Oyuncu tempOyuncu = new Oyuncu(obj2.get("kullaniciAdi").toString(),obj2.get("sifre").toString(),Integer.parseInt(obj2.get("bakiye").toString()),
                        Integer.parseInt( obj2.get("toplamPuan").toString()),gecilenUlkeler);
                oyuncuListesi.add(tempOyuncu);
               /* tempOyuncu.setKullaniciAdi();
                tempOyuncu.setSifre( );
                tempOyuncu.setBakiye(Integer.parseInt(obj2.get();
                tempOyuncu.setToplamPuan();*/

//                ArrayList<Ulke> gec =  (ArrayList<Ulke>)obj2.get("gecilenUlkeler");
                //               System.out.println(obj2.get("kullaniciAdi"));
//                System.out.println(gec.get(0).getUlkeAdi());
//                System.out.println(gec.get(1).getUlkeAdi());
//                System.out.println(gec.get(2).getUlkeAdi());

            }
            return oyuncuListesi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO - Guncellenecek..
    public void writeUsersIntoJson(){
//        ObjectMapper mapper = new ObjectMapper();
        //JSONObject obj = new JSONObject();
        try (FileWriter file = new FileWriter("data/" + "users.json")) {

            file.write("[");

            for(int i=0;i<oyuncuListesi.size();++i){
                System.out.println("oyuncuListesi:"+ oyuncuListesi.get(i).getGecilenUlkeler());
                JSONObject obj = new JSONObject();
                obj.put("kullaniciAdi", oyuncuListesi.get(i).getKullaniciAdi());

                obj.put("sifre", oyuncuListesi.get(i).getSifre());

                obj.put("bakiye", oyuncuListesi.get(i).getBakiye());

                obj.put("toplamPuan", oyuncuListesi.get(i).getToplamPuan());

//                ArrayList<Ulke> ulkeler = new ArrayList<Ulke>();
                String ulkeler = "";
                System.out.println("!!!!!!Ulkeler size" +oyuncuListesi.get(0).getGecilenUlkeler().size());
                for(int j=0;j<oyuncuListesi.get(i).getGecilenUlkeler().size();++j){
                    if(j<oyuncuListesi.get(i).getGecilenUlkeler().size()-1)
                        ulkeler+=oyuncuListesi.get(i).getGecilenUlkeler().get(j)+",";
                    else
                        ulkeler+=oyuncuListesi.get(i).getGecilenUlkeler().get(j);
                    System.out.println("Added Item  "+ oyuncuListesi.get(i).getGecilenUlkeler().get(j));
                }
                System.out.println("Ulkeler" + ulkeler);

                obj.put("gecilenUlkeler", ulkeler);

                file.write(obj.toString());
                file.write(",");
                file.write("\n");
                file.flush();
            }

            file.write("]");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // TODO - Guncellenecek..
    void ulkeOku() throws IOException {

        // Buffer'dan okuma yapabilmek icin olusturulan gecici bir string
        String tmpLine;

        // Okunan her bir soru satirini parcalara ayirip tutabilmek icin olusturulan bir string arrayi
        String[] questionDataFieldTokens;
        // Okunan her bir ulke satirini parcalara ayirip tutabilmek icin olusturulan bir string arrayi
        String[] countryDataFieldTokens;

        try {
            // ulkeler.csv dosyasi FileReader'i olusturulur.
            FileReader ulkelerCSVFileReader = new FileReader("data/ulkeler/"+"ulkeler.csv");
            // ulkeler.csv dosyasi FileReader'i okunarak BufferReadered(buffer)'a atilir
            BufferedReader UlkelerCSVBufferReader = new BufferedReader(ulkelerCSVFileReader);

            // ulkeler.csv dosyasinin buffer'ı satir satir okunur ve tmpLine'a atilir
            // her bir satir bir ulkeyi nesnesini ifade etmektedir
            while ((tmpLine = UlkelerCSVBufferReader.readLine()) != null) {

                // Buffer'dan okunan her bir satir virgül(,)'e gore tokenlarina ayrilarak countryDataFieldTokens arrayine atilir
                countryDataFieldTokens = tmpLine.split(",");

                // Okunan tokenlar ile gecici bir Ulke::ulke nesnesi olusturulur.
                Ulke ulke = new Ulke(
                        Integer.parseInt(countryDataFieldTokens[0]),
                        countryDataFieldTokens[1],
                        countryDataFieldTokens[2]);

                // Olusturulan bu ulke nesnesinin dinamik boyutlu olan komsular listesi elde ettigimiz token arrayinin
                // geriye kalan butun elemanlari oldugu icin dongu ile arrayin sonuna kadar olan kisim komsular listesine eklenir
                for (int j = 3; j < countryDataFieldTokens.length; j++) {
                    ulke.getKomsular().add(countryDataFieldTokens[j]);
                }

                // Her bir ulkeye ait sorulari okuyabilmek icin <ulkeadi>.csv dosyalarina erismemiz gerekli.
                // olusturulan gecici ulke nesnesi sorularini ekleyebilmek icin soru dosyasinin filepath'i olusturulur.
                String filepath = new String("data/ulkeler/"+ulke.getUlkeAdi() + ".csv");

                // Gecici ulkeye ait soru dosyasinin FileReader'i olusturulur
                FileReader questionCSVFileReader = new FileReader(filepath);
                // <ulkeadi>.csv dosyasi FileReader'i okunarak BufferedReader(buffer)'a atilir
                BufferedReader qeustionCSVBufferReader = new BufferedReader(questionCSVFileReader);

                /*
                 * Her bir dongu icin ulkeler.csv dosyasindan okunup olusturulan ulke nesnesinin, sahip olmasi
                 * gereken soruHavuzu listesinin elemanlari <ulke_adi>.csv formatindaki dosyalardan
                 * satir satir okunur ve soru nesnesi olusturularak listeye eklenir.
                */

                // <ulkeadi>.csv dosyasinin buffer'ı satir satir okunur ve tmpLine'a atilir
                // her bir satir bir soruyu ifade etmektedir
                while ((tmpLine = qeustionCSVBufferReader.readLine()) != null) {

                    // Buffer'dan okunan her bir satir virgül(,)'e gore tokenlarina ayrilarak questionDataFieldTokens arrayine atilir
                    questionDataFieldTokens = tmpLine.split(",");

                    // Okunan tokenlar ile gecici bir Soru::soru nesnesi olusturulur.
                    Soru soru = new Soru(
                            questionDataFieldTokens[0],
                            questionDataFieldTokens[1],
                            questionDataFieldTokens[2],
                            questionDataFieldTokens[3],
                            questionDataFieldTokens[4],
                            Integer.parseInt(questionDataFieldTokens[5]),
                            Integer.parseInt(questionDataFieldTokens[6]));

                    // Olusturulan gecici soru nesnesi tum datafiledlari duzgunce doldurularak
                    // olusturdugumuz ulke nesnesinin sorular listesine eklenir
                    ulke.getSoruHavuzu().add(soru);
                }

                // Soru okumak icin olusturulan BufferedReader(buffer) kapatilir
                qeustionCSVBufferReader.close();

                // Olusturulan gecici ulke nesnesi tum datafieldlari duzgunce doldurularak
                // OYS'nin Map::ulkeler datafield'ina eklenir
                ulkeler.put(ulke.getUlkeAdi(), ulke);
            }

            // Ulke okumak icin olusturulan BufferedReader(buffer) kapatilir.
            UlkelerCSVBufferReader.close();

        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException ex) {
            System.out.println("IOException was detected!!");
        }
    }

}
