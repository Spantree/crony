package net.spantree.crony

import groovy.util.slurpersupport.GPathResult
import net.fortuna.ical4j.data.ParserException
import net.spantree.crony.ical.CalendarItem
import spock.lang.Specification
import org.joda.time.DateTime

class CalendarItemSpec extends Specification{
	def "can parse ical files"() {
		when:
			InputStream iCalXmlStream = this.class.getClassLoader().getResourceAsStream("resources/cr-event-ical.xml")
			XmlSlurper slurper = new XmlSlurper()
			
			GPathResult events = slurper.parseText(iCalXmlStream.text)
			events.event.each{ ical ->
				
				try{
					CalendarItem cItem = new CalendarItem("$ical")
					cItem.events.each{ evtItem ->
						print evtItem.getRecurRule()?evtItem.getRecurRule():"No recur rule\n"
						evtItem.getOccurances(DateTime.now()).each { it ->
							println it
						}
						println()
					}
					
				} catch (ParserException pex) {
				
				}
				
			}
		then:
			true
	}

}
