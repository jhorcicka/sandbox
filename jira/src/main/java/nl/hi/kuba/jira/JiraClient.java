package nl.hi.kuba.jira;

import java.net.URI;
import java.util.concurrent.ExecutionException;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

class JiraClient {
    private String username;
    private String password;
    private String jiraUrl;
    private JiraRestClient restClient;

    JiraClient(String username, String password, String jiraUrl) {
        this.username = username;
        this.password = password;
        this.jiraUrl = jiraUrl;
        this.restClient = getJiraRestClient();
    }

    private JiraRestClient getJiraRestClient() {
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }

    private URI getJiraUri() {
        return URI.create(this.jiraUrl);
    }

    Issue getIssue(final String issueId) throws ExecutionException, InterruptedException {
        IssueRestClient issueClient = restClient.getIssueClient();
        final Promise<Issue> issue = issueClient.getIssue(issueId);
        return issue.get();
    }

    String getSprint(final String sprintId) throws ExecutionException, InterruptedException {
        final SearchResult searchResult = restClient.getSearchClient().searchJql(
                "(project = DC or project = DI) and sprint in openSprints() and (issuetype = bug or issuetype = story)")
                .get();
        for (Issue issue : searchResult.getIssues()) {
            System.err.println("issue: " + issue.getKey() + ": " + issue.getSummary());
        }
        
        /*
        ProjectRestClient projectRestClient = restClient.getProjectClient();
        final Promise<Project> projectPromise = projectRestClient.getProject("DC");
        final Project project = projectPromise.get();
        project.
        for (BasicComponent bc : project.getComponents()) {
            System.err.println("MYTODO: " + bc.getName());
        }
        */
        return "todo";
    }
}
