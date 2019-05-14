#include "dominion.h"
#include <stdio.h>
#include <stdlib.h>
#include "rngs.h"
#include "dominion_helpers.h"
#include <string.h>

#define UNIT_TEST "isGameOver"

int main () {
    struct gameState G;
    int i, r, failed = 0;
    int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, 
	       sea_hag, tribute, smithy};
  
    initializeGame(2, k, 1, &G);

    printf("Testing function %s:\n", UNIT_TEST);

    // intial test for start of game, which cannot also be the end
    printf("Province supply count is: %d\n", G.supplyCount[province]);
    r = isGameOver(&G);
    if(r == 0){
        printf("Test 1 passed. The game is not over.\n");
    }
    else{
        printf("Test 1 failed. The game is not over.\n");
        failed = 1;
    }

    // now test if province supply > 0 but supply count is not
    G.supplyCount[province] = 5;
    for(i = 0; i < 3; i++){
        G.supplyCount[i] = 0;   // set 3 supply count to 0 so j >= 3

        // supply not yet low enough
        if(i == 1){
            r = isGameOver(&G);
            if(r == 0){
                printf("Test 2 passed. There is still enough supply count.\n");
            }
            else{
                printf("Test 2 failed. The supply count is still high.\n");
                failed = 1;
            }
        }
        // supply low, cannot continue
        if(i == 2){
            r = isGameOver(&G);
            if(r == 1){
                printf("Test 3 passed. The game is over.\n");
            }
            else{
                printf("Test 3 failed. The supply is too low to continue.\n");
                failed = 1;
            }
        }
    }
    // if province supply is 0
    G.supplyCount[province] = 0;
    r = isGameOver(&G);
    if(r == 1){
        printf("Test 4 passed. The game is over.\n");
    }
    else{
        printf("Test 4 failed. The supply is too low to continue.\n");
        failed = 1;
    }

    if(failed == 1){
        printf("TESTS FAILED\n\n");
    }
    else{
        printf("TESTS PASSED\n\n");
    }
    return 0;
}