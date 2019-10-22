package nl.hi.kuba.jira;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static String URL = "https://jira.humaninference.com";
    private static String USERNAME = "j.horcicka@humaninference.com";
    private static String PASSWORD = "";

    private static void printReport(final Sprint sprint) {
        System.out.println("== Epics");
        final List<String> epics = new ArrayList<>(sprint.getEpicNames());
        Collections.sort(epics);

        for (final String epicName : epics) {
            System.out.println(epicName);
        }

        System.out.println("== Sprint in numbers");
        System.out.println("2 weeks");
        System.out.println(sprint.getCommitmentsCount() + " commitments"); // todo
        System.out.println(sprint.getEmergingCount() + " emerging"); // todo
        System.out.println(sprint.getSpilloversCount() + " spillovers"); // todo

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
