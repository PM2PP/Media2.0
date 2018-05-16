package de.hawhh.informatik.jprpab.mediathek.materialien.medien;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import de.hawhh.informatik.jprpab.mediathek.fachwerte.Geldbetrag;

public class PCVideospielTest extends AbstractVideospielTest
{

	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
	private static final String SYSTEM = "System";

	public PCVideospielTest()
	{
		//Superaufruf ist parameterlos und wird automatisch aufgerufen
	}

	@Override
	protected PCVideospiel getMedium()
	{
		return new PCVideospiel(TITEL, KOMMENTAR, SYSTEM);
	}

	@Test
	@Override
	public void testBerechneMietgebuehr()
	{
		assertEquals(getMedium().berechneMietgebuehr(1), Geldbetrag.get(200));
		assertEquals(getMedium().berechneMietgebuehr(7), Geldbetrag.get(200));
		assertEquals(getMedium().berechneMietgebuehr(8), Geldbetrag.get(700));
		assertEquals(getMedium().berechneMietgebuehr(11), Geldbetrag.get(700));
		assertEquals(getMedium().berechneMietgebuehr(12), Geldbetrag.get(1200));
		assertEquals(getMedium().berechneMietgebuehr(13), Geldbetrag.get(1200));
		assertEquals(getMedium().berechneMietgebuehr(14), Geldbetrag.get(1200));
		assertEquals(getMedium().berechneMietgebuehr(17), Geldbetrag.get(1700));
		assertEquals(getMedium().berechneMietgebuehr(23), Geldbetrag.get(2200));
		assertEquals(getMedium().berechneMietgebuehr(27), Geldbetrag.get(2700));
		assertEquals(getMedium().berechneMietgebuehr(28), Geldbetrag.get(3200));
	}

}
