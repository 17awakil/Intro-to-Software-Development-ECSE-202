//imports needed for program
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXBUF 100
/*
 * Student Name: Andre Wakil
 * ID: 260777355
 * */

/*bTree struct declared and initialized as a single instruction*/
struct bTree{
    char* val;
    struct bTree* left;
    struct bTree* right;
};
/*Function that creates a single bTree and returns its pointer*/
struct bTree* createbTree(char *name){
    //dynamic allocation of a bTree struct using malloc
    struct bTree* newTree=(struct bTree*)malloc(sizeof(struct bTree));
    //use of strdup() function to duplicate name into val
    newTree->val=strdup(name);
    //use of -> operator to dereference newTree and access val, left, right (equivalent to (*newVal).val etc.)
    newTree->left=NULL;
    newTree->right=NULL;
    return newTree;
}
/*addNode fucntion gets passed a bTree reference and a char reference to add a bTree to its correct location in the bTree*/
void addNode(struct bTree *root, char *name){
    //Use of strcmp() function to compare root->val to name for alphabetical precedence
    //new Nodes are added correspondingly to the binary tree
    if(strcmp(root->val,name)>0){
        if(root->left==NULL){
            root->left=createbTree(name);
        }
        else{
            addNode(root->left,name);
        }

    }
    else if(strcmp(root->val,name)<0){
        if(root->right==NULL){
            root->right=createbTree(name);
        }
        else{
            addNode(root->right,name);
        }
    }

}
/*Inorder traversal of the bTree*/
void traverseInorder(struct bTree *tree){
    /*recursive algorithm that prints in left, root, right order*/
    if(tree->left!=NULL){
        traverseInorder(tree->left);
    }
    printf("%s",tree->val);
    if(tree->right!=NULL){
        traverseInorder(tree->right);
    }
    }
/*getline function that is used to parse user input*/
ssize_t getline(char **lineptr, size_t *n, FILE *stream) {
    char *bufptr = NULL;
    char *p = bufptr;
    size_t size;
    int c;

    if (lineptr == NULL) {
        return -1;
    }
    if (stream == NULL) {
        return -1;
    }
    if (n == NULL) {
        return -1;
    }
    bufptr = *lineptr;
    size = *n;

    c = fgetc(stream);
    if (c == EOF) {
        return -1;
    }
    if (bufptr == NULL) {
        bufptr = malloc(128);
        if (bufptr == NULL) {
            return -1;
        }
        size = 128;
    }
    p = bufptr;
    while(c != EOF) {
        if ((p - bufptr) > (size - 1)) {
            size = size + 128;
            bufptr = realloc(bufptr, size);
            if (bufptr == NULL) {
                return -1;
            }
        }
        *p++ = c;
        if (c == '\n') {
            break;
        }
        c = fgetc(stream);
    }

    *p++ = '\0';
    *lineptr = bufptr;
    *n = size;

    return p - bufptr - 1;
}
/*main function*/
int main(int argc, char *argv[]) {
// Internal declarations
    FILE * FileD;			// File descriptor (an object)!
    char *line;				// Pointer to buffer used by getline function
    int bufsize = MAXBUF;	// Size of buffer to allocate
    int linelen;				// Length of string returned (getline)

// Argument check
    if (argc != 2) {
        printf("Usage: fileReader [text file name]\n");
        return -1;
    }

// Attempt to open the user-specified file.  If no file with
// the supplied name is found, exit the program with an error
// message.

    if ((FileD=fopen(argv[1],"r"))==NULL) {
        printf("Can't read from file %s\n",argv[1]);
        return -2;
    }

// Allocate a buffer for the getline function to return data to.

    line=(char *)malloc(bufsize+1);
    if (line==NULL) {
        printf("Unable to allocate a buffer for reading.\n");
        return -3;
    }
    //declaration of struct bTree pointer called myBtree
    struct bTree* myBTree;

    printf("Assignment 6 - File Sorting Program:\nEnter name of files to sort: %s\n\n",argv[1]);
    printf("File in sort order:\n\n");

    //counter i used to distinguish the code for the first iteration of the while loop and the rest of the iterations
    int i=0;
   while ((linelen=getline(&line,(size_t *)&bufsize,FileD))>0){
       //for first iteration, initialize myBTree to be a single assigned struct bTree pointer returned by createbTree()
       if(i==0){
           myBTree=createbTree(line);
       }
           //for all other iterations, use addNode();
       else{
           addNode(myBTree,line);
       }
       i++;
   }

    //traverseInorder called
    traverseInorder(myBTree);
    printf("\nProgram terminated\n");

    return EXIT_SUCCESS;
}
