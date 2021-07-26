package nl.hi.kuba.jira;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

class JiraClient {
    private static final String JQL_CURRENT_SPRINT =
            "(project = MDM) and sprint in openSprints() and (issuetype = bug or issuetype = story)";
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

    List<JiraIssue> getCurrentSprint() throws ExecutionException, InterruptedException {
        final SearchResult searchResult = restClient.getSearchClient().searchJql(JQL_CURRENT_SPRINT).get();
        final List<JiraIssue> jiraIssues = new ArrayList<>();

        for (final Issue issue : searchResult.getIssues()) {
            final Object epicObject = issue.getFieldByName("Epic Link").getValue();
            final String epicId = epicObject == null ? null : epicObject.toString();
            final String epicName = epicId == null ? "Others" : getIssue(epicId).getSummary();
            jiraIssues.add(new JiraIssue(issue.getKey(), issue.getSummary(), epicName, issue.getStatus().getName(),
                    issue.getIssueType().getName()));
        }

        return jiraIssues;
    }
}
