import java.util.NoSuchElementException;

public class ListaEncadeada<S> {
    public Node primeiroNode; // apontador para o primeiro Node
    public Node ultimoNode;   // apontador para o ultimo Node
    public String desc;  // String como "lista" usado na impressao

    public class Node {
        public Object dado;
        public Node proximo;

        public Node(Object o) {
            this(o, null);
        }

        public Node(Object o, Node proximoNode) {
            dado = o;
            proximo = proximoNode;
        }

        public Object getObject() {
            return dado;
        }

        public Node getProximo() {
            return proximo;
        }
    }

    public class IteratorNatural {
        private Node corrente;

        public IteratorNatural() {
            corrente = primeiroNode; // inicia no primeiro nó
        }

        public boolean hasNext() {
            return corrente != null;
        }

        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Object dado = corrente.dado;
            corrente = corrente.proximo; // move para o próximo nó
            return dado;
        }
    }

    public class IteratorReverso {
        private Node corrente;

        public IteratorReverso() {
            corrente = null;
            // Inicializa corrente como o último nó
            Node temp = primeiroNode;
            while (temp != null) {
                corrente = temp;
                temp = temp.proximo;
            }
        }

        public boolean hasNext() {
            return corrente != null;
        }

        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Object dado = corrente.dado;
            // Para encontrar o nó anterior, precisamos percorrer a lista
            Node temp = primeiroNode;
            Node anterior = null;

            while (temp != null && temp != corrente) {
                anterior = temp;
                temp = temp.proximo;
            }
            corrente = anterior; // move para o nó anterior
            return dado;
        }
    }

    public ListaEncadeada(String s) {
        this.desc = s;
        primeiroNode = ultimoNode = null;
    }

    public ListaEncadeada() {
        this("Unknown");
    }

    public synchronized void inserirNaFrente(Object insertItem) {
        if (estaVazia())
            primeiroNode = ultimoNode = new Node(insertItem);
        else
            primeiroNode = new Node(insertItem, primeiroNode);
    }

    public synchronized void inserirNoFinal(Object insertItem) {
        if (estaVazia())
            primeiroNode = ultimoNode = new Node(insertItem);
        else
            ultimoNode = ultimoNode.proximo = new Node(insertItem);
    }

    public synchronized Object removeDaFrente() throws ListaVaziaException {
        if (estaVazia())
            throw new ListaVaziaException(this.desc);
        Object itemRemovido = primeiroNode.dado;
        if (primeiroNode.equals(ultimoNode))
            primeiroNode = ultimoNode = null;
        else
            primeiroNode = primeiroNode.proximo;
        return itemRemovido;
    }

    public synchronized Object removeDoFinal() throws ListaVaziaException {
        if (estaVazia())
            throw new ListaVaziaException(this.desc);
        Object itemRemovido = ultimoNode.dado;
        if (primeiroNode.equals(ultimoNode))
            primeiroNode = ultimoNode = null;
        else {
            Node corrente = primeiroNode;
            while (corrente.proximo != ultimoNode)
                corrente = corrente.proximo;
            ultimoNode = corrente;
            corrente.proximo = null;
        }
        return itemRemovido;
    }

    public synchronized boolean estaVazia() {
        return primeiroNode == null;
    }

    public synchronized String toString() {
        Node corrente = primeiroNode;
        StringBuilder ret = new StringBuilder();
        if (estaVazia()) {
            return "{}";
        }
        while (corrente != null) {
            ret.append(corrente.dado.toString()).append("\n");
            corrente = corrente.proximo;
        }
        return ret.toString();
    }

    public IteratorNatural iterator() {
        return new IteratorNatural();
    }

    public IteratorReverso reverseIterator() {
        return new IteratorReverso();
    }
}
