package net.spantree.crony.cron

class CronExpression {
	enum CronTimeUnitType {
		WILDCARD,
		LIST,
		RANGE
	}
	
	class CronTimeUnit {
		CronTimeUnitType type = CronTimeUnitType.WILDCARD
		List valueList = []
		Long interval = 1
		boolean isRange = false;
		
		String toString() {
			String str = ""
			if(valueList.size()>0){
				str = valueList.join(",")
			}
			else {
				str += "*"
			}
			
			if(interval > 1) {
				str += "/$interval"
			}
			
			return str
		}
	}
	
	public CronTimeUnit min = new CronTimeUnit()
	public CronTimeUnit hour = new CronTimeUnit()
	public CronTimeUnit dayOfMonth = new CronTimeUnit()
	public CronTimeUnit month = new CronTimeUnit()
	public CronTimeUnit dayOfWeek = new CronTimeUnit()
	
	
	
	public String toString() {
		return "$min $hour $dayOfMonth $month $dayOfWeek"
	}
}
