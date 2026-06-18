pipeline {
agent any

tools{
   maven 'Maven-3.9.11'
   jdk 'JDK21'
}

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
