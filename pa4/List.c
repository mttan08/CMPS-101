// Matthew Tan
// mxtan
// pa4
// List.c: Doubly-linked list ADT written in C

#include<stdio.h>
#include<stdlib.h>
#include "List.h"

// struct is same as public class Node in java
typedef struct NodeObj
{
    int data;
    struct NodeObj* prev;
    struct NodeObj* next;
}NodeObj;

typedef NodeObj* Node; // private Node type

// struct is same as public class List in java
typedef struct ListObj
{
    Node front;
    Node back;
    Node cursor;
    int index;
    int length;
}ListObj;

// newNode()
// same as Node class constructor
Node newNode(int data)
{
    Node N = NULL;
    N = malloc(sizeof(NodeObj));
    //printf("List.c: newNode(): N malloc address: %p\n", N);
    N->data = data;
    N->prev = NULL;
    N->next = NULL;
    return (N);
}

// freeNode()
// frees heap memory pointed to by *pN, sets *pN to NULL
void freeNode(Node* pN)
{
    //printf("List.c: freeNode(): pN is: %p\n", pN);
    //printf("List.c: freeNode(): *pN is: %p\n", *pN);
    if (pN != NULL && *pN != NULL)
    {
        //printf("!!List.c: freeNode(): free(*pN) called\n");
        free(*pN);
        *pN = NULL;
    }
}

// newList()
// equivalent to List class constructor
List newList(void)
{
    List L = NULL;
    L = malloc(sizeof(ListObj));
    //printf("List.c: newList(): L malloc address: %p\n", L);
    L->front = NULL;
    L->back = NULL;
    L->cursor = NULL;
    L->index = -1;
    L->length = 0;
    return (L);
}

// freeList()
// frees all heap memory associated with List *pL
// and sets *pL to NULL
void freeList(List* pL)
{
    //printf("List.c: freeList() called\n");
    if (pL != NULL && *pL!= NULL) 
    { 
      while (length(*pL) > 0)
      {            
          //printf("List.c: freeList(): deleteFront() called\n");
          deleteFront(*pL);
      }
      //printf("!!List.c: freeList(): free(*pL) called for List\n");
      free(*pL);
      *pL = NULL;
    }
}

// Access functions ---------------------------------------

// length()
// returns the number of elements in this List
int length(List L)
{
    if (L == NULL)
    {
        printf("List Error: calling length() on NULL List reference\n");
        exit(1);
    }
    return L->length;
}

// index()
// returns the index of the cursor of the list
int index(List L)
{
    //printf("List.c: index() is called.\n");
    if (L == NULL)
    {
        printf("List Error: calling index() on NULL List reference\n");
        exit(1);
    }
    if (length(L) <= 0)
    {
        //printf("List.c: index() called: got to if statement.\n");
        return -1;
    }
    //printf("List.c: index(): index is: %d\n", L->index);
    return L->index;
}

// front()
// returns the front element. pre: length() > 0
int front(List L)
{
    if (L == NULL)
    {
        printf("List Error: calling front() on NULL List reference\n");
        exit(1);
    }
    if (length(L) <= 0)
    {
        printf("List Error: front() called on empty List\n");
    }
    return L->front->data;
}

// back()
// returns the back element. pre: length() > 0
int back(List L)
{
    if (L == NULL)
    {
        printf("List Error: calling back() on NULL List reference\n");
        exit(1);
    }
    if (length(L) <= 0)
    {
        printf("List Error: back() called on empty List\n");
    }
    return L->back->data;
}

// get()
// returns the cursor element. pre: length() > 0 and index() > 0
int get(List L)
{
    if (L == NULL)
    {
        printf("List Error: calling get() on NULL List reference\n");
        exit(1);
    }
    if (length(L) <= 0)
    {
        printf("List Error: get() called on empty List\n");
    }
    if (L->cursor == NULL)
    {
        printf("List Error: cannot get() if cursor is NULL\n");
    }
    return L->cursor->data;
}

