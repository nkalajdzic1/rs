package ba.unsa.etf.rpr.tutorijal_3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.Set;


public class ImenikController {
    Imenik imenik = new Imenik();
    @FXML Button btnDodaj, btnDajIme, btnDajBroj, btnBrojeviSaSlovom, btnDajBrojeveIzGrada, btnDajImenaIzGradova;
    @FXML TextField fldIme, fldBroj, fldImeOsobe, fldBrojOsobe;
    @FXML ChoiceBox<FiksniBroj.Grad> choiceGrad, choiceGradOsobe, choiceGradoviZaBrojeve, choiceGradoviZaOsobe;
    @FXML ChoiceBox<String> choiceSlovo;

    @FXML
    public void Listener(TextField field) {
        field.textProperty().addListener((observableValue, s, t1) -> {
            if(t1.isEmpty()) field.setStyle("-fx-background-color: lightpink");
            else field.setStyle("-fx-background-color: greenyellow");
        });
    }

    @FXML
    public void initialize() {
        ObservableList<FiksniBroj.Grad> listaGradova = FXCollections.observableArrayList();
        listaGradova.addAll(Arrays.asList(FiksniBroj.Grad.values()));
        choiceGrad.setItems(listaGradova);
        choiceGradOsobe.setItems(listaGradova);
        choiceGradoviZaBrojeve.setItems(listaGradova);
        choiceGradoviZaOsobe.setItems(listaGradova);

        ObservableList<String> slova = FXCollections.observableArrayList();
        char slovo = 'A';
        while(slovo != 'Z' + 1) {
            slova.add(String.valueOf(slovo));
            slovo++;
        }
        choiceSlovo.setItems(slova);

        Listener(fldIme);
        Listener(fldBroj);
        Listener(fldBrojOsobe);
        Listener(fldImeOsobe);
    }

    public void dodajAction() {
        String tacno = "-fx-background-color: greenyellow";
        if(fldIme.getStyle().equals(tacno) && fldBroj.getStyle().equals(tacno) && choiceGrad.getValue() != null) {
            imenik.dodaj(fldIme.getText(), new FiksniBroj(choiceGrad.getValue(), fldBroj.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Uspješno ste dodali novi kontakt!");
            alert.setContentText("Ime kontakta: " + fldIme.getText() + "\n" + "Broj kontakta: " + imenik.dajBroj(fldIme.getText()));
            alert.showAndWait();
            fldIme.setText("");
            fldIme.setStyle("");
            fldBroj.setText("");
            fldBroj.setStyle("");
            choiceGrad.setValue(null);
        }
    }

    public void actionDajIme() {
        String tacno = "-fx-background-color: greenyellow";
        if(fldBrojOsobe.getStyle().equals(tacno) && choiceGradOsobe.getValue() != null) {
            String ime = imenik.dajIme(new FiksniBroj(choiceGradOsobe.getValue(), fldBrojOsobe.getText()));
            if(ime == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Ne postoji ta osoba!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Ime tražene osobe: " + ime);
                alert.showAndWait();
            }
            fldBrojOsobe.setText("");
            fldBrojOsobe.setStyle("");
        }
    }

    public void actionDajBroj() {
        String tacno = "-fx-background-color: greenyellow";
        if(fldImeOsobe.getStyle().equals(tacno)) {
            String broj = imenik.dajBroj(fldImeOsobe.getText());
            if(broj == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Ne postoji ta osoba!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Broj tražene osobe: " + broj);
                alert.showAndWait();
            }
            fldImeOsobe.setText("");
            fldImeOsobe.setStyle("");
        }
    }

    public void dajSveBrojeveSaSlovom() {
        if(choiceSlovo.getValue() != null) {
            String imena = imenik.naSlovo(choiceSlovo.getValue().charAt(0)) +
                             imenik.naSlovo(choiceSlovo.getValue().toLowerCase().charAt(0));
            if(!imena.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(imena);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Ne postoji ni jedan kontak sa slovom " + choiceSlovo.getValue() + ".");
                alert.showAndWait();
            }
        }
    }

    public void actionDajBrojeveIzGrada() {
        if(choiceGradoviZaBrojeve.getValue() != null) {
            Set<TelefonskiBroj> brojevi = imenik.izGradaBrojevi(choiceGradoviZaBrojeve.getValue());
            if(!brojevi.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                StringBuilder br = new StringBuilder();
                for(TelefonskiBroj t: brojevi) br.append(t.ispisi()).append("\n");
                alert.setContentText(br.toString());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Nema brojeva iz izabranog grada.");
                alert.showAndWait();
            }
        }
    }

    public void actionDajImenaIzGradova() {
        if(choiceGradoviZaOsobe.getValue() != null) {
            Set<String> imena = imenik.izGrada(choiceGradoviZaOsobe.getValue());
            if(!imena.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                StringBuilder br = new StringBuilder();
                for(String t: imena) br.append(t).append("\n");
                alert.setContentText(br.toString());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Nema imena iz izabranog grada.");
                alert.showAndWait();
            }
        }
    }
}
