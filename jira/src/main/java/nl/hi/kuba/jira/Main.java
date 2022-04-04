package nl.hi.kuba.jira;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Main {
    private static String URL = "";
    private static String USERNAME = "";
    private static String PASSWORD = "";

    public static void main(String[] args) {
        try {
            final JiraClient client = new JiraClient(USERNAME, PASSWORD, URL);
            //printTeamPerformance(client);
            printSprintReport(new Sprint(client.getCurrentSprint()));
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    private static void printTeamPerformance(final JiraClient client) throws ExecutionException, InterruptedException {
        // year 2022
        final int firstSprintId = 1907;
        final int lastSprintId = 1918; // 2022-03-22
        for (int id = firstSprintId; id <= lastSprintId; id++) {
            final List<JiraIssue> sprintIssues = client.getSprintIssues(id);
            final int allIssues = sprintIssues.stream().collect(Collectors.toList()).size();
            final int doneIssues = sprintIssues.stream().filter(issue -> issue.getStatus().equals("Done")).collect(Collectors.toList()).size();
            if (allIssues != 0) {
                put("Sprint #" + id + ": " + doneIssues + "/" + allIssues);
                put(new Sprint(sprintIssues).getEpicNames().toString());
            }
        }
    }

    private static void printSprintReport(final Sprint sprint) {
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

        put("== Issues");

        for (final String progressStatus : Sprint.PROGRESS_STATES) {
            put("== " + progressStatus);
            for (final JiraIssue issue : sprint.getIssuesByProgressStatus(progressStatus)) {
                put(issue.toString());
            }
        }
    }

    private static void put(final String text) {
        System.out.println(text);
    }
}
