package ba.unsa.etf.rpr.tutorijal_3;

public class FiksniBroj extends TelefonskiBroj {

    private String broj;
    private Grad grad;
    public static enum Grad {
        SARAJEVO("033"), TUZLA("035"), ZENICA("032"), BRCKO("049"), TRAVNIK("030"),
        ORASJE("031"), LIVNO("034"), MOSTAR("036"), BIHAC("037"), GORAZDE("038"), SIROKIBRIJEG("039");
        private final String label;
        Grad(String label) { this.label = label; }
        public String getLabel() { return label; }
    }

    FiksniBroj(Grad grad, String broj) {
       this.broj = broj;
       this.grad = grad;
    }

    public Grad getGrad() {
        return grad;
    }

    public String getBroj() {
        return broj;
    }

    @Override
    public String ispisi() {
        return grad.getLabel() + "/" + broj;
    }

    @Override
    public int Hashcode() {
        return 0;
    }
}
