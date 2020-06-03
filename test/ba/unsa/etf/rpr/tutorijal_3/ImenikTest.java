package ba.unsa.etf.rpr.tutorijal_3;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static ba.unsa.etf.rpr.tutorijal_3.FiksniBroj.Grad.*;
import static org.junit.jupiter.api.Assertions.*;

public class ImenikTest {

    @Test
    void dodaj() {
        Imenik imenik = new Imenik();
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        imenik.dodaj("Hana Hanic", new FiksniBroj(SARAJEVO, "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(SARAJEVO, "123-456"));
        assertEquals("033/123-456", imenik.dajBroj("Meho Mehic"));
    }

    @Test
    void naSlovo() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Sara Sarac", new FiksniBroj(SARAJEVO, "123-156"));
        imenik.dodaj("Pero Peric", new FiksniBroj(SARAJEVO, "123-656"));
        imenik.dodaj("Ivo Ivic", new MobilniBroj(61, "321-645"));
        imenik.dodaj("Jozo Jozic", new MobilniBroj(64, "987-654"));
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        assertEquals("1. Ivo Ivic - 061/321-645", imenik.naSlovo('I').trim());
    }

    @Test
    void izGrada() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ivo Ivic", new FiksniBroj(SARAJEVO, "123-456"));
        imenik.dodaj("Sara Sarac", new FiksniBroj(SARAJEVO, "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(SARAJEVO, "123-656"));
        imenik.dodaj("Pero Peric", new MobilniBroj(64, "987-654"));
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        Set<String> set = imenik.izGrada(SARAJEVO);
        String result = "";
        for (String ime : set) {
            result += ime + ",";
        }
        assertEquals("Ivo Ivic,Meho Mehic,Sara Sarac,", result);
    }

    @Test
    void izGradaBrojevi() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ivo Ivic", new FiksniBroj(SARAJEVO, "123-456"));
        imenik.dodaj("Sara Sarac", new FiksniBroj(SARAJEVO, "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(SARAJEVO, "123-656"));
        imenik.dodaj("Pero Peric", new MobilniBroj(64, "987-654"));
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        Set<TelefonskiBroj> set = imenik.izGradaBrojevi(SARAJEVO);
        String result = "";
        for (TelefonskiBroj broj : set) {
            result += broj.ispisi() + ",";
        }
        assertEquals("033/123-156,033/123-456,033/123-656,", result);
    }

    @Test
    void dajIme() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ivo Ivic", new FiksniBroj(SARAJEVO, "123-456"));
        imenik.dodaj("Sara Sarac", new FiksniBroj(SARAJEVO, "123-156"));
        imenik.dodaj("Meho Mehic", new FiksniBroj(SARAJEVO, "123-656"));
        imenik.dodaj("Pero Peric", new MobilniBroj(64, "987-654"));
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        assertEquals("John Smith",imenik.dajIme(new MedunarodniBroj("+1","23 45-67-89")));
    }

    @Test
    void daLiSeSifriralo() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ivo Ivic", new FiksniBroj(SARAJEVO, "123-456"));
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        imenik.dodaj("Pero Peric", new MobilniBroj(64, "987-654"));
        // samo je bitno da vidimo da je hash-ovano
        assertNotEquals(33123456, imenik.getImenik().get("Ivo Ivic").Hashcode());
        assertNotEquals(123456789, imenik.getImenik().get("John Smith").Hashcode());
        assertNotEquals(64987654, imenik.getImenik().get("Pero Peric").Hashcode());
    }

    @Test
    void testGrada() {
        assertEquals("036", MOSTAR.getLabel());
        assertEquals("033", SARAJEVO.getLabel());
        assertEquals("035", TUZLA.getLabel());
        assertEquals("032", ZENICA.getLabel());
        assertEquals("049", BRCKO.getLabel());
        assertEquals("030", TRAVNIK.getLabel());
        assertEquals("031", ORASJE.getLabel());
        assertEquals("034", LIVNO.getLabel());
        assertEquals("037", BIHAC.getLabel());
        assertEquals("038", GORAZDE.getLabel());
        assertEquals("039", SIROKIBRIJEG.getLabel());
    }

    @Test
    void testZaIspise() {
        Imenik imenik = new Imenik();
        imenik.dodaj("Ivo Ivic", new FiksniBroj(SARAJEVO, "123-456"));
        imenik.dodaj("John Smith", new MedunarodniBroj("+1", "23 45-67-89"));
        imenik.dodaj("Pero Peric", new MobilniBroj(64, "987-654"));
        assertEquals("033/123-456", imenik.dajBroj("Ivo Ivic"));
        assertEquals("+1/23 45-67-89", imenik.dajBroj("John Smith"));
        assertEquals("064/987-654", imenik.dajBroj("Pero Peric"));
    }
}
