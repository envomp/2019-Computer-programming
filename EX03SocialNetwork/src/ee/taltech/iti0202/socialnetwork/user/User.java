
package ee.taltech.iti0202.socialnetwork.user;

import ee.taltech.iti0202.socialnetwork.message.Message;

import java.util.Set;

public class User {
    private String userName;
    private Integer userAge;
    private Set<Message> userMessages;

    public User(String name) {
        userName = name;
        userAge = null;
    }

    public User(String name, Integer age) {
        userName = name;
        userAge = age;
    }

    public void addMessage(Message message){
        userMessages.add(message);
    }

    public String getName() {
        return userName;
    }

    public Integer getAge() {
        return userAge;
    }

    public Set<Message> geetMessages() {
        return userMessages;
    }

}
