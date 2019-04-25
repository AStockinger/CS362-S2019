#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include "rngs.h"

#define CARD_TEST "smithy"

int main () {

    int i, card, before, after, failed = 0;
    int k[10] = {adventurer, council_room, feast, gardens, mine,
	       remodel, smithy, village, baron, great_hall};
    struct gameState G;
    
    initializeGame(4, k, 1, &G);

    printf("Testing %s:\n", CARD_TEST);

    // add card to hand
    G.hand[0][0] = gold;
    G.hand[0][1] = gold;
    G.hand[0][2] = gold;
    G.hand[0][3] = copper;
    G.hand[0][4] = copper;
    G.hand[0][5] = copper;
    G.hand[0][6] = silver;
    G.hand[0][7] = silver;
    G.hand[0][8] = silver;
    G.hand[0][9] = smithy;
    G.deck[0][0] = adventurer;
    G.deck[0][1] = cutpurse;
    G.deck[0][2] = treasure_map;
    G.deck[0][3] = embargo;
    G.deck[0][4] = great_hall;
    G.deck[0][5] = gold;

    G.handCount[0] = 10;

    before = G.handCount[0];

    // inspect hand
    printf("Player's original hand has %d cards.\n", G.handCount[0]);

    // play card
    cardEffect(smithy, 0, 0, 0, &G, G.hand[0][9], 0);

    after = G.handCount[0];

    for(i = 0; i < G.handCount[0]; i++){
        card = G.hand[0][i];
        if(card == smithy){
            printf("Smithy was not discarded. Test failed.\n");
            failed = 1;
        }
    }

    // check that 3 cards were gained and smithy was discarded, total gain of 2
    if(after == (before + 2)){
        printf("Test passed! The player gained 3 cards and discarded Smithy.\n");
    }
    else{
        printf("Test Failed. The player has %d cards, but should have %d.\n", after, before + 2);
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