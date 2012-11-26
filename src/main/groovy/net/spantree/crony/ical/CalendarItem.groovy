package net.spantree.crony.ical

import java.text.ParseException;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import net.fortuna.ical4j.util.CompatibilityHints;
import net.spantree.crony.ical.EventItem;

class CalendarItem {
	Calendar iCalCalendar
	static CalendarBuilder builder = new CalendarBuilder();
	
	public CalendarItem(String iCalString) {
		CompatibilityHints.setHintEnabled(CompatibilityHints.KEY_RELAXED_PARSING, true);
		iCalCalendar = builder.build(new StringReader(iCalString));
	}
	
	public List<EventItem> getEvents() {
		List<EventItem> events = []
		
		iCalCalendar.getComponents("VEVENT").each{
			events << new EventItem(it)
		}
		
		return events
	}
}
