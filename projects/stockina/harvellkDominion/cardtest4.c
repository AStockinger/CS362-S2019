#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"

#define CARD_TEST "great hall"

int main () {
    int failed = 0;
    int before, before1, after, after1;
    int k[10] = {adventurer, council_room, feast, gardens, mine,
	       remodel, smithy, village, baron, great_hall};
    struct gameState G;
    
    initializeGame(4, k, 1, &G);

    printf("Testing %s:\n", CARD_TEST);

    // add card to hand
    G.hand[0][G.handCount[0]] = great_hall;
    before = ++G.handCount[0];
    before1 = G.numActions;

    // inspect hand
    printf("Player's original hand has %d cards.\n", G.handCount[0]);

    // play card
    cardEffect(great_hall, 0, 0, 0, &G, G.hand[0][G.handCount[0] - 1], 0);

    after = G.handCount[0];
    after1 = G.numActions;

    if(before == after){
        printf("Test passed. The player gained one card and discarded Great Hall.\n");
    }
    else{
        printf("Test failed. The player did not gain a card.\n");
        failed = 1;
    }

    if((before1 + 1) == after1){
        printf("Test passed. The player gained 1 actions.\n");
    }
    else{
        printf("Test failed. The player did not gain one actions.\n");
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