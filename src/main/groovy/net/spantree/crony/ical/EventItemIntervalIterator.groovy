package net.spantree.crony.ical

import java.util.Iterator;

import net.fortuna.ical4j.model.Period
import net.fortuna.ical4j.model.PeriodList
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
			
			
			net.fortuna.ical4j.model.DateTime ical4jStartTime = new net.fortuna.ical4j.model.DateTime(startTime.toDate())
			net.fortuna.ical4j.model.DateTime ical4jEndTime = new net.fortuna.ical4j.model.DateTime(startTime.plusYears(1).toDate())
			if(itm.vEvent.getProperty("RRULE")) {
				PeriodList lst = itm.vEvent.calculateRecurrenceSet(new Period(ical4jStartTime,ical4jEndTime))
				dtIt = lst.iterator();
			}
			else if (itm.getStartDateTime()) {
				if(itm.getEndDateTime()) {
					dtIt =[new Interval(itm.getStartDateTime(),itm.getEndDateTime())].iterator()
				}
				else {
					dtIt =[new Interval(itm.getStartDateTime(),itm.getStartDateTime())].iterator()
				}
			}
			else
				dtIt = [].iterator()
			
			
		}

		@Override
		public boolean hasNext() {
			return dtIt.hasNext();
		}

		@Override
		public Interval next() {
			
			Object o = dtIt.next()
			if(o instanceof Period) {
				Period p = (Period)o;
				java.util.Date dStart = p.getRangeStart()
				java.util.Date dEnd = p.getRangeEnd()
				
				
				return new Interval(new DateTime(dStart),new DateTime(dEnd));
			}
			else if (o instanceof DateTime) {
				return new Interval(o,o);
			}
			else if (o instanceof Interval)
				return o;
			
			
		}

		@Override
		public void remove() {
			dtIt.remove();
		}
}