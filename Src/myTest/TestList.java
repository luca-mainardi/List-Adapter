package myTest;

import myAdapter.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

/**
 * Classe di test di tutti i metodi di {@link myAdapter.ListAdapter}
 * <p>
 * Summary: questa classe testa il funzionamento di tutti i metodi della classe
 * {@link myAdapter.ListAdapter}. Nella prima parte sono testati i metodi
 * ereditati dall'interfaccia {@link myAdapter.HCollection}, in seguito i metodi
 * ereditati dall'interfaccia {@link myAdapter.HList}. La sezione finale e'
 * dedicata invece ai metodi degli Iteratori, in quanto essi hanno molti scenari
 * di test
 * <br>
 * <br>
 * Test Suite Design: questa classe contiene un metodo di test per ogni metodo
 * della classe {@link myAdapter.ListAdapter}. Per i metodi di
 * {@link myAdapter.ListAdapter} che possono lanciare eccezioni sono inoltre
 * presenti test dedicati solo alle eccezioni lanciabili in modo da verificarne
 * il corretto funzionamento. Al metodo
 * {@link myAdapter.ListAdapter#subList(int, int)} e ai metodi degli iteratori
 * sono dedicati piu' test in quanto hanno molte funzionalita' e casi
 * particolari da testare.
 * I metodi ereditati da {@link myAdapter.HCollection} sono testati sia usando
 * un {@link myAdapter.HCollection} che un {@link myAdapter.HList} come oggetto
 * mentre quelli ereditati da {@link myAdapter.HList} sono testati solo con una
 * {@link myAdapter.HList}
 * I valori usati nei test sono di tipo {@link Integer}, {@link Double} e
 * {@link String} in modo da verificare il corretto funzionamento generale con
 * diversi tipi di oggetto. Poiche' gli oggetti di tipo
 * {@link myAdapter.ListAdapter} possono contenere anche il valore null, ogni
 * metodo e' testato anche nel caso di presenza di tale valore
 * <br>
 * <br>
 * PreConditions:
 * <br>
 * Prima dell'esecuzione di ogni test tutte le variabili di esecuzione devono
 * essere inizializzate a meno che il metodo testato non richieda variabili non
 * inizializzate
 * <br>
 * Ai metodi che ricevono dei parametri devono essere passati valori del tipo
 * richiesto altrimenti sara' lanciata un'eccezione del tipo
 * {@link ClassCastException}
 * <br>
 * <br>
 * PostConditions: I metodi di test modificano il contenuto della lista usando i
 * metodi messi a disposizione dalla classe {@link myAdapter.ListAdapter}. Lo
 * stato finale della lista deve corrispondere a quello di un altra lista a cui
 * sono stati aggiunti manualmente i valori aspettati
 * <br>
 * <br>
 * Test Suite Execution Record: Ogni metodo della classe
 * {@link myAdapter.ListAdapter} puo' dirsi correttamente funzionante se i test
 * ad esso relativi sono portati a termine con successo.
 * Il successo di tutti i metodi della classe garantisce la correttezza di tutte
 * le funzionalita' della classe {@link myAdapter.ListAdapter}
 * <br>
 * <br>
 * Execution Variables:
 * <br>
 * {@link myAdapter.HList} list1, list2 - liste usate per testare i metodi
 * ereditati da {@link myAdapter.HCollection} e da {@link myAdapter.HList}
 * <br>
 * {@link myAdapter.HCollection} coll1 - collezione usata per testare i metodi
 * di {@link myAdapter.HCollection}
 * <br>
 * {@link myAdapter.HCollection} coll2 - collezione usata come parametro per i
 * metodi che necessitano parametri di tipo {@link myAdapter.HCollection}
 * <br>
 * {@link myAdapter.HListIterator} listIter - iteratore usato per testare i
 * metodi ereditati dalle interfacce {@link myAdapter.HIterator} e
 * {@link myAdapter.HListIterator}
 * <br>
 * String[] argv - array di stringhe contente valori da inserire nella lista e
 * usato per testare i metodi che ricevono Object[] come parametro
 * <br>
 * <br>
 * 
 * @version JUnit 4.13.2
 * @version Harmcrest: 1.3
 * @version JVM from JME CLDC 1.1
 * 
 * @author Luca Mainardi
 * 
 * @see myAdapter.HList
 * @see myAdapter.HIterator
 * @see myAdapter.HListIterator
 * @see myAdapter.HCollection
 */
public class TestList {
	HCollection coll1 = null, coll2 = null;
	HList list1 = null, list2 = null;
	HListIterator listIter = null;
	String[] argv = null;

	/**
	 * Metodo per inizializzare le variabili di esecuzione prima di ogni test
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo metodo inizializza una {@link myAdapter.HCollection}, una
	 * {@link myAdapter.HList} e un array di stringhe che possono essere usati in
	 * ogni test.
	 * <br>
	 * <br>
	 * Description: La {@link myAdapter.HCollection} coll1 e la
	 * {@link myAdapter.HList} list1 vengono creati attraverso il costruttore di
	 * {@link myAdapter.ListAdapter}.
	 * L'array di stringhe argv e' creato e contiene 6 stringhe.
	 * Entrambe le liste sono vuote. Le altre variabili di stato rimangono
	 * inizializzate a null e devono essere create all'interno dei test
	 * che ne fanno uso
	 * <br>
	 * <br>
	 * PreConditions: Il costruttore {@link myAdapter.ListAdapter#ListAdapter()}
	 * deve essere correttamente funzionante
	 * <br>
	 * <br>
	 * PostConditions: Le due liste coll1 e list1 sono vuote e pronte ad essere
	 * utilizzate.
	 * L'array di stringhe args contiene 6 stringhe, di cui una presente due volte.
	 * Le altre variabili di esecuzione sono null.
	 */
	@Before
	public void setup() {
		System.out.println("Instantiate an empty Collection");
		coll1 = new ListAdapter();
		System.out.println("Instantiate an empty List");
		list1 = new ListAdapter();
		argv = new String[] { "pippo", "qui", "pluto", "paperino", "qui", "ciccio" };
	}

	/**
	 * Metodo per ripristinare le variabili di esecuzione allo stato iniziale dopo
	 * ogni test
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo metodo svuota coll1 list1 riportandole allo stato iniziale e
	 * stampa un messaggio a video alla terminazione di ogni metodo di test
	 * <br>
	 * <br>
	 * Description: La {@link myAdapter.HCollection} coll1 e la
	 * {@link myAdapter.HList} list1 vengono svuotate invocando il metodo
	 * {@link myAdapter.ListAdapter#clear()}.
	 * <br>
	 * <br>
	 * PreConditions: Il metodo {@link myAdapter.ListAdapter#clear()}
	 * deve essere correttamente funzionante
	 * <br>
	 * <br>
	 * PostConditions: Le due liste coll1 e list1 sono vuote
	 * 
	 */
	@After
	public void cleanup() {
		System.out.println("Purge all remaining elements");
		coll1.clear();
		list1.clear();
	}

	/**
	 * Test del costruttore {@link myAdapter.ListAdapter#ListAdapter(HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del costruttore di
	 * {@link myAdapter.ListAdapter} che riceve come parametro una
	 * {@link myAdapter.HCollection}
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * La collezione coll2 e' creata e riempita con i valori di argv e un valore
	 * null
	 * <br>
	 * Il costruttore {@link myAdapter.ListAdapter#ListAdapter(HCollection)} e'
	 * invocato usando come parametro coll2 per creare i
	 * {@link myAdapter.ListAdapter} coll1 e list1
	 * <br>
	 * Il costruttore {@link myAdapter.ListAdapter#ListAdapter(HCollection)} e'
	 * invocato usando come parametro list1 per creare list2
	 * <br>
	 * Viene verificato che la dimensione di coll2 sia uguale a quella di coll1, di
	 * list1 e di list2
	 * <br>
	 * Usando il metodo {@link myAdapter.ListAdapter#toArray()} si verifica che il
	 * contenuto di coll2 sia lo stesso di quello di list1, list2 e coll1
	 * <br>
	 * Verifica che se la collezione passata come parametro e' vuota allora anche la
	 * lista costruita sara'vuota
	 * <br>
	 * <br>
	 * Description: list1 e coll1 sono create usando come parametro coll2,
	 * contenente 6 stringhe e un valore null, poi list2 e' creata usando come
	 * parametro list1, infine la dimensione e il contenuto di list1, list 2 e coll1
	 * sono confrontati con quelli di coll2 per verificarne l'uguaglianza.
	 * Infine e' testato il caso limite in cui il parametro e' una lista vuota
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#toArray()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: list1, list2 e coll1 devono contenere gli stessi valori della
	 * collezione coll2 e avere di conseguenza la stessa dimensione
	 * <br>
	 * <br>
	 * Expected Results: il costruttore
	 * {@link myAdapter.ListAdapter#ListAdapter(HCollection)} deve costruire un
	 * {@link myAdapter.ListAdapter} che contiene gli stessi elementi della
	 * collezione passata come parametro
	 */
	@Test
	public void testConstructor() {
		System.out.println("TestConstructor");

		coll2 = new ListAdapter();
		for (int i = 0; i < argv.length; i++) {
			coll2.add(argv[i]);
		}
		coll2.add(null);

		coll1 = new ListAdapter(coll2);
		list1 = new ListAdapter(coll2);
		list2 = new ListAdapter(list1);

		assertEquals("Constructor with parameters not working correctly\n", coll2.size(), coll1.size());
		assertEquals("Constructor with parameters not working correctly\n", coll2.size(), list1.size());
		assertEquals("Constructor with parameters not working correctly\n", coll2.size(), list1.size());

		assertArrayEquals("Constructor with parameters not working correctly\n", coll2.toArray(), coll1.toArray());
		assertArrayEquals("Constructor with parameters not working correctly\n", coll2.toArray(), list1.toArray());
		assertArrayEquals("Constructor with parameters not working correctly\n", coll2.toArray(), list2.toArray());

		coll2 = new ListAdapter();
		list1 = new ListAdapter(coll2);
		assertEquals("Constructor with parameters not working correctly\n", 0, list1.size());

	}

	/**
	 * Test dell'eccezione lanciata dal costruttore
	 * {@link myAdapter.ListAdapter#ListAdapter(HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di un'eccezione nel caso in
	 * cui al costruttore sia passato come parametro il valore null
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il costruttore {@link myAdapter.ListAdapter#ListAdapter(HCollection)} e'
	 * invocato usando come parametro null per creare il
	 * {@link myAdapter.ListAdapter} list1
	 * <br>
	 * <br>
	 * Description: list1 e' creata passando come parametro al costruttore null in
	 * modo da verificare il lancio di una {@link NullPointerException}
	 * <br>
	 * <br>
	 * PostConditions: list1 non puo' essere creata poiche' il costruttore non
	 * puo'creare una lista ricevendo null invece che una collezione;
	 * <br>
	 * <br>
	 * Expected Results: il costruttore
	 * {@link myAdapter.ListAdapter#ListAdapter(HCollection)} lancia una
	 * {@link NullPointerException} perche' il parametro ricevuto non puo' essere
	 * utilizzato
	 */
	@Test(expected = NullPointerException.class)
	public void testConstructorException() {
		System.out.println("TestConstructorException");
		list1 = new ListAdapter(null);
	}

