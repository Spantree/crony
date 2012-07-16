package net.spantree.crony

import spock.lang.Specification
import net.spantree.crony.ical.ICalRecurRuleParser
import net.spantree.crony.ical.ICalRecurRule
import net.spantree.crony.converters.ICalToCronConverter
import org.joda.time.DateTime

class ICalToCronConverterSpec extends Specification {
	def "simple ical rules are converted"() {
		expect:
			String cronExprStr = ICalToCronConverter.convert(DateTime.now(),ICalRecurRuleParser.parse(rruleStr))[0].toString()
			cronExprStr == cronStr
		where:
			rruleStr << [
				"FREQ=DAILY;INTERVAL=1;UNTIL=20120716",
				"FREQ=WEEKLY;INTERVAL=1;UNTIL=20120729;BYDAY=FR,SA,TH,TU,WE"
				]
			
			cronStr << [
				"0 0 * * *",
				"0 0 * * 0"
				]
	}
}
