package ATM;

import java.util.Arrays;

public class MoneyCartridge {

    private int maxSize;
    private int[] stackArray;
    private int top;

    public int getPosition() {
        return top + 1;
    }

    MoneyCartridge(int capacity) {
        maxSize = capacity;
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int j) {
        stackArray[++top] = j;
    }

    public int pop() {
        return stackArray[top--];
    }

    public int peek() {
        return stackArray[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
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
//    public static void main(String[] args) {
//        MoneyCartridge theStack = new MoneyCartridge(10);
//        theStack.push(10);
//        theStack.push(20);
//        theStack.push(30);
//        theStack.push(40);
//        theStack.push(50);
//        while (!theStack.isEmpty()) {
//            int value = theStack.pop();
//            System.out.print(value + " ");
//        }
//    }
}