import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    private Tokenizer t = new Tokenizer();

    @Test
    void stringToCharTest() {
        ArrayList<Character> expected = new ArrayList<>();
        expected.add('h');
        expected.add('e');
        expected.add('l');
        expected.add('l');
        expected.add('o');
        expected.add(' ');
        expected.add('w');
        expected.add('o');
        expected.add('r');
        expected.add('l');
        expected.add('d');
        assertEquals(expected, t.stringToChar("hello world"));
    }


    @Test
    public void testTokens() {
        String command = "(add 3 4)";
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("add");
        expected.add("3");
        expected.add("4");

        ArrayList<String> result = t.Tokens(command);
        assertEquals(expected, result);
    }

    @Test
    void tokensWithParenthesesTest() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("command");
        expected.add("with");
        expected.add("parentheses");
        assertEquals(expected, t.Tokens("(command) (with) (parentheses)"));
    }

    @Test
    void invalidTokensTest() {
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(expected, t.Tokens("(command with no parentheses"));
    }

    @Test
    void emptyCommandTest() {
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(expected, t.Tokens(""));
    }

    @Test
    void nullCommandTest() {
        assertThrows(NullPointerException.class, () -> t.Tokens(null));
    }



}