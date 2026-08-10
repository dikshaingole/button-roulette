pipeline {
    agent any

    parameters {
        booleanParam(
            name: 'RUN_BOOTSTRAP',
            defaultValue: false,
            description: 'One-time setup only: creates the S3 bucket + DynamoDB table that hold Terraform state. Leave unchecked on normal builds — only check this the first time, or if the bucket/table were ever deleted.'
        )
    }

    tools {
        jdk 'JDK21'
        maven 'Maven-3.9.11'
        terraform 'terraform-tool'
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
        AWS_REGION = 'ap-south-1'
        EKS_CLUSTER_NAME = 'button-roulette-cluster'
        AWS_EC2_METADATA_DISABLED = 'true'
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
				bat 'terraform version'
				bat 'kubectl version --client'
				bat 'aws --version'
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

        stage('Terraform Bootstrap Init & Plan') {
            when {
                expression { params.RUN_BOOTSTRAP }
            }
            steps {
                dir('terraform/bootstrap') {
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-terraform-creds']]) {
                        bat 'aws sts get-caller-identity'
                        bat 'terraform init -input=false'
                        bat 'terraform plan -input=false -out=bootstrap.tfplan'
                    }
                }
            }
        }

        stage('Approve Bootstrap') {
            when {
                expression { params.RUN_BOOTSTRAP }
            }
            steps {
                input message: 'Review the bootstrap plan above (creates the S3 state bucket + DynamoDB lock table). Apply?', ok: 'Apply'
            }
        }

        stage('Terraform Bootstrap Apply') {
            when {
                expression { params.RUN_BOOTSTRAP }
            }
            steps {
                dir('terraform/bootstrap') {
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-terraform-creds']]) {
                        bat 'terraform apply -input=false -auto-approve bootstrap.tfplan'
                    }
                }
            }
        }

        stage('Terraform Init & Plan') {
            steps {
                dir('terraform/infrastructure') {
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-terraform-creds']]) {
                        bat 'aws sts get-caller-identity'
                        bat 'nslookup E0519FD7A4E014FEB1D4D767C169AD38.gr7.ap-south-1.eks.amazonaws.com'
                        bat 'curl -v --max-time 15 https://E0519FD7A4E014FEB1D4D767C169AD38.gr7.ap-south-1.eks.amazonaws.com/version'
                        bat 'terraform init -input=false'
                        bat 'terraform plan -input=false -out=tfplan'
                    }
                }
            }
        }

        stage('Approve Infrastructure Changes') {
            steps {
                input message: 'Review the Terraform plan above. Apply these infrastructure changes to AWS?', ok: 'Apply'
            }
        }

        stage('Terraform Apply') {
            steps {
                dir('terraform/infrastructure') {
                    withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-terraform-creds']]) {
                        bat 'terraform apply -input=false -auto-approve tfplan'
                    }
                }
            }
        }

        stage('Configure Kubeconfig') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-terraform-creds']]) {
                    bat 'aws eks update-kubeconfig --region %AWS_REGION% --name %EKS_CLUSTER_NAME%'
                }
            }
        }

        stage('Approve Kubernetes Deployment') {
            steps {
                input message: 'Deploy the latest backend/frontend images to the EKS cluster?', ok: 'Deploy'
            }
        }

        stage('Deploy to EKS') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-terraform-creds']]) {
                    bat 'kubectl apply -f k8s/'
                    bat 'kubectl rollout restart deployment/roulette-backend'
                    bat 'kubectl rollout restart deployment/roulette-frontend'
                    bat 'kubectl rollout status deployment/roulette-backend'
                    bat 'kubectl rollout status deployment/roulette-frontend'
                }
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