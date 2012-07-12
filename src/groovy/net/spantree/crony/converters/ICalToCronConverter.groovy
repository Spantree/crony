package net.spantree.crony.converters

import net.spantree.crony.cron.CronExpression
import net.spantree.crony.ical.ICalRecurRule

class ICalToCronConverter {
	List<String> convert(ICalRecurRule rule) {
		CronExpression expr = new CronExpression()
		
		
		switch(rule.frequency) {
			case ICalRecurRule.FREQUENCY.SECONDLY:
				throw new Exception("Can't convert secondly rules!")
			case ICalRecurRule.FREQUENCY.MINUTELY:
				expr.minList = [0]
				expr.hourList = [0]
				expr.dayOfMonthList = [1]
				expr.minInterval = rule.frequency
				break
			case ICalRecurRule.FREQUENCY.HOURLY:
				expr.minList = [0]
				expr.hourInterval = rule.frequency
				break
			case ICalRecurRule.FREQUENCY.DAILY:
				expr.minList = [0]
				expr.hourList = [0]
				expr.dayOfMonthInterval = rule.frequency
				break
			case ICalRecurRule.FREQUENCY.WEEKLY:
				throw new Exception("Can't convert weekly rules!")
				break
			case ICalRecurRule.FREQUENCY.MONTHLY:
				expr.hourList = [0]
				expr.minList = [0]
				expr.monthInterval = rule.frequency
				break
			case ICalRecurRule.FREQUENCY.YEARLY:
				expr.minList = [0]
				expr.monthInterval = rule.frequency
				break
				
		}
	}
}
