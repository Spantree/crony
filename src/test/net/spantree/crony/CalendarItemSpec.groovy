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
	
	def "can parse hard ical"() {
		when:
			def ical = """BEGIN:VCALENDAR
VERSION:2.0
PRODID:-//gyrobase/iCalendar//NONSGML v1.0//EN
BEGIN:VEVENT
DTSTART:20120711
RRULE:FREQ=DAILY;INTERVAL=1;UNTIL=20120714
SUMMARY:Bicycle Illinois
END:VEVENT
END:VCALENDAR
"""
		then:
			CalendarItem cItem = new CalendarItem("$ical")
			cItem.events.each{ evtItem ->
				print evtItem.getRecurRule()?evtItem.getRecurRule():"No recur rule\n"
				evtItem.getOccurances(DateTime.now()).each { it ->
					println it
				}
				println()
			}
	}

}
