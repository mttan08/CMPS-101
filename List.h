// Matthew Tan
// mxtan
// pa4
// List.h: Header file for List ADT

#ifndef _LIST_H_INCLUDE_
#define _LIST_H_INCLUDE_

// exported type ---------------------------------
typedef struct ListObj* List;

// constructors-destructors ----------------------
List newList(void);
void freeList(List* pL);

// access functions ------------------------------

// length()
// returns the length of the List
int length(List L);

// index()
// returns the index of the List
int index(List L);

// front()
// returns the front element of the List
int front(List L);

// back()
// retuns the back element of the List
int back(List L);

// get()
// gets element of cursor
// pre: length > 0
int get(List L);

// equals()
// returns if a List instance of another
int equals(List A, List B);

// manipulation procedures ------------------------

// clear()
// reset List to empty state
void clear(List L);

// moveFront()
// moves the cursor to the front of the List
void moveFront(List L);

// moveBack()
// moves the cursor to the back of the List
void moveBack(List L);

// movePrev()
// moves the cursor one previous of the List
void movePrev(List L);

// moveNext()
// moves the cursor one next of the List
void moveNext(List L);

// prepend()
// prepends data to the front of the List
void prepend(List L, int data);

// append()
// appends data to the end of the List
void append(List L, int data);

// insertBefore()
// inserts before the cursor element of the List
void insertBefore(List L, int data);


// insertAfter()
// inserts after the cursor element of the List
void insertAfter(List L, int data);

// deleteFront()
// deletes front element of the List
void deleteFront(List L);

// deleteBack()
// deletes back element of the List
void deleteBack(List L);


// delete()
// deletes cursor element of the List
void delete(List L);


// other operations ----------------------------------

// printList()
// prints list to file
void printList(FILE* out, List L);


// List copyList(List L)
// makes a copy of the List
List copyList(List L);

#endif