// equals
// returns 1 if this List and L are the 
// same integer sequence.
//the cursor is ignored in both lists.
int equals(List A, List B)
{
    int eq = 0;
    Node M = NULL;
    Node N = NULL;
    if (A == NULL || B == NULL)
    {
        printf("List Error: calling equals() on NULL List reference\n");
        exit(1);
    }
    eq = (A->length == B->length);
    M = A->front;
    N = B->front;
    while (eq && M != NULL)
    {
        eq = (M->data == N->data);
        M = M->next;
        N = N->next;  
    }
    return eq;
}

// Manipulation procedures ------------------------------------------------

// clear()
// resets this List to its original empty state
void clear(List L)
{
    if (L == NULL)
    {
        printf("List Error: calling clear() on a NULL List reference\n");
        exit(1);
    }
    while (length(L) > 0)
    {            
        //printf("List.c: freeList(): deleteFront() called\n");
        deleteFront(L);
    }
    L->front = NULL;
    L->back = NULL;
    L->cursor = NULL;
    L->index = -1;
    L->length = 0;
}

// moveFront()
// places the cursor to the front of the List
void moveFront(List L)
{
    //printf("List.c: in moveFront(): length is: %d\n", length(L));
    if (L == NULL)
    {
        printf("List Error: calling moveFront() on a NULL List reference\n");
        exit(1);
    }
    if (length(L) > 0)
    {
        L->cursor = L->front;
        L->index = 0;
    }
}

// moveBack()
// places the cursor at the back of the List
void moveBack(List L)
{
    if (L == NULL)
    {
        printf("List Error: calling moveBack() on a NULL List reference\n");
        exit(1);
    }
    if (length(L) > 0)
    {
        L->cursor = L->back;
        L->index = L->length - 1;
    }
}

// movePrev()
// moves cursor one step toward the front of the List
void movePrev(List L)
{
    if (L == NULL)
    {
        printf("List Error: calling movePrev() on a NULL List reference\n");
        exit(1);
    }
    if (L->cursor == L->front)
    {
        L->cursor = NULL;
        L->index = -1;
    }
    else
    {
        L->cursor = L->cursor->prev;
        L->index--;
    }
}

// moveNext()
// moves the cursor one step toward the back of the List
void moveNext(List L)
{
    if (L == NULL)
    {
        printf("List Error: calling moveNext() on a NULL List reference\n");
        exit(1);
    }
    if (L->cursor == L->back)
    {
        L->cursor = NULL;
        L->index = -1;
    }
    else
    {
        L->cursor = L->cursor->next;
        L->index++;
    }
}

// prepend()
// inserts a new element into the front of the List
void prepend(List L, int data)
{
    if (L == NULL)
    {
        printf("List Error: calling prepend() on a NULL List reference\n");
        exit(1);
    }
    Node N = newNode(data);
    if (L->front == NULL)
    {
        L->front = N;
        L->back = N;
        L->cursor = L->front;
    }
    else
    {
        L->front->prev = N;
        N->next = L->front;
        L->front = N;
        L->index++;
    }
    L->length++;
}

// append()
// inserts a new element into the back of the List
void append(List L, int data)
{
    if (L == NULL)
    {
        printf("List Error: calling apend() on a NULL List reference\n");
        exit(1);
    }
    Node N = newNode(data);
    if (L->back == NULL)
    {
        L->front = N;
        L->back = N;
        L->cursor = L->back;
    }
    else
    {
        L->back->next = N;
        N->prev = L->back;
        L->back = N;
        N->next = NULL;
    }
    L->length++;
}

// insertBefore()
// inserts new element before cursor
void insertBefore(List L, int data)
{
    if (L == NULL)
    {
        printf("List Error: calling apend() on a NULL List reference\n");
        exit(1);
    }
    if (L->cursor == NULL)
    {
        printf("List Error: cannot insertBefore()  if cursor is undefined\n");
    }
    if (L->cursor == L->front)
    {
        prepend(L, data);
    }
    else
    {
        Node N = newNode(data);
        N->prev = L->cursor->prev;
        N->next = L->cursor;
        L->cursor->prev->next = N;
        L->cursor->prev = N;
        L->index++;
        L->length++;
    }
}

