#include "heap.h"
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#define IMPLEMENT_ME						\
  printf("you need to implement %s function in %s\n",__func__, __FILE__);	\
  exit(-1);

/**
 * Note: typically, in C functions/variables that starts with 
 * __ are local and should not be used unless you really know what you
 * are doing. For example __start 
 */

int leftChild(int x){
  return 2*x+1;
}

int rightChild(int x){
  return 2*x+2;
}

int max(int x,int y){
  if(x>y) return x;
  return y;
}

int min(int x,int y){
  if(x<y) return x;
  return y;
}



int __isFull(heap_t heap)
{
  return (heap -> next == heap -> curr_size);
}

void __resize_heap(heap_t heap)
{
  heap -> curr_size += INCREASE_BY;
  heap -> data = (int *)realloc(heap->data,sizeof(int)*heap->curr_size);
}

void __swap(heap_t heap, int i, int j)
{
  int tmp = heap -> data[i];
  heap -> data[i] = heap -> data[j];
  heap -> data[j] = tmp;
}

int isEmpty(heap_t heap)
{
  return (heap -> next == 0);
}

heap_t create()
{
  heap_t newHeap= (heap_t)malloc(sizeof(heap_t));
  newHeap->data= (int*)calloc(SIZE,sizeof(int));
  newHeap->next=0;
  newHeap->curr_size=SIZE;

  return newHeap;
}

void insert(heap_t heap, int data)
{
  if(__isFull(heap))__resize_heap(heap);
  heap->data[heap->next]=data;
  int index=heap->next;
  heap->next++;

  while(index!=0){
    if(heap->data[index] < heap->data[(index-1)/2])__swap(heap,index,(index-1)/2);
    else break;
    index-=1;index/=2;
  }
}

int heap_remove(heap_t heap)
{
  int toReturn=heap->data[0];
  heap->data[0]=heap->data[heap->next-1];
  heap->next--;

  int x=0;
  while(1){
    if(leftChild(x) >=heap->next)break;
    else if(rightChild(x) >=heap->next){
      if(heap->data[x] > heap->data[leftChild(x)]){
        __swap(heap,x,leftChild(x));
        x=leftChild(x);
      }
      else break;
    }
    else{
      if(heap->data[x] <= min(heap->data[leftChild(x)],heap->data[rightChild(x)]))break;
      if(heap->data[x] > max(heap->data[leftChild(x)],heap->data[rightChild(x)])){
        if(heap->data[leftChild(x)]<heap->data[rightChild(x)]){
          __swap(heap,x,leftChild(x));
          x=leftChild(x);
        }
        else{
          __swap(heap,x,rightChild(x));
          x=rightChild(x);
        } 
      }
      else if(heap->data[x] > heap->data[leftChild(x)]){
        __swap(heap,x,leftChild(x));
        x=leftChild(x);
      }
      else{
        __swap(heap,x,rightChild(x));
        x=rightChild(x);
      }

    }
    
  }

  return toReturn;
  
}
