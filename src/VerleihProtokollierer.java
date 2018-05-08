import java.io.FileWriter;
import java.io.IOException;

public class VerleihProtokollierer
{
	FileWriter _filewriter;
	
	public VerleihProtokollierer()
	{ 
		try
		{
			//Beispiele für Fehler, wenn ungültiger Pfad statt "./protokoll.txt" angegeben wird:
			//C:\\Users\\Philip\\Documents -> dafür z.B. Zugriff verweigert, da ungeschützt
			//D:\\ -> Laufwerk D existiert bei Surface nicht, daher ebenfalls Fehler
			//Anmerkung: Zwei Backslash in einem String, also "\\" stehen für einen Backslash \
			_filewriter = new FileWriter("./protokoll.txt", true); //false falls beim ersten Mal alles überschrieben werden soll
		}
		catch (IOException e)
		{
			System.err.println(e);
//			e.printStackTrace();
		}
	}
	
	/**
	 * Speichert einen Verleihvorgang.
	 * 
	 * @param ereignis Hält den Verleihvorgang fest (Ausleihe oder Rückgabe).
	 * @param verleihkarte Die Verleihkarte zum zugehörigen Medium, welches ausgeliehen wird.
	 * 
	 * @require ereignis != null
	 * @require ereignis.equals("Ausleihe") || ereignis.equals("Rückgabe")
	 * @require verleihkarte != null
	 */
	public void protokolliere(String ereignis, Verleihkarte verleihkarte)
	{
		assert ereignis != null : "Vorbedingung verletzt: ereignis != null";
		assert ereignis.equals("Ausleihe") || ereignis.equals("Rückgabe") : "Vorbedingung verletzt: ereignis.equals(\"Ausleihe\") || ereignis.equals(\"Rückgabe\")";
		assert verleihkarte != null : "Vorbedingung verletzt: verleihkarte != null";
			
		try
		{
			_filewriter = new FileWriter("./protokoll.txt", true);
			_filewriter.write(ereignis + ": " + verleihkarte.getFormatiertenString());
			_filewriter.close();
//			_filewriter = new FileWriter("./protokoll.txt", true);
		}
		catch (IOException e)
		{
			System.err.println(e);
//			e.printStackTrace();
		}
//		finally
//		{
//			try
//			{
//				_filewriter.close();
//			}
//			catch(IOException e)
//			{
//				System.err.println(e);
//			}
//		}
		
		
//		System.out.println(ereignis + ": " + verleihkarte.getFormatiertenString());
	}

}