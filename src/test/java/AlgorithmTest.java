import ATM.Atm;
import ATM.AtmImpl;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.assertEquals;

public class AlgorithmTest {

    @Test
    public void testGetPossibleChanges() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] amounts = {5, 5, 5, 5, 5};
        int sum = 500;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();
        Method method = c.getDeclaredMethod("getPossibleChanges", int[].class, int[].class,
                int[].class, int.class, int.class);
        method.setAccessible(true);
        List<Integer[]> result = (List<Integer[]>) method.invoke(atm, denominations, amounts,
                new int[denominations.length], sum, 0);

        String actualGetPossibleChanges = "";

        for (Integer[] entry : result) {
            actualGetPossibleChanges += Arrays.toString(entry);
        }

        // then
        assertEquals("[1, 0, 0, 0, 0][0, 2, 1, 0, 0][0, 2, 0, 2, 0][0, 2, 0, 0, 5][0, 1, 3, 0, 0][0, 1, 2, 2, 0][0, 1, 2, 0, 5][0, 1, 1, 4, 0][0, 1, 1, 2, 5][0, 1, 0, 4, 5][0, 0, 5, 0, 0][0, 0, 4, 2, 0][0, 0, 4, 0, 5][0, 0, 3, 4, 0][0, 0, 3, 2, 5][0, 0, 2, 4, 5]",
                actualGetPossibleChanges);
    }

    @Test
    public void testGetPossibleChangesWithOneAmountOfEachDenomination() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] amounts = {1, 1, 1, 1, 1};
        int sum = 870;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();
        Method method = c.getDeclaredMethod("getPossibleChanges", int[].class, int[].class,
                int[].class, int.class, int.class);
        method.setAccessible(true);
        List<Integer[]> result = (List<Integer[]>) method.invoke(atm, denominations, amounts,
                new int[denominations.length], sum, 0);

        String actualGetPossibleChanges = "";

        for (Integer[] entry : result) {
            actualGetPossibleChanges += Arrays.toString(entry);
        }

        // then
        assertEquals("[1, 1, 1, 1, 1]", actualGetPossibleChanges);
    }

    @Test
    public void testGetPossibleChangesWithNoAmounts() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] amounts = {0, 0, 0, 0, 0};
        int sum = 100;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();
        Method method = c.getDeclaredMethod("getPossibleChanges", int[].class, int[].class,
                int[].class, int.class, int.class);
        method.setAccessible(true);
        List<Integer[]> result = (List<Integer[]>) method.invoke(atm, denominations, amounts,
                new int[denominations.length], sum, 0);

        String actualGetPossibleChanges = "";

        for (Integer[] entry : result) {
            actualGetPossibleChanges += Arrays.toString(entry);
        }

        // then
        assertEquals("", actualGetPossibleChanges);
    }

    @Test
    public void testGetPossibleChangesWithFullAmounts() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] amounts = {20, 20, 20, 20, 20};
        int sum = 1000;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();
        Method method = c.getDeclaredMethod("getPossibleChanges", int[].class, int[].class,
                int[].class, int.class, int.class);
        method.setAccessible(true);
        List<Integer[]> result = (List<Integer[]>) method.invoke(atm, denominations, amounts,
                new int[denominations.length], sum, 0);

        String actualGetPossibleChanges = "";

        for (Integer[] entry : result) {
            actualGetPossibleChanges += Arrays.toString(entry);
        }

        // then
        assertEquals("[2, 0, 0, 0, 0][1, 2, 1, 0, 0][1, 2, 0, 2, 0][1, 2, 0, 0, 5][1, 1, 3, 0, 0][1, 1, 2, 2, 0][1, 1, 2, 0, 5][1, 1, 1, 4, 0][1, 1, 1, 2, 5][1, 1, 1, 0, 10][1, 1, 0, 6, 0][1, 1, 0, 4, 5][1, 1, 0, 2, 10][1, 1, 0, 0, 15][1, 0, 5, 0, 0][1, 0, 4, 2, 0][1, 0, 4, 0, 5][1, 0, 3, 4, 0][1, 0, 3, 2, 5][1, 0, 3, 0, 10][1, 0, 2, 6, 0][1, 0, 2, 4, 5][1, 0, 2, 2, 10][1, 0, 2, 0, 15][1, 0, 1, 8, 0][1, 0, 1, 6, 5][1, 0, 1, 4, 10][1, 0, 1, 2, 15][1, 0, 1, 0, 20][1, 0, 0, 10, 0][1, 0, 0, 8, 5][1, 0, 0, 6, 10][1, 0, 0, 4, 15][1, 0, 0, 2, 20][0, 5, 0, 0, 0][0, 4, 2, 0, 0][0, 4, 1, 2, 0][0, 4, 1, 0, 5][0, 4, 0, 4, 0][0, 4, 0, 2, 5][0, 4, 0, 0, 10][0, 3, 4, 0, 0][0, 3, 3, 2, 0][0, 3, 3, 0, 5][0, 3, 2, 4, 0][0, 3, 2, 2, 5][0, 3, 2, 0, 10][0, 3, 1, 6, 0][0, 3, 1, 4, 5][0, 3, 1, 2, 10][0, 3, 1, 0, 15][0, 3, 0, 8, 0][0, 3, 0, 6, 5][0, 3, 0, 4, 10][0, 3, 0, 2, 15][0, 3, 0, 0, 20][0, 2, 6, 0, 0][0, 2, 5, 2, 0][0, 2, 5, 0, 5][0, 2, 4, 4, 0][0, 2, 4, 2, 5][0, 2, 4, 0, 10][0, 2, 3, 6, 0][0, 2, 3, 4, 5][0, 2, 3, 2, 10][0, 2, 3, 0, 15][0, 2, 2, 8, 0][0, 2, 2, 6, 5][0, 2, 2, 4, 10][0, 2, 2, 2, 15][0, 2, 2, 0, 20][0, 2, 1, 10, 0][0, 2, 1, 8, 5][0, 2, 1, 6, 10][0, 2, 1, 4, 15][0, 2, 1, 2, 20][0, 2, 0, 12, 0][0, 2, 0, 10, 5][0, 2, 0, 8, 10][0, 2, 0, 6, 15][0, 2, 0, 4, 20][0, 1, 8, 0, 0][0, 1, 7, 2, 0][0, 1, 7, 0, 5][0, 1, 6, 4, 0][0, 1, 6, 2, 5][0, 1, 6, 0, 10][0, 1, 5, 6, 0][0, 1, 5, 4, 5][0, 1, 5, 2, 10][0, 1, 5, 0, 15][0, 1, 4, 8, 0][0, 1, 4, 6, 5][0, 1, 4, 4, 10][0, 1, 4, 2, 15][0, 1, 4, 0, 20][0, 1, 3, 10, 0][0, 1, 3, 8, 5][0, 1, 3, 6, 10][0, 1, 3, 4, 15][0, 1, 3, 2, 20][0, 1, 2, 12, 0][0, 1, 2, 10, 5][0, 1, 2, 8, 10][0, 1, 2, 6, 15][0, 1, 2, 4, 20][0, 1, 1, 14, 0][0, 1, 1, 12, 5][0, 1, 1, 10, 10][0, 1, 1, 8, 15][0, 1, 1, 6, 20][0, 1, 0, 16, 0][0, 1, 0, 14, 5][0, 1, 0, 12, 10][0, 1, 0, 10, 15][0, 1, 0, 8, 20][0, 0, 10, 0, 0][0, 0, 9, 2, 0][0, 0, 9, 0, 5][0, 0, 8, 4, 0][0, 0, 8, 2, 5][0, 0, 8, 0, 10][0, 0, 7, 6, 0][0, 0, 7, 4, 5][0, 0, 7, 2, 10][0, 0, 7, 0, 15][0, 0, 6, 8, 0][0, 0, 6, 6, 5][0, 0, 6, 4, 10][0, 0, 6, 2, 15][0, 0, 6, 0, 20][0, 0, 5, 10, 0][0, 0, 5, 8, 5][0, 0, 5, 6, 10][0, 0, 5, 4, 15][0, 0, 5, 2, 20][0, 0, 4, 12, 0][0, 0, 4, 10, 5][0, 0, 4, 8, 10][0, 0, 4, 6, 15][0, 0, 4, 4, 20][0, 0, 3, 14, 0][0, 0, 3, 12, 5][0, 0, 3, 10, 10][0, 0, 3, 8, 15][0, 0, 3, 6, 20][0, 0, 2, 16, 0][0, 0, 2, 14, 5][0, 0, 2, 12, 10][0, 0, 2, 10, 15][0, 0, 2, 8, 20][0, 0, 1, 18, 0][0, 0, 1, 16, 5][0, 0, 1, 14, 10][0, 0, 1, 12, 15][0, 0, 1, 10, 20][0, 0, 0, 20, 0][0, 0, 0, 18, 5][0, 0, 0, 16, 10][0, 0, 0, 14, 15][0, 0, 0, 12, 20]",
                actualGetPossibleChanges);
    }

    @Test
    public void testGetMinimalChangeOf800() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] amounts = {5, 5, 5, 5, 5};
        int sum = 800;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();

        Method getPossibleChanges = c.getDeclaredMethod("getPossibleChanges", int[].class, int[].class,
                int[].class, int.class, int.class);
        getPossibleChanges.setAccessible(true);
        List<Integer[]> possibleChanges = (List<Integer[]>) getPossibleChanges.invoke(atm, denominations, amounts,
                new int[denominations.length], sum, 0);

        Method getMinimalChange = c.getDeclaredMethod("getMinimalChange", List.class);
        getMinimalChange.setAccessible(true);

        Integer[] minimalChange = (Integer[]) getMinimalChange.invoke(atm, possibleChanges);
        String actualGetMinimalChange = Arrays.toString(minimalChange);

        // then
        assertEquals("[1, 1, 1, 0, 0]", actualGetMinimalChange);
    }

    @Test
    public void testGetMinimalChangeOf1000With500s() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] amounts = {20, 20, 20, 20, 20};
        int sum = 1000;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();

        Method getPossibleChanges = c.getDeclaredMethod("getPossibleChanges", int[].class, int[].class,
                int[].class, int.class, int.class);
        getPossibleChanges.setAccessible(true);
        List<Integer[]> possibleChanges = (List<Integer[]>) getPossibleChanges.invoke(atm, denominations, amounts,
                new int[denominations.length], sum, 0);

        Method getMinimalChange = c.getDeclaredMethod("getMinimalChange", List.class);
        getMinimalChange.setAccessible(true);

        Integer[] minimalChange = (Integer[]) getMinimalChange.invoke(atm, possibleChanges);
        String actualGetMinimalChange = Arrays.toString(minimalChange);

        // then
        assertEquals("[2, 0, 0, 0, 0]", actualGetMinimalChange);
    }

    @Test
    public void testGetMinimalChangeOf1000With200s() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] amounts = {0, 20, 20, 20, 20};
        int sum = 1000;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();

        Method getPossibleChanges = c.getDeclaredMethod("getPossibleChanges", int[].class, int[].class,
                int[].class, int.class, int.class);
        getPossibleChanges.setAccessible(true);
        List<Integer[]> possibleChanges = (List<Integer[]>) getPossibleChanges.invoke(atm, denominations, amounts,
                new int[denominations.length], sum, 0);

        Method getMinimalChange = c.getDeclaredMethod("getMinimalChange", List.class);
        getMinimalChange.setAccessible(true);

        Integer[] minimalChange = (Integer[]) getMinimalChange.invoke(atm, possibleChanges);
        String actualGetMinimalChange = Arrays.toString(minimalChange);

        // then
        assertEquals("[0, 5, 0, 0, 0]", actualGetMinimalChange);
    }

    @Test
    public void testGetMinimalChangeOf1590() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] amounts = {2, 2, 2, 2, 2};
        int sum = 1590;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();

        Method getPossibleChanges = c.getDeclaredMethod("getPossibleChanges", int[].class, int[].class,
                int[].class, int.class, int.class);
        getPossibleChanges.setAccessible(true);
        List<Integer[]> possibleChanges = (List<Integer[]>) getPossibleChanges.invoke(atm, denominations, amounts,
                new int[denominations.length], sum, 0);

        Method getMinimalChange = c.getDeclaredMethod("getMinimalChange", List.class);
        getMinimalChange.setAccessible(true);

        Integer[] minimalChange = (Integer[]) getMinimalChange.invoke(atm, possibleChanges);
        String actualGetMinimalChange = Arrays.toString(minimalChange);

        // then
        assertEquals("[2, 2, 1, 1, 2]", actualGetMinimalChange);
    }

    @Test
    public void testGetMinimalChangeOfZero() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] amounts = {2, 2, 2, 2, 2};
        int sum = 0;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();

        Method getPossibleChanges = c.getDeclaredMethod("getPossibleChanges", int[].class, int[].class,
                int[].class, int.class, int.class);
        getPossibleChanges.setAccessible(true);
        List<Integer[]> possibleChanges = (List<Integer[]>) getPossibleChanges.invoke(atm, denominations, amounts,
                new int[denominations.length], sum, 0);

        Method getMinimalChange = c.getDeclaredMethod("getMinimalChange", List.class);
        getMinimalChange.setAccessible(true);

        Integer[] minimalChange = (Integer[]) getMinimalChange.invoke(atm, possibleChanges);
        String actualGetMinimalChange = Arrays.toString(minimalChange);

        // then
        assertEquals("[0, 0, 0, 0, 0]", actualGetMinimalChange);
    }

    @Test
    public void testGetMinimalChangeOf10() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] amounts = {2, 2, 2, 2, 2};
        int sum = 10;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();

        Method getPossibleChanges = c.getDeclaredMethod("getPossibleChanges", int[].class, int[].class,
                int[].class, int.class, int.class);
        getPossibleChanges.setAccessible(true);
        List<Integer[]> possibleChanges = (List<Integer[]>) getPossibleChanges.invoke(atm, denominations, amounts,
                new int[denominations.length], sum, 0);

        Method getMinimalChange = c.getDeclaredMethod("getMinimalChange", List.class);
        getMinimalChange.setAccessible(true);

        Integer[] minimalChange = (Integer[]) getMinimalChange.invoke(atm, possibleChanges);
        String actualGetMinimalChange = Arrays.toString(minimalChange);

        // then
        assertEquals("[]", actualGetMinimalChange);
    }

    @Test
    public void testGetChangeSum1() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] change = {5, 5, 5, 5, 5};
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();
        Method method = c.getDeclaredMethod("getChangeSum", int[].class, int[].class);
        method.setAccessible(true);
        int actual = (int) method.invoke(atm, denominations, change);

        // then
        assertEquals(4350, actual);
    }

    @Test
    public void testGetChangeSum2() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int[] change = {20, 20, 20, 20, 20};
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();
        Method method = c.getDeclaredMethod("getChangeSum", int[].class, int[].class);
        method.setAccessible(true);
        int actual = (int) method.invoke(atm, denominations, change);

        // then
        assertEquals(17400, actual);
    }

    @Test
    public void testGetArrayPositionOf500() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] array = {500, 200, 100, 50, 20};
        int value = 500;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();
        Method method = c.getDeclaredMethod("getArrayPosition", int[].class, int.class);
        method.setAccessible(true);
        int result = (Integer) method.invoke(atm, array, value);

        // then
        assertEquals(0, result);
    }

    @Test
    public void testGetArrayPositionOf20() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        // given
        int[] denominations = {500, 200, 100, 50, 20};
        int sum = 20;
        Atm atm = new AtmImpl();

        // when
        Class c = atm.getClass();
        Method method = c.getDeclaredMethod("getArrayPosition", int[].class, int.class);
        method.setAccessible(true);
        int result = (Integer) method.invoke(atm, denominations, sum);

        // then
        assertEquals(4, result);
    }
}
