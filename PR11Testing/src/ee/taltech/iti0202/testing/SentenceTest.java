package ee.taltech.iti0202.testing;

import ee.taltech.iti0202.sentence.Sentence;
import org.junit.Test;

import static org.junit.Assert.*;

public class SentenceTest {
    @Test
    public void removeWord() {
        fail();
    }

    @Test
    public void addWord_thenRemoveWord_allBasicCases() {
        Sentence s1 = new Sentence();
        s1.addWord("hello");
        assertEquals("Hello...", s1.toString());

        s1.addWord("world");
        assertEquals("Hello world...", s1.toString());

        s1.addPunctuation("??");
        assertEquals("Hello world??", s1.toString());
        assertFalse(s1.addWord("NO"));
        assertFalse(s1.addPunctuation("."));

        s1.removePunctuation();
        s1.removeWord("hello");
        assertEquals("World...", s1.toString());

        s1.removeWord("world");
        assertEquals("", s1.toString());

        assertFalse(s1.addPunctuation("wat?"));

        s1.addWord("??");
        s1.addPunctuation("hello");
        assertEquals("??hello", s1.toString());

        Sentence s7 = new Sentence(" hello     world    yes?");
        assertEquals("Hello world yes?", s7.toString());
        assertFalse(s7.addWord("CANNOT"));
    }

    @Test
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

    @Test
    public void equals() {
    }

    @Test
    public void main() {
    }
}
