import java.io.*;
import java.text.Format;

public class HashTableImp5 extends HashTableImp{
    /*
        New proposed adaptiv hash function algorithm
     */
    int[][] algoParameters=new int[10][26];
    HashTableImp5(int buckets){
        super(buckets);
        String b=buckets+"";
        while(b.length()<5)b="0"+b;
        File f=new File("NewAlgoParameters_"+b+".txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String[] ar=br.readLine().trim().split(" ");
            for(int location=0;location<10;location++){
                for(int c=0;c<26;c++){
                    algoParameters[location][c]=Integer.parseInt(ar[location*26+c]);

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

    }

    public int hash(String s){
        s=preProcess(s).toLowerCase();
        int hash=0;
        for(int x=0;x<s.length() && x<10;x++){
            hash=modAdd(hash,algoParameters[x][s.charAt(x)-'a']);
        }
        return hash;
    }
}
