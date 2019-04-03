pipeline {

    agent any

    tools {
        maven 'Maven 3.6.0'
    }

    parameters {
        string(name: 'tests', defaultValue: 'RunCucumberTest', description: 'cucumber tests')
        string(name: 'url', defaultValue: 'http://toolslist.safebear.co.uk:8080', description: 'test environment')
        string(name: 'browser', defaultValue: 'CHROME_HEADLESS', description: 'chrome headless')
        string(name: 'wait', defaultValue: '10', description: 'wait time in seconds')
    }

    triggers { pollSCM('* * * * *') } // poll the source code repo every minute.

    stages {

        stage('Start Selenium Server Hub'){
            steps {
                sh "cd src/test/resources/selenium_server/"
                sh "launch-hub.sh &"
            }
        }

        stage('Start Selenium Server Firefox Node'){

            steps {
                sh "cd src/test/resources/selenium_server/"
                sh "launch-firefox-node.sh &"
            }

        }

        stage('Start Selenium Server Chrome Node'){

            steps {
                sh "cd src/test/resources/selenium_server/"
                sh "launch-chrome-node.sh &"
            }

        }

        stage('BDD Requirements Testing') {
            steps {
                sh "mvn -Dtest=${params.tests} clean test -Durl=${params.url} -Dbrowser=${params.browser} -DwaitTime=${params.wait}"
            }

        }

        stage('Generate HTML report'){
            steps {
                sh "mvn cluecumber-report:reporting"
            }
            post {
                always {
                    publishHTML([
                            allowMissing         : false,
                            alwaysLinkToLastBuild: false,
                            keepAll              : false,
                            reportDir            : 'target/generated-report',
                            reportFiles          : 'index.html',
                            reportName           : 'BDD Report',
                            reportTitles         : ''])
                }
            }
        }
    }

}