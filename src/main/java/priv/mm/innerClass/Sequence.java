package priv.mm.innerClass;

public class Sequence {
    private Object[] items;
    private int next = 0;

    private Sequence(int size) {
        super();
        items = new Object[size];
    }

    private void add(Object o) {
        if (next < items.length) items[next++] = o;
    }

    private interface Selector {
        boolean end();

        Object current();

        void next();
    }

    private class SequenceSelector implements Selector {
        private int i;

        @Override
        public boolean end() {
            return i == items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length) i++;
        }
    }

    private Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++) {
            sequence.add(i + "");
        }
        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
