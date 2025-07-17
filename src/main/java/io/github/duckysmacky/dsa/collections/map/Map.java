package io.github.duckysmacky.dsa.collections.map;

import io.github.duckysmacky.dsa.collections.Collection;
import io.github.duckysmacky.dsa.collections.set.Set;

/// A structure which represents a key-value table of values, where each value has a unique key by which it can be
/// identified
///
/// @param <K> map's key type
/// @param <V> map's value type
public interface Map<K, V> extends Collection<V> {
    /// Get a value from the map by its key. If the value doesn't exist, will return `null`
    ///
    /// @return value at the specified key or `null`
    V get(K key);
    /// Get a value from the map by its key. If the value doesn't exist, will return the specified `defaultValue`
    ///
    /// @return value at the specified key or `defaultValue`
    V getOrDefault(K key, V defaultValue);
    /// Adds a new value to the map with the specified key. If the key already exists and there is already a value that
    /// is associated with that key, the value will be replaced
    void put(K key, V value);
    /// Adds a new key-value pair (entry) to the map. If the key already exists and there is already a value that is
    /// associated with that key, the value will be replaced
    void put(Entry<K, V> entry);
    /// Adds a new value to the map with the specified key if there is already no given key in the map. If the key
    /// already exists and there is already a value that is associated with that key, the value won't be added.
    void putIfAbsent(K key, V value);
    /// Adds a new key-value pair (entry) to the map if there is already no given key in the map. If the key
    /// already exists and there is already a value that is associated with that key, the value won't be added.
    void putIfAbsent(Entry<K, V> entry);
    /// Removes the value from the map at the specified key. Will also remove the key from the map completely
    V remove(K key);
    /// Replaced the value from the map at the specified key with the provided. If the key doesn't exist, will **not**
    /// create a new key-value pair
    void replace(K key, V newValue);
    /// Checks if the given key is in the map
    ///
    /// @return if the key is in the map
    boolean containsKey(K key);
    /// Checks if the given value is in the map
    ///
    /// @return if the value is in the map
    boolean containsValue(V value);
    /// Get all of map's keys as a set
    ///
    /// @return set of map's keys
    Set<K> keySet();
    /// Get all of map's entries (key-value pairs) as a set
    ///
    /// @return set of map's entries
    Set<Entry<K, V>> entrySet();
    /// Get all of map's values. Since they are values and not keys, they can be repeating
    ///
    /// @return collection of map's values
    Collection<V> values();
    /// A structure which represents a key-value pair within a map
    ///
    /// @param <K> map's key type
    /// @param <V> map's value type
    interface Entry<K, V> {
        void setKey(K key);
        void setValue(V value);
        K getKey();
        V getValue();
    }
}
