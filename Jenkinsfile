pipeline {
	agent { label 'Java11' }
	stages {
	    stage('clean up') {
            wrappers {
                preBuildCleanup { // Clean before build
                    includePattern('**/target/**')
                    deleteDirectories()
                    cleanupParameter('CLEANUP')
                }
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
