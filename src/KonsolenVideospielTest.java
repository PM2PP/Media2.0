import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 */
public class KonsolenVideospielTest extends AbstractVideospielTest
{
	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
	private static final String SYSTEM = "System";

	public KonsolenVideospielTest()
	{
		// Superaufruf ist parameterlos und wird automatisch aufgerufen
	}

	@Override
	protected KonsolenVideospiel getMedium()
	{
		return new KonsolenVideospiel(TITEL, KOMMENTAR, SYSTEM);
	}

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
