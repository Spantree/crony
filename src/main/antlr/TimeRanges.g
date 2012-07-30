lexer grammar TimeRanges;
options {filter=true;}

import CommonDateFormats;

@header {
package net.spantree.crony.grammars;
}

WEEK_DAY_TIME_RANGE_STRING
   : WEEK_DAY_TIME_RANGE
      {System.out.println("found week day time range "+getText());}
   ;
   
WEEK_DAY_RANGE_STRING
   : (WEEK_DAY_RANGE | (UNTIL ' '* WEEK_DAY_STRING))
      {System.out.println("found week day range "+getText());}
   ;
   
MONTH_DAY_RANGE_STRING
   : (MONTH_DAY_RANGE | (UNTIL ' '* MONTH_DAY_STRING))
      {System.out.println("found month day range "+getText());}
   ;
   
   
TIME_RANGE_STRING
    :(TIME_RANGE | (UNTIL ' '* TIME_STRING))
      {System.out.println("found time range "+getText());}
   ;
   
fragment
MONTH_DAY_RANGE: (MONTH_DAY ' '* '-' ' '* NUMBER) | (MONTH_DAY ' '* '-' ' '* MONTH_DAY);

fragment
WEEK_DAY_TIME_RANGE: (WEEK_DAY ' '* TIME_RANGE);

fragment
WEEK_DAY_RANGE: (WEEK_DAY ' '* '-' ' '* WEEK_DAY);

fragment
TIME_RANGE: (TIME ' '* '-' ' '* TIME);

fragment
UNTIL: U N T I L | T H R O U G H;