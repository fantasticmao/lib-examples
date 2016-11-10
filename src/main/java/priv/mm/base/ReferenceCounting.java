package priv.mm.base;

class Shared {
    private int refCount = 0;
    private static long counter = 0;
    private final long id = counter++;

    Shared() {
        System.out.println("creating " + this);
    }

    void addRef() {
        refCount++;
    }

    void dispose() {
        if (--refCount == 0) {
            System.out.println("disposing " + this);
        }
    }

    @Override
    public String toString() {
        return "Shared " + id;
    }
}

class Composing {
    private Shared shared;
    private static long counter = 0;
    private final long id = counter++;

    Composing(Shared shared) {
        System.out.println("creating " + this);
        this.shared = shared;
        this.shared.addRef();
    }

    void dispose() {
        System.out.println("disposing " + this);
        shared.dispose();
    }

    @Override
    public String toString() {
        return "Composing " + id;
    }
}

/**
 * ReferenceCounting
 * Created by MaoMao on 2016/11/1.
 */
public class ReferenceCounting {
    public static void main(String[] args) {
        Shared shared = new Shared();
        Composing[] composing = {new Composing(shared), new Composing(shared), new Composing(shared)};
        for (Composing c : composing) {
            c.dispose();
        }
    }
}
