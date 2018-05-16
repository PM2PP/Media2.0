package de.hawhh.informatik.jprpab.mediathek.materialien.medien;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.hawhh.informatik.jprpab.mediathek.fachwerte.Geldbetrag;

public class CDTest extends AbstractMediumTest
{
	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
	private static final String BEZEICHNUNG = "CD";
	private static final String INTERPRET = "CD Interpret";
	private static final int LAENGE = 100;
	private CD _cd2;

	public CDTest()
	{
		// super Aufruf !!
		_cd2 = getMedium();
	}

	@Test
	@Override
	public void testGetMedienBezeichnung()
	{
		String cdBezeichnung = BEZEICHNUNG;
		assertEquals(cdBezeichnung, getMedium().getMedienBezeichnung());
	}

	@Test
	public void testKonstruktor()
	{
		super.testKonstruktor();
		assertEquals(LAENGE, getMedium().getSpiellaenge());
		assertEquals(INTERPRET, getMedium().getInterpret());
	}

	@Test
	/*
	 * Von ein und der selben CD kann es mehrere Exemplare geben, die von
	 * unterschiedlichen Personen ausgeliehen werden können.
	 */
	public void testEquals()
	{
		assertFalse("Mehrere Exemplare der gleichen CD sind ungleich", getMedium().equals(_cd2));
		assertTrue("Dasselbe Exemplare der gleichen CD ist gleich", _cd2.equals(_cd2));
	}

	protected CD getMedium()
	{
		return new CD(TITEL, KOMMENTAR, INTERPRET, LAENGE);
	}

	@Test
	public void testBerechneMietgebuehr()
	{
		assertEquals(getMedium().berechneMietgebuehr(1), Geldbetrag.get(300));
		assertEquals(getMedium().berechneMietgebuehr(3), Geldbetrag.get(900));
		assertEquals(getMedium().berechneMietgebuehr(10), Geldbetrag.get(3000));
		assertEquals(_cd2.berechneMietgebuehr(1), Geldbetrag.get(300));
		assertEquals(_cd2.berechneMietgebuehr(3), Geldbetrag.get(900));
		assertEquals(_cd2.berechneMietgebuehr(10), Geldbetrag.get(3000));
	}

}
