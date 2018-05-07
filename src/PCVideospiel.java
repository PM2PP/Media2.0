
class PCVideospiel extends AbstractVideospiel
{

	public PCVideospiel(String titel, String kommentar, String system)
	{
		super(titel, kommentar, system);
	}

//	public int getPreisNachTagen(int tage)
//	{
//		int preis = 0;
//
//		if (tage > 7)
//		{
//			if ((tage % 4) == 0)
//			{
//				preis = (tage / 4 - 1) * 500;
//			}
//			else if ((tage % 4) == 1)
//			{
//				preis = (((tage - 1) / 4) - 1) * 500;
//			}
//			else if ((tage % 4) == 2)
//			{
//				preis = (((tage - 2) / 4) - 1) * 500;
//			}
//			else
//			{
//				preis = (((tage - 3) / 4) - 1) * 500;
//			}
//		}
//		return preis;
//	}
	public int getPreisNachTagen(int tage)
	{
		int preis = 0;
		while(tage > 7)
		{
			preis += 500;
			tage -= 4;
		}
		return preis;
	}
}
