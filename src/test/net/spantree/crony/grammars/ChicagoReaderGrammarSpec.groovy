package net.spantree.crony.grammars

import org.antlr.runtime.ANTLRStringStream
import org.antlr.runtime.CharStream
import org.antlr.runtime.CommonTokenStream
import org.antlr.runtime.Lexer
import org.antlr.runtime.TokenStream
import spock.lang.Specification

class ChicagoReaderGrammarSpec extends Specification {
	def "Test grammar"() {
		when:
			Lexer lexer = createParser "Through 7/6: Fri midnight"
		then:
			TokenStream tokens = new CommonTokenStream(lexer);
			println tokens.toString();
	}
	
	private ChicagoReader createParser(String testString) throws IOException {
        CharStream stream = new ANTLRStringStream(testString);
        ChicagoReader lexer = new ChicagoReader(stream);
        
        return lexer;
    }
}
