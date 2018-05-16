package de.hawhh.informatik.jprpab.mediathek.werkzeuge.rueckgabe;

import java.util.ArrayList;
import java.util.List;

import de.hawhh.informatik.jprpab.mediathek.fachwerte.Datum;
import de.hawhh.informatik.jprpab.mediathek.materialien.medien.Medium;
import de.hawhh.informatik.jprpab.mediathek.materialien.verleihkarte.Verleihkarte;
import de.hawhh.informatik.jprpab.mediathek.service.verleih.ProtokollierException;
import de.hawhh.informatik.jprpab.mediathek.service.verleih.VerleihService;

//import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/**
 * Ein RueckgabeWerkzeug stellt die Funktionalität der Rücknahme für die
 * Benutzungsoberfläche bereit. Die UI wird durch die RueckgabeUI gestaltet.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2018
 */
public class RueckgabeWerkzeug
{
	/**
	 * Die UI-Komponente der Rueckgabe.
	 */
	private final RueckgabeUI _rueckgabeUI;

	/**
	 * Der Service zum Ausleihen von Medien.
	 */
	private final VerleihService _verleihService;

	/**
	 * Initialisiert ein neues RueckgabeWerkzeug. Es wird die Benutzungsoberfläche
	 * mit den Rückgabeaktionen erzeugt, Beobachter an den Services registriert und
	 * die anzuzeigenden Materialien gesetzt.
	 * 
	 * @param verleihService
	 *            Der zu benutzenden VerleihService.
	 * 
	 * @require verleihService != null
	 */
	public RueckgabeWerkzeug(VerleihService verleihService)
	{
		assert verleihService != null : "Vorbedingung verletzt: verleihService != null";
		_verleihService = verleihService;

		// UI wird erzeugt.
		_rueckgabeUI = new RueckgabeUI();

		// Die Beobachter werden erzeugt und an den Services registriert.
//		try
//		{
			registriereServiceBeobachter();
//		}
//		catch (ProtokollierException e)
//		{
//			JOptionPane.showMessageDialog(null, e,
//    				"Fehlermeldung", JOptionPane.ERROR_MESSAGE);
//		}

		// Die Rückgabe-Aktionen werden erzeugt und an den UI-Widgets
		// registriert.
		registriereUIAktionen();

		// Die anzuzeigenden Materialien werden an der UI gesetzt.
		setzeAnzuzeigendeMaterialien();
	}

	/**
	 * Setzt die Materialien an der UI, die angezeigt werden sollen.
	 */
	private void setzeAnzuzeigendeMaterialien()
	{
//		try
//		{
			setzeAnzuzeigendeVerleihkarten();
//		}
//		catch (ProtokollierException e)
//		{
//			JOptionPane.showMessageDialog(null, e,
//    				"Fehlermeldung", JOptionPane.ERROR_MESSAGE);
//		}
	}

	/**
	 * Registriert die Aktionen, die bei bestimmten UI-Events ausgeführt werden.
	 * 
	 * @throws ProtokollierException
	 */
	private void registriereUIAktionen()
	{
		registriereRuecknahmeAktion();
		registriereVerleihkartenAnzeigenAktion();
	}

	/**
	 * Registriert die Aktion die ausgeführt wird, wenn Verleihkarten selektiert
	 * werden.
	 */
	private void registriereVerleihkartenAnzeigenAktion()
	{
		_rueckgabeUI.getVerleihkartenAuflisterTable().getSelectionModel().selectedIndexProperty().addListener(event ->
		{
			zeigeAusgewaehlteVerleihkarten();
			aktualisiereRuecknahmeButton();
		});
	}

	/**
	 * Registriert die Rücknahmeaktion.
	 */
	private void registriereRuecknahmeAktion()
	{
		_rueckgabeUI.getRuecknahmeButton().setOnAction(event -> {
//			try
//			{
				nimmAusgewaehlteMedienZurueck();
//			}
//			catch (ProtokollierException e)
//			{
//				JOptionPane.showMessageDialog(null, e,
//	    				"Fehlermeldung", JOptionPane.ERROR_MESSAGE);
//			}
		});
	}

