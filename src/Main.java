public class Main {
    public static void main(String[] args) {
        ListaEncadeada<String> lista = new ListaEncadeada<>("Minha Lista");

        // Adicionando elementos à lista
        lista.inserirNoFinal("1");
        lista.inserirNoFinal("2");
        lista.inserirNoFinal("3");
        lista.inserirNoFinal("4");

        System.out.println("Iterador na ordem natural:");
        ListaEncadeada<String>.IteratorNatural iterador = lista.iterator();
        while (iterador.hasNext()) {
            System.out.println(iterador.next());
        }

        System.out.println("\nIterador na ordem reversa:");
        ListaEncadeada<String>.IteratorReverso iteradorReverso = lista.reverseIterator();
        while (iteradorReverso.hasNext()) {
            System.out.println(iteradorReverso.next());
        }
    }
}
