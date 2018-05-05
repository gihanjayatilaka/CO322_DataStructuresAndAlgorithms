import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.SplittableRandom;

public class Learn {
    /*
        CO222 Lab 01
        Hash Functions
        E/14/158 gihanchanaka@gmail.com
        03-0-2018

        This is the Learn class.
        Should be run as
            java Learn fileName noOfLearningIterations noOfBuckets

 */
    static int[][] parameters=new int[10][26];
    static ArrayList<String>[] buckets;
    static int noOfBuckets;
    static Random r=new Random();
    public static void main(String[] args) {
        /*
            args[0] FileName for text
            args[1] Iterations
            args[2] Buckets
         */
        int iterations=Integer.parseInt(args[1]);
        int i=0;
        noOfBuckets=Integer.parseInt(args[2]);
        buckets=new ArrayList[noOfBuckets];
        ArrayList<String> allWords=new ArrayList<>();

        for(int x=0;x<noOfBuckets;x++)buckets[x]=new ArrayList<String>();


        try {

            FileReader fr = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(fr);

            while(br.ready()){
                String[] ar=br.readLine().split(" ");
                for(int x=0;x<ar.length ;x++){
                    String temp=HashTableImp.preProcess(ar[x]).toLowerCase();
                    if(temp.length()!=0)allWords.add(temp);

                }


            }


        }
        catch (FileNotFoundException e){
            System.out.println("File not found!");
            return;
        }
        catch (IOException e){
            System.out.println("Error reading file");
            return;
        }

        for(i=0;i<iterations;i++){
            int wordIndex=r.nextInt(allWords.size());
            adapt(allWords.get(wordIndex));
        }

        String b=noOfBuckets+"";
        while(b.length()<5)b="0"+b;
        File f=new File("NewAlgoParameters_"+b+".txt");

        try {
            FileWriter fw = new FileWriter(f);

            for (int l = 0; l < 10; l++) {
                for (int c = 0; c < 26; c++) {
                    fw.append(parameters[l][c] + " ");
                }
            }
            fw.append('n');
            fw.close();
        }
        catch (IOException e){
            System.out.println("Error while making parameter file");
        }
    }

    public static boolean adapt(String word){
        word=HashTableImp.preProcess(word).toLowerCase();
        if(word.length()==0)return false;
        int oldBucketNo=hash(word);
        int oldBucketSize=buckets[oldBucketNo].size();

        int newBucketNo=-1;
        for(int x=0;x<noOfBuckets;x++){
            if(buckets[x].size()<oldBucketSize){
                newBucketNo=x;
                oldBucketSize=buckets[x].size();
            }
        }

        if(newBucketNo>=0){
            int locationToChangeParameter=Math.min(word.length()-1,r.nextInt(10));
            char letterToChangeParameter=(char)(word.charAt(locationToChangeParameter)-'a');
            if(newBucketNo<oldBucketNo){
                parameters[locationToChangeParameter][letterToChangeParameter]+=noOfBuckets-Math.abs((oldBucketNo-newBucketNo)%noOfBuckets);
            }
            else{
                parameters[locationToChangeParameter][letterToChangeParameter]+=((newBucketNo-oldBucketNo));
            }



            buckets[newBucketNo].add(word);
            rehash();
        }
        else{
            buckets[oldBucketNo].add(word);
        }




        return true;
    }


    public static int hash(String s){
        s=HashTableImp.preProcess(s).toLowerCase();
        int hash=0;
        for(int x=0;x<s.length()&&x<10;x++){
            hash+=parameters[x][s.charAt(x)-'a'];
        }
        return (hash%noOfBuckets);
    }

    public static void rehash(){
        for(int x=0;x<noOfBuckets;x++){
            for(int y=buckets[x].size()-1;y>-1;y--){
                if(hash(buckets[x].get(y))!=x){
                    String temp=buckets[x].get(y);
                    buckets[x].remove(y);
                    buckets[hash(temp)].add(temp);
                }
            }
        }
    }

}
