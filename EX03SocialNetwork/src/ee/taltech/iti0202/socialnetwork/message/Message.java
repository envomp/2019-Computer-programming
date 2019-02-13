
package ee.taltech.iti0202.socialnetwork.message;

import ee.taltech.iti0202.socialnetwork.user.User;

public class Message {
    private String messageTitle;
    private String messageMessage;
    private User messageAuthor;

    public Message(String title, String message, User author) {
        messageTitle = title;
        messageAuthor = author;
        messageMessage = message;
    }

    public String getTitle() {
        return messageTitle;
    }

    public String getMessage() {
        return messageMessage;
    }

    public User getAuthor() {
        return messageAuthor;
    }
}
