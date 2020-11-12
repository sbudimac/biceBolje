package gui;

import api.Operacija;
import api.UgnjezdenUslov;
import api.Uslov;
import api.UslovFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FilterGUI extends HBox {
	private ComboBox<String> kljucevi;
	private ComboBox<Operacija> operacije;
	private TextField uslov;
	private CheckBox ugnjezden;
	private ComboBox<String> spoljasnjiKljucevi;
	
	public FilterGUI(Label lbKljucevi, Label lbOperacije, Label lbUslov, Label lbUgnjezden, Label lbSpoljasnjiKljucevi, ComboBox<String> kljucevi, ComboBox<Operacija> operacije, TextField uslov, CheckBox ugnjezden, ComboBox<String> spoljasnjiKljucevi) {
		super(lbUgnjezden, ugnjezden, lbSpoljasnjiKljucevi, spoljasnjiKljucevi,lbKljucevi, kljucevi, lbOperacije, operacije, lbUslov, uslov);
		this.kljucevi=kljucevi;
		this.operacije=operacije;
		this.uslov=uslov;
		this.ugnjezden=ugnjezden;
		this.spoljasnjiKljucevi=spoljasnjiKljucevi;
		
		this.ugnjezden.selectedProperty().addListener(new ChangeListener<Boolean>() {
           public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
               if(new_val) {
            	   spoljasnjiKljucevi.setVisible(true);
            	   spoljasnjiKljucevi.setManaged(true);
            	   lbSpoljasnjiKljucevi.setVisible(true);
            	   lbSpoljasnjiKljucevi.setManaged(true);
               }else {
            	   spoljasnjiKljucevi.setVisible(false);
            	   spoljasnjiKljucevi.setManaged(false);
            	   lbSpoljasnjiKljucevi.setVisible(false);
            	   lbSpoljasnjiKljucevi.setManaged(false);
               }
            }
        });
	}
	
	public Uslov getUslov() {
		String kljuc=kljucevi.getSelectionModel().getSelectedItem();
		Operacija operacija=operacije.getSelectionModel().getSelectedItem();
		String vrednost=uslov.getText();
		if(kljuc!=null && operacija!=null && vrednost!=null) {
			if(ugnjezden.isSelected()) {
				String ugnjezdeniKljuc=spoljasnjiKljucevi.getSelectionModel().getSelectedItem();
				if(ugnjezdeniKljuc!=null) {
					UgnjezdenUslov uslov=new UgnjezdenUslov(ugnjezdeniKljuc, UslovFactory.kreirajUslov(kljuc, operacija, vrednost));
					return uslov;
				}
			}
			return UslovFactory.kreirajUslov(kljuc, operacija, vrednost);
		}
		return null;
	}
}
