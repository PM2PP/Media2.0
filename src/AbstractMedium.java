
abstract class AbstractMedium implements Medium
{

	/**
	 * Ein Kommentar zum Medium
	 */
	private String _kommentar;

	/**
	 * Der Titel des Mediums
	 */
	private String _titel;

	/**
	 * Initialisiert ein neues Exemplar.
	 * 
	 * @param titel
	 *            Der Titel des Mediums
	 * @param kommentar
	 *            Ein Kommentar zu dem Medium
	 * 
	 * @require titel != null
	 * @require kommentar != null
	 * 
	 * @ensure getTitel() == titel
	 * @ensure getKommentar() == kommentar
	 */
	public AbstractMedium(String titel, String kommentar)
	{
		assert titel != null : "Vorbedingung verletzt: titel != null";
		assert kommentar != null : "Vorbedingung verletzt: kommentar != null";
		_titel = titel;
		_kommentar = kommentar;
	}

	public abstract String getMedienBezeichnung();

	public String getKommentar()
	{
		return _kommentar;
	}

	public void setKommentar(String kommentar)
	{
		assert kommentar != null : "Vorbedingung verletzt: kommentar != null";
		_kommentar = kommentar;
	}

	public String getTitel()
	{
		return _titel;
	}

	public void setTitel(String titel)
	{
		assert titel != null : "Vorbedingung verletzt: titel != null";
		_titel = titel;
	}

	public String getFormatiertenString()
	{
		return getMedienBezeichnung() + ":\n" + "    " + "Titel: " + _titel + "\n" + "    " + "Kommentar: "
				+ _kommentar;
	}

	public Geldbetrag berechneMietgebuehr(int mietTage)
	{
		return Geldbetrag.get(mietTage * 300);
	}
}
