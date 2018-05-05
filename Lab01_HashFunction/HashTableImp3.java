public class HashTableImp3 extends HashTableImp{
    /*
        djb2:this algorithm (k=33) was first reported by dan bernstein
     */
    HashTableImp3(int buckets){
        super(buckets);
    }

    public int hash(String s){
        s=preProcess(s);
        int hash=0;
        for(int x=0;x<s.length();x++){
            hash=modAdd(modMultiply(hash,33),s.charAt(x));
        }
        return hash;
    }
}
