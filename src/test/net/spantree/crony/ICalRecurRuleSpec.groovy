package net.spantree.crony

import spock.lang.Specification
import net.spantree.crony.ical.ICalRecurRuleParser
import net.spantree.crony.ical.ICalRecurRule

class ICalRecurRuleSpec extends Specification{
	def "ical recurrence rules are parsed"() {
		expect:
			ICalRecurRuleParser.parse(rruleStr)
		where:
			rruleStr << [
				"FREQ=DAILY;INTERVAL=1;UNTIL=20120716",
				"FREQ=WEEKLY;INTERVAL=1;UNTIL=20120729;BYDAY=FR,SA,TH,TU,WE"
				]
	}
}
