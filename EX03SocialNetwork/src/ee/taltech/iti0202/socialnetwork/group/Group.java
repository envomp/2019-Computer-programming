
package ee.taltech.iti0202.socialnetwork.group;

import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Group {

    private String groupName;
    private User groupOwner;
    private Set<User> groupUsers = new HashSet<>();
    private List<Message> groupMessages = new ArrayList<>();

    public Group(String name, User owner) {
        groupName = name;
        groupOwner = owner;
        groupUsers.add(owner);
    }

    public String getName() {
        return groupName;
    }

    public void setName(String name) {
        groupName = name;
    }

    public User getOwner() {
        return groupOwner;
    }

    public void addUser(User user) {
        groupUsers.add(user);
    }

    public Set<User> getParticipants() {
        return groupUsers;
    }

    public void publishMessage(Message message) {
        groupMessages.add(message);
    }

    public List<Message> getMessages() {
        return groupMessages;
    }

}
