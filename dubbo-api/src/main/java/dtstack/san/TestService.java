package dtstack.san;

import java.util.List;

public interface TestService {
    void test();

    String testString(String string);

    String getUser(int id);

    List<User> getUsers();
    String getUsersWithSchema(int id);
    String getUsersWithoutSchema(int id);
}
