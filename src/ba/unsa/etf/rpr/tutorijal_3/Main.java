package ba.unsa.etf.rpr.tutorijal_3;

public class Main {
    public static void main(String[] args) {
        FiksniBroj br = new FiksniBroj(FiksniBroj.Grad.SARAJEVO, "412-238");
        System.out.println(br.getGrad().getLabel());
    }
}
