package neutrel;

public class NeutrelFactory {
    private static NeutrelFactory singleInstance;

    NeutrelFactory() {
    }

    public static NeutrelFactory instance() {
            if (singleInstance == null)
                singleInstance = new NeutrelFactory();
            return singleInstance;
        }

        public Neutrel makeNeutrel(TipNeutrel tipNeutrel) {
            return switch (tipNeutrel) {
                case Neutrel1 -> new Neutrel1();
                case Neutrel2 -> new Neutrel2();
            };
        }
}
