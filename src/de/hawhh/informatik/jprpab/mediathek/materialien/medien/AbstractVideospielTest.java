package de.hawhh.informatik.jprpab.mediathek.materialien.medien;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class AbstractVideospielTest extends AbstractMediumTest
{
	private static final String BEZEICHNUNG = "Videospiel";
	private static final String SYSTEM = "System";

	public AbstractVideospielTest()
	{
		// super Aufruf !!
	}

	@Test
	@Override
	public void testKonstruktor()
	{
		super.testKonstruktor();
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

	@Test
	@Override
	public abstract void testBerechneMietgebuehr();

}