// insertAfter()
// inserts new element after cursor
void insertAfter(List L, int data)
{
    if (L == NULL)
    {
        printf("List Error: calling apend() on a NULL List reference\n");
        exit(1);
    }
    if (length(L) <= 0)
    {
        printf("List Error: insertAfter() called on empty List\n");
    }
    if (L->cursor == L->back)
    {
        append(L, data);
    }
    else
    {
        Node N = newNode(data);
        L->cursor->next->prev = N;
        N->next = L->cursor->next;
        N->prev = L->cursor;
        L->cursor->next = N;
        L->length++;
    }
}

// deleteFront()
// deletes front element
void deleteFront(List L)
{
    //printf("List.c: deleteFront() called\n");
    if (L == NULL)
    {
        printf("List Error: calling apend() on a NULL List reference\n");
        exit(1);
    }
    if (length(L) <= 0)
    {
        printf("List Error: cannot deleteFront() empty List\n");
    }
    else
    {
        //printf("List.c: freeList(): node is: %d\n", L->front->data);    

        //printf("---------------------------------------\n");
        //printf("List.c: deleteFront(): L->length is: %d\n", L->length);
        //printf("List.c: deleteFront(): L->front is: %p\n", L->front);
        //printf("List.c: deleteFront(): L->cursor is: %p\n", L->cursor);

      if (L->length == 1)
      {
          Node N = L->front;
          //printf("List.c: case 1: deleteFront(): N is: %p\n", N);
          freeNode(&N);
          //printf("List.c: case 1: deleteFront(): front.data is: %d\n", L->front->data);
          L->cursor = NULL;
          L->front = L->back = NULL;
          L->index = -1;
      }
      else //if (L->length > 1)
      {
          Node N = L->front;
          //printf("List.c: case 2: deleteFront(): front.data is: %d\n", L->front->data);
          L->front = L->front->next;
          L->front->prev = NULL;
          if (L->cursor != NULL)
          {
              L->index--;
          }
          freeNode(&N);
      }
      L->length--;
  }
}

// deleteBack()
// deletes back element
void deleteBack(List L)
{
    if (L == NULL)
    {
        printf("List Error: cannot deleteBack() on NULL List reference\n");
    }
    if (length(L) <= 0)
    {
        printf("List Error: cannot deleteBack() empty List\n");
    }
    else
    {
        if (L->length == 1)
        {
            Node N = L->back;
            freeNode(&N);
            L->cursor = NULL;
            L->front = L->back = NULL;
            L->index = -1;
        }
        else //if (L->length > 1)
        {
            Node N = L->back;
            L->back = L->back->prev;
            L->back->next = NULL;
            if (L->index == L->length - 1)
            {
                L->index = -1;
            }
            freeNode(&N);
        }
        L->length--;
        //printf("List.c: deleteBack(): current index is: %d\n", L->index);
    }
}

// delete()
// deletes cursor element
void delete(List L)
{
    if (L == NULL)
    {
        printf("List Error: cannot deleteBack() on NULL List reference\n");
    }
    if (length(L) <= 0)
    {
        printf("List Error: cannot delete() empty List");
    }
    if (L->cursor == NULL)
    {
        printf("List Error: cannot delete() on undefined cursor");
    }
    if (L->cursor == L->front)
    {
        deleteFront(L);
    }
    else if (L->cursor == L->back)
    {
        deleteBack(L);
    }
    else
    {
        Node N = L->cursor;
        L->cursor->prev->next = L->cursor->next;
        L->cursor->next->prev = L->cursor->prev;
        freeNode(&N);
        L->length--;
    }
    L->index = -1;
}

// printList()
// prints contents to a file
void printList(FILE* out, List L) 
{
    Node N = NULL;
    if(L == NULL) 
    {
        printf("List error: printList() called on NULL List reference\n");
        exit(1);
    }
    for (N = L->front; N != NULL; N = N->next) 
    {
        fprintf(out, "%d", N->data);
        fprintf(out, " ");
    }
}

// copyList()
// makes a copty of the List
List copyList(List L)
{
    List C = newList();
    if (length(L) > 0)
    {
        Node N = L->front;
        while (N != NULL)
        {
            append(C, N->data);
            N = N->next;
        }
    }
    C->cursor = NULL;
    C->index = -1;
    return C;
}
