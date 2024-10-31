package collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

class GenericCollectionsTest {
	
    //lettersAndNumbers tests
	
    @Test
    void lettersAndNumbers_nullList() {
        assertNull(GenericCollections.lettersAndNumbers(null));
    }

    @Test
    void lettersAndNumbers_emptyList() {
        assertEquals(new ArrayList<>(), GenericCollections.lettersAndNumbers(new ArrayList<>()));
    }

    @Test
    void lettersAndNumbers_onlyLetters() {
        List<Character> input = Arrays.asList('a', 'B', 'c', 'D');
        List<String> expected = Arrays.asList("U(d)", "L(c)", "U(b)", "L(a)");
        assertEquals(expected, GenericCollections.lettersAndNumbers(input));
    }

    @Test
    void lettersAndNumbers_onlyDigits() {
        List<Character> input = Arrays.asList('1', '2', '3');
        List<String> expected = Arrays.asList("N(3)", "N(2)", "N(1)");
        assertEquals(expected, GenericCollections.lettersAndNumbers(input));
    }

    @Test
    void lettersAndNumbers_mixedLettersAndDigits() {
        List<Character> input = Arrays.asList('a', '1', 'B', '3');
        List<String> expected = Arrays.asList("N(3)", "U(b)", "N(1)", "L(a)");
        assertEquals(expected, GenericCollections.lettersAndNumbers(input));
    }

    @Test
    void lettersAndNumbers_specialCharactersIgnored() {
        List<Character> input = Arrays.asList('@', '!', 'a', 'B');
        List<String> expected = Arrays.asList("U(b)", "L(a)");
        assertEquals(expected, GenericCollections.lettersAndNumbers(input));
    }

    @Test
    void lettersAndNumbers_mixedCaseLetters() {
        List<Character> input = Arrays.asList('A', 'b', 'C');
        List<String> expected = Arrays.asList("U(c)", "L(b)", "U(a)");
        assertEquals(expected, GenericCollections.lettersAndNumbers(input));
    }

    @Test
    void lettersAndNumbers_duplicates() {
        List<Character> input = Arrays.asList('a', 'a', '1', '1');
        List<String> expected = Arrays.asList("N(1)", "N(1)", "L(a)", "L(a)");
        assertEquals(expected, GenericCollections.lettersAndNumbers(input));
    }
    
    // modify tests
    
    private List<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    public void modify_testNullList() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            GenericCollections.modify(null);
        });
        assertEquals("List cannot be null", exception.getMessage());
    }

    @Test
    public void modify_testEmptyList() {
        int uniqueCount = GenericCollections.modify(list);
        List<Integer> expected = Arrays.asList(4, 5, 6, 8, 10, 12, 12, 15, 16, 18, 20, 20, 24, 25, 30);
        assertEquals(expected, list);
        assertEquals(13, uniqueCount);
    }

    @Test
    public void modify_testAllMultiplesOfThree() {
        list.addAll(Arrays.asList(3, -6, 3, -9, -3, 0, 12, 3));
        int uniqueCount = GenericCollections.modify(list);
        List<Integer> expected = Arrays.asList(4, 5, 6, 8, 10, 12, 12, 15, 16, 18, 20, 20, 24, 25, 30);
        assertEquals(expected, list);
        assertEquals(13, uniqueCount);
    }

    @Test
    public void modify_testListWithNoMultiplesOfThree() {
        list.addAll(Arrays.asList(1, 2, 4, 5, 7));
        int uniqueCount = GenericCollections.modify(list);
        List<Integer> expected = Arrays.asList(1, 2, 4, 4, 5, 5, 6, 7, 8, 10, 12, 12, 15, 16, 18, 20, 20, 24, 25, 30);
        assertEquals(expected, list);
        assertEquals(16, uniqueCount);
    }

    @Test
    public void modify_testMixedListWithMultiplesOfThreeAndOtherNumbers() {
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        int uniqueCount = GenericCollections.modify(list);
        List<Integer> expected = Arrays.asList(1, 2, 4, 4, 5, 5, 6, 7, 8, 10, 12, 12, 15, 16, 18, 20, 20, 24, 25, 30);
        assertEquals(expected, list);
        assertEquals(16, uniqueCount);
    }

    @Test
    public void modify_testListWithNegativesAndMultiplesOfThree() {
        list.addAll(Arrays.asList(-9, -6, -3, 0, 3, 6, 9));
        int uniqueCount = GenericCollections.modify(list);
        List<Integer> expected = Arrays.asList(4, 5, 6, 8, 10, 12, 12, 15, 16, 18, 20, 20, 24, 25, 30);
        assertEquals(expected, list);
        assertEquals(13, uniqueCount);
    }

    @Test
    public void modify_testListWithNonMultiplesOfThreeOnly() {
        list.addAll(Arrays.asList(1, 2, 4, 5, 7, 11, 13));
        int uniqueCount = GenericCollections.modify(list);
        List<Integer> expected = Arrays.asList(1, 2, 4, 4, 5, 5, 6, 7, 8, 10, 11, 12, 12, 13, 15, 16, 18, 20, 20, 24, 25, 30);
        assertEquals(expected, list);
        assertEquals(18, uniqueCount);
    }

    @Test
    public void modify_testListWithDuplicates() {
        list.addAll(Arrays.asList(2, 2, 3, 3, 5, 5, 6, 6));
        int uniqueCount = GenericCollections.modify(list);
        List<Integer> expected = Arrays.asList(2, 2, 4, 5, 5, 5, 6, 8, 10, 12, 12, 15, 16, 18, 20, 20, 24, 25, 30);
        assertEquals(expected, list);
        assertEquals(14, uniqueCount);
    }
    
    // removeEntryNthElement tests
    
    @Test
    void removeEntryNthElement_nullList() {
        assertThrows(NullPointerException.class, () -> GenericCollections.removeEntryNthElement(null, 2));
    }

    @Test
    void removeEntryNthElement_zeroOrNegativeN() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        assertThrows(IllegalArgumentException.class, () -> GenericCollections.removeEntryNthElement(list, 0));
        assertThrows(IllegalArgumentException.class, () -> GenericCollections.removeEntryNthElement(list, -1));
    }

    @Test
    void removeEntryNthElement_everySecondElement() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        GenericCollections.removeEntryNthElement(list, 2);
        List<Integer> expected = Arrays.asList(1, 3, 5, 7, 9);
        assertEquals(expected, list);
    }

    @Test
    void removeEntryNthElement_largeN() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        GenericCollections.removeEntryNthElement(list, 10);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, list);
    }

    @Test
    void removeEntryNthElement_everyElement() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        GenericCollections.removeEntryNthElement(list, 1);
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, list);
    }

    @Test
    void removeEntryNthElement_noElements() {
        List<Integer> list = new ArrayList<>();
        GenericCollections.removeEntryNthElement(list, 3);
        assertTrue(list.isEmpty());
    }

    @Test
    void removeEntryNthElement_withStrings() {
        List<String> list = new ArrayList<>(Arrays.asList("AA", "BB", "CC", "DD", "EE"));
        GenericCollections.removeEntryNthElement(list, 3);
        List<String> expected = Arrays.asList("AA", "BB", "DD", "EE");
        assertEquals(expected, list);
    }

    @Test
    void removeEntryNthElement_withMixedObjects() {
        List<Object> list = new ArrayList<>(Arrays.asList(1, "B", 2.0, 'C', true, 3));
        GenericCollections.removeEntryNthElement(list, 2);
        List<Object> expected = Arrays.asList(1, 2.0, true);
        assertEquals(expected, list);
    }


}
