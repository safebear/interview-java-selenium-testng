pipeline {

    agent any

    parameters {
        string(name: 'tests', defaultValue: 'RunCukes', description: 'cucumber tests')
        string(name: 'url', defaultValue: 'http://toolslist.safebear.co.uk:8080', description: 'test environment')
        string(name: 'browser', defaultValue: 'headless', description: 'chrome headless')
        string(name: 'wait', defaultValue: '10', description: 'wait time in seconds')
    }

    triggers { pollSCM('* * * * *') } // poll the source code repo every minute.

    stages {

        stage('Start Selenium Server Hub'){
            steps {
                sh "src/test/resources/selenium_server/launch-hub.sh"
            }
        }

        stage('Start Selenium Server Firefox Node'){

            steps {
                sh "src/test/resources/selenium_server/launch-firefox-node.sh"
            }

        }

        stage('Start Selenium Server Chrome Node'){

            steps {
                sh "src/test/resources/selenium_server/launch-chrome-node.sh"
            }

        }

        stage('BDD Requirements Testing') {
            steps {
                sh "mvn -Dtest=${params.tests} test -Durl=${params.url} -Dbrowser=${params.browser} -DwaitTime=${params.wait}"
            }
            post {
                always {
                    publishHTML([
                            allowMissing         : false,
                            alwaysLinkToLastBuild: false,
                            keepAll              : false,
                            reportDir            : 'target/cucumber',
                            reportFiles          : 'index.html',
                            reportName           : 'BDD Report',
                            reportTitles         : ''])
                }
            }
        }
    }

}