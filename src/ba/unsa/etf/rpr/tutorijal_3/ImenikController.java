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


public class ImenikController {
    Imenik imenik = new Imenik();
    @FXML Button btnDodaj, btnDajIme, btnDajBroj;
    @FXML TextField fldIme, fldBroj, fldImeOsobe, fldBrojOsobe;
    @FXML ChoiceBox<FiksniBroj.Grad> choiceGrad, choiceGradOsobe;

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
}
