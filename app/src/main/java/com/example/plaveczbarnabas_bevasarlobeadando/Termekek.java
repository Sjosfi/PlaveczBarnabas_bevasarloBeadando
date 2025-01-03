public class Termekek {
    private String nev;
    private int egysegar;
    private double mennyiseg;
    private String mertekegyseg;

    public Termekek(String nev, int egysegar, double mennyiseg, String mertekegyseg) {
        this.nev = nev;
        this.egysegar = egysegar;
        this.mennyiseg = mennyiseg;
        this.mertekegyseg = mertekegyseg;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getEgysegar() {
        return egysegar;
    }

    public void setEgysegar(int egysegar) {
        this.egysegar = egysegar;
    }

    public double getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(double mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public String getMertekegyseg() {
        return mertekegyseg;
    }

    public void setMertekegyseg(String mertekegyseg) {
        this.mertekegyseg = mertekegyseg;
    }

    public double getBruttoAr() {
        return Math.round(egysegar * mennyiseg * 100.0) / 100.0;
    }
}