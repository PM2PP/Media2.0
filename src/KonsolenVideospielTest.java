import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 */
public class KonsolenVideospielTest extends AbstractVideospielTest
{
	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
//	private static final String BEZEICHNUNG = "Videospiel";
	private static final String SYSTEM = "System";
//	private KonsolenVideospiel _kVideoSpiel;

	public KonsolenVideospielTest()
	{
		//Superaufruf ist parameterlos und wird automatisch aufgerufen
//		_kVideoSpiel = getMedium();
	}

//	@Test
//	public void testeVideospiel()
//	{
//		assertEquals(TITEL, _kVideoSpiel.getTitel());
//		assertEquals(KOMMENTAR, _kVideoSpiel.getKommentar());
//		assertEquals(SYSTEM, _kVideoSpiel.getSystem());
//	}
//
//	@Test
//	public void testGetMedienBezeichnung()
//	{
//		assertEquals(BEZEICHNUNG, _kVideoSpiel.getMedienBezeichnung());
//	}

	protected KonsolenVideospiel getMedium()
	{
		return new KonsolenVideospiel(TITEL, KOMMENTAR, SYSTEM);
	}

//	@Test
//	public final void testSetKommentar()
//	{
//		KonsolenVideospiel medium = getMedium();
//		medium.setKommentar("Kommentar2");
//		assertEquals(medium.getKommentar(), "Kommentar2");
//	}
//
//	@Test
//	public final void testSetTitel()
//	{
//		KonsolenVideospiel medium = getMedium();
//		medium.setTitel("Titel2");
//		assertEquals(medium.getTitel(), "Titel2");
//	}

	@Test
	public void testBerechneMietgebuehr()
	{
		assertEquals(getMedium().berechneMietgebuehr(1), Geldbetrag.get(200));
		assertEquals(getMedium().berechneMietgebuehr(4), Geldbetrag.get(900));
		assertEquals(getMedium().berechneMietgebuehr(6), Geldbetrag.get(900));
		assertEquals(getMedium().berechneMietgebuehr(7), Geldbetrag.get(1600));
		assertEquals(getMedium().berechneMietgebuehr(8), Geldbetrag.get(1600));
		assertEquals(getMedium().berechneMietgebuehr(9), Geldbetrag.get(1600));
		assertEquals(getMedium().berechneMietgebuehr(10), Geldbetrag.get(2300));
		assertEquals(getMedium().berechneMietgebuehr(11), Geldbetrag.get(2300));
		assertEquals(getMedium().berechneMietgebuehr(17), Geldbetrag.get(3700));
		assertEquals(getMedium().berechneMietgebuehr(18), Geldbetrag.get(3700));
		assertEquals(getMedium().berechneMietgebuehr(19), Geldbetrag.get(4400));
		assertEquals(getMedium().berechneMietgebuehr(27), Geldbetrag.get(5800));
		assertEquals(getMedium().berechneMietgebuehr(28), Geldbetrag.get(6500));
	}

}