	/**
	 * Registriert die Beobachter an den Services.
	 */
	private void registriereServiceBeobachter() //throws ProtokollierException
	{
		_verleihService.registriereBeobachter(() -> {
//			try
//			{
				setzeAnzuzeigendeVerleihkarten();
//			}
//			catch (ProtokollierException e)
//			{
//				JOptionPane.showMessageDialog(null, e,
//	    				"Fehlermeldung", JOptionPane.ERROR_MESSAGE);
//			}
		});
	}

	/**
	 * Holt alle Verleihkarten vom Verleihservice und setzt diese an der UI.
	 */
	private void setzeAnzuzeigendeVerleihkarten() //throws ProtokollierException
	{
		List<Verleihkarte> verleihkarten = _verleihService.getVerleihkarten();
		ObservableList<Verleihkarte> verleihkartenListe = FXCollections.observableArrayList();
		verleihkartenListe.addAll(verleihkarten);
		_rueckgabeUI.getVerleihkartenAuflisterTable().setItems(verleihkartenListe);
	}

	/**
	 * Gibt die vom Benutzer ausgewählten Medien zurück.
	 */
	private void nimmAusgewaehlteMedienZurueck()
	{
		List<Verleihkarte> verleihkarten = getSelectedVerleihkarten();
		List<Medium> medien = new ArrayList<Medium>();
		for (Verleihkarte verleihkarte : verleihkarten)
		{
			medien.add(verleihkarte.getMedium());
		}

		try
		{
			_verleihService.nimmZurueck(medien, Datum.heute());
		}
		catch (ProtokollierException e)
		{
//			JOptionPane.showMessageDialog(null, e,
//			"Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Zurückgeben nicht möglich");
			alert.setHeaderText("Da protokollieren nicht möglich ist, ist das Zurückgeben nicht möglich");
			alert.setContentText(e.toString());

			alert.showAndWait();
		}

	}

	/**
	 * Zeigt die Details der ausgewählten Verleihkarten an.
	 */
	private void zeigeAusgewaehlteVerleihkarten()
	{
		List<Verleihkarte> selektierteVerleihkarten = getSelectedVerleihkarten();
		TextArea _ausgewaehlteVerleihkartenTextArea = _rueckgabeUI.getVerleihkartenAnzeigerTextArea();
		_ausgewaehlteVerleihkartenTextArea.setText("");
		for (Verleihkarte verleihkarte : selektierteVerleihkarten)
		{
			_ausgewaehlteVerleihkartenTextArea.appendText(verleihkarte.getFormatiertenString());
			_ausgewaehlteVerleihkartenTextArea.appendText("--------------- \n");
		}
	}

	/**
	 * Graut den RuecknahmeButton aus, wenn keine Verleihkarten selektiert sind.
	 */
	private void aktualisiereRuecknahmeButton()
	{
		_rueckgabeUI.getRuecknahmeButton().setDisable(getSelectedVerleihkarten().isEmpty());
	}

	/**
	 * Gibt das Panel, dass die UI-Koponente darstellt zurück.
	 * 
	 * @return Das Panel, dass die UI-Koponente darstellt.
	 * 
	 * @ensure result != null
	 */
	public Pane getUIPane()
	{
		return _rueckgabeUI.getUIPane();
	}

	/**
	 * Liefert die vom Benutzer selektierten Verleihkarten.
	 * 
	 * @return Die selektierten Verleihkarten.
	 * 
	 * @ensure result != null
	 */
	private List<Verleihkarte> getSelectedVerleihkarten()
	{
		return _rueckgabeUI.getVerleihkartenAuflisterTable().getSelectionModel().getSelectedItems();
	}
}
