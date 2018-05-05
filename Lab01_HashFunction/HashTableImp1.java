public class HashTableImp1 extends HashTableImp {
    /*
        This is the default hash function in Java
     */
    HashTableImp1(int buckets){
        super(buckets);
    }

    public int hash(String s){
        s=preProcess(s);
        int hash=0;
        for(int x=0;x<s.length();x++){
            hash=modAdd(modMultiply(hash,31),s.charAt(x));
        }

        return hash;
    }
}
