package myAdapter;

import java.util.NoSuchElementException;

/**
 * Classe che implementa {@link myAdapter.HList} ed e' l'implementazione della
 * sottolista di {@link myAdapter.ListAdapter}
 * <p>
 * Questa classe e' una rappresentazione della lista principale limitata da due
 * indici (from e to).
 * Un oggetto di tipo ListIterator conserva i propri indici from e to, che
 * identificano la porzione accessibile della
 * lista principale, oltre che a un riferimento al ListAdapter father, su cui
 * e'stato invocato il metodo sublist per
 * creare questo oggetto (il riferimento father puo'essere null se questo
 * ListAdapter e' la lista principale).
 * Il riferimento a father e' necessario per incrementare la dimensione delle
 * liste in una posizione superiore
 * (considerando la gerarchia delle sottoliste) quando sono effettuate modifiche
 * strutturali su una sottolista
 * 
 * Gli elementi della lista sono conservati in un Vector
 * 
 * @version JVM from JME CLDC 1.1
 * 
 * @author Luca Mainardi
 * 
 * @see myAdapter.HListIterator
 * @see myAdapter.HCollection
 * 
 */
public class ListAdapter implements HList, HCollection {
    private int from, to;
    private Vector list;
    private ListAdapter father;
    private boolean hasFather;

    /**
     * Costruttore senza parametri, crea una lista principale vuota
     */
    public ListAdapter() {
        from = 0;
        to = 0;
        list = new Vector();
        hasFather = false;
        father = null;
    }

    /**
     * Costruttore con parametri, crea una lista principale contente gli elementi
     * della collezione passata come parametro, nell'ordine stabilito dal metodo
     * toArray() di tale collezione
     * 
     * @param coll - collezione contenente gli elementi da inserire nella lista
     *             creata
     * 
     * @throws NullPointerException se la collezione passata come parametro e' null
     */
    public ListAdapter(HCollection coll) {
        if (coll == null) {
            throw new NullPointerException();
        }
        from = 0;
        to = coll.size();
        hasFather = false;
        father = null;
        Object[] obj = coll.toArray();

        list = new Vector();

        for (int i = from; i < to; i++) {
            list.addElement(obj[i]);
        }
    }

    /**
     * Costruttore di una sottolista della lista padre passata come parametro,
     * contenente la porzione di essa contenuta tra gli indici fromIndex(incluso) e
     * toIndex(escluso)
     * 
     * @param father    - lista principale di cui viene creata la sottolista
     * @param fromIndex - indice della lista principale da cui inizia la sottolista
     *                  (incluso)
     * @param toIndex   - indice della lista principale in cui finisce la sottolista
     *                  (escluso)
     */
    private ListAdapter(ListAdapter father, int fromIndex, int toIndex) {
        this.list = father.list;
        to = toIndex;
        from = fromIndex;
        hasFather = true;
        this.father = father;
    }

    /**
     * Restituisce il numero di elementi presenti in questa lista
     *
     * @return il numero di elementi in questa lista
     */
    public int size() {
        return to - from;
    }

    /**
     * Restituisce true se questa lista non contiene elementi
     *
     * @return true se questa lista non contiene elementi
     */
    public boolean isEmpty() {
        return from == to;
    }

    /**
     * Restituisce true se questa lista contiene l'elemento specificato. Piu'
     * formalmente restituisce true se e solo se questa lista contiene almeno un
     * elemento e tale che (o==null ? e==null : o.equals(e)).
     *
     * @param obj - elemento di cui deve essere verificata la presenza nella lista
     * @return se questa lista contiene l'elemento specificato
     */
    public boolean contains(Object obj) {
        Vector v = new Vector();
        for (int i = 0; i < size(); i++) {
            v.addElement(list.elementAt(from + i));
        }
        return v.contains(obj);
    }

    /**
     * Restituisce un iteratore che opera sugli elementi di questa lista nella
     * sequenza corretta
     *
     *
     * @return un iteratore che opera sugli elementi di questa lista nella sequenza
     *         corretta
     */
    public HIterator iterator() {
        return new ListAdapterIterator(this);
    }

