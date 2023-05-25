package masodik_project;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Masodik_projekt {

    private List<String> sorok;
    private Fizetesek[] fizetesek;
    private Set<String> cimek = new HashSet<>();
    private Map<String, Integer> lakossag = new HashMap<>();

    public static void main(String[] args) throws IOException, ParseException {
        new Masodik_projekt().feladatok();
    }

    public Masodik_projekt() throws IOException, ParseException {
        sorok = Files.readAllLines(Path.of("Fizetesek.txt"));
        assert !sorok.isEmpty() : "Üres a fájl!";
        fizetesek = new Fizetesek[sorok.size() - 1];
        for (int i = 1; i < sorok.size(); i++) {
            fizetesek[i - 1] = new Fizetesek(sorok.get(i));
        }
        assert sorok.size() - 1 == fizetesek.length : "nincs meg minden fuvar";
        assert fizetesek[0] != null : "első fuvar hibája";
        assert fizetesek[fizetesek.length - 1] != null : "utolsó fuvar hibás";
        System.out.printf("a fájl FEJLÉCCEL %d sort tartalmaz!\n", sorok.size());
    }

    private void feladatok() throws IOException {
        feladat1();
        feladat2();
        feladat3();
        feladat4();
        feladat5();
        feladat6();
        feladat7();
    }

    private void feladat1() throws IOException {
        System.out.println("--- 1. feladat: Ki keress a legtöbbet?");

        int index = 0;
        for (int i = 0; i < fizetesek.length; i++) {
            if (fizetesek[i].getFizetes() > fizetesek[index].getFizetes()) {
                index = i;
            }
        }
        System.out.println("A legtöbbet kereső személy neve: " + fizetesek[index].getNev());
    }

    private void feladat2() {
        System.out.println("--- 2. feladat: Mennyi az átlag fizetés?");

        int osszeg = 0;
        for (int i = 0; i < fizetesek.length; i++) {
            osszeg = (int) (osszeg + fizetesek[i].getFizetes());
        }

        System.out.println("Az átlag fizetés:" + osszeg / fizetesek.length + "Ft");
    }

    private void feladat3() {
        System.out.println("--- 3. feladat: Mindenki Budapesti?");
        int i = 0;
        while (i < fizetesek.length && "Budapest".equals(fizetesek[i].getCim())) {
            i++;
        }
        System.out.println(i >= fizetesek.length ? "igen" : "nem");
    }

    private void feladat4() {
        System.out.println("--- 4. feladat: Van 20 év feletti Budapesti?");
        int i = 0;
        int kor = 20;
        while (i < fizetesek.length && fizetesek[i].getKor() < kor) {
            i++;
        }
        if (i <= fizetesek[i].getKor()) {
            System.out.println("Van 20 év feletti Budapesti");
        } else {
            System.out.println("Nincs 20 év feletti Budapesti");
        }

    }

    private void feladat5() {
        System.out.println("--- 5. feladat: Milyen címek vannak eltárolva?");
        for (Fizetesek fizetesek : fizetesek) {
            cimek.add(fizetesek.getCim());
        }
        System.out.println(cimek);
    }

    private void feladat6() {
        System.out.println("--- 6. feladat:Melyik címen hanyan laknak ?");
        
        for (Fizetesek fizetes : fizetesek) {
            String cim = fizetes.getCim();
            lakossag.put(cim, lakossag.getOrDefault(cim, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : lakossag.entrySet()) {
            String cim = entry.getKey();
            int lakosok = entry.getValue();
            System.out.println(cim + ": " + lakosok + " lakos");
        }

    }

    private void feladat7() {
        System.out.println("--- 7. feladat: Írd ki a 'nemBP.txt' fájlba a nem Budapestiek minden adatát!");
        String fajlnev = "nemBP.txt";
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fajlnev), "UTF-8"))) {
            for (Fizetesek fizetes : fizetesek) {
                if (!"Budapest".equals(fizetes.getCim())) {
                    writer.write(fizetes.toString());
                    writer.newLine();
                }
            }
            System.out.println("Adatok sikeresen kiírva az 'nemBP.txt' fájlba.");
        } catch (IOException e) {
            System.err.println("Hiba történt a fájl írása közben: " + e.getMessage());
        }

    }

}
