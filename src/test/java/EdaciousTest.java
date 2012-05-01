

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Edacious
 */
public class EdaciousTest
{
	@Before
	public void setUp()
	{
		//EdaciousFactory.setDebug(true);
	}

//	/** */
//	@Test
//	public void testgetOnetimeKey() throws Exception
//	{
//		String result = EdaciousFactory.getInstance().getOneTimeKey();
//		assertTrue(result.contains("-"));
//		assertEquals("4a8947d9-25e849fd", result);
//	}
//
//	/** */
//	@Test
//	public void testgetHash() throws Exception
//	{
//		String hash = EdaciousFactory.getInstance().getHash("foo", "bar");
//		assertEquals(32, hash.length());
//		assertEquals("ccf841f5cee2c1bd55e4219deeecaef2", hash);
//	}

//	@Test
//	public void testgetMeasureGroups() throws Exception
//	{
//		List<MeasureGroup> mg = EdaciousFactory.getInstance().getMeasureGroups("foo", "bar");
//		assertEquals(2, mg.size());
//	}

//	@Test
//	public void testgetUser() throws Exception
//	{
//		User user = EdaciousFactory.getInstance().getUser(1L, "bar");
//		System.out.println(user);
//		assertEquals(2, mg.size());
//	}

	@Test
	public void testgetUsers() throws Exception
	{
//		List<User> users = EdaciousFactory.getInstance().getUsers("foo", "bar");
//		System.out.println(users);
//		assertEquals(3, users.size());
	}
}
