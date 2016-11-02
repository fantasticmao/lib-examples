package priv.mm.innerClass;

/**
 * 在内部类中，可随意访问外部类的字段
 * <p>使用.this可在内部类中获取外部类对象的引用<pre>
 * Sequence sequence = Sequence.this;
 * </pre>使用.new可在外部类对象上获取内部类的引用<pre>
 * Selector selector = new Sequence(10).new SequenceSelector();</pre></p>
 */
interface Selector {
    boolean end();

    Object current();

    void next();
}

public class Sequence {
    private Object[] items;
    public int next = 0;

    public Sequence(int size) {
        super();
        items = new Object[size];
    }

    public void add(Object o) {
        if (next < items.length) items[next++] = o;
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

        public Sequence sequence() {
            Sequence sequence = Sequence.this;
            return sequence;
        }
    }

    public Selector selector() {
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
        new Sequence(1){
            @Override
            public void add(Object o) {
                super.add(o);
            }
        };
    }
}
