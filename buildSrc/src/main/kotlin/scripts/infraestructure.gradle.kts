package scripts

tasks.named<Wrapper>("wrapper") {
    gradleVersion = BuildPlugins.Versions.gradleVersion
    distributionType = Wrapper.DistributionType.ALL
}