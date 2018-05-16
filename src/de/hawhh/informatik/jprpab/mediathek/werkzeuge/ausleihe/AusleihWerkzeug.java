package de.hawhh.informatik.jprpab.mediathek.werkzeuge.ausleihe;
import java.util.List;

import de.hawhh.informatik.jprpab.mediathek.fachwerte.Datum;
import de.hawhh.informatik.jprpab.mediathek.materialien.kunde.Kunde;
import de.hawhh.informatik.jprpab.mediathek.materialien.medien.Medium;
import de.hawhh.informatik.jprpab.mediathek.service.ServiceObserver;
import de.hawhh.informatik.jprpab.mediathek.service.kundenstamm.KundenstammService;
import de.hawhh.informatik.jprpab.mediathek.service.medienbestand.MedienbestandService;
import de.hawhh.informatik.jprpab.mediathek.service.verleih.ProtokollierException;
import de.hawhh.informatik.jprpab.mediathek.service.verleih.VerleihService;
import de.hawhh.informatik.jprpab.mediathek.werkzeuge.SubWerkzeugObserver;
import de.hawhh.informatik.jprpab.mediathek.werkzeuge.kunden.KundenDetailAnzeigerWerkzeug;
import de.hawhh.informatik.jprpab.mediathek.werkzeuge.kunden.KundenauflisterWerkzeug;
import de.hawhh.informatik.jprpab.mediathek.werkzeuge.medien.MedienDetailAnzeigerWerkzeug;

//import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;

/**
 * Ein AusleihWerkzeug stellt die Funktionalität der Ausleihe für die
 * Benutzungsoberfläche bereit. Die UI wird durch die AusleiheUI gestaltet.
 * 
 * @author SE2-Team, PM2-Team
 * @version SoSe 2018
 */
public class AusleihWerkzeug
{
	/**
	 * Die UI-Komponente der Ausleihe.
	 */
	private final AusleiheUI _ausleiheUI;

	/**
	 * Der Service zum Ausleihen von Medien.
	 */
	private final VerleihService _verleihService;

	/**
	 * Das Sub-Werkzeug zum darstellen und selektieren der Kunden.
	 */
	private KundenauflisterWerkzeug _kundenAuflisterWerkzeug;

	/**
	 * Das Sub-Werkzeug zum darstellen und selektieren der Medien.
	 */
	private AusleiheMedienauflisterWerkzeug _medienAuflisterWerkzeug;

	/**
	 * Das Sub-Werkzeug zum anzeigen der Details der selektieten Medien.
	 */
	private MedienDetailAnzeigerWerkzeug _medienDetailAnzeigerWerkzeug;

	/**
	 * Das Sub-Werkzeug zum anzeigen der Details des selektieten Kunden.
	 */
	private KundenDetailAnzeigerWerkzeug _kundenDetailAnzeigerWerkzeug;

	/**
	 * Initialisiert ein neues AusleihWerkzeug. Es wird die Benutzungsoberfläche mit
	 * den Ausleihaktionen erzeugt, Beobachter an den Services registriert und die
	 * anzuzeigenden Materialien gesetzt.
	 * 
	 * @param medienbestand
	 *            Der Medienbestand.
	 * @param kundenstamm
	 *            Der Kundenstamm.
	 * @param verleihService
	 *            Der Verleih-Service.
	 * 
	 * @require medienbestand != null
	 * @require kundenstamm != null
	 * @require verleihService != null
	 */
	public AusleihWerkzeug(MedienbestandService medienbestand, KundenstammService kundenstamm,
			VerleihService verleihService)
	{
		assert medienbestand != null : "Vorbedingung verletzt: medienbestand != null";
		assert kundenstamm != null : "Vorbedingung verletzt: kundenstamm != null";
		assert verleihService != null : "Vorbedingung verletzt: verleihService != null";

		_verleihService = verleihService;

		// Subwerkzeuge erstellen
		_kundenAuflisterWerkzeug = new KundenauflisterWerkzeug(kundenstamm);
		_medienAuflisterWerkzeug = new AusleiheMedienauflisterWerkzeug(medienbestand, verleihService);
		_medienDetailAnzeigerWerkzeug = new MedienDetailAnzeigerWerkzeug();
		_kundenDetailAnzeigerWerkzeug = new KundenDetailAnzeigerWerkzeug();

		// UI erzeugen
		_ausleiheUI = new AusleiheUI(_kundenAuflisterWerkzeug.getUIPane(), _medienAuflisterWerkzeug.getUIPane(),
				_kundenDetailAnzeigerWerkzeug.getUIPane(), _medienDetailAnzeigerWerkzeug.getUIPane());

		// Beobachter erzeugen und an den Services registrieren
		registriereServiceBeobachter();

		// Beobachter erzeugen und an den Subwerkzeugen registrieren
		registriereSubWerkzeugBeobachter();

		// Die Ausleihaktionen erzeugen und an der UI registrieren
		registriereUIAktionen();
	}

	/**
	 * Registriert die Aktionen, die bei Benachrichtigungen der Services ausgeführt
	 * werden.
	 */
	private void registriereServiceBeobachter()
	{
		registriereAusleihButtonAktualisierenAktion();
	}

	/**
	 * Registriert die Aktionen, die bei bestimmten Änderungen in Subwerkzeugen
	 * ausgeführt werden.
	 */
	private void registriereSubWerkzeugBeobachter()
	{
		registriereKundenAnzeigenAktion();
		registriereMedienAnzeigenAktion();
	}

	/**
	 * Registriert die Aktionen, die bei bestimmten UI-Events ausgeführt werden.
	 */
	private void registriereUIAktionen()
	{
		registriereAusleihAktion();
	}

