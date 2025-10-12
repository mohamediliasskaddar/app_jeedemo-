pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'mohamediliasskaddar/jeedemo-img1'
        DOCKERHUB_CREDENTIALS = credentials('docker-hub-creds')
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
                sh "docker build -t ${DOCKER_IMAGE}:${env.BUILD_NUMBER} ."
                sh "docker tag ${DOCKER_IMAGE}:${env.BUILD_NUMBER} ${DOCKER_IMAGE}:latest"
            }
        }
        stage('Push to Docker Hub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh "docker push ${DOCKER_IMAGE}:${env.BUILD_NUMBER}"
                sh "docker push ${DOCKER_IMAGE}:latest"
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                sh 'minikube start || true'
                sh "sed -i 's|image: mohamediliasskaddar/jeedemo-img1:latest|image: ${DOCKER_IMAGE}:${env.BUILD_NUMBER}|g' k8s/app-deployment.yaml"
                sh 'kubectl apply -f k8s/postgres-statefulset.yaml'
                sh 'kubectl apply -f k8s/app-deployment.yaml'
                sh 'kubectl rollout status deployment/jeedemo-app'
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