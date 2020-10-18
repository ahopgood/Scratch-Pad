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
                git credentialsId: 'github_token', url: 'git://github.com/ahopgood/Scratch-Pad.git'
                sh 'mvn --version'
                sh 'java --version'
                sh 'mvn clean install'
            }
        }
    }
}
