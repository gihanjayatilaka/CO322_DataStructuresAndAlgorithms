public class HashTableImp4 extends HashTableImp{
    /*
        sdbm algorithm
        http://www.cse.yorku.ca/~oz/hash.html
     */
    HashTableImp4(int buckets){
        super(buckets);
    }

    public int hash(String s){
        s=preProcess(s);
        int hash=0;
        for(int x=0;x<s.length();x++){
            hash=modAdd(modMultiply(hash,65599),s.charAt(x));
        }
        return hash;
    }
}