    /**
     * Restituisce un array contenente tutti gli elementi in questa lista nella
     * sequenza corretta. Rispetta il contratto generale del metodo
     * Collection.toArray.
     *
     * @return un array contenente tutti gli elementi in questa lista nella
     *         sequenza corretta
     */
    public Object[] toArray() {
        Object[] toReturn = new Object[size()];
        for (int i = 0; i < size(); i++) {
            toReturn[i] = list.elementAt(from + i);
        }
        return toReturn;
    }

    /**
     * Restituisce un array contenente tutti gli elementi in questa lista nella
     * sequenza corretta; se la lista ha dimensione minore dell'array specificato,
     * viene restituita al suo interno; gli spazi rimanenti dell'array sono settati
     * a null.
     * In caso contrario, un nuovo array di Object viene allocato e usato per tale
     * scopo.
     * 
     * @param arrayTarget - l'array in cui gli elementi di questa lista devono
     *                    essere
     *                    inseriti, se abbastanza capiente; in caso contrario, un
     *                    nuovo array
     *                    di Object viene allocato e usato a questo scopo.
     * @return un array contenente tutti gli elementi in questa lista nella
     *         sequenza corretta
     * @throws NullPointerException se l'array specificato e'null.
     */

    public Object[] toArray(Object arrayTarget[]) {
        if (arrayTarget == null) {
            throw new NullPointerException();
        }

        Vector v = new Vector();
        for (int i = 0; i < size(); i++) {
            v.addElement(list.elementAt(from + i));
        }

        if (arrayTarget.length >= size()) {
            v.copyInto(arrayTarget);

            for (int i = size(); i < arrayTarget.length; i++) {
                arrayTarget[i] = null;
            }
            return arrayTarget;
        } else {
            Object[] toReturn = new Object[size()];
            v.copyInto(toReturn);
            return toReturn;
        }
    }

    /**
     * Aggiunge l'elemento specificato alla fine di questa lista
     *
     * @param obj - elemento da aggiungere alla fine della lista
     * @return sempre true
     */
    public boolean add(Object obj) {
        list.insertElementAt(obj, to);
        increaseFather();
        to++;
        return true;
    }

    /**
     * Rimuove la prima occorrenza in questa lista dell'elemento specificato. Se
     * questa lista non contiene l'elemento, rimane invariata. Più formalmente,
     * rimuove l'elemento con l'indice i più basso tale che (o==null ? get(i)==null
     * : o.equals(get(i))) (se tale elemento esiste).
     * Restituisce true se questa lista conteneva l'elemento specificato (o in modo
     * equivalente, se questa lista è cambiata a seguito di questa chiamata).
     *
     * @param obj - elemento da rimuovere dalla lista, se presente
     * @return true se questa lista è cambiata a seguito di questa chiamata
     */
    public boolean remove(Object obj) {
        if (isEmpty()) {
            return false;
        }

        if (contains(obj)) {
            int indexToRemove = list.indexOf(obj, from);
            list.removeElementAt(indexToRemove);
            decreaseFather();
            to--;
            return true;
        }
        return false;
    }

