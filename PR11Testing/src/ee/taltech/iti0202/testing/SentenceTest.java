package ee.taltech.iti0202.testing;

import ee.taltech.iti0202.sentence.Sentence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SentenceTest {

    @Test
    @After
    public void addWord_thenRemoveWord_allBasicCases() {
        Sentence s1 = new Sentence();
        assertTrue(s1.addWord("hello"));
        assertEquals("Hello...", s1.toString());

        assertTrue(s1.addWord("world"));
        assertEquals("Hello world...", s1.toString());

        assertTrue(s1.addPunctuation("??"));
        assertEquals("Hello world??", s1.toString());
        assertFalse(s1.addWord("NO"));
        assertFalse(s1.addPunctuation("."));

        assertTrue(s1.removePunctuation());
        assertTrue(s1.removeWord("hello"));
        assertEquals("World...", s1.toString());

        assertTrue(s1.removeWord("world"));
        assertEquals("", s1.toString());

        assertFalse(s1.addPunctuation("wat?"));

        assertTrue(s1.addWord("??"));
        assertTrue(s1.addPunctuation("hello"));
        assertEquals("??hello", s1.toString());

        Sentence s7 = new Sentence(" hello     world    yes?");
        assertEquals("Hello world yes?", s7.toString());
        assertFalse(s7.addWord("CANNOT"));
    }

    @Test
    @Before
    public void addPunctuation_manipulatePunctuation_basicPunctuation() {
        Sentence s1 = new Sentence("hello world");
        assertEquals("Hello world...", s1.toString());

        Sentence s2 = new Sentence("Hello world");
        assertEquals(s1.toString(), s2.toString());

        Sentence s3 = new Sentence("Hello world!");
        assertNotEquals(s1.toString(), s3.toString());

    }

    @Test
    public void testRemovePunctuation_allPossibilities_noPunctuationAfter() {
        Sentence s1 = new Sentence("Hi! Ignore those.");
        assertEquals("Hi!", s1.toString());

        Sentence s2 = new Sentence("so.me po.in.ts he,re but only end counts. yes?");
        assertEquals("So.me po.in.ts he,re but only end counts.", s2.toString());
    }
}
