package tn.altercall.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tn.altercall.entities.User;

@ExtendWith(SpringExtension.class)
//@DataJpaTest
public class UserRepositoryTest {
    
    //integration test
    @Autowired(required = true)
    private UserRepository userRepository;
    
    private User user;

    @BeforeEach
    public void setUp() {

        user = new User("scott","scott@gamil.com","adminstrator");
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        user = null;
    }
    
    // Test Case for Saving a Product
//    @Test
//    public void givenUserToAddShouldReturnAddedUser(){
//
//        userRepository.save(user);
//        User fetchedUser = userRepository.findById(user.getId()).get();
//        //assertEquals(product1.getName(),product2.getName());
//        assertEquals(1, fetchedUser.getId());
//    }
    
 // Test Case to Retrieve the List of Products
 //   @Test
//    public void GivenGetAllUserShouldReturnListOfAllUsers(){
//        User user1 = new User("scott2","scott2@gamil.com","adminstrator2");
//        User user2 = new User("scott3","scott3@gamil.com","adminstrator3");
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        List<User> userList = (List<User>) userRepository.findAll();
//        assertEquals("bat", userList.get(1).getUsername());
//
//    }

}
