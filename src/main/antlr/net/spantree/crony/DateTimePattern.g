grammar DateTimePattern;

options {
  language = Java;
  backtrack = true;
}

@parser::header {
  package net.spantree.crony;
}

@lexer::header {
  package net.spantree.crony;
}

//parse: PATTERN EOF; 

PATTERN
   : ( (DAYOFWEEK_LIST | DAYOFWEEK_SEQUENCE | DAYOFWEEK) ' '*)?
     (TIME | TIME_SEQUENCE)?
   ; 


WHITESPACE: (' ' | '\t')+ { skip(); };

fragment TIME_SEQUENCE:      TIME SEQUENCE_JOINER TIME;
fragment TIME:               (NOON|MIDNIGHT|HOUR (':' MINUTE)? DAYPERIOD?); 

DAYOFWEEK_LIST:       (DAYOFWEEK  LIST_JOINER DAYOFWEEK_LIST) | (DAYOFWEEK LIST_JOINER DAYOFWEEK) | DAYOFWEEK;
DAYOFWEEK_LIST_CHILD: (LIST_JOINER DAYOFWEEK)
DAYOFWEEK_SEQUENCE:   DAYOFWEEK SEQUENCE_JOINER DAYOFWEEK;

fragment DAYOFWEEK:          MONDAY | TUESDAY | WEDNESDAY | THURSDAY | FRIDAY | SATURDAY | SUNDAY; 

fragment MONDAY:    ('M'|'m') ('on' ('day')?)?;
fragment TUESDAY:   ('T'|'t') 'u' ('e' ('s' ('day')?)?)?;
fragment WEDNESDAY: ('W'|'w') ('ed' ('s' | 'nesday')?)?;
fragment THURSDAY:  ('T'|'t') 'h' ('u' ('r' ('s' 'day'?)?)?)?;
fragment FRIDAY:    ('F'|'f') ('ri' 'day'?)?;
fragment SATURDAY:  ('S'|'s') 'a' ('t' 'urday'?)?;
fragment SUNDAY:    ('S'|'s') 'u' ('n' 'day'?)?;

fragment DAYPERIOD: AM | PM;
fragment NOON:      ('N'|'n') 'oon';
fragment MIDNIGHT:  ('M'|'m') ('idnight' | 'idnite');
fragment AM:        ('A'|'a') ' '? ('M'|'m')? '.'?;
fragment PM:        ('P'|'p') ' '? ('M'|'m')? '.'?;
fragment HOUR:      ('2'..'9'|'10'|'11'|'12');
fragment MINUTE:    ('0'..'5' INT);

fragment AND: ' '* 'and' ' '*;

fragment SEQUENCE_JOINER:      ('-' | 'through' | 'thru');
fragment LIST_JOINER:          (CONJUNCTION_PHRASE);
fragment CONJUNCTION_SYMBOL:   ( ',' | ';' );
fragment CONJUNCTION_PHRASE:   (CONJUNCTION_SYMBOL? AND) | ( CONJUNCTION_SYMBOL ) ' '*;


INT: '0'..'9'+;