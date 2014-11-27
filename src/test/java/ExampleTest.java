import org.junit.*;
import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;
import static org.junit.Assert.assertEquals;

public class ExampleTest {
	public ExampleTest() {
		System.err.println("<init>");
		System.err.flush();
	}

	@BeforeClass
	public static void beforeClass() {
		System.err.println("beforeClass");
		System.err.flush();
	}

	@AfterClass
	public static void afterClass() {
		System.err.println("afterClass");
		System.err.flush();
	}

	@Before
	public void setUp() throws Exception {
		System.err.println("setUp");
		System.err.flush();
	}

	@After
	public void tearDown() throws Exception {
		System.err.println("tearDown");
		System.err.flush();
	}

	@Test
	public void substring_should_start_from_0() {
		assertEquals("substring result", "llo", "Hello".substring(2));
		assert 1 + 1 == 2 : "Arithmetics broken";
	}

	@Test(expected = StringIndexOutOfBoundsException.class)
	public void substring_should_throw_exception_for_invalid_index() {
		"".substring(1);
	}

	@Test(timeout = 2000)
	public void should_work_in_less_than_one_second()
			throws InterruptedException {
		Thread.sleep(1000);
	}

	@Test
	public void collection_constructor_should_copy_all_content() {
		Map<String, String> originalMap = new HashMap<String, String>();
		originalMap.put("a", "b");
		originalMap.put("c", "d");

		Map<String, String> copiedMap = new HashMap<String, String>(originalMap);

		assertThat(copiedMap).hasSize(2).isEqualTo(originalMap)
				.isNotSameAs(originalMap);

		originalMap.put("e", "f");

		assertThat(copiedMap).hasSize(2).excludes(entry("e", "f"))
				.isNotEqualTo(originalMap);
	}
}