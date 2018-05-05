#ifndef __HEAP_
#define __HEAP__

// 
struct heap {
  int *data; // array where data will be placed
  int next;  // array index of next item 
  int curr_size; // current size of the array 
};


typedef struct heap * heap_t; 

// create an empty heap and send a reference 
heap_t create(void);

// insert the given item to the heap 
void insert(heap_t, int);

// remove an item from the heap.
// remove is a function in stdio.h so this is called heap_remove
int heap_remove(heap_t);

// return true if the heap is empty 
int isEmpty(heap_t);

// initial heap will have this many elements 
#define SIZE 100

// when the array becomes full, add this many items to it 
#define INCREASE_BY 100

#endif
