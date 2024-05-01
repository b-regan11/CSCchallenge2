import javax.swing.JOptionPane;

import git.tools.client.GitSubprocessClient;
import github.tools.client.GitHubApiClient;
import github.tools.client.RequestParams;
import github.tools.responseObjects.*;

public class GitOperations {
    private String directoryPath;
    private String newRepoName;

    public GitOperations(String directoryPath, String newRepoName) {
        this.directoryPath = directoryPath;
        this.newRepoName = newRepoName;
    }

    public void performGitOperations() {
        System.out.println("Performing GitOperations for directory: " + directoryPath);
    }

    public void performGitInit() {
        GitSubprocessClient gitSubprocessClient = new GitSubprocessClient(directoryPath);
        gitSubprocessClient.gitInit();
        System.out.println("Git Init was completed at " + gitSubprocessClient.getRepoPath());
    }

    public void performNewRepo(String repoName, String repoDescription, boolean repoPriv) {
    // public void performNewRepo(String repoName) {
        String username = Credentials.getUsername();
        String token = Credentials.getToken();
        GitHubApiClient gitHubApiClient = new GitHubApiClient(username, token);
        
        RequestParams requestParams = new RequestParams();
        requestParams.addParam("name", repoName);
        requestParams.addParam("description", repoDescription);
        requestParams.addParam("private", repoPriv); 

        CreateRepoResponse createRepoResponse = gitHubApiClient.createRepo(requestParams);
        
        GetRepoInfoResponse repoInfo = gitHubApiClient.getRepoInfo(username, repoName);
        System.out.println(repoInfo.getUrl());
    }

    public void performRemoteAddOrigin(String repoName) {
        String username = Credentials.getUsername();
        String token = Credentials.getToken();
        
        GitHubApiClient gitHubApiClient = new GitHubApiClient(username, token);
        GetRepoInfoResponse repoInfo = gitHubApiClient.getRepoInfo(username, repoName);
        GitSubprocessClient gitSubprocessClient = new GitSubprocessClient(directoryPath);

        String gitHubLink = repoInfo.getUrl() + ".git";
        gitSubprocessClient.gitRemoteAdd("origin", gitHubLink);
    }

    public void performGitAdd() {
        GitSubprocessClient gitSubprocessClient = new GitSubprocessClient(directoryPath);
        gitSubprocessClient.gitAddAll();
    }

    public void performGitCommit(String message) {
        GitSubprocessClient gitSubprocessClient = new GitSubprocessClient(directoryPath);
        String commit = message;
        gitSubprocessClient.gitCommit(commit);
    }

    public void performGitPush() {
        GitSubprocessClient gitSubprocessClient = new GitSubprocessClient(directoryPath);
        gitSubprocessClient.gitPush("master");
    }

    public void performGitAddFile(String fileName) {
        GitSubprocessClient gitSubprocessClient = new GitSubprocessClient(directoryPath);
        gitSubprocessClient.gitAddFile(fileName);
    }

    public String performGetGitHubURL(String repoName) {
        String username = Credentials.getUsername();
        String token = Credentials.getToken();

        GitHubApiClient gitHubApiClient = new GitHubApiClient(username, token);
        GetRepoInfoResponse repoInfo = gitHubApiClient.getRepoInfo(username, repoName);
        String url = repoInfo.getUrl();

        // Display URL in a pop-up dialog
        JOptionPane.showMessageDialog(null, "GitHub URL: " + url, "GitHub Repository URL", JOptionPane.INFORMATION_MESSAGE);

        return url;
    }
}
