pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven-3.9.11'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/dikshaingole/button-roulette.git'
            }
        }

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

        stage('Build Backend') {
            steps {
                dir('roulette-backend') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('roulette-frontend') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                dir('roulette-backend') {
                    bat 'docker build -t dikshaingole/roulette-backend:latest .'
                }
            }
        }

        stage('Build Frontend Docker Image') {
            steps {
                dir('roulette-frontend') {
                    bat 'docker build -t dikshaingole/roulette-frontend:latest .'
                }
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'Dockerhub_Dikshaid',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    bat '''
                    echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                    '''
                }
            }
        }

        stage('Push Backend Image') {
            steps {
                bat 'docker push dikshaingole/roulette-backend:latest'
            }
        }

        stage('Push Frontend Image') {
            steps {
                bat 'docker push dikshaingole/roulette-frontend:latest'
            }
        }
    }

    post {
        success {
            echo 'Docker images pushed successfully.'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}