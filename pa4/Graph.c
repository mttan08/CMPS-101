// Matthew Tan
// mxtan
// pa4
// Graph.c: Graph ADT

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "Graph.h"

#define WHITE 0
#define GRAY 1
#define BLACK 2

// struct is same as public class Graph
typedef struct GraphObj
{
    List* neighbors;
    int* color;
    int* parent;
    int* distance;

    int order; // number of vertices
    int size; // number of edges
    int source; // source of the tree for BFS
}GraphObj;

typedef struct GraphObj* Graph; // private Graph type

/*** Constructors-Destructors ***/

// newGraph()
// creats a new graph 'object'
Graph newGraph(int n)
{
    Graph G = malloc(sizeof(GraphObj));
    //printf("in newGraph(): G malloc address: %p\n", G);
    G->neighbors = calloc(n + 1, sizeof(List));
    G->color = calloc(n + 1, sizeof(int));
    G->parent = calloc(n + 1, sizeof(int));
    G->distance = calloc(n + 1, sizeof(int));
    
    G->order = n;
    G->size = 0;
    G->source = NIL;
    for (int i = 1; i <= n; i++)
    {
        G->neighbors[i] = newList();
        G->color[i] = WHITE;
        G->parent[i] = NIL;
        G->distance[i] = INF;
    }
    return G;
}

// freeGraph()
// frees all the memory allocated
// for the graph
void freeGraph(Graph* pG)
{
    Graph ptr = *pG;
    //printf("in freeGraph(): ptr is: %p\n", ptr);
    //printf("in freeGraph(): *pG is: %p\n", *pG);
    // free list first
    for(int i = 1; i <= getOrder(ptr); i++)
    {
        freeList(&(ptr->neighbors[i]));
    }

    // free rest and set *pG to NULL
    free(ptr->neighbors);
    free(ptr->color);
    free(ptr->parent);
    free(ptr->distance);

    // finally free *pG
    //printf("in freeGraph(): free(*pG) called!!\n");
    free(*pG);
    *pG = NULL;
}

/*** Access functions ***/

// getOrder()
// gets the number of vertices
// in the graph
int getOrder(Graph G)
{
    if (G == NULL)
    {
        printf("Graph error: cannot getOrder() on NULL Graph reference\n");
        exit(1);
    }
    return G->order;
}

// getSource()
// gets the source vertex
int getSource(Graph G)
{
    if (G == NULL)
    {
        printf("Graph error: cannot getSource() on NULL Graph reference\n");
        exit(1);
    }
    return G->source;
}

// getSize()
// returns the number of vertices
// in the graph
int getSize(Graph G)
{
    if (G == NULL)
    {
        printf("Graph error: cannot getSize() on NULL graph reference\n");
        exit(1);
    }
    return G->size;
}

// getParent()
// returns the parent of the graph
// pre: 1<=u<=getOrder(G)
int getParent(Graph G, int u)
{
    if (G == NULL)
    {
        printf("Graph error: cannot getParent() on NULL Graph reference\n");
        exit(1);
    }
    if (!(1 <= u && u <= getOrder(G)))
    {
        printf("Graph error: cannot getParent() if u is out of bounds\n");
        exit(1);
    }
    return G->parent[u];
}

// getDist()
// returns the distance from parent
// and INF otherwise if getSource() is NIL
// pre: 1<=u<=getOrder(G)
int getDist(Graph G, int u) 
{
    //printf("in getDist: G->distance[u] is: %d\n", G->distance[u]);
    if (G == NULL) 
    {
        printf("Graph error: cannot getDist() on NULL Graph reference\n");
        exit(1);
    }
    if (!(1 <= u && u <= getOrder(G)))
    {
        printf("Graph error: cannot getDist() if u is out of bounds\n");
        exit(1);
    }
    if(getSource(G) == NIL) 
    {
        return INF;
    }
    return G->distance[u];
}

