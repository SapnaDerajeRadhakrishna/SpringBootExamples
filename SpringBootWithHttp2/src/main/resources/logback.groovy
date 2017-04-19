import ch.qos.logback.classic.AsyncAppender

scan("15 seconds")

//The 'hostname' variable contains the name of the current host. However, due to scoping rules the 'hostname' variable is available only at the topmost scope 
// but not in nested scopes. 

def Hostname="${hostname}"
def LOG_PATH = "logs"
def LOG_ARCHIVE = "${LOG_PATH}/archive"

appender("console-appender", ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
		pattern = "%d{yyyyMMddHHmmss.SSS};%C{0};${Hostname};%thread;%mdc{req_id};%.-1level;%msg;%n"
	}
}

appender("file-appender", FileAppender) {
	file = "${LOG_PATH}/logfile.log"
	encoder(PatternLayoutEncoder) {
		pattern = "%d{yyyyMMddHHmmss.SSS};%C{0};${Hostname};%thread;%mdc{req_id};%.-1level;%msg;%n"
		outputPatternAsHeader = true
	}
}

appender("app-appender", RollingFileAppender) {
	append = true
	rollingPolicy(TimeBasedRollingPolicy) {
	fileNamePattern = "/tmp/%d{yyyyMMdd}_spring_boot_http2.log.gz"
		maxHistory = 30
	}
	encoder(PatternLayoutEncoder) {
		pattern = "%d{yyyyMMddHHmmss.SSS};%C{0};${Hostname};%thread;%mdc{req_id};%.-1level;%msg;%n"
	}
}

appender("async-appender", AsyncAppender) {
	appenderRef("app-appender")
}


/*
*You use the logger() method to configure a logger. This method accepts the following parameters.
*1.	A string specifying the name of the logger
*2.	One of the OFF, ERROR, WARN, INFO, DEBUG, TRACE, or ALL fields of the Level class to represent the level of the designated logger.
*3.	An optional List containing one or more appenders to be attached to the logger.
*4.	An optional Boolean value indicating additivity. The default value is true.
*/

logger("org.springframework", DEBUG, ["file-appender"], false)
logger("org.maxwell.springboot",TRACE, ["app-appender"], false)

root(DEBUG, ["app-appender"])