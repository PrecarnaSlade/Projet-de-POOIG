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
        System.out.println(domino.getGraphicalRepresentation());
        domino.rotateClockwise();
        System.out.println(domino.getGraphicalRepresentation());
        domino.rotateAntiClockwise();
        System.out.println(domino.getGraphicalRepresentation());
        System.out.println(tuile.getId());
    }
}