// getPath()
// returns the shortest path in G to source u
// or appends L to the value NIL if no such
// path exsists
// pre: 1<=u<=getSource(G);
void getPath(List L, Graph G, int u)
{
    //printf("in getPath: u is: %d\n", u);
    if (G == NULL)
    {
        printf("Graph error: cannot getPath() on NULL Graph reference\n");
        exit(1);
    }
    if (!(1 <= u && u <= getOrder(G)))
    {
        printf("Graph error: cannot getPath() if u is out of bounds\n");
        exit(1);
    }
    if (u == getSource(G))
    {
        append(L, getSource(G));
    }
    else if (G->parent[u] == NIL)
    {
        append(L, NIL);
    }
    else
    {
        getPath(L, G, G->parent[u]);
        append(L, u);
    }
}

/*** Manipulation procedures ***/

// makeNull()
// returns a NULL graph reference
void makeNull(Graph G)
{
    //printf("in makeNull(): G->size is: %d\n", G->size);
    if (G == NULL)
    {
        printf("Graph error: cannot makeNull() on NULL Graph reference\n");
    }
    // run through a loop clearing the graph
    // and its adjacency lists
    for (int i = 1; i <= getOrder(G); i++)
    {
        //printf("in makeNull(): G->neighbors[i] is: %p\n", G->neighbors[i]);
        clear(G->neighbors[i]);
    }
    G->size = 0;
}

// addSortedEdge()
// helper for addEdge() in sorted order
// by using a modified insertion sort algorithm
void addSortedEdge(List L, int v)
{
    //printf("in addSortedEdge(): v is: %d\n", v);
    if (L == NULL)
    {
        printf("cannot addSortedEdge() on NULL List reference\n");
        exit(1);
    }
    if (length(L) == 0)
    {
        append(L, v);
        return;
    }    
    moveFront(L);
    while (index(L) != -1)
    {
        if (v < get(L))
        {
            insertBefore(L, v);
            break;
        }
        moveNext(L);
    }
    if (index(L) == -1)
    {
        append(L, v);
    }
}

// addEdge()
// adds an (undirected) edge to the current graph
void addEdge(Graph G, int u, int v)
{
    //printf("in addEdge(): u is: % d, v is: %d\n", u, v);
    if (G == NULL)
    {
        printf("Graph error: cannot addEdge() on NULL Graph reference\n");
        exit(1);
    }
    addSortedEdge(G->neighbors[u], v);
    addSortedEdge(G->neighbors[v], u);
    G->size++;
}

// addArc()
// adds directed edge to graph
void addArc(Graph G, int u, int v)
{
    if (G == NULL)
    {
        printf("Graph error: cannot addArc() on NULL Graph reference\n");
        exit(1);
    }
    addSortedEdge(G->neighbors[u], v);
    G->size++;
}

// BFS()
// implements the breadth first search (BFS)
// algorithm shown in class
void BFS(Graph G, int s)
{
    for (int x = 1; x <= getOrder(G); x++) 
    {
        //printf("in BFS: x is: %d\n", x);
        G->color[x] = WHITE;
        G->distance[x] = INF;
        G->parent[x] = NIL;
    }
    G->source = s;
    G->color[s] = GRAY;
    G->distance[s] = 0;
    G->parent[s] = NIL;
    List L = newList();
    append(L, s);
    //printf("in BFS: length(L) is: %d\n", length(L));
    while(length(L) > 0) 
    {
        int x = front(L);
        deleteFront(L);
        List getNeighbors = G->neighbors[x];
        moveFront(getNeighbors);
        while (index(getNeighbors) != -1) 
        {
            int y = get(getNeighbors);
            if (G->color[y] == WHITE) 
            {
                G->color[y] = GRAY;
                G->distance[y] = G->distance[x] + 1;
                G->parent[y] = x;
                append(L, y);
            }
            moveNext(getNeighbors);
        }
        G->color[x] = BLACK;
    }
    freeList(&L);
}

/*** Other operations ***/

// printGraph()
// prints a graph to a file
// or stdout
void printGraph(FILE* out, Graph G)
{
    if (G == NULL)
    {
        printf("Graph error: printGraph() called on NULL Graph reference\n");
        exit(1);
    }
    for (int i = 1; i <= getOrder(G); i++)
    {
        //printf("int printGraph(): i is: %d\n", i);
        List list = G->neighbors[i];
        fprintf(out, "%d:", i);
        moveFront(list);
        //printf("printGraph(): index is: %d\n", index(list));
        while (index(list) != -1)
        {
            fprintf(out, " %d", get(list));
            moveNext(list);
        }
        fprintf(out, "\n");
    }
}