	// TEST METODI EREDITATI DALL'INTERFACCIA HCOLLECTION

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#size()}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#size()}, invocandolo sia su liste vuote sia su
	 * liste piene
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Verifica della dimensione delle liste vuote
	 * <br>
	 * Inseirmento di valori di diverso tipo in coll1 e list1 e verifica della loro
	 * dimensione
	 * <br>
	 * Rimozione di valori da coll1 e list1 e verifica della loro
	 * dimensione
	 * <br>
	 * <br>
	 * Description: il metodo {@link myAdapter.ListAdapter#size()} e' invocato sulle
	 * liste coll1 e list1 vuote e dopo aver invocato i metodi
	 * {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#remove(Object)}. Il test verifica che ci sia
	 * stato effettivamente un incremento o decremento nel valore restituito dal
	 * metodo {@link myAdapter.ListAdapter#size()}. Il test e' eseguito anche usando
	 * il valore null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)},
	 * {@link myAdapter.ListAdapter#remove(Object)} e
	 * {@link myAdapter.ListAdapter#get(int)} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#size()} deve
	 * restituire il numero di elmenti presenti nella lista
	 * <br>
	 * <br>
	 * Expected Results: il valore restituito dal metodo
	 * {@link myAdapter.ListAdapter#size()} e' 0 se la lista e' vuota, altrimenti
	 * corrisponde al numero di elementi in essa presenti
	 */
	@Test
	public void testSize() {
		System.out.println("TestSize");

		assertEquals("List not empty", 0, coll1.size());
		assertEquals("List not empty", 0, list1.size());

		coll1.add(1);
		coll1.add(3.48);
		coll1.add(null);
		assertEquals("List size not working correctly\n", 3, coll1.size());

		for (int i = 0; i < argv.length; i++) {
			list1.add(argv[i]);
		}
		assertEquals("List size not working correctly\n", 6, list1.size());

		coll1.remove(1);
		coll1.remove(null);
		assertEquals("List size not working correctly\n", 1, coll1.size());

		Object s = list1.get(2);
		System.out.println(s);
		list1.remove(s);
		assertEquals("List size not working correctly\n", 5, list1.size());
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#isEmpty()}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#isEmpty()}, che verifica che una lista sia
	 * vuota, cioe' che la sua dimensione e' 0
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Viene verificato che list1 e coll1 siano vuote all'inizio del metodo
	 * <br>
	 * Vengono aggiunti due alementi a coll1 e il valore null a list1
	 * <br>
	 * Si verifica che entrambe le liste ora non siano vuote, cioe' che isEmpty()
	 * restituisca false
	 * <br>
	 * Viene rimosso un elemento di coll1 e l'unico elemento di list1
	 * <br>
	 * Si verifica che ora coll1 non sia vuota e list1 sia vuota
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di isEmpty() invocato
	 * su liste vuote, su liste piene e su liste riempite e successivamente
	 * svuotater.
	 * I valori presenti nelle liste non vuote sono di tipo {@link Double} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#remove(Object)} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: list1 e' vuota dopo che e' stato rimosso il valore null,
	 * coll1 invece ha ancora un elemento. Il metodo
	 * {@link myAdapter.ListAdapter#isEmpty()} restituisce quindi true quando
	 * invocato su list1, mentre false se invocato su coll1
	 * <br>
	 * <br>
	 * Expected Results: il metodo {@link myAdapter.ListAdapter#isEmpty()} deve
	 * restituire true se e solo se la lista e' vuota, cioe' se non contiene nessun
	 * elemento, false altrimenti
	 */
	@Test
	public void testIsEmpty() {
		System.out.println("TestIsEmpty");

		assertTrue("isEmpty not working correctly\n", list1.isEmpty());
		assertTrue("isEmpty not working correctly\n", coll1.isEmpty());

		coll1.add(5);
		coll1.add(8.4);
		list1.add(null);

		assertFalse("isEmpty not working correctly\n", list1.isEmpty());
		assertFalse("isEmpty not working correctly\n", coll1.isEmpty());

		coll1.remove(5);
		list1.remove(null);

		assertTrue("isEmpty not working correctly\n", list1.isEmpty());
		assertFalse("isEmpty not working correctly\n", coll1.isEmpty());
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#contains(Object)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#contains(Object)}, che verifica che un dato
	 * elemento sia contenuto nella lista
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Viene verificato che list1 e coll1 all'inizio del metodo non contengano dei
	 * valori (null per coll1 e una stringa per list1)
	 * <br>
	 * Vengono aggiunte due stringhe a list1 e il valore null a coll1
	 * <br>
	 * Si verifica la presenza di una stringa effettivamente contenuta in list1 e di
	 * una non contenuta in essa.
	 * Si verifica la presenza del valore null in coll1
	 * <br>
	 * Viene rimosso un elemento di list1 e l'unico elemento di coll1
	 * <br>
	 * Si verifica che ora coll1 non contenga null e che list1 non contenga la
	 * stringa appena rimossa
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#contains(Object)} invocato
	 * su liste vuote (restituisce sempre false per ogni elemento passato come
	 * parametro) e su liste piene passando come parametro sia elementi
	 * effettivamente presenti nelle liste sia elementi non contenuti in esse
	 * I valori presenti nelle liste non vuote sono di tipo {@link String} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#remove(Object)} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#contains(Object)} deve
	 * restituire true se e' presente nella lista almeno un elemento uguale a quello
	 * passato come parametro, false altrimenti
	 * <br>
	 * <br>
	 * Expected Results: il metodo {@link myAdapter.ListAdapter#contains(Object)}
	 * deve restituire true se ad esso sono passati come parametri gli elementi
	 * inseriti manualmente, false se la lista su cui e' invocato e' vuota o se il
	 * parametro non e' uno degli elementi inseriti manualmente
	 */
	@Test
	public void testContains() {
		System.out.println("TestContains");

		assertFalse("Contains not working correctly\n", coll1.contains(null));
		assertFalse("Contains not working correctly\n", list1.contains("pippo"));

		coll1.add(null);
		list1.add("pippo");
		list1.add("pluto");

		assertTrue("Contains not working correctly\n", coll1.contains(null));
		assertTrue("Contains not working correctly\n", list1.contains("pippo"));
		assertFalse("Contains not working correctly\n", list1.contains("qui"));

		list1.remove("pippo");
		coll1.remove(null);

		assertFalse("Contains not working correctly\n", coll1.contains(null));
		assertFalse("Contains not working correctly\n", list1.contains("pippo"));
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#toArray(Object[])}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo toArray,
	 * che restituisce un array di Object contenente tutti gli elementi presenti
	 * nella lista.
	 * Nella prima parte del test viene testato il metodo senza parametri, mentre in
	 * seguito viene testato il metodo
	 * {@link myAdapter.ListAdapter#toArray(Object[])}, che riceve come parametro un
	 * array di Object. Se tale array puo' contenere tutti i valori della lista
	 * allora questi sono inseriti nell'array e eventuali spazi rimasti liberi sono
	 * riempiti con valori null. Se invece l'array non e' sufficiente a contenere
	 * tutti gli elementi della lista, il metodo crea e restituisce un nuovo array
	 * di Object contente gli elementi della lista
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Viene verificato che l'invocazione di {@link myAdapter.ListAdapter#toArray()}
	 * sulla lista vuota list1 restituisce un array vuoto
	 * <br>
	 * Vengono aggiunti e rimossi alcuni elementi da coll1 e viene verificato che il
	 * metodo {@link myAdapter.ListAdapter#toArray()} invocato su coll1 restituisca
	 * un array uguale a uno creato manualmente e contenente i valori aspettati
	 * <br>
	 * Viene invocato {@link myAdapter.ListAdapter#toArray(Object[])} su list1 vuota
	 * passando come parametro un array contenente gli stessi valori di argv e si
	 * verifica che successivamente tale array contiene solo valori null
	 * <br>
	 * Si reinseriscono i valori di args nell'array da passare come parametro e si
	 * inseriscono due valori in list1. Viene invocato
	 * {@link myAdapter.ListAdapter#toArray(Object[])} su list1 usando l'array come
	 * parametro e si verifica che successivamente tale array contiene i due valori
	 * inseriti in list1 e i restanti valori sono null
	 * <br>
	 * Si crea un array da passare come parametro che contiene solo un valore e si
	 * verifica che invocando {@link myAdapter.ListAdapter#toArray(Object[])} su
	 * list1 passando come parametro tale array si ottiene un array contenente solo
	 * gli elementi di list1. Si verifica infine che l'array passato come parametro
	 * non e'stato modificato
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#toArray()} invocato su liste vuote o piene.
	 * Viene verificato anche {@link myAdapter.ListAdapter#toArray(Object[])} sia
	 * nel caso in cui il parametro sia un array di dimensione maggiore della lista
	 * sia nel caso in cui l'array parametro non e' sufficiente a contenere gli
	 * elementi della lista.
	 * I valori presenti nelle liste non vuote sono di tipo {@link String},
	 * {@link Integer}, {@link Double} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#remove(Object)} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#toArray()} deve
	 * restituire un array contenente gli elementi della lista nell'ordine in cui
	 * sono presenti in essa.
	 * Il metodo {@link myAdapter.ListAdapter#toArray(Object[])} deve restituire
	 * l'array passato come parametro in cui sono stati inseriti tutti gli elementi
	 * della lista su cui e' invocato nell'ordine in cui sono presenti in essa.
	 * Se tale array e' di dimensione maggiore della lista, gli spazi rimanenti
	 * devono essere valori null.
	 * Se tale array e' di dimensione maggiore della lista, gli spazi rimanenti
	 * devono essere valori null.
	 * Se invece tale array e' troppo poco capiente il metodo deve creare e
	 * restituire un nuovo array di Object contente gli elementi della lista
	 * <br>
	 * <br>
	 * Expected Results: i metodi {@link myAdapter.ListAdapter#toArray(Object[])} e
	 * {@link myAdapter.ListAdapter#toArray()} devono restituire degli array di
	 * Object uguali a quelli creati manualmente e contenenti i valori aspettati.
	 */
	@Test
	public void testToArray() {
		System.out.println("TestToArray");

		assertArrayEquals("ToArray not working correctly\n", new Object[0], list1.toArray());

		coll1.add(2);
		coll1.add(null);
		coll1.add(3.5);
		coll1.add(null);
		coll1.remove(2);

		assertArrayEquals("ToArray not working correctly\n", new Object[] { null, 3.5, null }, coll1.toArray());

		Object[] param = argv;
		list1.toArray(param);
		assertArrayEquals("ToArray with parameter not working correctly\n",
				new Object[] { null, null, null, null, null, null }, param);

		param = argv;
		list1.add("paperino");
		list1.add("topolino");
		list1.toArray(param);
		assertArrayEquals("ToArray with parameter not working correctly\n",
				new Object[] { "paperino", "topolino", null, null, null, null }, param);

		param = new Object[] { "pippo" };
		assertArrayEquals("ToArray with parameter not working correctly\n",
				new Object[] { "paperino", "topolino" }, list1.toArray(param));
		assertArrayEquals("ToArray with parameter not working correctly\n",
				new Object[] { "pippo" }, param);
	}

	/**
	 * Test dell'eccezione lanciata dal metodo
	 * {@link myAdapter.ListAdapter#toArray(Object[])}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di un'eccezione nel caso in
	 * cui al metodo sia passato come parametro il valore null
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il meotdo {@link myAdapter.ListAdapter#toArray(Object[])} e'
	 * invocato usando come parametro null invece che un array valido
	 * <br>
	 * <br>
	 * Description: su list1 e' invocato toArray(Object[]) passando come parametro
	 * null in
	 * modo da verificare il lancio di una {@link NullPointerException}
	 * <br>
	 * <br>
	 * PostConditions: list1 non puo' essere convertita in array perche' il
	 * parametro che il metodo riceve non e'valido; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#toArray(Object[])} lancia una
	 * {@link NullPointerException} perche' il parametro ricevuto non puo' essere
	 * utilizzato
	 */
	@Test(expected = NullPointerException.class)
	public void testToArrayException() {
		System.out.println("TestArrayException");
		list1.toArray(null);
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#add(Object)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo add,
	 * che riceve un'oggetto come parametro e lo inserisce in fondo alla lista.
	 * Se l'aggiunta ha successo, cioe' se la lista viene effettivamente
	 * modificata, il metodo restituisce true, altrimenti false
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Viene verificato che dopo l'invocazione di
	 * {@link myAdapter.ListAdapter#add(Object)} per aggiungere un elemento a coll1
	 * e tre a list1 le dimensioni delle liste siano quelle aspettate e le liste
	 * contengano gli elementi inseriti
	 * <br>
	 * Viene aggiunto il valore null a coll1 e si verifica la sua effettiva presenza
	 * in coll1 con il metodo {@link myAdapter.ListAdapter#contains(Object)}
	 * <br>
	 * Si verifica che il metodo {@link myAdapter.ListAdapter#toArray()} invocato su
	 * coll1 e list1 rstutuisca array uguali a degli array creati manualmente e
	 * contenenti i valori aspettati
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#add(Object)} invocato su liste vuote o non
	 * vuote.
	 * I valori inseriti nelle liste sono di tipo {@link String},
	 * {@link Integer}, {@link Double} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#size()},
	 * {@link myAdapter.ListAdapter#contains(Object)} e
	 * {@link myAdapter.ListAdapter#toArray()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il parametro passato al metodo
	 * {@link myAdapter.ListAdapter#add(Object)} deve essere presente in fondo alla
	 * lista, cioe' come ultimo elemento dell'array restituito dal metodo
	 * {@link myAdapter.ListAdapter#toArray()}. Il metodo deve restituire true se la
	 * lista viene modificata, false altrimenti
	 * <br>
	 * <br>
	 * Expected Results: Ogni invocazione del metodo restituisce true(l'aggiunta ha
	 * sempre successo). Gli array restituiti dal metodo
	 * {@link myAdapter.ListAdapter#toArray()} devono essere uguali ad array creati
	 * manualmente e contenenti i valori aspettati
	 */
	@Test
	public void testAdd() {
		System.out.println("TestAdd");

		assertTrue("Add not working correctly\n", coll1.add("pippo"));
		assertTrue("Add not working correctly\n", list1.add(0));
		assertTrue("Add not working correctly\n", list1.add(0.5));
		assertTrue("Add not working correctly\n", list1.add(2));

		assertEquals("Add not working correctly\n", 1, coll1.size());
		assertEquals("Add not working correctly\n", 3, list1.size());

		assertTrue("Add not working correctly\n", coll1.contains("pippo"));
		assertTrue("Add not working correctly\n", list1.contains(2));

		coll1.add(null);
		assertTrue("Add not working correctly\n", coll1.contains(null));

		assertArrayEquals("Add not working correctly\n", new Object[] { "pippo", null }, coll1.toArray());
		assertArrayEquals("Add not working correctly\n", new Object[] { 0, 0.5, 2 }, list1.toArray());
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#remove(Object)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo remove,
	 * che riceve un'oggetto come parametro e ne rimuove la prima istanza nella
	 * lista. Se la rimozione ha successo, cioe' se la lista viene effettivamente
	 * modificata, il metodo restituisce true, altrimenti false
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Viene verificato che l'invocazione di
	 * {@link myAdapter.ListAdapter#remove(Object)} su coll1
	 * list1 vuote restituisca il valore false
	 * <br>
	 * Viene aggiunto un valore a coll1 e si verifica il corretto funzionamento del
	 * metodo {@link myAdapter.ListAdapter#remove(Object)} nei casi in cui il
	 * parametro sia un elemento diverso o uguale a quello appena inserito.
	 * Si controlla che l'array restituito da
	 * {@link myAdapter.ListAdapter#toArray()} su coll1 sia uguale a un array vuoto
	 * <br>
	 * Si modifica a null un elemento di argv e si aggiungono a list1 tutti gli
	 * elementi di argv
	 * <br>
	 * Si verifica il funzionamento del metodo
	 * {@link myAdapter.ListAdapter#remove(Object)} su list1 passando come parametro
	 * il valore null,un elemento presente due volte in list1 e uno non presente
	 * nella lista
	 * <br>
	 * Si verifica che il metodo {@link myAdapter.ListAdapter#toArray()} invocato su
	 * list1 rstutuisca array uguale a un array creato manualmente e
	 * contenenti i valori aspettati
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#remove(Object)} invocato su liste vuote o non
	 * vuote, passando come parametro elementi non presenti nella lista, elementi
	 * presenti in copia singola ed elementi presenti piu'volte
	 * I valori inseriti nelle liste sono di tipo {@link String},
	 * {@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#add(Object)} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il parametro passato al metodo
	 * {@link myAdapter.ListAdapter#remove(Object)} deve essere rimosso dalla
	 * lista. Se sono presenti piu'copie di tale elemento viene rimossa la prima
	 * istanza di esso, nell'ordine in cui esso e' presente nell'array restituito da
	 * {@link myAdapter.ListAdapter#toArray()}.
	 * Il valore restituito dal metodo deve essere true se l'elemento
	 * e'effettivamente rimosso, false se non vengono effettuate modifiche alla
	 * lista
	 * <br>
	 * <br>
	 * Expected Results: Il metodo deve ritornare true quando il parametro con cui
	 * e' invocato e'presente nella lista, false se non e' presente(anche se la
	 * lista e' vuota). Gli array restituiti dal metodo
	 * {@link myAdapter.ListAdapter#toArray()} dopo la rimozione devono essere
	 * uguali ad array creati manualmente e contenenti i valori aspettati
	 */
	@Test
	public void testRemove() {
		System.out.println("TestRemove");

		assertFalse("Remove not working correctly\n", coll1.remove(2));
		assertFalse("Remove not working correctly\n", list1.remove(null));

		coll1.add(1);
		assertFalse("Remove not working correctly\n", coll1.remove(2));
		assertTrue("Remove not working correctly\n", coll1.remove(1));
		assertArrayEquals("Remove not working correctly\n", new Object[0], coll1.toArray());

		argv[2] = null;
		for (int i = 0; i < argv.length; i++) {
			list1.add(argv[i]);
		}

		assertTrue("Remove not working correctly\n", list1.remove(null));
		assertTrue("Remove not working correctly\n", list1.remove("qui"));
		assertFalse("Remove not working correctly\n", list1.remove("topolino"));

		assertArrayEquals("Remove not working correctly\n",
				new Object[] { "pippo", "paperino", "qui", "ciccio" },
				list1.toArray());
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#containsAll(HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#containsAll(HCollection)}, che verifica che
	 * tutti gli elementi della collezione passata come parametro siano contenuti
	 * nella lista
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Viene creata una collezione coll2 vuota e si verifica che list1 e coll1
	 * contengano tale collezione, cioe' che il metodo
	 * {@link myAdapter.ListAdapter#containsAll(HCollection)} restituisca true
	 * <br>
	 * Vengono aggiunti dei valori a list1 e coll1 e si verifica nuovamente che esse
	 * contengono tutta coll2 (ancora vuota)
	 * <br>
	 * Si aggingono altri elementi a coll1 e si aggiungono a coll2 diversi elementi
	 * in modo tale che essa contenga gli stessi elementi di coll1 ma in quantita'
	 * diversa.
	 * Si verifica poi che coll1 contenga tutta coll2
	 * <br>
	 * Si verifica che list1 non contenga coll2, cioe' che il metodo
	 * {@link myAdapter.ListAdapter#containsAll(HCollection)} restituisca false
	 * <br>
	 * Si modifica coll2 in modo che contenga solo un elemento appartenente a list1
	 * e uno non appartenente ad essa. Si verifica che list1 non contenga coll2,
	 * cioe' che il metodo{@link myAdapter.ListAdapter#containsAll(HCollection)}
	 * restituisca false
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#containsAll(HCollection)} invocato
	 * su liste vuote passando come parametro liste vuote, su liste vuote
	 * passando come parametro liste non vuote, e su liste non vuote passando come
	 * parametro liste non vuote. Nell'ultimo caso la lista passata come parametro
	 * puo' contenere solo elementi presenti nella lista su cui il metodo
	 * e'invocato, solo elementi non presenti in essa oppure sia elementi presenti
	 * sia non presenti.
	 * I valori presenti nelle liste non vuote sono di tipo {@link String},
	 * {@link Double},{@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#clear()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo
	 * {@link myAdapter.ListAdapter#containsAll(HCollection)} deve
	 * restituire true se e solo se nella lista sono presenti tutti gli elementi
	 * presenti nella collezione passata come parametro, senza tenere conto della
	 * loro molteplicita'
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#containsAll(HCollection)}
	 * deve restituire true quando ad esso e' passata come parametro una collezione
	 * vuota o contenente solo elementi presenti nella lista, anche se con
	 * molteplicita' diverse
	 */
	@Test
	public void testContainsAll() {
		System.out.println("TestContainsAll");

		coll2 = new ListAdapter();
		assertTrue("ContainsAll not working correctly\n", coll1.containsAll(coll2));
		assertTrue("ContainsAll not working correctly\n", list1.containsAll(coll2));

		coll1.add(null);
		list1.add(2);
		assertTrue("ContainsAll not working correctly\n", coll1.containsAll(coll2));
		assertTrue("ContainsAll not working correctly\n", list1.containsAll(coll2));

		coll1.add("pippo");
		coll1.add("pippo");
		coll1.add("qui");
		coll2.add("pippo");
		assertTrue("ContainsAll not working correctly\n", coll1.containsAll(coll2));
		coll2.add("pippo");
		coll2.add(null);
		coll2.add("pippo");
		assertTrue("ContainsAll not working correctly\n", coll1.containsAll(coll2));

		assertFalse("ContainsAll not working correctly\n", list1.containsAll(coll2));

		coll2.clear();
		coll2.add(2);
		coll2.add(3.5);
		assertFalse("ContainsAll not working correctly\n", list1.containsAll(coll2));
	}

	/**
	 * Test dell'eccezione lanciata dal metodo
	 * {@link myAdapter.ListAdapter#containsAll(HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di un'eccezione nel caso in
	 * cui al metodo sia passato come parametro il valore null
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il meotdo {@link myAdapter.ListAdapter#containsAll(HCollection)} e'
	 * invocato usando come parametro null invece che una collezione valida
	 * <br>
	 * <br>
	 * Description: su list1 e' invocato containsAll passando come parametro
	 * null in modo da verificare il lancio di una {@link NullPointerException}
	 * <br>
	 * <br>
	 * PostConditions: non si puo' verificare che list1 contenga il parametro
	 * perche' esso non e' una collezione esistente; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#containsAll(HCollection)} lancia una
	 * {@link NullPointerException} perche' il parametro ricevuto non puo' essere
	 * utilizzato
	 */
	@Test(expected = NullPointerException.class)
	public void testContainsAllException() {
		System.out.println("TestContainsAllException");
		list1.containsAll(null);
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#addAll(HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#addAll(HCollection)}, che aggiunge
	 * tutti gli elementi della collezione passata come parametro alla lista e
	 * restituisce true se essa viene effettivamente modificata, false altrimenti. I
	 * nuovi elementi vengono aggiunti in fondo alla lista, nell'ordine dell'array
	 * restituito dal metodo {@link myAdapter.ListAdapter#toArray()}
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Viene creata una collezione coll2 vuota e si verifica che il metodo
	 * {@link myAdapter.ListAdapter#addAll(HCollection)} invocato su list1 e
	 * coll1 vuote passando come parametro tale collezione restituisca false,
	 * perche' list1 e coll1 non subiscono modifiche
	 * <br>
	 * Vengono aggiunti dei valori a list1 e coll1 e si verifica nuovamente che esse
	 * non subiscano modifiche se a
	 * {@link myAdapter.ListAdapter#addAll(HCollection)} viene passata coll2
	 * (ancora vuota)
	 * <br>
	 * Si svuota list1 e si aggiungono elementi a coll2, poi si invoca
	 * {@link myAdapter.ListAdapter#addAll(HCollection)} su list1 e coll1
	 * passando come parametro coll2 e verificando che il valore restituito sia
	 * true.
	 * <br>
	 * Si verifica che gli array restituiti da
	 * {@link myAdapter.ListAdapter#toArray()} invocato su list1 e coll1
	 * corrispondano a degli array creati manualmente contenenti i valori aspettati
	 * e che la dimensione di coll1 sia ora uguale alla somma della sua dimensione
	 * prima di invocare il metodo e quella di coll2
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#addAll(HCollection)} invocato
	 * su liste vuote passando come parametro liste vuote, su liste vuote
	 * passando come parametro liste non vuote, e su liste non vuote passando come
	 * parametro liste non vuote.
	 * I valori presenti nelle liste non vuote sono di tipo
	 * {@link String},{@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)},
	 * {@link myAdapter.ListAdapter#size()} e
	 * {@link myAdapter.ListAdapter#toArray()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo
	 * {@link myAdapter.ListAdapter#addAll(HCollection)} deve aggiungere alla lista
	 * tutti gli elementi della collezione passata come parametro e
	 * restituire true se e solo se la lista subisce effettivamente delle modifiche
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#addAll(HCollection)}
	 * deve restituire false quando ad esso e' passata come parametro una collezione
	 * vuota, true altrimenti. Dopo l'invocazione del metodo la lista deve contenere
	 * tutti gli elementi di tale collezione, oltre a quelli gia' presenti prima
	 * dell'invocazione. I nuovi elementi vengono aggiunti in fondo alla lista,
	 * nell'ordine dell'array restituito dal metodo
	 * {@link myAdapter.ListAdapter#toArray()}
	 */
	@Test
	public void testAddAll() {
		System.out.println("TestAddAll");

		coll2 = new ListAdapter();
		assertFalse("AddAll not working correctly\n", coll1.addAll(coll2));
		assertFalse("AddAll not working correctly\n", list1.addAll(coll2));

		coll1.add("qui");
		list1.add(null);
		assertFalse("AddAll not working correctly\n", coll1.addAll(coll2));
		assertFalse("AddAll not working correctly\n", list1.addAll(coll2));

		list1.clear();
		coll2.add(2);
		coll2.add(null);
		int size = coll1.size();
		assertTrue("AddAll not working correctly\n", coll1.addAll(coll2));
		assertTrue("AddAll not working correctly\n", list1.addAll(coll2));

		assertEquals("AddAll not working correctly\n", size + coll2.size(), coll1.size());
		assertArrayEquals("AddAll not working correctly\n", new Object[] { "qui", 2, null }, coll1.toArray());
		assertArrayEquals("AddAll not working correctly\n", new Object[] { 2, null }, list1.toArray());
	}

	/**
	 * Test dell'eccezione lanciata dal metodo
	 * {@link myAdapter.ListAdapter#addAll(HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di un'eccezione nel caso in
	 * cui al metodo sia passato come parametro il valore null
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il meotdo {@link myAdapter.ListAdapter#addAll(HCollection)} e'
	 * invocato usando come parametro null invece che una collezione valida
	 * <br>
	 * <br>
	 * Description: su list1 e' invocato addAll passando come parametro
	 * null in modo da verificare il lancio di una {@link NullPointerException}
	 * <br>
	 * <br>
	 * PostConditions: non si puo' aggiungere a list1 il parametro
	 * perche' esso non e' una collezione esistente; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#addAll(HCollection)} lancia una
	 * {@link NullPointerException} perche' il parametro ricevuto non puo' essere
	 * utilizzato
	 */
	@Test(expected = NullPointerException.class)
	public void testAddAllException() {
		System.out.println("TestAddAllException");
		list1.addAll(null);
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#removeAll(HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#removeAll(HCollection)}, che rimuove
	 * tutti gli elementi della collezione passata come parametro alla lista e
	 * restituisce true se essa viene effettivamente modificata, false altrimenti.
	 * Se nella lista sono presenti piu'copie di un elemento nella collezione
	 * passata come parametro, tutte le copie vengono rimosse.
	 * Se invece nella lista sono presenti meno copie di un elemento rispetto alla
	 * collezione passata come parametro, vengono semplicemente rimosse tutte le
	 * copie presenti
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Viene creata una collezione coll2 vuota e si verifica che il metodo
	 * {@link myAdapter.ListAdapter#removeAll(HCollection)} invocato su list1 e
	 * coll1 vuote passando come parametro tale collezione restituisca false,
	 * perche' list1 e coll1 non subiscono modifiche
	 * <br>
	 * Vengono aggiunti dei valori a list1 e coll1 e si verifica nuovamente che esse
	 * non subiscano modifiche se a
	 * {@link myAdapter.ListAdapter#removeAll(HCollection)} viene passata coll2
	 * (ancora vuota)
	 * <br>
	 * Si aggiungono a coll2 alcuni elementi presenti il coll1 (anche in molteplice
	 * copia) e si verifica che ora
	 * {@link myAdapter.ListAdapter#removeAll(HCollection)} restituisce true se
	 * invocato su coll1 e false se invocato su list1.
	 * Si verifica inoltre che gli elementi di list1 sono rimasti invariati
	 * <br>
	 * Si aggiungono a coll2 alcuni elementi presenti in list1.
	 * Si verifica che gli array restituiti da
	 * {@link myAdapter.ListAdapter#toArray()} invocato su list1 e coll1
	 * corrispondano a degli array creati manualmente contenenti i valori aspettati
	 * <br>
	 * Si svuota coll2 e si aggiungono ad essa due copie di un elemento presente in
	 * singola copia in coll1. Si verifica che il metodo
	 * {@link myAdapter.ListAdapter#removeAll(HCollection)} rimuova l'unica copia
	 * presente in coll1 e restituisca true
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#removeAll(HCollection)} invocato
	 * su liste vuote passando come parametro liste vuote, su liste vuote
	 * passando come parametro liste non vuote, e su liste non vuote passando come
	 * parametro liste non vuote.
	 * I valori presenti nelle liste non vuote sono di tipo
	 * {@link String},{@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)},
	 * {@link myAdapter.ListAdapter#clear()} e
	 * {@link myAdapter.ListAdapter#toArray()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo
	 * {@link myAdapter.ListAdapter#removeAll(HCollection)} deve rimuovere dalla
	 * lista tutti gli elementi della collezione passata come parametro e
	 * restituire true se e solo se la lista subisce effettivamente delle modifiche
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#removeAll(HCollection)}
	 * deve restituire false quando ad esso e' passata come parametro una collezione
	 * vuota o se tutti gli elementi di tale collezione sono gia' contenuti nella
	 * lista, true altrimenti. Dopo l'invocazione del metodo la lista non deve
	 * contenere nessun elemento della collezione
	 */
	@Test
	public void testRemoveAll() {
		System.out.println("TestRemoveAll");

		coll2 = new ListAdapter();
		assertFalse("RemoveAll not working correctly\n", coll1.removeAll(coll2));
		assertFalse("RemoveAll not working correctly\n", list1.removeAll(coll2));

		for (int i = 0; i < argv.length; i++) {
			coll1.add(argv[i]);
		}

		list1.add(0);
		list1.add(1);
		list1.add(null);
		list1.add(0);

		assertFalse("RemoveAll not working correctly\n", coll1.removeAll(coll2));
		assertFalse("RemoveAll not working correctly\n", list1.removeAll(coll2));

		coll2.add("qui");
		coll2.add("paperino");

		assertTrue("RemoveAll not working correctly\n", coll1.removeAll(coll2));
		assertFalse("RemoveAll not working correctly\n", list1.removeAll(coll2));

		assertArrayEquals("AddAll not working correctly\n", new Object[] { 0, 1, null, 0 }, list1.toArray());

		coll2.add(null);
		coll2.add(1);

		assertTrue("RemoveAll not working correctly\n", list1.removeAll(coll2));

		assertArrayEquals("AddAll not working correctly\n", new Object[] { "pippo", "pluto", "ciccio" },
				coll1.toArray());
		assertArrayEquals("AddAll not working correctly\n", new Object[] { 0, 0 }, list1.toArray());

		coll2.clear();
		coll2.add("pippo");
		coll2.add("pippo");
		assertTrue("RemoveAll not working correctly\n", coll1.removeAll(coll2));
		assertArrayEquals("AddAll not working correctly\n", new Object[] { "pluto", "ciccio" },
				coll1.toArray());
	}

	/**
	 * Test dell'eccezione lanciata dal metodo
	 * {@link myAdapter.ListAdapter#removeAll(HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di un'eccezione nel caso in
	 * cui al metodo sia passato come parametro il valore null
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il meotdo {@link myAdapter.ListAdapter#removeAll(HCollection)} e'
	 * invocato usando come parametro null invece che una collezione valida
	 * <br>
	 * <br>
	 * Description: su list1 e' invocato removeAll passando come parametro
	 * null in modo da verificare il lancio di una {@link NullPointerException}
	 * <br>
	 * <br>
	 * PostConditions: non si puo' rimuovere da list1 il parametro
	 * perche' esso non e' una collezione esistente; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#removeAll(HCollection)} lancia una
	 * {@link NullPointerException} perche' il parametro ricevuto non puo' essere
	 * utilizzato
	 */
	@Test(expected = NullPointerException.class)
	public void testRemoveAllException() {
		System.out.println("TestRemoveAllException");
		list1.removeAll(null);
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#retainAll(HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#retainAll(HCollection)}, che rimuove
	 * tutti gli elementi che non sono presenti nella collezione passata come
	 * parametro e restituisce true se essa viene effettivamente modificata, false
	 * altrimenti.
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Viene creata una collezione coll2 vuota e si verifica che il metodo
	 * {@link myAdapter.ListAdapter#retainAll(HCollection)} invocato su list1 e
	 * coll1 vuote passando come parametro tale collezione restituisca false,
	 * perche' list1 e coll1 non subiscono modifiche
	 * <br>
	 * Vengono aggiunti dei valori a coll1 e si invoca
	 * {@link myAdapter.ListAdapter#retainAll(HCollection)} passando come parametro
	 * coll2 (ancora vuota). Si verifica che il metodo restituisce true e che coll1
	 * e'ora vuota.
	 * <br>
	 * Si aggiungono a list1 alcuni elementi e a coll2 due elementi presenti in
	 * coll1 (anche in molteplice copia) e si verifica che
	 * {@link myAdapter.ListAdapter#retainAll(HCollection)} restituisce true.
	 * Si verifica inoltre con il metodo {@link myAdapter.ListAdapter#toArray()} che
	 * gli elementi di list1 sono solo quelli presenti anche
	 * in coll2, mantenendo le molteplicita' iniziali
	 * <br>
	 * Si invoca nuovamente {@link myAdapter.ListAdapter#retainAll(HCollection)} su
	 * list1 e con coll2 come parametro e si verifica che ora il valore restituito
	 * e'false in quanto non ci sono elementi da rimuovere
	 * Si verifica con il metodo {@link myAdapter.ListAdapter#toArray()} che list1
	 * contiene gli stessi elementi che erano presenti prima dell'invocazione
	 * <br>
	 * Si invoca su coll1 {@link myAdapter.ListAdapter#retainAll(HCollection)}
	 * passando come parametro coll2 e si verifica che il valore restituito e' false
	 * e che coll1 e' ancora vuota
	 * <br>
	 * Si aggiunge a coll1 un elemento non presente in coll2,
	 * si invoca su coll1 {@link myAdapter.ListAdapter#retainAll(HCollection)}
	 * passando come parametro coll2 e si verifica che il valore restituito e' true
	 * e che dopo l'invocazione coll1 e' vuota
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#retainAll(HCollection)} invocato
	 * su liste vuote passando come parametro liste vuote, su liste vuote
	 * passando come parametro liste non vuote, e su liste non vuote passando come
	 * parametro liste non vuote.
	 * I valori presenti nelle liste non vuote sono di tipo
	 * {@link String},{@link Integer}, {@link Double} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#toArray()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo
	 * {@link myAdapter.ListAdapter#retainAll(HCollection)} deve rimuovere dalla
	 * lista tutti gli elementi non presenti nella collezione passata come parametro
	 * e restituire true se e solo se la lista subisce effettivamente delle
	 * modifiche
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#retainAll(HCollection)}
	 * deve restituire false quando e' invocato su una collezione
	 * vuota o quando contiene tutti gli elementi della collezione passata come
	 * parametro, false altrimenti. Dopo l'invocazione del metodo la lista deve
	 * contenere tutti gli elementi comuni alla collezione
	 */
	@Test
	public void testRetainAll() {
		System.out.println("TestRetainAll");
		coll2 = new ListAdapter();

		assertFalse("RetainAll not working correctly\n", coll1.retainAll(coll2));
		assertFalse("RetainAll not working correctly\n", list1.retainAll(coll2));

		for (int i = 0; i < argv.length; i++) {
			coll1.add(argv[i]);
		}
		assertTrue("RetainAll not working correctly\n", coll1.retainAll(coll2));
		assertArrayEquals("RetainAll not working correctly\n", new Object[0], coll1.toArray());

		list1.add(0);
		list1.add(1);
		list1.add(null);
		list1.add(0);
		coll2.add(null);
		coll2.add(0);
		assertTrue("RetainAll not working correctly\n", list1.retainAll(coll2));
		assertArrayEquals("RetainAll not working correctly\n", new Object[] { 0, null, 0 }, list1.toArray());

		assertFalse("RetainAll not working correctly\n", list1.retainAll(coll2));
		assertArrayEquals("RetainAll not working correctly\n", new Object[] { 0, null, 0 }, list1.toArray());

		assertFalse("RetainAll not working correctly\n", coll1.retainAll(coll2));
		assertArrayEquals("RetainAll not working correctly\n", new Object[0], coll1.toArray());

		coll1.add(0.5);
		assertTrue("RetainAll not working correctly\n", coll1.retainAll(coll2));
		assertArrayEquals("RetainAll not working correctly\n", new Object[0], coll1.toArray());

	}

	/**
	 * Test dell'eccezione lanciata dal metodo
	 * {@link myAdapter.ListAdapter#retainAll(HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di un'eccezione nel caso in
	 * cui al metodo sia passato come parametro il valore null
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il meotdo {@link myAdapter.ListAdapter#retainAll(HCollection)} e'
	 * invocato usando come parametro null invece che una collezione valida
	 * <br>
	 * <br>
	 * Description: su list1 e' invocato retainAll passando come parametro
	 * null in modo da verificare il lancio di una {@link NullPointerException}
	 * <br>
	 * <br>
	 * PostConditions: non si puo' eseguire l'operazione richiesta
	 * perche' il parametro non e' una collezione esistente; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#retainAll(HCollection)} lancia una
	 * {@link NullPointerException} perche' il parametro ricevuto non puo' essere
	 * utilizzato
	 */
	@Test(expected = NullPointerException.class)
	public void testRetainAllException() {
		System.out.println("TestRetainAllException");
		list1.retainAll(null);
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#clear()}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#clear()}, che rimuove tutti gli elementi della
	 * lsita su cui e'invocato
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si invoca il metodo {@link myAdapter.ListAdapter#clear()} invocato su list1 e
	 * coll1 vuote.
	 * Si verifica che list1 e coll1 siano ancora vuote usando il metodo
	 * {@link myAdapter.ListAdapter#toArray()}
	 * <br>
	 * Vengono aggiunti dei valori a coll1 e il valore null a list1 e si invoca
	 * {@link myAdapter.ListAdapter#clear()} su entrambe.
	 * Si verifica che list1 e coll1 siano vuote usando il metodo
	 * {@link myAdapter.ListAdapter#toArray()}
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#clear()} invocato
	 * su liste vuote e non vuote.
	 * I valori presenti nelle liste non vuote sono di tipo
	 * {@link String} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#toArray()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo
	 * {@link myAdapter.ListAdapter#clear()} deve rimuovere dalla
	 * lista tutti presenti
	 * <br>
	 * <br>
	 * Expected Results: dopo ogni invocazione di
	 * {@link myAdapter.ListAdapter#clear()} la lista su cui esso e'invocato e'vuota
	 */
	@Test
	public void testClear() {
		System.out.println("TestClear");

		coll1.clear();
		list1.clear();
		assertArrayEquals("RetainAll not working correctly\n", new Object[0], coll1.toArray());
		assertArrayEquals("RetainAll not working correctly\n", new Object[0], list1.toArray());

		for (int i = 0; i < argv.length; i++) {
			coll1.add(argv[i]);
		}
		coll1.clear();
		assertArrayEquals("RetainAll not working correctly\n", new Object[0], coll1.toArray());

		list1.add(null);
		list1.clear();
		assertArrayEquals("RetainAll not working correctly\n", new Object[0], list1.toArray());
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#equals(Object)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#equals(Object)}, che verifica l'uguaglianza
	 * dell'oggetto ListAdapter su cui e' invocato con un altro oggetto passato come
	 * parametro
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si crea la collezione coll2 vuota e si verifica che il metodo
	 * {@link myAdapter.ListAdapter#equals(Object)} invocato su list1 e
	 * coll1 vuote passando come parametro coll2 restituisca true
	 * <br>
	 * Viene aggiunto lo stesso elemento in coll1, list1 e coll2; si aggiunge poi un
	 * altro elemento a list1. Con il metodo
	 * {@link myAdapter.ListAdapter#equals(Object)} si verifica che coll1 e coll2
	 * sono uguali mentre list1 e coll2 no
	 * <br>
	 * Si verifica che se al metodo {@link myAdapter.ListAdapter#equals(Object)} e'
	 * passato un parametro che non e' istanza di ListAdapter, esso restituisce
	 * false in ogni caso, anche se il parametro corrisponde con il contenuto della
	 * lista
	 * <br>
	 * Si verifica il corretto funzionamento nel caso di valore null
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#equals(Object)} invocato
	 * su liste vuote e non vuote, passando sia parametri corretti sia non corretti
	 * I valori presenti nelle liste non vuote sono di tipo {@link String} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#clear()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#equals(Object)} deve
	 * restituire true se e solo se il parametro e' un ListAdapter e contiene gli
	 * stessi elementi della lista su cui e' invocato
	 * <br>
	 * <br>
	 * Expected Results: ogni invocazione di
	 * {@link myAdapter.ListAdapter#equals(Object)} con parametro che non e' istanza
	 * di ListAdapter restituisce false. Le altre invocazioni restituiscono true se
	 * e solo se il parametro e' un ListAdapter contenente gli stessi elementi della
	 * lista su cui il metodo e' invocato
	 */
	@Test
	public void testEquals() {
		System.out.println("TestEquals");

		coll2 = new ListAdapter();
		assertTrue("Equals not working correctly\n", list1.equals(coll2));
		assertTrue("Equals not working correctly\n", coll1.equals(coll2));

		coll2.add("pippo");
		coll1.add("pippo");
		list1.add("pippo");
		list1.add("ciccio");
		assertFalse("Equals not working correctly\n", list1.equals(coll2));
		assertTrue("Equals not working correctly\n", coll1.equals(coll2));

		String s1 = "pippo";
		assertFalse("Equals not working correctly\n", coll1.equals(s1));

		String[] s2 = { "pippo", "ciccio" };
		assertFalse("Equals not working correctly\n", list1.equals(s2));

		list1.clear();
		coll2.clear();
		list1.add(null);
		coll2.add(null);
		assertFalse("Equals not working correctly\n", list1.equals(null));
		assertTrue("Equals not working correctly\n", list1.equals(coll2));
		assertTrue("Equals not working correctly\n", list1.equals(list1));
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#hashCode()}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#hashCode()}, che restituisce l'hashCode della
	 * lista
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si verifica che list1 e coll1 vuote abbiano lo stesso hashCode
	 * <br>
	 * Vengono aggiunti gli stessi elementi in coll1 e list1 e si verifica che gli
	 * hashCode di list1 e coll1 siano uguali
	 * <br>
	 * Vengono cambiano gli stessi elementi in list1 e si verifica che gli
	 * hashCode di list1 e coll1 non siano uguali
	 * <br>
	 * Vengono svuotate entrambe le liste, vengono aggiunti gli stessi elementi in
	 * coll1 e list1 (di tipo diverso rispetto alla parte precedente) e si verifica
	 * che gli hashCode di list1 e coll1 siano uguali
	 * <br>
	 * Si svuota list1 e si verifica che gli hashCode di list1 e coll1 non siano
	 * uguali
	 * <br>
	 * Si verifica il corretto funzionamento nel caso di valore null
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#hashCode()} invocato
	 * su liste vuote e non vuote.
	 * I valori presenti nelle liste non vuote sono di tipo {@link String},
	 * {@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#clear()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#hashCode()} deve
	 * restituire un hash code uguale a quello di un altra lista se e solo se le
	 * liste contengono gli stessi elementi, cioe' se il metodo
	 * {@link myAdapter.ListAdapter#equals(Object)} restituirebbe true
	 * <br>
	 * <br>
	 * Expected Results: gli hash code di due liste sono uguali se e solo se il
	 * metodo {@link myAdapter.ListAdapter#equals(Object)} restituirebbe true
	 */
	@Test
	public void testHashCode() {
		System.out.println("TestHashCode");

		assertEquals("HashCode not working correctly\n", coll1.hashCode(), list1.hashCode());

		coll1.add(1);
		coll1.add(2);
		coll1.add(3);

		list1.add(1);
		list1.add(2);
		list1.add(3);

		assertEquals("HashCode not working correctly\n", coll1.hashCode(), list1.hashCode());

		list1.clear();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		assertNotEquals("HashCode not working correctly\n", coll1.hashCode(), list1.hashCode());

		list1.clear();
		coll1.clear();
		list1.add("pippo");
		list1.add("pluto");
		coll1.add("pippo");
		coll1.add("pluto");
		assertEquals("HashCode not working correctly\n", coll1.hashCode(), list1.hashCode());

		list1.clear();
		assertNotEquals("HashCode not working correctly\n", coll1.hashCode(), list1.hashCode());

		list1.clear();
		coll1.clear();
		coll1.add(null);
		assertNotEquals("HashCode not working correctly\n", coll1.hashCode(), list1.hashCode());

		list1.add(null);
		assertEquals("HashCode not working correctly\n", coll1.hashCode(), list1.hashCode());

	}

	// TEST METODI EREDITATI DALL'INTERFACCIA HLIST

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#get(int)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#get(int)}, che restituisce l'elemento della
	 * lista in posizione pari al parametro passato, considerando l'ordine
	 * restituito dal metodo {@link myAdapter.ListAdapter#toArray()}.
	 * L'indice e' valido solo se appartenente all'intervallo [0,size[
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si aggiunge un elemento a list1 e si verifica che il metodo get(0)
	 * restituisca l'elemento appena inserito
	 * <br>
	 * Si aggiunge il valore null a list1 e si verifica che il metodo get(1)
	 * restituisca il valore appena inserito
	 * <br>
	 * Si svuota la lista e si inseriscono vari elementi, poi si verifica che per
	 * ogni indice valido il metodo {@link myAdapter.ListAdapter#get(int)}
	 * restituisca l'elemento aspettato
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#get(int)} invocato
	 * su liste non vuote e passando solo parametri validi.
	 * I valori presenti nelle liste non vuote sono di tipo {@link String},
	 * {@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#clear()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#get(int)} deve
	 * restituire l'elemento della lista nella posizione indicata dall'indice
	 * passato come parametro, considerando l'ordine restituito dal metodo
	 * {@link myAdapter.ListAdapter#toArray()}
	 * <br>
	 * <br>
	 * Expected Results: ogni invocazione di get(i) restituisce l'elemento nella
	 * posizione i della lista
	 */
	@Test
	public void testGet() {
		System.out.println("TestGet");

		list1.add("pippo");
		assertEquals("Get not working correctly\n", "pippo", list1.get(0));

		list1.add(null);
		assertEquals("Get not working correctly\n", null, list1.get(1));

		list1.clear();
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		for (int i = 0; i < list1.size(); i++) {
			assertEquals("Get not working correctly\n", i, list1.get(i));
		}
	}

	/**
	 * Test delle eccezioni lanciate dal metodo
	 * {@link myAdapter.ListAdapter#get(int)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di eccezioni nel caso in
	 * cui al metodo siano passati come parametro indici non validi
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il meotdo {@link myAdapter.ListAdapter#get(int)} e'
	 * invocato usando un parametro uguale a 0, minore di 0, uguale alla dimensione
	 * della lista e uguale all'indice di un elemento che e'stato rimosso dalla
	 * lista
	 * <br>
	 * <br>
	 * Description: su list1 e' invocato get passando parametri non validi in modo
	 * da verificare il lancio di una {@link IndexOutOfBoundsException}
	 * <br>
	 * <br>
	 * PreConditions: I metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#remove(Object)} devono essere funzionanti
	 * <br>
	 * <br>
	 * PostConditions: non si possono eseguire le operazioni richieste
	 * perche' i parametri non sono validi; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#get(int)} lancia una
	 * {@link IndexOutOfBoundsException} perche' il parametro ricevuto non puo'
	 * essere utilizzato
	 */
	@Test
	public void testGetExceptions() {
		System.out.println("TestGetExceptions");

		IndexOutOfBoundsException thrown = assertThrows("Get exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.get(0));

		list1.add(0);
		list1.add(null);
		list1.add(2);

		thrown = assertThrows("Get exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.get(-1));

		thrown = assertThrows("Get exceptions not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.get(list1.size()));

		list1.remove(null);

		thrown = assertThrows("Get exceptions not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.get(2));
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#set(int,Object)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#set(int,Object)}, che sostituisce l'elemento
	 * della
	 * lista in posizione pari al parametro passato, considerando l'ordine
	 * restituito dal metodo {@link myAdapter.ListAdapter#toArray()}, con l'oggetto
	 * passato come secondo parametro.
	 * L'indice e' valido solo se appartenente all'intervallo [0,size[
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si aggiungono tre element1 a list1 e si sostituiscono con valori null usando
	 * il metodo {@link myAdapter.ListAdapter#set(int,Object)}.
	 * Si verifica che list1 contenga i valori aspettati usando il metodo
	 * {@link myAdapter.ListAdapter#toArray()}
	 * <br>
	 * si sostituiscono alcuni elementi di list1 con vari valori usando
	 * il metodo {@link myAdapter.ListAdapter#set(int,Object)}.
	 * Si verifica che list1 contenga i valori aspettati usando il metodo
	 * {@link myAdapter.ListAdapter#toArray()}
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#set(int,Object)} invocato
	 * su liste non vuote e passando solo parametri validi.
	 * I valori presenti nelle liste non vuote sono di tipo {@link String},
	 * {@link Integer},{@link Double} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#toArray()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#set(int,Object)} deve
	 * sostituire l'elemento della lista nella posizione indicata dall'indice
	 * passato come parametro con l'oggetto passato come secondo parametro
	 * <br>
	 * <br>
	 * Expected Results: ogni invocazione di set(i,obj) sostituisce l'elemento nella
	 * posizione i della lista con l'oggetto obj
	 */
	@Test
	public void testSet() {
		System.out.println("TestSet");

		list1.add("pippo");
		list1.add("pluto");
		list1.add("qui");
		for (int i = 0; i < 3; i++) {
			list1.set(i, null);
		}

		assertArrayEquals("Set not working correctly\n", new Object[] { null, null, null }, list1.toArray());

		list1.set(1, 4.5);
		list1.set(0, 2);

		assertArrayEquals("Set not working correctly\n", new Object[] { 2, 4.5, null }, list1.toArray());

		list1.set(1, 0);
		list1.set(2, 2);

		assertArrayEquals("Set not working correctly\n", new Object[] { 2, 0, 2 }, list1.toArray());
	}

	/**
	 * Test delle eccezioni lanciate dal metodo
	 * {@link myAdapter.ListAdapter#set(int,Object)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di eccezioni nel caso in
	 * cui al metodo siano passati come parametro indici non validi
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il meotdo {@link myAdapter.ListAdapter#set(int,Object)} e'
	 * invocato usando un parametro uguale a 0, minore di 0, uguale alla dimensione
	 * della lista e uguale all'indice di un elemento che e'stato rimosso dalla
	 * lista
	 * <br>
	 * <br>
	 * Description: su list1 e' invocato set passando parametri non validi in modo
	 * da verificare il lancio di una {@link IndexOutOfBoundsException}
	 * <br>
	 * <br>
	 * PreConditions: I metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#remove(Object)} devono essere funzionanti
	 * <br>
	 * <br>
	 * PostConditions: non si possono eseguire le operazioni richieste
	 * perche' i parametri non sono validi; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#set(int,Object)} lancia una
	 * {@link IndexOutOfBoundsException} perche' il parametro ricevuto non puo'
	 * essere utilizzato
	 */
	@Test
	public void testSetExceptions() {
		System.out.println("TestSetExceptions");

		IndexOutOfBoundsException thrown = assertThrows("Set exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.set(0, 1));

		list1.add(0);
		list1.add("pippo");
		list1.add(2);

		thrown = assertThrows("Set exceptions not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.set(-1, "qui"));

		thrown = assertThrows("Set exceptions not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.set(list1.size(), 4));

		list1.remove("pippo");

		thrown = assertThrows("Set not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.set(2, null));
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#add(int,Object)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#add(int,Object)}, che aggiunge nella posizione
	 * della lista pari al parametro passato, considerando l'ordine
	 * restituito dal metodo {@link myAdapter.ListAdapter#toArray()}, l'oggetto
	 * passato come secondo parametro.
	 * L'indice e' valido solo se appartenente all'intervallo [0,size]
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si crea una lista list2 vuota e si inseriscono degli elementi nelle liste
	 * vuote con il metodo {@link myAdapter.ListAdapter#add(int,Object)}.
	 * Si verifica che list1 e list2 contengano i valori aspettati usando i metodi
	 * {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#size()}.
	 * <br>
	 * Si aggiungono diversi elementi alla fine di list1 e list2 con il metodo
	 * {@link myAdapter.ListAdapter#add(int,Object)}.
	 * Si verifica che list1 e list2 contengano i valori aspettati usando i metod1
	 * {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#size()}
	 * <br>
	 * Si verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#add(int,Object)} nel caso di inserimenti in
	 * posizione iniziale o intermedia alla lista, usando i metodi
	 * {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#size()}
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#add(int,Object)} invocato
	 * su liste vuote e non vuote, passando solo parametri validi.
	 * I valori inseriti sono di tipo {@link String},
	 * {@link Integer},{@link Double} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)},
	 * {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#size()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#add(int,Object)} deve
	 * inserire nella posizione della lista indicata dall'indice
	 * passato come parametro l'oggetto passato come secondo parametro
	 * <br>
	 * <br>
	 * Expected Results: ogni invocazione di add(i,obj) inserisce nella
	 * posizione i della lista l'oggetto obj
	 */
	@Test
	public void testAddWithIndex() {
		System.out.println("TestAddWithIndex");

		list2 = new ListAdapter();

		list1.add(0, null);
		list2.add(0, 5);

		assertArrayEquals("Add with index not working correctly\n", new Object[] { null },
				list1.toArray());
		assertArrayEquals("Add with index not working correctly\n", new Object[] { 5 },
				list2.toArray());

		assertEquals("Add with index not working correctly\n", 1, list1.size());
		assertEquals("Add with index not working correctly\n", 1, list2.size());

		list1.add(1, "pippo");
		list1.add(2, "pluto");
		list1.add(3, "qui");
		assertArrayEquals("Add with index not working correctly\n", new Object[] { null, "pippo", "pluto", "qui" },
				list1.toArray());

		list2.add(4.5);
		list2.add(2, null);

		assertArrayEquals("Add with index not working correctly\n", new Object[] { 5, 4.5, null },
				list2.toArray());

		list2.add(0, null);

		assertArrayEquals("Add with index not working correctly\n", new Object[] { null, 5, 4.5, null },
				list2.toArray());

		list2.add(1, "ciccio");
		list2.add(3, 8);

		assertArrayEquals("Add with index not working correctly\n", new Object[] { null, "ciccio", 5, 8, 4.5, null },
				list2.toArray());

		assertEquals("Add with index not working correctly\n", 4, list1.size());
		assertEquals("Add with index not working correctly\n", 6, list2.size());
	}

	/**
	 * Test delle eccezioni lanciate dal metodo
	 * {@link myAdapter.ListAdapter#add(int,Object)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di eccezioni nel caso in
	 * cui al metodo siano passati come parametro indici non validi
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il meotdo {@link myAdapter.ListAdapter#add(int,Object)} e'
	 * invocato usando un parametro minore di 0, maggiore della dimensione
	 * della lista e uguale un indice valido prima di una rimozione
	 * <br>
	 * <br>
	 * Description: su list1 e' invocato add passando parametri non validi in modo
	 * da verificare il lancio di una {@link IndexOutOfBoundsException}
	 * <br>
	 * <br>
	 * PreConditions: I metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#remove(Object)} devono essere funzionanti
	 * <br>
	 * <br>
	 * PostConditions: non si possono eseguire le operazioni richieste
	 * perche' i parametri non sono validi; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#add(int,Object)} lancia una
	 * {@link IndexOutOfBoundsException} perche' il parametro ricevuto non puo'
	 * essere utilizzato
	 */
	@Test
	public void testAddWithIndexExceptions() {
		System.out.println("TestAddWithIndexExceptions");

		IndexOutOfBoundsException thrown = assertThrows("Add with index exceptions exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.add(1, 1));

		list1.add(0);
		list1.add("pippo");
		list1.add(2);

		thrown = assertThrows("Add with index exceptions not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.add(-1, "qui"));

		thrown = assertThrows("Add with index exceptions exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.add(list1.size() + 1, 4));

		list1.remove("pippo");

		thrown = assertThrows("Add with index exceptions not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.add(3, null));
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#addAll(int,HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#addAll(int,HCollection)}, che aggiunge nella
	 * posizione della lista pari al parametro passato, considerando l'ordine
	 * restituito dal metodo {@link myAdapter.ListAdapter#toArray()}, tutti gli
	 * elementi della collezione passata come secondo parametro.
	 * Se tale collezione e' vuota il metodo restituisce false, altrimenti true.
	 * L'indice e' valido solo se appartenente all'intervallo [0,size]
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si crea una collezione coll2 vuota.
	 * Si verifica che il metodo
	 * {@link myAdapter.ListAdapter#addAll(int,HCollection)} restituisca
	 * false se invocato su una lista vuota con parametro coll2 vuota
	 * <br>
	 * Si aggiunge un elemento a list1 e si verifica che il metodo
	 * {@link myAdapter.ListAdapter#addAll(int,HCollection)} restituisca
	 * false se invocato su essa con parametro coll2 vuota
	 * <br>
	 * Si aggiungono elementi a coll2 e a list1 e si verifica il corretto
	 * funzionamento del metodo
	 * {@link myAdapter.ListAdapter#addAll(int,HCollection)} invocato su list1 e
	 * usando come parametro coll2, con indice 0, list1.size() e intermedio alla
	 * lista
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#addAll(int,HCollection)} invocato
	 * su liste vuote e non vuote, passando solo parametri validi.
	 * I valori inseriti sono di tipo {@link String},
	 * {@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)},
	 * {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#size()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo
	 * {@link myAdapter.ListAdapter#addAll(int,HCollection)} deve
	 * inserire nella posizione della lista indicata dall'indice
	 * passato come parametro tutti gli oggetti della collezione passata come
	 * secondo parametro e restituire false se e solo se essa e'vuota
	 * <br>
	 * <br>
	 * Expected Results: ogni invocazione di add(i,coll) inserisce nella
	 * posizione i della lista tutti gli oggetti di coll. Solo quando coll e' una
	 * collezione vuota il metodo deve restituire false
	 */
	@Test
	public void testAddAllWithIndex() {
		System.out.println("TestAddAllWithIndex");

		coll2 = new ListAdapter();
		assertFalse("AddAll with index not working correctly\n", list1.addAll(0, coll2));

		list1.add("pippo");
		assertFalse("AddAll with index not working correctly\n", list1.addAll(0, coll2));
		assertFalse("AddAll with index not working correctly\n", list1.addAll(1, coll2));

		list1.add(null);
		list1.add("paperino");

		coll2.add(1);
		coll2.add("qui");
		coll2.add(null);

		int size = list1.size();
		assertTrue("AddAll with index not working correctly\n", list1.addAll(0, coll2));
		assertEquals("AddAll with index not working correctly\n", size + coll2.size(), list1.size());

		assertTrue("AddAll with index not working correctly\n", list1.addAll(list1.size(), coll2));
		assertEquals("AddAll with index not working correctly\n", size + 2 * coll2.size(), list1.size());

		assertTrue("AddAll with index not working correctly\n", list1.addAll(3, coll2));
		assertEquals("AddAll with index not working correctly\n", size + 3 * coll2.size(), list1.size());

		assertArrayEquals("AddAll with index not working correctly\n",
				new Object[] { 1, "qui", null, 1, "qui", null, "pippo", null, "paperino", 1, "qui", null, },
				list1.toArray());
	}

	/**
	 * Test delle eccezioni lanciate dal metodo
	 * {@link myAdapter.ListAdapter#addAll(int,HCollection)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di eccezioni nel caso in
	 * cui al metodo siano passati come parametro indici non validi o una collezione
	 * non esistente
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Il meotdo {@link myAdapter.ListAdapter#add(int,Object)} e'
	 * invocato usando un indice minore di 0 e maggiore della dimensione
	 * della lista.
	 * <br>
	 * Il meotdo {@link myAdapter.ListAdapter#add(int,Object)} e'
	 * invocato usando una collezione nulla
	 * <br>
	 * <br>
	 * Description: su list1 e' invocato add passando parametri non validi in modo
	 * da verificare il lancio di una {@link IndexOutOfBoundsException} o di una
	 * {@link NullPointerException}, a seconda dei parametri inseriti
	 * <br>
	 * <br>
	 * PreConditions: Il metodo {@link myAdapter.ListAdapter#add(Object)} deve
	 * essere
	 * funzionante
	 * <br>
	 * <br>
	 * PostConditions: non si possono eseguire le operazioni richieste
	 * perche' i parametri non sono validi; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo
	 * {@link myAdapter.ListAdapter#addAll(int,HCollection)} lancia una
	 * {@link IndexOutOfBoundsException} se l'indice non e' valido,
	 * {@link NullPointerException} se la collezione e' nulla
	 */
	@Test
	public void testAddAllWithIndexExceptions() {
		System.out.println("TestAddAllWithIndexExceptions");

		NullPointerException thrown1 = assertThrows(
				"AddAll with index exceptions exceptions not working correctly\n",
				NullPointerException.class,
				() -> list1.addAll(1, coll2));

		thrown1 = assertThrows(
				"AddAll with index exceptions exceptions not working correctly\n",
				NullPointerException.class,
				() -> list1.addAll(0, coll2));

		coll2 = new ListAdapter();
		coll2.add("pippo");
		coll2.add(null);

		IndexOutOfBoundsException thrown2 = assertThrows(
				"AddAll with index exceptions exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.addAll(1, coll2));

		list1.add(0);
		list1.add("pippo");
		list1.add(2);

		thrown2 = assertThrows("AddAll with index exceptions not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.add(-1, coll2));

		thrown2 = assertThrows("AddAll with index exceptions exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.add(list1.size() + 1, coll2));

	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#remove(int)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#remove(int)}, che rimuove l'elemento nella
	 * posizione della lista pari al parametro passato, considerando l'ordine
	 * restituito dal metodo {@link myAdapter.ListAdapter#toArray()}, e restituisce
	 * l'elemento rimosso.
	 * L'indice e' valido solo se appartenente all'intervallo [0,size[
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si inseriscono degli elementi in list1 vuota con il metodo
	 * {@link myAdapter.ListAdapter#add(Object)}.
	 * <br>
	 * Si rimuovono il primo e l'ultimo elemento della lista con il metodo
	 * {@link myAdapter.ListAdapter#remove(int)} e si verifica che list1 contenga i
	 * valori aspettati usando i metodi
	 * {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#size()}.
	 * Si verifica inoltre che il metodo restituisca il valore corretto
	 * <br>
	 * Si rimuovono degli elementi intermedi della lista con il metodo
	 * {@link myAdapter.ListAdapter#remove(int)} e si verifica che list1 contenga i
	 * valori aspettati usando i metodi
	 * {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#size()}.
	 * Si verifica inoltre che il metodo restituisca i valori corretti
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#remove(int)} invocato
	 * su liste non vuote, passando solo parametri validi.
	 * I valori inseriti sono di tipo {@link String},
	 * {@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)},
	 * {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#size()} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#remove(int)} deve
	 * rimuovere e restituire l'elemento della lista nella posizione indicata
	 * dall'indice passato come parametro
	 * <br>
	 * <br>
	 * Expected Results: ogni invocazione di remove(i) rimuove e restituisce dalla
	 * lista l'elemento nella posizione i della lista
	 */
	@Test
	public void testRemoveWithIndex() {
		System.out.println("TestRemoveWithIndex");

		list1.add(null);
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list1.add(null);
		list1.add("pippo");

		assertEquals("Remove with index not working correctly\n", "pippo", list1.remove(5));

		assertArrayEquals("Remove with index not working correctly\n", new Object[] { null, 0, 1, 2, null },
				list1.toArray());
		assertEquals("Remove with index not working correctly\n", 5, list1.size());

		assertEquals("Remove with index not working correctly\n", null, list1.remove(0));

		assertArrayEquals("Remove with index not working correctly\n", new Object[] { 0, 1, 2, null },
				list1.toArray());
		assertEquals("Remove with index not working correctly\n", 4, list1.size());

		assertEquals("Remove with index not working correctly\n", 1, list1.remove(1));
		assertEquals("Remove with index not working correctly\n", 2, list1.remove(1));

		assertArrayEquals("Remove with index not working correctly\n", new Object[] { 0, null },
				list1.toArray());
		assertEquals("Remove with index not working correctly\n", 2, list1.size());
	}

	/**
	 * Test delle eccezioni lanciate dal metodo
	 * {@link myAdapter.ListAdapter#remove(int)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di eccezioni nel caso in
	 * cui al metodo siano passati come parametro indici non validi
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il meotdo {@link myAdapter.ListAdapter#remove(int)} e'
	 * invocato usando un parametro uguale a 0,minore di 0, uguale alla dimensione
	 * della lista e uguale un indice valido prima di una rimozione
	 * <br>
	 * <br>
	 * Description: su list1 e' invocato remove passando parametri non validi in
	 * modo
	 * da verificare il lancio di una {@link IndexOutOfBoundsException}
	 * <br>
	 * <br>
	 * PreConditions: I metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#remove(Object)} devono essere funzionanti
	 * <br>
	 * <br>
	 * PostConditions: non si possono eseguire le operazioni richieste
	 * perche' i parametri non sono validi; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo {@link myAdapter.ListAdapter#remove(int)} lancia
	 * una {@link IndexOutOfBoundsException} perche' il parametro ricevuto non puo'
	 * essere utilizzato
	 */
	@Test
	public void testRemoveWithIndexExceptions() {
		System.out.println("TestRemoveWithIndexExceptions");

		IndexOutOfBoundsException thrown = assertThrows("Remove with index exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.remove(0));

		list1.add(0);
		list1.add("pippo");
		list1.add(null);

		thrown = assertThrows("Remove with index exceptions not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.remove(-1));

		thrown = assertThrows("Remove with index exceptions not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.remove(list1.size()));

		list1.remove("pippo");

		thrown = assertThrows("Remove with index not working correctly\n", IndexOutOfBoundsException.class,
				() -> list1.remove(2));
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#indexOf(Object)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#indexOf(Object)}, che restituisce l'indice della
	 * prima occorrenza nella lista dell'oggetto passato come parametro,
	 * considerando l'ordine restituito dal metodo
	 * {@link myAdapter.ListAdapter#toArray()}.
	 * Se la lista non contiene tale oggetto, il metodo restituisce il valore -1
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si inseriscono degli elementi in list1 vuota con il metodo
	 * {@link myAdapter.ListAdapter#add(Object)}.
	 * <br>
	 * Si verifica che il metodo {@link myAdapter.ListAdapter#indexOf(Object)}
	 * restituisca il valore corretto nei casi in cui gli vengano passati come
	 * parametro oggetti presenti in singola copia nella lista, oggetti presenti in
	 * molteplice copia e oggetti non presenti nella lista
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#indexOf(Object)} invocato
	 * su liste vuote e non vuote.
	 * I valori nella lista sono di tipo {@link String},
	 * {@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#remove(Object)} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#indexOf(Object)} deve
	 * restituire l'indice della prima occorrenza nella lista dell'oggetto passato
	 * come parametro, -1 se l'oggetto non appartiene alla lista
	 * <br>
	 * <br>
	 * Expected Results: ogni invocazione di indexOf(obj) restituisce l'indice della
	 * prima occorrenza di obj nella lista o -1 se obj non e'nella lista
	 */
	@Test
	public void testIndexOf() {
		System.out.println("TestIndexOf");

		assertEquals("IndexOf not working correctly\n", -1, list1.indexOf(0));
		assertEquals("IndexOf not working correctly\n", -1, list1.indexOf(null));

		list1.add(null);
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list1.add(null);
		list1.add(1);
		list1.add("pippo");

		assertEquals("IndexOf not working correctly\n", 0, list1.indexOf(null));
		assertEquals("IndexOf not working correctly\n", 2, list1.indexOf(1));
		assertEquals("IndexOf not working correctly\n", 6, list1.indexOf("pippo"));
		assertEquals("IndexOf not working correctly\n", -1, list1.indexOf(1.5));
		assertEquals("IndexOf not working correctly\n", -1, list1.indexOf("qui"));

		list1.remove(null);
		list1.remove(null);

		assertEquals("IndexOf not working correctly\n", -1, list1.indexOf(null));
	}

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#lastIndexOf(Object)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#lastIndexOf(Object)}, che restituisce l'indice
	 * dell'ultima occorrenza nella lista dell'oggetto passato come parametro,
	 * considerando l'ordine restituito dal metodo
	 * {@link myAdapter.ListAdapter#toArray()}.
	 * Se la lista non contiene tale oggetto, il metodo restituisce il valore -1
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si inseriscono degli elementi in list1 vuota con il metodo
	 * {@link myAdapter.ListAdapter#add(Object)}.
	 * <br>
	 * Si verifica che il metodo {@link myAdapter.ListAdapter#lastIndexOf(Object)}
	 * restituisca il valore corretto nei casi in cui gli vengano passati come
	 * parametro oggetti presenti in singola copia nella lista, oggetti presenti in
	 * molteplice copia e oggetti non presenti nella lista
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#lastIndexOf(Object)} invocato
	 * su liste vuote e non vuote.
	 * I valori nella lista sono di tipo {@link String},
	 * {@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: i metodi {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#remove(Object)} devono essere correttamente
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#lastIndexOf(Object)}
	 * deve
	 * restituire l'indice dell'ultima occorrenza nella lista dell'oggetto passato
	 * come parametro, -1 se l'oggetto non appartiene alla lista
	 * <br>
	 * <br>
	 * Expected Results: ogni invocazione di indexOf(obj) restituisce l'indice
	 * dell'ultima occorrenza di obj nella lista o -1 se obj non e'nella lista
	 */
	@Test
	public void testLastIndexOf() {
		System.out.println("TestLastIndexOf");

		assertEquals("LastIndexOf not working correctly\n", -1, list1.lastIndexOf(0));
		assertEquals("LastIndexOf not working correctly\n", -1, list1.lastIndexOf(null));

		list1.add(null);
		list1.add(0);
		list1.add(1);
		list1.add(2);
		list1.add(null);
		list1.add(1);
		list1.add("pippo");

		assertEquals("LastIndexOf not working correctly\n", 4, list1.lastIndexOf(null));
		assertEquals("LastIndexOf not working correctly\n", 5, list1.lastIndexOf(1));
		assertEquals("LastIndexOf not working correctly\n", 6, list1.lastIndexOf("pippo"));
		assertEquals("LastIndexOf not working correctly\n", -1, list1.lastIndexOf(1.5));
		assertEquals("LastIndexOf not working correctly\n", -1, list1.lastIndexOf("qui"));

		list1.remove(null);
		list1.remove(null);

		assertEquals("LastIndexOf not working correctly\n", -1, list1.lastIndexOf(null));
	}

	// TEST METODO SUBLIST

	/**
	 * Test del metodo {@link myAdapter.ListAdapter#subList(int, int)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#subList(int, int)}, che restituisce una
	 * sottolista della lista su cui e'invocato, contenente gli elementi compresi
	 * tra i due indici passati come parametro (il primo incluso mentre il secondo
	 * escluso).
	 * Nella prima parte del test viene verificata la presenza dei corretti valori
	 * nella sottolista, mentre nella seconda e' testato il corretto funzionamento
	 * della funzione di backing e delle sottoliste di sottoliste
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si invoca {@link myAdapter.ListAdapter#subList(int,int)} su una lista vuota e
	 * si verifica che la sottolista ottenuta sia vuota.
	 * <br>
	 * Si aggiungono elementi a list1 e si invoca
	 * {@link myAdapter.ListAdapter#subList(int,int)} su di essa passando diversi
	 * indici in modo da verificarne il corretto funzionamento anche nei casi
	 * particolari.
	 * Si verifica ogni volta che la sottolista list2 abbia la dimensione aspettata
	 * e contenga gli elementi aspettati attraverso i metodi
	 * {@link myAdapter.ListAdapter#toArray()} e
	 * {@link myAdapter.ListAdapter#size()}
	 * <br>
	 * Si crea una sottolista list3 della sottolista list2 e si verifica che essa
	 * contenga gli elementi aspettati.
	 * <br>
	 * Si aggiunge un elemento a list3 e si verifica che anche entrambe le liste
	 * padre list1 e list2 hanno subito una modifica e contengono tale elemento
	 * <br>
	 * Si effettua una modifica non struttutrale a list1 e si verifica che anche le
	 * sottoliste vengano modificate
	 * <br>
	 * Attraverso l'invocazione di {@link myAdapter.ListAdapter#clear()} su list3 si
	 * elimina una porzione di list2 e list1
	 * <br>
	 * Attraverso l'invocazione di {@link myAdapter.ListAdapter#clear()} su list2 si
	 * elimina una porzione di list1
	 * <br>
	 * Vengono testati vari metodi di {@link myAdapter.ListAdapter} per verificarne
	 * il corretto funzionamento quando invocati su sottoliste
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#subList(int,int)} invocato
	 * su liste vuote e non vuote e varie coppie di indici.
	 * E'testata anche l'invocazione del metodo su un'altra sottolista e il corretto
	 * funzionamento dei metodi di ListAdapter invocati su una sottolista.
	 * I valori nelle liste sono di tipo {@link String}, {@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: tutti i metodi di {@link myAdapter.ListAdapter} devono essere
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: il metodo {@link myAdapter.ListAdapter#subList(int,int)}
	 * deve restituire un oggetto di tipo {@link myAdapter.ListAdapter} che contenga
	 * una porzione della lista padre e che subisca le variazioni non strutturali
	 * fatte ad essa (e vice versa). Attraverso la sottolista possono invece essere
	 * apportate anche modifiche strutturali alla lista padre
	 * <br>
	 * <br>
	 * Expected Results: ogni metodo di {@link myAdapter.ListAdapter} invocato sulla
	 * sottolista ha lo stesso comportamento che avrebbe se invocato su una normale
	 * lista
	 */
	@Test
	public void testSubList() {
		System.out.println("TestSublist");

		list2 = list1.subList(0, 0);
		assertEquals("Sublist not working correctly\n", 0, list2.size());

		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(null);
		list1.add("pippo");
		list1.add("qui");
		list1.add("paperino");
		list1.add(null);
		list1.add(3);
		list1.add(null);

		int size = list1.size();

		list2 = list1.subList(1, 1);
		assertEquals("Sublist not working correctly\n", 0, list2.size());

		list2 = list1.subList(2, 5);
		assertEquals("Sublist not working correctly\n", 3, list2.size());
		assertArrayEquals("Sublist not working correctly\n", new Object[] { 3, null, "pippo" }, list2.toArray());

		list2 = list1.subList(0, list1.size());
		assertEquals("Sublist not working correctly\n", size, list2.size());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { 1, 2, 3, null, "pippo", "qui", "paperino", null, 3, null }, list2.toArray());

		HList list3 = list2.subList(1, 5);

		listIter = list3.listIterator();
		iterate(listIter);

		assertEquals("Sublist not working correctly\n", 4, list3.size());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { 2, 3, null, "pippo" }, list3.toArray());

		list3.add(2, "qui");
		assertEquals("Sublist not working correctly\n", 5, list3.size());
		assertEquals("Sublist not working correctly\n", size + 1, list2.size());
		assertEquals("Sublist not working correctly\n", size + 1, list1.size());

		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { 2, 3, "qui", null, "pippo" }, list3.toArray());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { 1, 2, 3, "qui", null, "pippo", "qui", "paperino", null, 3, null }, list2.toArray());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { 1, 2, 3, "qui", null, "pippo", "qui", "paperino", null, 3, null }, list1.toArray());

		list1.set(3, null);
		list1.set(9, 0);
		assertFalse("Contains not working correctly\n", list3.contains(0));

		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { 2, 3, null, null, "pippo" }, list3.toArray());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { 1, 2, 3, null, null, "pippo", "qui", "paperino", null, 0, null }, list2.toArray());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { 1, 2, 3, null, null, "pippo", "qui", "paperino", null, 0, null }, list1.toArray());

		assertEquals("Sublist not working correctly\n", list1, list2);
		assertNotEquals("Sublist not working correctly\n", list1, list3);
		assertEquals("IndexOf not working correctly\n", list3.indexOf(null), 2);
		assertEquals("LastIndexOf not working correctly\n", list3.lastIndexOf(3), 1);

		list3.clear();
		assertArrayEquals("Sublist not working correctly\n",
				new Object[0], list3.toArray());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { 1, "qui", "paperino", null, 0, null }, list2.toArray());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { 1, "qui", "paperino", null, 0, null }, list1.toArray());

		list2.clear();
		assertArrayEquals("Sublist not working correctly\n",
				new Object[0], list3.toArray());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[0], list2.toArray());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[0], list2.toArray());
		assertTrue("isEmpty not working correctly\n", list2.isEmpty());
		assertTrue("isEmpty not working correctly\n", list1.isEmpty());

		list2.add("ciccio");
		list2.add(0, "paperino");
		coll2 = new ListAdapter();
		coll2.add(null);
		coll2.add(3);
		list2.addAll(1, coll2);

		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { "paperino", null, 3, "ciccio" }, list2.toArray());
		assertArrayEquals("Sublist not working correctly\n",
				new Object[] { "paperino", null, 3, "ciccio" }, list1.toArray());
		assertTrue("ContainsAll not working correctly\n", list1.containsAll(coll2));
		list2.retainAll(coll2);
		assertTrue("Equals not working correctly\n", list1.equals(coll2));
		list2.removeAll(coll2);
		assertTrue("isEmpty not working correctly\n", list1.isEmpty());
		assertTrue("isEmpty not working correctly\n", list2.isEmpty());
	}

	/**
	 * Test delle eccezioni lanciate dal metodo
	 * {@link myAdapter.ListAdapter#subList(int,int)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di eccezioni nel caso in
	 * cui al metodo siano passati come parametro indici non validi
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Il meotdo {@link myAdapter.ListAdapter#subList(int from,int to)} e'
	 * invocato su liste vuote e non vuote usando indice from minore di zero, indice
	 * to maggiore di size() e indice from maggiore di to
	 * <br>
	 * <br>
	 * Description: su list1 e sulla sua sottolista list2 e' invocato subList
	 * passando parametri non validi in modo da verificare il lancio di una
	 * {@link IndexOutOfBoundsException}
	 * <br>
	 * <br>
	 * PreConditions: Il metodo {@link myAdapter.ListAdapter#add(Object)} deve
	 * essere funzionante
	 * <br>
	 * <br>
	 * PostConditions: non si possono eseguire le operazioni richieste
	 * perche' i parametri non sono validi; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo {@link myAdapter.ListAdapter#subList(int,int)}
	 * lancia
	 * una {@link IndexOutOfBoundsException} ogni volta che i parametri ricevuti non
	 * possono essere utilizzati
	 */
	@Test
	public void testSubListExceptions() {
		System.out.println("TestSubListExceptions");

		IndexOutOfBoundsException thrown = assertThrows("SubList exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.subList(-1, 0));
		thrown = assertThrows("SubList exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.subList(0, 1));

		list1.add(null);
		list1.add(0);
		list1.add("pippo");
		list1.add(1);
		list1.add(2);

		thrown = assertThrows("SubList exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.subList(-1, 1));
		thrown = assertThrows("SubList exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.subList(1, 6));
		thrown = assertThrows("SubList exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.subList(3, 1));

		list2 = list1.subList(1, 4);

		thrown = assertThrows("SubList exceptions not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list2.subList(-1, 4));
	}

	/**
	 * Test del backing del metodo {@link myAdapter.ListAdapter#subList(int, int)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica la funzione di backing del metodo
	 * {@link myAdapter.ListAdapter#subList(int, int)}, che permette di effettuare
	 * qualsiasi tipo di modifiche alla lista padre usando una sua sottolista (il
	 * vice versa e' valido solo per modifiche non strutturali)
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si aggiungono a list1 i valori di argv
	 * <br>
	 * Si crea una sottolista list2 con i primi 3 elementi di list1
	 * <br>
	 * Si aggiunge un elemento a list2 e si verifica l'incremento della dimensione
	 * di entrambe le liste
	 * <br>
	 * Si rimuove un elemento da list2 e si verifica il decremento della dimensione
	 * di entrambe le liste
	 * <br>
	 * Si svuota list2 e si verificano le corrette dimensioni di entrambe le liste
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#subList(int,int)} invocato su una sottolista per
	 * aggiungere e rimuovere elementi.
	 * I valori nelle liste sono di tipo {@link String}
	 * <br>
	 * <br>
	 * PreConditions: i {@link myAdapter.ListAdapter#add(Object)},
	 * {@link myAdapter.ListAdapter#add(int,Object)},
	 * {@link myAdapter.ListAdapter#remove(Object)},
	 * {@link myAdapter.ListAdapter#size()} e {@link myAdapter.ListAdapter#clear()},
	 * devono essere funzionanti
	 * <br>
	 * <br>
	 * PostConditions: la sottolista in ogni momento e'una frazione di lista padre
	 * pertanto le operazioni eseguite su list2 sono visibili esaminando list1
	 * <br>
	 * <br>
	 * Expected Results: le modifiche fatte alla sottolista list2 modificano allo
	 * stesso modo anche la lista padre list1
	 */
	@Test
	public void testBacking() {
		System.out.println("TestBacking");
		for (int i = 0; i < argv.length; i++) {
			list1.add(argv[i]);
		}
		System.out.println("List.toString() ? " + list1);

		int dl0, dl1, dli, dsl0, dsl1, dsli;

		iterate(list1.iterator());
		System.out.println(list1 + " " + list1.size());
		dl0 = list1.size();

		list2 = list1.subList(0, argv.length / 2);
		dsl0 = list2.size();
		System.out.println(list2 + " " + list2.size());

		list2.add("pipperissimo");
		System.out.println(list1 + " " + list1.size());
		System.out.println(list2 + " " + list2.size());
		dli = list1.size();
		dsli = list2.size();

		assertEquals("\n*** sublist add is NOT backed correctly ***\n", dli, dl0 + 1);
		assertEquals("\n*** sublist add is NOT backed correctly ***\n", dsli, dsl0 + 1);

		list2.remove("pipperissimo");
		assertEquals("\n*** list remove is NOT backed correctly ***\n", list1.size(), dl0);
		assertEquals("\n*** list remove is NOT backed correctly ***\n", list2.size(), dsl0);

		iterate(list2.iterator());
		System.out.println(list2 + " " + list2.size());

		list2.clear();
		dl1 = list1.size();
		dsl1 = list2.size();
		System.out.println(list1 + " " + list1.size());
		iterate(list1.iterator());
		System.out.println(list2 + " " + list2.size());
		iterate(list2.iterator());

		System.out.println(dl0 + " " + dl1 + " " + dsl0 + " " + dsl1);
		assertEquals("\n*** sublist is NOT backed correctly ***\n", dsl0, (dl0 / 2));
		assertEquals("\n*** sublist is NOT backed correctly ***\n", dsl1, 0);
		assertEquals("\n*** sublist is NOT backed correctly ***\n", dl1, (dl0 - dsl0));

	}

	/**
	 * Test della ricorsione del metodo
	 * {@link myAdapter.ListAdapter#subList(int, int)}
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica la ricorsione del metodo
	 * {@link myAdapter.ListAdapter#subList(int, int)}, cioe' l'invocazione ripetuta
	 * di tale metodo sulle sottoliste appena restituite da esso
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Si aggiungono per tre volte a list1 tutti i valori di argv e si verifica ogni
	 * volta il corretto incremento della dimensione di list1
	 * <br>
	 * Si invoca {@link myAdapter.ListAdapter#subList(int, int)} ripetutamente per
	 * creare delle sottoliste sempre piu'piccole finche' non si ottiene una lista
	 * vuota. A ogni passaggio si controlla che il contenuto e la dimensione siano
	 * quelli aspettati
	 * <br>
	 * <br>
	 * Description: Viene verificato il corretto funzionamento di
	 * {@link myAdapter.ListAdapter#subList(int,int)} invocato ricorsivamente su una
	 * lista.
	 * I valori nelle liste sono di tipo {@link String}
	 * <br>
	 * <br>
	 * PreConditions: i {@link myAdapter.ListAdapter#add(Object)} e
	 * {@link myAdapter.ListAdapter#size()} devono essere funzionanti
	 * <br>
	 * <br>
	 * PostConditions: ogni sottolista contiene gli stessi elementi della sua lista
	 * padre, eccetto il primo e l'ultimo
	 * <br>
	 * <br>
	 * Expected Results: il metodo puo'essere usato senza alcuna differenza se
	 * l'oggetto su cui e' invocato e'una lista padre o una sottolista
	 */
	@Test
	public void testRecursiveSublist() {
		System.out.println("TestRecursive SubListing");
		System.out.println(list1.size());

		assertEquals("List Starts not empty", list1.size(), 0);
		int prev = list1.size();
		for (int i = 0; i < argv.length; i++) {
			list1.add(argv[i]);
		}
		assertEquals("List add not working correctly", list1.size(), (prev + argv.length));
		System.out.println(list1.size());
		prev = list1.size();
		for (int i = 0; i < argv.length; i++) {
			list1.add(argv[i]);
		}
		assertEquals("List add not working correctly", list1.size(), (prev + argv.length));
		System.out.println(list1.size());
		prev = list1.size();
		for (int i = 0; i < argv.length; i++) {
			list1.add(argv[i]);
		}
		assertEquals("List add not working correctly", list1.size(), (prev + argv.length));
		System.out.println(list1.size());
		iterate(list1.iterator());

		int after = 0;
		int count = 0;
		while (list1.size() >= 2) {
			count++;
			prev = list1.size();
			list1 = list1.subList(1, prev - 1);
			after = list1.size();
			System.out.println(after);
			assertEquals("Iterative Sublisting not working at " + count + " iteration", after, (prev - 2));
			iterate(list1.iterator());
		}
	}

	/**
	 * Test dei metodi {@link myAdapter.ListAdapter#iterator()},
	 * {@link myAdapter.ListAdapter#listIterator()},{@link myAdapter.ListAdapter#listIterator(int)}
	 * e di tutti i metodi della classe privata ListAdapterIterator
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il funzionamento dei metodi
	 * {@link myAdapter.ListAdapter#iterator()},
	 * {@link myAdapter.ListAdapter#listIterator()},{@link myAdapter.ListAdapter#listIterator(int)}
	 * e di tutti i metodi della classe privata ListAdapterIterator.
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * viene testato il metodo {@link myAdapter.ListAdapter#iterator()}, sulla
	 * collezione coll1 vuota e si verifica il corretto funzionamento di hasNext().
	 * Si aggiungono a coll1 vari elementi e si verifica il corretto funzionamento
	 * di hasNext(), next() e remove()
	 * <br>
	 * viene testato il metodo {@link myAdapter.ListAdapter#listIterator()} sulla
	 * lista list2 vuota e su list1 non vuota e si verifica il corretto
	 * funzionamento di hasNext(), hasPrevious(), next(), previous(),
	 * add(Object), remove(), set(), previousIndex(), nextIndex()
	 * <br>
	 * Viene testato il corretto funzionamento del metodo
	 * {@link myAdapter.ListAdapter#listIterator(int)}
	 * <br>
	 * <br>
	 * Description: vengono testati i metodi degli iteratori in diverse situazioni,
	 * usando liste contenti valori di tipo {@link String}, {@link Double},
	 * {@link Integer} e null
	 * <br>
	 * <br>
	 * PreConditions: tutti i metodi di ListAdapterIterator devono essere
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: L'iteratore deve potersi muovere nella lista e poter
	 * ispezionare, aggiungere, rimuovere e modificare i suoi elementi
	 * <br>
	 * <br>
	 * Expected Results: dopo ogni operazione fatta dall'iteratore il contenuto
	 * della lista e' uguale al contenuto di un array di Object creato manualmente e
	 * contenente i valori aspettati
	 */
	@Test
	public void testIteratorMethods() {
		System.out.println("Test Iterator Methods");

		// HIterator test
		HIterator collIter = coll1.iterator();
		int count = 0;

		while (collIter.hasNext()) {
			list1.add(collIter.next());
			count++;
		}
		assertEquals("HasNext not working correctly\n", 0, count);
		assertEquals("HasNext not working correctly\n", 0, list1.size());

		coll1.add(1);
		coll1.add(null);
		coll1.add(2);
		coll1.add("ciccio");
		coll1.add(3);
		coll1.add("qui");

		count = 0;
		while (collIter.hasNext()) {
			list1.add(collIter.next());
			count++;
		}
		assertEquals("HasNext or Next not working correctly\n", 6, count);
		assertEquals("HasNext or Next not working correctly\n", 6, list1.size());

		collIter = coll1.iterator();
		count = 0;
		while (collIter.hasNext()) {
			collIter.next();
			collIter.remove();
			count++;
		}
		assertEquals("HasNext or Remove not working correctly\n", 0, coll1.size());
		assertEquals("HasNext or Remove not working correctly\n", 6, count);

		// HListIterator test
		list2 = new ListAdapter();
		listIter = list2.listIterator();
		assertFalse("HasNext not working correctly\n", listIter.hasNext());
		assertFalse("HasPrevious not working correctly\n", listIter.hasPrevious());

		listIter.add(null);
		assertEquals("Previous or Add not working correctly\n", null, listIter.previous());
		listIter.remove();
		assertEquals(0, list2.size());

		listIter = list1.listIterator();
		count = 0;
		while (listIter.hasNext()) {
			list2.add(listIter.next());
			count++;
		}
		assertArrayEquals("Iterator not working correctly\n", new Object[] { 1, null, 2, "ciccio", 3, "qui" },
				list2.toArray());
		assertEquals("PreviousIndex not working correctly\n", 5, listIter.previousIndex());
		assertEquals("NextIndex not working correctly\n", 6, listIter.nextIndex());

		count = 0;
		list2.clear();
		while (listIter.hasPrevious()) {
			list2.add(listIter.previous());
			count++;
		}
		assertArrayEquals("Iterator not working correctly\n", new Object[] { "qui", 3, "ciccio", 2, null, 1 },
				list2.toArray());
		assertEquals("PreviousIndex not working correctly\n", -1, listIter.previousIndex());
		assertEquals("NextIndex not working correctly\n", 0, listIter.nextIndex());

		listIter.next();
		listIter.add(4.8);
		assertArrayEquals("Iterator not working correctly\n", new Object[] { 1, 4.8, null, 2, "ciccio", 3, "qui" },
				list1.toArray());

		listIter.previous();
		listIter.remove();
		assertArrayEquals("Iterator not working correctly\n", new Object[] { 1, null, 2, "ciccio", 3, "qui" },
				list1.toArray());

		listIter.next();
		listIter.remove();
		assertArrayEquals("Iterator not working correctly\n", new Object[] { 1, 2, "ciccio", 3, "qui" },
				list1.toArray());

		listIter.next();
		listIter.add(10);
		assertArrayEquals("Iterator not working correctly\n", new Object[] { 1, 2, 10, "ciccio", 3, "qui" },
				list1.toArray());

		listIter.next();
		listIter.previous();
		listIter.set(0.5);
		assertArrayEquals("Iterator not working correctly\n", new Object[] { 1, 2, 10, 0.5, 3, "qui" },
				list1.toArray());

		// Iterator with Index test
		listIter = list1.listIterator(3);
		assertEquals("ListIterator with Index not working correctly\n", 3, listIter.nextIndex());
		assertEquals("ListIterator with Index not working correctly\n", 2, listIter.previousIndex());
		assertEquals("ListIterator with Index not working correctly\n", 0.5, listIter.next());
	}

	/**
	 * Test delle eccezioni lanciate dai metodi delgi iteratori
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il corretto lancio di eccezioni nel caso in
	 * cui ai metodi siano passati come parametro indici non validi o nel caso
	 * vengano eseguite operazioni non consentite
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Il meotdo {@link myAdapter.ListAdapter#listIterator(int)} e'
	 * invocato su liste vuote e non vuote usando indici non validi (minori di 0 o
	 * maggiori di size())
	 * <br>
	 * le operazioni next(), previous(), add(), remove() e set() sono usate in
	 * situazioni in cui tali operazioni non sono consentite
	 * <br>
	 * <br>
	 * Description: Il meotdo {@link myAdapter.ListAdapter#listIterator(int)} e'
	 * invocato con parametri non validi per verificare il lancio di una
	 * {@link IndexOutOfBoundsException}. Poi si verifica il lancio di
	 * {@link IllegalStateException} e {@link NoSuchElementException} da parte dei
	 * metodi degli iteratori
	 * <br>
	 * <br>
	 * PreConditions: tutti i metodi di ListAdapterIterator devono essere
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: non si possono eseguire le operazioni richieste
	 * perche' i parametri o lo stato non sono validi; viene quindi lanciata
	 * un'eccezione
	 * <br>
	 * <br>
	 * Expected Results: il metodo {@link myAdapter.ListAdapter#listIterator(int)}
	 * lancia una {@link IndexOutOfBoundsException} ogni volta che i parametri
	 * ricevuti non possono essere utilizzati.
	 * I metodi di ListAdapterIterator lanciano le eccezioni aspettate ogni volta
	 * che sono invocati in situazioni non valide
	 */

	@Test
	public void testIteratorExceptions() {
		System.out.println("TestIteratorExceptions");

		IndexOutOfBoundsException thrown = assertThrows("ListIterator exception not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.listIterator(-1));

		thrown = assertThrows("ListIterator exception not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.listIterator(1));

		list1.add(3);
		list1.add(null);

		thrown = assertThrows("ListIterator exception not working correctly\n",
				IndexOutOfBoundsException.class,
				() -> list1.listIterator(3));

		listIter = list1.listIterator();
		NoSuchElementException thrown2 = assertThrows("ListIterator exception not working correctly\n",
				NoSuchElementException.class,
				() -> listIter.previous());

		listIter = list1.listIterator(2);
		thrown2 = assertThrows("ListIterator exception not working correctly\n",
				NoSuchElementException.class,
				() -> listIter.next());

		IllegalStateException thrown3 = assertThrows("ListIterator exception not working correctly\n",
				IllegalStateException.class,
				() -> listIter.remove());

		thrown3 = assertThrows("ListIterator exception not working correctly\n",
				IllegalStateException.class,
				() -> listIter.set("paperino"));

		listIter = list1.listIterator(1);
		list1.add(null);
		list1.add(2);

		listIter.previous();
		listIter.remove();

		thrown3 = assertThrows("ListIterator exception not working correctly\n",
				IllegalStateException.class,
				() -> listIter.remove());

		thrown3 = assertThrows("ListIterator exception not working correctly\n",
				IllegalStateException.class,
				() -> listIter.set("paperino"));

		listIter.next();
		listIter.add("qui");

		thrown3 = assertThrows("ListIterator exception not working correctly\n",
				IllegalStateException.class,
				() -> listIter.remove());

		thrown3 = assertThrows("ListIterator exception not working correctly\n",
				IllegalStateException.class,
				() -> listIter.set("paperino"));

	}

	/**
	 * Test della scansione di una lista vuota con gli iteratori
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il funzionamento della scansione di una lista
	 * vuota usando un iteratore che parte dalla posizione finale e si muove verso
	 * l'inizio della lista
	 * <br>
	 * <br>
	 * Test Case Design:
	 * Viene creato un iteratore che opera su una lista vuota partendo dall'ultimo
	 * indice valido e viene avviata una scansione di tale lista con il metodo
	 * previous() e hasPrevious(), verificando che l'iteratore non compia
	 * spostamenti e che al termine la lista sia ancora vuota
	 * <br>
	 * <br>
	 * Description: viene eseguita una scansione su una lista vuota e viene
	 * verificato il corretto funzionamento dell'iteratore
	 * <br>
	 * <br>
	 * PreConditions: i metodi di ListAdapterIterator devono essere
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: L'iteratore non si deve spostare nella lista e non deve
	 * modificarla
	 * <br>
	 * <br>
	 * Expected Results: il metodo hasPrevious() restituisce false e il ciclo while
	 * non viene eseguito.
	 * La lista non viene modificata
	 */
	@Test
	public void testIterator1() {
		System.out.println("TestListIterator #1");
		iterate(list1.iterator());
		listIter = list1.listIterator(list1.size());
		while (listIter.hasPrevious()) {
			System.out.print(listIter.previous() + " ");
			iterate(list1.iterator());
			listIter.remove();
		}
		iterate(list1.iterator());

		assertEquals("\n*** listiterator from end not working ***\n", list1.size(), 0);
	}

	/**
	 * Test della scansione di una lista non vuota con gli iteratori
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il funzionamento della scansione di una lista
	 * non vuota usando un iteratore che parte dalla posizione iniziale e si muove
	 * verso la fine della lista, rimuovendo ogni elemento ispezionato
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Vengono aggiunti a list1 tutti gli elementi di argv
	 * <br>
	 * Viene creato un iteratore che opera su list1 partendo dall'inizio di essa e
	 * viene avviata una scansione di tale lista con il metodo
	 * next() e hasNext(), rimuovendo ogni elemento ispezionato
	 * <br>
	 * Viene verificata la corretta dimensione della lista prima e dopo il cilco
	 * dell'iteratore
	 * <br>
	 * <br>
	 * Description: viene verificato il corretto funzionamento di un ciclo di
	 * ispezione e rimozione di un iteratore che opera su una lista non vuota
	 * <br>
	 * <br>
	 * PreConditions: i metodi di ListAdapterIterator devono essere
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: L'iteratore deve arrivare alla fine della lista e rimuovere
	 * tutti gli elementi
	 * <br>
	 * <br>
	 * Expected Results: L'iteratore deve permettere di ispezionare ogni elemento
	 * della lista e invocare il metodo remove() in ogni posizione raggiunta.
	 * La lista viene svuotata
	 */
	@Test
	public void testIterator2() {
		System.out.println("TestListIterator #2");
		int dl0, dl1, dl2;
		dl0 = list1.size();
		for (int i = 0; i < argv.length; i++) {
			list1.add(argv[i]);
		}
		dl1 = list1.size();
		iterate(list1.iterator());
		listIter = list1.listIterator();
		while (listIter.hasNext()) {
			System.out.print(listIter.next() + " ");
			iterate(list1.iterator());
			listIter.remove();
		}
		dl2 = list1.size();
		iterate(list1.iterator());

		assertEquals("\n*** insertion and forward removal not working ***\n", dl1, (dl0 + argv.length));
		assertEquals("\n*** insertion and forward removal not working ***\n", dl2, 0);
	}

	/**
	 * Test della scansione di una lista non vuota con gli iteratori
	 * <p>
	 * <br>
	 * <br>
	 * Summary: Questo test verifica il funzionamento della scansione di una lista
	 * non vuota usando un iteratore che parte dalla posizione finale e si muove
	 * verso l'inizio della lista, rimuovendo ogni elemento ispezionato
	 * <br>
	 * <br>
	 * Test Case Design:
	 * <br>
	 * Vengono aggiunti a list1 tutti gli elementi di argv
	 * <br>
	 * Viene creato un iteratore che opera su list1 partendo dall'inizio di essa e
	 * viene fatto avanzare fino alla fine di essa con i metodo
	 * next() e hasNext()
	 * <br>
	 * Viene fatto retrocedere l'iteratore fino all'inizio della lista con i metodi
	 * previous() e hasPrevious(), rimuovendo ogni elemento ispezionato
	 * <br>
	 * Viene verificata la corretta dimensione della lista prima e dopo il cilco
	 * dell'iteratore
	 * <br>
	 * <br>
	 * Description: viene verificato il corretto funzionamento di un ciclo di
	 * ispezione e rimozione di un iteratore che opera su una lista non vuota
	 * partendo dalla fine e muovendosi all'indietro
	 * <br>
	 * <br>
	 * PreConditions: i metodi di ListAdapterIterator devono essere
	 * funzionanti
	 * <br>
	 * <br>
	 * PostConditions: L'iteratore deve arrivare all'inizio della lista e rimuovere
	 * tutti gli elementi
	 * <br>
	 * <br>
	 * Expected Results: L'iteratore deve permettere di ispezionare ogni elemento
	 * della lista e invocare il metodo remove() in ogni posizione raggiunta.
	 * La lista viene svuotata
	 */
	@Test
	public void testIterator3() {
		System.out.println("TestListIterator #3");
		int dl0, dl1, dl2;

		dl0 = list1.size();
		for (int i = 0; i < argv.length; i++) {
			list1.add(argv[i]);
		}
		dl1 = list1.size();
		iterate(list1.iterator());
		listIter = list1.listIterator();
		while (listIter.hasNext())
			listIter.next();
		while (listIter.hasPrevious()) {
			System.out.print(listIter.previous() + " ");
			iterate(list1.iterator());
			listIter.remove();
		}
		dl2 = list1.size();
		iterate(list1.iterator());

		assertEquals("\n*** insertion and forward to end and backward removal not working ***\n", dl1,
				(dl0 + argv.length));
		assertEquals("\n*** insertion and forward to end and backward removal not working ***\n", dl2, 0);
	}

	/**
	 * Stampa a video tutti i valori restituiti da next() invocato sull'iteratore
	 * passato come parametro
	 * 
	 * @param iter iteratore che opera sulla lista che si vuole ispezionare
	 */
	public static void iterate(HIterator iter) {
		System.out.print("{");
		while (iter.hasNext()) {
			System.out.print(iter.next() + "; ");
		}
		System.out.println("}");
	}
}