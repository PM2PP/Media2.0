import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PCVideospielTest
{

	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
	private static final String BEZEICHNUNG = "Videospiel";
	private static final String SYSTEM = "System";
	private PCVideospiel _pcVideoSpiel;

	public PCVideospielTest()
	{
		_pcVideoSpiel = getMedium();
	}

	@Test
	public void testeVideospiel()
	{
		assertEquals(TITEL, _pcVideoSpiel.getTitel());
		assertEquals(KOMMENTAR, _pcVideoSpiel.getKommentar());
		assertEquals(SYSTEM, _pcVideoSpiel.getSystem());
	}

	@Test
	public void testGetMedienBezeichnung()
	{
		assertEquals(BEZEICHNUNG, _pcVideoSpiel.getMedienBezeichnung());
	}

	protected PCVideospiel getMedium()
	{
		return new PCVideospiel(TITEL, KOMMENTAR, SYSTEM);
	}

	@Test
	public final void testSetKommentar()
	{
		PCVideospiel medium = getMedium();
		medium.setKommentar("Kommentar2");
		assertEquals(medium.getKommentar(), "Kommentar2");
	}

	@Test
	public final void testSetTitel()
	{
		PCVideospiel medium = getMedium();
		medium.setTitel("Titel2");
		assertEquals(medium.getTitel(), "Titel2");
	}

	@Test
	public void testBerechneMietgebuehr()
	{
		assertEquals(_pcVideoSpiel.berechneMietgebuehr(1), Geldbetrag.get(0));
		assertEquals(_pcVideoSpiel.berechneMietgebuehr(7), Geldbetrag.get(0));
		assertEquals(_pcVideoSpiel.berechneMietgebuehr(8), Geldbetrag.get(500));
		assertEquals(_pcVideoSpiel.berechneMietgebuehr(11), Geldbetrag.get(500));
		assertEquals(_pcVideoSpiel.berechneMietgebuehr(12), Geldbetrag.get(1000));
		assertEquals(_pcVideoSpiel.berechneMietgebuehr(20), Geldbetrag.get(2000));
		assertEquals(_pcVideoSpiel.berechneMietgebuehr(27), Geldbetrag.get(2500));
		assertEquals(_pcVideoSpiel.berechneMietgebuehr(28), Geldbetrag.get(3000));
	}

}
