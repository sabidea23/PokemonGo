package neutrel;

public class NeutrelFactory {
    private static NeutrelFactory singleInstance;

    public NeutrelFactory() {
    }

    public static NeutrelFactory instance() {
            if (singleInstance == null)
                singleInstance = new NeutrelFactory();
            return singleInstance;
        }

        public Neutrel makeNeutrel(TypeNeutrel tipNeutrel) {
            return switch (tipNeutrel) {
                case Neutrel1 -> new Neutrel1();
                case Neutrel2 -> new Neutrel2();
            };
        }
}
