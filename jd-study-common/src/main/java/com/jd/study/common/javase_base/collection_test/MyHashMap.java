package com.jd.study.common.javase_base.collection_test;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * 补充：异或运算：1^0=1,0^1=1,0^0=0,1^1=0,两个值相等则异或为0，两个值不相等，异或为0
 * 用途是：可以用于数值进行交换运算
 *
 *
 *
 *
 * hashMap采用的是懒初始化的方式，构造函数中并没有初始化table数组
 * 只有在真正往里面放东西的时候才开始初始化，调用的是inflatable方法进行初始数组的行为的
 *
 *
 * 删除的时候并没有把table数组进行移动
 *
 *
 *
 * @author dushuangcheng
 * @description
 * @create 2017/6/19
 */
public class MyHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    /**
     * @description:初始化的容量
     */
    private static int DEFAULT_INIT_CAPACITY = 1 << 4;
    /**
     * @description:最大的容量
     */
    private int MAX_CAPACITY = 1 << 30;
    /**
     * @description:默认的负载因子
     */
    private static float DEFAULT_LOADER_FACTOR = 0.75f;
    private final MyEntry<?, ?>[] EMPTY_ENTRY = {};
    /**
     * @description:存放链表节点的数组，其中这个长度必须是2的次幂，以保证良好的位运算来 代替取模运算。
     */
    transient MyEntry<K, V>[] table = (MyEntry<K, V>[]) EMPTY_ENTRY;
    /**
     * @description:在map中所包含的key-value对的数目
     */
    transient int size;
    /**
     * @description:这个值是决定什么什么进行重新扩张数组的长度的一个变量 这个值等于loaderFactor*capacity
     */
    int threshold;
    /**
     * @description:
     */
    float loadFactor;
    /**
     * @description:这个值显示了这个map被修改的次数，主要运用于fail-fast的机制
     */
    transient int modCount;
    /**
     * @description:这个值主要用于string 的key类型的，可选择的hash可以降低
     * hash碰撞
     */
    static final int ALTERNATIVE_HASHING_THRESHOLD_DEFAULT = Integer.MAX_VALUE;

    //构造函数
    public MyHashMap(int initCapacity, float loadFactor) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException("参数错误。。。");
        }
        if (initCapacity > MAX_CAPACITY) {
            initCapacity = MAX_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("参数错误。。。");
        }
        this.loadFactor = loadFactor;
        this.threshold = initCapacity;

        init();

    }

    private static class Holder {
        static final int ALTERNATIVE_HASHING_THRESHOLD;

        static {
            String altThreshold = java.security.AccessController.doPrivileged(
                    new sun.security.action.GetPropertyAction(
                            "jdk.map.althashing.threshold"));

            int threshold;
            try {
                threshold = (null != altThreshold)
                        ? Integer.parseInt(altThreshold)
                        : ALTERNATIVE_HASHING_THRESHOLD_DEFAULT;

                // disable alternative hashing if -1
                if (threshold == -1) {
                    threshold = Integer.MAX_VALUE;
                }

                if (threshold < 0) {
                    throw new IllegalArgumentException("value must be positive integer.");
                }
            } catch (IllegalArgumentException failed) {
                throw new Error("Illegal value for 'jdk.map.althashing.threshold'", failed);
            }

            ALTERNATIVE_HASHING_THRESHOLD = threshold;
        }
    }

    /**
     * @description:这个值主要用于作为一个随机的种子来避免hash碰撞
     */
    transient int hashSeed = 0;


    /**
     * @param
     * @return
     * @description构造函数中对map进行初始化的操作
     * @Author dushuangcheng
     * @throw
     * @date
     */
    private void init() {
    }

    public MyHashMap(int initCapacity) {
        this(initCapacity, DEFAULT_LOADER_FACTOR);
    }

    public MyHashMap() {
        this(DEFAULT_INIT_CAPACITY, DEFAULT_LOADER_FACTOR);
    }

    public MyHashMap(Map<? extends K, ? extends V> map) {
        this(Math.max((int) (map.size() / DEFAULT_LOADER_FACTOR) + 1, DEFAULT_INIT_CAPACITY), DEFAULT_LOADER_FACTOR);
        inflateTable(threshold);
    }

    private void inflateTable(int threshold) {
        int capacity = roundUpToPowerOf2(threshold);
        threshold = (int) Math.min(capacity * loadFactor, MAX_CAPACITY);
        //创建entry数组
        table = new MyEntry[capacity];
        initHashSeedAsNeeded(capacity);
    }

    /**
     * @param
     * @return
     * @description 对map的长度进行整理
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/19
     */
    private int roundUpToPowerOf2(int size) {
        return size >= MAX_CAPACITY ? MAX_CAPACITY : (size > 1) ? Integer.highestOneBit(size - 1) << 1 : 1;
    }

    /**
     * @param
     * @return
     * @description 初始化一个hash算法的种子根据需要, 初始化一个hash的掩码值
     * 规则是：若两个中有一个为true则返回true，但是都为false或者都为true返回false
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/19
     */
    private boolean initHashSeedAsNeeded(int size) {
        boolean currentAltHashing = hashSeed != 0;
        boolean useAltHashing = sun.misc.VM.isBooted() && (size >= Holder.ALTERNATIVE_HASHING_THRESHOLD);
        boolean switching = currentAltHashing ^ useAltHashing;
        if (switching) {
            hashSeed = useAltHashing ? sun.misc.Hashing.randomHashSeed(this) : 0;
        }
        return switching;
    }


    /**
     * @param
     * @return
     * @description hash算法
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/19
     */
    final int hash(Object k) {
        int h = hashSeed;
        if (0 != h && k instanceof String) {
            //调用字符串的hash算法
            return sun.misc.Hashing.stringHash32((String) k);
        }
        h ^= k.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /**
     * @param
     * @return
     * @description 根据hash值获取在数组中的下标索引值
     * 在这个体现了为啥要将数组的长度的值设置为2，因为当值为2的次幂的时候，减去1正好
     * 所有的为上是指都是1，方便把取模运算转为位运算。
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/19
     */
    static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param
     * @return
     * @description 获取对用的key的值，由于hashMap允许兼职为null,而且所有
     * 兼职为null的hash值对应为0.
     * 1.null的先进行判断，这个时候一般null的hash值为0，从而根据这个计算出它所对应的table数组的下标
     * 索引位置，如果是非null的情况，那么先计算hash值找到table数组对应的下标索引的位置，然后
     * 开始遍历这个位置上对应的链表，找到跟其key值相等的值，取出值即可
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/19
     */
    public V get(Object key) {
        if (key == null) {
            return getForNullKey();
        }
        //根据下标索引的位置找到这个entry
        MyEntry<K, V> entry = getEntry(key);
        return null == entry ? null : entry.getValue();
    }

    /**
     * @param
     * @return
     * @description put方法，hashMap采用的是懒初始化的方式，先初始化table数组
     * 注意在put方法的执行过程中，如果出现hash冲突的现象，在采用链表的方式进行解决的过程中
     * 后来的元素会被放置在靠近table数组的一侧，next指针指向之前放的那些元素的位置上。这是之前的误区
     *
     * 同时还要注意，在hashMap扩容之后不一定会进行重新计算hash值，何时进行计算，主要取决于一个函数进行计算的结果
     * 至于原理后面可以查看相关的资料。
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/20
     */
    public V put(K key, V value) {
        //table还是空的？先进行容量初始化，所谓的“充气过程”
        if (table == EMPTY_ENTRY) {
            inflateTable(threshold);
        }
        //key为null，hash值为0
        if (key == null) {
            for (MyEntry<K, V> e = table[0]; e != null; e = e.next) {
                if (e.key == null) {
                    V value1 = e.value;
                    e.value = value;
                    //记录进行了更改
                    e.recordAccess(this);
                    //记录进行了更改，便于在迭代器中进行检测到
                    return value1;
                }
            }
            //加入没有找到
            modCount++;
            //添加一个entry
            addEntry(0, null, value, 0);
            return null;
        }
        return null;
    }

    /**
     * @param
     * @return
     * @description
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/19
     */
    private V getForNullKey() {
        if (size == 0) {
            return null;
        }
        //这个遍历是从下标为0开始进行查找的允许是所有的null的key都对应在
        //index为0的位置处的值
        for (MyEntry<K, V> e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                return e.value;
            }
        }
        return null;
    }

    /**
     * @param
     * @return
     * @description 通过key的值获取entry
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/19
     */
    private MyEntry getEntry(Object key) {
        if (size == 0) {
            return null;
        }
        int hash = (key == 0) ? 0 : hash(key);
        //先根据hash值找到对应的下标索引位置，然后开始遍历这个位置处的链表结构
        for (MyEntry<K, V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                return e;
            }
        }
        return null;
    }

    public boolean containsKey(Object key) {
        MyEntry entry = getEntry(key);
        if (entry != null) {
            return true;
        }
        return false;
    }

    static class MyEntry<K, V> implements Map.Entry<K, V> {
        final K key;
        V value;
        MyEntry<K, V> next;
        int hash;

        public MyEntry(int hash, MyEntry<K, V> next, K key, V value) {
            this.hash = hash;
            this.next = next;
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        /**
         * @param
         * @return
         * @description 返回旧值
         * @Author dushuangcheng
         * @throw
         * @date 2017/6/19
         */
        @Override
        public V setValue(V value) {
            V oldValue = value;
            value = value;
            return oldValue;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry e = (Entry) obj;
            Object key1 = getKey();
            Object key2 = e.getKey();
            if (key1 == key2 || (key1 != null && key1.equals(key2))) {
                Object value1 = getValue();
                Object value2 = e.getValue();
                if (value1 == value2 || (value1 != null && value1.equals(value2))) {
                    return true;
                }
            }
            return false;
        }

        /**
         * @description key和value的hashCode值取异或即可
         * @author dushuangcheng
         * @create 2017/6/19
         */
        public final int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }

        public final String toString() {
            return getKey() + "==" + getValue();
        }

        /**
         * @param
         * @return
         * @description 这个方法只有在当一个entry中的key存在的情况下调用put(key, value)的时候会被调用
         * 在LinkedHashMap中会被调用。
         * @Author dushuangcheng
         * @throw
         * @date 2017/6/19
         */
        public void recordAccess(MyHashMap<K, V> m) {
        }

        /**
         * @param
         * @return
         * @description 当一个entry被删除的时候会被调用
         * @Author dushuangcheng
         * @throw
         * @date 2017/6/19
         */
        public void recordRemoval(MyHashMap<K, V> m) {
        }

    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    /**
     * @param bucketIndex 在数组中的位置
     * @return
     * @description 根据bucketIndex添加一个entry, 这个方法一般在put方法中会被调用到的
     * 1，主要要考虑到扩容的机制，当容量到达loadFactor*capacity的时候就扩容
     * @Author dushuangcheng
     * @throw
     * @date 2017/6/19
     */
    public void addEntry(int hash, K key, V value, int bucketIndex) {

    }

    /**
     * 创建一个entry，同时next指针指向自己
     *
     * @param hash
     * @param key
     * @param value
     * @param bucketIndex
     */
    public void createEntry(int hash, K key, V value, int bucketIndex) {
        MyEntry<K, V> entry = table[bucketIndex];
        //next指针指向自己的一个节点
        table[bucketIndex] = new MyEntry<>(hash, entry, key, value);
        size++;
    }

    /**
     * @param newCapacity 以新的容量重新进行扩容，扩容涉及到旧有元素的拷贝处理的过程
     * 看来决定是否进行rehash的过程主要是由 initHashSeedAsNeeded 这个函数来决定的 ,这个函数
     * 是什么样的原理呢，还有待考察。
     */
    void reSize(int newCapacity) {
        MyEntry<K, V>[] oldTable=table;
        //不能再扩容了
        if(table.length==MAX_CAPACITY){
            threshold=Integer.MAX_VALUE;
            return;
        }
        //重新创建一个
        MyEntry[] newTable = new MyEntry[newCapacity];
        //转移,同时根据这个方法来决定是否要进行rehash的过程 initHashSeedAsNeeded
        transferEntry(newTable,initHashSeedAsNeeded(newCapacity));
        table=newTable;
        threshold= (int) Math.min((int)newCapacity*loadFactor,MAX_CAPACITY+1);
    }

    /**
     *
     * @param newTable 旧有的entry
     * @param reHash 是否重新进行hash值的计算
     */
    void transferEntry(MyEntry<K,V>[] newTable,boolean reHash){
        int newCapacity=newTable.length;
        for(MyEntry<K,V> e:newTable){
            //内循环主要用于处理每个链表节点上的元素的操作
            while (e!=null){
                MyEntry<K, V> next = e.next;
                //也就是说可能不一定需要进行reHash的操作，这个值主要是由table数组的长度来决定的
                //这样一方面可以提高性能，避免进行不必要的hash操作
                if(reHash){
                    e.hash=null==e.key?0:hash(e.key);
                }
                //根据hash值从新计算在新的数组中的索引的位置
                int i=indexFor(e.hash,newCapacity);
                //这个赋值是说明了把
                e.next=newTable[i];
                newTable[i]=e;
                e=next;
            }
        }
    }
}
