package ee.taltech.iti0202.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SentenceTest {

    @Test
    @Before
    public void testToString_EmptySentence_IsEmptyString() {
        Sentence s1 = new Sentence();
        Sentence s2 = new Sentence();
        assertEquals("", s1.toString());
        assertEquals(s1, s2);
        assertFalse(s1.addWord(""));
    }


    @Test
    @After
    public void testAddWordAndPunctuation_thenRemoveWordAndPunctuation_allBasicCases() {
        Sentence s1 = new Sentence();
        assertTrue(s1.addWord("hello"));
        String actual = s1.toString();
        assertEquals("Hello...", actual);

        assertTrue(s1.addWord("world"));
        actual = s1.toString();
        assertEquals("Hello world...", actual);

        assertTrue(s1.addPunctuation("??"));
        actual = s1.toString();
        assertEquals("Hello world??", actual);
        assertFalse(s1.addWord("NO"));
        assertFalse(s1.addPunctuation("."));

        assertTrue(s1.removePunctuation());
        assertTrue(s1.removeWord("hello"));
        actual = s1.toString();
        assertEquals("World...", actual);

        assertTrue(s1.removeWord("world"));
        actual = s1.toString();
        assertEquals("", actual);

        assertFalse(s1.addPunctuation("wat?"));

        assertTrue(s1.addWord("??"));
        assertFalse(s1.removePunctuation());
        assertTrue(s1.addPunctuation("hello"));
        actual = s1.toString();
        assertEquals("??hello", actual);
    }

    @Test
    public void testToString_removeSpaces_noSpacesAfter() {
        Sentence s7 = new Sentence(" hello     world    yes?");
        String actual = s7.toString();
        assertEquals("Hello world yes?", actual);
        assertFalse(s7.addWord("CANNOT"));
    }

    @Test
    @Before
    public void testAddPunctuation_manipulatePunctuation_basicPunctuation() {
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

        Sentence s2 = new Sentence("so!me po.in?ts he,re but only end counts. yes?");
        assertEquals("So!me po.in?ts he,re but only end counts.", s2.toString());
    }

    @Test
    public void testRemoveWord_RemovesWord() {
        Sentence sentence = new Sentence("Aaa bbb");
        assertTrue(sentence.removeWord("bbb"));
        assertEquals("Aaa...", sentence.toString());
    }

    @Test
    public void addWord_addWords() {
        Sentence sentence = new Sentence("a");
        assertTrue(sentence.addWord("b"));
        assertTrue(sentence.addPunctuation("!"));
        assertEquals("A b!", sentence.toString());
        assertTrue(sentence.removePunctuation());
        assertTrue(sentence.removeWord("b"));
        assertEquals("A...", sentence.toString());
        assertFalse(sentence.removePunctuation());
        assertFalse(sentence.removeWord("b"));
        assertTrue(sentence.removeWord("a"));
        assertEquals("", sentence.toString());
    }

    @Test
    public void equals_punctuation() {
        Sentence s1 = new Sentence();
        Sentence s2 = new Sentence();
        assertTrue(s1.addWord(".."));
        assertTrue(s1.addPunctuation(".."));
        assertTrue(s2.addWord("."));
        assertEquals(s1, s2);
        assertTrue(s2.addPunctuation("..."));
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    public void equals_punctuation2() {
        Sentence s1 = new Sentence(". ..");
        Sentence s2 = new Sentence(". .");
        assertFalse(s2.removeWord("."));
        s2.addWord(".");
        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        assertTrue(s2.removePunctuation());
        assertEquals(new Sentence(), s2);
        assertEquals(new Sentence().hashCode(), s2.hashCode());

    }

    @Test
    public void testRemoveWord_NoWord() {
        Sentence sentence = new Sentence("");
        assertFalse(sentence.removeWord("zz"));
        assertFalse(sentence.addWord(" "));
        assertEquals("", sentence.toString());
    }

    @Test
    public void testToString_noWord() {
        // Sentence sentence = new Sentence("");
        // assertEquals("", sentence.toString());
        // assertFalse(sentence.addPunctuation("HI"));
        // assertEquals("", sentence.toString());
        // assertFalse(sentence.addWord("BYE"));
        // assertFalse(sentence.addPunctuation("HI"));
        // assertEquals("", sentence.toString());
    }

    @Test
    public void testRemoveWord_SentenceHasEnding() {
        Sentence sentence = new Sentence("Aaa bbb.");
        assertFalse(sentence.removeWord("bbb"));
        assertEquals("Aaa bbb.", sentence.toString());

        sentence = new Sentence("Aaa bbb!");
        assertFalse(sentence.removeWord("bbb"));
        assertEquals("Aaa bbb!", sentence.toString());

        sentence = new Sentence("Aaa bbb?");
        assertFalse(sentence.removeWord("bbb"));
        assertEquals("Aaa bbb?", sentence.toString());
    }
}
