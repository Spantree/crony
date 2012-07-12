package net.spantree.crony.ical

import java.text.SimpleDateFormat


class ICalRecurRuleParser {
	static public ICalRecurRule parse(String recurRule) {
		ICalRecurRule rule = new ICalRecurRule()
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd")
		if (recurRule.contains(";")) {
			Map properties = recurRule.split(";").collectEntries{
				List splitProp = it.split("=")
				//TODO: Make sure properties only occur once
				[(splitProp[0]):(splitProp[1])]
			}

			properties.each{k,v ->
				switch(k){
					case "FREQ":
						rule.frequency = Enum.valueOf(ICalRecurRule.FREQUENCY.class, v);
						break;
					case "UNTIL":
						rule.until = dateFormat.parse(v)
						break
					case "COUNT":
						rule.count = Long.parseLong(v)
						break
					case "INTERVAL":
						rule.interval = Long.parseLong(v)
						break
					case "BYSECOND":
						rule.bySec = v.split(",").collect{ Integer.parseInt(it) }
						break
					case "BYMINUTE":
						rule.byMin = v.split(",").collect{ Integer.parseInt(it) }
						break
					case "BYHOUR":
						rule.byHr = v.split(",").collect{ Integer.parseInt(it) }
						break
					case "BYDAY":
						rule.byDay = v.split(",").collect{ Enum.valueOf(ICalRecurRule.WEEKDAY.class, it) }
						break
					case "BYMONTHDAY":
						rule.byMonthDay = v.split(",").collect{ Integer.parseInt(it) }
						break
					case "BYYEARDAY":
						rule.byYearDay = v.split(",").collect{ Integer.parseInt(it) }
						break
					case "BYWEEKNO":
						rule.byWeekNo = v.split(",").collect{ Integer.parseInt(it) }
						break
					case "BYMONTH":
						rule.byMonth = v.split(",").collect{ Integer.parseInt(it) }
						break
					case "BYSETPOS":
						rule.byMonth = v.split(",").collect{ Integer.parseInt(it) }
						break
					case "WKST":
						rule.wkst = Enum.valueOf(ICalRecurRule.WEEKDAY.class, v);
						break
					default:
						throw new Exception("Unknown property: $k : $v")
				}
			}
		}
		else
		{
			return null;
		}

		return rule;
	}
}
