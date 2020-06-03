package ba.unsa.etf.rpr.tutorijal_3;

import java.util.Set;
import static ba.unsa.etf.rpr.tutorijal_3.FiksniBroj.Grad;

public abstract class Imenik {

    public abstract void Dodaj(String ime, TelefonskiBroj broj);
    public abstract String dajBroj(String ime);
    public abstract String dajIme(TelefonskiBroj broj);
    public abstract String naSlovo(char s);
    public abstract Set<String> izGrada(Grad g);
    public abstract Set<TelefonskiBroj> izGradaBrojevi(Grad g);

}
