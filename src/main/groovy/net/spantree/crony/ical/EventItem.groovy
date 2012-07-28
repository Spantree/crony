package net.spantree.crony.ical;

import groovy.util.XmlSlurper;
import groovy.util.slurpersupport.GPathResult

import java.text.ParseException;
import java.util.Iterator;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.property.DtStart
import net.fortuna.ical4j.model.property.RRule

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval
import org.joda.time.Period

import com.google.ical.compat.jodatime.DateTimeIterable;
import com.google.ical.compat.jodatime.DateTimeIteratorFactory;
import net.fortuna.ical4j.util.CompatibilityHints;

public class EventItem{
	
	protected VEvent vEvent
	
	public EventItem(VEvent v) {
		this.vEvent = v;
		
	}

	public DateTime getStartDateTime() {
		if(vEvent.getStartDate()?.date)
			if(vEvent.getStartDate().timeZone) {
				new DateTime(vEvent.getStartDate().date,DateTimeZone.forID(vEvent.getStartDate().timeZone.ID))
			}
			else {
				new DateTime(vEvent.getStartDate().date,DateTimeZone.getDefault())
			}
		else
			null
	}
	
	public DateTime getEndDateTime() {
		if(vEvent.getEndDate()?.date) {
			if(vEvent.getEndDate().timeZone) {
				DateTime dt = new DateTime(vEvent.getEndDate().date, DateTimeZone.forID(vEvent.getEndDate().timeZone.ID))
			}
			else {
				new DateTime(vEvent.getEndDate().date,DateTimeZone.getDefault())
			}
		}
		else
			null
	}
	
	public Period getDuration() {
		DateTime tempDateStart = new DateTime()
		Date tempDateEnd = vEvent.getDuration()?.getDuration()?.getTime(tempDateStart.toDate());
		if(tempDateEnd)
			return new Period(tempDateStart,tempDateEnd)
		else
			return null
		
	}
	
	public RRule getRecurRule() {
		return vEvent.getProperty("RRULE");
	}
	
	public EventItemIntervalIterator getOccurances(DateTime start) {
		return new EventItemIntervalIterator(this,start);
	}
	
	public EventItemIntervalIterator getOccurances() {
		if(getStartDateTime())
			return getOccurances(getStartDateTime());
		else
			return getOccurances(DateTime.now())
	}
	

}
