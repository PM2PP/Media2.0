package de.hawhh.informatik.jprpab.mediathek.service.verleih;
import java.io.FileWriter;
import java.io.IOException;

import de.hawhh.informatik.jprpab.mediathek.materialien.verleihkarte.Verleihkarte;

public class VerleihProtokollierer
{
	FileWriter _filewriter;

	public VerleihProtokollierer()
	{
		try
		{
			// Beispiele für Fehler, wenn ungültiger Pfad statt "./protokoll.txt" angegeben
			// wird:
			// C:\\Users\\Philip\\Documents -> dafür z.B. Zugriff verweigert, da ungeschützt
			// D:\\ -> Laufwerk D existiert bei Surface nicht, daher ebenfalls Fehler
			// Anmerkung: Zwei Backslash in einem String, also "\\" stehen für einen
			// Backslash \
			_filewriter = new FileWriter("./protokoll.", true); // false falls beim ersten Mal alles überschrieben
										 						// werden soll
		}
		catch (IOException e)
		{
			System.err.println(e);
			// e.printStackTrace();
		}
	}

	/**
	 * Speichert einen Verleihvorgang.
	 * 
	 * @param ereignis
	 *            Hält den Verleihvorgang fest (Ausleihe oder Rückgabe).
	 * @param verleihkarte
	 *            Die Verleihkarte zum zugehörigen Medium, welches ausgeliehen wird.
	 * 
	 * @require ereignis != null
	 * @require ereignis.equals("Ausleihe") || ereignis.equals("Rückgabe")
	 * @require verleihkarte != null
	 */
	public void protokolliere(String ereignis, Verleihkarte verleihkarte) throws ProtokollierException
	{
		assert ereignis != null : "Vorbedingung verletzt: ereignis != null";
		assert ereignis.equals("Ausleihe") || ereignis.equals(
				"Rückgabe") : "Vorbedingung verletzt: ereignis.equals(\"Ausleihe\") || ereignis.equals(\"Rückgabe\")";
		assert verleihkarte != null : "Vorbedingung verletzt: verleihkarte != null";

		try (FileWriter filewriter = new FileWriter("D:\\", true))
		{
			filewriter.write(ereignis + ": " + verleihkarte.getFormatiertenString());
			//filewriter.close();
			// _filewriter = new FileWriter("./protokoll.txt", true);
		}
		catch (IOException e)
		{
			throw new ProtokollierException(e.toString());
			// System.err.println(e);
		}
	}

}