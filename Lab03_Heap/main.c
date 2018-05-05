#include "heap.h"
#include <stdio.h>
#include <stdlib.h>

int proper_heap(heap_t heap)
{
  /* just keep on removing.
   * if the heap was implmement correctly you should not 
   * remove an item larger than previous 
   */
  if(!heap)         return !printf("bad reference\n");
  if(isEmpty(heap)) return !printf("Empty heap\n");
  
  int pre = heap_remove(heap);
  while(!isEmpty(heap)) {
    int curr = heap_remove(heap);
    if(curr < pre) return 0;
  }
  return 1; // good work
}
    
  

int main()
{
  int i=0;
  heap_t heap = create();
  
  for(i=0; i < 5*SIZE; i++) {
    int tmp = rand() % 60;
    insert(heap, tmp);
  }
  
  if(proper_heap(heap))
    printf("Congratulations, you implementation works\n");

  return 0;
}
