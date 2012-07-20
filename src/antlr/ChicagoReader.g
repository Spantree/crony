lexer grammar ChicagoReader;
options {filter=true;}

@header {
package net.spantree.crony.grammars;
}

WEEK_DAY_RANGE_STRING
   : (WEEK_DAY_RANGE | (UNTIL ' '* WEEK_DAY_STRING))
      {System.out.println("found week day range "+getText());}
   ;
   
WEEK_DAY_STRING
    : WEEK_DAY
      {System.out.println("found weekday "+getText());}
      ;

MONTH_DAY_RANGE_STRING
   : (MONTH_DAY_RANGE | (UNTIL ' '* MONTH_DAY_STRING))
      {System.out.println("found month day range "+getText());}
   ;
   
MONTH_DAY_STRING
    : MONTH_DAY
      {System.out.println("found month day "+getText());}
    ;

MONTH_STRING
   : MONTH
      {System.out.println("found month "+getText());}
   ;

TIME_MULTI_STRING
    : TIME_MULTI
      {System.out.println("found multiple times "+getText());}
   ;
   
TIME_RANGE_STRING
    :(TIME_RANGE | (UNTIL ' '* TIME_STRING))
      {System.out.println("found time range "+getText());}
   ;
   
TIME_STRING
   : TIME
      {System.out.println("found time string "+getText());}
   ;

NUMBER_STRING
   : NUMBER
      {System.out.println("found number "+getText());}
   ;


fragment
MONTH_DAY: (MONTH ' '* NUMBER) | (NUMBER ' '* '/' ' '* NUMBER);

fragment
MONTH_DAY_RANGE: (MONTH_DAY ' '* '-' ' '* NUMBER) | (MONTH_DAY ' '* '-' ' '* MONTH_DAY);

fragment
WEEK_DAY_RANGE: (WEEK_DAY ' '* '-' ' '* WEEK_DAY);

fragment
TIME_MULTI: (TIME ' '* AND ' '* TIME) | (TIME ' '* AND ' '* TIME_MULTI);

fragment
TIME_RANGE: (TIME ' '* '-' ' '* TIME);

fragment
TIME: (NUMBER (':' NUMBER (':' NUMBER)?)? ' '* (AM | PM)?) | N O O N | M I D N I G H T;

fragment
UNTIL: U N T I L | T H R O U G H;

fragment
AND: A N D | '&';

fragment
MONTH:  J A N ('.')? | J A N U A R Y | 
        F E B ('.')? | F R U A R Y | 
        M A R  ('.')? | M A R C H | 
        A P R  ('.')? | A P R I L | 
        M A Y | 
        J U N E | 
        J U L  ('.')? | J U L Y | 
        A U G  ('.')? | A U G U S T | 
        S E P (T)? ('.')? | S E P T E M B E R | 
        O C T ('.')? | O C T O B E R | 
        N O V ('.')? | N O V E M B E R | 
        D E C ('.')? | D E C E M B E R;
        
fragment
WEEK_DAY: M O N ('.')? | M O N D A Y (S)?|
          T U ('.')? | T U E ('.')? | T U E S ('.')? | T U E S D A Y (S)?|
          W E D ('.')? | W E D N E S D A Y (S)?|
          T H U ('.')? | T H U R ('.')? | T H U R S ('.')? | T H U R S D A Y (S)?|
          F R I ('.')? | F R I D A Y (S)?|
          S A ('.')? | S A T ('.')? | S A T U R D A Y (S)?|
          S U ('.')? | S U N ('.')? | S U N D A Y (S)?;


fragment      
NUMBER: '0'..'9' '0'..'9'*;

fragment
PM: P M | P '.' M '.';

fragment
AM: A M | A '.' M '.';

fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');