package ee.taltech.iti0202.socialnetwork;
import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.user.User;
import ee.taltech.iti0202.socialnetwork.message.Message;

import java.util.Set;

public class SocialNetwork {

    private Set<Group> networkGroups;

    public void registerGroup(Group group) {
        networkGroups.add(group);
    }

    public Set<Group> getGroups() {
        return networkGroups;
    }

    public Feed getFeedForUser(User user) {
        return new Feed(user, user.geetMessages());
    }


    public static void main(String[] args) {
        User user1 = new User("user1");
        User user2 = new User("user2", 10);
        User user3 = new User("user2", 20);
        Group group1 = new Group("group1", user1);
        group1.setName("newName");
        Group group2 = new Group("group1", user3);
        group1.addUser(user2);
        Message message1 = new Message("title1", "content1", user2);
        group1.publishMessage(message1);
        System.out.println(group1.getMessages()); // message1

        Message message2 = new Message("title2", "content2", user3);
        group2.publishMessage(message2);

        SocialNetwork socialNetwork = new SocialNetwork();
        socialNetwork.registerGroup(group1);
        socialNetwork.registerGroup(group2);
        System.out.println(socialNetwork.getGroups()); // group1 group2

        System.out.println(socialNetwork.getFeedForUser(user2)); // message1

    }
}
