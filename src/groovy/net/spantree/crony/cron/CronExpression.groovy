package net.spantree.crony.cron

class CronExpression {
	enum CronTimeUnitType {
		WILDCARD,
		LIST,
		RANGE
	}
	
	public CronTimeUnitType minType = CronTimeUnitType.WILDCARD
	public List minList = []
	public int minInterval = -1
	
	public CronTimeUnitType hourType = CronTimeUnitType.WILDCARD
	public List hourList = []
	public int hourInterval = -1
	
	public CronTimeUnitType dayOfMonthType = CronTimeUnitType.WILDCARD
	public List dayOfMonthList = []
	public int dayOfMonthInterval = -1
	
	public CronTimeUnitType monthListType = CronTimeUnitType.WILDCARD
	public List monthList = []
	public int monthInterval = -1
	
	public CronTimeUnitType weekListType = CronTimeUnitType.WILDCARD
	public List weekList = []
	public int weekInterval = -1
}
