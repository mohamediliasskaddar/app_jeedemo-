pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-21'
        }
    }

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
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            agent { label 'docker' }
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${env.BUILD_NUMBER} ."
                sh "docker tag ${DOCKER_IMAGE}:${env.BUILD_NUMBER} ${DOCKER_IMAGE}:latest"
            }
        }

        stage('Deploy') {
            agent { label 'docker' }
            steps {
                sh "docker-compose down"
                sh "docker-compose up -d --build"
            }
        }
    }

    post {
        success {
            echo "Déploiement réussi avec ${DOCKER_IMAGE}:${env.BUILD_NUMBER}"
        }
        failure {
            echo "Pipeline échoué !!"
        }
    }
}
