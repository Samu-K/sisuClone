# Instructions for development

## Style guide

 All developers should use Checkstyle as their linter. Make sure that Checkstyle is set to [google_checks](https://checkstyle.sourceforge.io/google_style.html).

## Development workflow

 **Nothing is pushed to main, ever!**

 Instead for each new feature always create an issue, then create a new branch and merge request relating to that issue.

 Make you changes to the new branch. When done ask someone to look over your code and merge it.

## CI / CD

Each merge request gets built. If the code does not compile, or does not pass tests, the merge is rejected.

On top of this, each merge request gets checked with checkstyle. If any stylistic errors rise up, the merge gets rejected.

**CI/CD is currently not working, due to there being no assigned runners. Course staff have been contacted about this**

## Project info
 Other contributors
  Eemil Heikkilä
  Veikka Liukkonen

 This is a school project, where the aim was to create an app to plan one's studies. The structure was to be similar to Sisu, and uses it's API for implementation.
 
