# What is this?
## Challenge 2: Graphic User Interphace Application
This repo contains a Git GUI assistant for working with GitHub, particularly for setting up a repo for the first time.

## Requirements

- Java 8 or higher 
- GitSubprocessClient | https://github.com/CSC109/GitSubprocessClient 
- GitHubApiClient | https://github.com/CSC109/GitHubApiClient 

## Authentication

For authentication, which GitHub requires in order to use their API, you need to generate a GitHub API token for your account.
Click [here](https://csc109.github.io/GitHubApiClient/access-token) to learn how to generate an access token.

After generating a token, in the Credentials.java page, please enter the token here:
```
private static final String token = "";
```
Please also enter your GitHub username here:
```
private static final String username = ""
```

## Usage
For usage, run the program to generate the GUI.
<br> To access any features, you must first select the directory you want to work with. This is done by clicking the 'Select Directory' button.
<br>
<br>
### To setup a repo for the first time:
- Click the 'Setup New Repository' button
- Enter a name for your repo
- Enter a description
- Select a Private or Public repo
- You have a repo!

### To add to GitHub:
- Click the "Git Add" button
- Click the "Git Commit" button
- Enter a commit message
- Click the "Git Push" button

### To add a .gitignore file to GitHub:
- Click the Add "Add Git Ignore" button
<br> **This automatically uses the "Git Add" button specifically for this file
- Click the "Git Commit" button
- Enter a commit message
- Click the "Git Push" button

### To add a README.md file to GitHub:
- Click the Add "Add README" button
<br> **This automatically uses the "Git Add" button specifically for this file
- Click the "Git Commit" button
- Enter a commit message
- Click the "Git Push" button
