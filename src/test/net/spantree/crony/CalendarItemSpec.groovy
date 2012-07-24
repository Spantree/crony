package net.spantree.crony

import groovy.util.slurpersupport.GPathResult
import net.fortuna.ical4j.data.ParserException
import net.spantree.crony.ical.CalendarItem
import spock.lang.Specification
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

import net.fortuna.ical4j.util.CompatibilityHints;

class CalendarItemSpec extends Specification{
	def "can parse ical files"() {
		when:
			
			InputStream iCalXmlStream = this.class.getClassLoader().getResourceAsStream("resources/cr-event-ical.xml")
			XmlSlurper slurper = new XmlSlurper()
			
			GPathResult events = slurper.parseText(iCalXmlStream.text)
			
		then:
			events.event.each{ ical ->
				
				try{
					CalendarItem cItem = new CalendarItem("$ical",DateTimeZone.getDefault())
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
	}
	
	def "can adjust until date of ical"() {
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
			int numEntries = 0;
			CalendarItem cItem = new CalendarItem("$ical",DateTimeZone.getDefault())
			cItem.events.each{ evtItem ->
				print evtItem.getRecurRule()?evtItem.getRecurRule():"No recur rule\n"
				evtItem.getOccurances(new DateTime(2012,6,1,0,0)).each { it ->
					println it
					numEntries ++;
				}
				println()
			}
		then:
			numEntries == 4
	}

}
