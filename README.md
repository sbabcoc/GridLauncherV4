# GridLauncherV4

My objective is to launch and manage a local Selenium 4 Grid programmatically, using only the required discrete Java artifacts required by this configuration.

## Problems with Existing Options

Here are the problems with the available CLI options that I'm aware of: 
* All of the CLI options I'm aware of are based on the massive `selenium-server` uber-JAR (more than 30 MB).
* This JAR isn't published to the standard Maven Central Java artifact repositories.
* The uber-JAR bakes in all of the dependencies required by every configuration it supports, which is why it's so large.
* It locks in the versions of all of these dependencies, making surgical remediation of bugs and vulnerabilities in each dependency impossible. (I'm not sure how you'd go about even determining which version of each discrete artifact was incorporated into the uber-JAR to figure out which identified vulnerabilities or bugs impact your installation.)

## The Solution: Launch with Discrete Artifacts

Instead of using the uber-JAR, this project uses discrete Java artifacts acquired from your configured Maven repositories. The majority of these are brought in transitively by `selenium-grid`, but this strategy enables targeted remediation of bugs and vulnerabilities via dependency management.
