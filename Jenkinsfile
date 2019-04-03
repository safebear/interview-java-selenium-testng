pipeline {

    agent any

    tools {
        maven 'Maven 3.6.0'
    }

    parameters {
        string(name: 'tests', defaultValue: 'testng', description: 'testng tests')
        string(name: 'url', defaultValue: 'http://toolslist.safebear.co.uk:8080', description: 'test environment')
        string(name: 'browser', defaultValue: 'CHROME_HEADLESS', description: 'chrome headless')
        string(name: 'wait', defaultValue: '10', description: 'wait time in seconds')
    }

    triggers { pollSCM('* * * * *') } // poll the source code repo every minute.

    stages {

        stage('BDD Requirements Testing') {
            steps {
                sh "mvn -Dtest=${params.tests} clean test -Durl=${params.url} -Dbrowser=${params.browser} -DwaitTime=${params.wait}"
            }
            post {
                always {
                    publishHTML([
                            allowMissing         : false,
                            alwaysLinkToLastBuild: false,
                            keepAll              : false,
                            reportDir            : 'target/surefire-reports',
                            reportFiles          : 'index.html',
                            reportName           : 'TestNG Report',
                            reportTitles         : ''])
                }
            }
        }
    }

}