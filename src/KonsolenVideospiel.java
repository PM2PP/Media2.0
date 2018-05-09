
class KonsolenVideospiel extends AbstractVideospiel
{

	public KonsolenVideospiel(String titel, String kommentar, String system)
	{
		super(titel, kommentar, system, "Konsolenvideospiel");
	}

//	public int getPreisNachTagen(int tage)
//	{
//		int preis = 0;
//
//		if (tage > 3)
//		{
//			if ((tage - 1) % 3 == 0)
//			{
//				preis = (tage / 3) * 700;
//			}
//
//			else if ((tage - 1) % 3 == 1)
//			{
//				if (tage % 3 == 2)
//				{
//					preis = (tage / 3) * 700;
//				}
//				else
//				{
//					preis = ((tage / 3) - 1) * 700;
//				}
//
//			}
//			else
//			{
//				if (tage % 3 == 0)
//				{
//					preis = ((tage / 3) - 1) * 700;
//				}
//				else
//				{
//					preis = ((tage / 3) - 2) * 700;
//				}
//			}
//		}
//
//		return preis;

//	}
	public int getPreisNachTagen(int tage)
	{
		int preis = 0;
		while(tage > 3)
		{
			preis += 700;
			tage -= 3;
		}
		return preis;
	}
//	return tage/3 * 700;
}
