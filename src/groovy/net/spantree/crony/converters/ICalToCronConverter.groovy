package net.spantree.crony.converters

import net.spantree.crony.cron.CronExpression
import net.spantree.crony.ical.ICalRecurRule
import net.spantree.crony.ical.ICalRecurRuleParser
import org.joda.time.DateTime

class ICalToCronConverter {

	static List<CronExpression> convert(DateTime now, ICalRecurRule rule) {
		CronExpression expr = new CronExpression()

		switch(rule.frequency) {
			case ICalRecurRule.FREQUENCY.SECONDLY:
				throw new Exception("Can't convert secondly rules!")
				break
			case ICalRecurRule.FREQUENCY.MINUTELY:
				expr.min.setInterval(rule.interval)
				break
			case ICalRecurRule.FREQUENCY.HOURLY:
				expr.min.valueList = [now.minuteOfHour]
				expr.hour.setInterval(rule.interval)
				break
			case ICalRecurRule.FREQUENCY.DAILY:
				expr.min.valueList = [now.minuteOfHour]
				expr.hour.valueList = [now.hourOfDay]
				expr.dayOfMonth.setInterval(rule.interval)
				break
			case ICalRecurRule.FREQUENCY.WEEKLY:
				expr.min.valueList = [0]
				expr.hour.valueList = [0]
				expr.dayOfWeek.valueList = [0]
				break
			case ICalRecurRule.FREQUENCY.MONTHLY:
				expr.hour.valueList = [0]
				expr.min.valueList = [0]
				expr.dayOfWeek.valueList = [1]
				expr.month.setInterval(rule.interval)
				break
			case ICalRecurRule.FREQUENCY.YEARLY:
				expr.hour.valueList = [0]
				expr.min.valueList = [0]
				expr.dayOfWeek.valueList = [1]
				expr.month.valueList = [1]
				break
			default:
				throw new Exception("Parser is unaware of frequence type ${rule.frequency}");
				
		}
		
		return [expr]
	}
}
