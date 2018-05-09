import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public abstract class AbstractMediumTest
{
	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
	private static Medium _aMedium;

	public AbstractMediumTest()
	{
		_aMedium = getMedium();
	}

	@Test
	public abstract void testGetMedienBezeichnung();

	@Test
	public void testKonstruktor()
	{
		assertEquals(TITEL, _aMedium.getTitel());
		assertEquals(KOMMENTAR, _aMedium.getKommentar());
	}

	@Test
	public final void testSetter()
	{
		_aMedium.setTitel("Titel2");
		assertEquals(_aMedium.getTitel(), "Titel2");
		_aMedium.setKommentar("Kommentar2");
		assertEquals(_aMedium.getKommentar(), "Kommentar2");
	}

	@Test
	public final void testGetFormatiertenString()
	{
		Medium medium = getMedium();
		assertNotNull(medium.getFormatiertenString());
	}

	protected abstract Medium getMedium();

	@Test
	public abstract void testBerechneMietgebuehr();

}
