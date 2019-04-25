#include "dominion.h"
#include <stdio.h>
#include <stdlib.h>
#include "rngs.h"
#include "dominion_helpers.h"
#include <string.h>

#define UNIT_TEST "updateCoins"

int main () {
    struct gameState G;
    int r, failed = 0;
    int k[10] = {adventurer, gardens, embargo, village, minion, mine, cutpurse, 
	       sea_hag, tribute, smithy};
  
    initializeGame(2, k, 1, &G);

    printf("Testing function %s:\n", UNIT_TEST);

    // variety of gold/silver/copper = 18 coins
    G.hand[0][0] = gold;
    G.hand[0][1] = gold;
    G.hand[0][2] = gold;
    G.hand[0][3] = copper;
    G.hand[0][4] = copper;
    G.hand[0][5] = copper;
    G.hand[0][6] = silver;
    G.hand[0][7] = silver;
    G.hand[0][8] = silver;
    G.hand[0][9] = adventurer; 

    G.handCount[0] = 10;

    r = updateCoins(0, &G, 0);
    if(G.coins == 18){
        printf("Test Passed. The correct number of coins is 18.\n");
    }
    else{
        printf("Test failed. We should have 18 coins, but we have %d\n", r);
        failed = 1;
    }

    // no coins
    G.hand[0][0] = smithy;
    G.hand[0][1] = smithy;
    G.hand[0][2] = cutpurse;
    G.hand[0][3] = cutpurse;
    G.hand[0][4] = treasure_map;
    G.hand[0][5] = treasure_map;
    G.hand[0][6] = embargo;
    G.hand[0][7] = embargo;
    G.hand[0][8] = adventurer;
    G.hand[0][9] = adventurer; 

    G.handCount[0] = 10;

    r = updateCoins(0, &G, 0);
    if(G.coins == 0){
        printf("Test Passed. The correct number of coins is 0.\n");
    }
    else{
        printf("Test failed. We should have 0 coins, but we have %d\n", r);
        failed = 1;
    }

    // bonus
    G.hand[0][0] = smithy;
    G.hand[0][1] = smithy;
    G.hand[0][2] = cutpurse;
    G.hand[0][3] = cutpurse;
    G.hand[0][4] = treasure_map;
    G.hand[0][5] = treasure_map;
    G.hand[0][6] = embargo;
    G.hand[0][7] = embargo;
    G.hand[0][8] = adventurer;
    G.hand[0][9] = adventurer; 

    G.handCount[0] = 10;

    r = updateCoins(0, &G, 4);
    if(G.coins == 4){
        printf("Test Passed. The correct number of coins is 4.\n");
    }
    else{
        printf("Test failed. We should have 4 coins, but we have %d\n", r);
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