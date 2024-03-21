package co.icesi.edu.structures;

import java.util.Iterator;

public class ListaEnlazada<T> implements Iterable<T> {
    private Nodo<T> cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(actual);
        }
    }

    public void eliminar(T dato) {
        if (cabeza != null) {
            if (cabeza.getDato().equals(dato)) {
                cabeza = cabeza.getSiguiente();
                if (cabeza != null) {
                    cabeza.setAnterior(null);
                }
                return;
            }
            Nodo<T> actual = cabeza;
            while (actual != null) {
                if (actual.getDato().equals(dato)) {
                    Nodo<T> anterior = actual.getAnterior();
                    Nodo<T> siguiente = actual.getSiguiente();
                    if (anterior != null) {
                        anterior.setSiguiente(siguiente);
                    }
                    if (siguiente != null) {
                        siguiente.setAnterior(anterior);
                    }
                    return;
                }
                actual = actual.getSiguiente();
            }
        }
    }

    public Nodo<T> buscar(T dato) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void recorrer() {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public void recorrerAlReves() {
        Nodo<T> actual = cabeza;
        while (actual != null && actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getAnterior();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListaEnlazadaIterator();
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//


    private class ListaEnlazadaIterator implements Iterator<T> {
        private Nodo<T> actual;

        public ListaEnlazadaIterator() {
            actual = cabeza;
        }

        @Override
        public boolean hasNext() {
            return actual != null;
        }

        @Override
        public T next() {
            T elemento = actual.getDato();
            actual = actual.getSiguiente();
            return elemento;
        }
    }

    //--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//--//

    public Object[] toArray() {
        // Contar la cantidad de elementos en la lista
        int size = 0;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            size++;
            actual = actual.getSiguiente();
        }

        // Crear un array del tamaño calculado
        Object[] array = new Object[size];

        // Llenar el array con los elementos de la lista
        actual = cabeza;
        int index = 0;
        while (actual != null) {
            array[index++] = actual.getDato();
            actual = actual.getSiguiente();
        }

        return array;
    }

    public boolean isEmpty() {
        return cabeza == null;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }
}
