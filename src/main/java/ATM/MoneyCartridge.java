package ATM;

class MoneyCartridge {

    private int capacity;
    private int currentQuantity;

    MoneyCartridge(int capacity, int initialQuantity) {
        this.capacity = capacity;
        this.currentQuantity = initialQuantity;
    }

    boolean get(int quantity) {
        if (!isEmpty() && quantity <= currentQuantity) {
            currentQuantity -= quantity;
            return true;
        }
        return false;
    }

    boolean put(int quantity) {
        if (!isFull() && (quantity + currentQuantity) <= capacity) {
            currentQuantity += quantity;
            return true;
        }
        return false;
    }

    boolean isEmpty() {
        return (currentQuantity == 0);
    }

    boolean isFull() {
        return (currentQuantity == capacity);
    }

    int getCurrentQuantity() {
        return currentQuantity;
    }

    int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return ", ёмкость [" + capacity + "]" +
                ", заполнение [" + currentQuantity + "]";
    }

}