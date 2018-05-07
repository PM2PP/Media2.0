import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class AbstractVideospielTest
{
	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
	private static final String BEZEICHNUNG = "Videospiel";
	private static final String SYSTEM = "System";
	private AbstractVideospiel _aVideospiel;
	
	public AbstractVideospielTest()
	{
		_aVideospiel = getMedium();
	}

	@Test
	public void testeVideospiel()
	{
		assertEquals(TITEL, _aVideospiel.getTitel());
		assertEquals(KOMMENTAR, _aVideospiel.getKommentar());
		assertEquals(SYSTEM, _aVideospiel.getSystem());
	}

	@Test
	public void testGetMedienBezeichnung()
	{
		assertEquals(BEZEICHNUNG, _aVideospiel.getMedienBezeichnung());
	}

	protected abstract AbstractVideospiel getMedium();

	@Test
	public final void testSetKommentar()
	{
		AbstractVideospiel medium = getMedium();
		medium.setKommentar("Kommentar2");
		assertEquals(medium.getKommentar(), "Kommentar2");
	}

	@Test
	public final void testSetTitel()
	{
		AbstractVideospiel medium = getMedium();
		medium.setTitel("Titel2");
		assertEquals(medium.getTitel(), "Titel2");
	}

	@Test
	public abstract void testBerechneMietgebuehr();

}
