/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.model.Adiacenze;
import it.polito.tdp.crimes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxCategoria"
    private ComboBox<String> boxCategoria; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="boxArco"
    private ComboBox<?> boxArco; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	
    	 if(this.model.FilteredPesoMedio()==null)
		  {
			  txtResult.appendText("ERRORE PESO MEDIO\n");
			  return;
		  }
		  
		 
		// this.boxArco.getItems().addAll(this.model.FilteredPesoMedio());
    	

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String categoria = this.boxCategoria.getValue();
    	Integer mese = this.boxMese.getValue();
    	if(mese==null )
    	{
    		txtResult.appendText("INSERIRE UN MESE\n");
    		return;
    	}
    	if(categoria==null )
    	{
    		txtResult.appendText("INSERIRE UNA CATEGORIA\n");
    		return;
    	}
    	this.model.creaGrafo(categoria, mese);
		
		  txtResult.appendText("VERTICI : "+this.model.VertexSize()+"\n");
		  txtResult.appendText("ARCHI : "+this.model.EdgeSet()+"\n");
		 
		  if(this.model.FilteredPesoMedio()==null)
		  {
			  txtResult.appendText("ERRORE PESO MEDIO\n");
			  return;
		  }
		 
		 for(Adiacenze s : this.model.FilteredPesoMedio())
		 {
			 txtResult.appendText(s.toString());
		 }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxCategoria != null : "fx:id=\"boxCategoria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxArco != null : "fx:id=\"boxArco\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	if(this.model.ListAllCategory() == null || this.model.ListMonths() == null)
    	{
    		txtResult.appendText("ERRORE CARICAMENTO DATABASE!\n");
    		return;
    	}
    	this.boxCategoria.getItems().addAll(this.model.ListAllCategory());
    	this.boxMese.getItems().addAll(this.model.ListMonths());
    }
}
