
class KonsolenVideospiel extends AbstractVideospiel
{

	public KonsolenVideospiel(String titel, String kommentar, String system)
	{
		super(titel, kommentar, system);
	}

	public int getPreisNachTagen(int tage)
	{
		int preis = 0;

		if (tage > 3)
		{
			if ((tage - 1) % 3 == 0)
			{
				preis = (tage / 3) * 700;
			}

			else if ((tage - 1) % 3 == 1)
			{
				if (tage % 3 == 2)
				{
					preis = (tage / 3) * 700;
				}
				else
				{
					preis = ((tage / 3) - 1) * 700;
				}

			}
			else
			{
				if (tage % 3 == 0)
				{
					preis = ((tage / 3) - 1) * 700;
				}
				else
				{
					preis = ((tage / 3) - 2) * 700;
				}
			}
		}

		return preis;

		// if (tage > 3)
		// {
		// if ((tage % 3 == 0))
		// {
		// preis = ((tage / 3) - 1) * 700;
		// }
		//
		// else if ((tage) % 3 == 1)
		// {
		// preis = ((tage / 3) - 2) * 700;
		//
		// }
		// else
		// {
		// preis = (tage / 3) * 700;
		// }
		// }
		//
		// return preis;
	}
}
