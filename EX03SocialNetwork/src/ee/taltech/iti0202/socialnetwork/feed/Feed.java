
package ee.taltech.iti0202.socialnetwork.feed;

import ee.taltech.iti0202.socialnetwork.user.User;
import ee.taltech.iti0202.socialnetwork.message.Message;

import java.util.Set;

public class Feed {

    private User feedUser;
    private Set<Message> feedMessages;

    public Feed(User user, Set<Message> messages) {
        feedUser = user;
        feedMessages = messages;
    }

    public User getUser() {
        return feedUser;
    }

    public Set<Message> getMessages() {
        return feedMessages;
    }
}