    /**
     * Restituisce true se questa lista contiene tutti gli elementi della collezione
     * specificata.
     *
     * @param coll - collezione di cui deve essere verificata la presenza in questa
     *             lista.
     * @return true se questa lista contiene tutti gli elementi della collezione
     *         specificata
     * @throws NullPointerException se la collezione specificata e'null
     * @see #contains(Object)
     */
    public boolean containsAll(HCollection coll) {
        if (coll == null) {
            throw new NullPointerException();
        }

        HIterator iter = coll.iterator();
        while (iter.hasNext()) {
            if (!contains(iter.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Aggiunge tutti gli elementi della collezione specificata alla fine di questa
     * lista, nell'ordine in cui vengono restituiti dall'iteratore della collezione
     * specificata. Il comportamento di questa operazione
     * non è specificato se la collezione specificata viene modificata mentre
     * l'operazione è in corso. (Si noti che ciò si verificherà se la collezione
     * specificata è questa lista e non è vuota.)
     *
     * @param coll - collezione di cui gli elementi devono essere inseriti in questa
     *             lista
     * @return true se questa lista è cambiata a seguito di questa chiamata
     * @see #add(Object)
     */
    public boolean addAll(HCollection coll) {
        if (coll == null) {
            throw new NullPointerException();
        }
        if (coll.isEmpty()) {
            return false;
        }
        HIterator iter = coll.iterator();
        while (iter.hasNext()) {
            add(iter.next());
        }
        return true;
    }

    /**
     *
     * Rimuove da questa lista tutti gli elementi contenuti nella collezione
     * specificata.
     * Dopo questa chiamata, la lista non conterrà elementi in comune con la
     * collezione specificata.
     * 
     * @param coll - collezione di cui gli elementi devono essere rimossi da questa
     *             lista
     * @return true se questa lista è cambiata a seguito di questa chiamata
     *
     * @throws NullPointerException se la collezione specificata e'null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean removeAll(HCollection coll) {
        if (coll == null) {
            throw new NullPointerException();
        }
        boolean modified = false;
        HIterator iter = coll.iterator();
        while (iter.hasNext()) {
            Object current = iter.next();
            while (contains(current)) {
                modified = true;
                remove(current);
            }
        }
        return modified;
    }

    /**
     * Conserva solo gli elementi in questa lista che sono contenuti nella
     * collezione specificata. In altre parole, rimuove da questa
     * lista tutti gli elementi che non sono contenuti nella collezione specificata.
     *
     * @param coll - collezione che definisce gli elementi di questa lista che
     *             devono
     *             essere conservati
     * @return true se questa lista è cambiata a seguito di questa chiamata
     *
     * @throws NullPointerException se la collezione specificata e'null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    public boolean retainAll(HCollection coll) {
        if (coll == null) {
            throw new NullPointerException();
        }
        boolean modified = false;

        HIterator iter = iterator();
        while (iter.hasNext()) {
            if (!coll.contains(iter.next())) {
                iter.remove();
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Rimuove tutti gli elementi da questo elenco. Questo elenco sarà vuoto dopo il
     * termine di questa chiamata.
     */
    public void clear() {
        HCollection coll = new ListAdapter(this);
        this.removeAll(coll);
    }

    /**
     * Confronta l'oggetto specificato con questa lista per determinarne
     * l'uguaglianza.
     * Restituisce true se e solo se l'oggetto specificato è anche una lista,
     * entrambe le liste hanno la stessa dimensione e tutte le coppie di elementi
     * corrispondenti nelle due liste sono uguali. (Due elementi e1 ed e2 sono
     * uguali se (e1==null ? e2==null : e1.equals(e2)).) In altre parole, due
     * liste sono definite uguali se contengono gli stessi elementi nello stesso
     * ordine.
     *
     * @param obj - Oggetto che deve essere confrontato con questa lista per
     *            determinarne l'uguaglianza
     * @return true se l'oggetto specificato e'uguale a questa lista
     *
     * @see Object#equals(Object)
     * @see HList#equals(Object)
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ListAdapter)) {
            return false;
        }
        ListAdapter objList = (ListAdapter) obj;
        if (size() != objList.size()) {
            return false;
        }
        HIterator iter = objList.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Object obj1 = iter.next();
            Object obj2 = get(i++);
            if (!(obj1 == null ? obj2 == null : obj1.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Restituisce il valore dell'hash code di questa lista.
     * Il codice hash di una lista è definito come il risultato del seguente
     * calcolo:
     * {@code 
     *  hashCode = 1;
        Iterator i = list.iterator();
        while (i.hasNext()) {
            Object obj = i.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
     * }}
     * 
     * Ciò garantisce che list1.equals(list2) implichi che
     * list1.hashCode()==list2.hashCode() per due elenchi qualsiasi, list1 e list2,
     * come richiesto dal contratto generale di Object.hashCode.
     *
     * @return il valore dell'hash code di questa lista
     *
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    public int hashCode() {
        int hashCode = 1;
        HListIterator iter = listIterator();
        while (iter.hasNext()) {
            Object obj = iter.next();
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    // ----------------------------------- HLIST METHODS
    // ------------------------------------------

    /**
     * Restituisce l'elemento nella posizione specificata in questa lista.
     * 
     * @param index - indice dell'elemento da restituire
     * @return l'elemento nella posizione specificata in questa lista
     * 
     * @throws IndexOutOfBoundsException se l'indice e' fuori dai limiti
     *                                   ({@code (index < 0 || index >= size())})
     */
    public Object get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return list.elementAt(from + index);
    }

    /**
     * Sostituisce l'elemento nella posizione specificata in questa lista con
     * l'elemento specificato
     *
     * @param index   - indice dell'elemento da sostituire
     * @param element - elemento da memorizzare nella posizione specificata in
     *                questa lista
     * @return l'elemento precedentemente nella posizione specificata.
     *
     * @throws IndexOutOfBoundsException se l'indice e' fuori dai limiti
     *                                   ({@code (index < 0 || index >= size())})
     */
    public Object set(int index, Object element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Object toReturn = list.elementAt(from + index);
        list.setElementAt(element, from + index);
        return toReturn;
    }

    /**
     * Inserisce l'elemento specificato nella posizione specificata in questa lista.
     * Sposta l'elemento attualmente in quella posizione (se presente) e tutti gli
     * elementi successivi a destra (aggiunge uno ai
     * rispettivi indici).
     *
     * @param index   - indice al quale l'elemento specificato deve essere inserito
     * @param element - elemento che deve essere inserito
     *
     * @throws IndexOutOfBoundsException se l'indice e' fuori dai limiti
     *                                   ({@code (index < 0 || index > size())})
     */
    public void add(int index, Object element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        list.insertElementAt(element, from + index);
        increaseFather();
        to++;
    }

    /**
     * Inserisce tutti gli elementi nella collezione specificata in questa lista
     * nella posizione specificata. Sposta l'elemento attualmente in quella
     * posizione (se presente) e tutti gli elementi successivi
     * a destra (aumenta i loro indici). I nuovi elementi verranno visualizzati in
     * questo elenco nell'ordine in cui vengono restituiti dall'iteratore della
     * collezione specificata. Il comportamento di questa operazione non è
     * specificato se la collezione specificata viene modificata mentre l'operazione
     * è in corso.
     * (Si noti che ciò si verificherà se la collezione specificata è questa lista e
     * non è vuota.)
     *
     * @param index - indice in cui inserire il primo elemento dalla collezione
     *              specificata
     * @param coll  - collezione contenente gli elementi da inserire nella lista
     * @return true se questa lista è cambiata a seguito di questa chiamata
     *
     * @throws NullPointerException      se la collezione specificata e'null
     * @throws IndexOutOfBoundsException se l'indice e' fuori dai limiti
     *                                   ({@code (index < 0 || index > size())})
     */
    public boolean addAll(int index, HCollection coll) {
        if (coll == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (coll.isEmpty()) {
            return false;
        }
        HIterator iter = coll.iterator();
        int i = 0;
        while (iter.hasNext()) {
            add(index + i, iter.next());
            i++;
        }
        return true;

    }

    /**
     * Rimuove l'elemento nella posizione specificata in questa lista. Sposta tutti
     * gli elementi successivi a sinistra (sottrae uno
     * dai loro indici).
     * Restituisce l'elemento che è stato rimosso dall'elenco.
     *
     * @param index - indice dell'elemento da rimuovere
     * @return l'elemento precedentemente nella posizione specificata
     *
     * @throws IndexOutOfBoundsException se l'indice e' fuori dai limiti
     *                                   ({@code (index < 0 || index >= size())})
     */
    public Object remove(int index) {
        if (isEmpty() || index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Object toReturn = list.elementAt(from + index);
        list.removeElementAt(from + index);
        decreaseFather();
        to--;
        return toReturn;
    }

    /**
     * Restituisce l'indice in questa lista della prima occorrenza dell'elemento
     * specificato oppure -1 se questa lista non contiene tale elemento.
     * Più formalmente, restituisce l'indice i più basso tale che (o==null ?
     * get(i)==null : o.equals(get(i))), o -1 se non esiste tale indice.
     *
     * @param obj - elemento da cercare
     * @return l'indice in questa lista della prima occorrenza dell'elemento
     *         specificato oppure -1 se questa lista non contiene tale elemento
     */
    public int indexOf(Object obj) {
        if (!contains(obj)) {
            return -1;
        }
        int index = list.indexOf(obj, from);
        return index - from;
    }

    /**
     * Restituisce l'indice in questa lista dell'ultima occorrenza dell'elemento
     * specificato oppure -1 se questa lista non contiene tale elemento.
     * Più formalmente, restituisce l'indice i più alto tale che (o==null ?
     * get(i)==null : o.equals(get(i))), o -1 se non esiste tale indice.
     *
     * @param obj - elemento da cercare
     * @return l'indice in questa lista dell'ultima occorrenza dell'elemento
     *         specificato oppure -1 se questa lista non contiene tale elemento
     */
    public int lastIndexOf(Object obj) {
        if (!contains(obj)) {
            return -1;
        }
        int index = list.lastIndexOf(obj, to - 1);
        return index - from;
    }

    /**
     * Restituisce un list iterator che opera sugli elementi in questa lista (nella
     * sequenza corretta).
     * 
     * @return un list iterator che opera sugli elementi in questa lista (nella
     *         sequenza
     *         corretta).
     */
    public HListIterator listIterator() {
        return new ListAdapterIterator(this);
    }

    /**
     * Restituisce un list iterator che opera sugli elementi in questa lista (nella
     * sequenza corretta), partendo dalla posizione specificata in questa lista.
     * L'indice specificato indica il primo elemento che verrebbe restituito da una
     * chiamata iniziale al metodo next(). Una chiamata iniziale al metodo
     * previous() restituirebbe l'elemento con l'indice specificato meno uno.
     *
     * @param index - indice del primo elemento che deve essere restituito da una
     *              chiamata al metodo next() del list iterator.
     * @return un list iterator che opera sugli elementi in questa lista (nella
     *         sequenza corretta), partendo dalla posizione specificata in questa
     *         lista.
     * @throws IndexOutOfBoundsException se l'indice e' fuori dai limiti
     *                                   ({@code (index < 0 || index > size())})
     */
    public HListIterator listIterator(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        return new ListAdapterIterator(this, index);
    }

    /**
     * Restituisce una visualizzazione della parte di questa lista compresa tra
     * fromIndex (incluso) e toIndex (escluso) specificati. (Se fromIndex e
     * toIndex sono uguali, la lista restituita è vuota.) La lista restituita è
     * supportata da questa lista, quindi le modifiche non strutturali nella lista
     * restituita si riflettono in questa lista e viceversa. La lista restituita
     * supporta tutte le operazioni delle liste supportate da questa lista.
     * 
     * Questo metodo elimina la necessità di operazioni di intervallo esplicite (del
     * tipo comunemente esistente per gli arary). Qualsiasi operazione che prevede
     * una lista può essere utilizzata come operazione di intervallo passando una
     * sottolista anziché un intero elenco. Ad esempio, la seguente istruzione
     * rimuove un intervallo di elementi da una lista:
     * 
     * <pre>
     * list.subList(from, to).clear();
     * </pre>
     *
     * 
     * Istruzioni simili possono essere costruite per indexOf e lastIndexOf e tutti
     * gli algoritmi nella classe ListAdapter possono essere applicati a una
     * subList.
     * 
     * La semantica della lista restituita da questo metodo diventa indefinita se
     * la lista di supporto (cioè questa lista) viene modificata strutturalmente in
     * qualsiasi modo diverso dalla lista restituita. (Le modifiche strutturali sono
     * quelle che cambiano la dimensione di questa lista, o la perturbano in altro
     * modo in modo tale che le iterazioni in corso possano produrre risultati
     * errati).
     *
     * @param fromIndex - indice iniziale (incluso) della sottolista.
     * @param toIndex   - indice finale (escluso) della sottolista.
     * @return una visualizzazione della parte di questa lista specificata
     *
     * @throws IndexOutOfBoundsException se gli indici sono fuori dai limiti
     *                                   ({@code (fromIndex < 0 || tiIndex > size() || fromIndex > toIndex)})
     */
    public HList subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        return new ListAdapter(this, from + fromIndex, from + toIndex);
    }

    /**
     * Incrementa il campo to della lista father (se esistente) e di tutte le liste
     * father di essa ricorsivamente
     */
    private void increaseFather() {
        if (hasFather) {
            father.to++;
            father.increaseFather();
        }
    }

    /**
     * Decrementa il campo to della lista father (se esistente) e di tutte le liste
     * father di essa ricorsivamente
     */
    private void decreaseFather() {
        if (hasFather) {
            father.to--;
            father.decreaseFather();
        }
    }

    // CLASS LISTADAPTERITERATOR

    /**
     * Un iteratore per ListAdapter che consente di attraversare la lista
     * in entrambe le direzioni, modificare la lista durante l'iterazione e ottenere
     * la
     * posizione corrente dell'iteratore nell'elenco.
     * 
     * Non ha alcun elemento corrente; la sua posizione del cursore si trova sempre
     * tra l'elemento
     * che sarebbe restituito da una chiamata a previous() e l'elemento che sarebbe
     * restituito da una chiamata a next(). In una lista di lunghezza n, ci sono n+1
     * valori di indice validi,
     * da 0 a n, compreso.
     * 
     * I metodi remove() e set(Object) non sono definiti in termini di posizione del
     * cursore, ma sono definiti
     * per operare sull'ultimo elemento restituito da una chiamata a next() o
     * previous().
     * <P>
     * 
     */
    private class ListAdapterIterator implements HListIterator, HIterator {
        private int previous;
        private int next;
        private ListAdapter adapter;
        private char lastOp;

        /**
         * Costruttore di ListAdapterIterator senza indice. Crea un iteratore che opera
         * su un ListAdapter specificato partendo dalla posizione iniziale di esso (un
         * invocazione di next() restituirebbe 0, mentre una di previous() lancerebbe un
         * eccezione)
         * 
         * @param adapter - ListAdapter su cui deve operare l'iteratore
         */
        public ListAdapterIterator(ListAdapter adapter) {
            this.adapter = adapter;
            previous = adapter.from - 1;
            next = adapter.from;
            lastOp = '0';
        }

        /**
         * Costruttore di ListAdapterIterator con indice. Crea un iteratore che opera
         * su un ListAdapter specificato partendo dalla posizione specificata di esso
         * (un invocazione iniziale di next() restituirebbe tale indice, mentre una di
         * previous() restituirebbe tale indice meno uno)
         * 
         * @param adapter   - ListAdapter su cui deve operare l'iteratore
         * @param fromIndex - indice dell'elemento di ListIterator che verrebbe
         *                  restituito da un'invocazione iniziale di next()
         */
        public ListAdapterIterator(ListAdapter adapter, int fromIndex) {
            this.adapter = adapter;
            previous = fromIndex - 1;
            next = fromIndex;
            lastOp = '0';
        }

        /**
         * Restituisce true se questo iteratore ha ancora degli elementi da ispezionare
         * durante l'attraversamento della lista in avanti. (In altre parole,
         * restituisce true se next() restituirebbe un elemento anziché generare
         * un'eccezione.)
         *
         * @return se questo iteratore ha ancora degli elementi da ispezionare
         *         durante l'attraversamento della lista in avanti.
         */
        public boolean hasNext() {
            return next < adapter.to;
        }

        /**
         * Restituisce l'elemento successivo nella lista. Questo metodo può essere
         * chiamato ripetutamente per scorrere l'elenco, o mescolato con le chiamate al
         * metodo previous() per andare avanti e indietro. (Si noti che alternando le
         * chiamate a next() e a previous() verra' restituito lo stesso elemento
         * ripetutamente.)
         *
         * @return l'elemento successivo nella lista.
         * @exception NoSuchElementException se l'iteratore non ha elementi successivi
         */
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastOp = 'N';
            Object toReturn = adapter.get(next - adapter.from);
            next++;
            previous++;
            return toReturn;
        }

        /**
         * Restituisce true se questo iteratore ha ancora degli elementi da ispezionare
         * durante l'attraversamento della lista all'indietro. (In altre parole,
         * restituisce true se previous() restituirebbe un elemento anziché generare
         * un'eccezione.)
         *
         * @return true se questo iteratore ha ancora degli elementi da ispezionare
         *         durante l'attraversamento della lista all'indietro
         */
        public boolean hasPrevious() {
            return previous >= adapter.from;
        }

        /**
         * Restituisce l'elemento precedente nella lista. Questo metodo può essere
         * chiamato ripetutamente per scorrere l'elenco, o mescolato con le chiamate al
         * metodo next() per andare avanti e indietro. (Si noti che alternando le
         * chiamate a next() e a previous() verra' restituito lo stesso elemento
         * ripetutamente.)
         *
         * @return l'elemento successivo nella lista.
         * @exception NoSuchElementException se l'iteratore non ha elementi successivi
         */
        public Object previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastOp = 'P';
            Object toReturn = adapter.get(previous);
            previous--;
            next--;
            return toReturn;
        }

        /**
         * Restituisce l'indice dell'elemento che verrebbe restituito da una chiamata
         * successiva al metodo next(). (Restituisce la dimensione della lista se
         * l'iteratore si trova alla fine della lista.)
         *
         * @return l'indice dell'elemento che verrebbe restituito da una chiamata
         *         successiva al metodo next(), o la dimensione della lista se
         *         l'iteratore si trova alla fine della lista.
         */
        public int nextIndex() {
            return next - adapter.from;
        }

        /**
         * Restituisce l'indice dell'elemento che verrebbe restituito da una chiamata
         * successiva al metodo previous(). (Restituisce -1 se l'iteratore si trova
         * all'inizio della lista.)
         *
         * @return l'indice dell'elemento che verrebbe restituito da una chiamata
         *         successiva al metodo previous(), o -1 se l'iteratore si trova
         *         all'inizio della lista.
         */
        public int previousIndex() {
            return previous - adapter.from;
        }

        // Modification Operations

        /**
         * Rimuove dalla lista l'ultimo elemento restituito dal metodo next() o
         * previous(). Questa chiamata può essere effettuata solo una
         * volta per chiamata a next() o previous().
         * Può essere effettuato solo se ListIterator.add non è stato chiamato dopo
         * l'ultima chiamata a next() o previous().
         *
         * @exception IllegalStateException se ne' next() ne' previous() sono stati
         *                                  chiamati, oppure remove o add sono stati
         *                                  chiamati dopo l'ultima chiamata a next() o
         *                                  previous()
         */
        public void remove() {
            if (lastOp == '0' || lastOp == 'A' || lastOp == 'R') {
                throw new IllegalStateException();
            }
            if (lastOp == 'N') {
                adapter.remove(previous);
                next--;
                previous--;
            }
            if (lastOp == 'P') {
                adapter.remove(next);
            }
            lastOp = 'R';
        }

        /**
         * Sostituisce l'ultimo elemento della lista restituito dal metodo next() o
         * previous() con l'elemento specificato.
         * Questa chiamata può essere effettuata solo se ne' ListIterator.remove ne'
         * ListIterator.add sono stati chiamati dopo l'ultima chiamata a next() o
         * previous()
         * 
         * @param obj - l'elemento con cui sostituire l'ultimo elemento della lista
         *            restituito dal metodo next() o previous()
         *
         * @exception IllegalStateException se ne' next() ne' previous() sono stati
         *                                  chiamati, oppure remove o add sono stati
         *                                  chiamati dopo l'ultima chiamata a next() o
         *                                  previous()
         */
        public void set(Object obj) {
            if (lastOp == '0' || lastOp == 'A' || lastOp == 'R') {
                throw new IllegalStateException();
            }
            if (lastOp == 'N') {
                adapter.set(previous, obj);
            }
            if (lastOp == 'P') {
                adapter.set(next, obj);
            }
        }

        /**
         * Inserisce l'elemento specificato nella lista.
         * L'elemento viene inserito immediatamente prima del prossimo elemento che
         * verrebbe restituito da next(), se presente, e dopo il prossimo elemento che
         * verrebbe restituito da previous(), se presente. (Se la lista non contiene
         * elementi, il nuovo elemento diventa l'unico elemento della lista.)
         * Il nuovo elemento viene inserito prima del cursore implicito: una successiva
         * chiamata a next() non sarebbe influenzata e una successiva chiamata a
         * previous() restituirebbe il nuovo elemento .
         * (Questa chiamata aumenta di uno il valore che verrebbe restituito da una
         * chiamata a nextIndex o previousIndex.)
         *
         * @param obj - l'elemento da inserire.
         */
        public void add(Object obj) {
            adapter.add(next, obj);
            next++;
            previous++;
            lastOp = 'A';
        }
    }

}
