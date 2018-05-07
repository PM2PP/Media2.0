import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PCVideospielTest extends AbstractVideospielTest
{

	private static final String KOMMENTAR = "Kommentar";
	private static final String TITEL = "Titel";
//	private static final String BEZEICHNUNG = "Videospiel";
	private static final String SYSTEM = "System";
//	private PCVideospiel _pcVideoSpiel;

	public PCVideospielTest()
	{
		//Superaufruf ist parameterlos und wird automatisch aufgerufen
//		_pcVideoSpiel = getMedium();
	}

//	@Test
//	public void testeVideospiel()
//	{
//		assertEquals(TITEL, _pcVideoSpiel.getTitel());
//		assertEquals(KOMMENTAR, _pcVideoSpiel.getKommentar());
//		assertEquals(SYSTEM, _pcVideoSpiel.getSystem());
//	}
//
//	@Test
//	public void testGetMedienBezeichnung()
//	{
//		assertEquals(BEZEICHNUNG, _pcVideoSpiel.getMedienBezeichnung());
//	}

	@Override
	protected PCVideospiel getMedium()
	{
		return new PCVideospiel(TITEL, KOMMENTAR, SYSTEM);
	}
//
//	@Test
//	public final void testSetKommentar()
//	{
//		PCVideospiel medium = getMedium();
//		medium.setKommentar("Kommentar2");
//		assertEquals(medium.getKommentar(), "Kommentar2");
//	}
//
//	@Test
//	public final void testSetTitel()
//	{
//		PCVideospiel medium = getMedium();
//		medium.setTitel("Titel2");
//		assertEquals(medium.getTitel(), "Titel2");
//	}

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
