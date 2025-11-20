/*
  sample-project에서는 모든 모듈이 `data` 모듈에 의존하고 있으며,
  이 파이프라인은 주어진 파라미터에 따라 프로젝트의 다양한 모듈을 빌드하고 배포합니다.

  파라미터:
  - DEPLOY_ENV: 배포 환경을 지정 ('test' 또는 'prod')
*/
pipeline {
    agent any

    parameters {
        choice(name: 'DEPLOY_ENV', choices: ['test', 'prod'])
    }

    environment {
        GITHUB_CREDS = credentials('github-creds')
        GITHUB_USERNAME = "${GITHUB_CREDS_USR}"
        GITHUB_TOKEN = "${GITHUB_CREDS_PSW}"
    }

    stages {
        stage('Root Source Build') {
            tools {
                jdk 'java-17'
                gradle 'gradle-8.2'
            }
            steps {
                sh 'gradle clean build -x test --refresh-dependencies'
                script {
                    if (params.DEPLOY_ENV == 'test') {
                        sh 'mv build/libs/*.jar /data/lims'
                        sh 'sudo systemctl restart lims-sample'
                    } else if (params.DEPLOY_ENV == 'prod') {
                        def transfers = [
                            sshTransfer(
                                sourceFiles: 'build/libs/*.jar',
                                removePrefix: 'build/libs/',
                                remoteDirectory: 'lims',
                                execCommand: 'sudo systemctl restart lims-sample'
                            )
                        ]
                        sshPublisher(
                            failOnError: true,
                            publishers: [
                                sshPublisherDesc(
                                    configName: 'Aries',
                                    transfers: transfers
                                ),
                                sshPublisherDesc(
                                    configName: 'Taurus',
                                    transfers: transfers
                                )
                            ]
                        )
                    }
                }
            }
        }

        stage('Panel Build') {
            tools {
                jdk 'java-17'
                gradle 'gradle-8.9'
            }
            steps {
                sh 'gradle clean :panel:publishToMavenLocal'
            }
        }

        stage('Web Build') {
            tools {
                jdk 'java-17'
                gradle 'gradle-8.9'
            }
            steps {
                sh 'gradle :web:copyWebResources --refresh-dependencies'
                script {
                    if (params.DEPLOY_ENV == 'test') {
                        sh 'sudo rsync -a --no-perms web/build/static/ /data/lims/static/'
                    } else if (params.DEPLOY_ENV == 'prod') {
                        def transfers = [
                            sshTransfer(
                                sourceFiles: 'web/build/static/**',
                                removePrefix: 'web/build/static',
                                remoteDirectory: 'lims/static'
                            )
                        ]
                        sshPublisher(
                            failOnError: true,
                            publishers: [
                                sshPublisherDesc(
                                    configName: 'Aries',
                                    transfers: transfers
                                ),
                                sshPublisherDesc(
                                    configName: 'Taurus',
                                    transfers: transfers
                                )
                            ]
                        )
                    }
                }
            }
        }
    }
}