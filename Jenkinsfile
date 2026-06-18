pipeline {
agent any

tools{
   maven 'Maven-3.9.11'
   jdk 'JDK21'
}

stages {
    stage('Environment Check') {
			steps {
				bat 'whoami'
				bat 'java -version'
				bat 'javac -version'
				bat 'mvn -version'
				bat 'docker --version'
				bat 'docker version'
				bat 'docker ps'
			}
		}
}


}
