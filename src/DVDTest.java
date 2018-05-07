import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DVDTest extends AbstractMediumTest
{
    private static final String KOMMENTAR = "Kommentar";
    private static final String TITEL = "Titel";
    private static final String BEZEICHNUNG = "DVD";
    private static final int LAENGE = 100;
    private static final String REGISSEUR = "DVD Regisseur";
//    private DVD _dvd1;
    private DVD _dvd2;

    public DVDTest()
    {
//        _dvd1 = getMedium();
        _dvd2 = getMedium(); 
    }

//    @Test
//    @Override
//    public void testGetMedienBezeichnung()
//    {
//        String dvdBezeichnung = BEZEICHNUNG;
//        assertEquals(dvdBezeichnung, _dvd1.getMedienBezeichnung());
//    }

    @Test
    @Override
    public void testKonstruktor()
    {
    	super.testKonstruktor();
//        assertEquals(TITEL, _dvd1.getTitel());
//        assertEquals(KOMMENTAR, _dvd1.getKommentar());
        assertEquals(LAENGE, getMedium().getLaufzeit());
        assertEquals(REGISSEUR, getMedium().getRegisseur());
    }

//    @Test
//    public final void testSetter()
//    {
//        _dvd1.setKommentar("Kommentar2");
//        assertEquals(_dvd1.getKommentar(), "Kommentar2");
//        _dvd1.setTitel("Titel2");
//        assertEquals(_dvd1.getTitel(), "Titel2");
//        _dvd1.setLaufzeit(90);
//        assertEquals(90, _dvd1.getLaufzeit());
//        _dvd1.setRegisseur("Regisseur2");
//        assertEquals("Regisseur2", _dvd1.getRegisseur());
//    }

    @Test
    /*
     * Von ein und der selben DVD kann es mehrere Exemplare geben, die von
     * unterschiedlichen Personen ausgeliehen werden können.
     */
    public void testEquals()
    {
        assertFalse("Mehrere Exemplare der gleichen DVD sind gleich",
                getMedium().equals(_dvd2));
        assertTrue("Mehrere Exemplare der gleichen DVD sind ungleich",
                _dvd2.equals(_dvd2));
    }

//    @Test
//    public final void testGetFormatiertenString()
//    {
//        Medium medium = getMedium();
//        assertNotNull(medium.getFormatiertenString());
//    }

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
