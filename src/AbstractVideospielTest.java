import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class AbstractVideospielTest extends AbstractMediumTest
{
//	private static final String KOMMENTAR = "Kommentar";
//	private static final String TITEL = "Titel";
	private static final String BEZEICHNUNG = "Videospiel";
	private static final String SYSTEM = "System";
//	private AbstractVideospiel _aVideospiel;
	
	public AbstractVideospielTest()
	{
		//_aVideospiel = getMedium();
	}

	@Test
	@Override
	public void testKonstruktor()
	{
		super.testKonstruktor();
//		assertEquals(TITEL, _aVideospiel.getTitel());
//		assertEquals(KOMMENTAR, _aVideospiel.getKommentar());
		assertEquals(SYSTEM, getMedium().getSystem());
	}

	@Test
	@Override
	public void testGetMedienBezeichnung()
	{
		assertEquals(BEZEICHNUNG, getMedium().getMedienBezeichnung());
	}

	@Override
	protected abstract AbstractVideospiel getMedium();

//	@Test
//	public final void testSetKommentar()
//	{
//		AbstractVideospiel medium = getMedium();
//		medium.setKommentar("Kommentar2");
//		assertEquals(medium.getKommentar(), "Kommentar2");
//	}

//	@Test
//	public final void testSetTitel()
//	{
//		AbstractVideospiel medium = getMedium();
//		medium.setTitel("Titel2");
//		assertEquals(medium.getTitel(), "Titel2");
//	}

	@Test
	@Override
	public abstract void testBerechneMietgebuehr();

}
