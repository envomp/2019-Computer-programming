
package ee.taltech.iti0202.socialnetwork.user;

public class User {
    private String userName;
    private Integer userAge;

    public User(String name) {
        userName = name;
        userAge = null;

    }

    public User(String name, Integer age) {
        userName = name;
        userAge = age;
    }

    public String getName() {
        return userName;
    }

    public Integer getAge() {
        return userAge;
    }

}
