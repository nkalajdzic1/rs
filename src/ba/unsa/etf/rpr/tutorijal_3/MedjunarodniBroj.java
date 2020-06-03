package ba.unsa.etf.rpr.tutorijal_3;

public class MedjunarodniBroj extends TelefonskiBroj {
    String drzava;
    String broj;

    public MedjunarodniBroj(String drzava, String broj) {
        this.drzava = drzava;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        return null;
    }

    @Override
    public int Hashcode() {
        return 0;
    }
}

