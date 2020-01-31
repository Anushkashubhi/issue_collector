import groovy.json.JsonSlurper 
import groovy.util.logging.Slf4j
import org.slf4j.impl.OutputChoice
import spock.lang.Specification

@NonCPS
collectissues(String data){
def jsonSlurper = new JsonSlurper() 
def resultJson = jsonSlurper.parseText(data)
def projectName = resultJson.key
echo "$projectName"
 httpRequest authentication: 'jira_password',
  customHeaders: [[maskValue: false, name: 'Accept', value: 'application/json']], 
     httpMode: 'GET', url:"http://ec2-18-191-16-16.us-east-2.compute.amazonaws.com:8080/rest/api/2/search?jql=project%3D${projectName}&fields=key%2Csummary%2Cdescription&maxResults=1000&startAt=0"
     def connection = url.toURL().openConnection()
 
}
def call(){
 def request = libraryResource 'data1.json'
 collectissues(request)
}

class SpyMethodArgsExampleSpec extends Specification {

    def "testing logging activity, but why?"() {
        given:
        ConsoleHandler ch = Spy(ConsoleHandler)

        PrintStream printStream = Mock(PrintStream)
        ch.log.CONFIG_PARAMS.outputChoice = Mock(OutputChoice)
        ch.log.CONFIG_PARAMS.outputChoice.getTargetPrintStream() >> printStream

        when:
        ch.run()

        then:
        1 * printStream.println(_ as String)
    }

    @Slf4j
    static class ConsoleHandler {
        void run() {
            log.error("test")
        }
    }
}
