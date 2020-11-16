package nl.hi.kuba.jira;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static String URL = "";
    private static String USERNAME = "";
    private static String PASSWORD = "";

    private static void printReport(final Sprint sprint) {
        put("== Epics");
        final List<String> epics = new ArrayList<>(sprint.getEpicNames());
        Collections.sort(epics);

        for (final String epicName : epics) {
            put(epicName);
        }

        put("== Sprint in numbers");
        put("2 weeks");
        put(sprint.getCommitmentsCount() + " commitments"); // todo
        put(sprint.getEmergingCount() + " emerging"); // todo
        put(sprint.getSpilloversCount() + " spillovers"); // todo

        put("== Planned work");

        for (final String epicName : epics) {
            put(epicName);
            for (final JiraIssue issue : sprint.getCommitments(epicName)) {
                put(issue.toString());
            }
        }

        put("== Unplanned work");

        for (final String epicName : sprint.getEmergedIssues().keySet()) {
            System.out.println(epicName);
            for (final JiraIssue issue : sprint.getEmergedIssues(epicName)) {
                System.out.println(issue);
            }
        }
    }

    private static void put(final String text) {
        System.out.println(text);
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
