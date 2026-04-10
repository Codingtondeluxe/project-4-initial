package com.example.iterable;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @Test
    void newBagStartsEmpty() {
        Bag<String> bag = new Bag<>();

        assertTrue(bag.isEmpty());
        assertEquals(0, bag.size());
    }

    @Test
    void addIncreasesSizeAndContainsItem() {
        Bag<String> bag = new Bag<>();

        bag.add("apple");

        assertFalse(bag.isEmpty());
        assertEquals(1, bag.size());
        assertTrue(bag.contains("apple"));
    }

    @Test
    void removeExistingItemReturnsTrueAndUpdatesSize() {
        Bag<Integer> bag = new Bag<>();
        bag.add(10);
        bag.add(20);

        boolean removed = bag.remove(10);

        assertTrue(removed);
        assertEquals(1, bag.size());
        assertFalse(bag.contains(10));
        assertTrue(bag.contains(20));
    }

    @Test
    void removeMissingItemReturnsFalseWithoutChangingSize() {
        Bag<String> bag = new Bag<>();
        bag.add("apple");

        boolean removed = bag.remove("pear");

        assertFalse(removed);
        assertEquals(1, bag.size());
        assertTrue(bag.contains("apple"));
    }

    @Test
    void bagSupportsDuplicateValues() {
        Bag<String> bag = new Bag<>();
        bag.add("x");
        bag.add("x");

        assertEquals(2, bag.size());

        boolean removedOne = bag.remove("x");

        assertTrue(removedOne);
        assertEquals(1, bag.size());
        assertTrue(bag.contains("x"));
    }

    @Test
    void addNullThrowsIllegalArgumentException() {
        Bag<String> bag = new Bag<>();

        assertThrows(IllegalArgumentException.class, () -> bag.add(null));
        assertTrue(bag.isEmpty());
    }

    @Test
    void iteratorHasNextAndNextFollowInsertionOrder() {
        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);

        Iterator<Integer> iterator = bag.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorNextThrowsWhenExhausted() {
        Bag<String> bag = new Bag<>();
        bag.add("only");

        Iterator<String> iterator = bag.iterator();
        assertEquals("only", iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void forEachLoopIteratesAllItems() {
        Bag<Integer> bag = new Bag<>();
        bag.add(4);
        bag.add(5);
        bag.add(6);

        int sum = 0;
        for (Integer value : bag) {
            sum += value;
        }

        assertEquals(15, sum);
    }

    // ── Extra Credit ────────────────────────────────────────────────────────────

    @Test
    void forEachVisitsAllItemsInInsertionOrder() {
        Bag<String> bag = new Bag<>();
        bag.add("a");
        bag.add("b");
        bag.add("c");

        List<String> visited = new ArrayList<>();
        bag.forEach(visited::add);

        assertEquals(List.of("a", "b", "c"), visited);
    }

    @Test
    void forEachWithNullActionThrowsNullPointerException() {
        Bag<String> bag = new Bag<>();
        bag.add("x");

        assertThrows(NullPointerException.class, () -> bag.forEach(null));
    }

    @Test
    void spliteratorReportsCorrectSizeEstimate() {
        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(3);

        Spliterator<Integer> spliterator = bag.spliterator();

        assertEquals(3, spliterator.estimateSize());
    }

    @Test
    void spliteratorCanBeUsedToStreamAllItems() {
        Bag<Integer> bag = new Bag<>();
        bag.add(10);
        bag.add(20);
        bag.add(30);

        int sum = (int) StreamSupport.stream(bag.spliterator(), false)
                .mapToInt(Integer::intValue)
                .sum();

        assertEquals(60, sum);
    }

    @Test
    void spliteratorOnEmptyBagHasZeroSize() {
        Bag<String> bag = new Bag<>();

        Spliterator<String> spliterator = bag.spliterator();

        assertEquals(0, spliterator.estimateSize());
        assertFalse(spliterator.tryAdvance(item -> fail("Should not visit any element")));
    }
}