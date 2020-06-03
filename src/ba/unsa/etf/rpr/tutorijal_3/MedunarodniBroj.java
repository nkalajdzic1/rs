package ba.unsa.etf.rpr.tutorijal_3;

public class MedunarodniBroj extends TelefonskiBroj {
    String drzava;
    String broj;

    public MedunarodniBroj(String drzava, String broj) {
        this.drzava = drzava;
        this.broj = broj;
    }

    @Override
    public String ispisi() {
        return drzava + "/" + broj;
    }

    @Override
    public int Hashcode() {
        return broj.hashCode();
    }
}

