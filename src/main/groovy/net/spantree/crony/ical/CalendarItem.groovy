package net.spantree.crony.ical

import java.text.ParseException;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import com.google.ical.compat.jodatime.DateTimeIterable;
import com.google.ical.compat.jodatime.DateTimeIteratorFactory;
import net.fortuna.ical4j.util.CompatibilityHints;

class CalendarItem {
	Calendar iCalCalendar
	static CalendarBuilder builder = new CalendarBuilder();
	
	public CalendarItem(String iCalString, DateTimeZone untilTz = DateTimeZone.UTC) {
		CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_RELAXED_PARSING, true);
		iCalCalendar = builder.build(new StringReader(iCalString));
		
		//Adjust the UNTIL portion of RRULES
		//iCal4j assumes this is in UTC
		if(untilTz && untilTz!=DateTimeZone.UTC) {
			iCalCalendar.getComponents("VEVENT").each{ VEvent vEvent ->
				def rrule = vEvent.getProperty("RRULE")
				net.fortuna.ical4j.model.Date until = rrule?.getRecur()?.getUntil()
				
				if(until) {
					int offset = untilTz.getOffset(until.getTime())
					until.setMinutes(until.getMinutes() - (int)(offset/60000))
					rrule.getRecur().setUntil(until)
				}
			}
		}
	}
	
	public List<EventItem> getEvents() {
		List<EventItem> events = []
		
		iCalCalendar.getComponents("VEVENT").each{
			events << new EventItem(it)
		}
		
		return events
	}
}
