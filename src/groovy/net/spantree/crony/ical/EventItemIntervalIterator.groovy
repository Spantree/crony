package net.spantree.crony.ical

import java.util.Iterator;

import org.joda.time.DateTime
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;

import com.google.ical.compat.jodatime.DateTimeIterable;
import com.google.ical.compat.jodatime.DateTimeIterator
import com.google.ical.compat.jodatime.DateTimeIteratorFactory;

class EventItemIntervalIterator implements Iterator<Interval> {
		static final DateTimeZone CST = DateTimeZone.forID("America/Los_Angeles");
	
		DateTimeIterator dtIt
		EventItem itm
		
		public EventItemIntervalIterator(EventItem itm) {
			this.itm = itm
			assert itm.startDateTime

			dtIt = DateTimeIteratorFactory.createDateTimeIterator(itm.vEvent.getProperty("RRULE").toString(), itm.startDateTime, CST, true);
		}

		@Override
		public boolean hasNext() {
			return dtIt.hasNext();
		}

		@Override
		public Interval next() {
			DateTime dtStart = dtIt.next()
			if(itm.duration) {
				Date dtEnd = dtStart + itm.duration;
				return new Interval(dtStart,dtEnd);
			}
			else {
				return new Interval(dtStart,dtStart);
			}
			
		}

		@Override
		public void remove() {
			dtIt.remove();
		}
}