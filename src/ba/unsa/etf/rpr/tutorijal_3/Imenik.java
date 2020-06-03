package ba.unsa.etf.rpr.tutorijal_3;

import java.util.*;
import java.util.stream.Collectors;

import static ba.unsa.etf.rpr.tutorijal_3.FiksniBroj.Grad;

public  class Imenik {
    HashMap<String, TelefonskiBroj> imenik = new HashMap<>();

    public void dodaj(String ime, TelefonskiBroj broj) { imenik.put(ime, broj); }
    public String dajBroj(String ime) { return imenik.getOrDefault(ime, null).ispisi(); }
    public String dajIme(TelefonskiBroj broj) {
        for(HashMap.Entry<String, TelefonskiBroj> entry: imenik.entrySet())
            if(entry.getValue().ispisi().equals(broj.ispisi()))  return entry.getKey();
        return null;
    }
    public String naSlovo(char s) {
        StringBuilder brojevi = new StringBuilder();
        int brojac = 1;
        for(HashMap.Entry<String, TelefonskiBroj> entry: imenik.entrySet()) {
            if(entry.getKey().charAt(0) == s) {
                brojevi.append(brojac).append(". ").append(entry.getKey()).append(" - ").append(entry.getValue().ispisi()).append("\n");
                brojac++;
            }
        }
        return brojevi.toString();
    }
    public Set<String> izGrada(Grad g) {
        Set<String> imena = new HashSet<>();
        for(HashMap.Entry<String, TelefonskiBroj> entry: imenik.entrySet()) {
            if(entry.getValue() instanceof FiksniBroj && ((FiksniBroj) entry.getValue()).getGrad() == g)
                imena.add(entry.getKey());
        }
        return new TreeSet<>(imena);
    }
    public  Set<TelefonskiBroj> izGradaBrojevi(Grad g) {
        Set<TelefonskiBroj> imena = new HashSet<>();
        for(HashMap.Entry<String, TelefonskiBroj> entry: imenik.entrySet()) {
            if(entry.getValue() instanceof FiksniBroj && ((FiksniBroj) entry.getValue()).getGrad() == g)
                imena.add(entry.getValue());
        }
        return imena.stream().sorted(Comparator.comparing(TelefonskiBroj::ispisi)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public HashMap<String, TelefonskiBroj> getImenik() {
        return imenik;
    }
}
