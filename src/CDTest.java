import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CDTest
{
	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
	private static final String CD_BEZEICHNUNG = "CD";
	private static final String INTERPRET = "CD Interpret";
	private static final int LAENGE = 100;
	private CD _cd1;
	private CD _cd2;

	public CDTest()
	{
		_cd1 = getMedium();
		_cd2 = getMedium();
	}

	@Test
	public void testGetMedienBezeichnung()
	{
		String cdBezeichnung = CD_BEZEICHNUNG;
		assertEquals(cdBezeichnung, _cd1.getMedienBezeichnung());
	}

	@Test
	public void testKonstruktor()
	{
		assertEquals(TITEL, _cd1.getTitel());
		assertEquals(KOMMENTAR, _cd1.getKommentar());
		assertEquals(LAENGE, _cd1.getSpiellaenge());
		assertEquals(INTERPRET, _cd1.getInterpret());
	}

	@Test
	public final void testSetter()
	{
		_cd1.setTitel("Titel2");
		assertEquals(_cd1.getTitel(), "Titel2");
		_cd1.setKommentar("Kommentar2");
		assertEquals(_cd1.getKommentar(), "Kommentar2");
		_cd1.setInterpret("Interpret2");
		assertEquals(_cd1.getInterpret(), "Interpret2");
		_cd1.setSpiellaenge(99);
		assertEquals(_cd1.getSpiellaenge(), 99);
	}

	@Test
	/*
	 * Von ein und der selben CD kann es mehrere Exemplare geben, die von
	 * unterschiedlichen Personen ausgeliehen werden können.
	 */
	public void testEquals()
	{
		assertFalse("Mehrere Exemplare der gleichen CD sind ungleich", _cd1.equals(_cd2));
		assertTrue("Dasselbe Exemplare der gleichen CD ist gleich", _cd1.equals(_cd1));
	}

	@Test
	public final void testGetFormatiertenString()
	{
		Medium medium = getMedium();
		assertNotNull(medium.getFormatiertenString());
	}

	private CD getMedium()
	{
		return new CD(TITEL, KOMMENTAR, INTERPRET, LAENGE);
	}

	@Test
	public void testBerechneMietgebuehr()
	{
		assertEquals(_cd1.berechneMietgebuehr(1), Geldbetrag.get(300));
		assertEquals(_cd1.berechneMietgebuehr(3), Geldbetrag.get(900));
		assertEquals(_cd1.berechneMietgebuehr(10), Geldbetrag.get(3000));
		assertEquals(_cd2.berechneMietgebuehr(1), Geldbetrag.get(300));
		assertEquals(_cd2.berechneMietgebuehr(3), Geldbetrag.get(900));
		assertEquals(_cd2.berechneMietgebuehr(10), Geldbetrag.get(3000));
	}

}
