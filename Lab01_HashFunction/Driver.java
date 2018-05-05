import java.io.*;

public class Driver {
    /*
        CO222 Lab 01
        Hash Functions
        E/14/158 gihanchanaka@gmail.com
        03-0-2018

        This is the Driver class.
        Should be run as
            java Driver fileName noOfBuckets

     */
    static int BUCKETS,TYPES=5;
    public static void main(String[] args) {
        String fileName=args[0];
        BUCKETS=Integer.parseInt(args[1]);
        File file=new File(fileName);

        HashTableImp[] hashTables=new HashTableImp[TYPES];
        hashTables[0]=new HashTableImp1(BUCKETS);
        hashTables[1]=new HashTableImp2(BUCKETS);
        hashTables[2]=new HashTableImp3(BUCKETS);
        hashTables[3]=new HashTableImp4(BUCKETS);
        hashTables[4]=new HashTableImp5(BUCKETS);

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            while(br.ready()){
                String[] ar=br.readLine().split(" ");
                for(int x=0;x<TYPES;x++){
                    for(int y=0;y<ar.length;y++)hashTables[x].insert(ar[y]);
                }
            }
            System.out.println("Algo\t\tMean\t\tStdDev\t\tEntrophy\t\tMin-Max");
            for(int x=0;x<TYPES;x++){
                System.out.print((x+1)+".\t\t");
                hashTables[x].printDistribution();
            }

            System.out.println("Bucket filled pattern");
            for(int x=0;x<TYPES;x++){
                hashTables[x].printBucketSizes();
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
}
