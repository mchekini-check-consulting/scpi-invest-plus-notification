node("ci-node"){
    def GIT_COMMIT_HASH = ""

    stage("Checkout"){
        checkout scm
        GIT_COMMIT_HASH = sh (script: "git log -n 1 --pretty=format:'%H'", returnStdout: true)
    }

    stage("Unit tests"){
        sh "chmod 777 mvnw && ./mvnw clean test"
    }

    stage("Quality Analyses"){
        sh "./mvnw clean verify sonar:sonar \\\n" +
                "  -Dsonar.projectKey=notification-service \\\n" +
                "  -Dsonar.projectName='notification-service' \\\n" +
                "  -Dsonar.host.url=https://sonar.check-consulting.net \\\n" +
                "  -Dsonar.token=sqp_210a1e1e2ac82c9e0cfc68b21b8980e5789a7d3f"
    }

    stage("Build Jar file"){
        sh "./mvnw package -DskipTests"
    }

    stage("Build Docker Image"){
        sh "sudo docker build -t mchekini/notification-service:$GIT_COMMIT_HASH ."
    }

    stage("Push Docker image"){
        withCredentials([usernamePassword(credentialsId: 'mchekini', passwordVariable: 'password', usernameVariable: 'username')]) {
            sh "sudo docker login -u $username -p $password"
            sh "sudo docker push mchekini/notification-service:$GIT_COMMIT_HASH"
            sh "sudo docker rmi mchekini/notification-service:$GIT_COMMIT_HASH"
        }
    }
}