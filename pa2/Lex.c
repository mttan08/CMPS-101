//Matthew Tan
// mxtan
// pa2
// Lex.c: Returns and outputfile of sorted items

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"List.h"
#define MAX_LEN 160

int main(int argc, char * argv[])
{
    int lineNum = 0, countLines = 0;
    FILE *in, *out;
    char str[MAX_LEN];
    char** strArray;
    if (argc < 3 || argc > 3)
    {
        printf("Usage: %s <input file> <output file>\n", argv[0]);
        exit(1);
    }

    in = fopen(argv[1], "r");
    out = fopen(argv[2], "w");
    if (in == NULL)
    {
        printf("Unable to open file %s for reading\n", argv[1]);
        exit(1);
    }
    if (out == NULL)
    {
        printf("Unable to open file %s for writing\n", argv[2]);
        exit(1);
    }
  
    while (fgets(str, MAX_LEN, in) != NULL)
    {
        //printf("countlines: Got here!\n");
        //printf("str is: %s", str);
        countLines++;
    }
    //printf("\n");
    //printf("number of lines: %d\n", countLines);

    rewind(in);

    strArray = malloc((countLines) * sizeof(char*));
    for (int i = 0; i < countLines; i++)
    {
        strArray[i] = malloc((MAX_LEN)* sizeof(char));
        //printf("make array: Got here!\n");
        //printf("strArray[i] is: %s\n", strArray[i]);
    }

    while (fgets(str, sizeof(str), in) != NULL)
    {
        //printf("-----------------------------------\n");
        //printf("lineNum is: %d\n", lineNum);
        //printf("str is: %s", str);
        //printf("copy array: Got here!\n");
        //printf("lineNumber in loop is: %d\n", lineNum);
        //printf("strArray is: %p\n", strArray[lineNum]);
        //memset(strArray[lineNum], '\0', sizeof(MAX_LEN));
        strcpy(strArray[lineNum], str);
        //printf("strArray[lineNum] is: %s", strArray[lineNum]);
        lineNum++;
    }

    List list = newList();
    append(list, 0);
    for (int i = 1; i < countLines; i++)
    {
        moveFront(list);
        char* element = strArray[i];
        int j = i;
        while (j > 0 && strcmp(element, strArray[get(list)]) >= 0)
        {
            moveNext(list);
           j--;
        }
        if (index(list) >= 0)
        {
            insertBefore(list, i);
        }
        else
        {
            append(list, i);
        }
    }
  
    moveFront(list);
    while (index(list) >= 0)
    {
        fprintf(out, "%s", strArray[get(list)]);
        moveNext(list);
    }

    fclose(in);
    fclose(out);
  
    freeList(&list);
    for (int i = 0; i < countLines; i++)
    {
        free(strArray[i]);
    }
    free(strArray);  

    return(0);
}
