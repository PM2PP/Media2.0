/**
 * {@link Videospiel} ist ein {@link Medium} mit einer zusätzlichen
 * Informationen zum kompatiblen System.
 * 
 * @author SE2-Team, PR2-Team, PR2-Team
 * @version SoSe 2018
 */
abstract class AbstractVideospiel extends AbstractMedium
{
	/**
	 * Das System, auf dem das Spiel lauffähig ist
	 */
	private String _system;

	public static final int _basispreis = 200; 
	/**
	 * Initialisiert ein neues Videospiel.
	 * 
	 * @param system
	 *            Die Bezeichnung des System
	 * 
	 * @param titel
	 *            Der Titel des Viedeospiels.
	 * @param kommentar
	 *            Ein Kommentar zu des Videospiels.
	 * 
	 * @require system != null
	 * 
	 * @ensure getSystem() == system
	 */
	protected AbstractVideospiel(String titel, String kommentar, String system, String medienbezeichnung)
	{
		super(titel, kommentar, medienbezeichnung);
		assert system != null : "Vorbedingung verletzt: system != null";
		_system = system;
	}

//	@Override
//	public String getMedienBezeichnung()
//	{
//		return "Videospiel";
//	}

	/**
	 * Gibt das System zurück, auf dem das Spiel lauffähig ist.
	 * 
	 * @return Das System, auf dem das Spiel ausgeführt werden kann.
	 * 
	 * @ensure result != null
	 */
	public String getSystem()
	{
		return _system;
	}

	@Override
	public String toString()
	{
		return getFormatiertenString(); 
	}

	@Override
	public String getFormatiertenString()
	{
		return super.getFormatiertenString() + "\n" + "    " + "System: " + _system + "\n";
	}
	
	@Override
	public Geldbetrag berechneMietgebuehr(int mietTage)
	{
		return Geldbetrag.get(getPreisNachTagen(mietTage) + _basispreis);
		
	}
	
	protected abstract int getPreisNachTagen(int tage);
	
}
