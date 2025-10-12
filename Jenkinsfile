pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'mohamediliasskaddar/jeedemo-img1'
//         DOCKERHUB_CREDENTIALS = credentials('docker-hub-creds')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Maven') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-21'
                }
            }
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                        sh "docker build -t ${DOCKER_IMAGE}:${env.BUILD_NUMBER} ."
                        sh "docker tag ${DOCKER_IMAGE}:${env.BUILD_NUMBER} ${DOCKER_IMAGE}:latest"
                        sh "docker push ${DOCKER_IMAGE}:${env.BUILD_NUMBER}"
                        sh "docker push ${DOCKER_IMAGE}:latest"
                    }
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
//                 sh 'minikube start'  // Si pas lancé
                sh "sed -i 's|image: jeedemo-img1:latest|image: ${DOCKER_IMAGE}:${env.BUILD_NUMBER}|g' k8s/app-deployment.yaml"  // Update image tag
                sh 'kubectl apply -f k8s/dbpostgres.yaml'
                sh 'kubectl apply -f k8s/app-deployment.yaml'
                sh 'kubectl rollout status deployment/jeedemo-app'  // Attend rollout
            }
        }
    }
    post {
        success {
            echo "Déploiement réussi avec ${DOCKER_IMAGE}:${env.BUILD_NUMBER}"
        }
        failure {
            echo "Pipeline échoué !!!!"
        }
    }
}