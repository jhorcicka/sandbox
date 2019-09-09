package nl.hi.kuba.jira;

public class Main {
    private static String USERNAME = "";
    private static String PASSWORD = "";
    private static String URL = "";

    private static void printReport(final Sprint sprint) {
        System.out.println("== Epics");

        for (final String epicName : sprint.getEpicNames()) {
            System.out.println(epicName);
        }

        System.out.println("== Sprint in numbers");
        System.out.println("2 weeks");
        System.out.println(sprint.getCommitmentsCount() + " commitments");
        System.out.println(sprint.getEmergingCount() + " emerging");
        System.out.println(sprint.getSpilloversCount() + " spillovers");

        System.out.println("== Planned work");

        for (final String epicName : sprint.getCommitments().keySet()) {
            System.out.println(epicName);
            for (final JiraIssue issue : sprint.getCommitments(epicName)) {
                System.out.println(issue.toString());
            }
        }

        System.out.println("== Unplanned work");

        for (final String epicName : sprint.getEmergedIssues().keySet()) {
            System.out.println(epicName);
            for (final JiraIssue issue : sprint.getEmergedIssues(epicName)) {
                System.out.println(issue);
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            final JiraClient client = new JiraClient(USERNAME, PASSWORD, URL);
            final Sprint sprint = new Sprint(client.getCurrentSprint());
            printReport(sprint);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
