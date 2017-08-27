// Matthew Tan
// mxtan
// pa4
// Graph.h: header file for Graph.c

#ifndef _GRAPH_H_INCLUDE_
#define _GRAPH_H_INCLUDE_
#define INF -1
#define NIL -2
#include"List.h"

typedef struct GraphObj* Graph; // exported type

/*** Constructors-Destructors ***/

// newGraph()
// creates a new graph
Graph newGraph(int n);

// freeGraph()
// frees all memory related
// to the graph
void freeGraph(Graph* pG);

/*** Access functions ***/

// getOrder()
// get the number of vertices
// of the graph
int getOrder(Graph G);

// getSize()
// get number of edges
// of the graph
int getSize(Graph G);

// getSource()
// gets the source vertex
// of the graph
int getSource(Graph G);

// getParent()
// gets parent of the current vertex
int getParent(Graph G, int u);

// getDist()
// returns distance from most
// recent BFS search to vertex u
// or INF if BFS() has not been called
int getDist(Graph G, int u);

// getPath()
// appends to List L vertices
// of the shortest path in G from
// source to u or appends L to NIL
// if no path exists
void getPath(List L, Graph G, int u);

/*** Manipulation procedures ***/

// makeNull()
// makes the graph NULL
void makeNull(Graph G);

// addEdge()
// adds edge to graph
void addEdge(Graph G, int u, int v);

// addArc()
// adds arc to graph
void addArc(Graph G, int u, int v);
void BFS(Graph G, int s);

/*** Other operations ***/

// printGraph()
// prints graph to stdout or file
void printGraph(FILE* out, Graph G);

#endif
