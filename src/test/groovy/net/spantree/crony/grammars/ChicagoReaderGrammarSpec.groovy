package net.spantree.crony.grammars

import java.lang.reflect.Field
import org.antlr.runtime.ANTLRStringStream
import org.antlr.runtime.CharStream
import org.antlr.runtime.CommonTokenStream
import org.antlr.runtime.Lexer
import org.antlr.runtime.Token
import org.antlr.runtime.TokenStream
import spock.lang.Specification

class ChicagoReaderGrammarSpec extends Specification {
	String dateStr = "Sept. 28-29, 8 p.m."
	
	def "Test ranges"() {
		when:
			TimeRanges lexer = createTimeRangesParser dateStr
			Map tokenIDtoName = [:]
			TimeRanges.class.getDeclaredFields().each{ Field f->
				if (f.getType() == int.class)
					tokenIDtoName[f.get()] = f.getName()
			}
		then:
			TokenStream stream = new CommonTokenStream(lexer);
			stream.fill();
			List tokens = stream.getTokens()
			tokens.each{ Token t ->
				println "${tokenIDtoName[t.type]} : ${t.text}"
			}
	}
	
	def "Test lists"() {
		when:
			Lexer lexer = createTimeListsParser dateStr
		then:
			TokenStream tokens = new CommonTokenStream(lexer);
			println tokens.toString();
	}
	
	private TimeRanges createTimeRangesParser(String testString) throws IOException {
        CharStream stream = new ANTLRStringStream(testString);
        return new TimeRanges(stream);
    }
	
	private TimeLists createTimeListsParser(String testString) throws IOException {
		CharStream stream = new ANTLRStringStream(testString);
		return new TimeLists(stream);
	}
}
