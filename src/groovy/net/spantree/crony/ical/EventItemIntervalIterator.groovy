package net.spantree.crony.ical

import java.util.Iterator;

import org.joda.time.DateTime
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;

import com.google.ical.compat.jodatime.DateTimeIterable;
import com.google.ical.compat.jodatime.DateTimeIterator
import com.google.ical.compat.jodatime.DateTimeIteratorFactory;

class EventItemIntervalIterator implements Iterator<Interval> {
		
		Iterator dtIt
		EventItem itm
		
		public EventItemIntervalIterator(EventItem itm) {
			this(itm,itm.getStartDateTime());
		}
		
		public EventItemIntervalIterator(EventItem itm, DateTime startTime) {
			this.itm = itm
			assert startTime
			
			//Hack: Since start date is always included (but it may not be valid), subtract a millisecond and then advance the iterator later.
			startTime = startTime.minus(1); 
			if(itm.vEvent.getProperty("RRULE")) {
				dtIt = DateTimeIteratorFactory.createDateTimeIterator(itm.getRecurRule().toString(), startTime, DateTimeZone.getDefault(), true);
				dtIt.next();
			}
			else if (itm.getStartDateTime())
				dtIt =[itm.getStartDateTime()].iterator()
			else
				dtIt = [].iterator()
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