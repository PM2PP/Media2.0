package de.hawhh.informatik.jprpab.mediathek.materialien.medien;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.hawhh.informatik.jprpab.mediathek.fachwerte.Geldbetrag;

public class DVDTest extends AbstractMediumTest
{
	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
	private static final String BEZEICHNUNG = "DVD";
	private static final int LAENGE = 100;
	private static final String REGISSEUR = "DVD Regisseur";
	private DVD _dvd2;

	public DVDTest()
	{
		// super Aufruf !!
		_dvd2 = getMedium();
	}

	@Test
	@Override
	public void testKonstruktor()
	{
		super.testKonstruktor();
		assertEquals(LAENGE, getMedium().getLaufzeit());
		assertEquals(REGISSEUR, getMedium().getRegisseur());
	}

	@Test
	/*
	 * Von ein und der selben DVD kann es mehrere Exemplare geben, die von
	 * unterschiedlichen Personen ausgeliehen werden können.
	 */
	public void testEquals()
	{
		assertFalse("Mehrere Exemplare der gleichen DVD sind gleich", getMedium().equals(_dvd2));
		assertTrue("Mehrere Exemplare der gleichen DVD sind ungleich", _dvd2.equals(_dvd2));
	}

	@Override
	protected DVD getMedium()
	{
		return new DVD(TITEL, KOMMENTAR, REGISSEUR, LAENGE);
	}

	@Test
	@Override
	public void testBerechneMietgebuehr()
	{
		assertEquals(getMedium().berechneMietgebuehr(1), Geldbetrag.get(300));
		assertEquals(getMedium().berechneMietgebuehr(3), Geldbetrag.get(900));
		assertEquals(getMedium().berechneMietgebuehr(10), Geldbetrag.get(3000));
		assertEquals(_dvd2.berechneMietgebuehr(1), Geldbetrag.get(300));
		assertEquals(_dvd2.berechneMietgebuehr(3), Geldbetrag.get(900));
		assertEquals(_dvd2.berechneMietgebuehr(10), Geldbetrag.get(3000));
	}

	@Override
	public void testGetMedienBezeichnung()
	{
		assertEquals(BEZEICHNUNG, getMedium().getMedienBezeichnung());
	}

}
