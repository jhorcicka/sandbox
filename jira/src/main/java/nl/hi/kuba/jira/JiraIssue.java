package nl.hi.kuba.jira;

public class JiraIssue {
    private String id;
    private String subject;
    private String epic;
    private String status;
    private String type;

    JiraIssue(final String id, final String subject, final String epic, final String status, final String type) {
        this.id = id;
        this.subject = subject;
        this.epic = epic;
        this.status = status;
        this.type = type;
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

    public String toString() {
        return getId() + " [" + getType() + "]: " + getSubject() + " " + getStatus().toUpperCase();
    }
}
