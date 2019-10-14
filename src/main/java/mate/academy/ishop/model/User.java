package mate.academy.ishop.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import mate.academy.ishop.generator.IdGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    private String login;
    private String token;
    private String password;
    @Transient
    private List<Order> completedOrders;
    @OneToOne(mappedBy = "user")
    private Bucket currentBucket;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "roles_users",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"))
    private Set<Role> roles;
    byte[] seed;

    public User(final String login) {
        userId = IdGenerator.getUserId();
        token = UUID.randomUUID().toString();
        this.login = login;
        password = new String();
        completedOrders = new ArrayList<Order>();
        roles = new HashSet<Role>();
        roles.add(Role.of("USER"));
    }

    public User() {
        userId = IdGenerator.getUserId();
        token = UUID.randomUUID().toString();
        this.login = "login";
        password = new String();
        completedOrders = new ArrayList<Order>();
        roles = new HashSet<Role>();
        roles.add(Role.of("USER"));
    }

    public User(final String login, final String password) {
        userId = IdGenerator.getUserId();
        token = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        completedOrders = new ArrayList<Order>();
        roles = new HashSet<Role>();
        roles.add(Role.of("USER"));
    }

    public User(Long userId, String login, String password, String token) {
        this.login = login;
        this.token = token;
        this.userId = userId;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public Long getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public List<Order> getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(final List<Order> completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Bucket getCurrentBucket() {
        return currentBucket;
    }

    public void setCurrentBucket(final Bucket currentBucket) {
        this.currentBucket = currentBucket;
    }

    public String getToken() {
        return token;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "userId = " + userId
                + " login " + login;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public byte[] getSeed() {
        return seed;
    }

    public void setSeed(byte[] seed) {
        this.seed = seed;
    }
}
