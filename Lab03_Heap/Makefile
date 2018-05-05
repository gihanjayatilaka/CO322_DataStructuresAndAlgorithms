CFLAGS=-Wall
CC=gcc

all: heap_test

heap_test: main.o heap.o 
	$(CC) $^ -o $@

clean:
	rm -f *.o *~

realclean: clean
	rm -f heap_test

run: heap_test
	echo "Running application ..."
	./heap_test


