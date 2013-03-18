lexer grammar ConjunctionPattern;

options {
  language = Java;
}

SENTENCE
  : (WORD | LIST)+
  ;

LIST
  : WORD ((CONJUNCTION | AND) WORD)+
  ;

CONJUNCTION
  : (', '|'; ') AND?
  ;

AND
  : ' and '
  ;

WORD
  : (ALPHANUM)+
  ;

fragment ALPHANUM
  : 'a'..'z'
  | 'A'..'Z'
  | '0'..'9'
  ;
  
WS
  : (' '|'\r'|'\t'|'\u000C'|'\n') {channel=99;}
  ;
