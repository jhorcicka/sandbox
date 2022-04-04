package nl.hi.kuba.jira;

import java.util.Set;

public class JiraIssue {
    private String id;
    private String subject;
    private String epic;
    private String status;
    private String type;
    private Set<String> labels;

    JiraIssue(final String id, final String subject, final String epic, final String status, final String type, final Set<String> labels) {
        this.id = id;
        this.subject = subject;
        this.epic = epic;
        this.status = status;
        this.type = type;
        this.labels = labels;
    }

    public String getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getEpic() {
        return epic;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public Set<String> getLabels() {
        return labels;
    }

    public String toString() {
        final String labelsPart = labels.isEmpty() ? "" : " {" + this.labels + "}";
        return getId() + " [" + getType() + "]: " + getSubject() + labelsPart;
    }
}
