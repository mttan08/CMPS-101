// Matthew Tan
// mxtan
// pa5
// GraphTest.c: test client for Graph ADT

#include<stdio.h>
#include<stdlib.h>
#include "List.h"
#include "Graph.h"

int main(int argc, char* argv[])
{
    Graph A = newGraph(6);
    List S = newList();
    Graph T = NULL;
    Graph C = NULL;

    for (int i = 1; i <= 6; i++)
    {
        append(S, i);
    }    

    addEdge(A, 1, 5);
    addEdge(A, 1, 2);
    addEdge(A, 2, 3);
    addEdge(A, 2, 5);
    addEdge(A, 3, 4);
    addEdge(A, 4, 5);
    addEdge(A, 4, 6);
    printGraph(stdout, A);
    printf("\n");

    printf("Graph order: %d\n", getOrder(A));
    printf("Graph size: %d\n", getSize(A));

    printf("\n");

    printf("DFS on regular is: \n");
    DFS(A, S);
    printf("\n");
    printf("x:  d  f  p\n");
    for (int i = 1; i <= 6; i++)
    {
        printf("%d: %2d %2d %2d\n", i, getDiscover(A, i), getFinish(A, i),
               getParent(A, i));
    }
    printf("\n");
    printList(stdout, S);
    printf("\n\n");

    addArc(A, 1, 5);
    addArc(A, 2, 5);
    addArc(A, 3, 4);
    addArc(A, 4, 6);
    addArc(A, 5, 4);
    printf("Graph order: %d\n", getOrder(A));
    printf("Graph size: %d\n", getSize(A));

    T = transpose(A);
    C = copyGraph(A);
    printf("\n");
    printGraph(stdout, C);
    printf("\n");
    printGraph(stdout, T);
    printf("\n");

    printf("DFS on transpose is:\n");
    DFS(T, S);
    printf("\n");
    printf("x:  d  f  p\n");
    for (int i = 1; i <= 6; i++)
    {
        printf("%d: %2d %2d %2d\n", i, getDiscover(T, i), getFinish(T, i),
               getParent(T, i));
    }
    printf("\n");
    printList(stdout, S);
    printf("\n");

    freeList(&S);
    freeGraph(&A);
    freeGraph(&T);
    freeGraph(&C);

    return (0);
}
