pipeline {
agent any

stages {
    stage('Check Installed Tools Versions') {
        steps {
            bat '''
            echo ===== JAVA VERSION =====
            java -version

            echo.
            echo ===== MAVEN VERSION =====
            mvn --version

            echo.
            echo ===== NODE VERSION =====
            node -v

            echo.
            echo ===== NPM VERSION =====
            npm -v
            '''
        }
    }
}


}
