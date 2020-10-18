pipeline {
	agent { label 'Java8' }
	stages {
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