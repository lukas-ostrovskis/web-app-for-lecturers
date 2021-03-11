package nl.tudelft.oopp.demo;

import nl.tudelft.oopp.demo.entities.Reply;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReplyTest {

	private static Reply reply1;
	private static Reply reply2;
	private static String id;
	private static String ownerId;
	private static String questionId;
	private static String content;
	
	@BeforeAll
	static void init() {
		id = "xScGkU9fyDCcLtkp";
		ownerId = "zwXK5JPGeaGeerSu";
		questionId = "BMjuKd75rp37unqu";
		content = "Lorem ipsum dolor sit amet";
		reply1 = new Reply(id, ownerId, questionId, content);
		reply2 = new Reply(id, ownerId, questionId, content);
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(reply1);
	}
	
	@Test
	public void getIdTest() {
		assertEquals("xScGkU9fyDCcLtkp", reply1.getId());
	}
	
	@Test
	public void getOwnerIdTest() {
		assertEquals("zwXK5JPGeaGeerSu", reply1.getOwnerId());
	}
	
	@Test
	public void getQuestionIdTest() {
		assertEquals("BMjuKd75rp37unqu", reply1.getQuestionId());
	}
	
	@Test
	public void getContentTest() {
		assertEquals("Lorem ipsum dolor sit amet", reply1.getContent());
	}
	
	@Test
	public void equalsTest() {
		assertTrue(reply1.equals(reply2));
	}
}
