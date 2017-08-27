// Matthew Tan
// mxtan
// pa4
// FindPath.c: finds the shortest
// path in a given graph

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"List.h"
#include"Graph.h"

int main(int argc, char* argv[]) 
{
    // check if argument is 3
    if (argc < 3 || argc > 3) 
    {
        fprintf(stderr, "Usage: %s <input file> <output file>\n", argv[0]);
        exit(1);
    }

    FILE *in = fopen(argv[1], "r");
    FILE *out = fopen(argv[2], "w");

    // if in is NULL quit
    if (in == NULL) 
    {
        printf("Unable to open file %s for reading\n", argv[1]);
        exit(1);
    }
    // if out is NULL quit
    if (out == NULL) 
    {
        printf("Unable to open file %s for writingn\n", argv[2]);
        exit(1);
    }

    int n = 0;
    fscanf(in, "%d", &n); // scan in vertices
    Graph graph = newGraph(n); // create a new graph
    int tmpSrc = 0; // source vertex for graph
    int tmpDest = 0; // destination vertex for graph
    int src = 0;  // source for BFS
    int dst = 0;  // destination for BFS

    // Part 1:

    /* 1. Read and store graph and print out its
     * adjacency list representation.
     */

    // loop through graph and 
    // print out its adjacenty lists
    while (fgetc(in) != EOF) 
    {
        fscanf(in, "%d", &tmpSrc); // scan tmpSrc
        fscanf(in, "%d", &tmpDest); // scan tmpDest
        // if source or destination is 0 don't add to graph
        if (tmpSrc == 0 && tmpDest == 0) 
        {
            break;
        }
        addEdge(graph, tmpSrc, tmpDest);
    }
    printGraph(out, graph); 
    fprintf(out, "\n");

    // Part 2:

    /* 2. Enter a loop and process the second part of input.
     * Each iteration of the loop should read in one pair
     * of vertices (source, destination), run BFS onf the
     * source vertex, print the distance to the the destination
     * vertex, then find and print out the resulting shortest
     * path if it exists, or print a message if no path from
     * source to destination exists.
    */

    // loop through graph using BFS
    // and print out distance from source 
    // to destination and shortest path 
    // between source and destination
    while (fgetc(in) != EOF) 
    {
        //printf("dst is: %d\n", dst);
        fscanf(in, "%d", &src);
        fscanf(in, "%d", &dst);
        // if src and dst == 0 don't count in BFS
        if (src == 0 && dst == 0) 
        {
            break;
        }
        List list = newList();
        BFS(graph, src);
        getPath(list, graph, dst);
        if (getDist(graph, dst) != INF) 
        {
            //printf("getDist() is: %d\n", getDist(graph, dst));
            fprintf(out, "The distance from %d to %d is %d\n", src, 
                   dst, length(list) - 1);
            fprintf(out, "A shortest %d-%d path is: ", src, dst);
            printList(out, list); 
            fprintf(out, "\n\n");
        }
        else 
        {
            fprintf(out, "The distance from %d to %d is infinity\n", 
                 src, dst);
            fprintf(out, "No %d-%d path exists\n\n", 
              src, dst);
        }
        freeList(&list);
    }

    // freeGraph() and close files
    freeGraph(&graph);
    fclose(in); 
    fclose(out);
    return(0);
}
