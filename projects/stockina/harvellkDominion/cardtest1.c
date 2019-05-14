#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>
#include <time.h>
#include "rngs.h"

#define CARD_TEST "adventurer"
#define NUMTRIES 5

int main () {
        int i,failed = 0;
        int k[10] = {adventurer, council_room, feast, gardens, mine,
            remodel, smithy, village, baron, great_hall};
        struct gameState G;
        int card, copper1 = 0, copper2 = 0, gold1 = 0, gold2 = 0, silver1 = 0, silver2 = 0;
        int cards, cards1, total1 = 0, total2 = 0;
        
        initializeGame(2, k, 1, &G);

        printf("Testing %s:\n", CARD_TEST);

        // add adventurer to hand
        G.hand[0][0] = cutpurse;
        G.hand[0][1] = embargo;
        G.hand[0][2] = ambassador;
        G.hand[0][3] = smithy;
        G.hand[0][4] = adventurer;
        G.deck[0][0] = gold;
        G.deck[0][1] = silver;
        G.deck[0][2] = copper;
        G.deck[0][3] = smithy;
        G.deck[0][4] = great_hall;

        cards = G.handCount[0];
        printf("The player's hand has %d cards.\n", cards);

        // inspect hand
        for(i = 0; i < G.handCount[0]; i++){
            card = G.hand[0][i];
            if(card == copper){
                copper1++;
                total1++;
            }
            if(card == silver){
                silver1++;
                total1++;
            }
            if(card == gold){
                gold1++;
                total1++;
            }
        }
        printf("Player's original hand: %d gold, %d silver, %d copper makes %d coins.\n", gold1, silver1, copper1, total1);

        // play card
        cardEffect(adventurer, 0, 0, 0, &G, G.hand[0][G.handCount[0] - 1], 0);

        // count coin cards in hand after play
        for(i = 0; i < G.handCount[0]; i++){
            card = G.hand[0][i];
            if(card == copper){
                copper2++;
                total2++;
            }
            if(card == silver){
                silver2++;
                total2++;
            }
            if(card == gold){
                gold2++;
                total2++;
            }
        }

        cards1 = G.handCount[0]; 

        // print results
        printf("Player's hand after adventurer: %d gold, %d silver, %d copper makes %d coins.\n", gold2, silver2, copper2, total2);
        printf("The players hand is now %d cards.\n", cards1);
        total1 += 2;
        if(total1 == total2 && (cards + 2) == cards1){
            printf("Test passed. Player drew 2 coin cards.\n");
        }
        else{
            printf("Test failed. Player did not draw 2 coin cards. \n");
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