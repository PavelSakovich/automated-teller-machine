package ATM;

import java.util.Arrays;

class MoneyCartridge {

    private int maxSize;
    private int[] stackArray;
    private int top;

    int getPosition() {
        return top + 1;
    }

    MoneyCartridge(int capacity, int initialQuantity) {
        maxSize = capacity;
        stackArray = new int[maxSize];
        top = -1;

        for (int i = 0; i < initialQuantity; i++) {
            push(1);
        }
    }

    void push(int j) {
        stackArray[++top] = j;
    }

    int pop() {
        return stackArray[top--];
    }

    public int peek() {
        return stackArray[top];
    }

    boolean isEmpty() {
        return (top == -1);
    }

    boolean isFull() {
        return (top == maxSize - 1);
    }

    @Override
    public String toString() {
        return ", ёмкость [" + maxSize + "]" +
                ", заполнение " + Arrays.toString(stackArray);
    }

    int getMaxSize() {
        return maxSize;
    }
}