	/**
	 * Registriert die Aktion zur Aktualisierung des Ausleihbuttons, wenn eine
	 * Benachrichtigung vom Verleihservice auftaucht.
	 */
	private void registriereAusleihButtonAktualisierenAktion()
	{
		_verleihService.registriereBeobachter(new ServiceObserver()
		{

			@Override
			public void reagiereAufAenderung()
			{
				// try
				// {
				aktualisiereAusleihButton();
				// }
				// catch (ProtokollierException e)
				// {
				// JOptionPane.showMessageDialog(null, e,
				// "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
				// }
			}
		});
	}

	/**
	 * Registriert die Aktion, die ausgeführt wird, wenn auf den Ausleih-Button
	 * gedrückt wird.
	 */
	private void registriereAusleihAktion()
	{

		_ausleiheUI.getAusleihButton().setOnAction(event ->
		{
//			try
//			{
				leiheAusgewaehlteMedienAus();
//			}
//			catch (ProtokollierException e)
//			{
//
//				JOptionPane.showMessageDialog(null, e, "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
//			}
		});

	}

	/**
	 * Registiert die Aktion, die ausgeführt wird, wenn ein Kunde ausgewählt wird.
	 */
	private void registriereKundenAnzeigenAktion()
	{
		_kundenAuflisterWerkzeug.registriereBeobachter(new SubWerkzeugObserver()
		{
			@Override
			public void reagiereAufAenderung()
			{
				zeigeAusgewaehltenKunden();
				// try
				// {
				aktualisiereAusleihButton();
				// }
				// catch (ProtokollierException e)
				// {
				// JOptionPane.showMessageDialog(null, e,
				// "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
				// }
			}
		});
	}

	/**
	 * Registiert die Aktion, die ausgeführt wird, wenn ein Medium ausgewählt wird.
	 */
	private void registriereMedienAnzeigenAktion()
	{
		_medienAuflisterWerkzeug.registriereBeobachter(new SubWerkzeugObserver()
		{

			@Override
			public void reagiereAufAenderung()
			{
				zeigeAusgewaehlteMedien();
				// try
				// {
				aktualisiereAusleihButton();
				// }
				// catch (ProtokollierException e)
				// {
				// JOptionPane.showMessageDialog(null, e,
				// "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
				// }
			}
		});
	}

	/**
	 * Überprüft, ob die selektierten Medien ausgeleihen werden können und ob ein
	 * Kunde selektiert ist, an den ausgeliehen werden könnte.
	 * 
	 * @return true, wenn ausleihen möglich ist, sonst false.
	 */
	private boolean istAusleihenMoeglich() // throws ProtokollierException
	{
		List<Medium> medien = _medienAuflisterWerkzeug.getSelectedMedien();
		Kunde kunde = _kundenAuflisterWerkzeug.getSelectedKunde();
		boolean ausleiheMoeglich = (kunde != null) && !medien.isEmpty()
				&& _verleihService.sindAlleNichtVerliehen(medien);
		return ausleiheMoeglich;
	}

	/**
	 * Leiht die ausgewählten Medien aus. Diese Methode wird über einen Listener
	 * angestoßen, der reagiert, wenn der Benutzer den Ausleihen-Button drückt.
	 */
	private void leiheAusgewaehlteMedienAus()
	{
		List<Medium> selectedMedien = _medienAuflisterWerkzeug.getSelectedMedien();
		Kunde selectedKunde = _kundenAuflisterWerkzeug.getSelectedKunde();
		Datum heute = Datum.heute();
		
		try
		{
		_verleihService.verleiheAn(selectedKunde, selectedMedien, heute);
		}
		catch (ProtokollierException e)
		{

//			JOptionPane.showMessageDialog(null, e, "Fehlermeldung", JOptionPane.ERROR_MESSAGE);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ausleihen nicht möglich");
			alert.setHeaderText("Da protokollieren nicht möglich ist, ist das Ausleihen nicht möglich");
			alert.setContentText(e.toString());

			alert.showAndWait();
		}
	}

	/**
	 * Zeigt die Details der ausgewählten Medien.
	 */
	private void zeigeAusgewaehlteMedien()
	{
		List<Medium> selectedMedien = _medienAuflisterWerkzeug.getSelectedMedien();
		_medienDetailAnzeigerWerkzeug.setMedien(selectedMedien);
	}

	/**
	 * Zeigt die Details des ausgewählten Kunden (rechts im Fenster)
	 */
	private void zeigeAusgewaehltenKunden()
	{
		Kunde kunde = _kundenAuflisterWerkzeug.getSelectedKunde();
		_kundenDetailAnzeigerWerkzeug.setKunde(kunde);
	}

	/**
	 * Setzt den Ausleihbutton auf benutzbar (enabled) falls die gerade selektierten
	 * Medien alle ausgeliehen werden können und ein Kunde ausgewählt ist.
	 * 
	 * Wenn keine Medien selektiert sind oder wenn mindestes eines der selektierten
	 * Medien bereits ausgeliehen ist oder wenn kein Kunde ausgewählt ist, wird der
	 * Button ausgegraut.
	 * 
	 * @throws ProtokollierException
	 */
	private void aktualisiereAusleihButton() // throws ProtokollierException
	{
		boolean istAusleihenMoeglich = istAusleihenMoeglich();
		_ausleiheUI.getAusleihButton().setDisable(!istAusleihenMoeglich);
	}

	/**
	 * Liefert das Pane, das die UI-Komponente darstellt.
	 * 
	 * @return Das Pane, das die UI-Komponente darstellt.
	 * 
	 * @ensure result != null
	 */
	public Pane getUIPane()
	{
		return _ausleiheUI.getUIPane();
	}
}
