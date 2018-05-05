import javafx.util.Pair;

import java.util.Iterator;
import java.util.LinkedList;

/*********************************************
 * CO322: Data structures and algorithms
 * Implementation of the hashTable
 *********************************************/
abstract class HashTableImp implements HashTable {
    int noOfBuckets;
    LinkedList<String>[] data;
    /* Put your code here */

    public HashTableImp(int buckets) {
        // create a open hash table with given number of buckets
        noOfBuckets=buckets;
        data=new LinkedList[noOfBuckets];
        for(int x=0;x<noOfBuckets;x++)data[x]=new LinkedList();

    }

    public void insert(String s){
        s=preProcess(s);
        if(s.length()==0)return;
        data[hash(s)].push(s);
    }

    public int search(String s){
        s=preProcess(s);
        Iterator<String> it=data[hash(s)].iterator();
        int count=0;
        while(it.hasNext())if(it.next().equals(s))count++;
        return count;
    }


    public abstract int hash(String s);

    public static String preProcess(String s){
        return s.replaceAll("[^a-zA-Z]", "");
    }



    public int total(){
        int sum=0;
        for(int x=0;x<noOfBuckets;x++)sum+=data[x].size();
        return sum;
    }
    public float mean(){
        return total()/(1.0f*noOfBuckets);
    }
    public float var(){
        float var=0;
        float mean=mean();
        for(int x=0;x<noOfBuckets;x++)var+=Math.pow(data[x].size()-mean,2);
        return var/(1.0f*noOfBuckets);
    }

    public float std(){
        return (float)Math.pow(var(),0.5f);
    }

    public int min(){
        int min=Integer.MAX_VALUE;
        for(int x=0;x<noOfBuckets;x++)min=Math.min(min,data[x].size());
        return min;
    }

    public int max(){
        int max=Integer.MIN_VALUE;
        for(int x=0;x<noOfBuckets;x++)max=Math.max(max,data[x].size());
        return max;
    }

    public float entrophy(){
        float total=total();
        float entrophy=0;
        for(int x=0;x<noOfBuckets;x++){
            float prob=data[x].size()/total;
            if(Math.abs(prob)>10e-10)entrophy-=(prob*Math.log(prob));
        }
        return entrophy;
    }

    public void printDistribution(){
        System.out.println(mean()+"\t\t"+std()+"\t\t"+entrophy()+"\t\t\t"+min()+"-"+max());

    }

    public int mod(int x){
        return x%noOfBuckets;
    }

    public int modMultiply(int x,int y){
        return mod((x*y));
    }
    public int modAdd(int x,int y){
        return mod((x+y));
    }

    public void printBucketSizes(){
        for(int x=0;x<noOfBuckets;x++)System.out.print(data[x].size()+"\t");
        System.out.println();
    }





}// end HashTableImp
