package dcgpt.class02;

import java.util.HashMap;

/**
 * @Author: LiuXingKun
 * @Date: 2023/3/17 16:06
 */
    //一个时间戳time 一个值value
    //创建一个HashMap他的key是我自己创建的myHashMap的key，但是他的值是value和time 就是：key,(value,time),: (17,(10,0)),(12,(9,1)),(11,(11,2))
    //put就是正常的put行为(key, new MyValue<V>(value, time++))，(17,(10,0))，(12,(9,1))，(11,(11,2))
    //setAll假如发生在3时刻，记录一下这个时间点，并把这个值记录下来，time++就变成了4
    //如果出现get行为，看time是不是大于setAll的时间点，是就是正常put进去的值，否则就取setAll的值。
public class Code05_SetAll {


    public static class MyValue<V>{
        public V value;
        public long time;
        public MyValue(V v,long t){
            value=v;
            time=t;
        }
    }
    private static class MyHashMap1<K,V>{
        public HashMap<K,MyValue<V>> map;
        public long time;
        public MyValue<V> setAll;
        public MyHashMap1(){
            map = new HashMap<>();
            time = 0;
            setAll = new MyValue<V>(null,-1);
        }

        public void put(K key,V value){
            map.put(key,new MyValue<V>(value,time++));
        }
        public void setAll(V value){
            setAll = new MyValue<V>(value,time++);
        }
        public V get(K key){
            if(!map.containsKey(key)){
                return null;
            }else {
                if(map.get(key).time > setAll.time){
                    return map.get(key).value;
                }else {
                    //key的时间是小于setAll的时间，那么就是被全部换成setAll的值了，取的时候就用setAll的值就好了
                    return setAll.value;
                }
            }
        }
    }
}
