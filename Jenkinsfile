pipeline {
	agent { label 'Java11' }
	stages {
	    stage('clean up') {
	        steps {
                cleanWs deleteDirs: true, patterns: [[pattern: '**/target/**', type: 'INCLUDE']]
	        }
	    }
    	stage('build') {
        	steps {
                sh 'mvn --version'
                sh 'java --version'
                sh 'mvn clean install'
            }
        }
    }
}
