public class test {

    public static void main(String[] args) {
        Domino domino = new Domino();
        Tuile tuile = new Tuile(null) {
            @Override
            public String getGraphicalRepresentation() {
                return null;
            }
        };
        System.out.println(domino);
        System.out.println(tuile.getId());
    }
}
