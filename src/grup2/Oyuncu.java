package grup2;

import java.util.ArrayList;
import java.util.Objects;

public class Oyuncu implements Comparable <Oyuncu>{

    private String kullaniciAdi;
    private String sifre;
    private int bakiye;
    private int toplamPuan;
    public ArrayList<String> gecilenUlkeler;

    public Oyuncu(String kullaniciAdi, String sifre, int bakiye, int toplamPuan, ArrayList<String> gecilenUlkeler) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.bakiye = bakiye;
        this.toplamPuan = toplamPuan;
        this.gecilenUlkeler = gecilenUlkeler;
    }

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

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public void bakiyeArttir(int bakiye) {
        this.bakiye += bakiye;
    }

    public void bakiyeAzalt(int bakiye) {
        this.bakiye -= bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }

    public void setToplamPuan(int toplamPuan) {
        this.toplamPuan = toplamPuan;
    }

    public void toplamPuanArttir(int toplamPuan) {
        this.toplamPuan += toplamPuan;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public int getBakiye() {
        return bakiye;
    }

    public int getToplamPuan() {
        return toplamPuan;
    }

    public void setGecilenUlkeler(ArrayList<String> gecilenUlkeler) {
        this.gecilenUlkeler = gecilenUlkeler;
    }

    public ArrayList<String> getGecilenUlkeler() {
        return gecilenUlkeler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oyuncu)) return false;
        Oyuncu oyuncu = (Oyuncu) o;
        return Objects.equals(kullaniciAdi, oyuncu.kullaniciAdi);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Oyuncu o) {

        if (this.toplamPuan < o.toplamPuan){
            return -1;
        } else if (this.toplamPuan == o.toplamPuan) {
            return 0;
        } else {
            return 1;
        }
    }
}
