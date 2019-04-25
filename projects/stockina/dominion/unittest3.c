#include "dominion.h"
#include <stdio.h>
#include <stdlib.h>
#include "rngs.h"
#include "dominion_helpers.h"
#include <string.h>

#define UNIT_TEST "getCost"

int main () {
    int i, r, failed = 0;
    // array of card vales corresponding to card number
    int costs[28] = {0, 2, 5, 8, 0, 3, 6, 6, 5, 4, 4, 5, 4, 4, 3, 4, 3, 5, 3, 5, 3, 4, 2, 5, 4, 4, 4, -1};
    // array of card names corresponding to card number
    char* cards[] = {"curse", "estate", "duchy", "province", "copper", "silver", "gold", "adventurer", "council_room", "feast", "gardens", "mine", "remodel", "smithy", "village", "baron", "great_hall", "minion", "steward", "tribute", "ambassador", "cutpurse", "embargo", "outpost", "salvager", "sea_hag", "treasure_map", "notacard"};

    printf("Testing function %s:\n", UNIT_TEST);

    // brute force test, comparing down the line
    for(i = 0; i < 28; i++){
        if(getCost(i) != costs[i]){
            r = getCost(i);
            printf("Test failed for card %s. The cost is %d, not %d.\n", cards[i], costs[i], r);
            failed = 1;
        }
    }

    if(failed == 1){
        printf("TESTS FAILED\n");
    }
    else{
        printf("All cards matched to their proper cost.\n");
        printf("TESTS PASSED\n\n");
    }
    return 0;
}