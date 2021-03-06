package nl.hi.kuba.jira;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Sprint {
    private Set<String> epicNames = new HashSet<>();
    private Map<String, Set<JiraIssue>> issues = new HashMap<>();

    Sprint(final List<JiraIssue> jiraIssues) {
        for (final JiraIssue issue : jiraIssues) {
            epicNames.add(issue.getEpic());

            if (!issues.containsKey(issue.getEpic())) {
                issues.put(issue.getEpic(), new HashSet<>());
            }

            issues.get(issue.getEpic()).add(issue);
        }
    }

    List<JiraIssue> getCommitments(final String epicName) {
        final List<JiraIssue> list = new ArrayList<>(issues.get(epicName));
        list.sort(Comparator.comparing(JiraIssue::getId));
        return list;
    }

    Map<String, Set<JiraIssue>> getCommitments() {
        return issues;
    }

    Set<JiraIssue> getEmergedIssues(final String epicName) {
        return new HashSet<>();
    }

    Map<String, Set<JiraIssue>> getEmergedIssues() {
        return new HashMap<>();
    }

    Set<String> getEpicNames() {
        return epicNames;
    }

    int getCommitmentsCount() {
        return getCommitmentIssues().size();
    }

    int getEmergingCount() {
        return 0;
    }

    int getSpilloversCount() {
        return getCommitmentIssues().stream().filter(issue -> !issue.getStatus().equals("DONE"))
                .collect(Collectors.toList()).size();
    }

    private Set<JiraIssue> getCommitmentIssues() {
        final Set<JiraIssue> commitmentIssues = new HashSet<>();
        issues.keySet().stream().forEach(epicName -> commitmentIssues.addAll(issues.get(epicName)));
        return commitmentIssues;
    }
}
