/**************************************************
 * Simple sorting algorithms 
 * Modified by E/11/XXX
 **************************************************/
import java.util.Random;

class CompareSorting { 
    
    static void bubble_sort(int [] data) { 
    }
    
    static void selection_sort(int [] data) { 
    }

    static void insertion_sort(int [] data) { 
    }


    // Helper functions 

    static int [] generate_data(int sizeOfData) { 
	/* create an array of sizeOfData and 
	 * populate with random integers betweem 0-1000
	 */

	int [] tmp = new int[sizeOfData];
	Random rand = new Random();
	for(int i=0; i < sizeOfData; i++)
	    tmp[i] = rand.nextInt(2*sizeOfData);
	return tmp; 
    }

    static int [] duplicate_array(int [] data) { 
	/* create a duplicate array of the given 
	 * useful when sending the same array to different 
	 * algorithms.
	 */
	int [] tmp = new int[data.length];
	for(int i=0; i< data.length; i++)
	    tmp[i] = data[i];
	
	return tmp; 
    }

    static void show(int [] data) {
	System.out.printf("\n");
	for(int i=0; i < data.length; i++)
	    System.out.printf("%d %c", data[i],
			      i == (data.length - 1) ? ' ' : ',');
	System.out.printf("\n");
    }
    
    static void postCondition(int [] data) { 
	/* if sorted, for any i data[i] > data[i-1]
	 * Need to run this with java -ea CompareSorting
	 */
	int i; 
	for(i=1; i < data.length; i++) 
	    if(data[i] > data[i-1]) break; 

	assert i == data.length : "Sorting algorithm used is broken";
    }

    public static void main(String [] ar) {
	int [] t = generate_data(30);
	show(t);
	postCondition(t); 

    }
}

	   
