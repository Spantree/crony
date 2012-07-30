lexer grammar TimeLists;
options {filter=true;}

import CommonDateFormats;

@header {
package net.spantree.crony.grammars;
}

WEEK_DAY_MONTH_DAY_TIME_LIST_STRING
    : WEEK_DAY_MONTH_DAY_TIME_LIST
    {System.out.println("found weekday, month day time list "+getText());}
    ;
    
WEEK_DAY_MONTH_DAY_LIST_STRING
    : WEEK_DAY_MONTH_DAY_LIST
    {System.out.println("found weekday, month day list "+getText());}
    ;
    
TIME_LIST_STRING
    : TIME_LIST
    {System.out.println("found time list "+getText());}
    ;
    
NUMBER_LIST_STRING
    : NUMBER_LIST
    {System.out.println("found number list "+getText());}
  ;

fragment
WEEK_DAY_MONTH_DAY_TIME_LIST: (WEEK_DAY_MONTH_DAY_TIME ' '* AND ' '* WEEK_DAY_MONTH_DAY_TIME);

fragment
WEEK_DAY_MONTH_DAY_LIST: (WEEK_DAY_MONTH_DAY ' '* AND ' '* WEEK_DAY_MONTH_DAY);

fragment
TIME_LIST: (NUMBER ' '* AND ' '* TIME);

fragment
NUMBER_LIST:  (NUMBER ' '* AND ' '* NUMBER (' '* AND ' '* NUMBER)*);
  
fragment
AND: A N D | '&' | ',';