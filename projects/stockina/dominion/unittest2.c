#include "dominion.h"
#include <stdio.h>
#include <stdlib.h>
#include "rngs.h"
#include "dominion_helpers.h"
#include <string.h>

#define UNIT_TEST "whoseTurn"

int main () {

    int i, r, failed = 0;
    struct gameState G;
    int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, 
	       sea_hag, tribute, smithy};

    printf("Testing function %s:\n", UNIT_TEST);

    // checking that G.whoseTurn and whoseTurn() match
    for(i = 0; i < 5; i++){
        G.whoseTurn = i;
        r = whoseTurn(&G);
        if(r == i){
            printf("Test passed. It is player %d's turn. \n", r);
        }
        else{
            printf("Test failed. Expected: %d\n", i);
            failed = 1;
        }
    }
  
    initializeGame(4, k, 1, &G);

    for(i = 0; i < 4; i++){
        r = whoseTurn(&G);
        if(r != G.whoseTurn){
            printf("Test failed.\n");
            failed = 1;
        }
        else{
            printf("Test passed.\n");
        }
    }

    if(failed == 1){
        printf("TESTS FAILED\n\n");
    }
    else{
        printf("TESTS PASSED\n\n");
    }
    return 0;
}