// DocumentJob.java
public class DocumentJob {
    private String sourceFilePath;
    private String outputFormat; // "PDF", "DOCX", "TXT"
    private String watermarkText;
    private boolean highPriority;
    private String userEmail;
    private User requestingUser;

    public DocumentJob(String source, String format, String watermark, boolean priority, User user) {
        this.sourceFilePath = source;
        this.outputFormat = format;
        this.watermarkText = watermark;
        this.highPriority = priority;
        this.requestingUser = user;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getWatermarkText() {
        return watermarkText;
    }

    public void setWatermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
    }

    public boolean isHighPriority() {
        return highPriority;
    }

    public void setHighPriority(boolean highPriority) {
        this.highPriority = highPriority;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public User getRequestingUser() {
        return requestingUser;
    }

    public void setRequestingUser(User requestingUser) {
        this.requestingUser = requestingUser;
    }

    @Override
    public String toString() {
        return "DocumentJob{" +
                "sourceFilePath='" + sourceFilePath + '\'' +
                ", outputFormat='" + outputFormat + '\'' +
                ", watermarkText='" + watermarkText + '\'' +
                ", highPriority=" + highPriority +
                ", userEmail='" + userEmail + '\'' +
                ", requestingUser=" + requestingUser +
                '}';
    }
}
