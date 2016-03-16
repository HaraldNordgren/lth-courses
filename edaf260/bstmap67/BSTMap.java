/*
 * BSTMap.java
 *
 * Last modified: Thu 17 Nov 2007
 */

/**
 *
 */
public class BSTMap<K,V> extends java.util.AbstractMap<K,V>
    implements java.util.SortedMap<K,V>, Cloneable //, java.io.Serializable
{
    /**
     * Temporary class used for indicating that a method or class is not
     * yet implemented.
     */
    static class NotImplemented extends RuntimeException {
        static final long serialVersionUID = 1L;
    }

    /**
     * The Comparator used to maintain order in this BSTMap, or null if
     * this BSTMap uses its elements natural ordering.
     */
    //private java.util.Comparator comparator;

    //private transient Entry root;

    /**
     * The number of entries in the tree
     */
    //private transient int size;

    /**
     * Constructs a new, empty map, sorted according to the keys' natural
     * order.  All keys inserted into the map must implement the
     * <tt>Comparable</tt> interface.  Furthermore, all such keys must be
     * <i>mutually comparable</i>: <tt>k1.compareTo(k2)</tt> must not throw
     * a ClassCastException for any elements <tt>k1</tt> and <tt>k2</tt> in
     * the map.  If the user attempts to put a key into the map that
     * violates this constraint (for example, the user attempts to put a
     * string key into a map whose keys are integers), the <tt>put(Object
     * key, Object value)</tt> call will throw a
     * <tt>ClassCastException</tt>.
     *
     * @see Comparable
     */
    public BSTMap() {
        throw new NotImplemented();
    }

    /**
     * Constructs a new, empty map, sorted according to the given
     * comparator. All keys inserted into the map must be <i>mutually
     * comparable</i> by the given comparator: <tt>comparator.compare(k1,
     * k2)</tt> must not throw a <tt>ClassCastException</tt> for any keys
     * <tt>k1</tt> and <tt>k2</tt> in the map.  If the user attempts to put
     * a key into the map that violates this constraint, the <tt>put(Object
     * key, Object value)</tt> call will throw a
     * <tt>ClassCastException</tt>.
     *
     * @param c the comparator that will be used to sort this map.  A
     * <tt>null</tt> value indicates that the keys' <i>natural ordering</i>
     * should be used.
     */
    public BSTMap(java.util.Comparator c) {
        throw new NotImplemented();
    }

    /**
     * Constructs a new map containing the same mappings as the given map,
     * sorted according to the keys' <i>natural order</i>.  All keys
     * inserted into the new map must implement the <tt>Comparable</tt>
     * interface. Furthermore, all such keys must be <i>mutually
     * comparable</i>: <tt>k1.compareTo(k2)</tt> must not throw a
     * <tt>ClassCastException</tt> for any elements <tt>k1</tt> and
     * <tt>k2</tt> in the map.  This method runs in n*log(n) time.
     *
     * @param  m the map whose mappings are to be placed in this map.
     * @throws ClassCastException the keys in t are not Comparable, or
     *         are not mutually comparable.
     * @throws NullPointerException if the specified map is null.
     */
    public BSTMap(java.util.Map m) {
        throw new NotImplemented();
    }

    /**
     * Constructs a new map containing the same mappings as the given
     * <tt>SortedMap</tt>, sorted according to the same ordering.  This
     * method runs in linear time.
     *
     * @param  m the sorted map whose mappings are to be placed in this
     * map, and whose comparator is to be used to sort this map.
     * @throws NullPointerException if the specified sorted map is null.
     */
    public BSTMap(java.util.SortedMap m) {
        throw new NotImplemented();
    }

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map.
     */
    public int size() {
        return 43;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the
     * specified key.
     *
     * @param key key whose presence in this map is to be tested.
     *
     * @return <tt>true</tt> if this map contains a mapping for the
     * specified key.
     * @throws ClassCastException if the key cannot be compared with the
     * keys currently in the map.
     * @throws NullPointerException key is <tt>null</tt> and this map uses
     * natural ordering, or its comparator does not tolerate <tt>null</tt>
     * keys.
     */
    public String doMap(){
	return "did it";
	//Bob comments
    }

    public String  doMap2() {
	return "2";
	//Name changed
    }

    public boolean containsKey(Object key) {
        throw new NotImplemented();
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only
     * if this map contains at least one mapping to a value <tt>v</tt> such
     * that <tt>(value==null ? v==null : value.equals(v))</tt>.  This
     * operation will probably require time linear in the Map size for most
     * implementations of Map.
     *
     * @param value value whose presence in this Map is to be tested.
     * @return  <tt>true</tt> if a mapping to <tt>value</tt> exists;
     * <tt>false</tt> otherwise.
     */
    public boolean containsValue(Object value) {
        throw new NotImplemented();
    }

    /**
     * Returns the value to which this map maps the specified key.  Returns
     * <tt>null</tt> if the map contains no mapping for this key.  A return
     * value of <tt>null</tt> does not <i>necessarily</i> indicate that the
     * map contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to <tt>null</tt>.  The <tt>containsKey</tt>
     * operation may be used to distinguish these two cases.
     *
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or
     * <tt>null</tt> if the map contains no mapping for the key.
     * @throws    ClassCastException key cannot be compared with the keys
     * currently in the map.
     * @throws NullPointerException key is <tt>null</tt> and this map uses
     * natural ordering, or its comparator does not tolerate <tt>null</tt>
     * keys.
     *
     * @see #containsKey(Object)
     */
    public V get(Object key) {
        throw new NotImplemented();
    }

    /**
     * Returns the comparator used to order this map, or <tt>null</tt> if
     * this map uses its keys' natural order.
     *
     * @return the comparator associated with this sorted map, or
     * <tt>null</tt> if it uses its keys' natural sort method.
     */
    public java.util.Comparator<? super K> comparator() {
        throw new NotImplemented();
    }

    /**
     * Returns the first (lowest) key currently in this sorted map.
     *
     * @return the first (lowest) key currently in this sorted map.
     * @throws    NoSuchElementException Map is empty.
     */
    public K firstKey() {
        throw new NotImplemented();
    }

    /**
     * Returns the last (highest) key currently in this sorted map.
     *
     * @return the last (highest) key currently in this sorted map.
     * @throws    NoSuchElementException Map is empty.
     */
    public K lastKey() {
        throw new NotImplemented();
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * These mappings replace any mappings that this map had for any of the
     * keys currently in the specified map.
     *
     * @param     map mappings to be stored in this map.
     * @throws    ClassCastException class of a key or value in the
     * specified map prevents it from being stored in this map.
     * @throws NullPointerException if the given map is <tt>null</tt> or
     * this map does not permit <tt>null</tt> keys and a key in the
     * specified map is <tt>null</tt>.
     */
    public void putAll(java.util.Map map) {
        throw new NotImplemented();
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for this key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key.
     *
     * @return previous value associated with specified key, or
     * <tt>null</tt> if there was no mapping for key.  A <tt>null</tt>
     * return can also indicate that the map previously associated
     * <tt>null</tt> with the specified key.
     * @throws    ClassCastException key cannot be compared with the keys
     * currently in the map.
     * @throws NullPointerException key is <tt>null</tt> and this map uses
     * natural order, or its comparator does not tolerate <tt>null</tt>
     * keys.
     */
    public V put(K key, V value) {
        throw new NotImplemented();
    }

    /**
     * Removes the mapping for this key from this BSTMap if present.
     *
     * @param  key key for which mapping should be removed
     * @return previous value associated with specified key, or
     * <tt>null</tt> if there was no mapping for key.  A <tt>null</tt>
     * return can also indicate that the map previously associated
     * <tt>null</tt> with the specified key.
     *
     * @throws    ClassCastException key cannot be compared with the keys
     * currently in the map.
     * @throws NullPointerException key is <tt>null</tt> and this map uses
     * natural order, or its comparator does not tolerate <tt>null</tt>
     * keys.
     */
    public V remove(Object key) {
        throw new NotImplemented();
    }

    /**
     * Removes all mappings from this BSTMap.
     */
    public void clear() {
        throw new NotImplemented();
    }

    /**
     * Returns a shallow copy of this <tt>BSTMap</tt> instance. (The keys
     * and values themselves are not cloned.)
     *
     * @return a shallow copy of this Map.
     */
    public Object clone() {
        throw new NotImplemented();
    }


    // Views

    /**
     * This field is initialized to contain an instance of the entry set
     * view the first time this view is requested.  The view is stateless,
     * so there's no reason to create more than one.
     */
    //private transient volatile java.util.Set entrySet = null;

    /**
     * Returns a Set view of the keys contained in this map.  The set's
     * iterator will return the keys in ascending order.  The map is backed
     * by this <tt>BSTMap</tt> instance, so changes to this map are
     * reflected in the Set, and vice-versa.  The Set supports element
     * removal, which removes the corresponding mapping from the map, via
     * the <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a set view of the keys contained in this BSTMap.
     */
    public java.util.Set<K> keySet() {
        throw new NotImplemented();
    }

    /**
     * Returns a collection view of the values contained in this map.  The
     * collection's iterator will return the values in the order that their
     * corresponding keys appear in the tree.  The collection is backed by
     * this <tt>BSTMap</tt> instance, so changes to this map are reflected
     * in the collection, and vice-versa.  The collection supports element
     * removal, which removes the corresponding mapping from the map
     * through the <tt>Iterator.remove</tt>, <tt>Collection.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations. It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a collection view of the values contained in this map.
     */
    public java.util.Collection<V> values() {
        throw new NotImplemented();
    }

    /**
     * Returns a set view of the mappings contained in this map.  The set's
     * iterator returns the mappings in ascending key order.  Each element
     * in the returned set is a <tt>Map.Entry</tt>.  The set is backed by
     * this map, so changes to this map are reflected in the set, and
     * vice-versa. The set supports element removal, which removes the
     * corresponding mapping from the BSTMap, through the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map.
     * @see Map.Entry
     */
    public java.util.Set<java.util.Map.Entry<K,V>> entrySet() {
        throw new NotImplemented();
    }

    /**
     * Returns a view of the portion of this map whose keys range from
     * <tt>fromKey</tt>, inclusive, to <tt>toKey</tt>, exclusive.  (If
     * <tt>fromKey</tt> and <tt>toKey</tt> are equal, the returned sorted
     * map is empty.)  The returned sorted map is backed by this map, so
     * changes in the returned sorted map are reflected in this map, and
     * vice-versa. The returned sorted map supports all optional map
     * operations.<p>
     *
     * The sorted map returned by this method will throw an
     * <tt>IllegalArgumentException</tt> if the user attempts to insert a
     * key less than <tt>fromKey</tt> or greater than or equal to
     * <tt>toKey</tt>.<p>
     *
     * Note: this method always returns a <i>half-open range</i> (which
     * includes its low endpoint but not its high endpoint).  If you need a
     * <i>closed range</i> (which includes both endpoints), and the key
     * type allows for calculation of the successor a given key, merely
     * request the subrange from <tt>lowEndpoint</tt> to
     * <tt>successor(highEndpoint)</tt>. For example, suppose that
     * <tt>m</tt> is a sorted map whose keys are strings.  The following
     * idiom obtains a view containing all of the key-value mappings in
     * <tt>m</tt> whose keys are between <tt>low</tt> and <tt>high</tt>,
     * inclusive:
     *             <pre>    SortedMap sub = m.submap(low, high+"\0");</pre>
     * A similar technique can be used to generate an <i>open range</i>
     * (which contains neither endpoint).  The following idiom obtains a
     * view containing all of the key-value mappings in <tt>m</tt> whose
     * keys are between <tt>low</tt> and <tt>high</tt>, exclusive:
     *             <pre>    SortedMap sub = m.subMap(low+"\0", high);</pre>
     *
     * @param fromKey low endpoint (inclusive) of the subMap.
     * @param toKey high endpoint (exclusive) of the subMap.
     *
     * @return a view of the portion of this map whose keys range from
     * <tt>fromKey</tt>, inclusive, to <tt>toKey</tt>, exclusive.
     *
     * @throws ClassCastException if <tt>fromKey</tt> and <tt>toKey</tt>
     * cannot be compared to one another using this map's comparator (or,
     * if the map has no comparator, using natural ordering).
     * @throws IllegalArgumentException if <tt>fromKey</tt> is greater than
     * <tt>toKey</tt>.
     * @throws NullPointerException if <tt>fromKey</tt> or <tt>toKey</tt>
     * is <tt>null</tt> and this map uses natural order, or its comparator
     * does not tolerate <tt>null</tt> keys.
     */
    public java.util.SortedMap<K,V> subMap(K fromKey, K toKey) {
        throw new NotImplemented();
    }

    /**
     * Returns a view of the portion of this map whose keys are strictly
     * less than <tt>toKey</tt>.  The returned sorted map is backed by this
     * map, so changes in the returned sorted map are reflected in this
     * map, and vice-versa.  The returned sorted map supports all optional
     * map operations.<p>
     *
     * The sorted map returned by this method will throw an
     * <tt>IllegalArgumentException</tt> if the user attempts to insert a
     * key greater than or equal to <tt>toKey</tt>.<p>
     *
     * Note: this method always returns a view that does not contain its
     * (high) endpoint.  If you need a view that does contain this
     * endpoint, and the key type allows for calculation of the successor a
     * given key, merely request a headMap bounded by
     * <tt>successor(highEndpoint)</tt>. For example, suppose that suppose
     * that <tt>m</tt> is a sorted map whose keys are strings.  The
     * following idiom obtains a view containing all of the key-value
     * mappings in <tt>m</tt> whose keys are less than or equal to
     * <tt>high</tt>:
     * <pre>
     *     SortedMap head = m.headMap(high+"\0");
     * </pre>
     *
     * @param toKey high endpoint (exclusive) of the headMap.
     * @return a view of the portion of this map whose keys are strictly
     * less than <tt>toKey</tt>.
     *
     * @throws ClassCastException if <tt>toKey</tt> is not compatible with
     * this map's comparator (or, if the map has no comparator, if
     * <tt>toKey</tt> does not implement <tt>Comparable</tt>).
     * @throws IllegalArgumentException if this map is itself a subMap,
     * headMap, or tailMap, and <tt>toKey</tt> is not within the specified
     * range of the subMap, headMap, or tailMap.
     * @throws NullPointerException if <tt>toKey</tt> is <tt>null</tt> and
     * this map uses natural order, or its comparator does not tolerate
     * <tt>null</tt> keys.
     */
    public java.util.SortedMap<K,V> headMap(K toKey) {
        throw new NotImplemented();
    }

    /**
     * Returns a view of the portion of this map whose keys are greater
     * than or equal to <tt>fromKey</tt>.  The returned sorted map is
     * backed by this map, so changes in the returned sorted map are
     * reflected in this map, and vice-versa.  The returned sorted map
     * supports all optional map operations.<p>
     *
     * The sorted map returned by this method will throw an
     * <tt>IllegalArgumentException</tt> if the user attempts to insert a
     * key less than <tt>fromKey</tt>.<p>
     *
     * Note: this method always returns a view that contains its (low)
     * endpoint.  If you need a view that does not contain this endpoint,
     * and the element type allows for calculation of the successor a given
     * value, merely request a tailMap bounded by
     * <tt>successor(lowEndpoint)</tt>. For For example, suppose that
     * suppose that <tt>m</tt> is a sorted map whose keys are strings.  The
     * following idiom obtains a view containing all of the key-value
     * mappings in <tt>m</tt> whose keys are strictly greater than
     * <tt>low</tt>:
     * <pre>
     *     SortedMap tail = m.tailMap(low+"\0");
     * </pre>
     *
     * @param fromKey low endpoint (inclusive) of the tailMap.
     * @return a view of the portion of this map whose keys are greater
     * than or equal to <tt>fromKey</tt>.
     * @throws ClassCastException if <tt>fromKey</tt> is not compatible
     * with this map's comparator (or, if the map has no comparator, if
     * <tt>fromKey</tt> does not implement <tt>Comparable</tt>).
     * @throws IllegalArgumentException if this map is itself a subMap,
     * headMap, or tailMap, and <tt>fromKey</tt> is not within the
     * specified range of the subMap, headMap, or tailMap.
     * @throws NullPointerException if <tt>fromKey</tt> is <tt>null</tt>
     * and this map uses natural order, or its comparator does not tolerate
     * <tt>null</tt> keys.
     */
    public java.util.SortedMap<K,V> tailMap(K fromKey) {
        throw new NotImplemented();
    }

    /**
     * Node in the Tree.  Doubles as a means to pass key-value pairs back
     * to user (see Map.Entry).
     */
    static class Entry implements java.util.Map.Entry {
        Object key;
        Object value;
        Entry left = null;
        Entry right = null;
        Entry parent;

        /**
         * Make a new cell with given key, value, and parent, and with
         * <tt>null</tt> child links.
         */
        Entry(Object key, Object value, Entry parent) {
            throw new NotImplemented();
        }

        /**
         * Returns the key.
         *
         * @return the key.
         */
        public Object getKey() {
            return new Integer(45);
        }

        /**
         * Returns the value associated with the key.
         *
         * @return the value associated with the key.
         */
        public Object getValue() {
	    return new String("Bob");
        }

        /**
         * Replaces the value currently associated with the key with the
         * given value.
         *
         * @return the value associated with the key before this method was
         * called.
         */
        public Object setValue(Object value) {
            return new String();
        }

        public boolean equals(Object o) {
            return false;
	    //YourComment
        }

        public int hashCode() {
            int keyHash = (key==null ? 0 : key.hashCode());
            int valueHash = (value==null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }
}
