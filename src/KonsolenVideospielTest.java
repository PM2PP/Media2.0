import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 */
public class KonsolenVideospielTest
{
	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
	private static final String BEZEICHNUNG = "Videospiel";
	private static final String SYSTEM = "System";
	private KonsolenVideospiel _kVideoSpiel;

	public KonsolenVideospielTest()
	{
		_kVideoSpiel = getMedium();
	}

	@Test
	public void testeVideospiel()
	{
		assertEquals(TITEL, _kVideoSpiel.getTitel());
		assertEquals(KOMMENTAR, _kVideoSpiel.getKommentar());
		assertEquals(SYSTEM, _kVideoSpiel.getSystem());
	}

	@Test
	public void testGetMedienBezeichnung()
	{
		assertEquals(BEZEICHNUNG, _kVideoSpiel.getMedienBezeichnung());
	}

	protected KonsolenVideospiel getMedium()
	{
		return new KonsolenVideospiel(TITEL, KOMMENTAR, SYSTEM);
	}

	@Test
	public final void testSetKommentar()
	{
		KonsolenVideospiel medium = getMedium();
		medium.setKommentar("Kommentar2");
		assertEquals(medium.getKommentar(), "Kommentar2");
	}

	@Test
	public final void testSetTitel()
	{
		KonsolenVideospiel medium = getMedium();
		medium.setTitel("Titel2");
		assertEquals(medium.getTitel(), "Titel2");
	}

	@Test
	public void testBerechneMietgebuehr()
	{
		assertEquals(_kVideoSpiel.berechneMietgebuehr(1), Geldbetrag.get(200));
		assertEquals(_kVideoSpiel.berechneMietgebuehr(4), Geldbetrag.get(900));
		assertEquals(_kVideoSpiel.berechneMietgebuehr(7), Geldbetrag.get(1600));
		assertEquals(_kVideoSpiel.berechneMietgebuehr(9), Geldbetrag.get(1600));
		assertEquals(_kVideoSpiel.berechneMietgebuehr(10), Geldbetrag.get(2300));
		assertEquals(_kVideoSpiel.berechneMietgebuehr(19), Geldbetrag.get(4500));
		assertEquals(_kVideoSpiel.berechneMietgebuehr(27), Geldbetrag.get(5900));
		assertEquals(_kVideoSpiel.berechneMietgebuehr(28), Geldbetrag.get(6600));
	}

}
