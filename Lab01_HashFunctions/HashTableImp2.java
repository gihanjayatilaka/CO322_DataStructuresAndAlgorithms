public class HashTableImp2 extends HashTableImp{
    /*
        Adding the character values
     */
    HashTableImp2(int buckets){
        super(buckets);
    }

    public int hash(String s){
        s=preProcess(s).toLowerCase();
        int hash=0;
        for(int x=0;x<s.length();x++){
            hash=modAdd(hash,s.charAt(x)-'a');
        }
        return hash;
    }
}
