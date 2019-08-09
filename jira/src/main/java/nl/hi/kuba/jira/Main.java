package nl.hi.kuba.jira;

import com.atlassian.jira.rest.client.api.domain.Issue;

public class Main {
    private static String USERNAME = "";
    private static String PASSWORD = "";
    private static String URL = "";
    
    public static void main(String[] args) {
        try {
            final JiraClient client = new JiraClient(USERNAME, PASSWORD, URL);
            final String sprint = client.getSprint("Papa");
            System.err.println("MYTODO: " + sprint);
            /*
            sprint info
            - number of issues
            - commitment issues
            - added issues
            - removed issues
            */

            final Issue issue = client.getIssue("DI-104");
            /*
            issue info needed
            - v ID
            - v epic name
            - type (bug/story)
            - status
             */
            if (issue != null) {
                System.err.println(
                        "MYTODO:\n" + "id=" + issue.getKey() + "\n" + "type=" + issue.getIssueType().getName() + "\n"
                                + "epic=" + issue.getFieldByName("Epic Link").getValue() + "\n" + "status=" + issue
                                .getStatus().getName() + "\n");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
