pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven-3.9.11'
    }

    // Email sent via Mailtrap Email Testing sandbox, configured under
    // Manage Jenkins > System > E-mail Notification:
    //   SMTP server: sandbox.smtp.mailtrap.io
    //   Port: 2525, Use SMTP Authentication (credentials from Mailtrap inbox > SMTP Settings)
    // Mailtrap captures all outgoing mail in its sandbox inbox regardless of
    // the "to" address below, so nothing reaches real inboxes until the SMTP
    // host is swapped for a production service (e.g. Gmail SMTP).
    environment {
        DEVOPS_EMAIL = 'dikshaingole5@gmail.com'
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

        stage('Build Applications') {
			parallel {

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
			}
		}

        stage('Build Docker Images') {
			parallel {

				stage('Backend Image') {
					steps {
						dir('roulette-backend') {
							bat 'docker build -t dikshaingole/roulette-backend:latest .'
						}
					}
				}

				stage('Frontend Image') {
					steps {
						dir('roulette-frontend') {
							bat 'docker build -t dikshaingole/roulette-frontend:latest .'
						}
					}
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
            mail to: "${env.DEVOPS_EMAIL}",
                 subject: "BUILD FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Build #${env.BUILD_NUMBER} for ${env.JOB_NAME} failed.\n\nCheck console output: ${env.BUILD_URL}console"
        }
    }
}