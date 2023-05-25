package masodik_project;

import java.text.NumberFormat;
import java.text.ParseException;

public class Fizetesek {

    String nev;
    private int kor;
    private String cim;
    double fizetes;

    public Fizetesek(String sor) throws ParseException {
        System.out.println(sor);
        String[] s = sor.split(",");
        this.nev = s[0];
        this.kor = Integer.parseInt(s[1]);
        this.cim = s[2];
        NumberFormat nf2 = NumberFormat.getInstance();
        Number num2 = nf2.parse(s[3]);
        this.fizetes = num2.doubleValue();
    }


    public Fizetesek(String nev, int kor, String cim, double fizetes) {
        this.nev = nev;
        this.kor = kor;
        this.cim = cim;
        this.fizetes = fizetes;
    }
    
    public String getCim() {
        return cim;
    }

    public double getFizetes() {
        return fizetes;
    }

    public int getKor() {
        return kor;
    }

    public String getNev() {
        return nev;
    }

    @Override
    public String toString() {
        return "Fizetesek{" + "nev=" + nev + ", kor=" + kor + ", cim=" + cim + ", fizetes=" + fizetes + '}';
    }
    
    
    
    
    
    
}
