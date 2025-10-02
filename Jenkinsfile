pipeline {
    agent any  // <-- on exécute sur le host Jenkins (pas dans un container)

    environment {
        DOCKER_IMAGE = 'jeedemo-img1'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Maven') {
            steps {
                // Appel de Maven via Docker ici manuellement
                sh '''
                    docker run --rm -v "$PWD":/app -w /app maven:3.9.6-eclipse-temurin-21 mvn clean package -DskipTests
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} .
                    docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${DOCKER_IMAGE}:latest
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    docker-compose down
                    docker-compose up -d --build
                '''
            }
        }
    }

    post {
        success {
            echo "Déploiement réussi avec :  ${DOCKER_IMAGE}:${BUILD_NUMBER}"
        }
        failure {
            echo "Pipeline échoué"
        }
    }
}
