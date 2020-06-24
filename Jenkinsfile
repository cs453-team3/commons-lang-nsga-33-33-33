#!groovy

/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
pipeline {
    agent any

    /*tools {
        maven 'Maven 3 (latest)'
        jdk 'JDK 1.8 (latest)'
    }*/

    stages {
        stage('Build') {
            steps {
                sh '/home/tmr/apache-maven-3.6.3/bin/mvn clean compile -Drat.skip=true'
                sh '/home/tmr/apache-maven-3.6.3/bin/mvn test-compile -Drat.skip=true'
            }
        }
        stage('Algorithm') {
            steps {
                // restart: false/true to delete collected data about tests
                step([$class: 'PrioraBuilder', mnCommitInterv: 100, mxCommitInterv: 100, prioraMethod: 'Greedy'])
                //step([$class: 'PrioraBuilder', mnCommitInterv: 60000, mxCommitInterv: 60000, prioraMethod: 'NSGA', weights: [0.33, 0.33, 0.33]]) // failure, average runtime, run#
                //step([$class: 'PrioraBuilder', mnCommitInterv: 60000, mxCommitInterv: 60000, prioraMethod: 'NSGA', weights: [0.5, 0.5, 0.0]])
                //step([$class: 'PrioraBuilder', mnCommitInterv: 60000, mxCommitInterv: 60000, prioraMethod: 'NSGA', weights: [0.5, 0.3, 0.2]])
            }
        }
        stage('Test') {
            steps {
                sh './priora/exectests'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        /*stage('Deploy') {
            when {
                branch 'master'
            }
            steps {
                sh 'mvn deploy'
            }
        }*/
    }

    // Send out notifications on unsuccessful builds.
    /*post {
        // If this build failed, send an email to the list.
        failure {
            script {
                if(env.BRANCH_NAME == "master") {
                    def state = (currentBuild.previousBuild != null) && (currentBuild.previousBuild.result == 'FAILURE') ? "Still failing" : "Failure"
                    emailext(
                            subject: "[Lang] Change on branch \"${env.BRANCH_NAME}\": ${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - $state",
                            body: """The Apache Jenkins build system has built ${env.JOB_NAME} (build #${env.BUILD_NUMBER})

Status: ${currentBuild.result}

Check console output at <a href="${env.BUILD_URL}">${env.BUILD_URL}</a> to view the results.
""",
                            to: "notifications@commons.apache.org",
                            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                    )
                }
            }
        }

        // If this build didn't fail, but there were failing tests, send an email to the list.
        unstable {
            script {
                if(env.BRANCH_NAME == "master") {
                    def state = (currentBuild.previousBuild != null) && (currentBuild.previousBuild.result == 'UNSTABLE') ? "Still unstable" : "Unstable"
                    emailext(
                            subject: "[Lang] Change on branch \"${env.BRANCH_NAME}\": ${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - $state",
                            body: """The Apache Jenkins build system has built ${env.JOB_NAME} (build #${env.BUILD_NUMBER})

Status: ${currentBuild.result}

Check console output at <a href="${env.BUILD_URL}">${env.BUILD_URL}</a> to view the results.
""",
                            to: "notifications@commons.apache.org",
                            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                    )
                }
            }
        }

        // Send an email, if the last build was not successful and this one is.
        success {
            script {
                if ((env.BRANCH_NAME == "master") && (currentBuild.previousBuild != null) && (currentBuild.previousBuild.result != 'SUCCESS')) {
                    emailext (
                            subject: "[Lang] Change on branch \"${env.BRANCH_NAME}\": ${env.JOB_NAME} - Build # ${env.BUILD_NUMBER} - Back to normal",
                            body: """The Apache Jenkins build system has built ${env.JOB_NAME} (build #${env.BUILD_NUMBER})

Status: ${currentBuild.result}

Check console output at <a href="${env.BUILD_URL}">${env.BUILD_URL}</a> to view the results.
""",
                            to: "notifications@commons.apache.org",
                            recipientProviders: [[$class: 'DevelopersRecipientProvider']]
                    )
                }
            }
        }
    }*/
}
