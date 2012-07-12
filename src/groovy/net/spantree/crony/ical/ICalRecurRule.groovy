package net.spantree.crony.ical

class ICalRecurRule {
	public enum FREQUENCY {
		SECONDLY,
		MINUTELY,
		HOURLY,
		DAILY,
		WEEKLY,
		MONTHLY,
		YEARLY
	}
	
	public enum WEEKDAY {
		SU,
		MO,
		TU,
		WE,
		TH,
		FR,
		SA
	}
	
	public FREQUENCY frequency;
	public Date until = null;
	public Long count = null;
	public Long interval = null;
	
	public List bySec = []
	public List byMin = []
	public List byHr = []
	public List byDay = []
	public List byMonthDay = []
	public List byYearDay = []
	public List byWeekNo = []
	public List byMonth = []
	public List bySetPos = []
	public WEEKDAY wkst = WEEKDAY.SU
	
	public ICalRecurRule() {
		
	}
}
