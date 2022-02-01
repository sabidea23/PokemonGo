package neutrel;

public class TestMakeNeutrel {
    public static void main(String[] args) {
        NeutrelFactory f = new NeutrelFactory();
        Neutrel1 neutrel1 = (Neutrel1)f.makeNeutrel(TipNeutrel.Neutrel1);
        Neutrel2 neutrel2 = (Neutrel2)f.makeNeutrel(TipNeutrel.Neutrel2);

        System.out.println(neutrel1.toString());
        System.out.println(neutrel2.toString());

    }
}
